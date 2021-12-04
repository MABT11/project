/*
 * Main class Driver class
 */

import java.util.Vector;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Main {

	public static void main(String[] args) {
		
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
		
//		RegistaredStudents r = new RegistaredStudents();
//		Courses course = new Courses();
//		Vector<RegistaredStudents>c=new Vector<RegistaredStudents>();
//
//		c = course.getStudentCourses();
//		Vector<String> courses=new Vector<>();
//		String studentid="100053896",crn="1234";
//		int len = c.size();
//		for(int i =0; i <len;i++) {
//			//check if the id matches the student id or not 
//			if(studentid.equals(c.elementAt(i).getID())) {
//				courses=c.elementAt(i).gv();
//				int len2=courses.size();//
//				for(int j =0;j<len2;j++) {
//					//if the course crn matches with the input crn
//					if(courses.elementAt(j).equals(crn)) {
//						//when i change this object i want the one below to get changed
//						c.elementAt(i).setCourseAt(j);
//						System.out.println(c.elementAt(i).getCourse());
////						course.saveStudentCoursesAdd(c);
//
//					}
//				}
//			}
//		}
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
           }
       });
	}
}
