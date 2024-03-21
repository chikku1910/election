package org.mgm.elector.gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.border.LineBorder;

import org.mgm.elector.control.ElectionButtonActions;
import org.mgm.elector.model.Election;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.awt.Dimension;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class ElectionPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5805866881576286587L;

	/**
	 * Create the panel.
	 */
	public ElectionPanel(Election election) {
		ElectionButtonActions actions=new ElectionButtonActions(election,this);
		setPreferredSize(new Dimension(714, 250));
		setMinimumSize(new Dimension(150, 150));
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setForeground(Color.BLACK);
		setBackground(new Color(228, 228, 228));
		
		JButton viewBtn = new JButton("VIEW");
		viewBtn.setFocusPainted(false);
		viewBtn.setToolTipText("shows election");
		viewBtn.setName("view button");
		viewBtn.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		viewBtn.setActionCommand("view");
		viewBtn.setFont(new Font("Times New Roman", Font.BOLD, 30));
		viewBtn.setBackground(new Color(64, 224, 208));
		viewBtn.setForeground(Color.BLACK);
		viewBtn.addActionListener(actions);
		
		JLabel electionName = new JLabel(election.getElectionName());
		electionName.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 29));
		electionName.setBackground(Color.BLACK);
		
		SimpleDateFormat format=new SimpleDateFormat("dd MMMM YYYY");
		
		JLabel dateName = new JLabel(format.format(election.getElectionDate()));
		dateName.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		
		JButton resultBtn = new JButton();
		resultBtn.setFocusPainted(false);
		resultBtn.setForeground(new Color(0, 0, 0));
		resultBtn.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		resultBtn.setName("result button");
		resultBtn.setFont(new Font("Times New Roman", Font.BOLD, 30));
		resultBtn.setBackground(new Color(64, 224, 208));
		resultBtn.addActionListener(actions);
		
		switch (election.getStatus()) {
		case VOTE:
			resultBtn.setText("VOTE");
			resultBtn.setActionCommand("vote");
			break;
		case COUNT:
			resultBtn.setText("COUNT");
			resultBtn.setActionCommand("count");
			break;

		case RESULT:
			resultBtn.setText("RESULT");
			resultBtn.setActionCommand("result");
			break;

		default:
			resultBtn.setText("INVALID");
			resultBtn.setActionCommand("invalid");
			break;
		}
		
		JPanel logoPanel= new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = -4845837328058495375L;

			@Override
    		protected void paintComponent(Graphics g) {
    			// TODO Auto-generated method stub
    			super.paintComponent(g);
    			Image photo=null;
    			try {
    				//URL url = getClass().getResource("/resources/keltronicon.jpg");
    				photo=ImageIO
    						.read(new FileInputStream("src/main/resource/48x48.png"));
    						
    			} catch (IOException e) {
    				e.printStackTrace();
    			}
    			g.drawImage(photo, 0, 0,this.getWidth(),this.getHeight(), null);
    		}
		};
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(27)
					.addComponent(logoPanel, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(electionName, GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE)
							.addGap(19))
						.addComponent(dateName, GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE))
					.addGap(10)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(viewBtn, GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
						.addComponent(resultBtn, GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE))
					.addGap(9))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(56)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(logoPanel, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(electionName, GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
									.addGap(43)
									.addComponent(dateName, GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(viewBtn, GroupLayout.PREFERRED_SIZE, 36, Short.MAX_VALUE)
									.addGap(64)
									.addComponent(resultBtn, GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)))
							.addGap(56))))
		);
		setLayout(groupLayout);

	}
}
