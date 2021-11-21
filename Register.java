import java.awt.event.*;
import java.io.*;
import java.util.Scanner;
import java.util.Vector;
import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.BevelBorder;

public class Register extends JFrame implements ActionListener{
	
	
	private JButton registerButton = new JButton("Register");
	private JButton resetButton = new JButton("Reset");
	private JButton backButton = new JButton("back");
	
	private JTextField userIDField = new JTextField(20);
	private JTextField firstNameField = new JTextField(20);
	private JTextField lastNameField = new JTextField(20);
	private JPasswordField userPasswordField = new JPasswordField(20);
	
	private JLabel userIDLabel = new JLabel("ID:");
	private JLabel firstNamelabel = new JLabel("First name:");
	private JLabel lastNamelabel = new JLabel("Last name:");
	private JLabel userPasswordLabel = new JLabel("Password:");
	private JLabel messageLabel = new JLabel("");
	
	private JRadioButton studentRadioButton = new JRadioButton("Student");
    private JRadioButton instructorRadioButton=new JRadioButton("Instructor");
    private ButtonGroup group = new ButtonGroup();
	
    private Users users = new Users();
    private String occupation=null,lname=null,fname=null,password=null,userid=null;
    private Vector<Users> temp;
    private JPanel panel = new JPanel(new GridBagLayout());
	private JCheckBox showPasswordCheckBox = new JCheckBox("Show Password");  
	

	public Register() {
		setTitle("Register");
		
		try {
			setIconImage(ImageIO.read(new File("ku.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(10, 10, 10, 10);
		panel.setBackground(Color.white);
		panel.setOpaque(true);
		panel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		panel.setBorder(BorderFactory.createEtchedBorder());
		panel.setBorder(BorderFactory.createLineBorder(Color.black));
		panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Register Panel"));
		showPasswordCheckBox.setBackground(panel.getBackground());
		
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
		
		showPasswordCheckBox.addActionListener(this);
		userPasswordField.setEchoChar('*');
		
		studentRadioButton.addActionListener(this);
		instructorRadioButton.addActionListener(this);
		studentRadioButton.setBackground(panel.getBackground());
		instructorRadioButton.setBackground(panel.getBackground());
		group.add(instructorRadioButton);
		group.add(studentRadioButton);

		
		constraints.gridx = 0;
        constraints.gridy = 0;
		panel.add(backButton, constraints);
		constraints.gridx = 1;
		panel.add(studentRadioButton, constraints);
		constraints.gridx = 2;
		panel.add(instructorRadioButton
);
		constraints.gridx = 0;
		constraints.gridy = 1;
		panel.add(firstNamelabel,constraints);
		constraints.gridx = 1;
		panel.add(firstNameField,constraints);
		constraints.gridx = 0;
		constraints.gridy = 2;
		panel.add(lastNamelabel,constraints);
		constraints.gridx = 1;
		panel.add(lastNameField,constraints);
		constraints.gridx = 0;
		constraints.gridy = 3;
		panel.add(userIDLabel,constraints);
		constraints.gridx = 1;
		panel.add(userIDField,constraints);
		constraints.gridx = 0;
		constraints.gridy = 4;
		panel.add(userPasswordLabel,constraints);
		constraints.gridx = 1;
		panel.add(userPasswordField,constraints);
		constraints.gridx=2;
		panel.add(messageLabel,constraints);
		constraints.gridx = 1;
		constraints.gridy = 6;
		panel.add(showPasswordCheckBox,constraints);
		constraints.gridx = 0;
		constraints.gridy = 7;
		constraints.anchor = GridBagConstraints.CENTER;
		panel.add(resetButton,constraints);
		constraints.gridx = 1;
		panel.add(registerButton,constraints);
		add(panel);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
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
			new LoginPage();
			 dispose();
		}
		//register button was clicked
		if(e.getSource()==registerButton) {
			register();
		}
		if(showPasswordCheckBox.isSelected()) {
			userPasswordField.setEchoChar((char)0);
		}
		else{
			userPasswordField.setEchoChar('*');
		}
	}
	public void register() {
		fname = firstNameField.getText();
		lname = lastNameField.getText();
		userid = userIDField.getText();
		password = String.valueOf(userPasswordField.getPassword());
	
		/*
		 * First check if the user filled the boxes with his credentials
		 */
		if(!Verify.NameVerifier(fname)&&!Verify.NameVerifier(lname)) {
			JOptionPane.showMessageDialog(null, "Please Enter a vaild name", "Registration Unuccessful", JOptionPane.ERROR_MESSAGE);	
		}
		if(!Verify.IDVerifier(userid)) {
			JOptionPane.showMessageDialog(null, "Please Enter a vaild ID", "Registration Unuccessful", JOptionPane.ERROR_MESSAGE);	
		}
		if(!Verify.PassVerifier(password)) {
			messageLabel.setForeground(Color.red);
			messageLabel.setText("Password should have at least 1 uppercase 1 lowercase letter 1 special character 1 number");
		}
		if(!studentRadioButton.isSelected()&&!instructorRadioButton.isSelected()) {
			JOptionPane.showMessageDialog(null, "You need to select user type STUDENT or INSTRUCTOR", "Registration Unsuccesful", JOptionPane.ERROR_MESSAGE);
		}
		if(studentRadioButton.isSelected()) {
			occupation = new String("STUDENT");
		}
		if(instructorRadioButton.isSelected()) {
			occupation= new String("INSTRUCTOR");
		}
		/* to verify username is in the vector or not there
		 * if the user is already in there display error message
		 */
		temp = new Vector<Users>();
		temp = users.getUsers();
		boolean flag = false;
		int len = temp.size();
		int i =0;
		for (i=0;i< len;i++) {
			System.out.println("Saved in vector " + temp.get(i).getID());
			System.out.println("From the user text field "+userid);
			//to check if the user exists 
			if(userid.equals(temp.get(i).getID())) {
				flag = true;
			}
		}
		if(flag) {
			JOptionPane.showMessageDialog(null, "User Exists", "Registration Unsuccessful", JOptionPane.ERROR_MESSAGE);
		}
		else {
			//add users to the vector of type user object
			temp.add(new Users(fname, lname, userid, password,occupation));
			JOptionPane.showMessageDialog(null, "Registration Successful", "Registration Completed", JOptionPane.INFORMATION_MESSAGE);
			
			new LoginPage();
			
			System.out.println(temp.size());
			users.saveUsers(temp);
			dispose();
			
		}
	}
}