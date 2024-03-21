package org.mgm.elector.gui;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.border.LineBorder;

import org.mgm.elector.control.ElectionActions;
import org.mgm.elector.control.ElectionButtonActions;
import org.mgm.elector.model.Designation;
import org.mgm.elector.model.DesignationDaoImp;
import org.mgm.elector.model.Election;
import org.mgm.elector.model.Election.Status;

import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ElectionViewPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8826893471882151161L;
	public ElectionViewPanel() {
	}

	/**
	 * Create the panel.
	 */
	public void initialize(Election election,JPanel epanel) {
		setLayout(new BorderLayout(0, 0));
		
		SimpleDateFormat format=new SimpleDateFormat("dd MMMM YYYY");
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension());
		scrollPane.getVerticalScrollBar().setUnitIncrement(15);
		add(scrollPane, BorderLayout.CENTER);
		
		JLabel headlbl = new JLabel(election!=null?election.getElectionName():"");
		headlbl.setPreferredSize(new Dimension(110, 60));
		headlbl.setOpaque(true);
		headlbl.setHorizontalAlignment(SwingConstants.CENTER);
		headlbl.setFont(new Font("Times New Roman", Font.BOLD, 30));
		headlbl.setBorder(new LineBorder(new Color(0, 0, 0)));
		headlbl.setBackground(new Color(0, 128, 128));
		scrollPane.setColumnHeaderView(headlbl);
		
		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JPanel electionInfoPanel = new JPanel();
		electionInfoPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		electionInfoPanel.setBackground(new Color(224, 255, 255));
		panel.add(electionInfoPanel);
		electionInfoPanel.setLayout(new GridLayout(9, 2, 0, 0));
		
		JLabel label1 = new JLabel("ELECTION NAME  :  ");
		label1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		label1.setPreferredSize(new Dimension(3, 50));
		label1.setHorizontalAlignment(SwingConstants.TRAILING);
		electionInfoPanel.add(label1);
		
		JLabel name = new JLabel(election!=null?election.getElectionName():"");
		name.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		name.setPreferredSize(new Dimension(3, 50));
		electionInfoPanel.add(name);
		
		JLabel label2 = new JLabel("ELECTION DATE  :  ");
		label2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		label2.setPreferredSize(new Dimension(3, 50));
		label2.setHorizontalAlignment(SwingConstants.TRAILING);
		electionInfoPanel.add(label2);
		
		JLabel electionDate = new JLabel(election!=null?format.format(election.getElectionDate()):"");
		electionDate.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		electionDate.setPreferredSize(new Dimension(3, 50));
		electionInfoPanel.add(electionDate);
		
		JLabel label3 = new JLabel("NOMINATION DATE :  ");
		label3.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		label3.setPreferredSize(new Dimension(3, 50));
		label3.setHorizontalAlignment(SwingConstants.TRAILING);
		electionInfoPanel.add(label3);
		
		JLabel nominationDate = new JLabel(election!=null?format.format(election.getNominationDate()):"");
		nominationDate.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		nominationDate.setPreferredSize(new Dimension(3, 50));
		electionInfoPanel.add(nominationDate);
		
		JLabel label4 = new JLabel("RESULT DATE :  ");
		label4.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		label4.setPreferredSize(new Dimension(3, 50));
		label4.setHorizontalAlignment(SwingConstants.TRAILING);
		electionInfoPanel.add(label4);
		
		JLabel resultDate = new JLabel(election!=null?format.format(election.getResultDate()):"");
		resultDate.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		resultDate.setPreferredSize(new Dimension(3, 50));
		electionInfoPanel.add(resultDate);
		
		JLabel label5 = new JLabel("TOTAL VOTERS :  ");
		label5.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		label5.setPreferredSize(new Dimension(3, 50));
		label5.setHorizontalAlignment(SwingConstants.TRAILING);
		electionInfoPanel.add(label5);
		
		JLabel voterCount = new JLabel(election!=null?election.getTotalVoters()+"":"");
		voterCount.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		voterCount.setPreferredSize(new Dimension(3, 50));
		electionInfoPanel.add(voterCount);
		
		JLabel label6 = new JLabel("TOTAL POSTS :  ");
		label6.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		label6.setPreferredSize(new Dimension(3, 50));
		label6.setHorizontalAlignment(SwingConstants.TRAILING);
		electionInfoPanel.add(label6);
		
		JLabel postCount = new JLabel("");
		postCount.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		postCount.setPreferredSize(new Dimension(3, 50));
		electionInfoPanel.add(postCount);
		
		JLabel label7 = new JLabel("TOTAL NOMINEES :  ");
		label7.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		label7.setPreferredSize(new Dimension(3, 50));
		label7.setHorizontalAlignment(SwingConstants.TRAILING);
		electionInfoPanel.add(label7);
		
		JLabel nomineeCount = new JLabel("");
		nomineeCount.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		nomineeCount.setPreferredSize(new Dimension(3, 50));
		electionInfoPanel.add(nomineeCount);
		
		JLabel label8 = new JLabel("POLLING COUNT :  ");
		label8.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		label8.setPreferredSize(new Dimension(3, 50));
		label8.setHorizontalAlignment(SwingConstants.TRAILING);
		electionInfoPanel.add(label8);
		
		JLabel polls = new JLabel(election!=null?election.getVotersCount()+"":"");
		polls.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		polls.setPreferredSize(new Dimension(3, 50));
		electionInfoPanel.add(polls);
		
		JLabel label9 = new JLabel("STATUS :  ");
		label9.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		label9.setPreferredSize(new Dimension(3, 50));
		label9.setHorizontalAlignment(SwingConstants.TRAILING);
		electionInfoPanel.add(label9);
		
		JLabel status = new JLabel();
		String electionStatus="";
		switch (election!=null? election.getStatus():Status.VOTE) {
		case VOTE:
			status.setText("POLL");
			electionStatus="VOTE";
			break;
		case COUNT:
			status.setText("POLLED");
			electionStatus="COUNT";
			break;

		case RESULT:
			status.setText("ELECTED");
			electionStatus="RESULT";
			break;

		default:
			status.setText("");
			break;
		}
		status.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		status.setPreferredSize(new Dimension(3, 50));
		electionInfoPanel.add(status);
		
		JLabel designationLabel=new JLabel("DESIGNATIONS ");
		designationLabel.setHorizontalAlignment(SwingConstants.CENTER);
		designationLabel.setBackground(new Color(144, 238, 144));
		designationLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 30));
		panel.add(designationLabel);
		
		JPanel designationPanel = new JPanel();
		designationPanel.setBackground(new Color(224, 255, 255));
		panel.add(designationPanel);
		designationPanel.setLayout(new GridLayout(0, 4, 5, 5));
		List<Designation> designations =null;
		int nCount=0;
		try {
			designations=ElectionApplication.getContext().getBean(DesignationDaoImp.class,"designationdaobean")
					.getAllDesignation(election);
			for (Designation designation : designations) {
				DesignationPanel desiPanel=new DesignationPanel(designation,election);
				desiPanel.setPreferredSize(new Dimension(300,400));
				designationPanel.add(desiPanel);
				nCount+=designation.getNomineeCount();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		postCount.setText(designations.size()+"");
		nomineeCount.setText(nCount+"");
		JPanel buttonPanel =new JPanel();
		buttonPanel.setLayout(new GridLayout(1, 4));
		add(buttonPanel, BorderLayout.SOUTH);
		JButton designationBtn = new JButton("ADD DESIGNATION");
		designationBtn.setForeground(new Color(0, 0, 0));
		designationBtn.setPreferredSize(new Dimension(100, 60));
		designationBtn.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		designationBtn.setFocusPainted(false);
		designationBtn.setBackground(new Color(0, 128, 128));
		designationBtn.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		designationBtn.setActionCommand("designation");
		buttonPanel.add(designationBtn);
		
		JButton electionBtn = new JButton(electionStatus);
		electionBtn.setForeground(new Color(0, 0, 0));
		electionBtn.setPreferredSize(new Dimension(100, 60));
		electionBtn.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		electionBtn.setFocusPainted(false);
		electionBtn.setBackground(new Color(0, 128, 128));
		electionBtn.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		electionBtn.setActionCommand(electionStatus.toLowerCase());
		buttonPanel.add(electionBtn);
		
		JButton deleteBtn = new JButton("DELETE ELECTION");
		deleteBtn.setPreferredSize(new Dimension(100, 60));
		deleteBtn.setForeground(Color.BLACK);
		deleteBtn.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		deleteBtn.setFocusPainted(false);
		deleteBtn.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		deleteBtn.setBackground(new Color(0, 128, 128));
		deleteBtn.setActionCommand("delete");
		buttonPanel.add(deleteBtn);
		
		JButton editBtn = new JButton("EDIT ELECTION");
		editBtn.setPreferredSize(new Dimension(100, 60));
		editBtn.setForeground(Color.BLACK);
		editBtn.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		editBtn.setFocusPainted(false);
		editBtn.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		editBtn.setBackground(new Color(0, 128, 128));
		editBtn.setActionCommand("edit");
		if (election!=null) {
			if (new Date().compareTo(election.getElectionDate()) > 0) {
				editBtn.setEnabled(false);
			} 
		}
		buttonPanel.add(editBtn);
		ElectionActions actions=new ElectionActions(election,epanel);
		editBtn.addActionListener(actions);
		deleteBtn.addActionListener(actions);
		designationBtn.addActionListener(new ElectionActions(designationPanel,election));
		electionBtn.addActionListener(new ElectionButtonActions(election,null));
		if (election.getStatus()!=Status.VOTE) {
			editBtn.setEnabled(false);
			designationBtn.setEnabled(false);
		}
	}
	public void setElection(Election election,JPanel panel) {
		removeAll();
		initialize(election,panel);
		revalidate();
		repaint();
		
	}

}
