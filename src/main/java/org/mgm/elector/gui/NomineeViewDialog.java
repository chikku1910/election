package org.mgm.elector.gui;

import javax.swing.JPanel;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import org.mgm.elector.model.Nominee;
import org.mgm.elector.model.NomineeDaoImp;

import java.awt.Color;

public class NomineeViewDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8699350704948170495L;
	private final JPanel contentPanel = new JPanel();
	/**
	 * Create the panel.
	 * @param nominee 
	 * @throws IOException 
	 */
	public NomineeViewDialog(Nominee nominee) throws IOException {
		setTitle("NOMINEE INFORMATION");
		//URL url=getClass().getResource("/resource/download (1).jpg");
		FileInputStream url=new FileInputStream("src/main/resource/48x48.png");
		Image logo=ImageIO.read(url);
		setIconImage(logo);
		setBounds(100, 100, 525, 383);
		setModal(true);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel nameLbl = new JLabel("NAME :  "+nominee.getNominee().getStudentName());
		nameLbl.setHorizontalAlignment(SwingConstants.LEFT);
		nameLbl.setFont(new Font("Times New Roman", Font.BOLD, 15));
		nameLbl.setBounds(172, 72, 311, 28);
		contentPanel.add(nameLbl);
		
		JLabel depLbl = new JLabel("DEPARTMENT :  "+nominee.getNominee().getDepartment().getDeptName());
		depLbl.setFont(new Font("Times New Roman", Font.BOLD, 15));
		depLbl.setHorizontalAlignment(SwingConstants.LEFT);
		depLbl.setBounds(172, 104, 311, 31);
		contentPanel.add(depLbl);
		
		JLabel postLbl = new JLabel("POST :  "+NomineeDialog.setPost(nominee.getDesignation().getPost()));
		postLbl.setHorizontalAlignment(SwingConstants.LEFT);
		postLbl.setFont(new Font("Times New Roman", Font.BOLD, 15));
		postLbl.setBounds(172, 146, 311, 28);
		contentPanel.add(postLbl);
		
		JLabel voteLbl = new JLabel("VOTE COUNT :  "+nominee.getVote());
		voteLbl.setFont(new Font("Times New Roman", Font.BOLD, 15));
		voteLbl.setBounds(172, 185, 311, 28);
		contentPanel.add(voteLbl);
		
		JPanel photoPanel =new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = -7714639766891082884L;

			@Override
			public void paintComponent(Graphics g) {
				// TODO Auto-generated method stub
			
			super.paintComponent(g);
			Image photo=null;
			try {
				
				photo=ImageIO
						.read(new ByteArrayInputStream(nominee.getNominee().getPhoto()));
						
			} catch (IOException e) {
				e.printStackTrace();
			}catch (NullPointerException e) {
				// TODO: handle exception
			}
			g.drawImage(photo, 0, 0,this.getWidth(),this.getHeight(), null);
		}
		};
		photoPanel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		photoPanel.setBounds(10, 72, 137, 141);
		contentPanel.add(photoPanel);
		
		JPanel symbolPanel = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = -7994994998236607401L;

			@Override
			public void paintComponent(Graphics g) {
				// TODO Auto-generated method stub
			
			super.paintComponent(g);
			Image photo=null;
			try {
				
				photo=ImageIO
						.read(new ByteArrayInputStream(nominee.getSymbol()));
						
			} catch (IOException e) {
				e.printStackTrace();
			}catch (NullPointerException e) {
				// TODO: handle exception
			}
			g.drawImage(photo, 0, 0,this.getWidth(),this.getHeight(), null);
		}
		};
		symbolPanel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		symbolPanel.setBounds(10, 236, 67, 55);
		contentPanel.add(symbolPanel);
		
		JLabel head = new JLabel("NOMINEE");
		head.setBackground(new Color(152, 251, 152));
		head.setOpaque(true);
		head.setHorizontalAlignment(SwingConstants.CENTER);
		head.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 30));
		head.setBounds(0, 0, 509, 36);
		contentPanel.add(head);
		{
			JPanel buttonPane = new JPanel();
			
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("DELETE");
				okButton.setBackground(new Color(152, 251, 152));
				okButton.setFocusPainted(false);
				okButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
				okButton.setActionCommand("OK");
				okButton.addActionListener(e->deleteNominee(nominee));
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton btnEdit = new JButton("EDIT");
				btnEdit.setBackground(new Color(152, 251, 152));
				btnEdit.setFocusPainted(false);
				btnEdit.setFont(new Font("Times New Roman", Font.BOLD, 20));
				btnEdit.setActionCommand("OK");
				btnEdit.addActionListener(e->editNominee(nominee));
				buttonPane.add(btnEdit);
				
			}
			{
				JButton cancelButton = new JButton("CANCEL");
				cancelButton.setBackground(new Color(152, 251, 152));
				cancelButton.setFocusPainted(false);
				cancelButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
				cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener(e->dispose());
				buttonPane.add(cancelButton);
			}
		}
	}
	
	private void editNominee(Nominee nominee) {
		// TODO Auto-generated method stub
		try {
			NomineeDialog dialog = new NomineeDialog(nominee);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void deleteNominee(Nominee nominee) {
		// TODO Auto-generated method stub
		try {
			ElectionApplication.getContext().getBean(NomineeDaoImp.class,"nomineedaobean").deleteNominee(nominee);
			dispose();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Nominee couldn't delete!", "FAILED", JOptionPane.WARNING_MESSAGE);
		}
	}
}
