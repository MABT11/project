import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JOptionPane;

public class Users {
	
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
				System.out.println(save.elementAt(i).getFirstName()+" "+save.elementAt(i).getLastName()+" "+save.elementAt(i).getID()+" "+save.elementAt(i).getPassword()+" "+save.elementAt(i).getOccupation());
			}
			S.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//save new users to vector of type user
	public void saveUsersObj(Vector<Users> save) {
		
		Vector<Users> load = new Vector<Users>();
		int len = save.size();
		for(int i = 0; i < len;i++) {
			load.add(new Users(
					save.elementAt(i).getFirstName(),
					save.elementAt(i).getLastName(),
					save.elementAt(i).getID(),
					save.elementAt(i).getPassword(),
					save.elementAt(i).getOccupation()
					));
		}
		saveUsers(save);
	}
	//read the users from the file to and store them in the vector of type users
	public Vector<Users> getUsers() {
		Vector<Users> load = new Vector<Users>();
		try {
			Scanner read = new Scanner(new File("users.txt"));
			while (read.hasNext()) {
				
				load.add(new Users(read.next(), read.next(), read.next(), read.next(), read.next()));
			}
			read.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return load;
	}
	public static void start() {
		String o ="Ahmed lname 1234567 pAsswor_d ADMIN\n"
				+ "Mubarak Bin 100053896 passwOrd7! STUDENT\n"
				+ "a a 1234 Password!9 STUDENT\n"
				+ "zzzzzzzzzz aaaaaaaaaaa 111111111111111 1qaz!aaaaa52 STUDENT\n"
				+ "muasssss qwerty 123456789 dkjgndiognA!8 STUDENT\n"
				+ "mohmed ahmed 789456123 Password!1 STUDENT\n"
				+ "ahmed almehri 12345432 password_1 STUDENT\n"
				+ "ahmeddd almehrrri 12342123 password_1 STUDENT\n"
				+ "fjkdfjkdsjf jdkbgjdkkdjfnje 58955856 password_1 INSTRUCTOR\n"
				+ "asdfghj xcvbn 123432 Lo0ol_99 STUDENT\n"
				+ "\n";
		try {
			PrintWriter S = new PrintWriter(new File("users.txt"));
			S.println(o);
			S.close();
		}catch(IOException e) {
			JOptionPane.showMessageDialog(null, "Error starting the program", "Error", 0, null);
			e.printStackTrace();

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
