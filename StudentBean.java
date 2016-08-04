package kr.ac.itschool.student.entities;

public class StudentBean {
	private String code;
	private String name;
	private String idcard;
	private String postno;
	private String addr1;
	private String addr2;
	private String professor;
	private String dept;
	private String age;
	private String deptchangecode;
	private String professorchangecode;
	
	public String getProfessorchangecode() {
		return professorchangecode;
	}
	public void setProfessorchangecode(String string) {
		this.professorchangecode = string;
	}
	public String getDeptchangecode() {
		return deptchangecode;
	}
	public void setDeptchangecode(String deptchangecode) {
		this.deptchangecode = deptchangecode;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public String getPostno() {
		return postno;
	}
	public void setPostno(String postno) {
		this.postno = postno;
	}
	public String getAddr1() {
		return addr1;
	}
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}
	public String getAddr2() {
		return addr2;
	}
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}
	public String getProfessor() {
		return professor;
	}
	public void setProfessor(String professor) {
		this.professor = professor;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	}