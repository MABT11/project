import java.awt.event.*;
import java.io.*;
import java.util.Vector;
import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.BevelBorder;

public class AddCourse extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	private JButton addcourseButton = new JButton("Add Course");
	private JButton resetButton = new JButton("Reset");
	private JButton backButton = new JButton("back");
	
	private JTextField courseField = new JTextField(20);
	private JTextField crnField = new JTextField(20);
	private JTextField hoursField = new JTextField(20);
	private JTextField sectionField = new JTextField(20);
	private JTextField timeField = new JTextField(20);
	private JTextField roomField = new JTextField(20);
	private JTextField instructorField = new JTextField(20);
	private JTextField numberofstudentsField = new JTextField(20);
	private JTextField maxstudentsField = new JTextField(20);
	private JTextField startdateField = new JTextField(20);
	private JTextField enddateField = new JTextField(20);
	
	String []s={Departments.PHYSICS.name(), Departments.CHEMISTRY.name(),Departments.ECCE.name()};
	private JComboBox<String> department = new JComboBox<String>(s);
	
	private JLabel courselabel = new JLabel("Course:");
	private JLabel crnlabel = new JLabel("CRN:");
	private JLabel hourslabel = new JLabel("Hours:");
	private JLabel sectionlabel = new JLabel("Section:");
	private JLabel timelabel = new JLabel("Time:");
	private JLabel roomlabel = new JLabel("Room:");
	private JLabel instructorlabel = new JLabel("Instructor:");
	private JLabel numberofstudentslabel = new JLabel("Number of Students:");
	private JLabel maxstudentslabel = new JLabel("Max Students:");
	private JLabel startdatelabel = new JLabel("Start Date:");
	private JLabel enddatelabel = new JLabel("End Date:");
	private JLabel departmentLabel = new JLabel("Department:");
	
	private Courses course = new Courses();
    private String coursename=null,crn=null,hours=null,section=null,time=null,room=null,id=null;
    private String numberofstudents=null,maxstudents=null,startdate=null,enddate=null;
    private Vector<Courses> temp;
    private JPanel panel = new JPanel(new GridBagLayout());
	

	public AddCourse() {
		setTitle("Add Course");
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
	
		addcourseButton.setFocusable(false);
		addcourseButton.addActionListener(this);
				
		resetButton.setFocusable(false);
		resetButton.addActionListener(this);
				
		backButton.setFocusable(false);
		backButton.addActionListener(this);

		constraints.gridx = 0;
        constraints.gridy = 0;
		panel.add(backButton, constraints);
		constraints.gridx = 1;
		constraints.gridx = 2;
		constraints.gridx = 0;
		constraints.gridy = 1;
		panel.add(courselabel,constraints);
		constraints.gridx = 1;
		panel.add(courseField,constraints);
		constraints.gridx = 0;
		constraints.gridy = 2;
		panel.add(crnlabel,constraints);
		constraints.gridx = 1;
		panel.add(crnField,constraints);
		constraints.gridx = 0;
		constraints.gridy = 3;
		panel.add(departmentLabel,constraints);
		constraints.gridx = 1;
		panel.add(department,constraints);
		constraints.gridx = 0;
		constraints.gridy = 4;
		panel.add(hourslabel,constraints);
		constraints.gridx = 1;
		panel.add(hoursField,constraints);
		constraints.gridx = 0;
		constraints.gridy = 5;
		panel.add(sectionlabel,constraints);
		constraints.gridx = 1;
		panel.add(sectionField,constraints);
		constraints.gridx = 0;
		constraints.gridy = 6;
		panel.add(timelabel,constraints);
		constraints.gridx = 1;
		panel.add(timeField,constraints);
		constraints.gridx = 0;
		constraints.gridy = 7;
		panel.add(roomlabel,constraints);
		constraints.gridx = 1;
		panel.add(roomField,constraints);
		constraints.gridx = 0;
		constraints.gridy = 8;
		panel.add(instructorlabel,constraints);
		constraints.gridx = 1;
		panel.add(instructorField,constraints);
		constraints.gridx = 0;
		constraints.gridy = 9;
		panel.add(numberofstudentslabel,constraints);
		constraints.gridx = 1;
		panel.add(numberofstudentsField,constraints);
		constraints.gridx = 0;
		constraints.gridy = 10;
		panel.add(maxstudentslabel,constraints);
		constraints.gridx = 1;
		panel.add(maxstudentsField,constraints);
		constraints.gridx = 0;
		constraints.gridy = 11;
		panel.add(startdatelabel,constraints);
		constraints.gridx = 1;
		panel.add(startdateField,constraints);
		constraints.gridx = 0;
		constraints.gridy = 12;
		panel.add(enddatelabel,constraints);
		constraints.gridx = 1;
		panel.add(enddateField,constraints);
		constraints.gridx = 0;
		constraints.gridy = 13;
		constraints.anchor = GridBagConstraints.CENTER;
		panel.add(resetButton,constraints);
		constraints.gridx = 1;
		panel.add(addcourseButton,constraints);
		add(panel);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setMinimumSize(new Dimension(600, 700));
		setLocationRelativeTo(null);
		setVisible(true);	
	}
		@Override
		public void actionPerformed(ActionEvent e) {
			//reset button was clicked
			if(e.getSource()==resetButton) {
				courseField.setText("");
				crnField.setText("");
				hoursField.setText("");
				sectionField.setText("");
				timeField.setText("");
				roomField.setText("");
				instructorField.setText("");
				numberofstudentsField.setText("");
				maxstudentsField.setText("");
				startdateField.setText("");
				enddateField.setText("");
			}
			/*
			 * if back was pressed go back to the Adminpage
			 */
			if(e.getSource()==backButton) {
				new CoursesPage();
				 dispose();
			}
			//register button was clicked
			if(e.getSource()==addcourseButton) {
				if(addcourse()){
					new CoursesPage();
					dispose();
				}
			}
			
		}
		public boolean addcourse() {
			coursename = courseField.getText().strip().replace(" ", "");
			crn = crnField.getText().strip().replace(" ", "");
			hours = hoursField.getText().strip().replace(" ", "");
			section = sectionField.getText().strip().replace(" ", "");
			time = timeField.getText().strip().replace(" ", "");
			room = roomField.getText().strip().replace(" ", "");
			id = instructorField.getText().strip().replace(" ", "");
			numberofstudents = numberofstudentsField.getText().strip().replace(" ", "");
			maxstudents = maxstudentsField.getText().strip().replace(" ", "");
			startdate = startdateField.getText().strip().replace(" ", "");
			enddate = enddateField.getText().strip().replace(" ", "");
			String dep =null;
			if(!Verify.crnVerifier(crn)) {
				JOptionPane.showMessageDialog(null, "Please Enter a vaild CRN", "Registration Unuccessful", JOptionPane.ERROR_MESSAGE);	
				return false;
			}
			if(department.getSelectedItem().equals(Departments.CHEMISTRY.name())) {
				dep=Departments.CHEMISTRY.name();
			}
			if(department.getSelectedItem().equals(Departments.ECCE.name())) {
				dep=Departments.ECCE.name();
			}
			if(department.getSelectedItem().equals(Departments.PHYSICS.name())) {
				dep=Departments.PHYSICS.name();
			}
			/*
			 * First check if the user filled the boxes with his credentials
			 */
		
			/* to verify username is in the vector or not there
			 * if the user is already in there display error message
			 */
			temp = new Vector<Courses>();
			temp = course.getCourses();
			boolean flag = false;
			int len = temp.size();
			int i =0;
			for (i=0;i< len;i++) {
				//to check if the crn exists 
				if(crn.equals(temp.elementAt(i).getCrn())) {
					flag = true;
				}
			}
			if(flag) {
				JOptionPane.showMessageDialog(null, "Course Exists", "Registration Unsuccessful", JOptionPane.ERROR_MESSAGE);
				return false;
			}
			else {
				//add users to the vector of type user object
				temp.add(new Courses(coursename, crn, hours, section,time,room,id,numberofstudents,maxstudents,startdate,enddate,dep));
				JOptionPane.showMessageDialog(null, "Registration Successful", "Registration Completed", JOptionPane.INFORMATION_MESSAGE);
				course.saveCourses(temp);
				return true;
			}
		}
		
}

