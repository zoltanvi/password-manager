package view;

import controller.Actions;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class PasswordManagerLogin {
    Actions actions = new Actions(this);
	JPanel loginpanel;
	private JTextField txtUsername;
	private JPasswordField txtPass1;
	
	public PasswordManagerLogin() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			System.out.println("Unable to set LookAndFeel");
			e.printStackTrace();
		}
		loginpanel = new JPanel();
		loginpanel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		loginpanel.add(panel_1, BorderLayout.NORTH);
		
		JLabel lblLogin = new JLabel(Labels.LOGIN_LOGIN);
		lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 38));
		panel_1.add(lblLogin);
		
		JPanel panel_2 = new JPanel();
		loginpanel.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(null);
		
		JLabel lblUsername = new JLabel(Labels.LOGIN_USERNAME);
		lblUsername.setBounds(74, 92, 132, 16);
		panel_2.add(lblUsername);
		
		JLabel lblPassword = new JLabel(Labels.LOGIN_PASSWORD);
		lblPassword.setBounds(74, 149, 173, 16);
		panel_2.add(lblPassword);
		
		txtUsername = new JTextField();
		txtUsername.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtUsername.selectAll();
			}
		});
		txtUsername.setBounds(252, 89, 380, 22);
		panel_2.add(txtUsername);
		txtUsername.setColumns(10);
		
		txtPass1 = new JPasswordField();
		txtPass1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				txtPass1.selectAll();
			}
		});
		txtPass1.setBounds(252, 146, 380, 22);
		panel_2.add(txtPass1);
		txtPass1.addActionListener(actions);
		txtUsername.addActionListener(actions);
		
		JButton btnLogin = new JButton(Labels.LOGIN_LOGINBUTTON);
		btnLogin.addActionListener(actions);
	
		btnLogin.setBounds(274, 222, 151, 25);
		panel_2.add(btnLogin);
		

	}
}