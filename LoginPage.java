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
import javax.swing.border.BevelBorder;

public class LoginPage extends JFrame implements ActionListener, KeyListener{
	/*
	 * creating the instance variables like the window, buttons, labels, user input 
	 * buttons
	 */
	private JButton loginButton = new JButton("Login");
	private JButton registerButton = new JButton("Register");
	private JButton backButton = new JButton("Back");
	private JPanel panel = new JPanel(new GridBagLayout());
	/*
	 * fields
	 */
	private JTextField userIDField = new JTextField(20);
	private JPasswordField userPasswordField = new JPasswordField(20);
	/*
	 * labels
	 */
	private JLabel userIDLabel = new JLabel("User ID:");
	private JLabel userPasswordLabel = new JLabel("Password:");
	private JCheckBox showPasswordCheckBox = new JCheckBox("Show Password");  
	/*
	 * pop message when the user try to sign in
	 */
	private JLabel messageLabel = new JLabel();

	LoginPage(){
		
		setTitle("Login");
		try {
			setIconImage(ImageIO.read(new File("ku.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}

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
		registerButton.setFocusable(false);
		//to add functionality to the box
		registerButton.addActionListener(this);
		
		//square box around the word
		backButton.setFocusable(false);
		//to add functionality to the box
		backButton.addActionListener(this);
		
		userIDField.addKeyListener(this);
		userPasswordField.addKeyListener(this);

		/*
		 * adding the componants to the window 
		 */
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(10, 10, 10, 10);
		
		panel.setBackground(Color.orange);
		panel.setOpaque(true);
		panel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		panel.setBorder(BorderFactory.createEtchedBorder());
		panel.setBorder(BorderFactory.createLineBorder(Color.black));
		panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Login"));
		showPasswordCheckBox.setBackground(Color.orange);
//		constraints.fill =  GridBagConstraints.HORIZONTAL;
		constraints.fill =  GridBagConstraints.VERTICAL;

//		constraints.ipadx = 20;
//		constraints.ipady = 5;
		//to move the buttons on the grid
		constraints.gridx = 0;
        constraints.gridy = 0;
		panel.add(userIDLabel, constraints);
		constraints.gridx = 1;
		panel.add(userIDField, constraints);
		constraints.gridx = 0;
        constraints.gridy = 1;
		panel.add(userPasswordLabel, constraints);
		constraints.gridx = 1;
		panel.add(userPasswordField, constraints);
		constraints.gridx = 1;
        constraints.gridy = 2;
		panel.add(showPasswordCheckBox, constraints);
		constraints.gridx = 0;
        constraints.gridy = 3;
		panel.add(loginButton, constraints);
		constraints.gridx = 1;
		panel.add(registerButton, constraints);
		constraints.gridx = 0;
        constraints.gridy = 4;
		constraints.insets = new Insets(1, 1, 1, 1);
		panel.add(messageLabel, constraints);
		add(panel);
		/*
		 * window configurations
		 */
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(420, 320));
		pack();
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
		if(showPasswordCheckBox.isSelected()) {
			userPasswordField.setEchoChar((char)0);
		}
		else{
			userPasswordField.setEchoChar('*');
		}
		/*
		 * if the loginButton was pressed
		 */
		if(e.getSource()==registerButton) {
			new Register();
			dispose();
		}
		if(e.getSource()==loginButton) {
			/*
			 * get the id and the password 
			 */
			String userid = userIDField.getText();
			String password = String.valueOf(userPasswordField.getPassword());
			/*
			 * To verify the id is in the hashmap or not there
			 */
			if(userid.isEmpty() || password.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Id and or password fields should not be empty", "Registration Unuccessful", JOptionPane.ERROR_MESSAGE);
			}
			else {
				Scanner fin;
				try {
					fin = new Scanner(new File("users.txt"));
					fin.useDelimiter("[ \n]");
					String tmpid,tmppass;
					while(fin.hasNext()) {
						tmpid = fin.next();
						tmppass = fin.next();
						if(tmpid.trim().equals(userid.trim())) {
							/*
							 * if username mathches check for the password and check
							 */
							if(tmppass.trim().equals(password.trim())) {
								/*
								 * Move to the dashboard 
								 */
								new Welcome();
								dispose();
							}
						}
						else {
							messageLabel.setForeground(Color.red);
							messageLabel.setText("Login Unsuccessful");
						}
					}
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			userIDField.requestFocus();
		}
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}