package kr.ac.itschool.student;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import kr.ac.itschool.student.daoservice.ProfessorDaoService;
import kr.ac.itschool.student.entities.ProfessorBean;


public  class ProfessorEnrollActionListener implements ActionListener {
			
			JTextField code;JTextField name;DefaultTableModel model;	 
			JTextField search;JTable table;JTextField labno;JTextField subject;
			
			ProfessorDaoService service=new ProfessorDaoService();
			boolean confirmchk;
	public ProfessorEnrollActionListener(JTextField code, JTextField name,JTextField labno,JTextField subject,
				DefaultTableModel model, JTextField search,JTable table) {
		this.code=code;
		this.name=name;
		this.labno=labno;
		this.subject=subject;
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
		ProfessorBean data=new ProfessorBean();
			data.setCode(code.getText());
			data.setName(name.getText());
					
		if(btntxt.equals("입력")){
				insertProfessor(data,btntxt);
				return;
			}
			if(btntxt.equals("검색")){
				searchProfessor(search.getText());
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
				updateProfessor(data);
			}
	}
	void updateProfessor(ProfessorBean data) {
		String result =service.updateRow(data);
		if(result.equals("")){
				JOptionPane.showMessageDialog(code, "수정 되었습니다.");
				screenClear();
				model.setValueAt(data.getName(),table.getSelectedRow(),1);
		}		
		else
			JOptionPane.showMessageDialog(code,"수정되지 않았습니다.\n"+result);
		}
	void insertProfessor(ProfessorBean data,String btntxt) {
		String message="-입력시 체크 사항-\n\n";
		if(!confirmchk)
			message +="CODE를 중복체크하세요! \n";
		if (code.getText().equals("")||code.getText()==null){
			message +="CODE를 입력하세요! \n";
		}
		if (name.getText().equals("")||name.getText()==null){
			message +="이름을 입력하세요! \n";
		}
		if(!message.equals("-입력시 체크 사항-\n\n")){
			JOptionPane.showMessageDialog(code, message);
			return;
		}
		boolean savechk2=service.insertRow(data);
		System.out.println(savechk2+"==여기");
		if(savechk2){
			Object row[]={"",""};
			row[0]=data.getCode();
			row[1]=data.getName();
			model.addRow(row);
			screenClear();		
			JOptionPane.showMessageDialog(code, "저장 되었습니다.");			
		}else{
			JOptionPane.showMessageDialog(code, "저장 실패");
		}
	}
	void searchProfessor(String search){
		model.setRowCount(0);
		ArrayList<ProfessorBean> list=null;
		if(search.equals(""))
			list=service.selectAll();
		else
			list=service.selectFind(search);
		Object row[]={"",""};
		for(ProfessorBean list1:list){
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