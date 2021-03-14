package com.persistent.connectors.model;

public class Student {

	private Long studentId;
	private String studentName;
	private int age;

	public Student(Long studentId, String studentName, int age) {
		super();
		this.studentId = studentId;
		this.studentName = studentName;
		this.age = age;
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", studentName=" + studentName + ", age=" + age + "]";
	}

}
