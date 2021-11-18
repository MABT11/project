import java.awt.event.*;
import java.io.*;
import java.util.HashMap;
import java.util.Scanner;
import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Register extends JFrame implements ActionListener{
	
	private JLabel labl = new JLabel("Welcome");
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
    private JRadioButton studentRadioButton = new JRadioButton("Student");
    private JRadioButton instructorRadioButton=new JRadioButton("Instructor");
    private ButtonGroup group = new ButtonGroup();
    
	private JPanel panel = new JPanel(new GridBagLayout());

	public Register() {
		setTitle("Register");
		
		try {
			setIconImage(ImageIO.read(new File("ku.png")));
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
		
		studentRadioButton.addActionListener(this);
		instructorRadioButton.addActionListener(this);
		group.add(instructorRadioButton);
		group.add(studentRadioButton);
		
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(10, 10, 10, 10);
		constraints.gridx = 0;
        constraints.gridy = 0;
		panel.add(backButton, constraints);
		constraints.gridx = 1;
		panel.add(studentRadioButton, constraints);
		constraints.gridx = 2;
		panel.add(instructorRadioButton, constraints);
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
		constraints.gridx = 0;
		constraints.gridy = 5;
		panel.setBorder(BorderFactory.createTitledBorder(
				 BorderFactory.createEtchedBorder(), "Register Panel"));
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
			String fname = firstNameField.getText();
			String lname = lastNameField.getText();
			String userid = userIDField.getText();
			String password = String.valueOf(userPasswordField.getPassword());
			/*
			 * First check if the user filled the boxes with his credentials
			 */
			if(userid.isEmpty() || password.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Id and password fields should not be empty", "Registration Unuccessful", JOptionPane.ERROR_MESSAGE);
			}
			else if(!studentRadioButton.isSelected()&&!instructorRadioButton.isSelected()) {
				JOptionPane.showMessageDialog(null, "You need to select user type STUDENT or INSTRUCTOR", "Registration Unsuccesful", JOptionPane.ERROR_MESSAGE);
			}
			/* to verify username is in the txt or not there
			 * if the user is already in there display error message
			 */
			else {
			Scanner fin;
			try {
				fin = new Scanner(new File("users.txt"));
				boolean flag = false;
				String o=null;
				while(fin.hasNext()) {
					if(fin.next().equals(userid)) {
						flag = true;		
					}
				}
				if(flag) {		
					JOptionPane.showMessageDialog(null, "User Exists", "Registration Unuccessful", JOptionPane.ERROR_MESSAGE);
				}
				
				if(!flag) {
					o = fname + " "+ lname +" " + userid + " " + password + " ";
					if(instructorRadioButton.isSelected()) {
						group.clearSelection();
						o+= "INSTRUCTOR";
					}
					if(studentRadioButton.isSelected()) {
						group.clearSelection();
						 o+= "STUDENT";
					}
					JOptionPane.showMessageDialog(null, "Registration Successful", "Registration Completed", JOptionPane.INFORMATION_MESSAGE);
					new LoginPage();
					PrintWriter wr;
					wr = new PrintWriter(new FileWriter("users.txt", true));
					wr.println(o);
					wr.close();
				}
				
			} catch (IOException e2) {
				e2.printStackTrace();
			}
			dispose();
			}
			}
		}	
}	