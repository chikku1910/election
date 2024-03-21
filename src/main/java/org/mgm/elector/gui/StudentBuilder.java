package org.mgm.elector.gui;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import javax.swing.border.TitledBorder;


import org.mgm.elector.model.Address;
import org.mgm.elector.model.Department;
import org.mgm.elector.model.Student;
import org.mgm.elector.model.StudentDAO;
import org.mgm.elector.model.StudentDAOImp;

import com.toedter.calendar.JDateChooser;

import javax.swing.border.LineBorder;
import javax.swing.SpinnerNumberModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;



public class StudentBuilder extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4403124665800952770L;
	private JTextField idTxt;
	private JTextField nameTxt;
	private JTextField phnoTxt;
	private JTextField emailTxt;
	private JTextField districtTxt;
	private JTextField houseNameTxt;
	private JTextField streetTxt;
	private JTextField pinTxt;
	private JTextField placeTxt;
	private JDateChooser dob;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JSpinner yearSpinner;
	private JComboBox<Department> departmentcombo;
	private File imgFile;
	private JPanel photoPanel;

	/**
	 * Create the panel.
	 */
	public StudentBuilder() {
	
		setLayout(new BorderLayout(0, 0));
		JLabel headLbl = new JLabel("STUDENT");
		headLbl.setHorizontalAlignment(SwingConstants.CENTER);
		headLbl.setBackground(new Color(46, 139, 87));
		headLbl.setForeground(new Color(211, 211, 211));
		headLbl.setOpaque(true);
		headLbl.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 30));
		headLbl.setPreferredSize(new Dimension(1,45));
		add(headLbl,BorderLayout.NORTH);
		
		JPanel studentPanel = new JPanel();
		add(studentPanel,BorderLayout.CENTER);
		
		JLabel label1 = new JLabel("STUDENT ID :");
		label1.setHorizontalAlignment(SwingConstants.TRAILING);
		label1.setFont(new Font("Times New Roman", Font.BOLD, 15));
		
		JLabel label2 = new JLabel("STUDENT NAME :");
		label2.setHorizontalAlignment(SwingConstants.TRAILING);
		label2.setFont(new Font("Times New Roman", Font.BOLD, 15));
		
		JLabel label3 = new JLabel("DEPARTMENT :");
		label3.setHorizontalAlignment(SwingConstants.TRAILING);
		label3.setFont(new Font("Times New Roman", Font.BOLD, 15));
		
		JLabel label4 = new JLabel("DATE OF BIRTH :");
		label4.setHorizontalAlignment(SwingConstants.TRAILING);
		label4.setFont(new Font("Times New Roman", Font.BOLD, 15));
		
		JLabel label5 = new JLabel("GENDER :");
		label5.setHorizontalAlignment(SwingConstants.TRAILING);
		label5.setFont(new Font("Times New Roman", Font.BOLD, 15));
		
		JLabel label6 = new JLabel("EMAIL :");
		label6.setHorizontalAlignment(SwingConstants.TRAILING);
		label6.setFont(new Font("Times New Roman", Font.BOLD, 15));
		
		JLabel label12 = new JLabel("PHONE NUMBER :");
		label12.setHorizontalAlignment(SwingConstants.TRAILING);
		label12.setFont(new Font("Times New Roman", Font.BOLD, 15));
		
		idTxt = new JTextField();
		idTxt.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		idTxt.setColumns(10);
		
		nameTxt = new JTextField();
		nameTxt.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		nameTxt.setColumns(10);
		
		dob=new JDateChooser();
		
		phnoTxt = new JTextField();
		phnoTxt.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		phnoTxt.setColumns(10);
		
		emailTxt = new JTextField();
		emailTxt.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		emailTxt.setColumns(10);
		
		photoPanel = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = -4146806061703386899L;

			@Override
			public void paintComponent(Graphics g) {
				// TODO Auto-generated method stub
			
			super.paintComponent(g);
			Image photo=null;
			try {
				
				photo=ImageIO
						.read(new FileInputStream(imgFile));
						
			} catch (IOException e) {
				e.printStackTrace();
			}catch (NullPointerException e) {
				// TODO: handle exception
			}
			g.drawImage(photo, 0, 0,this.getWidth(),this.getHeight(), null);
		}
		};
		photoPanel.setBackground(new Color(189, 183, 107));
		
		JButton browseBtn = new JButton("BROWSE");
		browseBtn.setForeground(new Color(46, 139, 87));
		browseBtn.setFocusPainted(false);
		browseBtn.addActionListener(e->browseFile());
		browseBtn.setFont(new Font("Times New Roman", Font.BOLD, 15));
		browseBtn.setBorder(new LineBorder(new Color(46, 139, 87), 1, true));
		browseBtn.setBackground(new Color(211, 211, 211));
		
		departmentcombo = new JComboBox<Department>();
		departmentcombo.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				StudentDAO dao=	ElectionApplication.getContext().getBean(StudentDAOImp.class,"studentdaobean");
				DefaultComboBoxModel<Department> model= new DefaultComboBoxModel<Department>();
				model.addAll(dao.getDepartments());
				departmentcombo.setModel(model);
			}
		});
		departmentcombo.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		JRadioButton maleRd = new JRadioButton("MALE");
		maleRd.setActionCommand("male");
		buttonGroup.add(maleRd);
		maleRd.setFont(new Font("Times New Roman", Font.BOLD, 15));
		
		JRadioButton femaleRd = new JRadioButton("FEMALE");
		femaleRd.setActionCommand("female");
		buttonGroup.add(femaleRd);
		femaleRd.setHorizontalAlignment(SwingConstants.CENTER);
		femaleRd.setFont(new Font("Times New Roman", Font.BOLD, 15));
		
		JRadioButton transRd = new JRadioButton("TRANSGENDER");
		transRd.setActionCommand("transgender");
		buttonGroup.add(transRd);
		transRd.setHorizontalAlignment(SwingConstants.CENTER);
		transRd.setFont(new Font("Times New Roman", Font.BOLD, 15));
		
		JLabel label13 = new JLabel("ACADEMIC YEAR :");
		label13.setHorizontalAlignment(SwingConstants.TRAILING);
		label13.setFont(new Font("Times New Roman", Font.BOLD, 15));
		
		yearSpinner = new JSpinner();
		yearSpinner.setModel(new SpinnerNumberModel(1, 1, 3, 1));
		yearSpinner.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		GroupLayout gl_studentPanel = new GroupLayout(studentPanel);
		gl_studentPanel.setHorizontalGroup(
			gl_studentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_studentPanel.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_studentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_studentPanel.createSequentialGroup()
							.addGroup(gl_studentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(label1, GroupLayout.DEFAULT_SIZE, 161, 250)
								.addComponent(label2, GroupLayout.DEFAULT_SIZE, 161, 250))
							.addGap(10)
							.addGroup(gl_studentPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_studentPanel.createSequentialGroup()
									.addGap(3)
									.addComponent(idTxt, GroupLayout.DEFAULT_SIZE, 219, 300))
								.addGroup(gl_studentPanel.createSequentialGroup()
									.addComponent(nameTxt, GroupLayout.DEFAULT_SIZE, 219, 300)
									.addGap(3)))
							.addGap(257)
							.addComponent(photoPanel, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_studentPanel.createSequentialGroup()
							.addComponent(label3, GroupLayout.DEFAULT_SIZE, 161, 250)
							.addGap(10)
							.addComponent(departmentcombo, GroupLayout.DEFAULT_SIZE, 219, 300)
							.addGap(257)
							.addComponent(browseBtn, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_studentPanel.createSequentialGroup()
							.addComponent(label4, GroupLayout.DEFAULT_SIZE, 162, 250)
							.addGap(12)
							.addComponent(dob, GroupLayout.DEFAULT_SIZE, 219, 300)
							.addGap(377))
						.addGroup(gl_studentPanel.createSequentialGroup()
							.addComponent(label5, GroupLayout.DEFAULT_SIZE, 161,250)
							.addGap(15)
							.addComponent(maleRd, GroupLayout.PREFERRED_SIZE, 109, 150)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(femaleRd, GroupLayout.DEFAULT_SIZE, 109, 150)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(transRd, GroupLayout.PREFERRED_SIZE, 148, 150)
							.addGap(224))
						.addGroup(gl_studentPanel.createSequentialGroup()
							.addComponent(label6, GroupLayout.DEFAULT_SIZE, 161, 250)
							.addGap(13)
							.addComponent(emailTxt, GroupLayout.DEFAULT_SIZE, 219, 300)
							.addGap(377))
						.addGroup(gl_studentPanel.createSequentialGroup()
							.addComponent(label12, GroupLayout.DEFAULT_SIZE, 161, 250)
							.addGap(13)
							.addComponent(phnoTxt, GroupLayout.DEFAULT_SIZE, 219, 300)
							.addGap(377))
						.addGroup(gl_studentPanel.createSequentialGroup()
							.addComponent(label13, GroupLayout.DEFAULT_SIZE, 161, 250)
							.addGap(12)
							.addComponent(yearSpinner, GroupLayout.DEFAULT_SIZE, 78, 100)
							.addGap(519)))
					.addGap(10))
		);
		gl_studentPanel.setVerticalGroup(
			gl_studentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_studentPanel.createSequentialGroup()
					.addGap(11)
					.addGroup(gl_studentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_studentPanel.createSequentialGroup()
							.addGap(10)
							.addComponent(label1, GroupLayout.PREFERRED_SIZE, 30, 35)
							.addGap(31)
							.addComponent(label2, GroupLayout.PREFERRED_SIZE, 31, 35)
							.addGap(21))
						.addGroup(gl_studentPanel.createSequentialGroup()
							.addGap(10)
							.addComponent(idTxt, GroupLayout.PREFERRED_SIZE, 30, 35)
							.addGap(31)
							.addComponent(nameTxt, GroupLayout.PREFERRED_SIZE, 31, 35)
							.addGap(21))
						.addGroup(gl_studentPanel.createSequentialGroup()
							.addComponent(photoPanel, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
							))
					.addGap(7)
					.addGroup(gl_studentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(label3, GroupLayout.DEFAULT_SIZE, 35, 35)
						.addComponent(departmentcombo, GroupLayout.PREFERRED_SIZE, 35, 35)
						.addGroup(gl_studentPanel.createSequentialGroup()
							.addGap(10)
							.addComponent(browseBtn, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)))
					.addGap(30)
					.addGroup(gl_studentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_studentPanel.createSequentialGroup()
							.addGap(2)
							.addComponent(label4, GroupLayout.PREFERRED_SIZE, 23, 35))
						.addComponent(dob, GroupLayout.PREFERRED_SIZE, 25, 35))
					.addGap(30)
					.addGroup(gl_studentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(label5, GroupLayout.PREFERRED_SIZE, 25, 35)
						.addGroup(gl_studentPanel.createSequentialGroup()
							.addGap(1)
							.addGroup(gl_studentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(maleRd, GroupLayout.PREFERRED_SIZE, 23, 35)
								.addComponent(femaleRd, GroupLayout.PREFERRED_SIZE, 23, 35)
								.addComponent(transRd, GroupLayout.PREFERRED_SIZE, 23, 35))
							.addGap(1)))
					.addGap(30)
					.addGroup(gl_studentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_studentPanel.createSequentialGroup()
							.addGap(2)
							.addComponent(label6, GroupLayout.PREFERRED_SIZE, 23, 35)
							.addGap(2))
						.addComponent(emailTxt, GroupLayout.PREFERRED_SIZE, 27, 35))
					.addGap(30)
					.addGroup(gl_studentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_studentPanel.createSequentialGroup()
							.addGap(2)
							.addComponent(label12, GroupLayout.PREFERRED_SIZE, 23, 35)
							.addGap(2))
						.addComponent(phnoTxt, GroupLayout.PREFERRED_SIZE, 27, 35))
					.addGap(30)
					.addGroup(gl_studentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(label13, GroupLayout.PREFERRED_SIZE, 28, 35)
						.addComponent(yearSpinner, GroupLayout.PREFERRED_SIZE, 28, 35)))
		);
		studentPanel.setLayout(gl_studentPanel);
		
		JPanel bottomPanel=new JPanel();
		bottomPanel.setPreferredSize(new Dimension(1,300));
		add(bottomPanel,BorderLayout.SOUTH);
		bottomPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel gapPanel=new JPanel();
		gapPanel.setPreferredSize(new Dimension(1,45));
		bottomPanel.add(gapPanel,BorderLayout.CENTER);
		JPanel addressPanel = new JPanel();
		addressPanel.setBorder(new TitledBorder(new LineBorder(new Color(46, 139, 87), 2, true), "AADRESS", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(46, 139, 87)));
		addressPanel.setBounds(10, 351, 770, 147);
		addressPanel.setLayout(new GridLayout(0, 4, 50,30));
		
		JLabel label7 = new JLabel("HOUSE NAME :");
		label7.setFont(new Font("Times New Roman", Font.BOLD, 15));
		label7.setHorizontalAlignment(SwingConstants.CENTER);
		addressPanel.add(label7);
		
		houseNameTxt = new JTextField();
		houseNameTxt.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		addressPanel.add(houseNameTxt);
		houseNameTxt.setColumns(10);
		
		JLabel label8 = new JLabel("STREET     :");
		label8.setFont(new Font("Times New Roman", Font.BOLD, 15));
		label8.setHorizontalAlignment(SwingConstants.CENTER);
		addressPanel.add(label8);
		
		streetTxt = new JTextField();
		streetTxt.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		addressPanel.add(streetTxt);
		streetTxt.setColumns(10);
		
		JLabel label9 = new JLabel("PLACE              :");
		label9.setFont(new Font("Times New Roman", Font.BOLD, 15));
		label9.setHorizontalAlignment(SwingConstants.CENTER);
		addressPanel.add(label9);
		
		placeTxt = new JTextField();
		placeTxt.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		addressPanel.add(placeTxt);
		placeTxt.setColumns(10);
		
		JLabel label10 = new JLabel("DISTRICT :");
		label10.setFont(new Font("Times New Roman", Font.BOLD, 15));
		label10.setHorizontalAlignment(SwingConstants.CENTER);
		addressPanel.add(label10);
		
		districtTxt = new JTextField();
		districtTxt.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		addressPanel.add(districtTxt);
		districtTxt.setColumns(10);
		
		JLabel label11 = new JLabel("PIN CODE          :");
		label11.setFont(new Font("Times New Roman", Font.BOLD, 15));
		label11.setHorizontalAlignment(SwingConstants.CENTER);
		addressPanel.add(label11);
		pinTxt = new JTextField();
		pinTxt.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		addressPanel.add(pinTxt);
		pinTxt.setColumns(10);
		addressPanel.setPreferredSize(new Dimension(1,210));
		bottomPanel.add(addressPanel,BorderLayout.NORTH);
		
		JLabel label = new JLabel("");
		addressPanel.add(label);
		
		JLabel label_1 = new JLabel("");
		addressPanel.add(label_1);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(46, 139, 87));
		FlowLayout flowLayout = (FlowLayout) panel_3.getLayout();
		flowLayout.setHgap(30);
		flowLayout.setVgap(15);
		flowLayout.setAlignment(FlowLayout.TRAILING);
		panel_3.setPreferredSize(new Dimension(1,60));
		bottomPanel.add(panel_3,BorderLayout.SOUTH);
		
		JButton addButton = new JButton("ADD");
		addButton.setFocusPainted(false);
		addButton.setForeground(new Color(46, 139, 87));
		addButton.setBackground(new Color(211, 211, 211));
		addButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
		addButton.addActionListener(e->addStudent());
		panel_3.add(addButton);
		
		JButton cancelButton = new JButton("CANCEL");
		cancelButton.setFocusPainted(false);
		cancelButton.setForeground(new Color(46, 139, 87));
		cancelButton.setBackground(new Color(211, 211, 211));
		cancelButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
		cancelButton.addActionListener(e->clear());
		panel_3.add(cancelButton);
		
	}

	private void browseFile() {
		// TODO Auto-generated method stub
		JFileChooser fileChooser=new JFileChooser();
		FileFilter imageFilter = new FileNameExtensionFilter(
			    "Image files", ImageIO.getReaderFileSuffixes());
		fileChooser.setFileFilter(imageFilter);
		if(fileChooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION) {

		imgFile	=fileChooser.getSelectedFile();
		}
		photoPanel.repaint();
		photoPanel.revalidate();
	}

	private void addStudent() {
		// TODO Auto-generated method stub
		Student student=new Student();
		try {
			
		
		student.setStudentId(idTxt.getText().trim());
		student.setStudentName(nameTxt.getText().trim());
		student.setDepartment((Department)departmentcombo.getSelectedItem());
		student.setAcademicYear((int)yearSpinner.getValue());
		student.setDateOfBrith(new java.sql.Date(dob.getDate().getTime()));
		student.setEmail(emailTxt.getText().trim());
		student.setPhoneNumber(phnoTxt.getText().trim());
		Address address=new Address();
		address.setHouseName(houseNameTxt.getText().trim());
		address.setStreet(streetTxt.getText().trim());
		address.setDistrict(districtTxt.getText().trim());
		address.setPinCode(pinTxt.getText().trim());
		address.setPlace(placeTxt.getText().trim());
		student.setAddress(address);
		student.setGender(buttonGroup.getSelection().isSelected()?buttonGroup.getSelection().getActionCommand():null);
		try {
			FileInputStream stream=new FileInputStream(imgFile);
			student.setPhoto(stream.readAllBytes());
			stream.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(this, "Invalid image format. Try again!", "INVALID INPUT", JOptionPane.WARNING_MESSAGE);
		}catch (NullPointerException e) {
			// TODO: handle exception
		}
		finally {
			try {
				StudentDAO dao=	ElectionApplication.getContext().getBean(StudentDAOImp.class,"studentdaobean");
				dao.persistStudent(student);
				JOptionPane.showMessageDialog(this, "Student added successfully", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
				addStudentPanel(student);
				clear();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				JOptionPane.showMessageDialog(this, "Student doesn't added. Try again!", "ERROR", JOptionPane.WARNING_MESSAGE);
			}
		}
		} catch (IllegalArgumentException e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(this, e.getMessage(), "INVALID INPUT", JOptionPane.WARNING_MESSAGE);
		}	
		
	}

	private void addStudentPanel(Student student) {
		// TODO Auto-generated method stub
		JList<StudentPanel> list=ElectionApplication.getContext().getBean(MGMElectionPanel.class,"mgmelectionpanel")
				.getVoterList();
		((DefaultListModel<StudentPanel>)list.getModel()).addElement(new StudentPanel(student));
		list.repaint();
		list.revalidate();
	}

	private void clear() {
		// TODO Auto-generated method stub
		idTxt.setText(null);
		nameTxt.setText(null);
		emailTxt.setText(null);
		phnoTxt.setText(null);
		houseNameTxt.setText(null);
		districtTxt.setText(null);
		pinTxt.setText(null);
		placeTxt.setText(null);
		streetTxt.setText(null);
		buttonGroup.clearSelection();
		yearSpinner.setValue(1);
		dob.setDate(null);
		departmentcombo.setSelectedIndex(-1);
		
	}
}
