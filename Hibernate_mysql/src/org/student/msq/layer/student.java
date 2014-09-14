package org.student.msq.layer;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "StudentTable")
public class student {

	
	private int ID;	
	private String student;
	
	@Id 
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	} 
	public String getStudentName() {
		return student;
	}
	public void setStudentName(String studentName) {
		student = studentName;
	}

}
