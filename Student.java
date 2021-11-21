
public class Student {
	private String firstName;
	private String lastName;
	private String []courses;
	
	public Student(String firstName, String lastName, String []courses) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.courses = courses;
	}
	public String getFirstName() {
		return this.firstName;
	}
	public String getLastName() {
		return this.lastName;
	}
	public String[] getCourses() {
		return this.courses;
	}

}
