import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JOptionPane;

public class Student extends Users{
	
	public Student() {
		super();
	}
	
//	public Vector<Courses> getCourses() {
//		Vector<Courses> load = new Vector<Courses>();
//		try {
//			File courses = new File("courses.txt");
//			Scanner read = new Scanner(courses);
//			while (read.hasNext()) {
//				load.add(new Courses(read.next(), read.next(), read.next(),
//									read.next(),read.next(), read.next(),
//									read.next(), read.next(), read.next(),
//									read.next(), read.next()));
//			}
//			if(courses.length()<100) {
//				addCoursesFile();
//			}
//			read.close();
//		} catch (FileNotFoundException e) {
//			addCoursesFile();
//			return getCourses();	
//		}
//		return load;
//	}
//	public void addCoursesFile() {
////name, crn, credit hours, section, time, building, instructor name, student number, capacity, start, end
////		String o =getID()+","getCourses();
//		try { 	
//			PrintWriter S = new PrintWriter(new File("courses.txt"));
//			S.println(o);
//			S.close();
//		}catch(IOException e) {
//			JOptionPane.showMessageDialog(null, "Error starting the program", "Error", 0, null);
//			e.printStackTrace();
//		}
//	}

}
