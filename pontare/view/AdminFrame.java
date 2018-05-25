package view;

import javax.swing.JFrame;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.List;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import model.Informations;
import model.Observations;
import model.User;

public class AdminFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField observationsText;
	private JTextField workedHoursText;
	private JTextField rankText;
	private JTextField salaryText;
	private JTextField passText;
	private JTextField nameText;
	private static JTable userTable;
	private static DefaultTableModel model1;
	private static DefaultTableModel model2;
	private static DefaultTableModel model3;
	private JTextField usernameText;
	private JButton addObservations;
	private JButton addInformations;
	private JButton viewInformations;
	private JButton updateInformations;
	private JButton deleteInformations;
	private JButton viewObservations;
	private JButton updateObservations;
	private JButton deleteObservations;
	private JButton addEmployee;
	private JButton viewEmployees;
	private JButton updateEmployee;
	private JButton deleteEmployee;
	private JLabel newUsername;
	private JLabel newPassword;
	private JTextField newUsernameText;
	private JTextField newPassText;
	private JLabel lblNewName;
	private JLabel lblNewRank;
	private JLabel lblNewSalary;
	private JLabel lblNewWorkedh;
	private JLabel lblNewObserv;
	private JTextField newNameText;
	private JTextField newRankText;
	private JTextField newSalaryText;
	private JTextField newWorkedHoursText;
	private JTextField newObservationsText;
	private JLabel show_validation_here;
	
	public AdminFrame() {
		setTitle("Admin");
		setSize(600, 800);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 16));
		setResizable(false);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		addEmployee = new JButton("add employee");
		addEmployee.setFont(new Font("Tahoma", Font.PLAIN, 20));
		addEmployee.setBounds(350, 30, 220, 25);
		panel.add(addEmployee);
		
		viewEmployees = new JButton("view employees");
		viewEmployees.setFont(new Font("Tahoma", Font.PLAIN, 20));
		viewEmployees.setBounds(350, 70, 220, 25);
		panel.add(viewEmployees);
		
		updateEmployee = new JButton("update employee");
		updateEmployee.setFont(new Font("Tahoma", Font.PLAIN, 20));
		updateEmployee.setBounds(350, 110, 220, 25);
		panel.add(updateEmployee);
		
		deleteEmployee = new JButton("delete employee");
		deleteEmployee.setFont(new Font("Tahoma", Font.PLAIN, 20));
		deleteEmployee.setBounds(350, 150, 220, 25);
		panel.add(deleteEmployee);
		
		JLabel username = new JLabel("username");
		username.setFont(new Font("Tahoma", Font.PLAIN, 20));
		username.setBounds(30, 30, 150, 25);
		panel.add(username);
		
		JLabel pass = new JLabel("password");
		pass.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pass.setBounds(30, 60, 150, 25);
		panel.add(pass);
		
		JLabel rank = new JLabel("rank");
		rank.setFont(new Font("Tahoma", Font.PLAIN, 20));
		rank.setBounds(30, 180, 150, 25);
		panel.add(rank);
		
		JLabel name = new JLabel("name");
		name.setFont(new Font("Tahoma", Font.PLAIN, 20));
		name.setBounds(30, 150, 150, 30);
		panel.add(name);
		
		JLabel salary = new JLabel("salary");
		salary.setFont(new Font("Tahoma", Font.PLAIN, 20));
		salary.setBounds(30, 210, 150, 25);
		panel.add(salary);
		
		JLabel workedHours = new JLabel("workedHours");
		workedHours.setFont(new Font("Tahoma", Font.PLAIN, 20));
		workedHours.setBounds(30, 240, 150, 25);
		panel.add(workedHours);
		
		observationsText = new JTextField();
		observationsText.setFont(new Font("Tahoma", Font.PLAIN, 20));
		observationsText.setBounds(161, 270, 160, 25);
		panel.add(observationsText);
		observationsText.setColumns(10);
		
		workedHoursText = new JTextField();
		workedHoursText.setFont(new Font("Tahoma", Font.PLAIN, 20));
		workedHoursText.setColumns(10);
		workedHoursText.setBounds(161, 240, 160, 25);
		panel.add(workedHoursText);
		
		rankText = new JTextField();
		rankText.setFont(new Font("Tahoma", Font.PLAIN, 20));
		rankText.setColumns(10);
		rankText.setBounds(161, 180, 160, 25);
		panel.add(rankText);
		
		salaryText = new JTextField();
		salaryText.setFont(new Font("Tahoma", Font.PLAIN, 20));
		salaryText.setColumns(10);
		salaryText.setBounds(161, 210, 160, 25);
		panel.add(salaryText);
		
		passText = new JTextField();
		passText.setFont(new Font("Tahoma", Font.PLAIN, 20));
		passText.setColumns(10);
		passText.setBounds(161, 60, 160, 25);
		panel.add(passText);
		
		nameText = new JTextField();
		nameText.setFont(new Font("Tahoma", Font.PLAIN, 20));
		nameText.setColumns(10);
		nameText.setBounds(161, 150, 160, 25);
		panel.add(nameText);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 510, 520, 240);
		panel.add(scrollPane);
		
		userTable = new JTable();
		model1 = new DefaultTableModel(
			new Object[][] {},
			new String[] {"id", "username", "password"}
		);
		model2 = new DefaultTableModel(
				new Object[][] {},
				new String[] {"userid", "username", "name", "rank", "salary", "workedHours"}
			);
		model3 = new DefaultTableModel(
				new Object[][] {},
				new String[] {"userid", "username", "observation"}
			);
		userTable.setModel(model1);
		scrollPane.setViewportView(userTable);
		
		addObservations = new JButton("add observations");
		addObservations.setFont(new Font("Tahoma", Font.PLAIN, 20));
		addObservations.setBounds(350, 350, 220, 25);
		panel.add(addObservations);
		
		addInformations = new JButton("add informations");
		addInformations.setFont(new Font("Tahoma", Font.PLAIN, 20));
		addInformations.setBounds(350, 190, 220, 25);
		panel.add(addInformations);
		
		JLabel observations = new JLabel("observations");
		observations.setFont(new Font("Tahoma", Font.PLAIN, 20));
		observations.setBounds(30, 270, 150, 25);
		panel.add(observations);
		
		usernameText = new JTextField();
		usernameText.setFont(new Font("Tahoma", Font.PLAIN, 20));
		usernameText.setColumns(10);
		usernameText.setBounds(160, 30, 160, 25);
		panel.add(usernameText);
		
		viewInformations = new JButton("view informations");
		viewInformations.setFont(new Font("Tahoma", Font.PLAIN, 20));
		viewInformations.setBounds(350, 230, 220, 25);
		panel.add(viewInformations);
		
		updateInformations = new JButton("update informations");
		updateInformations.setFont(new Font("Tahoma", Font.PLAIN, 20));
		updateInformations.setBounds(350, 270, 220, 25);
		panel.add(updateInformations);
		
		deleteInformations = new JButton("delete informations");
		deleteInformations.setFont(new Font("Tahoma", Font.PLAIN, 20));
		deleteInformations.setBounds(350, 310, 220, 25);
		panel.add(deleteInformations);
		
		viewObservations = new JButton("view observations");
		viewObservations.setFont(new Font("Tahoma", Font.PLAIN, 20));
		viewObservations.setBounds(350, 389, 220, 25);
		panel.add(viewObservations);
		
		updateObservations = new JButton("update observations");
		updateObservations.setFont(new Font("Tahoma", Font.PLAIN, 20));
		updateObservations.setBounds(350, 427, 220, 25);
		panel.add(updateObservations);
		
		deleteObservations = new JButton("delete observations");
		deleteObservations.setFont(new Font("Tahoma", Font.PLAIN, 20));
		deleteObservations.setBounds(350, 465, 220, 25);
		panel.add(deleteObservations);
		
		newUsername = new JLabel("new username");
		newUsername.setFont(new Font("Tahoma", Font.PLAIN, 20));
		newUsername.setBounds(30, 90, 150, 25);
		panel.add(newUsername);
		
		newPassword = new JLabel("new password");
		newPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		newPassword.setBounds(30, 120, 150, 25);
		panel.add(newPassword);
		
		newUsernameText = new JTextField();
		newUsernameText.setFont(new Font("Tahoma", Font.PLAIN, 20));
		newUsernameText.setColumns(10);
		newUsernameText.setBounds(160, 90, 160, 25);
		panel.add(newUsernameText);
		
		newPassText = new JTextField();
		newPassText.setFont(new Font("Tahoma", Font.PLAIN, 20));
		newPassText.setColumns(10);
		newPassText.setBounds(160, 120, 160, 25);
		panel.add(newPassText);
		
		lblNewName = new JLabel("new name");
		lblNewName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewName.setBounds(30, 300, 150, 30);
		panel.add(lblNewName);
		
		lblNewRank = new JLabel("new rank");
		lblNewRank.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewRank.setBounds(30, 330, 150, 25);
		panel.add(lblNewRank);
		
		lblNewSalary = new JLabel("new salary");
		lblNewSalary.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewSalary.setBounds(30, 360, 150, 25);
		panel.add(lblNewSalary);
		
		lblNewWorkedh = new JLabel("new workedH.");
		lblNewWorkedh.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewWorkedh.setBounds(30, 390, 150, 25);
		panel.add(lblNewWorkedh);
		
		lblNewObserv = new JLabel("new observ.");
		lblNewObserv.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewObserv.setBounds(30, 420, 150, 25);
		panel.add(lblNewObserv);
		
		newNameText = new JTextField();
		newNameText.setFont(new Font("Tahoma", Font.PLAIN, 20));
		newNameText.setColumns(10);
		newNameText.setBounds(161, 300, 160, 25);
		panel.add(newNameText);
		
		newRankText = new JTextField();
		newRankText.setFont(new Font("Tahoma", Font.PLAIN, 20));
		newRankText.setColumns(10);
		newRankText.setBounds(161, 330, 160, 25);
		panel.add(newRankText);
		
		newSalaryText = new JTextField();
		newSalaryText.setFont(new Font("Tahoma", Font.PLAIN, 20));
		newSalaryText.setColumns(10);
		newSalaryText.setBounds(161, 360, 160, 25);
		panel.add(newSalaryText);
		
		newWorkedHoursText = new JTextField();
		newWorkedHoursText.setFont(new Font("Tahoma", Font.PLAIN, 20));
		newWorkedHoursText.setColumns(10);
		newWorkedHoursText.setBounds(161, 390, 160, 25);
		panel.add(newWorkedHoursText);
		
		newObservationsText = new JTextField();
		newObservationsText.setFont(new Font("Tahoma", Font.PLAIN, 20));
		newObservationsText.setColumns(10);
		newObservationsText.setBounds(161, 420, 160, 25);
		panel.add(newObservationsText);
		
		show_validation_here = new JLabel("");
		show_validation_here.setForeground(Color.RED);
		show_validation_here.setFont(new Font("Tahoma", Font.PLAIN, 25));
		show_validation_here.setBackground(Color.RED);
		show_validation_here.setBounds(30, 460, 300, 30);
		panel.add(show_validation_here);
	}
	
	// actions event
	public void addEmployeeEvent(ActionListener listener) {
		addEmployee.addActionListener(listener);
	}
	
	public void viewEmployeesEvent(ActionListener listener) {
		viewEmployees.addActionListener(listener);
	}
	
	public void updateEmployeeEvent(ActionListener listener) {
		updateEmployee.addActionListener(listener);
	}
	
	public void deleteEmployeeEvent(ActionListener listener) {
		deleteEmployee.addActionListener(listener);
	}
	
	public void addInformationsEvent(ActionListener listener) {
		addInformations.addActionListener(listener);
	}
	
	public void viewInformationsEvent(ActionListener listener) {
		viewInformations.addActionListener(listener);
	}
	
	public void updateInformationsEvent(ActionListener listener) {
		updateInformations.addActionListener(listener);
	}
	
	public void deleteInformationsEvent(ActionListener listener) {
		deleteInformations.addActionListener(listener);
	}
	
	public void addObservationsEvent(ActionListener listener) {
		addObservations.addActionListener(listener);
	}
	
	public void viewObservationsEvent(ActionListener listener) {
		viewObservations.addActionListener(listener);
	}
	
	public void updateObservationsEvent(ActionListener listener) {
		updateObservations.addActionListener(listener);
	}
	
	public void deleteObservationsEvent(ActionListener listener) {
		deleteObservations.addActionListener(listener);
	}
	
	public void validateNameEvent(KeyListener listener) {
		nameText.addKeyListener(listener);
	}
	
	public void SetValidationText(String text) {
		show_validation_here.setText(text);
	}
	
	// get text from text fields
	public String getUsernameText() {
		return usernameText.getText();
	}
	
	public String getPassText() {
		return passText.getText();
	}
	
	public String getNameText() {
		return nameText.getText();
	}
	
	public String getRankText() {
		return rankText.getText();
	}
	
	public int getSalaryText() {
		return Integer.parseInt(salaryText.getText());
	}
	
	public float getWorkedHoursText() {
		return Float.parseFloat(workedHoursText.getText());
	}
		
	public String getObservationText() {
		return observationsText.getText();
	}
	
	public String getNewUsernameText() {
		return newUsernameText.getText();
	}
	
	public String getNewPassText() {
		return newPassText.getText();
	}
	
	public String getNewNameText() {
		return newNameText.getText();
	}
	
	public String getNewRankText() {
		return newRankText.getText();
	}
	
	public int getNewSalaryText() {
		return Integer.parseInt(newSalaryText.getText());
	}
	
	public float getNewWorkedHoursText() {
		return Float.parseFloat(newWorkedHoursText.getText());
	}
		
	public String getNewObservationText() {
		return newObservationsText.getText();
	}
	
	@SuppressWarnings("unchecked")
	public static void setTable(List<Object> objects, int informationType) {
		model1.setRowCount(0);
		
		// show informations
		if(objects.size() != 0 && informationType == 0 && objects.get(0).getClass().isInstance(new User())) {
			List<User> users = (List<User>) (Object) objects;			
			
			for(User user : users) {
				Object[] o = new Object[3];
				o[0] = user.getId();
				o[1] = user.getUsername();
				o[2] = user.getPassword();
								
				model1.addRow(o);
				userTable.setModel(model1);
			}
		}
		
		model2.setRowCount(0);
		
		// show informations
		if(objects.size() != 0 && informationType == 1 && objects.get(0).getClass().isInstance(new Informations())) {
			List<Informations> informations = (List<Informations>) (Object) objects;			
			
			for(Informations info : informations) {
				Object[] o = new Object[6];
				o[0] = info.getUserId();
				o[1] = info.getUsername();
				o[2] = info.getName();
				o[3] = info.getRank();
				o[4] = info.getSalary();
				o[5] = info.getWorkedHours();
				
				model2.addRow(o);
				userTable.setModel(model2);
			}
		}
		
		model3.setRowCount(0);
		
		// show observations
		if(objects.size() != 0 && informationType == 2 && objects.get(0).getClass().isInstance(new Observations())) {
			List<Observations> observations = (List<Observations>) (Object) objects;			
			
			for(Observations obs : observations) {
				Object[] o = new Object[3];
				o[0] = obs.getUserId();
				o[1] = obs.getUsername();
				o[2] = obs.getObservation();
				
				model3.addRow(o);
				userTable.setModel(model3);
			}
		}
	}
}
