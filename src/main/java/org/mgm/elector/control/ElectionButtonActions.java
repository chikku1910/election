package org.mgm.elector.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.mgm.elector.gui.ElectionApplication;
import org.mgm.elector.gui.ElectionBuilder;
import org.mgm.elector.gui.ElectionViewPanel;
import org.mgm.elector.gui.MGMElectionPanel;
import org.mgm.elector.gui.StudentBuilder;
import org.mgm.elector.gui.StudentPanel;
import org.mgm.elector.gui.VotingPanel;
import org.mgm.elector.gui.WinnerPanel;
import org.mgm.elector.model.Designation;
import org.mgm.elector.model.DesignationDaoImp;
import org.mgm.elector.model.Election;
import org.mgm.elector.model.ElectionDaoImp;
import org.mgm.elector.model.Election.Status;
import org.mgm.elector.model.Nominee;
import org.mgm.elector.model.NomineeDaoImp;

import org.mgm.elector.model.StudentDAOImp;
import org.springframework.context.ApplicationContext;

public class ElectionButtonActions implements ActionListener {
	private ApplicationContext context;
	private ElectionApplication window;
	private JFrame frame;
	private JList<StudentPanel> list;
	private Election election;
	private JPanel panel;
	public ElectionButtonActions() {
		
	}
	public ElectionButtonActions(Election election,JPanel panel) {
		this.election=election;
		this.panel=panel;
	}
	public ElectionButtonActions(JList<StudentPanel> list) {
		this.list = list;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		context=ElectionApplication.getContext();
		window =context.getBean(ElectionApplication.class,"window");
		frame=window.getFrame();
		switch (e.getActionCommand()) {
		case "register":viewStudentBuilder();
			break;
		case "count":findResult();
			break;
		case "vote":loadVotePanel();
			break;
		case "result":viewResult();
			break;
		case "create election":registerAction();
			break;
		case "view":viewElection();
			break;
		case "delete":deletestudent();
			break;
		default:
			break;
		}

	}
	
	private void viewResult() {
		// TODO Auto-generated method stub
		WinnerPanel panel=context.getBean(WinnerPanel.class,"winners");
		panel.setElection(election);
		frame.remove(window.getPanel());
		window.setPanel(panel);
		frame.revalidate();
		frame.repaint();	
	}
	private void findResult() {
		// TODO Auto-generated method stub

		try {
			List<Designation> designations= ElectionApplication.getContext().getBean(DesignationDaoImp.class,"designationdaobean")
			.getAllDesignation(election);
			List<Nominee> nominees=null;
			for (Designation designation : designations) {
				nominees=ElectionApplication.getContext().getBean(NomineeDaoImp.class,"nomineedaobean").getNominees(designation);
				Collections.sort(nominees, (n1,n2)->((Integer)n2.getVote()).compareTo(n1.getVote()));
				designation.setWinner(nominees.isEmpty()?null :nominees.get(0));
				ElectionApplication.getContext().getBean(DesignationDaoImp.class,"designationdaobean").adddesignation(designation);
			}
			election.setStatus(Status.RESULT);
			ElectionApplication.getContext().getBean(ElectionDaoImp.class,"electiondaobean").persistElection(election);
			JOptionPane.showMessageDialog(null, "Election result Calculated successfully", "INFORMATION",JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Something wrong! Couldn't calculated result", "INFORMATION",JOptionPane.INFORMATION_MESSAGE);
		}
		
	}
	private void loadVotePanel() {
		// TODO Auto-generated method stub
		
		VotingPanel votePanel=context.getBean(VotingPanel.class,"votepanel");
		votePanel.setElection(election);
		frame.remove(window.getPanel());
		window.setPanel(votePanel);
		frame.revalidate();
		frame.repaint();
			
	}
	private void viewStudentBuilder() {
		// TODO Auto-generated method stub
		StudentBuilder builder=context.getBean(StudentBuilder.class,"builder");
		frame.remove(context.getBean(MGMElectionPanel.class,"mgmelectionpanel"));
		window.setPanel(builder);
		frame.revalidate();
		frame.repaint();	
	}
	private void deletestudent() {
		if(list.isSelectionEmpty()) {
			JOptionPane.showMessageDialog(null, "Select a student", "INFORMATION",JOptionPane.INFORMATION_MESSAGE);
		}
		else {
			int decision=	JOptionPane.showInternalConfirmDialog(null, "Confirm deletion of the student", "CONFIRMATION",
				JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
			if (decision==JOptionPane.YES_OPTION) {
					try {
						ElectionApplication.getContext().getBean(StudentDAOImp.class,"studentdaobean")
						.deleteStudent(list.getSelectedValue().getStudent().getStudentId());;

						((DefaultListModel<StudentPanel>)list.getModel()).removeElement(list.getSelectedValue());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		}	
		
	}
	
	private void registerAction() {
		ElectionBuilder electionBuilder=null;
		try {
			electionBuilder = new ElectionBuilder();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		electionBuilder.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		electionBuilder.setVisible(true);
	}
	private void viewElection() {
		ElectionViewPanel view=context.getBean(ElectionViewPanel.class,"view");
		view.setElection(election,panel);
		frame.remove(context.getBean(MGMElectionPanel.class,"mgmelectionpanel"));
		window.setPanel(view);
		frame.revalidate();
		frame.repaint();	
	}
	

}
