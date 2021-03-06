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

public class DeptEnrollButtonListener implements ActionListener {
	DeptDaoService service=new DeptDaoService();
				JFrame frame;
				JFrame frame1;
				boolean confirmchk;
public DeptEnrollButtonListener(JFrame frame) {
				this.frame= frame;
				frame1= new JFrame("학과 등록");
}

@Override
public void actionPerformed(ActionEvent e) {
				
				frame.setVisible(false);
				frame1.setLocation(100,200);
				frame1.setPreferredSize(new Dimension(1000,200));
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
			JLabel code2 =new JLabel("학과코드");
			code2.setHorizontalAlignment(JLabel.CENTER);
			JTextField code = new JTextField(10);	
			JLabel name2 =new JLabel("학과명");
			name2.setHorizontalAlignment(JLabel.CENTER);
			JTextField name = new JTextField(10);
		    JPanel panel= new JPanel();	
			
			JPanel codeserch=new JPanel();
			JButton codeser=new JButton("중복검사");
			       codeserch.add(code);
			       codeserch.add(codeser);	
	       
	       panel.add(code2);
	       panel.add(codeserch);   
	       panel.add(name2 );
	       panel.add(name );     
	       
	   GridLayout gridLayout = new GridLayout(2,2,10,10);	
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
	        String colNames[] = { "학과코드", "학과명" };
	        DefaultTableModel model = new DefaultTableModel(colNames, 0);
	        JTable table = new JTable(model);   
	        JTableHeader header=table.getTableHeader();
	        header.setPreferredSize(new Dimension(100,30));
	        header.setBackground(Color.lightGray);
	        contentPane2.add(new JScrollPane(table), BorderLayout.EAST);
	      
	       DeptEnrollMouseListener mouse=new DeptEnrollMouseListener(code,name,model,search,table);
	       table.addMouseListener(mouse);
	        
	      DeptEnrollActionListener listener3= new DeptEnrollActionListener(code,name,model,search,table);
	      button11.addActionListener(listener3);
	      button12.addActionListener(listener3);
	      button13.addActionListener(listener3);
	      button14.addActionListener(listener3);
	      button15.addActionListener(listener3);
	      codeser.addActionListener(listener3);
	}
}