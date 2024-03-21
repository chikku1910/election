package org.mgm.elector.gui;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.border.EmptyBorder;

import org.mgm.elector.model.Nominee;

import java.awt.Color;

public class NomineePanel extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4601358225829376237L;
	private Nominee nominee;
	
	
	/**
	 * Create the panel.
	 * @param nominee 
	 */
	public NomineePanel(Nominee nominee) {
		this.nominee=nominee;
		setBackground(new Color(46, 139, 87));
		setLayout(new BorderLayout());
		setToolTipText("keltron");
		JPanel symbol = new JPanel(){
			/**
			 * 
			 */
			private static final long serialVersionUID = -4157072545558422527L;

			@Override
    		protected void paintComponent(Graphics g) {
    			// TODO Auto-generated method stub
    			super.paintComponent(g);
    			Image photo=null;
    			try {
    				photo=ImageIO
    						.read(new ByteArrayInputStream(nominee.getSymbol()));
    						
    			} catch (Exception e) {
    				e.printStackTrace();
    			}
    			g.drawImage(photo, (this.getWidth()/2)-15, 0,30,25, null);
    		}
		};
		symbol.setOpaque(false);
		symbol.setPreferredSize(new Dimension(150,25));
		add(symbol, BorderLayout.SOUTH);
		
		
		
		JButton photo = new JButton(){
			/**
			 * 
			 */
			private static final long serialVersionUID = 9076797295131366784L;

			@Override
    		protected void paintComponent(Graphics g) {
    			// TODO Auto-generated method stub
    			super.paintComponent(g);
    			Image photo=null;
    			try {
    				//URL url = getClass().getResource("/resources/keltronicon.jpg");
    				photo=ImageIO
    						.read(new ByteArrayInputStream(nominee.getNominee().getPhoto()));
    						
    			} catch (IOException e) {
    				e.printStackTrace();
    			}
    			g.drawImage(photo, 0,0,this.getWidth(),this.getHeight(), null);
    		}
		};
		photo.setFocusPainted(false);
		photo.setBorderPainted(false);
		photo.setBorder(new EmptyBorder(0, 0, 0, 0));
		photo.setPreferredSize(new Dimension( 150,142));
		add(photo, BorderLayout.NORTH);
		photo.addActionListener(this);
		JLabel name=new JLabel(nominee.getNominee()!=null?nominee.getNominee().getStudentName():"");
		name.setForeground(new Color(240, 248, 255));
		name.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		name.setHorizontalAlignment(SwingConstants.CENTER);
		name.setPreferredSize(new Dimension( 150,25));
		add(name,BorderLayout.CENTER);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		NomineeViewDialog nominee=null;
		try {
			nominee = new NomineeViewDialog(this.nominee);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		nominee.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		nominee.setVisible(true);
	}

}
