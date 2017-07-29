package Test;

import controller.PasswordManagerController;
import model.Account;
import model.Password;
import model.PasswordManagerDAO;
import org.jasypt.exceptions.EncryptionOperationNotPossibleException;
import org.jasypt.util.text.BasicTextEncryptor;
import view.PasswordManagerGUI;

import javax.swing.*;

public class TestSQL {


    public static void main(String[] args) {
        PasswordManagerDAO dao = new PasswordManagerDAO();

        Password pw = new Password();
        pw.setUsername("nerf");
        pw.setWebpage("http://www.google.com");
        pw.setP_username("albert");
        pw.setP_password("never_and_ever");

           dao.addPassword(pw);

        System.out.println(dao.getUserPasswords("nerf"));
        System.out.println("ALL USER\n\n");
        System.out.println(dao.getAllUser());



    }

}
