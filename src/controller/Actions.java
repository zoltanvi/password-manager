package controller;

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

    }
}
