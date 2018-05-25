package model;

import java.io.Serializable;
import javax.persistence.*;


@Entity
@Table(name = "OBSERVATIONS")
public class Observations implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
    @JoinColumn(name = "userId")
	private int userId;
    
    @JoinColumn(name = "username")
    private String username;
	
	@Column(name = "observation")
	private String observation;
	
	public Observations() {}

	public Observations(int userId, String username, String observation) {
		this.userId = userId;
		this.username = username;
		this.observation = observation;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}
	
	
	
}
