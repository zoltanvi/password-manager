package Test;

import controller.PasswordManagerController;
import model.PasswordManagerDAO;
import org.jasypt.exceptions.EncryptionOperationNotPossibleException;
import org.jasypt.util.text.BasicTextEncryptor;
import view.PasswordManagerGUI;

import javax.swing.*;

public class TestSQL {


    public static void main(String[] args) {
        PasswordManagerDAO dao = new PasswordManagerDAO();

       if(dao.existUser("zooli")){
           System.out.println("létezik");
       } else {
           System.out.println("nemlétezik :(");
       }


        System.out.println(dao.getPassword("admin"));
    }

}
