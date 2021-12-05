/*
 * Main class Driver class
 */

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Main {

	public static void main(String[] args) {
		
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
//        	    new AddDropStudents();
//        	    new AddDropInstructor();
//        	    new Register();
//        	    new AdminPage();
//        	    new CoursesPage();
//        	    new AddCourse();
//        	   new RemoveStudentCourses();
//        	   new ModifyStudentDetails();
//        	   new ModifyInstructorDetails();
           }
       });
	}
}
