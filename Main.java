/*
 * Main class Driver class
 */
import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		
		LoginInfo logininfo = new LoginInfo();
		new UserMode(logininfo.getLoginInfo());
		
	}
}
