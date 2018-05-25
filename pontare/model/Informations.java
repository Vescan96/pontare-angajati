package model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "INFORMATIONS")
public class Informations implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
    @JoinColumn(name = "userId")
	private int userId;
	
    @JoinColumn(name = "username")
	private String username;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "rank")
	private String rank;
	
	@Column(name = "salary")
	private int salary;
	
	@Column(name = "workedHours")
	private float workedHours;
	
	public Informations() {}
	
	public Informations(int userId, String username, String name, String rank, int salary, float workedHours) {
		this.userId = userId;
		this.username = username;
		this.name = name;
		this.rank = rank;
		this.salary = salary;
		this.workedHours = workedHours;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public float getWorkedHours() {
		return workedHours;
	}

	public void setWorkedHours(float workedHours) {
		this.workedHours = workedHours;
	}
	
	
	
}
