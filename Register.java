
import java.awt.event.*;
import java.util.Vector;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.BevelBorder;

public class Register extends JFrame implements ActionListener{
	
	private JButton registerButton;
	private JButton resetButton;
	private JButton backButton;
	
	private JTextField idField ;
	private JTextField firstNameField;
	private JTextField lastNameField ;
	private JComboBox<String> department;
	private JPasswordField passwordField;
	private JTextField crnField;

	private JLabel idLabel;
	private JLabel firstNamelabel;
	private JLabel lastNamelabel;
	private JLabel passwordLabel;
	private JLabel messageLabel;
	private JLabel departmentLabel;
	private JLabel crnLabel;

	private JRadioButton studentRadioButton;
    private JRadioButton instructorRadioButton;
    private ButtonGroup group;
    
    private JPanel panel;
	private JCheckBox showPasswordCheckBox;  
	
	private String fname,lname,dep,userid,password,ocuupation;
	private String[]crn=new String[5];
	private String occupation;
	private Users users = new Users();
	private Vector<Users> temp;
	private Vector<RegistaredStudents>c=new Vector<RegistaredStudents>();
	private Courses course = new Courses();
	private Vector<RegistaredStudents>cc=new Vector<RegistaredStudents>();

	public Register() {
		
		setTitle("Register");
		
		init();
		
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
		passwordField.setEchoChar('*');
		
		studentRadioButton.addActionListener(this);
		instructorRadioButton.addActionListener(this);
		
		studentRadioButton.setBackground(panel.getBackground());
		instructorRadioButton.setBackground(panel.getBackground());
		group.add(instructorRadioButton);
		group.add(studentRadioButton);

		addComponents();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setMinimumSize(new Dimension(600, 500));
		setLocationRelativeTo(null);
		setVisible(true);	
	}
	/*
	 * Initializing all attributes
	 */
	public void init() {
		
		backButton= new JButton("Back");
		registerButton = new JButton("Register");
		resetButton= new JButton("Reset");
		
		idField  = new JTextField(20);
		firstNameField = new JTextField(20);
		lastNameField = new JTextField(20);
		String []s={Departments.PHYSICS.name(), Departments.CHEMISTRY.name(),Departments.ECCE.name()};
		department = new JComboBox<String>(s);
		passwordField = new JPasswordField(20);
		crnField = new JTextField(20);
		
		idLabel = new JLabel("ID:");
		firstNamelabel = new JLabel("First name:");
		lastNamelabel = new JLabel("Last name:");
		passwordLabel = new JLabel("Password:");
		departmentLabel = new JLabel("Department:");
		messageLabel = new JLabel("");
		crnLabel = new JLabel("CRN:");
		
		studentRadioButton = new JRadioButton("Student");
	    instructorRadioButton=new JRadioButton("Instructor");
	    group = new ButtonGroup();
		
	    showPasswordCheckBox = new JCheckBox("Show Password");  
		
		panel = new JPanel(new GridBagLayout());
	}
	/*
	 * adding component to the frame
	 */
	public void addComponents() {
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(10, 10, 10, 10);
		
		constraints.gridx = 0;
        constraints.gridy = 0;
		panel.add(backButton, constraints);
		constraints.gridx = 1;
		panel.add(studentRadioButton, constraints);
		constraints.gridx = 2;
		panel.add(instructorRadioButton,constraints);
		constraints.gridx = 0;
		constraints.gridy = 1;
		panel.add(firstNamelabel,constraints);
		constraints.gridx = 1;
		constraints.gridwidth = 100;
		panel.add(firstNameField,constraints);
		constraints.gridx = 0;
		constraints.gridy = 2;
		panel.add(lastNamelabel,constraints);
		constraints.gridx = 1;
		panel.add(lastNameField,constraints);
		constraints.gridx = 0;
		constraints.gridy = 3;
		panel.add(crnLabel,constraints);
		constraints.gridx = 1;
		panel.add(crnField,constraints);
		constraints.gridx = 0;
		constraints.gridy = 4;
		panel.add(departmentLabel,constraints);
		constraints.gridx = 1;
		panel.add(department,constraints);
		constraints.gridx = 0;
		constraints.gridy = 5;
		panel.add(idLabel,constraints);
		constraints.gridx = 1;
		panel.add(idField,constraints);
		constraints.gridx = 0;
		constraints.gridy = 6;
		panel.add(passwordLabel,constraints);
		constraints.gridx = 1;
		panel.add(passwordField,constraints);
		constraints.gridwidth = 1;
		constraints.gridx=3;
		panel.add(messageLabel,constraints);
		constraints.gridx = 0;
		constraints.gridy = 7;
		panel.add(showPasswordCheckBox,constraints);
		constraints.gridx = 0;
		constraints.gridy = 8;
		constraints.anchor = GridBagConstraints.CENTER;
		panel.add(resetButton,constraints);
		constraints.gridx = 1;
		panel.add(registerButton,constraints);
		add(panel);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		//reset button was clicked
		if(e.getSource()==resetButton) {
			idField.setText("");
			passwordField.setText("");
			firstNameField.setText("");
			lastNameField.setText("");
			crnField.setText("");
		}
		/*
		 * if back was pressed go back to the Adminpage
		 */
		if(e.getSource()==backButton) {
			new AdminPage();
			 dispose();
		}
		//register button was clicked
		if(e.getSource()==registerButton) {
			if(register()){
				new AdminPage();
				dispose();
				
				
			}
		}
		if(showPasswordCheckBox.isSelected()) {
			passwordField.setEchoChar((char)0);
		}
		else{
			passwordField.setEchoChar('*');
		}
	}
	public boolean register() {
		fname = firstNameField.getText().strip().replace(" ", "");
		lname = lastNameField.getText().strip().replace(" ", "");
		userid = idField.getText().strip().replace(" ", "");
		password = String.valueOf(passwordField.getPassword());
		occupation=null;
		dep=null;
		crn=crnField.getText().split(" ");
		/*
		 * First check if the user filled the boxes with his credentials
		 */
		if(!Verify.NameVerifier(fname)||!Verify.NameVerifier(lname)) {
			JOptionPane.showMessageDialog(null, "Please Enter a vaild name", "Registration Unuccessful", JOptionPane.ERROR_MESSAGE);	
			return false;
		}
		int l = crn.length;
		for(int i = 0; i<l;i++) {
			if(!Verify.crnVerifier(crn[i])) {
				JOptionPane.showMessageDialog(null, "Please Enter a vaild CRN", "Registration Unuccessful", JOptionPane.ERROR_MESSAGE);	
				return false;
			}
		}
		if(!Verify.IDVerifier(userid)) {
			JOptionPane.showMessageDialog(null, "Please Enter a vaild ID", "Registration Unuccessful", JOptionPane.ERROR_MESSAGE);	
			return false;
		}
		if(!Verify.PassVerifier(password)) {
			messageLabel.setForeground(Color.red);
			messageLabel.setText("<html>Password should at least have<br>1 uppercase letter<br>1 lowercase letter<br>1 special character @#$%&+_!=<br>1 number");
			return false;
		}
		if(department.getSelectedItem().equals(Departments.CHEMISTRY.name())) {
			dep=new String(Departments.CHEMISTRY.name());
		}
		if(department.getSelectedItem().equals(Departments.ECCE.name())) {
			dep=new String(Departments.ECCE.name());
		}
		if(department.getSelectedItem().equals(Departments.PHYSICS.name())) {
			dep=new String(Departments.PHYSICS.name());
		}
		for(int i =0;i<l;i++) {
			if(dep.equals("PHYSICS")&&!isEligiable(crn[i], "PHYSICS")&&isDublicate(crn)) {
				JOptionPane.showMessageDialog(null, "Please enter a CRN that matches student department", "Registration Unsuccesful", JOptionPane.ERROR_MESSAGE);
				return false;
			}
			if(dep.equals("ECCE")&&!isEligiable(crn[i], "ECCE")) {
				JOptionPane.showMessageDialog(null, "Please enter a CRN that matches student department", "Registration Unsuccesful", JOptionPane.ERROR_MESSAGE);
				return false;
			}
			if(dep.equals("CHEMISTRY")&&!isEligiable(crn[i], "CHEMISTRY")) {
				JOptionPane.showMessageDialog(null, "Please enter a CRN that matches student department", "Registration Unsuccesful", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
		if(!studentRadioButton.isSelected()&&!instructorRadioButton.isSelected()) {
			JOptionPane.showMessageDialog(null, "You need to select user type STUDENT or INSTRUCTOR", "Registration Unsuccesful", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if(studentRadioButton.isSelected()) {
			occupation = new String(Occupation.STUDENT.name());
		}
		else if(instructorRadioButton.isSelected()) {
			occupation= new String(Occupation.INSTRUCTOR.name());
		}
		/* to verify username is in the vector or not there
		 * if the user is already in there display error message
		 */
		temp = new Vector<Users>();
		temp = users.getUsers();
		
		c=course.getStudentCourses();
		cc=course.getSenseiCourses();
		boolean idFound = false;
		int len = temp.size();
		int i =0;
		for (i=0;i<len;i++) {
			//to check if the user exists 
			if(userid.equals(temp.elementAt(i).getID())) {
				idFound = true;
			}
		}
		if(idFound) {
			JOptionPane.showMessageDialog(null, "User Exists", "Registration Unsuccessful", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		else {
			JOptionPane.showMessageDialog(null, "Registration Successful", "Registration Completed", JOptionPane.INFORMATION_MESSAGE);
			if(studentRadioButton.isSelected()) {
				temp.add(new Users(fname, lname, userid, password,occupation,dep));
				c.add(new RegistaredStudents(fname,lname,userid,password,occupation,dep,crn));
				users.saveUsers(temp);
				course.saveStudentCourses(c);
			}
			else if(instructorRadioButton.isSelected()) {
				for(int ii = 0;ii<cc.size();ii++) {
					System.out.println(cc.elementAt(ii).gc());
				}
				temp.add(new Users(fname, lname, userid, password,occupation,dep));
				cc.add(new RegistaredStudents(fname,lname,userid,password,occupation,dep,crn));
				users.saveUsers(temp);
				course.saveSenseiCourses(cc);
			}
			return true;
		}
	}
	private boolean isDublicate(String[] crn2) {
		for(int i =0;i<crn2.length;i++) {
			for(int j=i+1;j<crn.length;j++) {
				if(crn2[i].equals(crn2[j])) {
					return false;
				}
			}
		}
		return true;
	}
	public boolean isEligiable(String course,String dep) {
		
		Vector <Courses>l=new Courses().getCourses();
		for(int i = 0; i<l.size();i++) 
			if(l.elementAt(i).getCrn().equals(course)) 
				if(l.elementAt(i).getDept().equals(dep)) 
					return true;
		return false;	
	}
}
