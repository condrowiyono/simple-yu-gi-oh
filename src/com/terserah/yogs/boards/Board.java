package com.terserah.yogs.boards;

import com.terserah.yogs.boards.player.Deck;
import com.terserah.yogs.boards.player.Phase;
import com.terserah.yogs.boards.player.Player;
import com.terserah.yogs.cards.Card;

public class Board {
	public boolean isGameEnds() {
		return gameEnds;
	}

	public void setGameEnds(boolean gameEnds) {
		this.gameEnds = gameEnds;
	}

	private boolean gameEnds = false;
	 private  Player activePlayer;
	
	public void setActivePlayer(Player activePlayer) {
		this.activePlayer = activePlayer;
	}

	public void setOpponentPlayer(Player opponentPlayer) {
		this.opponentPlayer = opponentPlayer;
	}

	public Player getWinner() {
		return winner;
	}

	public void setWinner(Player winner) {
		this.winner = winner;
		gameEnds = true;
	}

	public  Player getActivePlayer() {
		return activePlayer;
	}

	public  Player getOpponentPlayer() {
		return opponentPlayer;
	}

	private  Player opponentPlayer;
	private Player winner;
	
	public Board(){
		Card.setBoard(this);
	}
	
	public void whoStarts(Player p1, Player p2){
			this.activePlayer = p1;
			this.opponentPlayer = p2;
	}
	
	
	
	public void startGame(Player p1, Player p2){
		whoStarts(p1,p2);
		Deck d1= new Deck(activePlayer.getDeck().getDeck());
		Deck d2= new Deck(opponentPlayer.getDeck().getDeck());
		activePlayer.getField().getDeck().setDeck(d1.getDeck());
		opponentPlayer.getField().getDeck().setDeck(d2.getDeck());
		
		activePlayer.getField().addNCardsToHand(4);
		opponentPlayer.getField().addNCardsToHand(3);
		
		activePlayer.getField().setPhase(Phase.MAIN1);
	}
	
	public void nextPlayer(){
		
		Player tmp = this.activePlayer;
		this.activePlayer = opponentPlayer;
		opponentPlayer = tmp;
		if(this.activePlayer.getField().getDeck().getDeck().size()==0)
		{
			this.winner = this.opponentPlayer;
			return;
		}
		activePlayer.getField().addCardToHand();
		
		activePlayer.setSummonedMonster(false);
		opponentPlayer.setSummonedMonster(false);
		
		activePlayer.getField().setPhase(Phase.MAIN1);
		opponentPlayer.getField().setPhase(Phase.MAIN1);
		
	}
	
	
	
}