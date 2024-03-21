package org.mgm.elector.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.mgm.elector.model.Designation.Post;
import org.mgm.elector.model.Nominee;
import org.mgm.elector.model.NomineeDaoImp;
import org.mgm.elector.model.Student;
import org.mgm.elector.model.StudentDAOImp;

import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class VoteConfirmDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1149600833229054031L;
	private final JPanel contentPanel = new JPanel();
	private JPanel electedNomineePanel;

	/**
	 *use mail system
	 */
	public VoteConfirmDialog(Map<Post, Nominee> electedNominees,Student student,JComboBox<Student> studentCombo) {
		setBounds(100, 100, 898, 474);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		contentPanel.add(scrollPane, BorderLayout.CENTER);
		
		JLabel headLabel=new JLabel("CONFIRM YOUR NOMINEES");
		headLabel.setVerticalAlignment(SwingConstants.CENTER);
		headLabel.setHorizontalAlignment(SwingConstants.CENTER);
		headLabel.setForeground(new Color(240, 248, 255));
		headLabel.setOpaque(true);
		headLabel.setBackground(new Color(46, 139, 87));
		headLabel.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		scrollPane.setColumnHeaderView(headLabel);
		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
			
		
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		electedNominees.forEach((key,value)->{
			if (value!=null) {
				
			electedNomineePanel=new JPanel();
			electedNomineePanel.setLayout(new BorderLayout(10,10));
			JLabel postLabel=new JLabel(key.toString());
			postLabel.setVerticalAlignment(SwingConstants.CENTER);
			postLabel.setHorizontalAlignment(SwingConstants.CENTER);
			postLabel.setForeground(new Color(240, 248, 255));
			postLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
			electedNomineePanel.setBackground(new Color(46, 139, 87));
			electedNomineePanel.add(postLabel,BorderLayout.NORTH);
			electedNomineePanel.add(new NomineePanel(value),BorderLayout.CENTER);
			panel. add(electedNomineePanel);
			}
		});
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("CONFIRM VOTE");
				okButton.setForeground(new Color(240, 248, 255));
				okButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
				okButton.setFocusPainted(false);
				okButton.setBackground(new Color(46, 139, 87));
				okButton.setActionCommand("OK");
				okButton.addActionListener(e->voteNominees(electedNominees,student,studentCombo));
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("CANCEL");
				cancelButton.setForeground(new Color(240, 248, 255));
				cancelButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
				cancelButton.setFocusPainted(false);
				cancelButton.setBackground(new Color(46, 139, 87));
				cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener(e->dispose());
				buttonPane.add(cancelButton);
			}
		}
	}

	private void voteNominees(Map<Post, Nominee> electedNominees,Student student, JComboBox<Student> studentCombo) {
		// TODO Auto-generated method stub
		List<Nominee> nominees=new ArrayList<Nominee>();
		electedNominees.forEach((key,value)->{
			if (value!=null) {
				nominees.add(value);
			}
			
		});
		try {
			int otp=12345;
			int code=Integer.parseInt( JOptionPane.showInputDialog("Enter the otp send to the mail "+student.getEmail()));
			
			if (otp==code) {
				ElectionApplication.getContext().getBean(NomineeDaoImp.class, "nomineedaobean").addVote(nominees);
				student.setVoted(true);
				ElectionApplication.getContext().getBean(StudentDAOImp.class, "studentdaobean").persistStudent(student);
				((DefaultComboBoxModel<Student>) studentCombo.getModel()).removeElement(student);
				dispose();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
