/*
 * Main class Driver class
 */
import java.io.IOException;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Main {

	public static enum type{
		STUDENT,
		INSTRUCTOR,
		ADMIN		
	}
	public static void main(String[] args) throws IOException {
		
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
//        	   new LoginPage();
        	   new AddDropStudents();
//        	   new AddDropInstructor();
//        	   new Register();
//        	   new AdminPage();
           }
       });
//		System.out.println(Verify.IDVerifier("100053896"));
	}
}
