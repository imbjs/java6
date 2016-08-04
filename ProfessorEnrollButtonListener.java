package kr.ac.itschool.student;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import kr.ac.itschool.student.daoservice.DeptDaoService;

public class ProfessorEnrollButtonListener implements ActionListener {
	DeptDaoService service=new DeptDaoService();
				JFrame frame;
				JFrame frame1;
				boolean confirmchk;
public ProfessorEnrollButtonListener(JFrame frame) {
				this.frame= frame;
				frame1= new JFrame("교수 등록");
}

@Override
public void actionPerformed(ActionEvent e) {
				
				frame.setVisible(false);
				frame1.setLocation(100,200);
				frame1.setPreferredSize(new Dimension(1000,300));
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
			JLabel code2 =new JLabel("교수코드");
			code2.setHorizontalAlignment(JLabel.CENTER);
			JTextField code = new JTextField(10);	
			JLabel name2 =new JLabel("이름");
			name2.setHorizontalAlignment(JLabel.CENTER);
			JTextField name = new JTextField(10);
			JLabel labno2 =new JLabel("연구실번호");
			labno2.setHorizontalAlignment(JLabel.CENTER);
			JTextField labno = new JTextField(10);
			JLabel subject2 =new JLabel("담당과목");
			subject2.setHorizontalAlignment(JLabel.CENTER);
			JTextField subject = new JTextField(10);
		    JPanel panel= new JPanel();	
			
			JPanel codeserch=new JPanel();
			JButton codeser=new JButton("중복검사");
			       codeserch.add(code);
			       codeserch.add(codeser);	
	       
	       panel.add(code2);
	       panel.add(codeserch);   
	       panel.add(name2 );
	       panel.add(name );     
	       panel.add(labno2 );     
	       panel.add(labno );     
	       panel.add(subject2 );     
	       panel.add(subject );     
	       
	   GridLayout gridLayout = new GridLayout(4,2,10,10);	
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
	        String colNames[] = { "교수코드", "성명" };
	        DefaultTableModel model = new DefaultTableModel(colNames, 0);
	        JTable table = new JTable(model);   
	        JTableHeader header=table.getTableHeader();
	        header.setPreferredSize(new Dimension(100,30));
	        header.setBackground(Color.lightGray);
	        contentPane2.add(new JScrollPane(table), BorderLayout.EAST);
	      
	       ProfessorEnrollMouseListener mouse=new ProfessorEnrollMouseListener(code,name,labno,subject,model,search,table);
	       table.addMouseListener(mouse);
	        
	      ProfessorEnrollActionListener listener3= new ProfessorEnrollActionListener(code,name,labno,subject,model,search,table);
	      button11.addActionListener(listener3);
	      button12.addActionListener(listener3);
	      button13.addActionListener(listener3);
	      button14.addActionListener(listener3);
	      button15.addActionListener(listener3);
	      codeser.addActionListener(listener3);
	}
}