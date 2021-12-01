/*
 * Main class Driver class
 */

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Vector;
import javax.imageio.ImageIO;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Main {
	/*
	 * static because it will be the same for all pages
	 */
	private static BufferedImage bufferedImage;

	public static void main(String[] args) {
		/*
		 * download & crop the university logo
		 */
		try {
			bufferedImage = ImageIO.read(new File("kulogo.png"));
			} catch (IOException io) {
			System.out.println("You don't have ku logo on the current directory ");
			try{
				bufferedImage = ImageIO.read(new URL("https://media.glassdoor.com/sqll/500688/khalifa-university-squarelogo-1555333009225.png"));
			}catch(IOException e) {
				System.out.println("You don't have internet connection and you don't have kulogo.png file");
			}
		}
		
//		Courses course = new Courses();
//		course.getStudentCourses();		
		
		
//		Users u = new Users();
//		Vector<Users>v=new Vector<>();
//		v=u.getStudentVector();
//		for(int i = 0; i<16;i++)
//			System.out.println(v.elementAt(i).getID());
//		RegistaredStudents r = new RegistaredStudents();
//		System.out.println(r.getNumberOfStudentEnrolled("1346"));
//		System.out.println(r.getNumberOfStudentEnrolled("8426"));
//		System.out.println(r.getNumberOfStudentEnrolled("8425"));
//		System.out.println(r.getNumberOfStudentEnrolled("4321"));
//		System.out.println(r.getNumberOfStudentEnrolled("4961"));
//		System.out.println(r.getNumberOfStudentEnrolled("2486"));
//		System.out.println(r.getNumberOfStudentEnrolled("4567"));
//		System.out.println(r.getNumberOfStudentEnrolled("2859"));
		try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
		 /*
         * For thread safety only
         */
       SwingUtilities.invokeLater(new Runnable() {
           @Override
           public void run() {
      	  
        	   new LoginPage();
//        	   new AddDropStudents();
//        	   new AddDropInstructor();
//        	   new Register();
//        	   new AdminPage();
//        	   new CoursesPage();
//        	   new AddCourse();
           }
       });
	}
	/*
	 * getter method to use the logo everywhere
	 */
	public static BufferedImage getIcon() {
		return bufferedImage.getSubimage(6, 8, 165, 170);
	}
}