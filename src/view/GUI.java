package view;


import controller.Menu;
import controller.PropertiesController;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class GUI{

	private JFrame frame;
	private JPanel panel;
	static JMenu firstMenu;
	static JMenuItem mnNewAccount;
	static JMenuItem mnLogin;
	static JMenuItem mnLogout;
	private JMenuItem mntmExit;
	private JMenu secondMenu;
	private JMenu thirdMenu;
	private JMenu fourthMenu;
	static JLabel lblLoggedIn;
	static JLabel lblLoggedInAs;
	public static String SESSION_USERNAME;
	public static String SESSION_PASSWORD;
	public static String filepath;
    private PropertiesController props = new PropertiesController();


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public GUI() {
		filepath = System.getenv("APPDATA");
		initfirst();
	}

	private void initfirst(){

		Login lo = new Login();
		NewAccount na = new NewAccount();

		frame = new JFrame();
		frame.setSize(new Dimension(700, 500));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout());
		panel.setBackground(Color.black);
		//panel.add(lo.loginpanel);
		panel.add(na.newAcc);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		firstMenu = new JMenu(props.getProp("firstMenu"));
		menuBar.add(firstMenu);
		
		mnNewAccount = new JMenuItem(props.getProp("newAccMenu"));
        Menu menu = new Menu();
		mnNewAccount.addActionListener(menu);
		firstMenu.add(mnNewAccount);
		
		mnLogin = new JMenuItem(props.getProp("loginMenu"));
		mnLogin.addActionListener(menu);
		firstMenu.add(mnLogin);
		
		mnLogout = new JMenuItem(props.getProp("logoutMenu"));
		mnLogout.addActionListener(menu);
		
		
		mntmExit = new JMenuItem(props.getProp("exitMenu"));
		mntmExit.addActionListener(menu);
		firstMenu.add(mntmExit);
		
		secondMenu = new JMenu(props.getProp("secondMenu"));
		menuBar.add(secondMenu);
		
		thirdMenu = new JMenu(props.getProp("thirdMenu"));
		menuBar.add(thirdMenu);
		
		fourthMenu = new JMenu(props.getProp("fourthMenu"));
		menuBar.add(fourthMenu);
		
		lblLoggedIn = new JLabel(props.getProp("notLoggedIn"));
		lblLoggedIn.setForeground(Color.orange);
		menuBar.add(lblLoggedIn);
		
		lblLoggedInAs = new JLabel();
		lblLoggedInAs.setFont(new Font("Tahoma", Font.ITALIC, 13));
		lblLoggedInAs.setForeground(new Color(36, 114, 60));
		
		menuBar.add(lblLoggedInAs);
	
	}
}
