package sgcib.eliot.ewos.datalake.HBernate_orm.access;

import sgcib.eliot.ewos.datalake.HBernate_orm.annotations.Column;
import sgcib.eliot.ewos.datalake.HBernate_orm.annotations.Table;

@Table(name = "PERSON")
public class Person {

	@Column(name = "ID")
	private String id;
	@Column
	private String firstname;
	@Column(name = "LASTNAME")
	private String lastname;
	@Column(name = "AGE")
	private String age;

	public Person() {
		super();
	}

	public Person(String id, String firstName, String lastName, String age) {
		this.id = id;
		this.firstname = firstName;
		this.lastname = lastName;
		this.age = age;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

}
