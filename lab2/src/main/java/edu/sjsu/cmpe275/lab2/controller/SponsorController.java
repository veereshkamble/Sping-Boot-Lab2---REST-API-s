package edu.sjsu.cmpe275.lab2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.sjsu.cmpe275.lab2.entity.Address;
import edu.sjsu.cmpe275.lab2.entity.Sponsor;
import edu.sjsu.cmpe275.lab2.service.SponsorService;

@RestController
public class SponsorController {

	@Autowired
	private SponsorService sponsorService;
	
	@RequestMapping(value = "/sponsor", method = RequestMethod.GET)
	@ResponseBody
	public Object findAll() {
		return sponsorService.findAll();
	}
	
	@RequestMapping(value = "/sponsor", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Sponsor> createSponsor(@RequestBody Sponsor sponsor) {
		String id = "";
		
		if(sponsor.getName() != "") {
			try {
				sponsorService.save(sponsor);
			}
			catch (Exception e) {
				System.out.println("Name field cannot be empty");
			}
			//return ResponseEntity.ok(json);
			//return sponsorService.findById(sponsor.getId());
			return ResponseEntity.ok(sponsorService.findById(sponsor.getId()));
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
	@RequestMapping(value = "/sponsor/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Sponsor> getSponsor(@PathVariable("id") long id) {
		if(sponsorService.findById(id) != null){
			try {
				sponsorService.findById(id);
				return ResponseEntity.ok(sponsorService.findById(id));
			} 
			catch (Exception e) {
				//return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
				System.out.println("Sponsor with ID: " + id +"does not exist");
			} 
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return null;
	}
	
	@RequestMapping(value = "/sponsor/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Sponsor> updateSponsor(@PathVariable("id") long id, @RequestBody Sponsor sponsor) {
		
		if(sponsorService.findById(id) != null) {
			
			try {
				if(sponsor.getName() != "") {
					
					try {
						sponsor.setId(id);
						sponsorService.save(sponsor);
					}
					catch (Exception e) {
						System.out.println("Name field cannot be empty");
					}
					return ResponseEntity.ok(sponsorService.findById(sponsor.getId()));
				} else {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
				}
			}
			catch (Exception e) {
				System.out.println("Sponsor with ID: " + id +"does not exist");
			}
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return null;
	}
	
	@RequestMapping(value = "/sponsor/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Sponsor> deleteSponsor(@PathVariable("id") long id) {
		
		Sponsor sponsorToDelete = sponsorService.findById(id);
		
		if(sponsorToDelete != null) {
			
			try {
				sponsorService.delete(sponsorToDelete);
			}
			catch (Exception e) {
				System.out.println("Sponsor with ID: " + id + "does not exist");
			}
			return ResponseEntity.ok(sponsorToDelete);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
	}
	
	/*	@RequestMapping(value = "/sponsor", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Sponsor create(@RequestParam(value = "name", required = true) String name, @RequestParam(value = "description", required = false) String description, @RequestBody Address address) {
		String id = "";
		Address address1 = new Address(address.getCity(), address.getState(), address.getStreet(), address.getZip());
		Sponsor sponsor = new Sponsor(name, description, address1);
		
		try {
			sponsorService.save(sponsor);
		}
		catch (Exception e) {
			//return "Error creating the Sponsor: " + e.toString();
		}
			return sponsorService.findById(sponsor.getId());
			//return "User successfully created";
	} */
}
