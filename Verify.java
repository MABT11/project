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
          Pattern pattern = Pattern.compile( "\\d{5,10}" );//100053896
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
			.{8,}              anything, at least eight places though
			$                  end-of-string
		   */
          Pattern pattern = Pattern.compile( "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+_!=])(?=\\S+$).{8,}$" );
          Matcher matcher = pattern.matcher(pass);
          if(matcher.matches())
              return true;
          else
        	  return false;
	  }
}
