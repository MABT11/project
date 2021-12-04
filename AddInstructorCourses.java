import java.awt.event.*;
import java.util.Vector;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.BevelBorder;

public class AddInstructorCourses extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	private JButton addcourseButton = new JButton("Assign Course");
	private JButton backButton = new JButton("back");
	
	private JTextField idField = new JTextField(20);
	private JTextField crnField = new JTextField(20);
	
	private JLabel idLabel = new JLabel("Instructor ID:");
	private JLabel crnlabel = new JLabel("CRN:");
	
	private Courses course = new Courses();
    private String studentid=null,crn=null;
    private Vector<RegistaredStudents>c=new Vector<RegistaredStudents>();
	private JPanel panel = new JPanel(new GridBagLayout());
	

	public AddInstructorCourses() {
		setTitle("Add Course");
		
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(10, 10, 10, 10);
		panel.setBackground(Color.white);
		panel.setOpaque(true);
		panel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		panel.setBorder(BorderFactory.createEtchedBorder());
		panel.setBorder(BorderFactory.createLineBorder(Color.black));
		panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Course Load"));
	

		addcourseButton.setFocusable(false);
		addcourseButton.addActionListener(this);
				
		backButton.setFocusable(false);
		backButton.addActionListener(this);

		constraints.gridx = 0;
        constraints.gridy = 0;
		panel.add(backButton, constraints);
		constraints.gridy = 1;
		panel.add(idLabel,constraints);
		constraints.gridx = 1;
		panel.add(idField,constraints);
		constraints.gridx = 0;
		constraints.gridy = 2;
		panel.add(crnlabel,constraints);
		constraints.gridx = 1;
		panel.add(crnField,constraints);
		constraints.gridy = 3;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.gridx = 1;
		panel.add(addcourseButton,constraints);
		add(panel);		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);	
	}
		@Override
		public void actionPerformed(ActionEvent e) {
			/*
			 * if back was pressed go back to the add drop instructor where the jtable is at
			 */
			if(e.getSource()==backButton) {
				new AddDropInstructor();
				 dispose();
			}
			//register button was clicked
			if(e.getSource()==addcourseButton) {
				if(addcourse()){
					new AddDropInstructor();
					dispose();
				}
			}
		}
		public boolean addcourse() {
			studentid = idField.getText().strip().replace(" ", "");
			crn = crnField.getText().strip();
			if(!Verify.crnVerifier(crn)) {
				JOptionPane.showMessageDialog(null, "Please enter one CRN at a time", "Registration Failed", JOptionPane.ERROR_MESSAGE);
				return false;
			}
			c=course.getSenseiCourses();
			int len = c.size();
			for (int i=0;i<len;i++) {
				//to check if the user exists 
				if(studentid.equals(c.elementAt(i).getID())) {
					c.elementAt(i).addCourse(crn,c.elementAt(i).getDepartment());
					course.saveSenseiCoursesAdd(c);
					return true;	
				}
			}
			return false;
		}
}

