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
                //Labels.MENUBAR_FIRST_LOGIN,
                //Labels.MENUBAR_FIRST_LOGOUT,
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
                //Labels.MENUBAR_FIRST_REGISTRATION,
                Labels.MENUBAR_FIRST_LOGIN,
                //Labels.MENUBAR_FIRST_LOGOUT,
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
                //Labels.MENUBAR_FIRST_LOGIN,
                //Labels.MENUBAR_FIRST_LOGOUT,
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
                //Labels.MENUBAR_FIRST_REGISTRATION,
                //Labels.MENUBAR_FIRST_LOGIN,
                Labels.MENUBAR_FIRST_LOGOUT,
                Labels.MENUBAR_FIRST_EXIT);
        createMenu(Labels.MENUBAR_SECOND);
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
            System.out.println(Labels.MENUBAR_FIRST_REGISTRATION);
        }
        if (actionCommand.equals(Labels.MENUBAR_FIRST_LOGIN)) {
            gui.switchToLog();
            System.out.println(Labels.MENUBAR_FIRST_LOGIN);
        }
        if (actionCommand.equals(Labels.MENUBAR_FIRST_EXIT)) {
            gui.exit();
            System.out.println(Labels.MENUBAR_FIRST_EXIT);
        }


    }
}
