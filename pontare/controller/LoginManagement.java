package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import client.Client;
import model.Admin;
import model.User;
import view.AdminFrame;
import view.LoginFrame;
import view.UserFrame;

public class LoginManagement {

	private List<Object> list = new ArrayList<Object>();
	
	public LoginManagement(LoginFrame frame) {
		frame.setVisible(true);
		
		frame.registerEvent(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				boolean pass = true;
				
				if(frame.getChckbxUser().isSelected() && frame.getChckbxAdmin().isSelected()) {
					JOptionPane.showMessageDialog(null, "please choose just one option!", "Error", JOptionPane.ERROR_MESSAGE);
					pass = false;
				}
				
				if(frame.getChckbxAdmin().isSelected() && pass) 
					JOptionPane.showMessageDialog(null, "already exist and administrator", "Error", JOptionPane.ERROR_MESSAGE);
				
				if(frame.getChckbxUser().isSelected() && pass) {
					String username = frame.getUserText();
					String password = frame.getPassText();
					
					list.clear();
					list.add(0);	
					list.add(new User(username, password));
										
					new Client().registerUser(list);
				}					
			}
			
		});
		
		frame.loginEvent(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				boolean pass = true;
				
				if(frame.getChckbxUser().isSelected() && frame.getChckbxAdmin().isSelected()) {
					JOptionPane.showMessageDialog(null, "please choose just one option!", "Error", JOptionPane.ERROR_MESSAGE);
					pass = false;
				}
				
				if(frame.getChckbxAdmin().isSelected() && pass) {
					String name = frame.getUserText();
					String password = frame.getPassText();
					
					list.clear();
					list.add(1);
					list.add(new Admin(name, password));
					
					if(new Client().loginAdmin(list)) 
						new AdminManagement(new AdminFrame());
					else
						JOptionPane.showMessageDialog(null, "wrong username or password, or user doesn't exist", "Error", JOptionPane.ERROR_MESSAGE);					
				}
				
				if(frame.getChckbxUser().isSelected() && pass) {
					String username = frame.getUserText();
					String password = frame.getPassText();
						
					
					User user = new User(username, password);
					
					list.clear();
					list.add(2);
					list.add(user);							
					
					if(new Client().loginUser(list))
						new UserManagement(user, new UserFrame(), true);
					else
						JOptionPane.showMessageDialog(null, "wrong username or password, or user doesn't exist", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
			
		});
		
	}
}
