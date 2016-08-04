package kr.ac.itschool.student;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import kr.ac.itschool.student.daoservice.DeptDaoService;
import kr.ac.itschool.student.entities.DeptBean;

public class DeptEnrollMouseListener implements MouseListener {
	JTextField code;JTextField name;DefaultTableModel model;	 
	JTextField search;JTable table;
	DeptEnrollMouseListener(JTextField code, JTextField name,
			DefaultTableModel model, JTextField search,JTable table) {
		this.code=code;
		this.name=name;
		this.model=model;
		this.search=search;
		this.table=table;
	}
	public void mouseClicked(MouseEvent e) {
		JTable target = (JTable) e.getSource();
		int row=target.getSelectedRow();
		if (row==-1){
			JOptionPane.showMessageDialog(code,"항목을 입력하세요");
			return;
		}
		String code =(String) target.getValueAt(row,0);
		String name =(String) target.getValueAt(row,1);
		if(e.getButton()==3){
				deleteDept(code,name, row);
			return;
		}
		DeptDaoService service=new DeptDaoService();
		DeptBean data=service.selectRow(code);
		this.code.setText(data.getCode());
		this.name.setText(data.getName());
	}
	private void deleteDept(String code,String name, int row) {
		int result=JOptionPane.showConfirmDialog(null, code+"\n"+name+"\n"+"do you wantto delete?" , "test spanish  " , JOptionPane.OK_CANCEL_OPTION);
		if(result==0){
			DeptDaoService service=new DeptDaoService();
			boolean success=service.deleteRow(code);
		if(success){
				model.removeRow(row);
		}
		}else{
			return;
		}
	}
	@Override
	public void mouseEntered(MouseEvent e) {
	}
	@Override
	public void mouseExited(MouseEvent e) {
	}
	@Override
	public void mousePressed(MouseEvent e) {
	}
	@Override
	public void mouseReleased(MouseEvent e) {
	}
}