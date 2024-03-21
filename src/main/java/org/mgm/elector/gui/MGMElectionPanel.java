package org.mgm.elector.gui;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JSplitPane;
import javax.swing.ListCellRenderer;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;

import java.awt.Dimension;
import javax.swing.SwingConstants;
import java.awt.Font;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.swing.border.LineBorder;

import org.mgm.elector.control.ElectionButtonActions;
import org.mgm.elector.model.Election;
import org.mgm.elector.model.ElectionDaoImp;
import org.mgm.elector.model.Student;
import org.mgm.elector.model.StudentDAO;
import org.mgm.elector.model.StudentDAOImp;

import java.awt.GridLayout;
import javax.swing.ScrollPaneConstants;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

public class MGMElectionPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6080765650843924549L;
	private JList<StudentPanel> voterList;
	private JPanel electionPanel;
	

	public JPanel getElectionPanel() {
		return electionPanel;
	}

	public JList<StudentPanel> getVoterList() {
		return voterList;
	}

	/**
	 * Create the panel.
	 */
	public MGMElectionPanel() {
		setLayout(new BorderLayout(0, 0));
		JSplitPane splitPane = new JSplitPane();
		splitPane.setDividerSize(1);
		splitPane.setResizeWeight(1);
		
		add(splitPane, BorderLayout.CENTER);
		
		JPanel mainVoterPanel = new JPanel();
		mainVoterPanel.setPreferredSize(new Dimension(800, 10));
		splitPane.setRightComponent(mainVoterPanel);
		mainVoterPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel voterlbl = new JLabel("VOTER'S LIST");
		voterlbl.setOpaque(true);
		voterlbl.setPreferredSize(new Dimension(65, 50));
		voterlbl.setHorizontalAlignment(SwingConstants.CENTER);
		voterlbl.setFont(new Font("Times New Roman", Font.BOLD, 20));
		voterlbl.setBackground(new Color(30, 144, 255));
		mainVoterPanel.add(voterlbl, BorderLayout.NORTH);
		JPanel buttonPanel=new JPanel();
		mainVoterPanel.add(buttonPanel, BorderLayout.SOUTH);
		JButton voterBtn = new JButton("REGISTER VOTER");
		voterBtn.setActionCommand("register");
		voterBtn.setBackground(new Color(30, 144, 255));
		voterBtn.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		voterBtn.setFocusPainted(false);
		voterBtn.setPreferredSize(new Dimension(117, 50));
		voterBtn.setFont(new Font("Times New Roman", Font.BOLD, 20));
		voterBtn.addActionListener(new ElectionButtonActions());
		buttonPanel.setLayout(new GridLayout(0, 2, 0, 0));
		buttonPanel.add(voterBtn);
		
		JButton deleteBtn = new JButton("DELETE STUDENT");
		deleteBtn.setActionCommand("delete");
		deleteBtn.setFont(new Font("Times New Roman", Font.BOLD, 20));
		deleteBtn.setBackground(new Color(255, 100, 100));
		deleteBtn.setFocusPainted(false);
		deleteBtn.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		buttonPanel.add(deleteBtn);
		
		JScrollPane voterScroll = new JScrollPane();
		voterScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		voterScroll.getVerticalScrollBar().setUnitIncrement(15);
		voterScroll.getVerticalScrollBar().setPreferredSize(new Dimension());
		mainVoterPanel.add(voterScroll, BorderLayout.CENTER);
		Set<Student> voterSet=null;
		List<StudentPanel> studentPanels=new ArrayList<StudentPanel>();
		try {
			StudentDAO dao=	ElectionApplication.getContext().getBean(StudentDAOImp.class,"studentdaobean");
			voterSet = dao.getAllStudents();
			
			StudentPanel studentPanel=null;
			for (Student student : voterSet) {
				studentPanel=new StudentPanel(student);
				studentPanels.add(studentPanel);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		voterList = new JList<StudentPanel>();
		voterList.setVisibleRowCount(-1);
		voterList.setFixedCellHeight(250);
		voterList.setFixedCellWidth(398);
		voterList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		voterList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		DefaultListModel<StudentPanel> model=new DefaultListModel<StudentPanel>();
		model.addAll(studentPanels);
		voterList.setCellRenderer(new VoterListCellRenderer());
		voterList.setModel(model);
		deleteBtn.addActionListener(new ElectionButtonActions(voterList));
		voterScroll.setViewportView(voterList);
	
		JPanel mainElectionPanel = new JPanel();
		splitPane.setLeftComponent(mainElectionPanel);
		mainElectionPanel.setLayout(new BorderLayout(0, 0));
		
		JButton electionBtn = new JButton("+ CREATE NEW ELECTION");
		electionBtn.setActionCommand("create election");
		electionBtn.setFont(new Font("Times New Roman", Font.BOLD, 30));
		electionBtn.setFocusPainted(false);
		electionBtn.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		electionBtn.setBackground(new Color(0, 128, 128));
		electionBtn.setPreferredSize(new Dimension(89, 50));
		electionBtn.addActionListener(new ElectionButtonActions());
		mainElectionPanel.add(electionBtn, BorderLayout.SOUTH);
		
		JScrollPane electionScroll = new JScrollPane();
		mainElectionPanel.add(electionScroll, BorderLayout.CENTER);
		electionScroll.getVerticalScrollBar().setPreferredSize(new Dimension());
		electionScroll.getVerticalScrollBar().setUnitIncrement(15);
		JLabel electionlbl = new JLabel("MGM ELECTIONS");
		electionlbl.setBackground(new Color(0, 128, 128));
		electionlbl.setFont(new Font("Times New Roman", Font.BOLD, 30));
		electionlbl.setForeground(new Color(0, 0, 0));
		electionlbl.setHorizontalAlignment(SwingConstants.CENTER);
		electionlbl.setOpaque(true);
		electionlbl.setPreferredSize(new Dimension(46, 50));
		electionScroll.setColumnHeaderView(electionlbl);
		
		electionPanel = new JPanel();
		electionScroll.setViewportView(electionPanel);
		electionPanel.setLayout(new BoxLayout(electionPanel, BoxLayout.Y_AXIS));
		List<Election> elections=null;
		try {
			elections= ElectionApplication.getContext().getBean(ElectionDaoImp.class,"electiondaobean").getElections();
			for (Election election : elections) {
				ElectionPanel elecPanel=new ElectionPanel(election);
				electionPanel.add(elecPanel);
			}
			  
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}

	private class VoterListCellRenderer  implements ListCellRenderer<StudentPanel>{
	
		@Override
		public Component getListCellRendererComponent(JList<? extends StudentPanel> list, StudentPanel value, int index,
				boolean isSelected, boolean cellHasFocus) {
			// TODO Auto-generated method stub
			if (isSelected) {
				value.setBackground(new Color(30, 144, 255));
			}
			else {
				value.setBackground(new Color(152, 251, 152));
			}
			setPreferredSize(new Dimension(400,250));
			return value;
		}
	}


}
