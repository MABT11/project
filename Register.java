import java.awt.event.*;
import java.io.*;
import java.util.HashMap;
import java.awt.GridLayout;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Register extends JFrame implements ActionListener{
	
	JLabel labl = new JLabel("Welcome");
	JButton registerButton = new JButton("Register");
	JButton resetButton = new JButton("Reset");
	JButton backButton = new JButton("back");
	JTextField userIDField = new JTextField();
	JTextField firstNameField = new JTextField();
	JTextField lastNameField = new JTextField();
	JPasswordField userPasswordField = new JPasswordField();
	JLabel userIDLabel = new JLabel("ID:");
	JLabel firstNamelabel = new JLabel("First name:");
	JLabel lastNamelabel = new JLabel("Last name:");
	JLabel userPasswordLabel = new JLabel("Password:");

	HashMap<String,String> logininfo = new HashMap<String,String>();

	public Register(HashMap<String,String> loginInfo) {
		
		logininfo = loginInfo;

		setTitle("Register");
		try {
			setIconImage(ImageIO.read(new File("C:/Users/Mubarak/Desktop/ku.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		//square box around the word
		registerButton.setFocusable(false);
		//to add functionality to the box
		registerButton.addActionListener(this);
		
		//square box around the word
		resetButton.setFocusable(false);
		//to add functionality to the box
		resetButton.addActionListener(this);
		
		//square box around the word
		backButton.setFocusable(false);
		//to add functionality to the box
		backButton.addActionListener(this);
		add(new JLabel(""));
		add(new JLabel(""));
		add(backButton);
		add(new JLabel(""));
		add(firstNamelabel);
		add(firstNameField);
		add(lastNamelabel);
		add(lastNameField);
		add(userIDLabel);
		add(userIDField);
		add(userPasswordLabel);
		add(userPasswordField);
		add(resetButton);
		add(registerButton);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(420,420);
		setLayout(new GridLayout(10,2));
		setLocationRelativeTo(null);
		setVisible(true);	
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		//reset button was clicked
		if(e.getSource()==resetButton) {
			userIDField.setText("");
			userPasswordField.setText("");
		}
		/*
		 * if back was pressed go back to the loginpage
		 */
		if(e.getSource()==backButton) {
			new LoginPage(logininfo);
			 dispose();
		}
		//register button was clicked
		if(e.getSource()==registerButton) {
			String userid = userIDField.getText();
			String password = String.valueOf(userPasswordField.getPassword());
			String o = "First Name: " + firstNameField.getText();
			o +="\nlast Name: " + lastNameField.getText();
			o +="\nUsername: " + userid +"\nPassword: " + password;
			/*
			 * First check if the user filled the boxes with his credentials
			 */
			if(userid.isEmpty() || password.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Id and password fields should not be empty", "Registration Unuccessful", JOptionPane.ERROR_MESSAGE);
			}
			
			
			/* to verify username is in the hashmap or not there
			 * if the user is already in there display error message
			 */
			else if(logininfo.containsKey(userid)) {
				//if the password matches 
				JOptionPane.showMessageDialog(null, "User Exists", "Registration Unuccessful", JOptionPane.ERROR_MESSAGE);
				}
			/*
			 * if the user was not found create a new user
			 * go to the loginpage
			 */
			else{
				logininfo.put(userid, password);				
				JOptionPane.showMessageDialog(null, "Registration Successful", "Registration Completed", JOptionPane.INFORMATION_MESSAGE);
				new LoginPage(logininfo);
				try {
					new LoginInfo(userid,password,o);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				 dispose();
				}
			}
		}	
}	
