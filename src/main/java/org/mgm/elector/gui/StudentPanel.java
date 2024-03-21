package org.mgm.elector.gui;

import javax.swing.JPanel;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Graphics;

import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.border.BevelBorder;

import org.mgm.elector.model.Student;

public class StudentPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3103942758878919679L;
	private Student student;
	/**
	 * Create the panel.
	 */
	public StudentPanel(Student student) {
		this.student=student;
		setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		setBackground(new Color(152, 251, 152));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{20,100,161,20};
		gridBagLayout.rowHeights = new int[]{15,60,60,60,15};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0};
		setLayout(gridBagLayout);
		
		JPanel photoPanel = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 4194443523936411804L;

			@Override
    		protected void paintComponent(Graphics g) {
    			// TODO Auto-generated method stub
    			super.paintComponent(g);
    			Image photo=null;
    			try {
    				
    				photo=ImageIO
    						.read(new ByteArrayInputStream(student.getPhoto()));
    						
    			} catch (IOException e) {
    				e.printStackTrace();
    			}
    			g.drawImage(photo, 0, 0,this.getWidth(),this.getHeight(), null);
    		}
		};
		
		photoPanel.setPreferredSize(new Dimension(75, 75));
		
		photoPanel.setBackground(new Color(255, 0, 0));
		
		GridBagConstraints gbc_photoLbl = new GridBagConstraints();
		gbc_photoLbl.gridheight = 3;
		gbc_photoLbl.fill = GridBagConstraints.BOTH;
		gbc_photoLbl.insets = new Insets(30, 0, 30, 0);
		gbc_photoLbl.gridx = 1;
		gbc_photoLbl.gridy = 1;
		add(photoPanel, gbc_photoLbl);
		
		JLabel nameLbl = new JLabel(student.getStudentName());
		nameLbl.setFont(new Font("Tahoma", Font.BOLD, 12));
		GridBagConstraints gbc_nameLbl = new GridBagConstraints();
		gbc_nameLbl.anchor = GridBagConstraints.SOUTH;
		gbc_nameLbl.fill = GridBagConstraints.HORIZONTAL;
		gbc_nameLbl.insets = new Insets(0, 20, 5, 5);
		gbc_nameLbl.gridx = 2;
		gbc_nameLbl.gridy = 1;
		add(nameLbl, gbc_nameLbl);
		
		JLabel departmentLbl = new JLabel(student.getDepartment().getDeptName());
		departmentLbl.setFont(new Font("Tahoma", Font.BOLD, 12));
		GridBagConstraints gbc_departmentLbl = new GridBagConstraints();
		gbc_departmentLbl.fill = GridBagConstraints.HORIZONTAL;
		gbc_departmentLbl.insets = new Insets(0, 20, 5, 5);
		gbc_departmentLbl.gridx = 2;
		gbc_departmentLbl.gridy = 2;
		add(departmentLbl, gbc_departmentLbl);
		
		JLabel yearLbl = new JLabel("Year : "+student.getAcademicYear());
		yearLbl.setFont(new Font("Tahoma", Font.BOLD, 12));
		GridBagConstraints gbc_yearLbl = new GridBagConstraints();
		gbc_yearLbl.anchor = GridBagConstraints.NORTH;
		gbc_yearLbl.insets = new Insets(0, 20, 0, 5);
		gbc_yearLbl.fill = GridBagConstraints.HORIZONTAL;
		gbc_yearLbl.gridx = 2;
		gbc_yearLbl.gridy = 3;
		add(yearLbl, gbc_yearLbl);

	}
	public Student getStudent() {
		return student;
	}

}
