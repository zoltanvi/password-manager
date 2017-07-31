package view;

import controller.PasswordManagerController;

import java.awt.Toolkit;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.*;

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
    private PasswordManagerPasswords pass;
    private PasswordManagerMenuBar pMenuBar;
    private PasswordManagerController controller;
    private AddNewPassword addPass;
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
		lblNotLoggedIn.setForeground(new Color(255, 0, 11));
        lblNotLoggedIn.setFont(new Font("Tahoma", Font.PLAIN, 15));

		lblLoggedInAs = new JLabel(Labels.MENUBAR_LOGGED_IN_AS);
		lblLoggedInAs.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLoggedInAs.setForeground(new Color(0, 63, 230));

        lblUser = new JLabel();
		lblUser.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblUser.setForeground(new Color(212, 89, 169));
		
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

    public void switchToPass(){
        panel.removeAll();
        pMenuBar.passwordsMenuPopulate();
        pMenuBar.add(lblLoggedInAs);
        pMenuBar.add(lblUser);
        pass = new PasswordManagerPasswords(this);
        panel.add(pass.panelPasswords);
        panel.repaint();
        panel.revalidate();
    }

    public void switchToAdd(){
        panel.removeAll();
        addPass = new AddNewPassword(this);
        panel.add(addPass.contentPanel);
        panel.repaint();
        panel.revalidate();
    }

    public void switchToInp(){
        JOptionPane.showMessageDialog(this.getPanel(),
                "Not implemented yet!",
                "Error", JOptionPane.ERROR_MESSAGE);
    }
    public void switchToExp(){
        JOptionPane.showMessageDialog(this.getPanel(),
                "Not implemented yet!",
                "Error", JOptionPane.ERROR_MESSAGE);
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

    public PasswordManagerPasswords getPass() {
        return pass;
    }
}
