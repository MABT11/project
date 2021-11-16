/*
 * The gui in which we know what type of user we are dealing with admin, student or instructor
 */

import java.awt.event.*;
import java.net.URL;
import java.io.*;
import java.util.HashMap;
import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public class UserMode extends JFrame implements ActionListener {
	/*
	 * creating our instance variables, window and buttons 
	 */
	
	JLabel labl = new JLabel();
	JButton instructorButton = new JButton("Instructor");
	JButton studentButton = new JButton("Student");
	JButton adminButton = new JButton("Admin");
	HashMap<String,String> logininfo = new HashMap<String,String>();
	
	public UserMode(HashMap<String,String> loginInfo) throws IOException{
		/*
		 * copying the hash function so the other window can have the current info about the users
		 */
		logininfo = loginInfo;
		/*
		 * to download an image from the Internet to appear as the icon for the program
		 */
		// URL url = new URL("https://media.glassdoor.com/sqll/500688/khalifa-university-squarelogo-1555333009225.png");
		setTitle("Banner Self Service");
		setIconImage(ImageIO.read(new File("C:/Users/Mubarak/Desktop/ku.png")));
		adminButton.setFocusable(false);
		adminButton.addActionListener(this);
		adminButton.setFont(new Font(null,Font.BOLD,20));
		setTitle("Welcome");
		studentButton.setFocusable(false);
		studentButton.addActionListener(this);
		studentButton.setFont(new Font(null,Font.BOLD,20));
		
		instructorButton.setFocusable(false);
		instructorButton.addActionListener(this);
		instructorButton.setFont(new Font(null,Font.BOLD,20));
		
		setLayout(new GridLayout(3,3));
		add(new JLabel(""));
		add(instructorButton);
		add(new JLabel(""));
		add(new JLabel(""));
		add(studentButton);
		add(new JLabel(""));
		add(new JLabel(""));
		add(adminButton);
		add(new JLabel(""));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(720,400);
		setVisible(true);
		setLocationRelativeTo(null);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==studentButton) {
			new LoginPage(logininfo);
			dispose();
		}
		if(e.getSource()==instructorButton) {
			new LoginPage(logininfo);
			dispose();
		}
		if(e.getSource()==adminButton) {
			new LoginPage(logininfo);
			dispose();
		}
	}
}
