package edu.sjsu.cmpe275.lab2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.sjsu.cmpe275.lab2.entity.Opponent;
import edu.sjsu.cmpe275.lab2.entity.Sponsor;
import edu.sjsu.cmpe275.lab2.repository.OpponentRepository;

@Service
public class OpponentService {

	@Autowired
	private OpponentRepository opponentRepository;
	
	@Transactional
	public Opponent save(Opponent opponent) {
		return opponentRepository.save(opponent);
	}
	
	@Transactional
	public Opponent findById(long id) {
		return opponentRepository.findOne(id);
	}
	
	@Transactional
	public void delete(Opponent opponent) {
		opponentRepository.delete(opponent);
		return;
	}
	
	@Transactional
	public Object findAll() {
		return opponentRepository.findAll();
	}
}
