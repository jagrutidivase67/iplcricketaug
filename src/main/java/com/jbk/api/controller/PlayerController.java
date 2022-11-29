package com.jbk.api.controller;

import java.util.List;
import java.util.Scanner;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jbk.api.entity.Player;
import com.jbk.api.exception.PlayerAlreadyPresentException;
import com.jbk.api.service.PlayerService;

@RestController
@RequestMapping(value="/player")
public class PlayerController {
	@Autowired
	private PlayerService playerservice;

	@PostMapping(value = "/saveplayer")
	public ResponseEntity<Boolean> PlayerSave( @Valid @RequestBody Player player) {
		boolean isAvailable = playerservice.PlayerSave(player);
		if (isAvailable) {
			return new ResponseEntity<Boolean>(isAvailable, HttpStatus.CREATED);
		}
		throw new PlayerAlreadyPresentException("player Already present Exception<<"+player.getplayerJersyNo());

	}

	@GetMapping(value = "/getplayerbyjersyno/{playerJersyNo}")
	public ResponseEntity<Player> getPlayerByJersyNo(@PathVariable int playerJersyNo) {
		Player player = playerservice.getPlayerByJersyNo(playerJersyNo);
		if (player != null) {
			return new ResponseEntity<Player>(player, HttpStatus.OK);
		} else {
			return  new ResponseEntity<Player>(player, HttpStatus.OK);
		}
	}

	@DeleteMapping(value = "/dropoutplayerbyjersyno/{playerJersyNo}")
	public ResponseEntity<Boolean> injuredPlayer(@PathVariable int playerJersyNo) {
		boolean isInjured = playerservice.injuredPlayer(playerJersyNo);
		if (isInjured) {
			return new ResponseEntity<Boolean>(isInjured, HttpStatus.OK);
		} else

			return new ResponseEntity<Boolean>(isInjured, HttpStatus.OK);

	}

	@GetMapping(value = "/getallplayersofteam/{list}")
	public ResponseEntity<List<Player>> getallPlayers() {
		List<Player> list = playerservice.getallPlayers();
		if (!list.isEmpty()) {
			return new ResponseEntity<>(list, HttpStatus.OK);
		} else
			return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@PutMapping(value = "/updateplayerbyteam/{playerJersyNo}")
	public ResponseEntity<Boolean> UpdateplayerTeam(@Valid @RequestBody Player player, @PathVariable int playerJersyNo) {
		boolean playerIsUpdated = playerservice.UpdateplayerTeam(player);
		if (playerIsUpdated) {
			return new ResponseEntity<Boolean>(playerIsUpdated, HttpStatus.OK);
		} else
			return new ResponseEntity<Boolean>(playerIsUpdated, HttpStatus.OK);

	}

	@GetMapping(value = "/getplayerbyname/{PlayerName}")
	public ResponseEntity<List<Player>> getPlayerByName(@PathVariable String PlayerName) {
		List<Player> player = playerservice.getPlayerByName(PlayerName);
		if (player != null) {
			return new ResponseEntity<List<Player>>(player, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<Player>>(player, HttpStatus.OK);
		}
		

	}

	@GetMapping(value = "/getmaxrunplayers")
	public ResponseEntity<List<Player>> getmaxrunplayer() {
		List<Player> maxrunplayer = playerservice.getmaxrunplayer();
		return new ResponseEntity<List<Player>>(maxrunplayer, HttpStatus.OK);
	}
	@GetMapping(value="/getmaxwicketplayers")
	public ResponseEntity<List<Player>>getmaxwicketplayer()
	{
		List<Player>maxwicketplayer=playerservice.getmaxwicketplayer();
		return new ResponseEntity<List<Player>>(maxwicketplayer, HttpStatus.OK);
	
		
	}
	@DeleteMapping(value="/deleteallplayers")
		public ResponseEntity<String>deleteAllPlayers(){
		String msg=playerservice.deleteAllPlayers();
		if(msg!=null)
		{
			return new ResponseEntity<String>(msg, HttpStatus.OK);
			
		}
		return new ResponseEntity<String>(msg, HttpStatus.OK);
				
	}
	@GetMapping(value = "/getplayerbyteamname/{playerTeam}")
	public ResponseEntity<List<Player>> getPlayerByTeamName(@PathVariable String playerTeam) {
		List<Player> player = playerservice.getPlayerByTeamName(playerTeam);
		if (player != null) {
			return new ResponseEntity<List<Player>>(player, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<Player>>(player, HttpStatus.OK);

		
	}
}
	@PostMapping(value = "/uploadsheet")
	public ResponseEntity<String> uploadsheet(@RequestParam MultipartFile File, HttpSession session) {
		String msg = playerservice.uploadsheet(File, session);
		return new ResponseEntity<String>(msg, HttpStatus.OK);

	
	
	}
	
}
