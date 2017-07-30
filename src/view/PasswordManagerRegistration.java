package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Font;


public class PasswordManagerRegistration {

	JPanel regpanel;
	private JTextField txtUsername;
	private JPasswordField txtPass1;
	private JPasswordField txtPass2;
    private  PasswordManagerGUI gui;

	public PasswordManagerRegistration(PasswordManagerGUI gui) {
        this.gui = gui;
		regpanel = new JPanel();
		regpanel.setLayout(new BorderLayout(0, 0));
		JPanel panel_1 = new JPanel();
		regpanel.add(panel_1, BorderLayout.NORTH);

		JLabel lblRegistration = new JLabel(Labels.REG_REGISTRATION);
		lblRegistration.setFont(new Font("Tahoma", Font.PLAIN, 38));
		panel_1.add(lblRegistration);

		JPanel panel_2 = new JPanel();
		regpanel.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(null);

		JLabel lblUsername = new JLabel(Labels.REG_USERNAME);
		lblUsername.setBounds(74, 92, 132, 16);
		panel_2.add(lblUsername);
		
		JLabel lblPassword = new JLabel(Labels.REG_PASSWORD);
		lblPassword.setBounds(74, 149, 173, 16);
		panel_2.add(lblPassword);
		
		JLabel lblPasswordAgain = new JLabel(Labels.REG_RE_PASSWORD);
		lblPasswordAgain.setBounds(74, 204, 173, 16);
		panel_2.add(lblPasswordAgain);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(252, 89, 380, 22);
		panel_2.add(txtUsername);
		txtUsername.setColumns(10);
		
		txtPass1 = new JPasswordField();
		txtPass1.setBounds(252, 146, 380, 22);
		panel_2.add(txtPass1);
		
		txtPass2 = new JPasswordField();
		txtPass2.setBounds(252, 201, 380, 22);
		txtPass1.addActionListener(gui.getController());
		txtPass1.setActionCommand(Labels.REG_PASS1FIELD);
		txtPass2.addActionListener(gui.getController());
        txtPass2.setActionCommand(Labels.REG_PASS2FIELD);
		txtUsername.addActionListener(gui.getController());
        txtUsername.setActionCommand(Labels.REG_USERFIELD);
		panel_2.add(txtPass2);
		
		JButton btnRegistration = new JButton(Labels.REG_REGBUTTON);
		btnRegistration.addActionListener(gui.getController());
	
		btnRegistration.setBounds(278, 288, 151, 25);
		panel_2.add(btnRegistration);

	}

    public JTextField getTxtUsername() {
        return txtUsername;
    }

    public JPasswordField getTxtPass1() {
        return txtPass1;
    }

    public JPasswordField getTxtPass2() {
        return txtPass2;
    }
}
