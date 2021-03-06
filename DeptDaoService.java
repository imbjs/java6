package kr.ac.itschool.student.daoservice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import kr.ac.itschool.dbpool.DBConnectionManager;
import kr.ac.itschool.student.entities.DeptBean;

public   class DeptDaoService  {
	DBConnectionManager db= DBConnectionManager.getInstance();
	Connection cn=null;
	PreparedStatement ps=null;
	ResultSet rs=null;

	public boolean insertRow(DeptBean data) {
		boolean savechk=false;
	
		StringBuilder sb= new StringBuilder();
		sb.append("INSERT INTO dept(code,name");
		sb.append(")values(");
		sb.append(" '"+data.getCode()+"','"+data.getName()+"')");
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
		String sql="SELECT code from dept  where code='"+code+"' ";
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
	public ArrayList<DeptBean> selectAll() {
		ArrayList<DeptBean> list=new ArrayList<DeptBean>();
		DeptBean data=null;
		String sql="SELECT * from dept";
		try {
			cn= db.getConnection();
			ps= (PreparedStatement) cn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				data=new DeptBean();
				data.setCode(rs.getString("code"));
				data.setName(rs.getString("name"));
				list.add(data);
				}
		}catch (Exception e) {
			System.out.println("db error:"+e.getMessage());
		}
		return list;
	}

	public ArrayList<DeptBean> selectFind(String search) {
		ArrayList<DeptBean> list=new ArrayList<DeptBean>();
		DeptBean data=null;
		String sql="SELECT * from dept  where code like '%"+search+"%' OR  name like '%"+search+"%'";
		System.out.println(sql+"====selectFind");
		try {
			cn= db.getConnection();
			ps= (PreparedStatement) cn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				data=new DeptBean();
				data.setCode(rs.getString("code"));
				data.setName(rs.getString("name"));
				list.add(data);
			}
		}catch (Exception e) {
			System.out.println("db error:"+e.getMessage());
		}
		return list;
	}

	public  DeptBean selectRow(String code) {
		String sql="SELECT * from dept where code='"+code+"' ";
		DeptBean data= null;
		try {
			cn= db.getConnection();
			ps= (PreparedStatement) cn.prepareStatement(sql);
			rs=ps.executeQuery();
			if(rs.next()){
				data=new DeptBean();
				data.setCode(rs.getString("code"));
				data.setName(rs.getString("name"));
				cn.close();
				ps.close();
				rs.close();
			}			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return data;
	}

	public String updateRow(DeptBean data) {
		String result="";
		StringBuilder sb= new StringBuilder();
		try {
			sb.append("UPDATE dept SET name='"+data.getName()+"' ");
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
		String sql="DELETE  from dept where code='"+code+"' ";
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
	public ArrayList<String> combolist1() {
		ArrayList<String> list=new ArrayList<String>();
		String sql="SELECT *  from dept";
		try {
			cn= db.getConnection();
			ps= (PreparedStatement) cn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				list.add(rs.getString("name"));
		//		String str =rs.getString("code") +" "+rs.getString("name");
		//		list.add(str);
				}
		}catch (Exception e) {
			System.out.println("db error:"+e.getMessage());
		}
		return list;		
	}
	public String changecode(String dept) {
		String list1 = null;
		String sql="select  code from dept where name='"+dept+"' ";
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