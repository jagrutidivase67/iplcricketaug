package com.jbk.api.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
public class Player {
	@Id
	@Min(1)
	private int  playerJersyNo;
	
	@NotNull (message = "player name  should not be null")
	private String playerName;
	
	@NotNull (message = "player country  should not be null")
    private String playerCountry;
	
	@NotNull (message = "player team  should not be null")
    private String playerTeam;
	
	@Min(16)
	private int playerAge;
	
	@NotNull (message = "player role  should not be null")
	private String playerRole;
	
	private int  playerRuns;

	private int playerWicket;
	public Player() {
		// TODO Auto-generated constructor stub
	}
	public Player(int playerJersyNo, String playerName, String playerCountry, String playerTeam, int playerAge,
			String playerRole, int playerRuns, int playerWicket) {
		super();
		this.playerJersyNo = playerJersyNo;
		this.playerName = playerName;
		this.playerCountry = playerCountry;
		this.playerTeam = playerTeam;
		this.playerAge = playerAge;
		this.playerRole = playerRole;
		this.playerRuns = playerRuns;
		this.playerWicket = playerWicket;
	}
	public int getplayerJersyNo() {
		return playerJersyNo;
	}
	public void setplayerJersyNo(int playerJersyNo) {
		this.playerJersyNo = playerJersyNo;
	}
	public String getPlayerName() {
		return playerName;
	}
	public void setplayerName(String playerName) {
		this.playerName = playerName;
	}
	public String getPlayerCountry() {
		return playerCountry;
	}
	public void setplayerCountry(String playerCountry) {
		this.playerCountry = playerCountry;
	}
	public String getPlayerTeam() {
		return playerTeam;
	}
	public void setplayerTeam(String playerTeam) {
		this.playerTeam = playerTeam;
	}
	public int getPlayerAge() {
		return playerAge;
	}
	public void setplayerAge(int playerAge) {
		this.playerAge = playerAge;
	}
	public String getplayerRole() {
		return playerRole;
	}
	public void setplayerRole(String playerRole) {
		this.playerRole = playerRole;
	}
	public int getplayerRuns() {
		return playerRuns;
	}
	public void setplayerRuns(int playerRuns) {
		this.playerRuns = playerRuns;
	}
	public int getplayerWicket() {
		return playerWicket;
	}
	public void setplayerWicket(int playerWicket) {
		this.playerWicket = playerWicket;
	}
	@Override
	public String toString() {
		return "Player [playerJersyNo=" + playerJersyNo + ", playerName=" + playerName + ", playerCountry="
				+ playerCountry + ", playerTeam=" + playerTeam + ", playerAge=" + playerAge + ", playerRole="
				+ playerRole + ", playerRuns=" + playerRuns + ", playerWicket=" + playerWicket + "]";
	}
	
}