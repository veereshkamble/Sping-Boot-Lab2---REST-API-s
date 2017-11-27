package edu.sjsu.cmpe275.lab2.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.sjsu.cmpe275.lab2.entity.Opponent;

@Repository
public interface OpponentRepository extends CrudRepository<Opponent, Long> {
	
	
}
