package view;

import controller.PasswordManagerController;

import java.awt.Toolkit;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PasswordManagerGUI {

	private JFrame frame;
	private JPanel panel;
	private static JLabel lblNotLoggedIn;
	private static JLabel lblLoggedInAs;
	private static JLabel lblUser;
	private static String SESSION_USERNAME;
	private static String SESSION_PASSWORD;
	private PasswordManagerLogin login;
	private PasswordManagerRegistration registration;
    private PasswordManagerMenuBar pMenuBar;
    private PasswordManagerController controller;
	public PasswordManagerGUI() {

	    controller = new PasswordManagerController(this);
		login = new PasswordManagerLogin(this);
        registration = new PasswordManagerRegistration(this);

		frame = new JFrame();
		frame.setSize(new Dimension(700, 500));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout());
		panel.add(login.loginpanel);
		pMenuBar = new PasswordManagerMenuBar(this);
        frame.setJMenuBar(pMenuBar);


		lblNotLoggedIn = new JLabel(Labels.MENUBAR_NOT_LOGGED_IN);
		lblNotLoggedIn.setForeground(new Color(210, 111, 255));
        lblNotLoggedIn.setFont(new Font("Tahoma", Font.PLAIN, 13));

		lblLoggedInAs = new JLabel(Labels.MENUBAR_LOGGED_IN_AS);
		lblLoggedInAs.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblLoggedInAs.setForeground(new Color(0, 215, 68));

        lblUser = new JLabel();
		lblUser.setFont(new Font("Courier New", Font.BOLD, 15));
		lblUser.setForeground(new Color(0, 98, 212));
		
		pMenuBar.add(lblNotLoggedIn);
	
	}

	public void switchToReg(){
        panel.removeAll();
        pMenuBar.regMenuPopulate();
        pMenuBar.add(lblNotLoggedIn);
        panel.add(registration.regpanel);
        panel.repaint();
        panel.revalidate();
    }

    public void switchToLog(){
	    panel.removeAll();
	    pMenuBar.loginMenuPopulate();
        pMenuBar.add(lblNotLoggedIn);
        panel.add(login.loginpanel);
        panel.repaint();
        panel.revalidate();
    }

    public void switchToLoggedIn(){
        pMenuBar.loggedinMenuPopulate();
        pMenuBar.add(lblLoggedInAs);
        pMenuBar.add(lblUser);
    }




    public void switchToInp(){
        System.out.println("Not implemented yet!");
    }
    public void switchToExp(){
        System.out.println("Not implemented yet!");
    }

	public void exit(){
    	frame.dispose();
	}

    public JFrame getFrame() {
        return frame;
    }

    public PasswordManagerLogin getLogin() {
        return login;
    }

    public PasswordManagerRegistration getRegistration() {
        return registration;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public static String getSessionUsername() {
        return SESSION_USERNAME;
    }

    public static void setSessionUsername(String sessionUsername) {
        SESSION_USERNAME = sessionUsername;
    }

    public static String getSessionPassword() {
        return SESSION_PASSWORD;
    }

    public static void setSessionPassword(String sessionPassword) {
        SESSION_PASSWORD = sessionPassword;
    }

    public PasswordManagerMenuBar getpMenuBar() {
        return pMenuBar;
    }

    public static JLabel getLblLoggedInAs() {
        return lblLoggedInAs;
    }

    public static JLabel getLblUser() {
        return lblUser;
    }

    public JPanel getPanel() {
        return panel;
    }

    public PasswordManagerController getController() {
        return controller;
    }
}
