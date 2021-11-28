/*
 * Dashboard gui not completed at all
 */

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.BevelBorder;
/*
 * the filter is mouse event
 */
public class AdminPage extends JFrame implements ActionListener, MouseListener {
	
	private JLabel addDropStudent;
	private JLabel addDropInstructor;
	private JLabel courses;
	
	private JButton logoutButton;
	private JButton registerButton;
	
	
	private JPanel panel;
	private JLabel studentLabel;
	private JLabel facultyLabel;
	
	public AdminPage(){
		
		setTitle("Banner Self Service");
		setIconImage(Main.getIcon());
		init();
		
		studentLabel.setToolTipText("The number of currently enrolled students in the system");
		facultyLabel.setToolTipText("The number of currently enrolled Faculty admin and instructors in the system");
		
		logoutButton.setFocusable(false);
		logoutButton.addActionListener(this);
		logoutButton.setToolTipText("Logout of the system");
		logoutButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		registerButton.addActionListener(this);
		registerButton.setFocusable(false);
		registerButton.setToolTipText("Register new users to the system");
		registerButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		//like the hyperlink text change cursor make it underlined and blue
		addDropInstructor.setForeground(Color.BLUE.darker());
		addDropInstructor.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		addDropInstructor.addMouseListener(this);
		
		addDropStudent.setForeground(Color.BLUE.darker());
		addDropStudent.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		addDropStudent.addMouseListener(this);
		
		courses.setForeground(Color.BLUE.darker());
		courses.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		courses.addMouseListener(this);
		
		/*
		 * configuring the panel and the layout then adding everything to the panel
		 */
		panel.setBackground(Color.white);
		panel.setOpaque(true);
		panel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		panel.setBorder(BorderFactory.createEtchedBorder());
		panel.setBorder(BorderFactory.createLineBorder(Color.black));
		panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Dashboard"));
		
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(10, 10, 10, 10);
		constraints.fill =  GridBagConstraints.VERTICAL;
		
		constraints.gridx = 0;
        constraints.gridy = 0;
		panel.add(logoutButton,constraints);
		
		constraints.gridx=1;
		panel.add(registerButton,constraints);
		
		constraints.gridx=0;
        constraints.gridy=1;
        panel.add(addDropStudent,constraints);
        
        constraints.gridx=1;
        panel.add(addDropInstructor,constraints);
        
        constraints.gridx=0;
        constraints.gridy=2;
        panel.add(courses,constraints);
        
        constraints.gridx=0;
        constraints.gridy = 3;
        panel.add(facultyLabel,constraints);
        
        constraints.gridx=1;
        constraints.gridy = 3;
        panel.add(studentLabel,constraints);
        add(panel);
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	/*
	 * Initializing all attributes
	 */
	public void init() {
		addDropStudent = new JLabel("Add and drop Students");
		addDropInstructor = new JLabel("Add and drop Instructors");
		courses = new JLabel("Courses");
		
		logoutButton= new JButton("Logout");
		registerButton = new JButton("Register");
		
		Users users = new Users();
		studentLabel=new JLabel("Number of students " +users.getStudents());
		facultyLabel=new JLabel("Number of faculty "+users.getFaculty());
		
		panel = new JPanel(new GridBagLayout());
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==logoutButton) {
			new LoginPage();
			dispose();
		}
		if(e.getSource()==registerButton) {
			new Register();
			dispose();
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource()==addDropStudent) {
			new AddDropStudents();
			dispose();
		}
		if(e.getSource()==addDropInstructor) {
			new AddDropInstructor();
			dispose();
		}
		if(e.getSource()==courses) {
			new CoursesPage();
			dispose();
		}
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}