package entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="teacher_Details")
public class TeacherDetails extends BaseEntity{
	
	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "age")
	private int age;

	@Column(name = "hobby")
	private String hobby;

	@OneToOne(mappedBy = "teacherDetails", cascade = CascadeType.ALL)
	private Teacher teacher;
	

	public TeacherDetails() {
	}


	public TeacherDetails(String firstName, String lastName, int age, String hobby) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.hobby = hobby;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	public String getHobby() {
		return hobby;
	}


	public void setHobby(String hobby) {
		this.hobby = hobby;
	}


	public Teacher getTeacher() {
		return teacher;
	}


	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
}
