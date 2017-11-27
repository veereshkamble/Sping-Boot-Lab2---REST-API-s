package edu.sjsu.cmpe275.lab2.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;

@Entity
public class Opponent {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private long player1;
	
	private long player2;
	
	@Autowired
	@Transient
	Player player;

	public Opponent() { }
	
	private Opponent(long id) {
		this.id = id;
	}
	
	public Opponent(Long player1, Long player2) {
		super();
		this.player1 = player1;
		this.player2 = player2;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getPlayer1() {
		return player1;
	}

	public void setPlayer1(long player1) {
		this.player1 = player1;
	}

	public long getPlayer2() {
		return player2;
	}

	public void setPlayer2(long player2) {
		this.player2 = player2;
	}
	
}
