package org.mgm.elector.gui;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.ByteArrayInputStream;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.border.LineBorder;

import org.mgm.elector.model.Designation;
import org.mgm.elector.model.Designation.Post;
import org.mgm.elector.model.DesignationDaoImp;
import org.mgm.elector.model.Election;
import org.mgm.elector.model.ElectionDaoImp;
import org.mgm.elector.model.Election.Status;
import org.mgm.elector.model.Nominee;
import org.mgm.elector.model.NomineeDaoImp;
import org.mgm.elector.model.Student;
import org.mgm.elector.model.StudentDAOImp;
import org.springframework.context.ApplicationContext;

public class VotingPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2676278729705842100L;

	public VotingPanel() {
	}

	private Election election;
	private Map<Post, JList<Nominee>> nomineMap;
	private StudentViewPanel panel;
	private JButton voteButton;
	private Student student;
	private JComboBox<Student> studentCombo;
	private Map<Post,JScrollPane> scrollMap;
	
	public void setElection(Election election) {
		this.election = election;
		removeAll();
		initialize();
	}

	/**
	 * Create the panel.
	 */
	public void initialize() {
		setLayout(new BorderLayout(0, 0));
		
		JLabel headLabel = new JLabel("ELECT YOUR REPRESENTATIVES");
		headLabel.setHorizontalAlignment(SwingConstants.CENTER);
		headLabel.setPreferredSize(new Dimension(156, 60));
		headLabel.setOpaque(true);
		headLabel.setForeground(new Color(240, 248, 255));
		headLabel.setBackground(new Color(46, 139, 87));
		headLabel.setFont(new Font("Times New Roman", Font.BOLD, 25));
		add(headLabel, BorderLayout.NORTH);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setPreferredSize(new Dimension(10, 60));
		FlowLayout flowLayout = (FlowLayout) buttonPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		add(buttonPanel, BorderLayout.SOUTH);
		
		JButton endButton=new JButton("END ELECTION");
		endButton.setPreferredSize(new Dimension(250, 50));
		endButton.setForeground(new Color(240, 248, 255));
		endButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
		endButton.setFocusPainted(false);
		endButton.setBackground(new Color(46, 139, 87));
		endButton.setActionCommand("end");
		endButton.addActionListener(e->endElection());
		buttonPanel.add(endButton);
		
		voteButton = new JButton("VOTE");
		voteButton.setPreferredSize(new Dimension(250, 50));
		voteButton.setForeground(new Color(240, 248, 255));
		voteButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
		voteButton.setFocusPainted(false);
		voteButton.setBackground(new Color(46, 139, 87));
		voteButton.setActionCommand("vote");
		voteButton.setEnabled(false);
		voteButton.addActionListener(e->voteConfirm());
		buttonPanel.add(voteButton);
		
		JButton cancelBtn = new JButton("CANCEL");
		cancelBtn.setPreferredSize(new Dimension(250, 50));
		cancelBtn.setForeground(new Color(240, 248, 255));
		cancelBtn.setFont(new Font("Times New Roman", Font.BOLD, 20));
		cancelBtn.setFocusPainted(false);
		cancelBtn.setBackground(new Color(46, 139, 87));
		cancelBtn.addActionListener(e->cancel());
		buttonPanel.add(cancelBtn);
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		JPanel postPanel = new JPanel();
		scrollPane.setViewportView(postPanel);
		postPanel.setLayout(new BoxLayout(postPanel, BoxLayout.Y_AXIS));
		panel=new StudentViewPanel();
		postPanel.add(panel);
		List<Designation> designations= ElectionApplication.getContext().getBean(DesignationDaoImp.class,"designationdaobean")
				.getAllDesignation(election) ;
		nomineMap=new HashMap<Designation.Post, JList<Nominee>>();
		scrollMap=new HashMap<Post,JScrollPane>();
		for (Designation designation :designations){
			JScrollPane postScroll = new JScrollPane();
			postScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			postScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			postScroll.setColumnHeaderView(new JLabel(designation.getPost().toString()));
			JPanel postScrollPanel=new JPanel();
			postScrollPanel.setLayout(new BorderLayout());
			postScrollPanel.setPreferredSize(new Dimension(1,192));
			JList<Nominee> nomineeList=new JList<Nominee>();
			nomineeList.setVisibleRowCount(-1);
			List<Nominee> nominees=ElectionApplication.getContext().getBean(NomineeDaoImp.class,"nomineedaobean")
					.getNominees(designation);
		
			DefaultListModel<Nominee> model=new DefaultListModel<Nominee>();
			NomineeListRenderer renderer=new NomineeListRenderer();
			model.addAll(nominees);
			nomineeList.setCellRenderer(renderer);
			nomineeList.setModel(model);
			nomineeList.setFixedCellHeight(192);
			nomineeList.setFixedCellWidth(200);
			nomineeList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
			
			nomineeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			
			nomineMap.put(designation.getPost(), nomineeList);
			postScrollPanel.add(nomineeList,BorderLayout.CENTER);
			postScroll.setViewportView(postScrollPanel);
			postPanel.add(postScroll);
			scrollMap.put(designation.getPost(), postScroll);
		}
		
	}
	private void endElection() {
		// TODO Auto-generated method stub
		int decision=	JOptionPane.showInternalConfirmDialog(null, "Confirm completion of the election", "CONFIRMATION",
				JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
			if (decision==JOptionPane.YES_OPTION) {
				election.setStatus(Status.COUNT);
				ElectionApplication.getContext().getBean(ElectionDaoImp.class,"electiondaobean").persistElection(election);
				ApplicationContext context=ElectionApplication.getContext();
				ElectionApplication window =context.getBean(ElectionApplication.class,"window");
				JFrame frame=window.getFrame();
				frame.remove(window.getPanel());
				window.setPanel(context.getBean(MGMElectionPanel.class,"mgmelectionpanel"));
				frame.repaint();
				frame.revalidate();
			}
	}

	private void cancel() {
		// TODO Auto-generated method stub
		panel.deSelectStudent();
		nomineMap.forEach((key,value)->{
			value.setSelectedValue(null, false);
		});	
	}

private void voteConfirm() {
		// TODO Auto-generated method stub
	Map<Post, Nominee> electedNominees=new HashMap<Designation.Post, Nominee>();
	nomineMap.forEach((key,value)->{
		electedNominees.put(key, value.getSelectedValue());
	});
	try {
		VoteConfirmDialog dialog = new VoteConfirmDialog(electedNominees,student,studentCombo);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
		cancel();
	} catch (Exception e) {
		e.printStackTrace();
	}
	}
private class NomineeListRenderer  implements ListCellRenderer<Nominee>{

	
	@Override
	public Component getListCellRendererComponent(JList<? extends Nominee> list, Nominee value, int index,
			boolean isSelected, boolean cellHasFocus) {
		
		NomineePanel panel=new NomineePanel(value);
		if (isSelected) {
			panel.setBackground(new Color(30, 144, 255));
		}
		else {
			panel.setBackground(new Color(152, 251, 152));
		}
		return panel;
	}
	
}
private class StudentViewPanel extends JPanel implements ItemListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6336624560483073050L;
	private JLabel idLabel;
	private JLabel nameLabel;
	private JLabel departmentLabel;
	private JLabel phnoLabel;
	private JLabel emailLabel;
	private JLabel yearLabel;
	private JLabel dobLabel;
	protected Image img;
	private JPanel photoPanel;
	
	public StudentViewPanel() {
		
		JLabel label1 = new JLabel("SELECT STUDENT    :");
		label1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		label1.setPreferredSize(new Dimension(50, 20));
		
		studentCombo = new JComboBox<Student>();
		DefaultComboBoxModel<Student> model=new DefaultComboBoxModel<Student>();
		Set<Student> students= ElectionApplication.getContext().getBean(StudentDAOImp.class,"studentdaobean")
				.getAllStudents();
		for (Student student:students) {
			if (!student.isVoted()) {
				model.addElement(student);
			}
		}
		studentCombo.setModel(model);
		studentCombo.setSelectedIndex(-1);
		studentCombo.addItemListener(this);
		JLabel label2 = new JLabel("STUDENT ID              :");
		label2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		label2.setPreferredSize(new Dimension(50, 20));
		
		JLabel label3 = new JLabel("STUDENT NAME       :");
		label3.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		label3.setPreferredSize(new Dimension(50, 20));
		
		idLabel = new JLabel("");
		idLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		JLabel label4 = new JLabel("DEPARTMENT           :");
		label4.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		label4.setPreferredSize(new Dimension(50, 20));
		
		JLabel label5 = new JLabel("PHONE NUMBER       :");
		label5.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		label5.setPreferredSize(new Dimension(50, 20));
		
		JLabel label6 = new JLabel("EMAIL                         :");
		label6.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		label6.setPreferredSize(new Dimension(50, 20));
		
		nameLabel = new JLabel("");
		nameLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		departmentLabel = new JLabel("");
		departmentLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		phnoLabel = new JLabel("");
		phnoLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		emailLabel = new JLabel("");
		emailLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		JLabel label7 = new JLabel("YEAR                     :");
		label7.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		JLabel label8 = new JLabel("DATE OF BIRTH :");
		label8.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		yearLabel = new JLabel("");
		yearLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		dobLabel = new JLabel("");
		dobLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		photoPanel = new JPanel() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 6182912708083080182L;

			@Override
			public void paintComponent(Graphics g) {
				// TODO Auto-generated method stub
			
			super.paintComponent(g);
			
			try {
				
				g.drawImage(img, 0, 0,this.getWidth(),this.getHeight(), null);
						
			} catch (NullPointerException e) {
				// TODO: handle exception
			}catch (Exception e) {
				// TODO: handle exception
			}
			}
			};
		photoPanel.setBorder(new LineBorder(Color.BLACK, 1, true));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(30)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(label1, GroupLayout.DEFAULT_SIZE, 175, 250)
								.addComponent(label2, GroupLayout.DEFAULT_SIZE, 175, 250))
							.addGap(37)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(studentCombo, 0, 213, 350)
								.addComponent(idLabel, GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE))
							.addGap(212)
							.addComponent(photoPanel, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
							.addGap(27))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(label3, GroupLayout.DEFAULT_SIZE, 175, 250)
							.addGap(37)
							.addComponent(nameLabel, GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
							.addGap(373))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(label4, GroupLayout.DEFAULT_SIZE, 175, 250)
							.addGap(37)
							.addComponent(departmentLabel, GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
							.addGap(43)
							.addComponent(label7, GroupLayout.DEFAULT_SIZE, 131, 250)
							.addGap(41)
							.addComponent(yearLabel, GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(label5, GroupLayout.DEFAULT_SIZE, 175, 250)
							.addGap(37)
							.addComponent(phnoLabel, GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
							.addGap(373))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(label6, GroupLayout.DEFAULT_SIZE, 175, 250)
							.addGap(37)
							.addComponent(emailLabel, GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
							.addGap(44)
							.addComponent(label8, GroupLayout.PREFERRED_SIZE, 138,250)
							.addGap(33)
							.addComponent(dobLabel, GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)))
					.addGap(21))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(15)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(label1, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
							.addGap(68)
							.addComponent(label2, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 17, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addComponent(studentCombo, GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
							.addGap(64)
							.addComponent(idLabel, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
							.addGap(17))
						.addComponent(photoPanel, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE))
					.addGap(1)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(label3, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(nameLabel, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(label4, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 8, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(departmentLabel)
							.addPreferredGap(ComponentPlacement.RELATED, 8, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
							.addComponent(label7))
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
							.addComponent(yearLabel)))
					.addGap(6)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(label5, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(phnoLabel))
					.addGap(11)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(label6, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(emailLabel, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(label8, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(dobLabel, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addGap(11))
		);
		setLayout(groupLayout);

	}

	public  void deSelectStudent() {
		// TODO Auto-generated method stub
		studentCombo.setSelectedIndex(-1);
		idLabel.setText(null);
		nameLabel.setText(null);
		departmentLabel.setText(null);
		phnoLabel.setText(null);
		emailLabel.setText(null);
		yearLabel.setText(null);
		dobLabel.setText(null);
		img=null;
		photoPanel.repaint();
		photoPanel.revalidate();
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		
		if (e.getStateChange()==ItemEvent.SELECTED) {
			student = (Student) studentCombo.getSelectedItem();
			voteButton.setEnabled(true);
			idLabel.setText(student.getStudentId());
			nameLabel.setText(student.getStudentName());
			departmentLabel.setText(student.getDepartment().getDeptName());
			phnoLabel.setText(student.getPhoneNumber());
			emailLabel.setText(student.getEmail());
			yearLabel.setText(student.getAcademicYear() + "");
			dobLabel.setText(new SimpleDateFormat("dd MMMM YYYY").format(student.getDateOfBrith()));
			try {
				img = ImageIO.read(new ByteArrayInputStream(student.getPhoto()));
				photoPanel.repaint();
				photoPanel.revalidate();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
			
			if(student.getGender().equalsIgnoreCase("male")) {
				if (scrollMap.get(Post.WOMEN_REPRESENTATIVE1)!=null) {
					scrollMap.get(Post.WOMEN_REPRESENTATIVE1).setVisible(false);
				}
				if (scrollMap.get(Post.WOMEN_REPRESENTATIVE2)!=null) {
					scrollMap.get(Post.WOMEN_REPRESENTATIVE2).setVisible(false);
				}
			}
			switch (student.getAcademicYear()) {
			case 1:
				if (scrollMap.get(Post.REPRESENTATIVE_Y2)!=null) {
					scrollMap.get(Post.REPRESENTATIVE_Y2).setVisible(false);
				}
				if (scrollMap.get(Post.REPRESENTATIVE_Y3)!=null) {
					scrollMap.get(Post.REPRESENTATIVE_Y3).setVisible(false);
				}
				break;
			case 2:
				if (scrollMap.get(Post.REPRESENTATIVE_Y1)!=null) {
					scrollMap.get(Post.REPRESENTATIVE_Y1).setVisible(false);
				}
				if (scrollMap.get(Post.REPRESENTATIVE_Y3)!=null) {
					scrollMap.get(Post.REPRESENTATIVE_Y3).setVisible(false);
				}
				break;
			case 3:
				if (scrollMap.get(Post.REPRESENTATIVE_Y2)!=null) {
					scrollMap.get(Post.REPRESENTATIVE_Y2).setVisible(false);
				}
				if (scrollMap.get(Post.REPRESENTATIVE_Y1)!=null) {
					scrollMap.get(Post.REPRESENTATIVE_Y1).setVisible(false);
				}
				break;
			default:
				break;
			}
			switch (student.getDepartment().getDeptId()) {
			case "D01":
				if (scrollMap.get(Post.CIVIL_REPRESENTATIVE)!=null) {
					scrollMap.get(Post.CIVIL_REPRESENTATIVE).setVisible(false);
				}
				if (scrollMap.get(Post.MECH_REPRESENTATIVE)!=null) {
					scrollMap.get(Post.MECH_REPRESENTATIVE).setVisible(false);
				}
				if (scrollMap.get(Post.CT_REPRESENTATIVE)!=null) {
					scrollMap.get(Post.CT_REPRESENTATIVE).setVisible(false);
				}
				if (scrollMap.get(Post.EC_REPRESENTATIVE)!=null) {
					scrollMap.get(Post.EC_REPRESENTATIVE).setVisible(false);
				}
				if (scrollMap.get(Post.AUTO_REPRESENTATIVE)!=null) {
					scrollMap.get(Post.AUTO_REPRESENTATIVE).setVisible(false);
				}
				break;
			case "D02":
				if (scrollMap.get(Post.CIVIL_REPRESENTATIVE)!=null) {
					scrollMap.get(Post.CIVIL_REPRESENTATIVE).setVisible(false);
				}
				if (scrollMap.get(Post.MECH_REPRESENTATIVE)!=null) {
					scrollMap.get(Post.MECH_REPRESENTATIVE).setVisible(false);
				}
				if (scrollMap.get(Post.AI_REPRESENTATIVE)!=null) {
					scrollMap.get(Post.AI_REPRESENTATIVE).setVisible(false);
				}
				if (scrollMap.get(Post.EC_REPRESENTATIVE)!=null) {
					scrollMap.get(Post.EC_REPRESENTATIVE).setVisible(false);
				}
				if (scrollMap.get(Post.AUTO_REPRESENTATIVE)!=null) {
					scrollMap.get(Post.AUTO_REPRESENTATIVE).setVisible(false);
				}
				break;
			case "D03":
				if (scrollMap.get(Post.CIVIL_REPRESENTATIVE)!=null) {
					scrollMap.get(Post.CIVIL_REPRESENTATIVE).setVisible(false);
				}
				if (scrollMap.get(Post.MECH_REPRESENTATIVE)!=null) {
					scrollMap.get(Post.MECH_REPRESENTATIVE).setVisible(false);
				}
				if (scrollMap.get(Post.CT_REPRESENTATIVE)!=null) {
					scrollMap.get(Post.CT_REPRESENTATIVE).setVisible(false);
				}				
				if (scrollMap.get(Post.AI_REPRESENTATIVE)!=null) {
					scrollMap.get(Post.AI_REPRESENTATIVE).setVisible(false);
				}
				if (scrollMap.get(Post.AUTO_REPRESENTATIVE)!=null) {
					scrollMap.get(Post.AUTO_REPRESENTATIVE).setVisible(false);
				}
				break;
			case "D04":
				if (scrollMap.get(Post.AI_REPRESENTATIVE)!=null) {
					scrollMap.get(Post.AI_REPRESENTATIVE).setVisible(false);
				}
				if (scrollMap.get(Post.MECH_REPRESENTATIVE)!=null) {
					scrollMap.get(Post.MECH_REPRESENTATIVE).setVisible(false);
				}
				if (scrollMap.get(Post.CT_REPRESENTATIVE)!=null) {
					scrollMap.get(Post.CT_REPRESENTATIVE).setVisible(false);
				}
				if (scrollMap.get(Post.EC_REPRESENTATIVE)!=null) {
					scrollMap.get(Post.EC_REPRESENTATIVE).setVisible(false);
				}
				if (scrollMap.get(Post.AUTO_REPRESENTATIVE)!=null) {
					scrollMap.get(Post.AUTO_REPRESENTATIVE).setVisible(false);
				}
				break;
			case "D05":
				if (scrollMap.get(Post.CIVIL_REPRESENTATIVE)!=null) {
					scrollMap.get(Post.CIVIL_REPRESENTATIVE).setVisible(false);
				}
				if (scrollMap.get(Post.AI_REPRESENTATIVE)!=null) {
					scrollMap.get(Post.AI_REPRESENTATIVE).setVisible(false);
				}
				if (scrollMap.get(Post.CT_REPRESENTATIVE)!=null) {
					scrollMap.get(Post.CT_REPRESENTATIVE).setVisible(false);
				}
				if (scrollMap.get(Post.EC_REPRESENTATIVE)!=null) {
					scrollMap.get(Post.EC_REPRESENTATIVE).setVisible(false);
				}
				if (scrollMap.get(Post.AUTO_REPRESENTATIVE)!=null) {
					scrollMap.get(Post.AUTO_REPRESENTATIVE).setVisible(false);
				}
				break;
			case "D06":
				if (scrollMap.get(Post.CIVIL_REPRESENTATIVE)!=null) {
					scrollMap.get(Post.CIVIL_REPRESENTATIVE).setVisible(false);
				}
				if (scrollMap.get(Post.MECH_REPRESENTATIVE)!=null) {
					scrollMap.get(Post.MECH_REPRESENTATIVE).setVisible(false);
				}
				if (scrollMap.get(Post.CT_REPRESENTATIVE)!=null) {
					scrollMap.get(Post.CT_REPRESENTATIVE).setVisible(false);
				}
				if (scrollMap.get(Post.EC_REPRESENTATIVE)!=null) {
					scrollMap.get(Post.EC_REPRESENTATIVE).setVisible(false);
				}
				if (scrollMap.get(Post.AI_REPRESENTATIVE)!=null) {
					scrollMap.get(Post.AI_REPRESENTATIVE).setVisible(false);
				}
				break;
			default:
				break;
			}
		}
		
	}
}
}
