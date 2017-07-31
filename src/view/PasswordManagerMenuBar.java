package view;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PasswordManagerMenuBar extends JMenuBar implements ActionListener{

    private PasswordManagerGUI gui;
     PasswordManagerMenuBar(PasswordManagerGUI gui){
        super();
        this.gui = gui;

        createMenu(Labels.MENUBAR_FIRST,
                Labels.MENUBAR_FIRST_REGISTRATION,
                Labels.MENUBAR_FIRST_EXIT);
        createMenu(Labels.MENUBAR_SECOND);
        createMenu(Labels.MENUBAR_THIRD);
        createMenu(Labels.MENUBAR_FOURTH);
    }

    private void createMenu(String name, String... subnames){
        JMenu menu = new JMenu(name);
        this.add(menu);

        for(String subname : subnames){
            JMenuItem menuItem = new JMenuItem(subname);
            menu.add(menuItem);
            menuItem.addActionListener(this);
        }
    }

    public void regMenuPopulate(){
        this.removeAll();
        createMenu(Labels.MENUBAR_FIRST,
                Labels.MENUBAR_FIRST_LOGIN,
                Labels.MENUBAR_FIRST_EXIT);
        createMenu(Labels.MENUBAR_SECOND);
        createMenu(Labels.MENUBAR_THIRD);
        createMenu(Labels.MENUBAR_FOURTH);
        this.repaint();
        this.revalidate();
    }

    public void loginMenuPopulate(){
        this.removeAll();
        createMenu(Labels.MENUBAR_FIRST,
                Labels.MENUBAR_FIRST_REGISTRATION,
                Labels.MENUBAR_FIRST_EXIT);
        createMenu(Labels.MENUBAR_SECOND);
        createMenu(Labels.MENUBAR_THIRD);
        createMenu(Labels.MENUBAR_FOURTH);
        this.repaint();
        this.revalidate();
    }
    public void loggedinMenuPopulate(){
        this.removeAll();
        createMenu(Labels.MENUBAR_FIRST,
                Labels.MENUBAR_FIRST_LOGOUT,
                Labels.MENUBAR_FIRST_EXIT);
        createMenu(Labels.MENUBAR_SECOND,
                Labels.MENUBAR_SECOND_PASSWORDS);
        createMenu(Labels.MENUBAR_THIRD,
                Labels.MENUBAR_THIRD_IMPORT);
        createMenu(Labels.MENUBAR_FOURTH,
                Labels.MENUBAR_FOURTH_EXPORT);
        this.repaint();
        this.revalidate();
    }

    public void passwordsMenuPopulate(){
        this.removeAll();
        createMenu(Labels.MENUBAR_FIRST,
                Labels.MENUBAR_FIRST_LOGOUT,
                Labels.MENUBAR_FIRST_EXIT);
        createMenu(Labels.MENUBAR_SECOND,
                Labels.MENUBAR_SECOND_PASSWORDS);
        createMenu(Labels.MENUBAR_THIRD);
        createMenu(Labels.MENUBAR_FOURTH);
        this.repaint();
        this.revalidate();


    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();

        System.out.println("Activated the '" + actionCommand + "' menu");

        if (actionCommand.equals(Labels.MENUBAR_FIRST_REGISTRATION)) {
            gui.switchToReg();
            gui.getRegistration().getTxtPass1().setText(null);
            gui.getRegistration().getTxtPass2().setText(null);
            gui.getRegistration().getTxtUsername().setText(null);
        }
        if (actionCommand.equals(Labels.MENUBAR_FIRST_LOGIN)) {
            gui.switchToLog();
            gui.getLogin().getTxtUsername().setText(null);
            gui.getLogin().getTxtPass1().setText(null);
        }
        if (actionCommand.equals(Labels.MENUBAR_FIRST_EXIT)) {
            gui.exit();
        }
        if(actionCommand.equals(Labels.MENUBAR_FIRST_LOGOUT)){
            gui.getController().logoutUser();
        }
        if(actionCommand.equals(Labels.MENUBAR_SECOND_PASSWORDS)){
            gui.getController().openPasswords();

        }
        if(actionCommand.equals(Labels.MENUBAR_THIRD_IMPORT)){
            gui.switchToInp();
        }
        if(actionCommand.equals(Labels.MENUBAR_FOURTH_EXPORT)){
            gui.switchToExp();
        }


    }
}
