package view;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class PasswordManagerGUI {

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
	private static String SESSION_USERNAME;
	private static String SESSION_PASSWORD;

	public PasswordManagerGUI() {
		PasswordManagerLogin lo = new PasswordManagerLogin();

		frame = new JFrame();
		frame.setSize(new Dimension(700, 500));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout());
		panel.add(lo.loginpanel);
		//panel.add(na.regpanel);

		PasswordManagerMenuBar pMenuBar = new PasswordManagerMenuBar(this);
        frame.setJMenuBar(pMenuBar);

		lblLoggedIn = new JLabel(Labels.MENUBAR_NOT_LOGGED_IN);
		lblLoggedIn.setForeground(new Color(210, 111, 255));
		pMenuBar.add(lblLoggedIn);

		lblLoggedInAs = new JLabel();
		lblLoggedInAs.setFont(new Font("Tahoma", Font.ITALIC, 13));
		lblLoggedInAs.setForeground(new Color(0, 197, 206));
		
		pMenuBar.add(lblLoggedInAs);
	
	}

	public void switchToReg(){
        PasswordManagerRegistration reg = new PasswordManagerRegistration();
        panel.removeAll();
        PasswordManagerMenuBar menu = new PasswordManagerMenuBar(this);
        menu.regMenuPopulate();
        panel.add(reg.regpanel);
        panel.repaint();
        panel.revalidate();
    }

    public void switchToLog(){
        PasswordManagerLogin lo = new PasswordManagerLogin();
	    panel.removeAll();
        panel.add(lo.loginpanel);
        panel.repaint();
        panel.revalidate();
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
}
