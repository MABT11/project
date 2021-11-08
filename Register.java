import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Register implements ActionListener{
	JFrame frame = new JFrame("Sign up");
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

//	PrintWriter fout ;
	HashMap<String,String> logininfo = new HashMap<String,String>();

	public Register(HashMap<String,String> loginInfo) {
		
		logininfo = loginInfo;
		
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
		frame.add(new JLabel(""));
		frame.add(new JLabel(""));
		frame.add(backButton);
		frame.add(new JLabel(""));
		frame.add(firstNamelabel);
		frame.add(firstNameField);
		frame.add(lastNamelabel);
		frame.add(lastNameField);
		frame.add(userIDLabel);
		frame.add(userIDField);
		frame.add(userPasswordLabel);
		frame.add(userPasswordField);
		frame.add(resetButton);
		frame.add(registerButton);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(420,420);
		frame.setLayout(new GridLayout(10,2));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);	
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
			frame.dispose();
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
				JOptionPane.showMessageDialog(null, "Id and password filds should not be empty", "Registration Unuccessful", JOptionPane.ERROR_MESSAGE);
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
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				frame.dispose();
				}
			}
		}	
}	