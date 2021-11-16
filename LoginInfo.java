/*
 * We store the information about the users here in a hash function 
 */
import java.io.*;
import java.util.HashMap;

public class LoginInfo {
	/*
	 * A hashmap is the best way to store key value pairs of information
	 * the key is the id and the value is the password
	 */
	
	public HashMap<String,String> logininfo = new HashMap<String,String>();
	public PrintWriter wr;
	public LoginInfo(String user, String password,String o) throws IOException{
		//ADMIN accounts
		logininfo.put("mubarak", "1234");
		logininfo.put("ahmed", "password");
		logininfo.put("z", "z");
		/*
		 * every time a user gets created we need to hash it and store it inside our hashmap
		 */
		logininfo.put(user, password);
		//still we are overwriting the file every time we create a new user 
		wr = new PrintWriter(new FileWriter("users.txt", true));
		wr.println(o);
		wr.close();
	}
	/*
	 * several constructors for testing purposes
	 */
	public LoginInfo(String user, String password){
		//ADMIN accounts
		logininfo.put("mubarak", "1234");
		logininfo.put("ahmed", "password");
		//NEW USERS CREATED
		logininfo.put(user, password);
	}
	public LoginInfo(){
		//ADMIN accounts
		logininfo.put("mubarak", "1234");
		logininfo.put("ahmed", "password");
	}
	public HashMap<String,String> getLoginInfo() {
		return logininfo;
	}
}
