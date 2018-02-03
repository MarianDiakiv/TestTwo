package entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="course")
public class Course extends BaseEntity{
	
	@Column(name="title")
	private String title;

	@Column(name="description")
	private String description;

	@Column(name = "price", columnDefinition = "DECIMAL(5,2)")
	private BigDecimal price;

	@ManyToOne(cascade = {
					CascadeType.DETACH, 
					CascadeType.MERGE, 
					CascadeType.PERSIST, 
					CascadeType.REFRESH},
			fetch=FetchType.LAZY)
	@JoinColumn(name = "teacher_id")
	private Teacher teacher;

	@ManyToMany//(fetch =FetchType.EAGER)//завантажеє всі дані а lazy лише з одної таблиці
	@JoinTable(name = "course_student", 
	joinColumns = @JoinColumn(name = "course_id"), 
	inverseJoinColumns = @JoinColumn(name = "student_id"))
	List<Student> students  = new ArrayList<>();

	public Course(String title, String description, BigDecimal price) {
		
		this.title = title;
		this.description = description;
		this.price = price;
		
	}
	public Course() {
		
	}
	
	
	
	
	
	
	
	
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	
	
	public List<Student> getStudents() {
		return students;
	}
	public void setStudents(List<Student> students) {
		this.students = students;
	}
	@Override
	public String toString() {
		return "Course [title=" + title + ", description=" + description + ", price=" + price + ", getId()=" + getId()
				+ "]";
	}
	
	
	
	
	

	
	
}
