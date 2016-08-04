package kr.ac.itschool.student;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import kr.ac.itschool.student.daoservice.DeptDaoService;
import kr.ac.itschool.student.daoservice.ProfessorDaoService;
import kr.ac.itschool.student.daoservice.StudentDaoService;
import kr.ac.itschool.student.entities.StudentBean;

public  class StudentEnrollActionListener implements ActionListener {
			JTextField code;JComboBox dept;JTextField name;JTextField idcard;JTextField postno;
			JTextField addr1;JTextField addr2;JComboBox professor;DefaultTableModel model;	 
			JTextField search;JTable table;
			
			StudentDaoService service=new StudentDaoService();
			DeptDaoService service2=new DeptDaoService();
			ProfessorDaoService service3=new ProfessorDaoService();
			boolean confirmchk;
	public StudentEnrollActionListener(JTextField code, JComboBox dept, JTextField name, JTextField idcard,
				JTextField postno, JTextField addr1, JTextField addr2, JComboBox professor,
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
	boolean confirmChk(){
		boolean findcode=service.findCode(code.getText());
		if(findcode){
			JOptionPane.showMessageDialog(code, "CODE중복입니다.");
			code.setText("");
			confirmchk=false;
		}else{
			JOptionPane.showMessageDialog(code, "사용 가능한 CODE입니다.");
			confirmchk=true;
		}
		return findcode;
	}
	
	public void actionPerformed(ActionEvent e) {
		String btntxt=e.getActionCommand();
		if(btntxt.equals("중복검사")){
			if(code.getText().equals("")) {
					JOptionPane.showMessageDialog(code, "CODE를 입력하세요.");
					return;
			}			
			boolean findcode=confirmChk();
			if(findcode) 
				return;
			}
			StudentBean data=new StudentBean();
			data.setCode(code.getText());
			data.setName(name.getText());
			data.setIdcard(idcard.getText());
			data.setPostno(postno.getText());
			data.setAddr1(addr1.getText());
			data.setAddr2(addr2.getText());
			data.setProfessor(professor.getSelectedItem().toString());
			data.setDept(dept.getSelectedItem().toString());
			String  list1 = service2.changecode(dept.getSelectedItem().toString());
			data.setDeptchangecode(list1);
			String  list2=service3.changecode2(professor.getSelectedItem().toString());
			data.setProfessorchangecode(list2);
						
			if(btntxt.equals("입력")){
				insertStudent(data,btntxt);
				return;
			}
			if(btntxt.equals("검색")){
				searchStudent(search.getText());
			}
			if(btntxt.equals("취소")){
				model.setRowCount(0);
				screenClear();
			}
			if(btntxt.equals("수정")){
				if( code.getText().equals("")) {
					JOptionPane.showMessageDialog(code, "검색후 수정 항목을 선택하세요.");
					return;
				}
				String before=model.getValueAt(table.getSelectedRow(), 0).toString();
				String after= code.getText();
				if(!before.equals(after)){
					JOptionPane.showMessageDialog(code, "CODE는 수정 할 수 없습니다.");
					code.setText(before);
					return;
				}
				updateStudent(data);
			}
	}
	void updateStudent(StudentBean data) {
		String result =service.updateRow(data);
		if(result.equals("")){
				JOptionPane.showMessageDialog(code, "수정 되었습니다.");
				screenClear();
				model.setValueAt(data.getDept(),table.getSelectedRow(),1);
				model.setValueAt(data.getName(),table.getSelectedRow(),2);
				model.setValueAt(data.getProfessor(),table.getSelectedRow(),3);
		}
		else
			JOptionPane.showMessageDialog(code,"수정되지 않았습니다.\n"+result);
		}
	void insertStudent(StudentBean data,String btntxt) {
		String message="-입력시 체크 사항-\n\n";
		if(!confirmchk)
			message +="CODE를 중복체크하세요! \n";
		if (code.getText().equals("")||code.getText()==null){
			message +="CODE를 입력하세요! \n";
		}
		if (idcard.getText().equals("")||idcard.getText()==null){
			message +="주민번호를 입력하세요! \n";
		}
		if(!message.equals("-입력시 체크 사항-\n\n")){
			JOptionPane.showMessageDialog(code, message);
			return;
		}
		boolean savechk2=service.insertRow(data);
		System.out.println(savechk2+"==여기");
		if(savechk2){
			Object row[]={"","","",""};
			row[0]=data.getCode();
			row[1]=data.getDept();
			row[2]=data.getName();
			row[3]=data.getProfessor();
			model.addRow(row);
			screenClear();		
			JOptionPane.showMessageDialog(code, "저장 되었습니다.");			
		}else{
			JOptionPane.showMessageDialog(code, "저장 실패");
		}
	}
	void searchStudent(String search){
		model.setRowCount(0);
		ArrayList<StudentBean> list=null;
		if(search.equals(""))
			list=service.selectAll();
		else
			list=service.selectFind(search);
		Object row[]={"","","",""};
		for(StudentBean list1:list){
			row[0]=list1.getCode();
			row[1]=list1.getDept();
			row[2]=list1.getName();
			row[3]=list1.getProfessor();
			model.addRow(row);
		}
	}
	void screenClear(){
		code.setText("");
		name.setText("");
		idcard.setText("");
		postno.setText("");
		addr1.setText("");
		addr2.setText("");
		dept.setSelectedItem("");
		professor.setSelectedItem("");
		confirmchk=false;
	}
}