package org.mgm.elector.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JCheckBox;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.mgm.elector.model.Designation;
import org.mgm.elector.model.Designation.Post;
import org.mgm.elector.model.DesignationDaoImp;
import org.mgm.elector.model.Election;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class DesignationBulider extends JDialog implements ItemListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6529215654202827196L;
	private final JPanel contentPanel = new JPanel();
	private List<JCheckBox> designations=new ArrayList<JCheckBox>();
	private JPanel designationPanel;
	private Election election;
	private Document xmlDocument;

	
	/**
	 * Create the dialog.
	 */
	public DesignationBulider(JPanel designationPanel,Election election) {
		setResizable(false);
		this.designationPanel=designationPanel;
		this.election=election;
		setTitle("DESIGNATIONS");
		setBounds(100, 100, 1109, 467);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JLabel headLbl = new JLabel("SELECT DESIGNATION");
			headLbl.setHorizontalAlignment(SwingConstants.CENTER);
			headLbl.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
			headLbl.setPreferredSize(new Dimension(108, 40));
			headLbl.setOpaque(true);
			headLbl.setBackground(new Color(152, 251, 152));
			contentPanel.add(headLbl, BorderLayout.NORTH);
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(new GridLayout(0, 2, 0, 0));
			{
				JCheckBox chairman = new JCheckBox("CHAIRMAN");
				chairman.setFocusPainted(false);
				chairman.setSelected(true);
				chairman. setForeground(new Color(0, 128, 0));
				chairman.setActionCommand("chairman");
				chairman.addItemListener(this);
				chairman.setFont(new Font("Times New Roman", Font.PLAIN, 15));
				panel.add(chairman);
				designations.add(chairman);
			
				JCheckBox VC = new JCheckBox("VICE CHAIRMAN(LADY)");
				VC.setFocusPainted(false);
				VC.setSelected(true);
				VC.setFont(new Font("Times New Roman", Font.PLAIN, 15));
				VC. setForeground(new Color(0, 128, 0));
				VC.setActionCommand("VC");
				VC.addItemListener(this);
				panel.add(VC);
				designations.add(VC);
			
				JCheckBox GS = new JCheckBox("GENERAL SECRETARY");
				GS.setFocusPainted(false);
				GS.setSelected(true);
				GS.setFont(new Font("Times New Roman", Font.PLAIN, 15));
				GS. setForeground(new Color(0, 128, 0));
				GS.setActionCommand("GS");
				GS.addItemListener(this);
				panel.add(GS);
				designations.add(GS);
			
				JCheckBox UUC1 = new JCheckBox("UNIVERSITY UNION COUNCILER 1");
				UUC1.setFocusPainted(false);
				UUC1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
				UUC1. setForeground(new Color(220, 20, 60));
				UUC1.setActionCommand("UUC1");
				UUC1.addItemListener(this);
				panel.add(UUC1);
				designations.add(UUC1);
			
				JCheckBox UUC2 = new JCheckBox("UNIVERSITY UNION COUNCILER 2");
				UUC2.setFocusPainted(false);
				UUC2.setFont(new Font("Times New Roman", Font.PLAIN, 15));
				UUC2. setForeground(new Color(220, 20, 60));
				UUC2.setActionCommand("UUC2");
				UUC2.addItemListener(this);
				panel.add(UUC2);
				designations.add(UUC2);
			
				JCheckBox ME = new JCheckBox("MAGAZINE EDITOR");
				ME.setFocusPainted(false);
				ME.setFont(new Font("Times New Roman", Font.PLAIN, 15));
				ME. setForeground(new Color(220, 20, 60));
				ME.setActionCommand("ME");
				ME.addItemListener(this);
				panel.add(ME);
				designations.add(ME);
			
				JCheckBox ACS = new JCheckBox("ARTS CLUB SECRETARY");
				ACS.setFocusPainted(false);
				ACS.setFont(new Font("Times New Roman", Font.PLAIN, 15));
				ACS. setForeground(new Color(220, 20, 60));
				ACS.setActionCommand("ACS");
				ACS.addItemListener(this);
				panel.add(ACS);
				designations.add(ACS);
			
				JCheckBox WR1 = new JCheckBox("WOMEN REPRESENTATIVE 1");
				WR1.setFocusPainted(false);
				WR1.setSelected(true);
				WR1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
				WR1. setForeground(new Color(0, 128, 0));
				WR1.setActionCommand("WR1");
				WR1.addItemListener(this);
				panel.add(WR1);
				designations.add(WR1);
			
				JCheckBox WR2 = new JCheckBox("WOMEN REPRESENTATIVE 2");
				WR2.setFocusPainted(false);
				WR2.setSelected(true);
				WR2.setFont(new Font("Times New Roman", Font.PLAIN, 15));
				WR2. setForeground(new Color(0, 128, 0));
				WR2.setActionCommand("WR2");
				WR2.addItemListener(this);
				panel.add(WR2);
				designations.add(WR2);
			
				JCheckBox Y1R = new JCheckBox("FIRST YEAR REPRESENTATIVE");
				Y1R.setFocusPainted(false);
				Y1R.setSelected(true);
				Y1R.setFont(new Font("Times New Roman", Font.PLAIN, 15));
				Y1R. setForeground(new Color(0, 128, 0));
				Y1R.setActionCommand("Y1R");
				Y1R.addItemListener(this);
				panel.add(Y1R);
				designations.add(Y1R);
			
				JCheckBox Y2R = new JCheckBox("SECOND YEAR REPRESENTATIVE");
				Y2R.setFocusPainted(false);
				Y2R.setSelected(true);
				Y2R.setFont(new Font("Times New Roman", Font.PLAIN, 15));
				Y2R. setForeground(new Color(0, 128, 0));
				Y2R.setActionCommand("Y2R");
				Y2R.addItemListener(this);
				panel.add(Y2R);
				designations.add(Y2R);
			
				JCheckBox Y3R = new JCheckBox("THIRD YEAR REPRESENTATIVE");
				Y3R.setFocusPainted(false);
				Y3R.setSelected(true);
				Y3R.setFont(new Font("Times New Roman", Font.PLAIN, 15));
				Y3R. setForeground(new Color(0, 128, 0));
				Y3R.setActionCommand("Y3R");
				Y3R.addItemListener(this);
				panel.add(Y3R);
				designations.add(Y3R);
			
				JCheckBox DRcivil = new JCheckBox("DEPARTMENT REPRESENTATIVE - CIVIL");
				DRcivil.setFocusPainted(false);
				DRcivil.setSelected(true);
				DRcivil.setFont(new Font("Times New Roman", Font.PLAIN, 15));
				DRcivil. setForeground(new Color(0, 128, 0));
				DRcivil.setActionCommand("DRCIVIL");
				DRcivil.addItemListener(this);
				panel.add(DRcivil);
				designations.add(DRcivil);
			
				JCheckBox DRmech = new JCheckBox("DEPARTMENT REPRESENTATIVE - MECHANICAL");
				DRmech.setFocusPainted(false);
				DRmech.setSelected(true);
				DRmech.setFont(new Font("Times New Roman", Font.PLAIN, 15));
				DRmech. setForeground(new Color(0, 128, 0));
				DRmech.setActionCommand("DRMECH");
				DRmech.addItemListener(this);
				panel.add(DRmech);
				designations.add(DRmech);
			
				JCheckBox DRCT = new JCheckBox("DEPARTMENT REPRESENTATIVE - CT");
				DRCT.setFocusPainted(false);
				DRCT.setSelected(true);
				DRCT.setFont(new Font("Times New Roman", Font.PLAIN, 15));
				DRCT. setForeground(new Color(0, 128, 0));
				DRCT.setActionCommand("DRCT");
				DRCT.addItemListener(this);
				panel.add(DRCT);
				designations.add(DRCT);
		
				JCheckBox DRAI = new JCheckBox("DEPARTMENT REPRESENTATIVE - AI");
				DRAI.setFocusPainted(false);
				DRAI.setSelected(true);
				DRAI.setFont(new Font("Times New Roman", Font.PLAIN, 15));
				DRAI. setForeground(new Color(0, 128, 0));
				DRAI.setActionCommand("DRAI");
				DRAI.addItemListener(this);
				panel.add(DRAI);
				designations.add(DRAI);
			
				JCheckBox DREC = new JCheckBox("DEPARTMENT REPRESENTATIVE - EC");
				DREC.setFocusPainted(false);
				DREC.setSelected(true);
				DREC.setFont(new Font("Times New Roman", Font.PLAIN, 15));
				DREC. setForeground(new Color(0, 128, 0));
				DREC.setActionCommand("DREC");
				DREC.addItemListener(this);
				panel.add(DREC);
				designations.add(DREC);
			
			
				JCheckBox DRAUTO = new JCheckBox("DEPARTMENT REPRESENTATIVE - AUTO");
				DRAUTO.setFocusPainted(false);
				DRAUTO.setSelected(true);
				DRAUTO.setFont(new Font("Times New Roman", Font.PLAIN, 15));
				DRAUTO. setForeground(new Color(0, 128, 0));
				DRAUTO.setActionCommand("DRAUTO");
				DRAUTO.addItemListener(this);
				panel.add(DRAUTO);
				designations.add(DRAUTO);
				List<Designation> designationsdb=ElectionApplication.getContext().getBean(DesignationDaoImp.class,"designationdaobean")
						.getAllDesignation(election);
				for (Designation designation : designationsdb) {
					switch (designation.getPost()) {
					case CHAIRMAN:
						chairman.setSelected(false);
						chairman.setEnabled(false);
						break;
					case VICE_CHAIRMAN:
						VC.setSelected(false);
						VC.setEnabled(false);
						break;
					case GENERAL_SECRETARY:
						GS.setSelected(false);
						GS.setEnabled(false);
						break;
					case MAGAZINE_EDITOR:
						ME.setSelected(false);
						ME.setEnabled(false);
						break;
					case WOMEN_REPRESENTATIVE1:
						WR1.setSelected(false);
						WR1.setEnabled(false);
						break;
					case WOMEN_REPRESENTATIVE2:
						WR2.setSelected(false);
						WR2.setEnabled(false);
						break;
					case UNIVERSITY_UNION_COUNCILER1:
						UUC1.setSelected(false);
						UUC1.setEnabled(false);
						break;
					case UNIVERSITY_UNION_COUNCILER2:
						UUC2.setSelected(false);
						UUC2.setEnabled(false);
						break;
					case REPRESENTATIVE_Y1:
						Y1R.setSelected(false);
						Y1R.setEnabled(false);
						break;
					case REPRESENTATIVE_Y2:
						Y2R.setSelected(false);
						Y2R.setEnabled(false);
						break;
					case REPRESENTATIVE_Y3:
						Y3R.setSelected(false);
						Y3R.setEnabled(false);
						break;
					case CIVIL_REPRESENTATIVE:
						DRcivil.setSelected(false);
						DRcivil.setEnabled(false);
						break;
					case MECH_REPRESENTATIVE:
						DRmech.setSelected(false);
						DRmech.setEnabled(false);
						break;
					case CT_REPRESENTATIVE:
						DRCT.setSelected(false);
						DRCT.setEnabled(false);
						break;
					case EC_REPRESENTATIVE:
						DREC.setSelected(false);
						DREC.setEnabled(false);
						break;
					case AI_REPRESENTATIVE:
						DRAI.setSelected(false);
						DRAI.setEnabled(false);		
						break;
					case AUTO_REPRESENTATIVE:
						DRAUTO.setSelected(false);
						DRAUTO.setEnabled(false);
						break;
					case ARTS_CLUB_SECRETARY:
						ACS.setSelected(false);
						ACS.setEnabled(false);
						break;
					default:
						break;
					}
				}
		}
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("CREATE");
				okButton.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
				okButton.setFocusPainted(false);
				okButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
				okButton.setActionCommand("OK");
				okButton.addActionListener(e->createdesignations());
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("CANCEL");
				cancelButton.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
				cancelButton.setFocusPainted(false);
				cancelButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
				cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener(e->dispose());
				buttonPane.add(cancelButton);
			}
		}
	}
	}

	
	private void createdesignations() {
		// TODO Auto-generated method stub
		try {
			xmlDocument=DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(ElectionApplication.FILE);
		} catch (SAXException | IOException | ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		xmlDocument.getDocumentElement().normalize();
		for (JCheckBox jCheckBox : designations) {
			
			if (jCheckBox.isSelected()) {
				Designation designation=new Designation();
				String id=Integer.parseInt(setData(designation,jCheckBox))+1+"";
				
				designation.setElection(election);
				designation.setWinner(null);
				ElectionApplication.getContext().getBean(DesignationDaoImp.class,"designationdaobean")
				.adddesignation(designation);
				DesignationPanel desiPanel=new DesignationPanel(designation,election);
				desiPanel.setPreferredSize(new Dimension(300,400));
				designationPanel.add(desiPanel);
				xmlDocument.getElementsByTagName("designationid").item(0).setTextContent(id);
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
	            Transformer transformer;
				try {
					transformer = transformerFactory.newTransformer();
					DOMSource source = new DOMSource(xmlDocument);
		            StreamResult result = new StreamResult(ElectionApplication.FILE);
		            transformer.transform(source, result);

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
	            			}	
		}
		designationPanel.revalidate();
		designationPanel.repaint();
		dispose();
	}

	private String setData(Designation designation,JCheckBox checkBox) {
		
		String id=xmlDocument.getElementsByTagName("designationid").item(0).getTextContent();
		switch (checkBox.getActionCommand()) {
		case "chairman":
			designation.setPost(Post.CHAIRMAN);
			designation.setDesignationName("CHAIRMAN");
			designation.setDesignationId("P01D0"+id);
			break;
		case "VC":
			designation.setPost(Post.VICE_CHAIRMAN);
			designation.setDesignationName("VICE CHAIRMAN");
			designation.setDesignationId("P02D0"+id);
			break;
		case "GS":
			designation.setPost(Post.GENERAL_SECRETARY);
			designation.setDesignationName("GENERAL SECRETARY");
			designation.setDesignationId("P03D0"+id);
			break;
		case "ME":
			designation.setPost(Post.MAGAZINE_EDITOR);
			designation.setDesignationName("MAGAZINE_EDITOR");
			designation.setDesignationId("P04D0"+id);
			break;
		case "WR1":
			designation.setPost(Post.WOMEN_REPRESENTATIVE1);
			designation.setDesignationName("WOMEN REPRESENTATIVE 1");
			designation.setDesignationId("P05D0"+id);
			break;
		case "WR2":
			designation.setPost(Post.WOMEN_REPRESENTATIVE2);
			designation.setDesignationName("WOMEN REPRESENTATIVE 2");
			designation.setDesignationId("P06D0"+id);
			break;
		case "UUC1":
			designation.setPost(Post.UNIVERSITY_UNION_COUNCILER1);
			designation.setDesignationName("UNIVERSITY UNION COUNCILER 1");
			designation.setDesignationId("P07D0"+id);
			break;
		case "UUC2":
			designation.setPost(Post.UNIVERSITY_UNION_COUNCILER2);
			designation.setDesignationName("UNIVERSITY UNION COUNCILER 2");
			designation.setDesignationId("P08D0"+id);
			break;
		case "Y1R":
			designation.setPost(Post.REPRESENTATIVE_Y1);
			designation.setDesignationName("FIRST YEAR REPRESENTATIVE");
			designation.setDesignationId("P09D0"+id);
			break;
		case "Y2R":
			designation.setPost(Post.REPRESENTATIVE_Y2);
			designation.setDesignationName("SECOND YEAR REPRESENTATIVE");
			designation.setDesignationId("P10D0"+id);
			break;
		case "Y3R":
			designation.setPost(Post.REPRESENTATIVE_Y3);
			designation.setDesignationName("THIRD YEAR REPRESENTATIVE");
			designation.setDesignationId("P11D0"+id);
			break;
		case "DRCIVIL":
			designation.setPost(Post.CIVIL_REPRESENTATIVE);
			designation.setDesignationName("DEPARTMENT REPRESENTATIVE CIVIL");
			designation.setDesignationId("P12D0"+id);
			break;
		case "DRMECH":
			designation.setPost(Post.MECH_REPRESENTATIVE);
			designation.setDesignationName("DEPARTMENT REPRESENTATIVE MECHANICAL");
			designation.setDesignationId("P13D0"+id);
			break;
		case "DRCT":
			designation.setPost(Post.CT_REPRESENTATIVE);
			designation.setDesignationName("DEPARTMENT REPRESENTATIVE CT");
			designation.setDesignationId("P14D0"+id);
			break;
		case "DREC":
			designation.setPost(Post.EC_REPRESENTATIVE);
			designation.setDesignationName("DEPARTMENT REPRESENTATIVE EC");
			designation.setDesignationId("P15D0"+id);
			break;
		case "DRAI":
			designation.setPost(Post.AI_REPRESENTATIVE);
			designation.setDesignationName("DEPARTMENT REPRESENTATIVE AI");
			designation.setDesignationId("P16D0"+id);
			break;
		case "DRAUTO":
			designation.setPost(Post.AUTO_REPRESENTATIVE);
			designation.setDesignationName("DEPARTMENT REPRESENTATIVE AUTO");
			designation.setDesignationId("P17D0"+id);
			break;
		case "ACS":
			designation.setPost(Post.ARTS_CLUB_SECRETARY);
			designation.setDesignationName("ARTS CLUB SECRETARY");
			designation.setDesignationId("P18D0"+id);
			break;
		default:
			break;
		}
		return id;
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		if (e.getStateChange()==ItemEvent.SELECTED) {
			((JCheckBox)e.getItemSelectable()).setForeground(new Color(0, 128, 0));
		} else {
			((JCheckBox)e.getItemSelectable()).setForeground(new Color(220, 20, 60));
		}
	}

	
}
