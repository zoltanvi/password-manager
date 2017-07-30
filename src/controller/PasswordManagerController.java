package controller;

import model.Account;
import model.Password;
import model.PasswordManagerDAO;
import org.jasypt.exceptions.EncryptionOperationNotPossibleException;
import org.jasypt.util.text.BasicTextEncryptor;
import view.Labels;
import view.PasswordManagerGUI;
import view.PasswordManagerPasswords;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class PasswordManagerController implements ActionListener{

    private PasswordManagerDAO dao;
    private PasswordManagerGUI gui;

    public PasswordManagerController(PasswordManagerGUI gui){
        this.gui = gui;
        dao = new PasswordManagerDAO();
    }

    /** Logging in the user */
    public void loginUser(){
        String usrnm = gui.getLogin().getTxtUsername().getText();
        String pwd = gui.getLogin().getTxtPass1().getText();

        if(usrnm.length() != 0 && pwd.length() != 0){
            gui.setSessionUsername(usrnm);
            gui.setSessionPassword(pwd);
            gui.getLblUser().setText(usrnm + "  ");
            BasicTextEncryptor encryptor = new BasicTextEncryptor();
            encryptor.setPassword(pwd);

            try{
                if(encryptor.decrypt(dao.getPassword(usrnm)).equals(gui.getSessionPassword())){
                    System.out.println("Successfully logged in!");
                    JOptionPane.showMessageDialog(gui.getPanel(),
                            "Successfully logged in as " + usrnm + "!",
                            "Login", JOptionPane.PLAIN_MESSAGE);
                    gui.switchToLoggedIn();
                }

            } catch(EncryptionOperationNotPossibleException xx){
                JOptionPane.showMessageDialog(gui.getPanel(),
                        "Wrong password!",
                        "Password", JOptionPane.ERROR_MESSAGE);
            }catch(NullPointerException x) {
                JOptionPane.showMessageDialog(gui.getPanel(),
                        "This username not exist!",
                        "Username", JOptionPane.ERROR_MESSAGE);
            }
        }
   }

    /** Register an Account */
    public void regUser() {
        String usrnm = gui.getRegistration().getTxtUsername().getText();
        String pwd = gui.getRegistration().getTxtPass1().getText();
        String pwda = gui.getRegistration().getTxtPass2().getText();

        if (dao.existsUser(usrnm) == false &&
                usrnm.length() != 0 &&
                pwd.equals(pwda) &&
                pwd.length() != 0){

            BasicTextEncryptor encryptor = new BasicTextEncryptor();
            encryptor.setPassword(pwd);

            try {
                Account acc = new Account();
                acc.setUsername(usrnm);
                acc.setPassword(encryptor.encrypt(pwd));
                if (dao.addAccount(acc)){
                    JOptionPane.showMessageDialog(gui.getPanel(),
                            "Successfully registered as " + usrnm + "!",
                            "Registration", JOptionPane.PLAIN_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(gui.getPanel(),
                            "Failed to register this user!",
                            "Registration", JOptionPane.ERROR_MESSAGE);
                }

            }catch (Exception x){
                JOptionPane.showMessageDialog(gui.getPanel(),
                        "Something went wrong!",
                        "Registration", JOptionPane.ERROR_MESSAGE);
                x.printStackTrace();
            }
        } else if(usrnm.length() == 0){
            JOptionPane.showMessageDialog(gui.getPanel(),
                    "The username cannot be empty!",
                    "Registration", JOptionPane.ERROR_MESSAGE);
        } else if(!pwd.equals(pwda)){
            JOptionPane.showMessageDialog(gui.getPanel(),
                    "Passwords do not match!",
                    "Registration", JOptionPane.ERROR_MESSAGE);
        } else if(pwd.length() == 0 || pwda.length() == 0){
            JOptionPane.showMessageDialog(gui.getPanel(),
                    "No password fields can be empty!",
                    "Registration", JOptionPane.ERROR_MESSAGE);
        } else if(dao.existsUser(usrnm)){
            JOptionPane.showMessageDialog(gui.getPanel(),
                    "This username is already taken!",
                    "Registration", JOptionPane.ERROR_MESSAGE);
        }

        //checking all user
        System.out.println(dao.getAllUser());

    }

    /** Logging out the user */
    public void logoutUser(){
        gui.setSessionPassword(null);
        gui.setSessionUsername(null);
        gui.getLogin().getTxtPass1().setText(null);
        gui.getLogin().getTxtUsername().setText(null);
        //gui.getPass().clearTable();
        gui.switchToLog();
        JOptionPane.showMessageDialog(gui.getPanel(),
                "Successfully logged out!",
                "Logout", JOptionPane.PLAIN_MESSAGE);
    }

    public void closeConnection(){
        dao.closeConnection();
    }

    public void openPasswords(){
        gui.switchToPass();
    }

    public List<Password> getPassw(String username){

        List<Password> rePassList = new ArrayList<>();

        BasicTextEncryptor encryptor = new BasicTextEncryptor();
        encryptor.setPassword(gui.getSessionPassword());
        for (Password temp : dao.getUserPasswords(username)) {
          try {
              Password rePass = new Password();
              rePass.setUsername(username);
              rePass.setWebpage(encryptor.decrypt(temp.getWebpage()));
              rePass.setP_username(encryptor.decrypt(temp.getP_username()));
              rePass.setP_password(encryptor.decrypt(temp.getP_password()));
              rePassList.add(rePass);
          } catch (Exception x){
              x.printStackTrace();
          }
        }
        return rePassList;
    }

    public void addNewPassword(Password password){
        dao.addPassword(password);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand =  e.getActionCommand();

        if(actionCommand.equals(Labels.LOGIN_LOGINBUTTON)
                || actionCommand.equals(Labels.LOGIN_PASSFIELD)
                || actionCommand.equals(Labels.LOGIN_USERFIELD)){
            loginUser();

        }
        if(actionCommand.equals(Labels.REG_PASS1FIELD)
                || actionCommand.equals(Labels.REG_PASS2FIELD)
                || actionCommand.equals(Labels.REG_REGBUTTON)
                || actionCommand.equals(Labels.REG_USERFIELD)){
            regUser();
        }

    }
}

