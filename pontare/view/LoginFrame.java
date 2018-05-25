package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	
	private JTextField usernameText;
	private JTextField passText;
	private JButton login;
	private JButton register;
	private JCheckBox chckbxUser;
	private JCheckBox chckbxAdmin;

	private JLabel show_validation_here;
	
	public LoginFrame() {
		setResizable(false);
		this.setSize(500, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblUsername = new JLabel("username");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblUsername.setBounds(50, 50, 150, 50);
		panel.add(lblUsername);
		
		JLabel lblPassword = new JLabel("password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblPassword.setBounds(50, 150, 150, 50);
		panel.add(lblPassword);
		
		show_validation_here = new JLabel("");
		show_validation_here.setForeground(Color.RED);
		show_validation_here.setFont(new Font("Tahoma", Font.PLAIN, 25));
		show_validation_here.setBackground(Color.RED);
		show_validation_here.setBounds(50, 110, 300, 30);
		panel.add(show_validation_here);
		
		usernameText = new JTextField();
		usernameText.setFont(new Font("Tahoma", Font.PLAIN, 20));
		usernameText.setToolTipText("");
		usernameText.setBounds(230, 55, 200, 40);
		panel.add(usernameText);
		usernameText.setColumns(10);
		
		passText = new JTextField();
		passText.setFont(new Font("Tahoma", Font.PLAIN, 20));
		passText.setColumns(10);
		passText.setBounds(230, 155, 200, 40);
		panel.add(passText);
		
		chckbxUser = new JCheckBox("User");
		chckbxUser.setFont(new Font("Tahoma", Font.PLAIN, 20));
		chckbxUser.setBounds(50, 250, 130, 30);
		panel.add(chckbxUser);
		
		chckbxAdmin = new JCheckBox("Administrator");
		chckbxAdmin.setFont(new Font("Tahoma", Font.PLAIN, 20));
		chckbxAdmin.setBounds(230, 250, 150, 30);
		panel.add(chckbxAdmin);
		
		login = new JButton("Login");
		login.setFont(new Font("Tahoma", Font.PLAIN, 25));
		login.setBounds(50, 350, 150, 50);
		panel.add(login);
		
		register = new JButton("Register");
		register.setFont(new Font("Tahoma", Font.PLAIN, 25));
		register.setBounds(230, 350, 150, 50);
		panel.add(register);
		
	}
	
	public void loginEvent(ActionListener listener) {
		login.addActionListener(listener);
	}
	
	public void registerEvent(ActionListener listener) {
		register.addActionListener(listener);
	}
	
	public JCheckBox getChckbxUser() {
		return chckbxUser;
	}
	
	public JCheckBox getChckbxAdmin() {
		return chckbxAdmin;
	}
	
	public String getUserText() {
		return usernameText.getText();
	}
	
	public String getPassText() {
		return passText.getText();
	}
	
	public void SetValidationText(String text) {
		show_validation_here.setText(text);
	}
}
