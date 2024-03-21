package org.mgm.elector.gui;

import javax.swing.JPanel;

import org.mgm.elector.model.Designation;
import org.mgm.elector.model.DesignationDaoImp;
import org.mgm.elector.model.Election;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Dimension;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.util.List;
import java.awt.Color;

public class WinnerPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2888295696634413348L;
	/**
	 * Create the panel.
	 */
	public void setElection(Election election) {
		//removeAll();
		initialize(election);
	}
	public void initialize(Election election) {
		setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel(election.getElectionName());
		lblNewLabel.setForeground(new Color(240, 248, 255));
		lblNewLabel.setBackground(new Color(46, 139, 87));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 28));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setOpaque(true);
		lblNewLabel.setPreferredSize(new Dimension(46, 60));
		add(lblNewLabel, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		List<Designation> designations=ElectionApplication.getContext().getBean(DesignationDaoImp.class,"designationdaobean")
				.getAllDesignation(election);
		for (Designation designation : designations) {
			JPanel electedNomineePanel = new JPanel();
			electedNomineePanel.setLayout(new BorderLayout(10,10));
			JLabel postLabel=new JLabel(designation.getPost().toString());
			postLabel.setVerticalAlignment(SwingConstants.CENTER);
			postLabel.setHorizontalAlignment(SwingConstants.CENTER);
			postLabel.setForeground(new Color(240, 248, 255));
			postLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
			electedNomineePanel.setBackground(new Color(46, 139, 87));
			electedNomineePanel.add(postLabel,BorderLayout.NORTH);
			electedNomineePanel.add(new NomineePanel(designation.getWinner()),BorderLayout.CENTER);
			panel. add(electedNomineePanel);
		}
		
	}

}
