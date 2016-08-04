package kr.ac.itschool.student;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import kr.ac.itschool.student.daoservice.DeptDaoService;
import kr.ac.itschool.student.daoservice.ProfessorDaoService;
import kr.ac.itschool.student.daoservice.StudentDaoService;

public class StudentEnrollButtonListener implements ActionListener {
		StudentDaoService service=new StudentDaoService();
		DeptDaoService service2=new DeptDaoService();
		ProfessorDaoService service3=new ProfessorDaoService();
				JFrame frame;
				JFrame frame1;
				boolean confirmchk;
public StudentEnrollButtonListener(JFrame frame) {
				this.frame= frame;
				frame1= new JFrame("학생 등록");
}

@SuppressWarnings("unchecked")
@Override
public void actionPerformed(ActionEvent e) {
				
				frame.setVisible(false);
				frame1.setLocation(100,200);
				frame1.setPreferredSize(new Dimension(1000,500));
				frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				Container contentPane= frame1.getContentPane();
				JButton home= new JButton("Home");
			
				home.addActionListener(new ActionListener() {
					@Override
		public void actionPerformed(ActionEvent e) {
				frame1.setVisible(false);
				frame.setVisible(true);
				return;
				}
				});
			JLabel code2 =new JLabel("학번");
			code2.setHorizontalAlignment(JLabel.CENTER);
			JTextField code = new JTextField(10);	
			JLabel dept2 =new JLabel("학과");
			dept2.setHorizontalAlignment(JLabel.CENTER);
	//		JTextField dept = new JTextField(20);	
			JLabel name2 =new JLabel("이름");
			name2.setHorizontalAlignment(JLabel.CENTER);
			JTextField name = new JTextField(8);
			JLabel idcard2 =new JLabel("주민번호");
			idcard2.setHorizontalAlignment(JLabel.CENTER);
			JTextField idcard = new JTextField(13);	
			JLabel postno2 =new JLabel("우편번호");
			postno2.setHorizontalAlignment(JLabel.CENTER);
			JTextField postno = new JTextField(6);	
			JLabel addr12 =new JLabel("주소1");
			addr12.setHorizontalAlignment(JLabel.CENTER);
			JTextField addr1 = new JTextField(20);	
			JLabel addr22 =new JLabel("주소2");
			addr22.setHorizontalAlignment(JLabel.CENTER);
			JTextField addr2 = new JTextField(20);	
			JLabel professor2 =new JLabel("지도교수");
			professor2.setHorizontalAlignment(JLabel.CENTER);
	//		JTextField professor = new JTextField(10);	
		    JPanel panel= new JPanel();	
	
			JPanel codeserch=new JPanel();
			JButton codeser=new JButton("중복검사");
			       codeserch.add(code);
			       codeserch.add(codeser);	
			
	
		   ArrayList<String> list1 = service2.combolist1();
		   JComboBox dept=new JComboBox();
		  dept.setModel(new DefaultComboBoxModel(list1.toArray()));
		 		 
		  ArrayList<String> list2 = service3.combolist2();
		  JComboBox professor=new JComboBox();
	      professor.setModel(new DefaultComboBoxModel(list2.toArray()));
	      
	      
	       panel.add(code2);    panel.add(codeserch);   panel.add(dept2);   panel.add(dept);
	       panel.add(name2 );    panel.add(name );      panel.add(idcard2 );      panel.add(idcard );
	       panel.add(postno2 );       panel.add(postno );       panel.add(addr12 );       panel.add(addr1 );
	       panel.add(addr22 );	       panel.add(addr2 );	       panel.add(professor2 );	       panel.add(professor );
	    
	       GridLayout gridLayout = new GridLayout(8,2,5,5);	
	   		panel.setLayout(gridLayout);
	       contentPane.add(panel, BorderLayout.WEST);
	       
	  	  JPanel panel2= new JPanel();			  
	 	  JButton button11 = new JButton("입력");
	      JButton button12 = new JButton("수정");
	      JButton button13 = new JButton("삭제");
	      JTextField search = new JTextField(10);
	      JButton button14 = new JButton("검색");
	      JButton button15 = new JButton("취소");			
	     	panel2.add(button11);
	        panel2.add(button12);
	        panel2.add(button13);
	        panel2.add(search);
	        panel2.add(button14);
	        panel2.add(button15);
	        panel2.add(home);
	     contentPane.add(panel2, BorderLayout.SOUTH);
		frame1.pack();
		frame1.setVisible(true);
			
			Container contentPane2= frame1.getContentPane();
	        String colNames[] = { "학번", "학과", "성명","지도교수" };
	        DefaultTableModel model = new DefaultTableModel(colNames, 0);
	        JTable table = new JTable(model);   
	        JTableHeader header=table.getTableHeader();
	        header.setPreferredSize(new Dimension(100,30));
	        header.setBackground(Color.lightGray);
	        contentPane2.add(new JScrollPane(table), BorderLayout.EAST);
	      
	        StudentMouseListener mouse=new StudentMouseListener(code,dept,name,idcard,
	    			postno,addr1,addr2,professor,model,search,table);
	       table.addMouseListener(mouse);
	        
	      StudentEnrollActionListener listener3= new StudentEnrollActionListener(code,dept,name,idcard,
	       	postno,addr1,addr2,professor,model,search,table);
	      button11.addActionListener(listener3);
	      button12.addActionListener(listener3);
	      button13.addActionListener(listener3);
	      button14.addActionListener(listener3);
	      button15.addActionListener(listener3);
	      codeser.addActionListener(listener3);
	}
}