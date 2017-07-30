package view;

import com.sun.javafx.image.impl.BaseIntToByteConverter;
import model.Password;
import org.jasypt.util.text.BasicTextEncryptor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddNewPass extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField txtWebpage;
    private JTextField txtUsername;
    private JPasswordField txtPass1;
    private JPasswordField txtPass2;
    private JLabel lblWebpage;
    private JLabel lblUsername;
    private JLabel lblPassword;
    private JLabel lblPasswordAgain;
    private PasswordManagerGUI gui;

    public AddNewPass(PasswordManagerGUI gui) {
        this.gui = gui;
        pack();
        setVisible(true);
        this.setResizable(false);
        this.setSize(400,300);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);


        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        if(txtPass1.getText().equals(txtPass2.getText()) && txtPass1.getText().length() != 0){
            Password pass = new Password();
            BasicTextEncryptor encryptor = new BasicTextEncryptor();
            encryptor.setPassword(gui.getSessionPassword());

            pass.setUsername(gui.getSessionUsername());

            pass.setWebpage(encryptor.encrypt(txtWebpage.getText()));
            pass.setP_username(encryptor.encrypt(txtUsername.getText()));
            pass.setP_password(encryptor.encrypt(txtPass1.getText()));
            try{
                gui.getController().addNewPassword(pass);
                JOptionPane.showMessageDialog(gui.getPanel(),
                        "Password added successfully!",
                        "Password added", JOptionPane.PLAIN_MESSAGE);

            } catch (Exception x){
                x.printStackTrace();
                JOptionPane.showMessageDialog(gui.getPanel(),
                        "Something went wrong!",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }

        } else {
            JOptionPane.showMessageDialog(gui.getPanel(),
                    "Passwords do not match or empty!",
                    "Password", JOptionPane.ERROR_MESSAGE);
        }
        //dispose();

    }

    private void onCancel() {
        // add your code here if necessary
        this.setVisible(false);
        //this.dispose();
        //dispose();
    }

}
