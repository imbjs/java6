package kr.ac.itschool.dao;

import java.util.ArrayList;

import kr.ac.itschool.student.entities.ProfessorBean;


public  interface  ProfessorDao {
	public  boolean  insertRow(ProfessorBean data);
	public  ProfessorBean selectRow(String code);
	public  boolean findCode(String code);
	public  ArrayList<ProfessorBean> selectAll();
	public  ArrayList<ProfessorBean> selectFind(String search);
	public  String  updateRow(ProfessorBean data);
	public  boolean  deleteRow(String code);
}
