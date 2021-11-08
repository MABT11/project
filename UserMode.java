/*
 * The gui in which we know what type of user we are dealing with admin, student or instructor
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.awt.Font;
import java.awt.GridLayout;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class UserMode implements ActionListener {
	/*
	 * creating our instance variables, window and buttons 
	 */
	JFrame frame = new JFrame("Banner Self Service");
	JLabel labl = new JLabel("Welcome");
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
		URL url = new URL("https://i.pinimg.com/originals/b3/cf/46/b3cf46044ddf5c8c9eeaffdc3eab27e2.jpg");
		
		frame.setIconImage(ImageIO.read(url));
		adminButton.setFocusable(false);
		adminButton.addActionListener(this);
		adminButton.setFont(new Font(null,Font.BOLD,20));
		
		studentButton.setFocusable(false);
		studentButton.addActionListener(this);
		studentButton.setFont(new Font(null,Font.BOLD,20));
		
		instructorButton.setFocusable(false);
		instructorButton.addActionListener(this);
		instructorButton.setFont(new Font(null,Font.BOLD,20));
		
		frame.setLayout(new GridLayout(3,3));
		frame.add(new JLabel(""));
		frame.add(instructorButton);
		frame.add(new JLabel(""));
		frame.add(new JLabel(""));
		frame.add(studentButton);
		frame.add(new JLabel(""));
		frame.add(new JLabel(""));
		frame.add(adminButton);
		frame.add(new JLabel(""));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(720,400);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==studentButton) {
			new LoginPage(logininfo);
			frame.dispose();
		}
		if(e.getSource()==instructorButton) {
			new LoginPage(logininfo);
			frame.dispose();
		}
		if(e.getSource()==adminButton) {
			new LoginPage(logininfo);
			frame.dispose();
		}
	}
}
