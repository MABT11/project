
public class Instructor {
	private String firstName;
	private String lastName;
	private String []courseLoad;
	public Instructor(String firstName, String lastName,String []courseLoad) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.courseLoad = courseLoad;
	}
	public String getFirstName() {
		return this.firstName;
	}
	public String getLastName() {
		return this.lastName;
	}
	public String[] getCourseLoad(){
		return this.courseLoad;
	}

}