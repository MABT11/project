import java.awt.event.*;
import java.util.Vector;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.BevelBorder;

public class RemoveStudentCourses extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	private JButton removeCourseButton = new JButton("Remove Student");
	private JButton backButton = new JButton("back");
	
	private JTextField idField = new JTextField(20);
	private JTextField crnField = new JTextField(20);
	
	private JLabel idLabel = new JLabel("Student ID:");
	private JLabel crnlabel = new JLabel("CRN:");
	
	private Courses course = new Courses();
    private String studentid=null,crn=null;
 
	private Vector<RegistaredStudents>c=new Vector<RegistaredStudents>();
	
	private JPanel panel = new JPanel(new GridBagLayout());
	

	public RemoveStudentCourses() {
		setTitle("Remove Course");
		
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(10, 10, 10, 10);
		panel.setBackground(Color.white);
		panel.setOpaque(true);
		panel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		panel.setBorder(BorderFactory.createEtchedBorder());
		panel.setBorder(BorderFactory.createLineBorder(Color.black));
		panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Remove Student Courses"));
	

		removeCourseButton.setFocusable(false);
		removeCourseButton.addActionListener(this);
				
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
		panel.add(removeCourseButton,constraints);
		add(panel);		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);	
	}
		@Override
		public void actionPerformed(ActionEvent e) {
			/*
			 * if back was pressed go back to the Adminpage
			 */
			if(e.getSource()==backButton) {
				new AddDropStudents();
				 dispose();
			}
			//register button was clicked
			if(e.getSource()==removeCourseButton) {
				if(remove_course()){
					new AddDropStudents();
					dispose();
				}
			}
		}
		public boolean remove_course() {
			studentid = idField.getText().strip().replace(" ", "");
			crn = crnField.getText().strip();
			if(!Verify.crnVerifier(crn)) {
				JOptionPane.showMessageDialog(null, "Please enter one CRN at a time", "Registration Failed", JOptionPane.ERROR_MESSAGE);
				return false;
			}
			 /*
			  * 1 i need a list of all students with their courses getStudentCourses
			  * 2 check if he has the course 
			  * 3 delete the course
			  */
			c = course.getStudentCourses();
			Vector<String> courses=new Vector<>();
			
			int len = c.size();
			boolean crnNotFound=false;
			boolean idNotFound=false;
			for(int i =0; i <len;i++) {
				//check if the id matches the student id or not 
				if(studentid.equals(c.elementAt(i).getID())) {
					courses=c.elementAt(i).gv();
					int len2=courses.size();//
					for(int j =0;j<len2;j++) {
						//if the course crn matches with the input crn
						if(courses.elementAt(j).equals(crn)) {
							//when i change this object i want the one below to get changed
							c.elementAt(i).setCourseAt(j);
							course.saveStudentCoursesAdd(c);
							return true;
						}
						else {
							crnNotFound=true;
						}
					}
					idNotFound=false;
				}
				else {
					idNotFound=true;
				}
			}
			if(idNotFound)
				JOptionPane.showMessageDialog(null, "Student ID not found", "Registration Failed", JOptionPane.ERROR_MESSAGE);
			if(crnNotFound)
				JOptionPane.showMessageDialog(null, "CRN was not found", "Registration Failed", JOptionPane.ERROR_MESSAGE);
			return false;
		}
}