package kr.ac.itschool.student;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import kr.ac.itschool.student.daoservice.StudentDaoService;
import kr.ac.itschool.student.entities.StudentBean;

public class StudentMouseListener implements MouseListener {
	JTextField code;JComboBox dept;JTextField name;JTextField idcard;JTextField postno;
	JTextField addr1;JTextField addr2;JComboBox professor;DefaultTableModel model;	 
	JTextField search;JTable table;
	StudentMouseListener(JTextField code, JComboBox dept, JTextField name, JTextField idcard,
			JTextField postno, JTextField addr1, JTextField addr2,JComboBox professor,
			DefaultTableModel model, JTextField search,JTable table) {
		this.code=code;
		this.dept=dept;
		this.name=name;
		this.idcard=idcard;
		this.postno=postno;
		this.addr1=addr1;
		this.addr2=addr2;
		this.professor=professor;
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
		String dept =(String) target.getValueAt(row,1);
		String name =(String) target.getValueAt(row,2);
		String professor =(String) target.getValueAt(row,3);
		if(e.getButton()==3){
				deleteStudent(code,dept,name,professor, row);
			return;
		}
		StudentDaoService service=new StudentDaoService();
		StudentBean data=service.selectRow(code);
		this.code.setText(data.getCode());
		this.name.setText(data.getName());
		idcard.setText(data.getIdcard());
		postno.setText(data.getPostno());
		addr1.setText(data.getAddr1());
		addr2.setText(data.getAddr2());
		this.professor.setSelectedItem(data.getProfessor());
		this.dept.setSelectedItem(data.getDept());
	}
	private void deleteStudent(String code,String dept,String name,String professor, int row) {
		int result=JOptionPane.showConfirmDialog(null, code+"\n"+dept+"\n"+name+"\n"+professor+"\n"
		+"do you wantto delete?" , "test spanish  " , JOptionPane.OK_CANCEL_OPTION);
		if(result==0){
			StudentDaoService service=new StudentDaoService();
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