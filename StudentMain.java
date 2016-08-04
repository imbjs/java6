package kr.ac.itschool.student;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class StudentMain {

	public static void main(String[] args) {
				JFrame frame = new JFrame("�л� ���");
				frame.setLocation(100,100);
				frame.setPreferredSize(new Dimension(400,500));
				Container contentPane = frame.getContentPane();
				
		        JPanel panel = new JPanel();
		        JButton button1 = new JButton("�л� ���");
		        JButton button2 = new JButton("���� ���");
		        JButton button3 = new JButton("�а� ���");
		        panel.add(button1);
		        panel.add(button2);
		        panel.add(button3);
		       contentPane.add(panel, BorderLayout.CENTER);
		       
		       StudentEnrollButtonListener listener1= new  StudentEnrollButtonListener(frame);
		       button1.addActionListener(listener1);
		       
		      ProfessorEnrollButtonListener listener2= new ProfessorEnrollButtonListener(frame);
		       button2.addActionListener(listener2);
		       
		       DeptEnrollButtonListener listener3= new DeptEnrollButtonListener(frame);
		       button3.addActionListener(listener3);
		       
		 
		      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		      frame.pack();
		      frame.setVisible(true);   
		}
}