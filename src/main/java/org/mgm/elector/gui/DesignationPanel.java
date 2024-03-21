package org.mgm.elector.gui;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.border.LineBorder;

import org.mgm.elector.model.Designation;
import org.mgm.elector.model.DesignationDaoImp;
import org.mgm.elector.model.Election;
import org.mgm.elector.model.Election.Status;
import org.mgm.elector.model.Nominee;
import org.mgm.elector.model.NomineeDaoImp;


import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;


import javax.swing.JDialog;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import javax.swing.JScrollPane;
import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class DesignationPanel extends JPanel  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2895009322174378820L;
	private JPanel nomineesPanel;

	/**
	 * Create the panel.
	 * @param designation 
	 */
	public DesignationPanel(Designation designation,Election election) {
		JPopupMenu popupMenu=new JPopupMenu();
		JMenuItem delete=new JMenuItem("DELETE");
		delete.addActionListener(e->delete(this,designation));
		JMenuItem add= new JMenuItem("ADD NOMINEE");
		add.addActionListener(e->addNominee(designation));
		popupMenu.add(add);
		popupMenu.add(delete);
		if (election.getStatus()!=Status.VOTE) {
			
			add.setEnabled(false);
			delete.setEnabled(false);
		}
		popupMenu.setBackground(Color.WHITE);
		setComponentPopupMenu(popupMenu);
		setBorder(new LineBorder(new Color(0, 0, 0)));
		
		JLabel winnerLbl = new JLabel("WINNER : ");
		if (designation.getWinner()!=null) {
			winnerLbl.setText(winnerLbl.getText() + designation.getWinner().getNominee() != null
					? winnerLbl.getText()+ designation.getWinner().getNominee().getStudentName()
					: "");
		}
		winnerLbl.setBackground(new Color(245, 222, 179));
		winnerLbl.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JLabel numberLbl = new JLabel("NUMBER OF NOMINES :"+designation.getNomineeCount());
		numberLbl.setFont(new Font("Tahoma", Font.BOLD, 12));
		numberLbl.setBackground(new Color(245, 222, 179));
		
		JLabel postlbl = new JLabel("POST NAME : "+designation.getDesignationName());
		postlbl.setFont(new Font("Tahoma", Font.BOLD, 13));
		postlbl.setBackground(new Color(245, 222, 179));
		
		JScrollPane nomineeScroll = new JScrollPane();
		nomineeScroll.getHorizontalScrollBar().setPreferredSize(new Dimension());
		nomineeScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		
		nomineesPanel = new JPanel();
		nomineesPanel.setBackground(new Color(46, 139, 87));
		
		List<Nominee> nominees=null;
		try {
			nominees= ElectionApplication.getContext().getBean(NomineeDaoImp.class,"nomineedaobean")
					.getNominees(designation);
			for (Nominee nominee : nominees) {
				
				NomineePanel nomineePanel=new NomineePanel(nominee);
				nomineePanel.setPreferredSize(new Dimension(150,192));
				nomineesPanel.add(nomineePanel);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		nomineeScroll.setViewportView(nomineesPanel);
		
		JPanel imgPanel =  new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = -3391405074047775408L;

			@Override
    		protected void paintComponent(Graphics g) {
    			// TODO Auto-generated method stub
    			super.paintComponent(g);
    			Image photo=null;
    			try {
    				
    				if (designation.getWinner() != null) {
						photo = ImageIO.read(new ByteArrayInputStream(designation.getWinner().getNominee().getPhoto()));
								
					}
    				else {
    					photo = ImageIO.read( new FileInputStream("src/main/resource/48x48.png"));
					}
    						
    			} catch (IOException e) {
    				e.printStackTrace();
    			}
    			g.drawImage(photo, 0, 0,this.getWidth(),this.getHeight(), null);
    		}
		};
		imgPanel.setBackground(new Color(127, 255, 212));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(imgPanel, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(postlbl, GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
						.addComponent(numberLbl, GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
						.addComponent(winnerLbl, GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE))
					.addGap(47))
				.addComponent(nomineeScroll, GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(33)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(postlbl, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
							.addGap(31)
							.addComponent(numberLbl, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
							.addGap(34)
							.addComponent(winnerLbl, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
						.addComponent(imgPanel, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(nomineeScroll, GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

	}

	private void delete(DesignationPanel panel, Designation d) {
		// TODO delete from database
		try {
			
			ElectionApplication.getContext().getBean(DesignationDaoImp.class,"designationdaobean")
			.deleteDesignation(d);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Container parent=panel.getParent();
		parent.remove(panel);
		parent.getParent().repaint();
		parent.getParent().revalidate();
	}

	private void addNominee(Designation designation) {
		
		try {
			NomineeDialog dialog = new NomineeDialog(designation,nomineesPanel);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
