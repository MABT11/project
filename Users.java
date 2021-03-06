import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JOptionPane;

public class Users implements Info{
	
	private String firstName;
	private String lastName;
	private String id;
	private String password;
	private String occupation;
	private String department;
	
	public Users(String firstName, String lastName, String id, String password, String occupation,String department) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.id = id;
		this.password = password;
		this.occupation = occupation;
		this.department=department;
	}
	public Users(){
		this.firstName=null;
		this.lastName = null;
		this.id = null;
		this.password = null;
		this.occupation = null;
		this.department=null;
	}
	//save students to file after they have been modified
	public void saveModifiedStudents(Vector<Users> sv) {
		try {
			PrintWriter S = new PrintWriter(new FileWriter("users.txt"));
			
			Vector<Users> instructor =new Vector<Users>();
			Users u=new Users();
			instructor=u.getInstructorVector();
			Vector<Users> iv=getInstructorVector();
			Vector<Users> av=getAdminVector();
			Vector<Users> users=getUsers();
			int numberofstudent=sv.size();
			int numberofinstructor=iv.size();
			int numberofadmin=av.size();
			int sum =numberofstudent+numberofinstructor+numberofadmin;
			int a=0,b=0,c=0;
			for(int i =0;i<sum;i++) {
				if(users.elementAt(i).occupation.equals("STUDENT")) {
					S.println(sv.elementAt(a).getFirstName()+" "+sv.elementAt(a).getLastName()+" "+sv.elementAt(a).getID()+" "+sv.elementAt(a).getPassword()+" "+sv.elementAt(a).getOccupation()+" "+sv.elementAt(a).getDepartment());
					a++;
				}
				else if(users.elementAt(i).occupation.equals("INSTRUCTOR")) {
					S.println(iv.elementAt(b).getFirstName()+" "+iv.elementAt(b).getLastName()+" "+iv.elementAt(b).getID()+" "+iv.elementAt(b).getPassword()+" "+iv.elementAt(b).getOccupation()+" "+iv.elementAt(b).getDepartment());
					b++;
				}
				else if(users.elementAt(i).occupation.equals("ADMIN")) {
					S.println(av.elementAt(c).getFirstName()+" "+av.elementAt(c).getLastName()+" "+av.elementAt(c).getID()+" "+av.elementAt(c).getPassword()+" "+av.elementAt(c).getOccupation()+" "+av.elementAt(c).getDepartment());
					c++;
				}
			}
			S.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//save instructors to file after they have been modified
	public void saveModifiedInstructors(Vector<Users> iv) {
		try {
			PrintWriter S = new PrintWriter(new FileWriter("users.txt"));
			
			Vector<Users> instructor =new Vector<Users>();
			Users u=new Users();
			instructor=u.getInstructorVector();
			Vector<Users> sv=getStudentVector();
			Vector<Users> av=getAdminVector();
			Vector<Users> users=getUsers();
			int numberofstudent=sv.size();
			int numberofinstructor=iv.size();
			int numberofadmin=av.size();
			int sum =numberofstudent+numberofinstructor+numberofadmin;
			int a=0,b=0,c=0;
			for(int i =0;i<sum;i++) {
				if(users.elementAt(i).occupation.equals("STUDENT")) {
					S.println(sv.elementAt(a).getFirstName()+" "+sv.elementAt(a).getLastName()+" "+sv.elementAt(a).getID()+" "+sv.elementAt(a).getPassword()+" "+sv.elementAt(a).getOccupation()+" "+sv.elementAt(a).getDepartment());
					a++;
				}
				else if(users.elementAt(i).occupation.equals("INSTRUCTOR")) {
					S.println(iv.elementAt(b).getFirstName()+" "+iv.elementAt(b).getLastName()+" "+iv.elementAt(b).getID()+" "+iv.elementAt(b).getPassword()+" "+iv.elementAt(b).getOccupation()+" "+iv.elementAt(b).getDepartment());
					b++;
				}
				else if(users.elementAt(i).occupation.equals("ADMIN")) {
					S.println(av.elementAt(c).getFirstName()+" "+av.elementAt(c).getLastName()+" "+av.elementAt(c).getID()+" "+av.elementAt(c).getPassword()+" "+av.elementAt(c).getOccupation()+" "+av.elementAt(c).getDepartment());
					c++;
				}
			}
			S.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void saveUsers(Vector<Users> save) {
		try {
			PrintWriter S = new PrintWriter(new FileWriter("users.txt"));
			int len = save.size();
			for(int i = 0;i<len;i++) {
				S.println(save.elementAt(i).getFirstName()+" "+save.elementAt(i).getLastName()+" "+save.elementAt(i).getID()+" "+save.elementAt(i).getPassword()+" "+save.elementAt(i).getOccupation()+" "+save.elementAt(i).getDepartment());
			}
			S.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//read the users from the file to and store them in the vector of type users
	public Vector<Users> getUsers() {
		Vector<Users> load = new Vector<Users>();
		/*
		 * read from the file
		 */
		try {
			File users = new File("users.txt");
			Scanner read = new Scanner(users);
			while (read.hasNext()) {
				load.add(new Users(read.next(), read.next(), read.next(), read.next(), read.next(),read.next()));
			}
			/*
			 * if the file exists but its empty add users to the file
			 */
			if(users.length()<100) {
				addUsersFile();
			}
			read.close();
			/*
			 * if the file was not found create a file and add users to the file
			 */
		} catch (FileNotFoundException e) {
			addUsersFile();
			// a little bit of recursion to save the day
			return getUsers();
		}
		return load;
	}
	/*
	 * If the file is empty or if the file DNE then write users to the  file
	 */
	public void addUsersFile() {
		String o ="Ahmed lname 1234567 pAsswor_d ADMIN REGISTRAR\n"
				+ "Mubarak Bin 100053896 passwOrd7! STUDENT ECCE\n"
				+ "sayed nassar 400041236 password_1 INSTRUCTOR ECCE\n"
				+ "khalid mohsen 100001234 Password!9 STUDENT "+Departments.CHEMISTRY.name()+"\n"
				+ "mohmmed ali 100011111 1qz!aa52 STUDENT "+Departments.ECCE.name()+"\n"
				+ "muasssss qwerty 123456789 dkjgndiognA!8 STUDENT "+Departments.ECCE.name()+"\n"
				+ "mohmed ahmed 789456123 Password!1 STUDENT "+Departments.CHEMISTRY.name()+"\n"
				+ "alia almehri 123454321 password_1 STUDENT "+Departments.PHYSICS.name()+"\n"
				+ "tom tony 741965256 password_1 STUDENT "+Departments.PHYSICS.name()+"\n"
				+ "salem labeb 589558565 password_1 INSTRUCTOR "+Departments.PHYSICS.name()+"\n"
				+ "awadh saif 123421231 Lad0k$ STUDENT "+Departments.ECCE.name()+"\n"
				+ "adel khalifa 123432256 1_drowssap STUDENT "+Departments.PHYSICS.name()+"\n"
				+ "yazan almousabi 100012222 roopOd_1 STUDENT "+Departments.ECCE.name()+"\n"
				+ "naser bdr 100044444 password_1 INSTRUCTOR "+Departments.PHYSICS.name()+"\n"
				+ "mosab hamza 951313333 Hello_its3 STUDENT "+Departments.PHYSICS.name()+"\n"
				+ "hamdan alhmadi 851666666 me_lo923 STUDENT "+Departments.ECCE.name()+"\n"
				+ "mamon bek 951471352 password_1 INSTRUCTOR "+Departments.CHEMISTRY.name()+"\n"
				+ "adbullah omar 777114586 qwerty_1 STUDENT "+Departments.ECCE.name()+"\n"
				+ "jamal tariq 888888895 asdfgh0$ STUDENT "+Departments.CHEMISTRY.name()+"\n"
				+ "elyas zikkos 456980460 password_1 INSTRUCTOR "+Departments.CHEMISTRY.name()+"\n"
				+ "abdulrhman abbas 999999999 Lo0ol_99 STUDENT "+Departments.CHEMISTRY.name()+"\n"
				+ "samer amer 101010101 ppAA_88 STUDENT "+Departments.ECCE.name();
		try {
			PrintWriter S = new PrintWriter(new File("users.txt"));
			S.println(o);
			S.close();
		} catch(IOException io) {
			JOptionPane.showMessageDialog(null, "Error starting the program", "Error", 0, null);
			io.printStackTrace();
		}
	}
	@Override
	public String toString() {
		String o="Name: "+this.getFirstName()+" "+this.getLastName();
		o += " User ID: "+this.getID()+" Password: "+this.getPassword();
		o += " Occupation: "+this.getOccupation();
		o+=" Department: "+this.getDepartment();
		return o;
	}
	//getters methods
	public String getFirstName() {
		return this.firstName;
	}
	public String getLastName() {
		return this.lastName;
	}
	public String getID() {
		return this.id;
	}
	public String getPassword() {
		return this.password;
	}
	public String getOccupation() {
		return this.occupation;
	}
	public String getDepartment() {
		return department;
	}
	/*
	 * return the number of users
	 */
	public int getFaculty() {
		Vector<Users> users = new Vector<Users>();
		users= getUsers();
		int count = 0,len = users.size();
		for(int i = 0; i<len;i++)
			if(users.elementAt(i).getOccupation().equals(Occupation.ADMIN.name())||users.elementAt(i).getOccupation().equals(Occupation.INSTRUCTOR.name()))
				count++;
		return count;
	}
	/*
	 * returns the number of instructors
	 */
	public int getInstructors() {
		Vector<Users> users = new Vector<Users>();
		users= getUsers();
		int count = 0,len = users.size();
		for(int i = 0; i<len;i++)
			if(users.elementAt(i).getOccupation().equals(Occupation.INSTRUCTOR.name()))
				count++;
		return count;
	}
	/*
	 * returns an instructor vector of type users
	 */
	public Vector<Users> getInstructorVector() {
		
		Vector<Users> students = new Vector<Users>();
		Vector<Users> s=new Vector<Users>();
		students= getUsers();
		int len = students.size();
		
		for(int j = 0; j < len; j++) {
				if(students.elementAt(j).getOccupation().trim().equals(Occupation.INSTRUCTOR.name())) {
					s.add(students.elementAt(j));
				}
		}
		return s;
	}
	public Vector<Users> getAdminVector() {
		
		Vector<Users> sensei = new Vector<Users>();
		Vector<Users> s=new Vector<Users>();
		sensei= getUsers();
		int len = sensei.size();
		
		for(int j = 0; j < len; j++) {
				if(sensei.elementAt(j).getOccupation().trim().equals(Occupation.ADMIN.name())) {
					s.add(sensei.elementAt(j));
				}
		}
		return s;
	}
	/*
	 * return number of students
	 */
	public int getStudents() {
		Vector<Users> users = new Vector<Users>();
		users= getUsers();
		int count = 0,len = users.size();
		for(int i = 0; i<len;i++)
			if(users.elementAt(i).getOccupation().equals(Occupation.STUDENT.name()))
				count++;
		return count;
	}
	/*
	 * return a student vector of type users
	 */
	public Vector<Users> getStudentVector() {
		
		Vector<Users> students = new Vector<Users>();
		Vector<Users> s=new Vector<Users>();
		students= getUsers();
		int len = students.size();
		
		for(int j = 0; j < len; j++) {
				if(students.elementAt(j).getOccupation().trim().equals(Occupation.STUDENT.name())) {
					s.add(students.elementAt(j));
				}
		}
		return s;
	}
	/*
	 * return a 2D-Object with the info of the student for now its being used in the jtable only
	 */
	@Override
	public Object[][] getStudentInfo() {

		Vector<RegistaredStudents>c=new Vector<RegistaredStudents>();
		Courses course = new Courses();
		c=course.getStudentCourses();
		int count = getStudents();
		
		Object[][] students =new Object[count][4];
		System.out.println("Count: "+count);
		for(int i = 0; i < count;i++) {
			System.out.println(i+" "+c.elementAt(i).getID());
			students[i][0]=c.elementAt(i).getID();
			students[i][1]=c.elementAt(i).getFirstName()+" "+c.elementAt(i).getLastName();
			students[i][2]=c.elementAt(i).getDepartment();
			students[i][3]=c.elementAt(i).getCourses();
		}
		return students;
	}
	/*
	 * return a 2D object with information on instructors its being used for the instructors in jtable
	 */
	@Override
	public Object[][] getInstructorInfo() {

		Vector<RegistaredStudents>c=new Vector<RegistaredStudents>();
		Courses course = new Courses();
		c=course.getSenseiCourses();
		
		int count = getInstructors();

		Object[][] instructors =new Object[count][4];
		for(int i = 0; i < count;i++) {
				instructors[i][0]=c.elementAt(i).getID();
				instructors[i][1]=c.elementAt(i).getFirstName()+" "+c.elementAt(i).getLastName();
				instructors[i][2]=c.elementAt(i).getDepartment();
				instructors[i][3]=c.elementAt(i).getCourses();
		}
		return instructors;
	}
	//setters methods
	public void setFirstName(String fname) {
		this.firstName=fname;
	}
	public void setLastName(String lname) {
		this.lastName=lname;
	}
	public void setID(String id) {
		this.id=id;
	}
	public void setPassword(String password) {
		this.password=password;
	}
	public void setOccupation(String oc) {
		this.occupation=oc;
	}
	public void setDepartment(String department) {
		this.department = department;
	}	
}