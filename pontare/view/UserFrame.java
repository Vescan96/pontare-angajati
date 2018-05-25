package view;

import javax.swing.JFrame;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import model.Informations;
import model.Observations;

public class UserFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField timer;
	private JButton start;
	private JButton stop;
	private JButton viewInformations;
	private JButton viewObservations;
	private JTable table;
	private DefaultTableModel model1, model2;
	private JScrollPane scrollPane;
	private JButton logOut;

	public UserFrame() {
		setResizable(false);
		getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 20));
		setTitle("User");
		setSize(500, 500);
		setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel time = new JLabel("time : ");
		time.setFont(new Font("Tahoma", Font.PLAIN, 20));
		time.setBounds(160, 50, 80, 30);
		panel.add(time);
		
		start = new JButton("start");
		start.setFont(new Font("Tahoma", Font.PLAIN, 20));
		start.setBounds(30, 50, 100, 30);
		panel.add(start);
		
		stop = new JButton("stop");
		stop.setFont(new Font("Tahoma", Font.PLAIN, 20));
		stop.setBounds(370, 50, 100, 30);
		panel.add(stop);
		
		timer = new JTextField();
		timer.setText("0:00:00");
		timer.setEditable(false);
		timer.setFont(new Font("Tahoma", Font.PLAIN, 25));
		timer.setBounds(230, 50, 110, 30);
		panel.add(timer);
		
		viewInformations = new JButton("view informations");
		viewInformations.setFont(new Font("Tahoma", Font.PLAIN, 20));
		viewInformations.setBounds(30, 120, 200, 30);
		panel.add(viewInformations);
		
		viewObservations = new JButton("view observations");
		viewObservations.setFont(new Font("Tahoma", Font.PLAIN, 20));
		viewObservations.setBounds(270, 120, 200, 30);
		panel.add(viewObservations);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 200, 440, 180);
		panel.add(scrollPane);
		
		table = new JTable();
		model1 = new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"userid", "username", "name", "rank", "salary", "workedHours"
			}
		);
		
		model2 = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"userid", "username", "observation"
				}
			);
		table.setModel(model1);
		scrollPane.setViewportView(table);
		table.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		logOut = new JButton("log out");
		logOut.setFont(new Font("Tahoma", Font.PLAIN, 20));
		logOut.setBounds(175, 400, 150, 30);
		panel.add(logOut);
		
	}
	
	public void startEvent(ActionListener listener) {
		start.addActionListener(listener);
	}
	
	public void stopEvent(ActionListener listener) {
		stop.addActionListener(listener);
	}
	
	public void viewInformationsEvent(ActionListener listener) {
		viewInformations.addActionListener(listener);
	}
	
	public void viewObservationsEvent(ActionListener listener) {
		viewObservations.addActionListener(listener);
	}
	
	public void logOutEvent(ActionListener listener) {
		logOut.addActionListener(listener);
	}
	
	public void setTimerText(String time) {
		timer.setText(time);
	}
	
	@SuppressWarnings("unchecked")
	public void setTableText(List<Object> objects, int informationType) {
		model1.setRowCount(0);
		
		// show informations
		if(objects.size() != 0 && informationType == 1 && objects.get(0).getClass().isInstance(new Informations())) {
			List<Informations> informations = (List<Informations>) (Object) objects;			
			
			for(Informations info : informations) {
				Object[] o = new Object[7];
				o[0] = info.getUserId();
				o[1] = info.getUsername();
				o[2] = info.getName();
				o[3] = info.getRank();
				o[4] = info.getSalary();
				o[5] = info.getWorkedHours();
				
				model1.addRow(o);
				table.setModel(model1);
			}
		}
		
		model2.setRowCount(0);
		
		// show observations
		if(objects.size() != 0 && informationType == 2 && objects.get(0).getClass().isInstance(new Observations())) {
			List<Observations> observations = (List<Observations>) (Object) objects;			
			
			for(Observations obs : observations) {
				Object[] o = new Object[3];
				o[0] = obs.getUserId();
				o[1] = obs.getUsername();
				o[2] = obs.getObservation();
				
				model2.addRow(o);
				table.setModel(model2);
			}
		}
	}
	
}
