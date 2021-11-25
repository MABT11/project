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
	private Users users = new Users();

	public LoginPage(){
		
		setTitle("Login");
		try {
			setIconImage(ImageIO.read(new File("ku.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}

		showPasswordCheckBox.addActionListener(this);
		userPasswordField.setEchoChar('*');
		
		//square box around the word
		messageLabel.setFont(new Font(null,Font.ITALIC,13));
		//to add functionality to the box
		loginButton.addActionListener(this);
		loginButton.setFocusable(false);
		loginButton.setFont(new Font(null,Font.PLAIN,18));
		loginButton.setPreferredSize(new Dimension(78,25));
		loginButton.setToolTipText("Login");
		loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		
		userPasswordField.addKeyListener(this);

		/*
		 * adding the componants to the window 
		 */
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(10, 10, 10, 10);
		
		panel.setBackground(Color.white);
		panel.setOpaque(true);
		panel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		panel.setBorder(BorderFactory.createEtchedBorder());
		panel.setBorder(BorderFactory.createLineBorder(Color.black));
		panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Login"));
		showPasswordCheckBox.setBackground(panel.getBackground());
		constraints.fill =  GridBagConstraints.VERTICAL;

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
		constraints.gridx = 1;
        constraints.gridy = 3;
		panel.add(loginButton, constraints);
		constraints.gridx = 0;
        constraints.gridy = 4;
		constraints.insets = new Insets(1, 1, 1, 1);
		//span
		panel.add(messageLabel, constraints);
		add(panel);
		/*
		 * window configurations
		 */
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(380, 320));
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
		if(e.getSource()==loginButton) {
			if(log()) {
				System.out.println("Login success");
			}
			else {
				System.out.println("Login failure");
			}
		}
	}
	public boolean log() {
		if(userIDField.getText().isEmpty() || String.valueOf(userPasswordField.getPassword()).isEmpty()) {
			JOptionPane.showMessageDialog(null, "Id and or password fields should not be empty", "Registration Unuccessful", JOptionPane.ERROR_MESSAGE);
		}
		
		Vector<Users> temp = new Vector<Users>();
		temp = users.getUsers();
		int len = temp.size();
		for(int i= 0;i<len;i++) {
			if(temp.elementAt(i).getID().equals(userIDField.getText().trim())) {
				if(temp.elementAt(i).getPassword().equals(String.valueOf(userPasswordField.getPassword()))) {
					if(temp.elementAt(i).getOccupation().equals("ADMIN")) {
						new AdminPage();
						dispose();
						return true;
					}
					if(temp.elementAt(i).getOccupation().equals("STUDENT")) {
						new StudentPage();
						dispose();
						return true;
					}
					if(temp.elementAt(i).getOccupation().equals("INSTRUCTOR")) {
						new InstructorPage();
						dispose();
						return true;
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Passsword Incorrect","Error",0);
				}
			}
		}
		return false;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			log();
		}
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
