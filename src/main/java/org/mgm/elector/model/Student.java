package org.mgm.elector.model;


import java.io.Serializable;
import java.sql.Date;

import javax.swing.JOptionPane;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;


@Entity
public class Student implements Serializable,Comparable<Student> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -287914943416920240L;
	@Id
	 private String studentId;
	 @Column(nullable = false)
	 private String studentName;
	 @ManyToOne
	 private Department department;
	 @Embedded
	 private Address address;
	 @Column(length = 12)
	 private String gender;
	 @Column(nullable = false)
	 private Date dateOfBrith;
	 @Column(nullable = false, unique = true)
	 private String email;
	 @Column(nullable = false,length = 10,unique = true)
	 private String phoneNumber;
	 private int academicYear;
	 @Lob
	 @Column(columnDefinition = "BLOB")
	 private byte[] photo;
	 private boolean voted;
	 
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		if (studentId==null || studentId.length()!=10)
			throw new IllegalArgumentException("student id is empty and should have 10 digits");
		this.studentId = studentId;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		if (studentName==null)
			throw new IllegalArgumentException(" Student name is empty");
		this.studentName = studentName;
	}
	
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public Date getDateOfBrith() {
		return dateOfBrith;
	}
	public void setDateOfBrith(Date dateOfBrith) {
		if (dateOfBrith==null)
			throw new IllegalArgumentException("Enter date of birth ");
		this.dateOfBrith = dateOfBrith;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		if (email==null || !email.endsWith("@gmail.com"))
			throw new IllegalArgumentException(" Enter email  and must ends with @gmail.com");
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		if (phoneNumber==null)
			throw new IllegalArgumentException("Enter phone number");
		if (phoneNumber.length()!=10) {
			throw new IllegalArgumentException("invalid phone number");
		}
		try {
			Long.parseLong(phoneNumber);
			this.phoneNumber=phoneNumber;
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "phone number should be numbers!","INVALID INPUT ERROR",
					JOptionPane.WARNING_MESSAGE);
		}
	}
	public int getAcademicYear() {
		return academicYear;
	}
	public void setAcademicYear(int academicYear) {
		if (academicYear<=0 && academicYear>3)
			throw new IllegalArgumentException("academic year must be between 1 and 3 ");
		this.academicYear = academicYear;
	}
	public byte[] getPhoto() {
		return photo;
	}
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		if (gender==null)
			throw new IllegalArgumentException("select gender");
		this.gender = gender;
	}
	@Override
	public String toString() {
		return studentName+"("+studentId+")";
	}
	@Override
	public int compareTo(Student o) {
		// TODO Auto-generated method stub
		return studentName.compareToIgnoreCase(o.studentName);
	}
	public boolean isVoted() {
		return voted;
	}
	public void setVoted(boolean voted) {
		this.voted = voted;
	}

	
}
