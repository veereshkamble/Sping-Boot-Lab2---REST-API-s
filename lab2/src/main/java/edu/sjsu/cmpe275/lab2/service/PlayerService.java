package edu.sjsu.cmpe275.lab2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.sjsu.cmpe275.lab2.entity.Player;
import edu.sjsu.cmpe275.lab2.entity.Sponsor;
import edu.sjsu.cmpe275.lab2.repository.PlayerRepository;

@Service
public class PlayerService {
	
	@Autowired
	private PlayerRepository playerRepository;
	
	@Transactional
	public Player save(Player player) {
		return playerRepository.save(player);
	}
	
	@Transactional
	public Player findById(long id) {
		return playerRepository.findOne(id);
	}
	
	@Transactional
	public void delete(Player player) {
		playerRepository.delete(player);;
		return;
	}
	
	@Transactional
	public Object findAll() {
		return playerRepository.findAll();
	}
}
