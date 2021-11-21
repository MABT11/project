import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Vector;

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
	
	//write users to file 
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
	//save new users to User object
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
