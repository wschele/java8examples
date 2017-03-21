package de.schele.java8examples.functional.basics.predicates;

public class Employee {

	public Employee(Integer id, Integer age, String gender, String fName, String lName) {
		this.id = id;
		this.age = age;
		this.gender = gender;
		this.firstName = fName;
		this.lastName = lName;
	}

	private Integer id;
	private Integer age;

	private String gender;
	private String firstName;
	private String lastName;

	// Please generate Getter and Setters
	public Integer getId() {
		return id;
	}
	
	public Integer getAge() {
		return age;
	}

	public String getGender() {
		return gender;
	}	

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}	

	@Override
	public String toString() {
		// To change body of generated methods, choose Tools | Templates.
		return this.getId().toString() + " - " + this.getAge().toString(); 
	}
}