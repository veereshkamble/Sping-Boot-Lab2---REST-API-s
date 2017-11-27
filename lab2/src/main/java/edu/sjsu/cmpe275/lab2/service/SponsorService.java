package edu.sjsu.cmpe275.lab2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.sjsu.cmpe275.lab2.entity.Sponsor;
import edu.sjsu.cmpe275.lab2.repository.SponsorRepository;

@Service
public class SponsorService {
	
	@Autowired
	private SponsorRepository sponsorRepository;
	
	@Transactional
	public Sponsor save(Sponsor sponsor) {
		return sponsorRepository.save(sponsor);
	}
	
	@Transactional
	public Sponsor findById(long id) {
		return sponsorRepository.findOne(id);
	}
	
	@Transactional
	public void delete(Sponsor sponsor) {
		sponsorRepository.delete(sponsor);;
		return;
	}
	
	@Transactional
	public Object findAll() {
		return sponsorRepository.findAll();
	}

}
