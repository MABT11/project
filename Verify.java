/*
 * Regex to check the sanity of the input name, pass, id
 */
import java.util.regex.*;
public class Verify {
	
	  public static boolean NameVerifier(String name) {
          Pattern pattern = Pattern.compile( "[A-Z][a-z]*",Pattern.CASE_INSENSITIVE );
          Matcher matcher = pattern.matcher(name);
          if(matcher.matches())
        	  return true;
          else
        	  return false;
	  }
	  public static boolean IDVerifier(String id) {
          Pattern pattern = Pattern.compile( "\\d{9,9}" );//100053896
          Matcher matcher = pattern.matcher(id);
          if(matcher.matches())
        	  return true;
          else
        	  return false;
	  }
	  public static boolean crnVerifier(String id) {
          Pattern pattern = Pattern.compile( "\\d{4,4}" );//1234
          Matcher matcher = pattern.matcher(id);
          if(matcher.matches())
        	  return true;
          else
        	  return false;
	  }
	  public static boolean PassVerifier(String pass) {
		  /*
		   * ^                 start-of-string
			(?=.*[0-9])        a digit must occur at least once
			(?=.*[a-z])        a lower case letter must occur at least once
			(?=.*[A-Z])        an upper case letter must occur at least once
			(?=.*[@#$%^&+=])   a special character must occur at least once
			(?=\S+$)           no whitespace allowed in the entire string
			.{6,}              anything, at least six characters though
			$                  end-of-string
		   */
          Pattern pattern = Pattern.compile( "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+_!=])(?=\\S+$).{6,}$" );
          Matcher matcher = pattern.matcher(pass);
          if(matcher.matches())
              return true;
          else
        	  return false;
	  }
}
