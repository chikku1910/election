package org.mgm.elector.gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.SwingConstants;

import javax.swing.JPasswordField;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.border.LineBorder;

import org.mgm.elector.control.LoginButtonsAction;
import org.springframework.context.support.GenericApplicationContext;

import javax.swing.DebugGraphics;

@SuppressWarnings("serial")
public class LoginPanel extends JPanel {
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Create the panel.
	 */
	public LoginPanel() {
		initialize();
	}
	private void initialize() {
		GenericApplicationContext context=ElectionApplication.getContext();
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{50,150, 250,50, 0};
		gridBagLayout.rowHeights = new int[]{60, 40, 60, 60, 60, 60, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel headlbl = new JLabel("ELECTORAL SYSTEM LOGIN");
		headlbl.setForeground(new Color(0, 0, 255));
		headlbl.setHorizontalAlignment(SwingConstants.CENTER);
		headlbl.setFont(new Font("Times New Roman", Font.BOLD, 19));
		headlbl.setBackground(new Color(240, 128, 128));
		headlbl.setOpaque(true);
		GridBagConstraints gbc_headlbl = new GridBagConstraints();
		gbc_headlbl.fill = GridBagConstraints.BOTH;
		gbc_headlbl.insets = new Insets(0, 0, 5, 0);
		gbc_headlbl.gridwidth = 5;
		gbc_headlbl.gridx = 0;
		gbc_headlbl.gridy = 0;
		add(headlbl, gbc_headlbl);
		JLabel userName = new JLabel("USERNAME :     ");
		userName.setHorizontalAlignment(SwingConstants.TRAILING);
		userName.setFont(new Font("Times New Roman", Font.BOLD, 18));
		GridBagConstraints gbc_userName = new GridBagConstraints();
		gbc_userName.fill = GridBagConstraints.BOTH;
		gbc_userName.insets = new Insets(15, 0, 15, 0);
		gbc_userName.gridx = 1;
		gbc_userName.gridy = 2;
		add(userName, gbc_userName);
		
		textField = context.getBean(JTextField.class,"username");
		textField.setColumns(10);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.BOTH;
		gbc_textField.insets = new Insets(15, 0, 15, 0);
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 2;
		add(textField, gbc_textField);
		
		JLabel passWord = new JLabel("PASSWORD :      ");
		passWord.setHorizontalAlignment(SwingConstants.TRAILING);
		passWord.setFont(new Font("Times New Roman", Font.BOLD, 18));
		GridBagConstraints gbc_passWord = new GridBagConstraints();
		gbc_passWord.fill = GridBagConstraints.BOTH;
		gbc_passWord.insets = new Insets(15, 0, 15, 0);
		gbc_passWord.gridx = 1;
		gbc_passWord.gridy = 3;
		add(passWord, gbc_passWord);
		
		passwordField = context.getBean(JPasswordField.class,"password");
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.fill = GridBagConstraints.BOTH;
		gbc_passwordField.insets = new Insets(15, 0, 15, 0);
		gbc_passwordField.gridx = 2;
		gbc_passwordField.gridy = 3;
		add(passwordField, gbc_passwordField);
		
		JButton loginBtn = new JButton("LOGIN");
		loginBtn.setActionCommand("login");
		loginBtn.setForeground(new Color(0, 0, 255));
		loginBtn.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		loginBtn.setBackground(new Color(255, 99, 71));
		loginBtn.setFocusPainted(false);
		loginBtn.setPreferredSize(new Dimension(100, 30));
		loginBtn.setFont(new Font("Times New Roman", Font.BOLD, 18));
		LoginButtonsAction action=new LoginButtonsAction();
		loginBtn.addActionListener(action);
		GridBagConstraints gbc_loginBtn = new GridBagConstraints();
		gbc_loginBtn.fill = GridBagConstraints.BOTH;
		gbc_loginBtn.insets = new Insets(20, 60, 5, 60);
		gbc_loginBtn.gridwidth = 2;
		gbc_loginBtn.gridx = 1;
		gbc_loginBtn.gridy = 4;
		add(loginBtn, gbc_loginBtn);
		
		JButton changeBtn = new JButton("Change password ?");
		changeBtn.setActionCommand("change password");
		changeBtn.setDebugGraphicsOptions(DebugGraphics.FLASH_OPTION);
		changeBtn.setRequestFocusEnabled(false);
		changeBtn.setOpaque(false);
		changeBtn.setFocusable(false);
		changeBtn.setBackground(new Color(255, 192, 203,175));
		changeBtn.setHorizontalAlignment(SwingConstants.LEADING);
		changeBtn.setBorderPainted(false);
		changeBtn.setBorder(null);
		changeBtn.setFocusPainted(false);
		changeBtn.setForeground(new Color(0, 0, 255));
		changeBtn.setFont(new Font("Times New Roman", Font.BOLD, 18));
		changeBtn.setContentAreaFilled(false);
		changeBtn.addActionListener(action);
		GridBagConstraints gbc_changeBtn = new GridBagConstraints();
		gbc_changeBtn.fill = GridBagConstraints.HORIZONTAL;
		gbc_changeBtn.anchor = GridBagConstraints.NORTH;
		gbc_changeBtn.insets = new Insets(0, 60, 5, 60);
		gbc_changeBtn.gridwidth = 2;
		gbc_changeBtn.gridx = 1;
		gbc_changeBtn.gridy = 5;
		add(changeBtn, gbc_changeBtn);
	}
}
