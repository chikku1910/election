package org.mgm.elector.gui;

import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


import org.mgm.elector.control.BackButtonController;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.GenericApplicationContext;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

public class ElectionApplication {

	public static final File FILE = new File("src/main/resource/login.xml");
	private JFrame frame;
	private JPanel panel;
	private JButton backButton;
    public static  GenericApplicationContext CONTEXT=null;
    public static GenericApplicationContext getContext() {
		return CONTEXT;
		
	}
    private static void setContext() {
		CONTEXT=new GenericApplicationContext();
		new XmlBeanDefinitionReader(CONTEXT).loadBeanDefinitions("daoimpbeans.xml","electorguibeans.xml");
		CONTEXT.refresh();
   	}
	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		frame.add(panel,BorderLayout.CENTER);
		this.panel = panel;
	}

	public JFrame getFrame() {
		return frame;
	}
	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					setContext();
					ElectionApplication window = CONTEXT.getBean(ElectionApplication.class, "window");				
							window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws IOException 
	 * @wbp.parser.entryPoint
	 */
	public ElectionApplication() throws IOException {
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 */
	
	private void initialize() throws IOException {
		
		
		Rectangle size = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
		
		frame = new JFrame();
		frame.setBounds(0, 0, (int)size.getWidth(), (int)size.getHeight());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//URL url=getClass().getResource("/resource/download (1).jpg");
		FileInputStream url=new FileInputStream("src/main/resource/48x48.png");
		Image logo=ImageIO.read(url);
		frame.setIconImage(logo);
		frame.setTitle("ELECTORAL SYSTEM");
		
		JPanel headPanel=new JPanel();
		headPanel.setPreferredSize(new Dimension(frame.getWidth(),60));
		headPanel.setBackground(new Color(255, 182, 193));
		frame.getContentPane().add(headPanel,BorderLayout.NORTH);
		headPanel.setLayout(new BorderLayout(0, 0));
		
		backButton=new JButton() {
				/**
			 * 
			 */
			private static final long serialVersionUID = -2666345920949419554L;

				@Override
	    		protected void paintComponent(Graphics g) {
			// TODO Auto-generated method stub
			super.paintComponent(g);
			Image photo=null;
			try {
				//URL url = getClass().getResource("/resources/keltronicon.jpg");
				photo=ImageIO
						.read(new FileInputStream("src/main/resource/backimg.png"));
						
			} catch (IOException e) {
				e.printStackTrace();
			}
			g.drawImage(photo, 0, 0,this.getWidth(),this.getHeight(), null);
		}
		};
		backButton.setPreferredSize(new Dimension(60,30));
		backButton.setFocusPainted(false);
		backButton.setBorder(null);
		backButton.setVisible(false);
		backButton.addActionListener(new BackButtonController(this));
		headPanel.add(backButton,BorderLayout.WEST);
		
		
		JLabel mgmLbl = new JLabel("MGM POLYTECHNIC COLLEGE KILIMANOOR");
		mgmLbl.setIconTextGap(15);
		mgmLbl.setOpaque(true);
		mgmLbl.setIcon(new ImageIcon(logo));
		mgmLbl.setBackground(new Color(255, 182, 193));
		mgmLbl.setPreferredSize(new Dimension(frame.getWidth()-10,60));
		mgmLbl.setFont(new Font("Times New Roman", Font.BOLD, 45));
		mgmLbl.setHorizontalAlignment(SwingConstants.CENTER);
		headPanel.add(mgmLbl,BorderLayout.CENTER);
		
	    panel = new JPanel() {
	    		/**
			 * 
			 */
			private static final long serialVersionUID = 1737483388080378691L;

				@Override
	    		protected void paintComponent(Graphics g) {
	    			// TODO Auto-generated method stub
	    			super.paintComponent(g);
	    			Image backIcon=null;
	    			try {
	    				//URL url = getClass().getResource("/resources/keltronicon.jpg");
	    				backIcon=ImageIO
	    						.read(new FileInputStream("src/main/resource/college.jpg"));
	    			} catch (IOException e) {
	    				e.printStackTrace();
	    			}
	    			g.drawImage(backIcon, 0, 0,this.getWidth(),this.getHeight(), null);
	    		}
	    };
	    		
	    		
		FlowLayout flowLayout_1 = (FlowLayout) panel.getLayout();
		flowLayout_1.setVgap((int)size.getHeight()/4);
		flowLayout_1.setHgap((int)size.getWidth()/9);
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		
		JPanel loginPanel = new LoginPanel();
		loginPanel.setBackground(new Color(255, 192, 203,175));
		loginPanel.setPreferredSize(new Dimension(frame.getWidth()/4, frame.getHeight()/3));
		panel.add(loginPanel);
	}
	public void showBackButton() {
		// TODO Auto-generated method stub
		backButton.setVisible(true);
	}
	
	
}
