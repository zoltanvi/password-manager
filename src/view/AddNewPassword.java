package view;

import model.Password;
import org.jasypt.util.text.BasicTextEncryptor;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class AddNewPassword {
    JPanel contentPanel;
    private JPanel panel;
    private JTextField txtWebpage;
    private JTextField txtUsername;
    private JPasswordField txtPassword2;
    private JPasswordField txtPassword1;
    private PasswordManagerGUI gui;

    public AddNewPassword(PasswordManagerGUI gui) {
        panel = new JPanel();
        contentPanel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(contentPanel, BorderLayout.CENTER);

        this.gui = gui;

        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPanel.setLayout(new BorderLayout(0, 0));
        {
            JPanel panelTitle = new JPanel();
            contentPanel.add(panelTitle, BorderLayout.NORTH);
            {
                JLabel lblAddNewPassword = new JLabel("Add New Password");
                lblAddNewPassword.setFont(new Font("Tahoma", Font.PLAIN, 25));
                panelTitle.add(lblAddNewPassword);
            }
        }
        {
            JPanel panel = new JPanel();
            contentPanel.add(panel, BorderLayout.CENTER);
            GridBagLayout gbl_panel = new GridBagLayout();
            gbl_panel.columnWidths = new int[]{125, 38, 236, 0, 0};
            gbl_panel.rowHeights = new int[]{0, 27, 10, 27, 0, 0, 0, 0, 0, 0};
            gbl_panel.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
            gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
            panel.setLayout(gbl_panel);
            {
                Component verticalStrut = Box.createVerticalStrut(20);
                GridBagConstraints gbc_verticalStrut = new GridBagConstraints();
                gbc_verticalStrut.insets = new Insets(0, 0, 5, 5);
                gbc_verticalStrut.gridx = 2;
                gbc_verticalStrut.gridy = 0;
                panel.add(verticalStrut, gbc_verticalStrut);
            }
            {
                JLabel lblWebpage = new JLabel("Webpage");
                GridBagConstraints gbc_lblWebpage = new GridBagConstraints();
                gbc_lblWebpage.anchor = GridBagConstraints.EAST;
                gbc_lblWebpage.insets = new Insets(0, 0, 5, 5);
                gbc_lblWebpage.gridx = 0;
                gbc_lblWebpage.gridy = 1;
                panel.add(lblWebpage, gbc_lblWebpage);
            }
            {
                txtWebpage = new JTextField();
                GridBagConstraints gbc_txtWebpage = new GridBagConstraints();
                gbc_txtWebpage.insets = new Insets(0, 0, 5, 5);
                gbc_txtWebpage.fill = GridBagConstraints.BOTH;
                gbc_txtWebpage.gridx = 2;
                gbc_txtWebpage.gridy = 1;
                panel.add(txtWebpage, gbc_txtWebpage);
                txtWebpage.setColumns(10);
            }
            {
                Component horizontalStrut = Box.createHorizontalStrut(20);
                GridBagConstraints gbc_horizontalStrut = new GridBagConstraints();
                gbc_horizontalStrut.insets = new Insets(0, 0, 5, 0);
                gbc_horizontalStrut.gridx = 3;
                gbc_horizontalStrut.gridy = 1;
                panel.add(horizontalStrut, gbc_horizontalStrut);
            }
            {
                Component verticalStrut = Box.createVerticalStrut(20);
                GridBagConstraints gbc_verticalStrut = new GridBagConstraints();
                gbc_verticalStrut.insets = new Insets(0, 0, 5, 5);
                gbc_verticalStrut.gridx = 0;
                gbc_verticalStrut.gridy = 2;
                panel.add(verticalStrut, gbc_verticalStrut);
            }
            {
                JLabel lblUsername = new JLabel("Username");
                GridBagConstraints gbc_lblUsername = new GridBagConstraints();
                gbc_lblUsername.anchor = GridBagConstraints.EAST;
                gbc_lblUsername.insets = new Insets(0, 0, 5, 5);
                gbc_lblUsername.gridx = 0;
                gbc_lblUsername.gridy = 3;
                panel.add(lblUsername, gbc_lblUsername);
            }
            {
                Component horizontalStrut = Box.createHorizontalStrut(20);
                GridBagConstraints gbc_horizontalStrut = new GridBagConstraints();
                gbc_horizontalStrut.insets = new Insets(0, 0, 5, 5);
                gbc_horizontalStrut.gridx = 1;
                gbc_horizontalStrut.gridy = 3;
                panel.add(horizontalStrut, gbc_horizontalStrut);
            }
            {
                txtUsername = new JTextField();
                GridBagConstraints gbc_txtUsername = new GridBagConstraints();
                gbc_txtUsername.insets = new Insets(0, 0, 5, 5);
                gbc_txtUsername.fill = GridBagConstraints.HORIZONTAL;
                gbc_txtUsername.gridx = 2;
                gbc_txtUsername.gridy = 3;
                panel.add(txtUsername, gbc_txtUsername);
                txtUsername.setColumns(10);
            }
            {
                Component verticalStrut = Box.createVerticalStrut(20);
                GridBagConstraints gbc_verticalStrut = new GridBagConstraints();
                gbc_verticalStrut.insets = new Insets(0, 0, 5, 5);
                gbc_verticalStrut.gridx = 0;
                gbc_verticalStrut.gridy = 4;
                panel.add(verticalStrut, gbc_verticalStrut);
            }
            {
                JLabel lblPassword = new JLabel("Password");
                GridBagConstraints gbc_lblPassword = new GridBagConstraints();
                gbc_lblPassword.anchor = GridBagConstraints.EAST;
                gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
                gbc_lblPassword.gridx = 0;
                gbc_lblPassword.gridy = 5;
                panel.add(lblPassword, gbc_lblPassword);
            }
            {
                txtPassword1 = new JPasswordField();
                GridBagConstraints gbc_txtPassword1 = new GridBagConstraints();
                gbc_txtPassword1.insets = new Insets(0, 0, 5, 5);
                gbc_txtPassword1.fill = GridBagConstraints.HORIZONTAL;
                gbc_txtPassword1.gridx = 2;
                gbc_txtPassword1.gridy = 5;
                panel.add(txtPassword1, gbc_txtPassword1);
            }
            {
                Component verticalStrut = Box.createVerticalStrut(20);
                GridBagConstraints gbc_verticalStrut = new GridBagConstraints();
                gbc_verticalStrut.insets = new Insets(0, 0, 5, 5);
                gbc_verticalStrut.gridx = 0;
                gbc_verticalStrut.gridy = 6;
                panel.add(verticalStrut, gbc_verticalStrut);
            }
            {
                JLabel lblPasswordAgain = new JLabel("Password again");
                GridBagConstraints gbc_lblPasswordAgain = new GridBagConstraints();
                gbc_lblPasswordAgain.anchor = GridBagConstraints.EAST;
                gbc_lblPasswordAgain.insets = new Insets(0, 0, 5, 5);
                gbc_lblPasswordAgain.gridx = 0;
                gbc_lblPasswordAgain.gridy = 7;
                panel.add(lblPasswordAgain, gbc_lblPasswordAgain);
            }
            {
                txtPassword2 = new JPasswordField();
                GridBagConstraints gbc_txtPassword2 = new GridBagConstraints();
                gbc_txtPassword2.anchor = GridBagConstraints.NORTH;
                gbc_txtPassword2.insets = new Insets(0, 0, 5, 5);
                gbc_txtPassword2.fill = GridBagConstraints.HORIZONTAL;
                gbc_txtPassword2.gridx = 2;
                gbc_txtPassword2.gridy = 7;
                panel.add(txtPassword2, gbc_txtPassword2);
            }
        }
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setBackground(UIManager.getColor("CheckBox.light"));
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            contentPanel.add(buttonPane, BorderLayout.SOUTH);

            {
                JButton okButton = new JButton("OK");
                okButton.addActionListener(arg0 ->
                {
                    if(txtPassword1.getText().equals(txtPassword2.getText()) && txtPassword1.getText().length() != 0){
                        Password pass = new Password();
                        BasicTextEncryptor encryptor = new BasicTextEncryptor();
                        encryptor.setPassword(gui.getSessionPassword());

                        pass.setUsername(gui.getSessionUsername());

                        pass.setWebpage(encryptor.encrypt(txtWebpage.getText()));
                        pass.setP_username(encryptor.encrypt(txtUsername.getText()));
                        pass.setP_password(encryptor.encrypt(txtPassword1.getText()));
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
                    txtWebpage.setText(null);
                    txtPassword1.setText(null);
                    txtPassword2.setText(null);
                    txtUsername.setText(null);

                });
                okButton.setActionCommand("OK");
                buttonPane.add(okButton);
            }
            {
                JButton cancelButton = new JButton("Cancel");
                cancelButton.addActionListener(e ->
                {
                    gui.switchToPass();
                });
                cancelButton.setActionCommand("Cancel");
                buttonPane.add(cancelButton);
            }

        }
    }

}
