package kr.ac.itschool.student.daoservice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import kr.ac.itschool.dbpool.DBConnectionManager;
import kr.ac.itschool.student.entities.ProfessorBean;

public   class ProfessorDaoService  {
	DBConnectionManager db= DBConnectionManager.getInstance();
	Connection cn=null;
	PreparedStatement ps=null;
	ResultSet rs=null;

	public boolean insertRow(ProfessorBean data) {
		boolean savechk=false;
	
		StringBuilder sb= new StringBuilder();
		sb.append("INSERT INTO professor(code,name,labno,subject");
		sb.append(")values(");
		sb.append(" '"+data.getCode()+"','"+data.getName()+"','"+data.getLabno()+"','"+data.getSubject()+"')");
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
		String sql="SELECT code from professor  where code='"+code+"' ";
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
	public ArrayList<ProfessorBean> selectAll() {
		ArrayList<ProfessorBean> list=new ArrayList<ProfessorBean>();
		ProfessorBean data=null;
		String sql="SELECT * from professor";
		try {
			cn= db.getConnection();
			ps= (PreparedStatement) cn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				data=new ProfessorBean();
				data.setCode(rs.getString("code"));
				data.setName(rs.getString("name"));
				list.add(data);
				}
		}catch (Exception e) {
			System.out.println("db error:"+e.getMessage());
		}
		return list;
	}

	public ArrayList<ProfessorBean> selectFind(String search) {
		ArrayList<ProfessorBean> list=new ArrayList<ProfessorBean>();
		ProfessorBean data=null;
		String sql="SELECT * from professor  where code like '%"+search+"%' OR  name like '%"+search+"%'";
		System.out.println(sql+"====selectFind");
		try {
			cn= db.getConnection();
			ps= (PreparedStatement) cn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				data=new ProfessorBean();
				data.setCode(rs.getString("code"));
				data.setName(rs.getString("name"));
				list.add(data);
			}
		}catch (Exception e) {
			System.out.println("db error:"+e.getMessage());
		}
		return list;
	}

	public  ProfessorBean selectRow(String code) {
		String sql="SELECT * from professor where code='"+code+"' ";
		ProfessorBean data= null;
		try {
			cn= db.getConnection();
			ps= (PreparedStatement) cn.prepareStatement(sql);
			rs=ps.executeQuery();
			if(rs.next()){
				data=new ProfessorBean();
				data.setCode(rs.getString("code"));
				data.setName(rs.getString("name"));
				data.setLabno(rs.getString("labno"));
				data.setSubject(rs.getString("subject"));
				cn.close();
				ps.close();
				rs.close();
			}			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return data;
	}

	public String updateRow(ProfessorBean data) {
		String result="";
		StringBuilder sb= new StringBuilder();
		try {
			sb.append("UPDATE professor SET name='"+data.getName()+"',");
			sb.append("labno='"+data.getLabno()+"',subject='"+data.getSubject()+"'  ");
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
		String sql="DELETE  from professor where code='"+code+"' ";
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
	public ArrayList<String> combolist2() {
		ArrayList<String> list2=new ArrayList<String>();
		String sql="SELECT name  from professor";
		try {
			cn= db.getConnection();
			ps= (PreparedStatement) cn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				list2.add(rs.getString("name"));
				}
		}catch (Exception e) {
			System.out.println("db error:"+e.getMessage());
		}
		return list2;
	}
	public String changecode2(String professor) {
		String list1=null;
		String sql="select  code from professor where name='"+professor+"' ";
		try {
			cn= db.getConnection();
			ps= (PreparedStatement) cn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				list1=rs.getString("code");
				}
		}catch (Exception e) {
			System.out.println("db error:"+e.getMessage());
		}
		return list1;		
	}	
}