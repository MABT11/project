/*
 * Login page, where the users needs login to the system and each user type will have his own page
 */

import java.awt.event.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.BevelBorder;

public class LoginPage extends JFrame implements ActionListener, KeyListener{
	/*
	 * creating the instance variables like the window, buttons, labels, user input 
	 * buttons
	 */
	private JButton loginButton;
	private JPanel panel;
	/*
	 * fields
	 */
	private JTextField idField;
	private JPasswordField passwordField;
	/*
	 * labels
	 */
	private JLabel idLabel;
	private JLabel passwordLabel;
	private JCheckBox showPasswordCheckBox;

	public LoginPage(){
		
		init();
		setTitle("Login");
		
		showPasswordCheckBox.addActionListener(this);
		passwordField.setEchoChar('*');
		passwordField.addKeyListener(this);
		
		//to add functionality to the box
		loginButton.addActionListener(this);
		loginButton.setFocusable(false);
		loginButton.setFont(new Font(null,Font.PLAIN,18));
		loginButton.setPreferredSize(new Dimension(78,25));
		loginButton.setToolTipText("Login");
		loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		panel.setBackground(Color.white);
		panel.setOpaque(true);
		panel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		panel.setBorder(BorderFactory.createEtchedBorder());
		panel.setBorder(BorderFactory.createLineBorder(Color.black));
		panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Login"));
		
		showPasswordCheckBox.setBackground(panel.getBackground());
		
		addComponents();
		pack();
		/*
		 * window configurations
		 */
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(380, 320));
		setLocationRelativeTo(null);
		setVisible(true);
	}
	/*
	 * Initializing all attributes
	 */
	private void init() {
		loginButton = new JButton("Login");
		panel = new JPanel(new GridBagLayout());
		idField = new JTextField(20);
		passwordField= new JPasswordField(20);
		idLabel= new JLabel("User ID:");
		passwordLabel = new JLabel("Password:"); 
		showPasswordCheckBox = new JCheckBox("Show Password");
	}
	/*
	 * adding the componants to the window 
	 */
	private void addComponents() {
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(10, 10, 10, 10);
		constraints.fill =  GridBagConstraints.VERTICAL;

		//to move the buttons on the grid
		constraints.gridx = 0;
        constraints.gridy = 0;
		panel.add(idLabel, constraints);
		constraints.gridx = 1;
		panel.add(idField, constraints);
		constraints.gridx = 0;
        constraints.gridy = 1;
		panel.add(passwordLabel, constraints);
		constraints.gridx = 1;
		panel.add(passwordField, constraints);
		constraints.gridx = 1;
        constraints.gridy = 2;
		panel.add(showPasswordCheckBox, constraints);
		constraints.gridx = 1;
        constraints.gridy = 3;
		panel.add(loginButton, constraints);
		add(panel);
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
			passwordField.setEchoChar((char)0);
		}
		else{
			passwordField.setEchoChar('*');
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
		/*
		 * check if the fields are empty or not
		 */
		if(idField.getText().isEmpty() || String.valueOf(passwordField.getPassword()).isEmpty()) {
			JOptionPane.showMessageDialog(null, "ID and or Password should not be empty", "Registration Unuccessful", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		/*
		 * then check for the input fields if they match the data or not
		 */
		Users users = new Users();
		Vector<Users> temp = new Vector<Users>();
		temp = users.getUsers();
		int len = temp.size();
		for(int i= 0;i<len;i++) {
			if(temp.elementAt(i).getID().equals(idField.getText().trim())) {
				if(temp.elementAt(i).getPassword().equals(String.valueOf(passwordField.getPassword()))) {
					// check his occupation in order for him to go to his page
					if(temp.elementAt(i).getOccupation().equals(Occupation.ADMIN.name())) {
						new AdminPage();
						dispose();
						return true;
					}
					if(temp.elementAt(i).getOccupation().equals(Occupation.STUDENT.name())) {
						new StudentPage(temp.elementAt(i));
						dispose();
						return true;
					}
					if(temp.elementAt(i).getOccupation().equals(Occupation.INSTRUCTOR.name())) {
						new InstructorPage(temp.elementAt(i));
						dispose();
						return true;
					}
				}	
			}
		}
		JOptionPane.showMessageDialog(null, "ID and or Password is incorrect","Error",0); 
		return false;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		
		if (e.getKeyCode() == KeyEvent.VK_ENTER)
			log();
	}
	@Override
	public void keyReleased(KeyEvent e) {
		
	}
}