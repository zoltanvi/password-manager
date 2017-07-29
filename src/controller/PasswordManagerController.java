package controller;

import model.PasswordManagerDAO;
import org.jasypt.exceptions.EncryptionOperationNotPossibleException;
import org.jasypt.util.text.BasicTextEncryptor;
import view.Labels;
import view.PasswordManagerGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PasswordManagerController implements ActionListener{

    private PasswordManagerDAO dao;
    private PasswordManagerGUI gui;

    public PasswordManagerController(PasswordManagerGUI gui){
        this.gui = gui;
        dao = new PasswordManagerDAO();
    }

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
                            "Login",  JOptionPane.PLAIN_MESSAGE);
                    gui.switchToLoggedIn();
                }

            } catch(EncryptionOperationNotPossibleException xx){
                JOptionPane.showMessageDialog(gui.getPanel(),
                        "Wrong password!",
                        "Password",
                        JOptionPane.ERROR_MESSAGE);
            }catch(NullPointerException x) {
                JOptionPane.showMessageDialog(gui.getPanel(),
                        "This username not exist!",
                        "Username",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
   }

    public void regUser(){

        /*
        if (GUI.server.isUser(txtUsername.getText()) == false) {

			if (txtPass1.getText().equals(txtPass2.getText())
					&& txtPass1.getText().length() != 0) {

				GUI.SESSION_PASSWORD = txtPass1.getText();
				BasicTextEncryptor encryptor = new BasicTextEncryptor();
				encryptor.setPasswordCharArray(txtPass1.getPassword());

				try {
					if (GUI.server.newProfil(txtUsername.getText(),
							encryptor.encrypt(txtPass1.getText())) != 0) {
						JOptionPane.showMessageDialog(panel,
								"Sikeres regisztracio!", "",
								JOptionPane.PLAIN_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(panel,
								"Sikertelen regisztracio!", "Hiba!",
								JOptionPane.PLAIN_MESSAGE);
					}
				} catch (Exception e2) {
					System.out.println("Hiba a kodolassal!");
				}

			} else if (txtUsername.getText().length() == 0) {
				JOptionPane.showMessageDialog(panel,
						"A felhasznalonev nem lehet ures!", "Hiba!",
						JOptionPane.PLAIN_MESSAGE);
			} else if(txtPass1.getText().length() == 0 || txtPass2.getText().length() == 0){
				JOptionPane.showMessageDialog(panel, "Egyik jelszo mezo sem lehet ures!",
						"Hiba!", JOptionPane.PLAIN_MESSAGE);
			} else if ((txtPass1.getText().equals(txtPass2.getText())) == false) {
				JOptionPane.showMessageDialog(panel,
						"A jelszavak nem egyeznek!", "Hiba!",
						JOptionPane.PLAIN_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(panel, "Ezzel a felhasznalonevvel mar regisztraltak!",
					"Hiba!", JOptionPane.PLAIN_MESSAGE);
		}

		System.out.println("Felhasználók listája:\n");
			GUI.server.printEveryOne();
		System.out.println("##");

         */


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
            // TODO REGISTRATION
        }

    }
}

