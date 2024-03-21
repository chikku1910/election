package org.mgm.elector.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.mgm.elector.gui.DesignationBulider;
import org.mgm.elector.gui.ElectionApplication;
import org.mgm.elector.gui.ElectionBuilder;
import org.mgm.elector.gui.MGMElectionPanel;
import org.mgm.elector.model.Election;
import org.mgm.elector.model.ElectionDaoImp;
import org.springframework.context.ApplicationContext;

public class ElectionActions implements ActionListener {

	private Election election;
	private JPanel designationPanel;
	private JPanel panel;
	
	public ElectionActions(Election election,JPanel panel) {
		this.election = election;
		this.panel=panel;
	}

	public ElectionActions(JPanel designationPanel,Election election) {
		// TODO Auto-generated constructor stub
		this.designationPanel=designationPanel;
		this.election = election;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		switch (e.getActionCommand()) {
			case "designation":createDesignation();
				break;
			case "delete":deleteElection();
				break;
			case "edit":editElection();
				break;
			default:
				break;
		}
	}

	private void editElection() {
		// TODO Auto-generated method stub
		ElectionBuilder electionBuilder=null;
		try {
			electionBuilder = new ElectionBuilder(election);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		electionBuilder.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		electionBuilder.setVisible(true);
	}

	private void deleteElection() {
		// TODO Auto-generated method stub
		int decision=	JOptionPane.showInternalConfirmDialog(null, "Confirm deletion of the student", "CONFIRMATION",
				JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
	if (decision==JOptionPane.YES_OPTION) {
		
		try {
			ElectionApplication.getContext().getBean(ElectionDaoImp.class,"electiondaobean")
			.deleteElection(election.getElectionId());
			ApplicationContext context=ElectionApplication.getContext();
			ElectionApplication	window =context.getBean(ElectionApplication.class,"window");
			MGMElectionPanel mgmElectionPanel=context.getBean(MGMElectionPanel.class,"mgmelectionpanel");
			mgmElectionPanel.getElectionPanel().remove(this.panel);
			mgmElectionPanel.getElectionPanel().revalidate();
			mgmElectionPanel.getElectionPanel().repaint();
			JFrame frame=window.getFrame();
			JPanel panel =window.getPanel();
			frame.remove(panel);
			window.setPanel(mgmElectionPanel);
			frame.revalidate();
			frame.repaint();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Election deletion failed!", "FAILED", JOptionPane.WARNING_MESSAGE);
		}
		
		
		}
	}

	
	private void createDesignation() {
		// TODO Auto-generated method stub
		try {
			DesignationBulider dialog = new DesignationBulider(designationPanel,election);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
