package edu.sjsu.cmpe275.lab2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.sjsu.cmpe275.lab2.entity.Player;
import edu.sjsu.cmpe275.lab2.entity.Sponsor;
import edu.sjsu.cmpe275.lab2.service.PlayerService;

@RestController
public class PlayerController {
	
	@Autowired
	PlayerService playerService;
	
	@RequestMapping(value = "/player", method = RequestMethod.GET)
	@ResponseBody
	public Object findAll() {
		return playerService.findAll();
	}
	
	@RequestMapping(value = "/player", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Player> createPlayer(@RequestBody Player player) {
		String id = "";
		
		boolean requiredFields = true;
		if(player.getFirstName() == "" || player.getLastName() == "" || player.getEmail() == "") {
			requiredFields = false;
		}
		
		
		if(requiredFields) {
			try {
				playerService.save(player);
			}
			catch (Exception e) {
				System.out.println("FirstName, LastName or Email fields cannot be empty");
				//return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
			}
			//return ResponseEntity.ok(json);
			//return sponsorService.findById(sponsor.getId());
			return ResponseEntity.ok(playerService.findById(player.getId()));
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		
	}
	
	@RequestMapping(value = "/player/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Player> getPlayer(@PathVariable("id") long id){
		if(playerService.findById(id) != null){
			try {
				playerService.findById(id);
				return ResponseEntity.ok(playerService.findById(id));
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
	
	@RequestMapping(value = "/player/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Player> updatePlayer(@PathVariable("id") long id, @RequestBody Player player) {
		
		boolean requiredFields = true;
		
		if(player.getFirstName() == "" || player.getLastName() == "" || player.getEmail() == "") {
			requiredFields = false;
		}
		
		if(playerService.findById(id) != null) {
			
			
			try {
				if(requiredFields) {
					
					try {
						player.setId(id);
						playerService.save(player);
					}
					catch (Exception e) {
						System.out.println("FirstName, LastName or Email fields cannot be empty");
						//return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
					}
					return ResponseEntity.ok(playerService.findById(player.getId()));
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
	
	@RequestMapping(value = "/player/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Player> deletePlayer(@PathVariable("id") long id) {
		
		Player playerToDelete = playerService.findById(id);
		
		if(playerToDelete != null) {
			
			try {
				playerService.delete(playerToDelete);
			}
			catch (Exception e) {
				System.out.println("Sponsor with ID: " + id + "does not exist");
			}
			return ResponseEntity.ok(playerToDelete);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
	}
	

}
