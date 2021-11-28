import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Vector;
import javax.swing.JOptionPane;

public class Courses {
	
	private String course,numberofstudents;
	private String crn,maxstudents,startdate,enddate;
	private String hours, instructor, time, section,room;
	
	public Courses(String course, String crn, String hours, String section, String time, String room, String instructor, String numberofstudents, String maxstudents, String startdate, String enddate) {
		this.course = course;
		this.crn = crn;
		this.hours = hours;
		this.section = section;
		this.time = time;
		this.room = room;
		this.instructor = instructor;
		this.numberofstudents = numberofstudents;
		this.maxstudents = maxstudents;
		this.startdate = startdate;
		this.enddate = enddate;
	}
	public Courses() {
		this.course = null;
		this.crn = null;
		this.hours = null;
		this.section = null;
		this.time = null;
		this.room = null;
		this.instructor = null;
		this.numberofstudents = null;
		this.maxstudents = null;
		this.startdate = null;
		this.enddate = null;
	}
	public String getCourse() {
		return this.course;
	}
	public String getCourseRealName() {
		return this.course.replace('-', ' ');
	}
	public String getCrn() {
		return this.crn;
	}
	public String getHours() {
		return this.hours;
	}
	public String getSection() {
		return this.section;
	}
	public String getTime() {
		return this.time;
	}
	public String getRoom() {
		return this.room;
	}
	public String getInstructor() {
		return this.instructor;
	}
	public String getNumberOfStudents() {
		return this.numberofstudents;
	}
	public String getMaxstudents() {
		return this.maxstudents;
	}
	public String getStartdate() {
		return this.startdate;
	}
	public String getEnddate() {
		return this.enddate;
	}
//	public String getStudentCourses() {
//		Vector<Courses> courses = new Vector<Courses>();
//		Vector<Users> users =new Vector<Users>();
//		Users u=new Users();
//		courses=getCourses();
//		
//		
//		/*
//		 * for each student check each course if he is enrolled in it or not
//		 * then return an array consisting of all the courses he is enrolled in
//		 */
//		int students =u.getStudents();
//		int count = 0;
//		for(int i = 0; i<students;i++) {
//			
//		}
//		
//		return "";
//	}
	//setters methods
	public void setCourse(String course) {
		this.course = course;
	}
	public void setCrn(String crn) {
		this.crn = crn;
	}
	public void setHours(String hours) {
		this.hours = hours;
	}
	public void setSection(String section) {
		this.section = section;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public void setRoom(String room) {
		this.room = room;
	}
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
	public void setNumberofstudents(String students) {
		this.numberofstudents = students;
	}
	public void setMaxstudents(String max) {
		this.maxstudents = max;
	}
	public void setStartdate(String start) {
		this.startdate = start;
	}
	public void setEnddate(String end) {
		this.enddate = end;
	}
	/*
	 * each time you add a student you will do this 
	 */
	public void saveCourses(Vector<Courses> save) {
		try {
			PrintWriter S = new PrintWriter(new FileWriter("courses.txt"));
			int len = save.size();
			for(int i = 0;i<len;i++) {
				S.println(save.elementAt(i).getCourse()
						+" "+save.elementAt(i).getCrn()
						+" "+save.elementAt(i).getHours()
						+" "+save.elementAt(i).getSection()
						+" "+save.elementAt(i).getTime()
						+" "+save.elementAt(i).getRoom()
						+" "+save.elementAt(i).getInstructor()
						+" "+save.elementAt(i).getNumberOfStudents()
						+" "+save.elementAt(i).getMaxstudents()
						+" "+save.elementAt(i).getStartdate()
						+" "+save.elementAt(i).getEnddate());
			}
			S.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public Vector<Courses> getCourses() {
		Vector<Courses> load = new Vector<Courses>();
		try {
			File courses = new File("courses.txt");
			Scanner read = new Scanner(courses);
			while (read.hasNext()) {
				load.add(new Courses(read.next(), read.next(), read.next(),
									read.next(),read.next(), read.next(),
									read.next(), read.next(), read.next(),
									read.next(), read.next()));
			}
			if(courses.length()<100) {
				addCoursesFile();
			}
			read.close();
		} catch (FileNotFoundException e) {
			addCoursesFile();
			return getCourses();	
		}
		return load;
	}
	public void addCoursesFile() {
//name, crn, credit hours, section, time, building, instructor name, registered student, capacity, start, end
		String o ="Object-Orientied-Programming 1234 4 B1 12:50 G10001 Majid "+this.getNumberOfStudents()+" 30 25/8 12/12\n"
				+ "Object-Orientied-Programming 4567 4 B2 12:50 G10002 Ahmed 20 30 25/8 12/12\n"
				+ "Object-Orientied-Programming 1346 4 B3 12:50 G10001 Omar 29 30 25/8 12/12\n"
				+ "Object-Orientied-Programming 2486 4 B4 12:50 G10001 Ali 30 30 25/8 12/12\n"
				+ "English-I 1793 4 3 12:50 G10001 Salem 19 30 25/8 12/12\n"
				+ "Nanochemistry 8426 4 4 12:50 G10001 Khaled 15 30 25/8 12/12\n"
				+ "Chemistry-II 4637 4 5 12:50 G10002 Ahmed 20 30 25/8 12/12\n"
				+ "Chemistry-I 1346 4 6 12:50 G10001 Omar 29 30 25/8 12/12\n"
				+ "Physics-I 2486 4 7 12:50 G10001 Ali 30 30 25/8 12/12\n"
				+ "Physics-II 7510 4 8 12:50 G10001 Salem 19 30 25/8 12/12\n"
				+ "Calculus-I 8426 4 9 12:50 G10001 Khaled 15 30 25/8 12/12\n"
				+ "Calculus-II 4567 4 10 12:50 G10002 Ahmed 20 30 25/8 12/12\n"
				+ "Calculus-III 1346 4 11 12:50 G10001 Omar 29 30 25/8 12/12\n"
				+ "Digital-Logic-Design 2486 4 12 12:50 G10001 Ali 30 30 25/8 12/12\n"
				+ "Statics 2020 4 2 12:50 G10001 Salem 19 30 25/8 12/12\n"
				+ "Thermodynamics 8426 4 3 12:50 G10001 Khaled 15 30 25/8 12/12\n"
				+ "Economics 4961 4 1 12:50 G10002 Ahmed 20 30 25/8 12/12\n"
				+ "Accounting 9376 4 2 12:50 G10001 Omar 29 30 25/8 12/12\n"
				+ "Biochemistry 2486 4 6 12:50 G10001 Ali 30 30 25/8 12/12\n"
				+ "Geomatics 1793 4 9 12:50 G10001 Salem 19 30 25/8 12/12\n"
				+ "Thermodynamics 8426 4 8 12:50 G10001 Khaled 15 30 25/8 12/12\n";
		try { 	
			PrintWriter S = new PrintWriter(new File("courses.txt"));
			S.println(o);
			S.close();
		}catch(IOException e) {
			JOptionPane.showMessageDialog(null, "Error starting the program", "Error", 0, null);
			e.printStackTrace();
		}
	}
	
	@Override
	public String toString() {
		String o="Course: "+this.getCourse()+" Crn: "+this.getCrn();
		o += " Hours: "+this.getHours()+" Section: "+this.getSection();
		o += " Time: "+this.getTime()+" Room: "+this.getRoom();
		o += " Instructor: "+this.getInstructor()+" NumberofStudents: "+this.getNumberOfStudents();
		o += " MaxStudents: "+this.getMaxstudents()+" Start Date: "+this.getStartdate();
		o += " End Date: "+this.getEnddate();
		return o;
	}

	public Object[][] coursesInfo() {
		
		Vector<Courses> s =new Vector<Courses>();
		s = getCourses();
		int len = s.size();
		
		Object[][] classes =new Object[len][11];
		for(int i = 0; i < len;i++) {
			classes[i][0]=s.elementAt(i).getCrn();
			classes[i][1]=s.elementAt(i).getCourseRealName();
			classes[i][2]=s.elementAt(i).getHours();
			classes[i][3]=s.elementAt(i).getSection();
			classes[i][4]=s.elementAt(i).getTime();
			classes[i][5]=s.elementAt(i).getRoom();
			classes[i][6]=s.elementAt(i).getInstructor();
			classes[i][7]=s.elementAt(i).getNumberOfStudents();
			classes[i][8]=s.elementAt(i).getMaxstudents();
			classes[i][9]=s.elementAt(i).getStartdate();
			classes[i][10]=s.elementAt(i).getEnddate();
		}
		return classes;
	}
}
