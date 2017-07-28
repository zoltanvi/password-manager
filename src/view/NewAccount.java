package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JTextField;
import controller.Menu;
import controller.PropertiesController;

import javax.swing.JPasswordField;
import javax.swing.JButton;


public class NewAccount{

	JPanel newAcc;
	private JTextField txtUsername;
	private JPasswordField txtPass1;
	private JPasswordField txtPass2;

	
	public NewAccount() {
		PropertiesController props = new PropertiesController();
		Menu menu = new Menu();
		newAcc = new JPanel();
		newAcc.setLayout(new BorderLayout(0, 0));
		JPanel panel_1 = new JPanel();
		newAcc.add(panel_1, BorderLayout.NORTH);

		JLabel lblRegistration = new JLabel(props.getProp("registration"));
		lblRegistration.setFont(new Font("Tahoma", Font.PLAIN, 38));
		panel_1.add(lblRegistration);

		JPanel panel_2 = new JPanel();
		newAcc.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(null);

		JLabel lblUsername = new JLabel(props.getProp("username"));
		lblUsername.setBounds(74, 92, 132, 16);
		panel_2.add(lblUsername);
		
		JLabel lblPassword = new JLabel(props.getProp("password"));
		lblPassword.setBounds(74, 149, 173, 16);
		panel_2.add(lblPassword);
		
		JLabel lblPasswordAgain = new JLabel(props.getProp("passwordAgain"));
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
		txtPass1.addActionListener(menu);
		txtPass2.addActionListener(menu);
		txtUsername.addActionListener(menu);
		panel_2.add(txtPass2);
		
		JButton btnRegistration = new JButton(props.getProp("regButton"));
		btnRegistration.addActionListener(menu);
	
		btnRegistration.setBounds(278, 288, 151, 25);
		panel_2.add(btnRegistration);

	}
}
