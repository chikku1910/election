package org.mgm.elector.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.imageio.ImageIO;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.mgm.elector.model.Designation;
import org.mgm.elector.model.Designation.Post;
import org.xml.sax.SAXException;
import org.mgm.elector.model.Nominee;
import org.mgm.elector.model.NomineeDaoImp;
import org.mgm.elector.model.Student;
import org.mgm.elector.model.StudentDAOImp;
import org.w3c.dom.Document;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class NomineeDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4150946800454684257L;
	private final JPanel contentPanel = new JPanel();
	private JTextField idTxt;
	private JTextField departmentTxt;
	private JTextField postTxt;
	private Post post=null;
	private JComboBox<Student> nomineeCombo;
	protected File symbolFile;
	private JPanel symbolPanel;
	private JPanel photoPanel;
	protected Image img;
	private Document xmlDocument;
	private Designation designation;
	private int count;
	private JPanel nomineesPanel;

	public NomineeDialog(Designation designation,JPanel nomineesPanel) {
		// TODO Auto-generated constructor stub
		this.post=designation.getPost();
		this.designation=designation;
		this.nomineesPanel=nomineesPanel;
		try {
			initialize(null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public NomineeDialog(Nominee nominee) {
		try {
			initialize(nominee);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void initialize(Nominee nominee) throws IOException {
		// TODO Auto-generated method stub
		setResizable(false);
		setBounds(100, 100, 632, 400);
		setModal(true);
		setTitle("ADD NOMINEE");
		//URL url=getClass().getResource("/resource/download (1).jpg");
		FileInputStream url=new FileInputStream("src/main/resource/48x48.png");
		Image logo=ImageIO.read(url);
		setIconImage(logo);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("NOMINEE");
			lblNewLabel.setOpaque(true);
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setForeground(new Color(240, 255, 255));
			lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 30));
			lblNewLabel.setBorder(new LineBorder(new Color(192, 192, 192), 2));
			lblNewLabel.setBackground(new Color(46, 139, 87));
			lblNewLabel.setBounds(0, 0, 616, 40);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel label1 = new JLabel("NOMINEE :  ");
			label1.setFont(new Font("Times New Roman", Font.BOLD, 15));
			label1.setBounds(10, 80, 138, 28);
			contentPanel.add(label1);
		}
		{
			JLabel label2 = new JLabel("NOMINEE ID :  ");
			label2.setFont(new Font("Times New Roman", Font.BOLD, 15));
			label2.setBounds(10, 119, 138, 28);
			contentPanel.add(label2);
		}
		{
			JLabel label3 = new JLabel("DEPARTMENT :  ");
			label3.setFont(new Font("Times New Roman", Font.BOLD, 15));
			label3.setBounds(10, 158, 138, 28);
			contentPanel.add(label3);
		}
		{
			JLabel label4 = new JLabel("POST :  ");
			label4.setFont(new Font("Times New Roman", Font.BOLD, 15));
			label4.setBounds(10, 197, 138, 28);
			contentPanel.add(label4);
		}
		{
			JLabel label5 = new JLabel("SYMBOL :  ");
			label5.setFont(new Font("Times New Roman", Font.BOLD, 15));
			label5.setBounds(10, 256, 89, 28);
			contentPanel.add(label5);
		}
		
		nomineeCombo = new JComboBox<Student>();
		nomineeCombo.setModel(setStudentModel(nominee));
		nomineeCombo.addItemListener(e->selectStudent());
		nomineeCombo.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		nomineeCombo.setBounds(171, 80, 216, 28);
		contentPanel.add(nomineeCombo);
		{
			idTxt = new JTextField(nominee!=null?nominee.getNomineeId():setNomineeId(post));
			idTxt.setEditable(false);
			idTxt.setFont(new Font("Times New Roman", Font.BOLD, 15));
			idTxt.setBounds(171, 119, 216, 28);
			contentPanel.add(idTxt);
			idTxt.setColumns(10);
		}
		{
			departmentTxt = new JTextField(nominee!=null?nominee.getNominee().getDepartment().getDeptName():"");
			departmentTxt.setEditable(false);
			departmentTxt.setFont(new Font("Times New Roman", Font.BOLD, 15));
			departmentTxt.setColumns(10);
			departmentTxt.setBounds(171, 163, 216, 28);
			contentPanel.add(departmentTxt);
		}
		{
			postTxt = new JTextField(setPost(nominee!=null?nominee.getDesignation().getPost():post));
			postTxt.setEditable(false);
			postTxt.setFont(new Font("Times New Roman", Font.BOLD, 15));
			postTxt.setColumns(10);
			postTxt.setBounds(171, 202, 216, 28);
			contentPanel.add(postTxt);
		}
		
		symbolPanel = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = -7862114377953604197L;

			@Override
			public void paintComponent(Graphics g) {
				// TODO Auto-generated method stub
			
			super.paintComponent(g);
			Image photo=null;
			try {
				
				if (nominee!=null) {
					photo = ImageIO.read(new ByteArrayInputStream(nominee.getSymbol()));
				}
				photo=ImageIO
						.read(new FileInputStream(symbolFile));
						
			} catch (IOException e) {
				e.printStackTrace();
			}catch (NullPointerException e) {
				// TODO: handle exception
			}
			g.drawImage(photo, 0, 0,this.getWidth(),this.getHeight(), null);
		}
		};
		symbolPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		symbolPanel.setBounds(111, 244, 61, 61);
		contentPanel.add(symbolPanel);
		
		JButton browseBtn = new JButton("Browse");
		browseBtn.addActionListener(e->browseSymbol());
		browseBtn.setFocusPainted(false);
		browseBtn.setFont(new Font("Times New Roman", Font.BOLD, 15));
		browseBtn.setBackground(SystemColor.scrollbar);
		browseBtn.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		browseBtn.setBounds(182, 254, 100, 34);
		contentPanel.add(browseBtn);
		
		photoPanel = new JPanel() {

			/**
			 * 
			 */
			private static final long serialVersionUID = -6584875248625376826L;

			@Override
			public void paintComponent(Graphics g) {
				// TODO Auto-generated method stub
			
			super.paintComponent(g);
			
			try {
				if (nominee!=null) {
					img = ImageIO.read(new ByteArrayInputStream(nominee.getNominee().getPhoto()));
				}
				g.drawImage(img, 0, 0,this.getWidth(),this.getHeight(), null);
						
			} catch (NullPointerException e) {
				// TODO: handle exception
			}catch (Exception e) {
				// TODO: handle exception
			}
			
		}
		};
		photoPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		photoPanel.setBounds(485, 52, 121, 120);
		contentPanel.add(photoPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton((nominee==null)?"ADD":"SAVE");
				okButton.setFocusPainted(false);
				okButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
				okButton.setActionCommand((nominee==null)?"add":"save");
				okButton.addActionListener(e->saveNominee(e));
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setFocusPainted(false);
				cancelButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
				cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener(e->dispose());
				buttonPane.add(cancelButton);
			}
		}
	}
	private String setNomineeId(Post post2) {
		try {
			xmlDocument=DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(ElectionApplication.FILE);
		} catch (SAXException | IOException | ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		xmlDocument.getDocumentElement().normalize();
		count=Integer.parseInt( xmlDocument.getElementsByTagName("nomineeid").item(0).getTextContent());
		String id="N0";
		switch (post) {
		case CHAIRMAN:
			id="P01"+id+count;
			break;
		case VICE_CHAIRMAN:
			id="P02"+id+count;
			break;
		case GENERAL_SECRETARY:
			id="P03"+id+count;
			break;
		case MAGAZINE_EDITOR:
			id="P04"+id+count;
			break;
		case WOMEN_REPRESENTATIVE1:
			id="P05"+id+count;
			break;
		case WOMEN_REPRESENTATIVE2:
			id="P06"+id+count;
			break;
		case UNIVERSITY_UNION_COUNCILER1:
			id="P07"+id+count;
			break;
		case UNIVERSITY_UNION_COUNCILER2:
			id="P08"+id+count;
			break;
		case REPRESENTATIVE_Y1:
			id="P09"+id+count;
			break;
		case REPRESENTATIVE_Y2:
			id="P10"+id+count;
			break;
		case REPRESENTATIVE_Y3:
			id="P11"+id+count;
			break;
		case CIVIL_REPRESENTATIVE:
			id="P12"+id+count;
			break;
		case MECH_REPRESENTATIVE:
			id="P13"+id+count;
			break;
		case CT_REPRESENTATIVE:
			id="P14"+id+count;
			break;
		case EC_REPRESENTATIVE:
			id="P15"+id+count;
			break;
		case AI_REPRESENTATIVE:
			id="P16"+id+count;
			break;
		case AUTO_REPRESENTATIVE:
			id="P17"+id+count;
			break;
		case ARTS_CLUB_SECRETARY:
			id="P18"+id+count;
			break;
		default:
			id="";
			break;
		}
		return id;
	}
	public static String setPost(Post post) {
		// TODO Auto-generated method stub
		String postName;
		switch (post) {
		case CHAIRMAN:
			postName="CHAIRMAN";
			break;
		case VICE_CHAIRMAN:
			postName="VICE CHAIRMAN";
			break;
		case GENERAL_SECRETARY:
			postName="GENERAL SECRETARY";
			break;
		case MAGAZINE_EDITOR:
			postName="VICE CHAIRMAN";
			break;
		case WOMEN_REPRESENTATIVE1:
			postName="WOMEN REPRESENTATIVE 1";
			break;
		case WOMEN_REPRESENTATIVE2:
			postName="WOMEN REPRESENTATIVE 2";
			break;
		case UNIVERSITY_UNION_COUNCILER1:
			postName="UNIVERSITY UNION COUNCILER 1";
			break;
		case UNIVERSITY_UNION_COUNCILER2:
			postName="UNIVERSITY UNION COUNCILER 2";
			break;
		case REPRESENTATIVE_Y1:
			postName="FIRST YEAR REPRESENTATIVE";
			break;
		case REPRESENTATIVE_Y2:
			postName="SECOND YEAR REPRESENTATIVE";
			break;
		case REPRESENTATIVE_Y3:
			postName="THIRD YEAR REPRESENTATIVE";
			break;
		case CIVIL_REPRESENTATIVE:
			postName="CIVIL REPRESENTATIVE";
			break;
		case MECH_REPRESENTATIVE:
			postName="MECH REPRESENTATIVE";
			break;
		case CT_REPRESENTATIVE:
			postName="CT REPRESENTATIVE";
			break;
		case EC_REPRESENTATIVE:
			postName="EC REPRESENTATIVE";
			break;
		case AI_REPRESENTATIVE:
			postName="AI REPRESENTATIVE";	
			break;
		case AUTO_REPRESENTATIVE:
			postName="AUTO REPRESENTATIVE";
			break;
		case ARTS_CLUB_SECRETARY:
			postName="ARTS CLUB SECRETARY";
			break;
		default:
			postName="";
			break;
		}
		return postName;
	}
	private ComboBoxModel<Student> setStudentModel(Nominee nominee) {
		// TODO Auto-generated method stub
		DefaultComboBoxModel<Student> model=new DefaultComboBoxModel<Student>();
		model.setSelectedItem(nominee!=null?nominee.getNominee():null);
		model.addAll(ElectionApplication.getContext().getBean(StudentDAOImp.class,"studentdaobean").getAllStudents());
		return model;
	}
	/**
	 * Create the dialog.
	 * @param nominee 
	 * @throws IOException 
	 */
	
	private void browseSymbol() {
		// TODO Auto-generated method stub
		JFileChooser fileChooser=new JFileChooser();
		FileFilter imageFilter = new FileNameExtensionFilter(
			    "Image files", ImageIO.getReaderFileSuffixes());
		fileChooser.setFileFilter(imageFilter);
		if(fileChooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION) {

		symbolFile	=fileChooser.getSelectedFile();
		}
		symbolPanel.repaint();
		symbolPanel.revalidate();
	}


	private void selectStudent() {
		// TODO Auto-generated method stub
	Student student=(Student)((DefaultComboBoxModel<Student>)nomineeCombo.getModel()).getSelectedItem();
	departmentTxt.setText(student.getDepartment().getDeptName());
	try {
		img=ImageIO.read(new ByteArrayInputStream(student.getPhoto()));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	photoPanel.repaint();
	photoPanel.revalidate();
	}


	private void saveNominee(ActionEvent e) {
		Nominee nominee=new Nominee();
		nominee.setDesignation(designation);
		nominee.setNominee((Student)nomineeCombo.getSelectedItem());
		nominee.setNomineeId(idTxt.getText());
		try {
			FileInputStream stream=new FileInputStream(symbolFile);
			nominee.setSymbol(stream.readAllBytes());
			stream.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// TODO Auto-generated method stub
		switch (e.getActionCommand()) {
		case "add":
			ElectionApplication.getContext().getBean(NomineeDaoImp.class,"nomineedaobean").addNominee(nominee);
			nomineesPanel.add(new NomineePanel(nominee));
			nomineesPanel.repaint();
			nomineesPanel.revalidate();
			count++;
			xmlDocument.getElementsByTagName("nomineeid").item(0).setTextContent(count+"");
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
			dispose();
			break;
		case "save":
			
			dispose();
			break;
		default:
			break;
		}
	}
}
