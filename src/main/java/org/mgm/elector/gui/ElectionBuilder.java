package org.mgm.elector.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.xml.parsers.DocumentBuilderFactory;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.mgm.elector.model.Election;
import org.mgm.elector.model.ElectionDaoImp;
import org.mgm.elector.model.Student;
import org.mgm.elector.model.StudentDAO;
import org.mgm.elector.model.StudentDAOImp;
import org.w3c.dom.Document;


import com.toedter.calendar.JDateChooser;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Set;

import javax.swing.SwingConstants;
import java.awt.Color;

public class ElectionBuilder extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2171791876854855337L;
	private final JPanel contentPanel = new JPanel();
	private JTextField electionIdTxt;
	private JTextField electionNameTxt;
	private JDateChooser nominationDate;
	private JDateChooser electionDate;
	private JDateChooser resultDate;
	private JTextField electionYearTxt;
	private String id;
	private Document xmlDocument;
	

	/**
	 * Create the dialog.
	 * @throws IOException 
	 */
	public ElectionBuilder() throws IOException {
		initialize(null);
	}
	
	public ElectionBuilder(Election election) throws IOException {
		
		initialize(election);
	}
	private void  initialize(Election election) throws IOException {
		setTitle("ELECTION INFORMATION");
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
		{
			JLabel electionIdLbl = new JLabel("ELECTION ID              :  ");
			electionIdLbl.setBounds(41, 37, 184, 28);
			electionIdLbl.setHorizontalAlignment(SwingConstants.LEFT);
			electionIdLbl.setFont(new Font("Times New Roman", Font.BOLD, 15));
			contentPanel.add(electionIdLbl);
		}
		{
			electionIdTxt = new JTextField(election!=null?election.getElectionId(): electionId());
			electionIdTxt.setEditable(false);
			electionIdTxt.setBounds(235, 37, 160, 30);
			contentPanel.add(electionIdTxt);
			electionIdTxt.setColumns(10);
		}
		{
			JLabel electionNameLbl = new JLabel("ELECTION NAME       : ");
			electionNameLbl.setBounds(41, 78, 184, 28);
			electionNameLbl.setHorizontalAlignment(SwingConstants.LEFT);
			electionNameLbl.setFont(new Font("Times New Roman", Font.BOLD, 15));
			contentPanel.add(electionNameLbl);
		}
		{
			electionNameTxt = new JTextField(election!=null?election.getElectionName(): "");
			electionNameTxt.setBounds(235, 78, 160, 30);
			electionNameTxt.setColumns(10);
			contentPanel.add(electionNameTxt);
		}
		
		JLabel nominationlbl = new JLabel("NOMINATION DATE  : ");
		nominationlbl.setFont(new Font("Times New Roman", Font.BOLD, 15));
		nominationlbl.setBounds(41, 170, 184, 28);
		contentPanel.add(nominationlbl);
		
		nominationDate=new JDateChooser();
		nominationDate.setMinSelectableDate(new Date());
		nominationDate.setDate(election!=null?election.getNominationDate():new Date());
		nominationDate.setBounds(235, 170, 160, 28);
		contentPanel.add(nominationDate);
		{
			JLabel electionlbl = new JLabel("ELECTION DATE        : ");
			electionlbl.setFont(new Font("Times New Roman", Font.BOLD, 15));
			electionlbl.setBounds(41, 209, 184, 28);
			contentPanel.add(electionlbl);
		}
		{
			JLabel resultlbl = new JLabel("RESULT DATE             : ");
			resultlbl.setFont(new Font("Times New Roman", Font.BOLD, 15));
			resultlbl.setBounds(41, 248, 184, 28);
			contentPanel.add(resultlbl);
		}
		
		electionDate = new JDateChooser();
		electionDate.setBounds(235, 209, 160, 28);
		electionDate.setDate(election!=null?election.getElectionDate():new Date( nominationDate.getDate()
				.getTime()+(10*24*60*60*1000)));
		electionDate.setMinSelectableDate(new Date( nominationDate.getDate()
				.getTime()+(10*24*60*60*1000)));
		contentPanel.add(electionDate);
		
		resultDate = new JDateChooser();
		resultDate.setBounds(235, 248, 160, 28);
		resultDate.setDate(election!=null?election.getResultDate():electionDate.getDate());
		resultDate.setMinSelectableDate( electionDate.getDate());
		contentPanel.add(resultDate);
		
		JLabel headlbl = new JLabel("ELECTION");
		headlbl.setBackground(new Color(152, 251, 152));
		headlbl.setHorizontalAlignment(SwingConstants.CENTER);
		headlbl.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		headlbl.setOpaque(true);
		headlbl.setBounds(0, 0, 509, 26);
		contentPanel.add(headlbl);
		
		JLabel electionYearlbl = new JLabel("ELECTION YEAR        : ");
		electionYearlbl.setHorizontalAlignment(SwingConstants.LEFT);
		electionYearlbl.setFont(new Font("Times New Roman", Font.BOLD, 15));
		electionYearlbl.setBounds(41, 122, 184, 37);
		contentPanel.add(electionYearlbl);
		
		electionYearTxt = new JTextField(election!=null?election.getYear(): "");
		electionYearTxt.setColumns(10);
		electionYearTxt.setBounds(235, 122, 160, 30);
		contentPanel.add(electionYearTxt);
		{
			JPanel buttonPane = new JPanel();
			
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton((election==null)?"CREATE":"SAVE");
				okButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
				okButton.setActionCommand((election==null)?"CREATE":"SAVE");
				okButton.addActionListener(e->getElectionInfo(e));
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("CANCEL");
				cancelButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
				cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener(e->dispose());
				buttonPane.add(cancelButton);
			}
		}
	}
	
	private String electionId() {
		// TODO Auto-generated method stub
		xmlDocument=null;
		try {
			xmlDocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(ElectionApplication.FILE);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		xmlDocument.getDocumentElement().normalize();
		id= xmlDocument.getElementsByTagName("electionid").item(0).getTextContent();
		
		return "MGME"+id;
	}

	private void getElectionInfo(ActionEvent e) {
		Election election=new Election();
		try {
			election.setElectionId(electionIdTxt.getText());
			election.setElectionName(electionNameTxt.getText());
			election.setYear(electionYearTxt.getText());
			election.setNominationDate(new java.sql.Date( nominationDate.getDate().getTime()));
			election.setElectionDate(new java.sql.Date( electionDate.getDate().getTime()));
			election.setResultDate(new java.sql.Date( resultDate.getDate().getTime()));
			int decision=	JOptionPane.showInternalConfirmDialog(null, "warning! Do you want stop voting in previous election?"
					+ "previous election couldn't elect anymore ", "CONFIRMATION",
					JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
			if (decision==JOptionPane.YES_OPTION) {
				
			try {
				StudentDAO dao=ElectionApplication.getContext().getBean(StudentDAOImp.class,"studentdaobean");
				Set<Student> students=dao.getAllStudents();
				for (Student student : students) {
					student.setVoted(false);
					dao.persistStudent(student);
				}
				
				election.setTotalVoters(students.size());
				
				ElectionApplication.getContext().getBean(ElectionDaoImp.class,"electiondaobean")
				.persistElection(election);
				if (e.getActionCommand().equals("CREATE")) {
					JOptionPane.showMessageDialog(contentPanel, "Election created successfully", "SUCCESS",
							JOptionPane.INFORMATION_MESSAGE);
				}
			
				if (e.getActionCommand().equals("SAVE")) {
					JOptionPane.showMessageDialog(contentPanel, "Election modified successfully", "SUCCESS",
						JOptionPane.INFORMATION_MESSAGE);
					dispose();
					return;
				}
				id=Integer.parseInt(id)+1+"";
				if (id.length()==1) {
				id="00"+id;
				}
				else {
					id="0"+id;
				}
				xmlDocument.getElementsByTagName("electionid").item(0).setTextContent(id);
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer;
				try {
					transformer = transformerFactory.newTransformer();
					DOMSource source = new DOMSource(xmlDocument);
			        StreamResult result = new StreamResult(ElectionApplication.FILE);
			        transformer.transform(source, result);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					}
				addElectionPanel(election);
				dispose();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(contentPanel,"Election creation failed!", "WARNING", JOptionPane.WARNING_MESSAGE);
				}
			}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(contentPanel, e1.getMessage(), "WARNING", JOptionPane.WARNING_MESSAGE);
		}
		
	}

	private void addElectionPanel(Election election) {
		// TODO Auto-generated method stub
	JPanel panel=ElectionApplication.getContext().getBean(MGMElectionPanel.class,"mgmelectionpanel").getElectionPanel();
	panel.add(new ElectionPanel(election));
	panel.revalidate();
	panel.repaint();
	}
	
}
