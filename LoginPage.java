/*
 * Login page gui where the users can either register as new user or login to the system
 * currently the login gui is the same for all three types of users small variations
 * need to be made
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginPage implements ActionListener{
	/*
	 * creating the instance variables like the window, buttons, labels, user input 
	 */
	JFrame frame = new JFrame("Login");
	/*
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
	/*
	 * pop message when the user try to sign in
	 */
	JLabel messageLabel = new JLabel();
	HashMap<String,String> logininfo = new HashMap<String,String>();
	
	LoginPage(HashMap<String,String> loginInfo){
		
		logininfo = loginInfo;
		
		/* to change the font of object
		 * objName.setFont(new Font(null,Font.ITALIC,25));
		 */
		
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
		frame.add(new JLabel(""));
		frame.add(new JLabel(""));
		frame.add(new JLabel(""));
		frame.add(backButton);
		frame.add(new JLabel(""));
		frame.add(new JLabel(""));
		frame.add(userIDLabel);
		frame.add(userIDField);
		frame.add(new JLabel(""));
		frame.add(userPasswordLabel);
		frame.add(userPasswordField);
		frame.add(new JLabel(""));

		frame.add(resetButton);
		frame.add(loginButton);
		frame.add(registerButton);
		frame.add(new JLabel(""));
		frame.add(messageLabel);
		/*
		 * window configurations
		 */
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(420,420);
		frame.setLayout(new GridLayout(7,3));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
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
			new LoginInfo();
			frame.dispose();
		}
		/*
		 * resting the input to the empty string
		 */
		if(e.getSource()==resetButton) {
			userIDField.setText("");
			userPasswordField.setText("");
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
//					messageLabel.setForeground(Color.green);
//					messageLabel.setText("Login Successful");
//					JOptionPane.showMessageDialog(null, "Login Successful", "Welcome", JOptionPane.INFORMATION_MESSAGE);
					new Welcome(logininfo,userIDField.getText());
					frame.dispose();
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
			frame.dispose();
		}	
	}
	//	LoginPage(){
	//	loginButton.setFocusable(false);
	//	loginButton.addActionListener(this);
	//	resetButton.setFocusable(false);
	//	resetButton.addActionListener(this);
	//	backButton.setFocusable(false);
	//	backButton.addActionListener(this);
	//	frame.add(userIDLabel);
	//	frame.add(userIDField);
	//	frame.add(userPasswordLabel);
	//	frame.add(userPasswordField);
	//	frame.add(resetButton);
	//	frame.add(loginButton);
	//	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	//	frame.setSize(420,420);
	//	frame.setLayout(new GridLayout(3,2));
	//	frame.setLocationRelativeTo(null);
	//	frame.setVisible(true);
	//}
}