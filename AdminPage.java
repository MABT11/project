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
	private JButton logoutButton = new JButton("Logout");
	private JPanel panel = new JPanel(new GridBagLayout());
	
	public AdminPage(){
		setTitle("Banner Self Service");
		try {
			setIconImage(ImageIO.read(new File("ku.png")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//square box around the word
		logoutButton.setFocusable(false);
		//to add functionality to the box
		logoutButton.addActionListener(this);
		
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(10, 10, 10, 10);
		
		addDropInstructor.setForeground(Color.BLUE.darker());
		addDropInstructor.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		addDropInstructor.addMouseListener(this);
		addDropStudent.setForeground(Color.BLUE.darker());
		addDropStudent.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		addDropStudent.addMouseListener(this);
		
		
		panel.setBackground(Color.white);
		panel.setOpaque(true);
		panel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		panel.setBorder(BorderFactory.createEtchedBorder());
		panel.setBorder(BorderFactory.createLineBorder(Color.black));
		panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Dashboard"));
		constraints.fill =  GridBagConstraints.VERTICAL;
		
		constraints.gridx = 0;
        constraints.gridy = 0;
		panel.add(logoutButton,constraints);
        constraints.gridy = 1;
        panel.add(addDropStudent,constraints);
        constraints.gridx = 1;
        panel.add(addDropInstructor,constraints);
        add(panel);
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==addDropInstructor) {
			new addDropInstructor();
			dispose();
		}
		
		if(e.getSource()==logoutButton) {
			new LoginPage();
			dispose();
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource()==addDropStudent) {
			new addDropStudents();
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