/*
 * Dashboard gui not completed at all
 */

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.BevelBorder;
/*
 * the filter is mouse event
 */
public class AdminPage extends JFrame implements ActionListener, MouseListener {
	
	private JLabel addDropStudent = new JLabel("Add and drop Students");
	private JLabel addDropInstructor = new JLabel("Add and drop Instructor");
	private JLabel courses = new JLabel("Courses");
	
	private JButton logoutButton = new JButton("Logout");
	private JButton registerButton = new JButton("Register new users");

	private JPanel panel = new JPanel(new GridBagLayout());
	
	public AdminPage(){
		
		setTitle("Banner Self Service");
		try {
			setIconImage(ImageIO.read(new File("ku.png")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		logoutButton.setFocusable(false);
		logoutButton.addActionListener(this);
		
		registerButton.addActionListener(this);
		registerButton.setFocusable(false);
		
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(10, 10, 10, 10);
		
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
		
		//panel and colors
		panel.setBackground(Color.white);
		panel.setOpaque(true);
		panel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		panel.setBorder(BorderFactory.createEtchedBorder());
		panel.setBorder(BorderFactory.createLineBorder(Color.black));
		panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Dashboard"));
		constraints.fill =  GridBagConstraints.VERTICAL;
		
		//adding everything to the panel
		constraints.gridx = 0;
        constraints.gridy = 0;
		panel.add(logoutButton,constraints);
		constraints.gridx=1;
		panel.add(registerButton,constraints);
		constraints.gridx=0;
        constraints.gridy = 1;
        panel.add(addDropStudent,constraints);
        constraints.gridx = 1;
        panel.add(addDropInstructor,constraints);
        constraints.gridx=0;
        constraints.gridy = 2;
        panel.add(courses,constraints);
        
        add(panel);
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
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