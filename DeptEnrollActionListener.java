package kr.ac.itschool.student;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import kr.ac.itschool.student.daoservice.DeptDaoService;
import kr.ac.itschool.student.entities.DeptBean;


public  class DeptEnrollActionListener implements ActionListener {
			
			JTextField code;JTextField name;DefaultTableModel model;	 
			JTextField search;JTable table;
			
			DeptDaoService service=new DeptDaoService();
			boolean confirmchk;
	public DeptEnrollActionListener(JTextField code, JTextField name,
				DefaultTableModel model, JTextField search,JTable table) {
		this.code=code;
		this.name=name;
		this.model=model;
		this.search=search;
		this.table=table;
		}
	boolean confirmChk(){
		boolean findcode=service.findCode(code.getText());
		if(findcode){
			JOptionPane.showMessageDialog(code, "CODE�ߺ��Դϴ�.");
			code.setText("");
			confirmchk=false;
		}else{
			JOptionPane.showMessageDialog(code, "��� ������ CODE�Դϴ�.");
			confirmchk=true;
		}
		return findcode;
	}
	
	public void actionPerformed(ActionEvent e) {
		String btntxt=e.getActionCommand();
		if(btntxt.equals("�ߺ��˻�")){
			if(code.getText().equals("")) {
					JOptionPane.showMessageDialog(code, "CODE�� �Է��ϼ���.");
					return;
			}			
			boolean findcode=confirmChk();
			if(findcode) 
				return;
			}
		DeptBean data=new DeptBean();
			data.setCode(code.getText());
			data.setName(name.getText());
					
		if(btntxt.equals("�Է�")){
				insertDept(data,btntxt);
				return;
			}
			if(btntxt.equals("�˻�")){
				searchStudent(search.getText());
			}
			if(btntxt.equals("���")){
				model.setRowCount(0);
				screenClear();
			}
			if(btntxt.equals("����")){
				if( code.getText().equals("")) {
					JOptionPane.showMessageDialog(code, "�˻��� ���� �׸��� �����ϼ���.");
					return;
				}
				String before=model.getValueAt(table.getSelectedRow(), 0).toString();
				String after= code.getText();
				if(!before.equals(after)){
					JOptionPane.showMessageDialog(code, "CODE�� ���� �� �� �����ϴ�.");
					code.setText(before);
					return;
				}
				updateDept(data);
			}
	}
	void updateDept(DeptBean data) {
		String result =service.updateRow(data);
		if(result.equals("")){
				JOptionPane.showMessageDialog(code, "���� �Ǿ����ϴ�.");
				screenClear();
				model.setValueAt(data.getName(),table.getSelectedRow(),1);
		}		
		else
			JOptionPane.showMessageDialog(code,"�������� �ʾҽ��ϴ�.\n"+result);
		}
	void insertDept(DeptBean data,String btntxt) {
		String message="-�Է½� üũ ����-\n\n";
		if(!confirmchk)
			message +="CODE�� �ߺ�üũ�ϼ���! \n";
		if (code.getText().equals("")||code.getText()==null){
			message +="CODE�� �Է��ϼ���! \n";
		}
		if (name.getText().equals("")||name.getText()==null){
			message +="�ֹι�ȣ�� �Է��ϼ���! \n";
		}
		if(!message.equals("-�Է½� üũ ����-\n\n")){
			JOptionPane.showMessageDialog(code, message);
			return;
		}
		boolean savechk2=service.insertRow(data);
		System.out.println(savechk2+"==����");
		if(savechk2){
			Object row[]={"",""};
			row[0]=data.getCode();
			row[1]=data.getName();
			model.addRow(row);
			screenClear();		
			JOptionPane.showMessageDialog(code, "���� �Ǿ����ϴ�.");			
		}else{
			JOptionPane.showMessageDialog(code, "���� ����");
		}
	}
	void searchStudent(String search){
		model.setRowCount(0);
		ArrayList<DeptBean> list=null;
		if(search.equals(""))
			list=service.selectAll();
		else
			list=service.selectFind(search);
		Object row[]={"",""};
		for(DeptBean list1:list){
			row[0]=list1.getCode();
			row[1]=list1.getName();
			model.addRow(row);
		}
	}
	void screenClear(){
		code.setText("");
		name.setText("");
		confirmchk=false;
	}
}