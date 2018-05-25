package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.JOptionPane;
import javax.swing.Timer;

import client.Client;
import model.User;
import view.UserFrame;

public class UserManagement implements Observer {
	private Timer timer;
	private static final int informations = 1;
	private static final int observations = 2;
	private int i, seconds, minutes, hours;

	private List<Object> list = new ArrayList<Object>();
	protected static boolean isLoggedIn = false;
	protected static String username;
	
	public UserManagement() {}
	
	public UserManagement(User user, UserFrame frame, boolean isLoggedIn) {
		UserManagement.isLoggedIn = isLoggedIn;
		UserManagement.username = user.getUsername();
		
		frame.setVisible(true);

		frame.startEvent(new ActionListener() {
			
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//count time for user work
								
				timer = new Timer(1000, new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						i++;
													
						seconds = i % 61;
						
						if(seconds == 60) {
							minutes++;
						}
						if(minutes == 60) {
							hours++;
							minutes = 0;							
						}
						
						String h = "" + hours;
						String m = "" + minutes;
						String s = "" + seconds;
						String time = h + ":" + m + ":" + s;
												
						frame.setTimerText(time);
					}
					
				});
				
				timer.start();
			}
		});
		
		frame.stopEvent(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				timer.stop();	
			}
		});
						
		frame.viewInformationsEvent(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				list.clear();
				list.add(3);
				list.add(user);
				list.add(informations);
				
				frame.setTableText(new Client().view(list), informations);
			}
		});
		
		frame.viewObservationsEvent(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				list.clear();
				list.add(3);
				list.add(user);
				list.add(observations);
				
				frame.setTableText(new Client().view(list), observations);
			}
		});
		
		frame.logOutEvent(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				float h = (float) hours;

				if(minutes > 45)
					h += 1.75 ;
				if(minutes == 30)
					h += 1.5;
							
				list.clear();
				list.add(4);
				list.add(user);
				list.add(h);
								
				UserManagement.isLoggedIn = false;
				
				new Client().update(list);
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				
				frame.setVisible(false);
			}
		});
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		if(isLoggedIn)
			JOptionPane.showMessageDialog(null, "you have a new observation", "informational window", JOptionPane.INFORMATION_MESSAGE);
	}
	
}
