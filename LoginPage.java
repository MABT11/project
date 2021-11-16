/*
 * Login page gui where the users can either register as new user or login to the system
 * currently the login gui is the same for all three types of users small variations
 * need to be made
 */

import java.awt.event.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.util.*;
import java.awt.*;
import javax.swing.*;

public class LoginPage extends JFrame implements ActionListener{
	/*
	 * creating the instance variables like the window, buttons, labels, user input 
	 * buttons
	 */
	JButton loginButton = new JButton("Login");
	JButton resetButton = new JButton("Reset");
	JButton registerButton = new JButton("Register");
	JButton backButton = new JButton("Back");
	
	
	/*
	 * fields
	 */
	JTextField userIDField = new JTextField();
	JPasswordField userPasswordField = new JPasswordField();
	/*
	 * labels
	 */
	JLabel userIDLabel = new JLabel("User ID:");
	JLabel userPasswordLabel = new JLabel("Password:");
	// JLabel showPasswordLabel = new JLabel("👁");
	JCheckBox showPasswordCheckBox = new JCheckBox("Show Password");  
	/*
	 * pop message when the user try to sign in
	 */
	JLabel messageLabel = new JLabel();
	HashMap<String,String> logininfo = new HashMap<String,String>();
	
	LoginPage(HashMap<String,String> loginInfo){
		
		logininfo = loginInfo;
		setTitle("Login");
		try {
			setIconImage(ImageIO.read(new File("C:/Users/Mubarak/Desktop/ku.png")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/* to change the font of object
		 * objName.setFont(new Font(null,Font.ITALIC,25));
		 */
		// showPasswordLabel.setFont(new Font(null,0,20));
		// showPasswordLabel.setVisible(false);
		// userPasswordField.setEchoChar((char)0);
		// userPasswordField.setEchoChar('*');
		showPasswordCheckBox.addActionListener(this);
		userPasswordField.setEchoChar('*');


		//square box around the word
		messageLabel.setFocusable(false);
		messageLabel.setFont(new Font(null,Font.ITALIC,13));
		//square box around the word
		loginButton.setFocusable(false);
		//to add functionality to the box
		loginButton.addActionListener(this);
		
		//square box around the word
		resetButton.setFocusable(false);
		//to add functionality to the box
		resetButton.addActionListener(this);
		resetButton.setLocation(350,400);
		
		//square box around the word
		registerButton.setFocusable(false);
		//to add functionality to the box
		registerButton.addActionListener(this);
		
		//square box around the word
		backButton.setFocusable(false);
		//to add functionality to the box
		backButton.addActionListener(this);
		/*
		 * adding the componants to the window 
		 */
		add(new JLabel(""));
		add(new JLabel(""));
		add(new JLabel(""));
		add(backButton);
		add(new JLabel(""));
		add(new JLabel(""));
		add(userIDLabel);
		add(userIDField);
		add(new JLabel(""));
		add(userPasswordLabel);
		add(userPasswordField);
		add(showPasswordCheckBox);
		add(resetButton);
		add(loginButton);
		add(registerButton);
		add(new JLabel(""));
		add(messageLabel);
		/*
		 * window configurations
		 */
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(420,420);
		setLayout(new GridLayout(7,3));
		setLocationRelativeTo(null);
		setVisible(true);
	}

	/*
	 * Adding life to the program
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		/*
		 * if the backbutton is pressed we will go back to the user type window
		 * and close the current window
		 */
		if(e.getSource()==backButton) {
			LoginInfo l = new LoginInfo();
			try {
				new UserMode(l.getLoginInfo());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			dispose();
		}
		/*
		 * resting the input to the empty string
		 */
		if(e.getSource()==resetButton) {
			userIDField.setText("");
			userPasswordField.setText("");
		}
		if(showPasswordCheckBox.isSelected()) {
			userPasswordField.setEchoChar((char)0);
		}
		else{
			userPasswordField.setEchoChar('*');
		}
		/*
		 * if the loginButton was pressed
		 */
		if(e.getSource()==loginButton) {
			/*
			 * get the id and the password 
			 */
			String userid = userIDField.getText();
			String password = String.valueOf(userPasswordField.getPassword());
			/*
			 * To verify the id is in the hashmap or not there
			 */
			if(logininfo.containsKey(userid)) {
				/*
				 * if username mathches check for the password and check
				 */
				if(logininfo.get(userid).equals(password)) {
					/*
					 * display a suitable message and move to the dashboard 
					 */
					new Welcome(logininfo,userIDField.getText());
					dispose();
				}
				else {
					messageLabel.setForeground(Color.red);
					messageLabel.setText("Login Unsuccessful");
					JOptionPane.showMessageDialog(null, "Password incorrect", "Login Unsuccessful", JOptionPane.ERROR_MESSAGE);
				}
			}
			/*
			 * if the user id was not found in the hashmap then the user enterned incorrect userid
			 * and thus error message is displayed
			 */
			else {
				messageLabel.setForeground(Color.red);
				messageLabel.setText("Login Unsuccessful");
				JOptionPane.showMessageDialog(null, "Username incorrect", "Login Unsuccessful", JOptionPane.ERROR_MESSAGE);
			}
		}
		if(e.getSource()==registerButton) {
			new Register(logininfo);
			dispose();
		}	
	}
}
