package edu.sjsu.cmpe275.lab2.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
public class Player implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
	private long id;
	
	@NotNull
	private String firstname;
	
	@NotNull
	private String lastname;
	
	@NotNull
	private String email;
	
	private String description;
	
	private Address address;
	
	private Sponsor sponsor;
	
	@OneToMany(targetEntity = Opponent.class, mappedBy = "player2", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Player> opponents;
	
	private Player() { }
	
	public Player(Long id) {
		this.id = id;
	}

	public Player(String firstName, String lastName, String email, String description, Address address, Sponsor sponsor,
			List<Player> opponents) {
		super();
		this.firstname = firstName;
		this.lastname = lastName;
		this.email = email;
		this.description = description;
		this.address = address;
		this.sponsor = sponsor;
		this.opponents = opponents;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstname;
	}

	public void setFirstName(String firstName) {
		this.firstname = firstName;
	}

	public String getLastName() {
		return lastname;
	}

	public void setLastName(String lastName) {
		this.lastname = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Sponsor getSponsor() {
		return sponsor;
	}

	public void setSponsor(Sponsor sponsor) {
		this.sponsor = sponsor;
	}

	public List<Player> getOpponents() {
		return opponents;
	}

	public void setOpponents(List<Player> opponents) {
		this.opponents = opponents;
	}
	
	
	

}
