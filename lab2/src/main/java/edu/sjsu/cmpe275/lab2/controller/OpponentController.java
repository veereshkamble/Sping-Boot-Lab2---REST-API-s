package edu.sjsu.cmpe275.lab2.controller;

import java.util.List;

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

import edu.sjsu.cmpe275.lab2.entity.Opponent;
import edu.sjsu.cmpe275.lab2.entity.Player;
import edu.sjsu.cmpe275.lab2.service.OpponentService;
import edu.sjsu.cmpe275.lab2.service.PlayerService;

@RestController
public class OpponentController {

	@Autowired
	OpponentService opponentService;
	
	@Autowired
	PlayerService playerService;
	
	@RequestMapping(value = "/opponent", method = RequestMethod.GET)
	@ResponseBody
	public Object findAll() {
		return opponentService.findAll();
	}
	
	@RequestMapping(value = "/opponent/{id1}/{id2}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Player> createOpponent(@PathVariable("id1") long id1, @PathVariable("id2") long id2) {
		String id = "";
		
		
		
		if(playerService.findById(id1) != null && playerService.findById(id2) != null) {
			
			List<Opponent> opponents = (List<Opponent>) opponentService.findAll();
			for(Opponent opp: opponents) {
				if((opp.getPlayer1() == id1 && opp.getPlayer2() == id2) || (opp.getPlayer1() == id2 && opp.getPlayer2() == id1))  {
					System.out.println("Opponents already exist");
					
					System.out.println("Opponents exist " + opp.getId());
					System.out.println("Opponents exist " + opp.getPlayer1());
					System.out.println("Opponents exist " + opp.getPlayer2());
					
					return ResponseEntity.status(HttpStatus.OK).body(null);
					
					
				}
			}
			
			Opponent opponent = new Opponent();
			
			opponent.setPlayer1(id1);
			opponent.setPlayer2(id2);
			
			opponentService.save(opponent);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
			
			
		
		
		List<Opponent> opponents = (List<Opponent>) opponentService.findAll();
		System.out.println("Opponents Size=" + opponents.size());
		
		for(Opponent opp: opponents) {
			System.out.println(opp.getId() + " " + opp.getPlayer1() + " " + opp.getPlayer2());
		}
		
		return null;
	
	}
	
	@RequestMapping(value = "/opponent/{id1}/{id2}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Player> deleteOpponent(@PathVariable("id1") long id1, @PathVariable("id2") long id2) {
		String id = "";
		
		
		
		if(playerService.findById(id1) != null && playerService.findById(id2) != null) {
			
			Opponent opponent = new Opponent();
			List<Opponent> opponents = (List<Opponent>) opponentService.findAll();
			for(Opponent opp: opponents) {
				if((opp.getPlayer1() == id1 && opp.getPlayer2() == id2) || (opp.getPlayer1() == id2 && opp.getPlayer2() == id1))  {
					System.out.println("Opponents exist");
					
					
					opponent.setId(opp.getId());
					opponent.setPlayer1(opp.getPlayer1());
					opponent.setPlayer2(opp.getPlayer2());
					
					System.out.println("Opponents ID " + opponent.getId());
					System.out.println("Opponents Player 1 " + opponent.getPlayer1());
					System.out.println("Opponents Player 2 " + opponent.getPlayer2());
					
					opponentService.delete(opponent);
					
					return ResponseEntity.status(HttpStatus.OK).body(null);
				} else {
					return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);				
					}
			}
		
			
			
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
			
			
		
		
		List<Opponent> opponents = (List<Opponent>) opponentService.findAll();
		System.out.println("Opponents Size=" + opponents.size());
		
		for(Opponent opp: opponents) {
			System.out.println(opp.getId() + " " + opp.getPlayer1() + " " + opp.getPlayer2());
		}
		
		return null;
	
	}
}
