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
	
	public Users(String firstName, String lastName, String id, String password, String occupation) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.id = id;
		this.password = password;
		this.occupation = occupation;
	}
	public Users(){
		this.firstName=null;
		this.lastName = null;
		this.id = null;
		this.password = null;
		this.occupation = null;
	}
	
	//save users to file 
	public void saveUsers(Vector<Users> save) {
		try {
			PrintWriter S = new PrintWriter(new FileWriter("users.txt"));
			int len = save.size();
			for(int i = 0;i<len;i++) {
				S.println(save.elementAt(i).getFirstName()+" "+save.elementAt(i).getLastName()+" "+save.elementAt(i).getID()+" "+save.elementAt(i).getPassword()+" "+save.elementAt(i).getOccupation());
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
				
				load.add(new Users(read.next(), read.next(), read.next(), read.next(), read.next()));
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
		String o ="Ahmed lname 1234567 pAsswor_d ADMIN\n"
				+ "Mubarak Bin 100053896 passwOrd7! STUDENT\n"
				+ "sayed nassar 40004 password_1 INSTRUCTOR\n"
				+ "khalid mohsen 1234 Password!9 STUDENT\n"
				+ "mohmmed ali 11111 1qz!aa52 STUDENT\n"
				+ "muasssss qwerty 123456789 dkjgndiognA!8 STUDENT\n"
				+ "mohmed ahmed 789456123 Password!1 STUDENT\n"
				+ "alia almehri 12345432 password_1 STUDENT\n"
				+ "tom tony 12342123 password_1 STUDENT\n"
				+ "salem labeb 58955856 password_1 INSTRUCTOR\n"
				+ "awadh saif 123432 Lad0k$ STUDENT\n"
				+ "adel khalifa 12222 1_drowssap STUDENT\n"
				+ "yazan almousabi 13333 roopOd_1 STUDENT\n"
				+ "naser bdr 44444 password_1 INSTRUCTOR\n"
				+ "mosab hamza 66666 Hello_its3 STUDENT\n"
				+ "hamdan alhmadi 77711 me_lo923 STUDENT\n"
				+ "mamon bek 471352 password_1 INSTRUCTOR\n"
				+ "adbullah omar 88888 qwerty_1 STUDENT\n"
				+ "jamal tariq 99999 asdfgh0$ STUDENT\n"
				+ "elyas zikkos 456980 password_1 INSTRUCTOR\n"
				+ "abdulrhman abbas 101010 Lo0ol_99 STUDENT";
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
		
		Vector<Users> instructors = new Vector<Users>();
		instructors= getUsers();
		int len = instructors.size();
		for(int i = 0; i<len;i++)
			if(instructors.elementAt(i).getOccupation().equals(Occupation.INSTRUCTOR.name()))
				continue;
			else
				instructors.remove(i);
		return instructors;
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
		students= getUsers();
		int len = students.size();
		for(int i = 0; i<len;i++)
			if(students.elementAt(i).getOccupation().equals(Occupation.STUDENT.name()))
				continue;
			else
				students.remove(i);
		return students;
	}
	/*
	 * return a 2D-Object with the info of the student for now its being used in the jtable only
	 */
	@Override
	public Object[][] getStudentInfo() {
		Vector<Users> s =new Vector<Users>();
		s = getUsers();
		int len =s.size();
		int count = getStudents();
		int prev =0;
		Object[][] students =new Object[count][3];
		for(int i = 0; i < count;i++) {
			for(int j = prev; j < len; j++) {
				if(s.elementAt(j).getOccupation().trim().equals(Occupation.STUDENT.name())) {
					students[i][0]=s.elementAt(j).getID();
					students[i][1]=s.elementAt(j).getFirstName()+" "+s.elementAt(j).getLastName();
					students[i][2]=s.elementAt(j).getPassword();
					prev=++j;
					break;
				}
			}
		}
		return students;
	}
	/*
	 * return a 2D object with information on instructors its being used for the instructors in jtable
	 */
	@Override
	public Object[][] getInstructorInfo() {
		Vector<Users> s =new Vector<Users>();
		s = getUsers();
		int len = s.size();
		int count = getInstructors();
		int prev =0;
		Object[][] instructors =new Object[count][3];
		for(int i = 0; i < count;i++) {
			for(int j = prev; j < len; j++) {
				if(s.elementAt(j).getOccupation().trim().equals(Occupation.INSTRUCTOR.name())) {
					instructors[i][0]=s.elementAt(j).getID();
					instructors[i][1]=s.elementAt(j).getFirstName()+" "+s.elementAt(j).getLastName();
					instructors[i][2]=s.elementAt(j).getPassword();
					prev=++j;
					break;
				}
			}
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
}