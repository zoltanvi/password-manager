package controller;

import view.Labels;
import view.PasswordManagerLogin;
import view.PasswordManagerRegistration;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Actions implements ActionListener {
    PasswordManagerLogin login;
    PasswordManagerRegistration reg;

    public Actions(PasswordManagerLogin login){
        this.login = login;
    }

    public Actions(PasswordManagerRegistration reg){
        this.reg = reg;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       String actionCommand =  e.getActionCommand();

       if(actionCommand.equals(Labels.LOGIN_LOGINBUTTON)
               || actionCommand.equals(Labels.LOGIN_PASSFIELD)
               || actionCommand.equals(Labels.LOGIN_USERFIELD)){
           // TODO LOGIN

       }
       if(actionCommand.equals(Labels.REG_PASS1FIELD)
               || actionCommand.equals(Labels.REG_PASS2FIELD)
               || actionCommand.equals(Labels.REG_REGBUTTON)
               || actionCommand.equals(Labels.REG_USERFIELD)){
            // TODO REGISTRATION
       }

    }
}
