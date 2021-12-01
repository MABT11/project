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
	private String hours, id, time, section,room;

	
	public Courses(String course, String crn, String hours, String section, String time, String room, String id,String numberofstudents, String maxstudents, String startdate, String enddate) {
		this.course = course;
		this.crn = crn;
		this.hours = hours;
		this.section = section;
		this.time = time;
		this.room = room;
		this.id=id;
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
		this.id=null;
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
	public String getID() {
		return this.id;
	}
	public String getNumberOfStudents() {
		return this.numberofstudents;
	}
	public String getMaxStudents() {
		return this.maxstudents;
	}
	public String getStartdate() {
		return this.startdate;
	}
	public String getEnddate() {
		return this.enddate;
	}
	/*
	 * it will return a vector with each student with his her regitared courses
	 */
	public Vector<RegistaredStudents> getStudentCourses() {

		Vector<Users> student =new Vector<Users>();
		Vector <RegistaredStudents>r=new Vector<RegistaredStudents>();
		Users u=new Users();
		student=u.getStudentVector();
		/*
		 * for each student check each course if he is enrolled in it or not
		 * then return an array consisting of all the courses he is enrolled in
		 */
		try{
			File file = new File("studentCourses.txt");
			Scanner read = new Scanner(file);
			//read each line
			while (read.hasNextLine()) {
		
				String splited[] = read.nextLine().split(" ");
				
				int len = splited.length;
				String crn[]=new String[len-1];
				for(int i = 1; i<len;i++) {
					crn[i-1]=splited[i];
				}
				int slen= student.size();
				for(int i =0;i <slen;i++) {
					if(student.elementAt(i).getID().equals(splited[0])) {
					r.add(new RegistaredStudents(student.elementAt(i).getFirstName(),
							student.elementAt(i).getLastName(),
							student.elementAt(i).getID(),
							student.elementAt(i).getPassword(),
							student.elementAt(i).getOccupation(),
							student.elementAt(i).getDepartment(),
							crn));
				}
//					for(int j = 0; j <len;j++) {
//						if(getCrn().equals(crn[j])) {
//							setNumberOfStudents(getNumberOfStudents()+1);
//						}
					}
			}
			if(file.length()<100) {
				addStudentCoursesFile();
			}
			read.close();
		} catch (FileNotFoundException e) {
			addStudentCoursesFile();
			return getStudentCourses();	
		}
		return r;
	}
	public void addStudentCoursesFile() {
		//student id, registared courses
			String o ="100053896 1234 4321 1346\n"
					+ "100001234 4961 8486 8426 8425\n"
					+ "100011111 1234 4321 1346\n"
					+ "123456789 1234 4321 1346\n"
					+ "789456123 4961 8486 8426 8425\n"
					+ "123454321 2486 7510 2020 2859 4567\n"
					+ "123421231 1234 4321 1346\n"
					+ "123432256 2486 7510 2020 2859 4567\n"
					+ "100012222 1234 4321 1346\n"
					+ "951313333 2486 7510 2020 2859 4567\n"
					+ "851666666 1234 4321 1346\n"
					+ "777114586 1234 4321 1346\n"
					+ "888888895 4961 8486 8426 8425\n"
					+ "999999999 4961 8486 8426 8425\n"
					+ "101010101 1234 4321 1346\n"
					+ "741965256 2486 7510 2020 2859 4567";
			try { 	
				PrintWriter S = new PrintWriter(new File("studentCourses.txt"));
				S.println(o);
				S.close();
			}catch(IOException e) {
				JOptionPane.showMessageDialog(null, "Error starting the program", "Error", 0, null);
				e.printStackTrace();
			}
	}
	/*
	 * it will return a vector that has the instructor information along with his load/courses that he teaches
	 */
	public Vector<RegistaredStudents> getSenseiCourses() {
		
		Vector<Users> instructor =new Vector<Users>();
		Vector <RegistaredStudents>r=new Vector<RegistaredStudents>();
		Users u=new Users();
		instructor=u.getInstructorVector();
		/*
		 * for each instructor check each course if he is enrolled in it or not
		 * then return an array consisting of all the courses he is enrolled in
		 */
		try{
			File file = new File("InstructorCourses.txt");
			Scanner read = new Scanner(file);
			//read each line
			while (read.hasNextLine()) {
				
				String splited[] =  read.nextLine().split(" ");
				int len = splited.length;
				
				String crn[]=new String[len-1];
				for(int i = 1; i<len;i++) {
					crn[i-1]=splited[i];
				}
				int slen= instructor.size();
				for(int i =0;i <slen;i++) {
					if(instructor.elementAt(i).getID().equals(splited[0])) {
					r.add(new RegistaredStudents(instructor.elementAt(i).getFirstName(),
							instructor.elementAt(i).getLastName(),
							instructor.elementAt(i).getID(),
							instructor.elementAt(i).getPassword(),
							instructor.elementAt(i).getOccupation(),
							instructor.elementAt(i).getDepartment(),
							crn));
					}
				}
			}
			if(file.length()<100) {
				addSenseiCoursesFile();
			}
			read.close();
		} catch (FileNotFoundException e) {
			addSenseiCoursesFile();
			return getSenseiCourses();	
		}
		return r;
	}
	public void addSenseiCoursesFile() {
		//student id, registared courses
				String o ="400041236 1234 4321 1346\n"
						+ "589558565 2486 7510 2020\n"
						+ "100044444 2859 4567\n"
						+ "951471352 4961 8486\n"
						+ "456980460 8426 8425";
				try { 	
					PrintWriter S = new PrintWriter(new File("InstructorCourses.txt"));
					S.println(o);
					S.close();
				}catch(IOException e) {
					JOptionPane.showMessageDialog(null, "Error starting the program", "Error", 0, null);
					e.printStackTrace();
				}
			}
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
	public void setFirstNameInstructor(String id) {
		this.id = id;
	}
	public void setNumberOfStudents(String students) {
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
						+" "+save.elementAt(i).getID()
						+" "+save.elementAt(i).getNumberOfStudents()
						+" "+save.elementAt(i).getMaxStudents()
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
									read.next(), read.next(),read.next(),
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
//name, crn, credit hours, section, time, building, instructor id, registered student, capacity, start, end
		String o ="Object-Orientied-Programming 1234 4 B1 12:50 G10001 400041236 8 30 25/8 12/12\n"
				+ "Nanochemistry 8426 4 4 12:50 G10001 456980460 11 30 25/8 12/12\n"
				+ "Chemistry-I 8425 4 4 12:50 G10001 456980460 11 30 25/8 12/12\n"
				+ "Electric-Circuit-I 1346 4 6 12:50 G10001 400041236 8 30 25/8 12/12\n"
				+ "Physics-I 2486 4 7 12:50 G10001 589558565 30 30 25/8 12/12\n"
				+ "Physics-II 7510 4 8 12:50 G10001 589558565 19 30 25/8 12/12\n"
				+ "Electromagnetic 2859 4 9 12:50 G10001 100044444 15 30 25/8 12/12\n"
				+ "Qauntum-Physics 4567 4 10 12:50 G10002 100044444 20 30 25/8 12/12\n"
				+ "Digital-Logic-Design 4321 4 12 12:50 G10001 400041236 8 30 25/8 12/12\n"
				+ "Statics 2020 4 2 12:50 G10001 589558565 19 30 25/8 12/12\n"
				+ "Thermodynamics 4961 4 1 12:50 G10002 951471352 20 30 25/8 12/12\n"
				+"Biochemistry 8486 4 6 12:50 G10001 456980460 30 30 25/8 12/12\n";
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
		o += " ID: "+this.getID();
		o += " NumberofStudents: "+this.getNumberOfStudents();
		o += " MaxStudents: "+this.getMaxStudents()+" Start Date: "+this.getStartdate();
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
			classes[i][6]=s.elementAt(i).getID();
			classes[i][7]=s.elementAt(i).getNumberOfStudents();
			classes[i][8]=s.elementAt(i).getMaxStudents();
			classes[i][9]=s.elementAt(i).getStartdate();
			classes[i][10]=s.elementAt(i).getEnddate();
		}
		return classes;
	}
}
class RegistaredStudents extends Users{
	
	private String[] courses;
	public RegistaredStudents(String firstName, String lastName, String id, String password, String occupation, String department,String[] courses) {
		super(firstName,lastName,id,password,occupation,department);
		this.courses=courses;
	}
	public RegistaredStudents() {
		courses=null;
	}
	public String getCourses() {
		String o = "";
		int len = this.courses.length;
		for(int i = 0; i<len;i++) {
			o+=this.courses[i]+(i<len-1?", ":"");
		}
		return o;
	}
	public String gc() {
		String o = "";
		int len = this.courses.length;
		for(int i = 0; i<len;i++) {
			o+=this.courses[i]+" ";
		}
		return o;
	}
	/*
	 * to find the number of the students enrolled in the passed crn
	 */
	public int getNumberOfStudentEnrolled(String crn) {
		
		Vector<RegistaredStudents>c=new Vector<RegistaredStudents>();
		Courses course = new Courses();
		c=course.getStudentCourses();
		int count = c.size();
		int enrolled=0;
		for(int i = 0; i <count;i++) {
			String s[]=c.elementAt(i).gc().split(" ");
			for(int j=0;j<s.length;j++) {
				if(s[j].equals(crn))
					enrolled++;
			}
		}
		return enrolled;
	}
}