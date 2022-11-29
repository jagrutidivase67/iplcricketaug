package com.jbk.api.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import com.jbk.api.entity.Player;

public interface PlayerService {
	public boolean PlayerSave(Player player);
	public Player getPlayerByJersyNo(int PlayerJersyNo);
	public boolean injuredPlayer(int PlayerJersyNo);
	 public List<Player> getallPlayers(); 
	 public Boolean UpdateplayerTeam(Player player);
	 public List <Player> getPlayerByName(String playerName);
	 public List <Player> getmaxrunplayer();
	 public String uploadsheet( MultipartFile File,HttpSession session);
	    
	 public List<Player>getPlayerByTeamName(String playerTeam);
		
	 public List<Player> getmaxwicketplayer();
	   
	 public String deleteAllPlayers();





}
