package view;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import controller.Menu;
import controller.PropertiesController;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class Login {

	JPanel loginpanel;
	private JTextField txtUsername;
	private JPasswordField txtPass1;
    private PropertiesController props = new PropertiesController();
	
	public Login() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			System.out.println("Unable to set LookAndFeel");
			e.printStackTrace();
		}
		Menu menu = new Menu();
		loginpanel = new JPanel();
		loginpanel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		loginpanel.add(panel_1, BorderLayout.NORTH);
		
		JLabel lblLogin = new JLabel(props.getProp("login"));
		lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 38));
		panel_1.add(lblLogin);
		
		JPanel panel_2 = new JPanel();
		loginpanel.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(null);
		
		JLabel lblUsername = new JLabel(props.getProp("username"));
		lblUsername.setBounds(74, 92, 132, 16);
		panel_2.add(lblUsername);
		
		JLabel lblPassword = new JLabel(props.getProp("password"));
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
		txtPass1.addActionListener(menu);
		txtUsername.addActionListener(menu);
		
		JButton btnLogin = new JButton(props.getProp("logButton"));
		btnLogin.addActionListener(menu);
	
		btnLogin.setBounds(274, 222, 151, 25);
		panel_2.add(btnLogin);
		

	}
}