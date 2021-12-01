import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JOptionPane;

public class Student extends Users{
	private Vector <Student> courses;
	
	public Student(Vector<Student>courses) {
		this.setCourses(courses);
	}
	public Student() {
		super();
		this.setCourses(null);
	}

	public Vector <Student> getCourses() {
		return courses;
	}
	public void setCourses(Vector <Student> courses) {
		this.courses = courses;
	}

}
