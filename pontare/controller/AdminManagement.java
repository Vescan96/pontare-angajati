package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;
import client.Client;
import model.User;
import view.AdminFrame;

public class AdminManagement extends Observable {

	private final int employee = 0;
	private final int informations = 1;
	private final int observations = 2;
	
	private List<Object> list = new ArrayList<Object>();
	private UserManagement um = new UserManagement();
	
	public AdminManagement(AdminFrame frame) {
		this.addObserver(um);
		
		frame.setVisible(true);
		
		frame.validateNameEvent(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent event) {
				if( Character.isDigit(event.getKeyChar()) || Character.isWhitespace(event.getKeyChar()) )
					frame.SetValidationText("Invalid name!");
								
				if( event.getKeyChar() == KeyEvent.VK_ENTER)
					frame.SetValidationText("");
			}

			@Override
			public void keyReleased(KeyEvent arg0) {				
			}

			@Override
			public void keyTyped(KeyEvent arg0) {				
			}
			
		});
		
		frame.addEmployeeEvent(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String username = frame.getUsernameText();
				String password = frame.getPassText();
								
				list.clear();
				list.add(5);
				list.add(new User(username, password));
				list.add("");
				list.add(employee);
				
				new Client().add(list);
			}
		});
		
		frame.viewEmployeesEvent(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				list.clear();
				list.add(6);
				list.add(employee);
				
				new Transmission(new Client().view(list), employee).sendData();	
				
			}
		});
	
		frame.updateEmployeeEvent(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String username = frame.getUsernameText();
				String newUsername = frame.getNewUsernameText();
				String newPassword = frame.getNewPassText();
				
				String data = newUsername + "," + newPassword;
				
				list.clear();
				list.add(7);
				list.add(username);
				list.add(data);
				list.add(employee);
				
				new Client().update(list);
			}
		});
		
		frame.deleteEmployeeEvent(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String username = frame.getUsernameText();
				
				list.clear();
				list.add(8);
				list.add(username);
				list.add(employee);
				
				new Client().delete(list);
			}
		});
		
		frame.addInformationsEvent(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String username = frame.getUsernameText();
				String name = frame.getNameText();
				String rank = frame.getRankText();
				int salary = frame.getSalaryText();
				float workedHours = frame.getWorkedHoursText();
				
				String data = name + "," + rank + "," + salary + "," + workedHours;
				
				list.clear();
				list.add(5);
				list.add(username);
				list.add(data);
				list.add(informations);
				
				new Client().add(list);
			}
		});
		
		frame.viewInformationsEvent(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				list.clear();
				list.add(6);
				list.add(informations);
				
				new Transmission(new Client().view(list), informations).sendData();	
				
			}
		});
		
		frame.updateInformationsEvent(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String username = frame.getUsernameText();
				String newName = frame.getNewNameText();
				String newRank = frame.getNewRankText();
				int newSalary = frame.getNewSalaryText();
				float newWorkedHours = frame.getNewWorkedHoursText();
				
				String data = newName + "," + newRank + "," + newSalary + "," + newWorkedHours;
				
				list.clear();
				list.add(7);
				list.add(username);
				list.add(data);
				list.add(informations);
				
				new Client().update(list);
			}			
		});
		
		frame.deleteInformationsEvent(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String username = frame.getUsernameText();
				
				list.clear();
				list.add(8);
				list.add(username);
				list.add(informations);
				
				new Client().delete(list);
			}
		});
		
		frame.addObservationsEvent(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String username = frame.getUsernameText();
				String observation = frame.getObservationText();
				
				list.clear();
				list.add(5);
				list.add(username);
				list.add(observation);
				list.add(observations);
				
				if(new Client().add(list) && UserManagement.isLoggedIn && UserManagement.username.equals(username)) {
					setChanged();
					notifyObservers();
				}
			}
		});
		
		frame.viewObservationsEvent(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				list.clear();
				list.add(6);
				list.add(observations);
				
				new Transmission(new Client().view(list), observations).sendData();			
			}
		});
		
		frame.updateObservationsEvent(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String username = frame.getUsernameText();
				String newObservation = frame.getNewObservationText();
								
				list.clear();
				list.add(7);
				list.add(username);
				list.add(newObservation);
				list.add(observations);
				
				new Client().update(list);
			}			
		});
	
		frame.deleteObservationsEvent(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String username = frame.getUsernameText();
				
				list.clear();
				list.add(8);
				list.add(username);
				list.add(observations);
				
				new Client().delete(list);
			}
		});
	
	}
	
}
