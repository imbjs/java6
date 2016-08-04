package kr.ac.itschool.student.daoservice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import kr.ac.itschool.dbpool.DBConnectionManager;
import kr.ac.itschool.student.entities.StudentBean;

public   class StudentDaoService  {
	DBConnectionManager db= DBConnectionManager.getInstance();
	Connection cn=null;
	PreparedStatement ps=null;
	ResultSet rs=null;

	public boolean insertRow(StudentBean data) {
		boolean savechk=false;
	
		StringBuilder sb= new StringBuilder();
		sb.append("INSERT INTO student(code,name,idcard,postno,addr1,");
		sb.append("addr2,professor,dept");
		sb.append(")values(");
		sb.append(" '"+data.getCode()+"','"+data.getName()+"','"+data.getIdcard()+"',");
		sb.append(" '"+data.getPostno()+"','"+data.getAddr1()+"','"+data.getAddr2()+"',");
		sb.append(" '"+data.getProfessorchangecode()+"','"+data.getDeptchangecode()+"')");
		String sql=sb.toString();
		try {
			cn= db.getConnection();
			ps= (PreparedStatement) cn.prepareStatement(sql);
			ps.execute();
			savechk=true;
			cn.close();
			ps.close();
			rs.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return savechk;
	}

	public boolean findCode(String code) {
		String sql="SELECT code from student where code='"+code+"' ";
		boolean findcode=false;	
		try {
			cn= db.getConnection();
			ps= (PreparedStatement) cn.prepareStatement(sql);
			rs=ps.executeQuery();
			if(rs.next()){
				findcode=true;
			}
			cn.close();
			ps.close();
			rs.close();
		}catch (Exception e) {
			System.out.println("db error:"+e.getMessage());
		}
		return findcode;
	}
	public ArrayList<StudentBean> selectAll() {
		ArrayList<StudentBean> list=new ArrayList<StudentBean>();
		StudentBean data=null;
		String sql="select a.code, a.name,a.idcard,a.postno,a.addr1,a.addr2,c.name,b.name  from student a,dept b,professor c  where a.dept=b.code and a.professor=c.code";
		try {
			cn= db.getConnection();
			ps= (PreparedStatement) cn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				data=new StudentBean();
				data.setCode(rs.getString(1));
				data.setName(rs.getString(2));
				data.setIdcard(rs.getString(3));
				data.setPostno(rs.getString(4));
				data.setAddr1(rs.getString(5));
				data.setAddr2(rs.getString(6));
				data.setProfessor(rs.getString(7));
				data.setDept(rs.getString(8));
				list.add(data);
				}
		}catch (Exception e) {
			System.out.println("db error:"+e.getMessage());
		}
		return list;
	}

	public ArrayList<StudentBean> selectFind(String search) {
		ArrayList<StudentBean> list=new ArrayList<StudentBean>();
		StudentBean data=null;
		String sql="select a.code, a.name,a.idcard,a.postno,a.addr1,a.addr2,c.name,b.name  from student a,dept b,professor c  where a.dept=b.code and a.professor=c.code a.code like '%"+search+"%' OR  a.idcard like '%"+search+"%'";
		System.out.println(sql+"====selectFind");
		try {
			cn= db.getConnection();
			ps= (PreparedStatement) cn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				data=new StudentBean();
				data.setCode(rs.getString(1));
				data.setName(rs.getString(2));
				data.setIdcard(rs.getString(3));
				data.setPostno(rs.getString(4));
				data.setAddr1(rs.getString(5));
				data.setAddr2(rs.getString(6));
				data.setProfessor(rs.getString(7));
				data.setDept(rs.getString(8));
				list.add(data);
			}
		}catch (Exception e) {
			System.out.println("db error:"+e.getMessage());
		}
		return list;
	}

	public  StudentBean selectRow(String code) {
		String sql="select a.code, a.name,a.idcard,a.postno,a.addr1,a.addr2,c.name,b.name  from student a,dept b,professor c  where a.dept=b.code and a.professor=c.code and a.code like '"+code+"'" ;
		StudentBean data= null;
		try {
			cn= db.getConnection();
			ps= (PreparedStatement) cn.prepareStatement(sql);
			rs=ps.executeQuery();
			if(rs.next()){
				data=new StudentBean();
				data.setCode(rs.getString(1));
				data.setName(rs.getString(2));
				data.setIdcard(rs.getString(3));
				data.setPostno(rs.getString(4));
				data.setAddr1(rs.getString(5));
				data.setAddr2(rs.getString(6));
				data.setProfessor(rs.getString(7));
				data.setDept(rs.getString(8));
				cn.close();
				ps.close();
				rs.close();
			}			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return data;
	}

	public String updateRow(StudentBean data) {
		String result="";
		StringBuilder sb= new StringBuilder();
		try {
			sb.append("UPDATE student SET name='"+data.getName()+"',");
			sb.append("idcard='"+data.getIdcard()+"',postno='"+data.getPostno()+"',");
			sb.append("addr1='"+data.getAddr1()+"',addr2='"+data.getAddr2()+"',");
			sb.append("professor='"+data.getProfessorchangecode()+"',dept='"+data.getDeptchangecode()+"' ");
			sb.append(" where code='"+data.getCode()+"'");
			String sql=sb.toString();
			cn= db.getConnection();
			ps= (PreparedStatement) cn.prepareStatement(sql);
			ps.execute();
			cn.close();
			ps.close();
		} catch (Exception e) {
			result =e.getMessage();
		}
		return result;
	}

	public boolean deleteRow(String code) {
		String sql="DELETE  from student where code='"+code+"' ";
		boolean success=false;
		try {
			cn= db.getConnection();
			ps= (PreparedStatement) cn.prepareStatement(sql);
			ps.execute();
			success=true;
			cn.close();
			ps.close();
			
		}catch (Exception e) {
			System.out.println("db error:"+e.getMessage());
		}
		return success;
	}
}