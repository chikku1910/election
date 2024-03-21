package org.mgm.elector.control;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.mgm.elector.gui.ElectionApplication;
import org.mgm.elector.gui.MGMElectionPanel;
import org.springframework.context.ApplicationContext;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class LoginButtonsAction implements ActionListener {
	private String userName;
	private String password;
	private Document xmlDocument;
	public LoginButtonsAction() {
		try {
			readXML();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void readXML() throws SAXException, IOException, ParserConfigurationException {
		// TODO Auto-generated method stub
		xmlDocument=DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(ElectionApplication.FILE);
		xmlDocument.getDocumentElement().normalize();
		userName= xmlDocument.getElementsByTagName("username").item(0).getTextContent();
		password= xmlDocument.getElementsByTagName("password").item(0).getTextContent();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("login")) {
			login();
			}
		else if (e.getActionCommand().equals("change password")) {
			new	ResetDialogBox();

			}
		}
	
	private void login() {
		ApplicationContext context=ElectionApplication.getContext();
		ElectionApplication window=context.getBean(ElectionApplication.class,"window");
		JFrame frame=window.getFrame();
		if(context.getBean(JTextField.class, "username").getText().equals(userName)
				&&new String( context.getBean(JPasswordField.class, "password").getPassword()).equals(password)) {
			frame.remove(window.getPanel());
			window.setPanel(context.getBean(MGMElectionPanel.class,"mgmelectionpanel"));
			frame.revalidate();
			frame.repaint();
			window.showBackButton();
		}
		else {
			JOptionPane.showMessageDialog(window.getPanel(),"Invalid username or password","INVALID INPUT",
					JOptionPane.WARNING_MESSAGE);
		}
	}
	
	private void changePassword( String oldPassword, String newPassword) throws TransformerException {
		if (oldPassword.equals(password)) {
			password=newPassword;
			xmlDocument.getElementsByTagName("password").item(0).setTextContent(password);
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(xmlDocument);
            StreamResult result = new StreamResult(ElectionApplication.FILE);
            transformer.transform(source, result);
			
		}
		else {
			JOptionPane.showMessageDialog(null, "password not changed,check the old Password","ERROR",
					JOptionPane.WARNING_MESSAGE);
		}
	}
	
	@SuppressWarnings("serial")
	private class ResetDialogBox extends JDialog implements ActionListener{
		private final JPanel contentPanel = new JPanel();
		private JTextField oldPasswordTxt;
		private JTextField newPasswordTxt;
		
		private ResetDialogBox() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);	
		
		setModal(true);
		setTitle("RESET PASSWORD");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel label2 = new JLabel("Old Password :  ");
		label2.setHorizontalAlignment(SwingConstants.TRAILING);
		label2.setFont(new Font("Times New Roman", Font.BOLD, 20));
		label2.setBounds(38, 25, 154, 31);
		contentPanel.add(label2);
		
		JLabel label1 = new JLabel("New Pasword :  ");
		label1.setHorizontalAlignment(SwingConstants.TRAILING);
		label1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		label1.setBounds(38, 98, 154, 31);
		contentPanel.add(label1);
		
		oldPasswordTxt = new JTextField();
		oldPasswordTxt.setBounds(202, 30, 158, 26);
		contentPanel.add(oldPasswordTxt);
		oldPasswordTxt.setColumns(10);
		
		newPasswordTxt = new JTextField();
		newPasswordTxt.setBounds(202, 98, 158, 25);
		contentPanel.add(newPasswordTxt);
		newPasswordTxt.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("RESET");
				okButton.setFocusPainted(false);
				okButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
				okButton.setActionCommand("reset");
				buttonPane.add(okButton);
				okButton.addActionListener(this);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("CANCEL");
				cancelButton.setFocusPainted(false);
				cancelButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
				cancelButton.setActionCommand("cancel");
				cancelButton.addActionListener(this);
				buttonPane.add(cancelButton);
			}
		}
		setVisible(true);
	}
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getActionCommand().equals("reset")) {
				try {
					changePassword(oldPasswordTxt.getText(),newPasswordTxt.getText());
				} catch (TransformerException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();
			} else if(e.getActionCommand().equals("cancel")){

				dispose();
			}	
			
		}
		
	}

}
