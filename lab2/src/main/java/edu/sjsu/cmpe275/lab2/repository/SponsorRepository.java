package edu.sjsu.cmpe275.lab2.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.sjsu.cmpe275.lab2.entity.Sponsor;

@Repository
public interface SponsorRepository extends CrudRepository<Sponsor, Long> {
	
	
}
