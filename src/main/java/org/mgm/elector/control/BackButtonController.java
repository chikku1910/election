package org.mgm.elector.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.mgm.elector.gui.ElectionApplication;
import org.mgm.elector.gui.ElectionPanel;
import org.mgm.elector.gui.ElectionViewPanel;
import org.mgm.elector.gui.MGMElectionPanel;
import org.mgm.elector.gui.StudentBuilder;
import org.mgm.elector.gui.VotingPanel;
import org.mgm.elector.gui.WinnerPanel;
import org.mgm.elector.model.Election;
import org.mgm.elector.model.ElectionDaoImp;
import org.springframework.context.ApplicationContext;

public class BackButtonController implements ActionListener {
private ElectionApplication window;

	public BackButtonController(ElectionApplication window) {
	
	this.window = window;
}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JFrame frame=window.getFrame();
		ApplicationContext context=ElectionApplication.getContext();
		JPanel panel =window.getPanel();
		
		if (panel instanceof MGMElectionPanel) {
			
		}
		else if (panel instanceof ElectionViewPanel) {
			frame.remove(panel);
			MGMElectionPanel ePanel=context.getBean(MGMElectionPanel.class,"mgmelectionpanel");
			window.setPanel(ePanel);
			ePanel.getElectionPanel().removeAll();
			List<Election> elections=null;
			try {
				elections= ElectionApplication.getContext().getBean(ElectionDaoImp.class,"electiondaobean").getElections();
				for (Election election : elections) {
					ElectionPanel elecPanel=new ElectionPanel(election);
					ePanel.getElectionPanel().add(elecPanel);
				}
				ePanel.getElectionPanel().revalidate();
				ePanel.getElectionPanel().repaint();
			} catch (Exception e1) {
				// TODO: handle exception
			}
		}
		else if (panel instanceof StudentBuilder) {
			frame.remove(panel);
			window.setPanel(context.getBean(MGMElectionPanel.class,"mgmelectionpanel"));
			
		}
		else if (panel instanceof VotingPanel) {
			frame.remove(panel);
			window.setPanel(context.getBean(MGMElectionPanel.class,"mgmelectionpanel"));
			
		}
		else if (panel instanceof WinnerPanel) {
			frame.remove(panel);
			window.setPanel(context.getBean(MGMElectionPanel.class,"mgmelectionpanel"));
		}
		
		frame.revalidate();
		frame.repaint();
	}

}
