/*
 * Main class Driver class
 */

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
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