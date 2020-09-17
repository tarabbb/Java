package student_player;

import boardgame.Board;
import boardgame.Move;

import Saboteur.SaboteurPlayer;
import Saboteur.cardClasses.SaboteurBonus;
import Saboteur.cardClasses.SaboteurCard;
import Saboteur.cardClasses.SaboteurDestroy;
import Saboteur.cardClasses.SaboteurDrop;
import Saboteur.cardClasses.SaboteurMalus;
import Saboteur.cardClasses.SaboteurMap;
import Saboteur.cardClasses.SaboteurTile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import Saboteur.SaboteurBoardState;
import Saboteur.SaboteurMove;



/** A player file submitted by a student. */
public class StudentPlayer extends SaboteurPlayer {
	private int[] nug={0,0};
	/**
	 * You must modify this constructor to return your student number. This is
	 * important, because this is what the code that runs the competition uses to
	 * associate you with your agent. The constructor should do nothing else.
	 */
	public StudentPlayer() {
		super("       ");
	}

	/**
	 * This is the primary method that you need to implement. The ``boardState``
	 * object contains the current state of the game, which your agent must use to
	 * make decisions.
	 */
	public Move chooseMove(SaboteurBoardState boardState) {
        if(boardState.getWinner()!=Board.NOBODY){
        	nug[0]=0;
        	nug[1]=0;
        }
        boolean kn=knownegget(boardState);
     // get tiles from hiddenboard
     		SaboteurTile[][] mapTiles = boardState.getHiddenBoard();
       
		// get all legal moves
		List<SaboteurMove> legalMoves = boardState.getAllLegalMoves();
		List<SaboteurMove> tileMovesf = legalMoves.stream()
				.filter(move -> move.getCardPlayed() instanceof SaboteurTile).collect(Collectors.toList());
		
		// get handcards
		List<SaboteurCard> handCards = boardState.getCurrentPlayerCards();
		
		//if nugget in (12,7) &&(12,6) is not connected with (12,7),destroy(12,6)
		if(nug[0]==12 && nug[1]==7){
			if(mapTiles[12][6]!=null){
				if(mapTiles[12][6].getPath()[2][1]!=1 || !isConnected(mapTiles[12][6].getPath())){
					for(int i=0;i<handCards.size();i++){
						if(handCards.get(i) instanceof SaboteurDestroy){
							return new SaboteurMove(new SaboteurDestroy(), 12, 6, boardState.getTurnPlayer());
						}
					}
				}
			}
		}
		//if nugget in (12,3) &&(12,4) is not connected with (12,3) destroy(12,4)
		if(nug[0]==12 && nug[1]==3){
			if(mapTiles[12][4]!=null){
				if(mapTiles[12][4].getPath()[0][1]!=1 || !isConnected(mapTiles[12][4].getPath())){
					for(int i=0;i<handCards.size();i++){
						if(handCards.get(i) instanceof SaboteurDestroy){
							return new SaboteurMove(new SaboteurDestroy(), 12, 4, boardState.getTurnPlayer());
						}
					}
				}
			}
		}
		//if nugget in (12,5) and  (12,4) or (12,6) or (11,5)or (13,5)  is in legalmoves,
		if(nug[1]==5 ){
			for(int i=0;i<tileMovesf.size();i++){
			if(tileMovesf.get(i).getPosPlayed()[0]==12 && tileMovesf.get(i).getPosPlayed()[1]==4 ){
				SaboteurTile t=(SaboteurTile)tileMovesf.get(i).getCardPlayed();
				if(t.getPath()[2][1]==1 && isConnected(t.getPath())){
		    	return tileMovesf.get(i);
				}
		    }
			if(tileMovesf.get(i).getPosPlayed()[0]==12 && tileMovesf.get(i).getPosPlayed()[1]==6 ){
				SaboteurTile t=(SaboteurTile)tileMovesf.get(i).getCardPlayed();
				if(t.getPath()[0][1]==1 && isConnected(t.getPath())){
		    	return tileMovesf.get(i);
				}
		    }
				if(tileMovesf.get(i).getPosPlayed()[0]==11 && tileMovesf.get(i).getPosPlayed()[1]==5 ){
					SaboteurTile t=(SaboteurTile)tileMovesf.get(i).getCardPlayed();
					if(t.getPath()[1][0]==1 && isConnected(t.getPath())){
			    	return tileMovesf.get(i);
					}
			    }
				if(tileMovesf.get(i).getPosPlayed()[0]==13 && tileMovesf.get(i).getPosPlayed()[1]==5 ){
					SaboteurTile t=(SaboteurTile)tileMovesf.get(i).getCardPlayed();
					if(t.getPath()[1][2]==1 && isConnected(t.getPath())){
			    	return tileMovesf.get(i);
					}
			    }
			
			}
			}
		//if we know that nugget in (12,7) and have legal move in (12,6) or (12,8) or (11,7),(13,7)
		if(nug[1]==7 ){
			for(int i=0;i<tileMovesf.size();i++){
			if(tileMovesf.get(i).getPosPlayed()[0]==12 && tileMovesf.get(i).getPosPlayed()[1]==6  ){
				SaboteurTile t=(SaboteurTile)tileMovesf.get(i).getCardPlayed();
				if(t.getPath()[2][1]==1 && isConnected(t.getPath())){
		    	return tileMovesf.get(i);
				}
		    }
			if(tileMovesf.get(i).getPosPlayed()[0]==12 && tileMovesf.get(i).getPosPlayed()[1]==8  ){
				SaboteurTile t=(SaboteurTile)tileMovesf.get(i).getCardPlayed();
				if(t.getPath()[0][1]==1 && isConnected(t.getPath())){
		    	return tileMovesf.get(i);
				}
			}
			if(tileMovesf.get(i).getPosPlayed()[0]==11 && tileMovesf.get(i).getPosPlayed()[1]==7  ){
				SaboteurTile t=(SaboteurTile)tileMovesf.get(i).getCardPlayed();
				if(t.getPath()[1][0]==1 && isConnected(t.getPath())){
		    	return tileMovesf.get(i);
				}
		    }
			if(tileMovesf.get(i).getPosPlayed()[0]==13 && tileMovesf.get(i).getPosPlayed()[1]==7  ){
				SaboteurTile t=(SaboteurTile)tileMovesf.get(i).getCardPlayed();
				if(t.getPath()[1][2]==1 && isConnected(t.getPath())){
		    	return tileMovesf.get(i);
				}
			}
			}
			}
		
		//if we know that nugget is in (12,3) legalmovein (12,4) or (12,2) or (11,3) or (13,3)
		if(nug[1]==3 ){
		for(int i=0;i<tileMovesf.size();i++){
		if(tileMovesf.get(i).getPosPlayed()[0]==12 && tileMovesf.get(i).getPosPlayed()[1]==4  ){
			SaboteurTile t=(SaboteurTile)tileMovesf.get(i).getCardPlayed();
			if(t.getPath()[0][1]==1 && isConnected(t.getPath())){
	    	return tileMovesf.get(i);
			}
	    }
		if(tileMovesf.get(i).getPosPlayed()[0]==12 && tileMovesf.get(i).getPosPlayed()[1]==2  ){
			SaboteurTile t=(SaboteurTile)tileMovesf.get(i).getCardPlayed();
			if(t.getPath()[2][1]==1 && isConnected(t.getPath())){
	    	return tileMovesf.get(i);
			}
	    }
		if(tileMovesf.get(i).getPosPlayed()[0]==11 && tileMovesf.get(i).getPosPlayed()[1]==3  ){
			SaboteurTile t=(SaboteurTile)tileMovesf.get(i).getCardPlayed();
			if(t.getPath()[1][0]==1 && isConnected(t.getPath())){
	    	return tileMovesf.get(i);
			}
	    }
		if(tileMovesf.get(i).getPosPlayed()[0]==13 && tileMovesf.get(i).getPosPlayed()[1]==3  ){
			SaboteurTile t=(SaboteurTile)tileMovesf.get(i).getCardPlayed();
			if(t.getPath()[1][2]==1 && isConnected(t.getPath())){
	    	return tileMovesf.get(i);
			}
	    }
		}
		}
		
		//if we don't know nugget but have (12,2) with path[2][1]==1 and (12,8) with path[0][1]==1 in legal moves
		for(int i=0;i<tileMovesf.size();i++){
			if(tileMovesf.get(i).getPosPlayed()[0]==12 && tileMovesf.get(i).getPosPlayed()[1]==2  ){
				SaboteurTile t=(SaboteurTile)tileMovesf.get(i).getCardPlayed();
				if(t.getPath()[2][1]==1 && isConnected(t.getPath())){
		    	return tileMovesf.get(i);
				}
		    }
			if(tileMovesf.get(i).getPosPlayed()[0]==12 && tileMovesf.get(i).getPosPlayed()[1]==8  ){
				SaboteurTile t=(SaboteurTile)tileMovesf.get(i).getCardPlayed();
				if(t.getPath()[0][1]==1 && isConnected(t.getPath())){
		    	return tileMovesf.get(i);
				}
		    }
			
			}
		
		
		for(int i=0;i<tileMovesf.size();i++){
			    
			
				//find if I can have moves that is legal and connected tiles in (12,4) or (12,6)
				if(tileMovesf.get(i).getPosPlayed()[0]==12 && tileMovesf.get(i).getPosPlayed()[1]==4 && pathconnect(tileMovesf.get(i))){
					return tileMovesf.get(i);
				}
			
				if(tileMovesf.get(i).getPosPlayed()[0]==12 && tileMovesf.get(i).getPosPlayed()[1]==6 && pathconnect(tileMovesf.get(i))){
					return tileMovesf.get(i);
				
			
			}
		}
		

		
		// find if there is malusMoves in legal moves
				List<SaboteurMove> malusMoves = legalMoves.stream()
						.filter(move -> move.getCardPlayed() instanceof SaboteurMalus).collect(Collectors.toList());
				// if have maluseMoves,using it
				if (malusMoves.size() > 0) {
					return malusMoves.get(0);
				}



				// to see if need to heal from malus or not
		List<SaboteurMove> bonusMoves = legalMoves.stream()
				.filter(move -> move.getCardPlayed() instanceof SaboteurBonus).collect(Collectors.toList());
		// if I have bonus card in legal moves, heal myself from malus
		if (bonusMoves.size() > 0) {
			return bonusMoves.get(0);
		}
		
		//
		List<SaboteurMove> legaltilemoves = legalMoves.stream()
				.filter(move -> move.getCardPlayed() instanceof SaboteurTile).collect(Collectors.toList());
		

		


		
		// if I have map moves in legal moves,choosing one destination 
					for(int i = 0;i<handCards.size();i++){
						if(handCards.get(i) instanceof SaboteurMap && nug[1]==0){//if I have mapcard in my hand and I don't now where nugget is
							int[] test={12,5};
							if(isMap(boardState,test)==false){
								return new SaboteurMove(handCards.get(i),12,5,boardState.getTurnPlayer());
							}
							test[1]=3;
							if(isMap(boardState,test)==false){
								return new SaboteurMove(handCards.get(i),12,3,boardState.getTurnPlayer());
							}
							test[1]=7;
							if(isMap(boardState,test)==false){
								return new SaboteurMove(handCards.get(i),12,7,boardState.getTurnPlayer());
							}
						}
					}
		
		

		// get tile card from hands
		List<SaboteurTile> tileCards = handCards.stream().filter(card -> card instanceof SaboteurTile)
				.map(card -> (SaboteurTile) card).collect(Collectors.toList());
		// 
		List<SaboteurTile> liveCards = tileCards.stream().filter(card -> isConnected(card.getPath()))
				.collect(Collectors.toList());
		// 
		List<SaboteurTile> deadCards = tileCards.stream().filter(card -> !isConnected(card.getPath()))
				.collect(Collectors.toList());
		// drop !isconnected card(if have livecards<=3)
					for(int i=0; i< handCards.size(); i++) {
						if (handCards.get(i) instanceof SaboteurTile) {
							int[][] path = ((SaboteurTile) handCards.get(i)).getPath();
							if (!isConnected(path) && liveCards.size()<=3 && frontpos(mapTiles)<10) {
								return new SaboteurMove(new SaboteurDrop(), i, 0, boardState.getTurnPlayer());
							}
						}
			        }
		//drop bonus card(if bonus card>1) and livecards<=3
					
					int[] countBonus={0};
					for(int i = 0;i<handCards.size();i++){
						if(handCards.get(i) instanceof SaboteurBonus){
							countBonus[0]++;
							if(countBonus[0]>1 && liveCards.size()<=3){
								return new SaboteurMove(new SaboteurDrop(), i, 0, boardState.getTurnPlayer());
							}
						}
					}
					
					// if all destination have mapped or I know the place of nugget, drop it
					for(int i=0; i< handCards.size(); i++) {
						if (handCards.get(i) instanceof SaboteurMap) {
							return new SaboteurMove(new SaboteurDrop(), i, 0, boardState.getTurnPlayer());
						}
			        }
		
         //drop destory card if livecards<=3
					
					
					for(int i = 0;i<handCards.size();i++){
						if(handCards.get(i) instanceof SaboteurDestroy){
							
							if(liveCards.size()<=3){
								return new SaboteurMove(new SaboteurDrop(), i, 0, boardState.getTurnPlayer());
							}
						}
					}
					
					

		// get all reachable place from entrance
		List<int[]> reachableEnds = MyTools.reachableEnds(mapTiles, new int[] { 5, 5 });

		// choosing tile moves from closest ends and legal moves
		// sorted with reverse x order(ie.bigger x value is more optimal)
		//ie.try to choose tile that are as higher value as possible
		List<int[]> closestEnds = reachableEnds.stream().sorted((p1, p2) -> {
			if (p2[0] != p1[0]) {
				return p2[0] - p1[0];
			}
			SaboteurTile t1 = mapTiles[p1[0]][p1[1]];
			SaboteurTile t2 = mapTiles[p2[0]][p2[1]];
			//
			if(nug[0]!=0 && nug[1]!=0){
			   if((Math.pow(p1[1]-nug[1],2))!=(Math.pow(p2[1]-nug[1],2))){
				return (int)(Math.pow(p1[1]-nug[1],2)-Math.pow(p2[1]-nug[1],2));
			}
			}
				
				//
			return pathScore(t2.getPath()) - pathScore(t1.getPath());
		}).collect(Collectors.toList());

		   int[] closestEnd = closestEnds.get(0);
     
			List<int[]> closerEnds = closestEnds.stream().filter(end -> end[0] >= closestEnd[0] - 2)
					.collect(Collectors.toList());
			
			List<SaboteurMove> tileMoves = legalMoves.stream().filter(move -> {
				if (move.getCardPlayed() instanceof SaboteurTile) {
					SaboteurTile tile = (SaboteurTile) move.getCardPlayed();
					int[][] path = tile.getPath();
					int[] pos = move.getPosPlayed();
					if (isConnected(path) && pos[0]>=5 && pos[0] <= 13 && pos[1] >= 2 && pos[1] <= 8) {
						return true;
					}
				}
				return false;
			}).sorted((m1, m2) -> {
				int[] p1 = m1.getPosPlayed();
				int[] p2 = m2.getPosPlayed();
				if (p2[0] != p1[0]) {
					return p2[0] - p1[0];
				}
				//sort using eval function 
				SaboteurTile s1=(SaboteurTile)(m1.getCardPlayed());
				SaboteurTile s2=(SaboteurTile)(m2.getCardPlayed());
				return pathScore(s2.getPath())-pathScore(s1.getPath());
				
			}).collect(Collectors.toList());
			int[][] forwards = { { 1, 0 }, { 0, -1 }, { 0, 1 } };
			for (int[] end : closerEnds) {
				for (int m = 0; m < forwards.length; m++) {
					int x = end[0] + forwards[m][0];
					int y = end[1] + forwards[m][1];
					for (int i = 0; i < tileMoves.size(); i++) {
						if (tileMoves.get(i).getPosPlayed()[0]==x && tileMoves.get(i).getPosPlayed()[1]==y){
							return tileMoves.get(i);
						}
					}
				}
			}

			// finding if there is destroy card
			// if there is, only destroy !isConnected path
			List<SaboteurMove> destroyMoves = legalMoves.stream().filter(move -> {
				if (move.getCardPlayed() instanceof SaboteurDestroy) {
					int[] pos = move.getPosPlayed();
					SaboteurTile tile = mapTiles[pos[0]][pos[1]];
					if (tile != null) {
						int[][] path = tile.getPath();
						if (!isConnected(path)) {
							return true;
						}
					}
				}
				return false;
			}).collect(Collectors.toList());
			int min=(int)Math.pow(destroyMoves.get(0).getPosPlayed()[0]-nug[0], 2)+(int)Math.pow(destroyMoves.get(0).getPosPlayed()[1]-nug[1], 2);
			int index=0;
			for(int j=0;j<destroyMoves.size();j++){
				int pos[]=destroyMoves.get(j).getPosPlayed();
				if(nug[0]!=0 && nug[1]!=0){
					if((int)Math.pow(pos[0]-nug[0], 2)+(int)Math.pow(pos[1]-nug[1], 2)<min){
						index=j;
						min=(int)Math.pow(pos[0]-nug[0], 2)+(int)Math.pow(pos[1]-nug[1], 2);
					}
				}else{
					if((int)Math.pow(pos[0]-12, 2)+(int)Math.pow(pos[1]-5, 2)<min){
						index=j;
						min=(int)Math.pow(pos[0]-12, 2)+(int)Math.pow(pos[1]-5, 2);
					}
					
				}
			}
			if (destroyMoves.size() > 0) {
				return destroyMoves.get(index);
			}
			
			

			
			
			// finding if there is a handCard that can be dropped
			// only drop the tile that !isConnected or Malus card(if we have more then one)
			int[] countMalus={0};
			for(int i = 0;i<handCards.size();i++){
				if(handCards.get(i) instanceof SaboteurMalus){
					countMalus[0]++;
					if(countMalus[0]>1){
						return new SaboteurMove(new SaboteurDrop(), i, 0, boardState.getTurnPlayer());
					}
				}
			}
			
			



		// // otherwise,random move
		return boardState.getRandomMove();
	}

	// condition1:the middle of tile must have path
		// condition2:there is at least a path to get in and get out the tile
		// ie.at least 2 points of path[0][1],path[1][0],path[1][2],path[2][1] is 1
	private boolean isConnected(int[][] path) {
		if (path[1][1] == 1) {
			long opens = Arrays.asList(path[0][1], path[1][0], path[1][2], path[2][1]).stream()
					.filter(point -> point == 1).count();
			if (opens > 1) {
				return true;
			}
		}
		return false;
	}

	private int pathScore(int[][] path) {
		int score = 0;
		if (path[1][0] == 1) {
			score += 100;
		}
		if (path[0][1] == 1) {
			score += 10;
		}
		if (path[2][1] == 1) {
			score += 10;
		}
		if (path[1][2] == 1) {
			score += 20;
		}
		return score;
	}
	
	//find if already used isMapped card in pos[]
	private boolean isMap(SaboteurBoardState boardState,int pos[]){
		SaboteurTile[][] hiddenTiles = boardState.getHiddenBoard();
		if(hiddenTiles[pos[0]][pos[1]].getIdx().equals("8")){
			return false;
	}
		if(hiddenTiles[pos[0]][pos[1]].getIdx().equals("nugget")){
			nug[0]=pos[0];
			nug[1]=pos[1];
		}
		return true;
	}
	//whether I know where nugget is or not
	private boolean knownegget(SaboteurBoardState boardState){
		SaboteurTile[][] hiddenTiles = boardState.getHiddenBoard();
		if(hiddenTiles[12][3].getIdx().equals("nugget") || hiddenTiles[12][5].getIdx().equals("nugget")|| hiddenTiles[12][7].getIdx().equals("nugget")){
			return true;
		}
		if(hiddenTiles[12][3].getIdx().equals("hidden1") && hiddenTiles[12][5].getIdx().equals("hidden2") ){
			nug[0]=12;
			nug[1]=7;
			return true;		
		}
		if(hiddenTiles[12][5].getIdx().equals("hidden1") && hiddenTiles[12][3].getIdx().equals("hidden2") ){
			nug[0]=12;
			nug[1]=7;
			return true;		
		}
		if(hiddenTiles[12][3].getIdx().equals("hidden1") && hiddenTiles[12][7].getIdx().equals("hidden2") ){
			nug[0]=12;
			nug[1]=5;
			return true;		
		}
		if(hiddenTiles[12][7].getIdx().equals("hidden1") && hiddenTiles[12][3].getIdx().equals("hidden2") ){
			nug[0]=12;
			nug[1]=5;
			return true;		
		}
		if(hiddenTiles[12][5].getIdx().equals("hidden1") && hiddenTiles[12][7].getIdx().equals("hidden2") ){
			nug[0]=12;
			nug[1]=3;
			return true;		
		}
		if(hiddenTiles[12][7].getIdx().equals("hidden1") && hiddenTiles[12][5].getIdx().equals("hidden2") ){
			nug[0]=12;
			nug[1]=3;
			return true;		
		}
		return false;
	}
	
	//find the front position that I can reach
	private int frontpos(SaboteurTile[][] mapTiles){
		
		int[] entrance={5,5};
		ArrayList<int[]> reachablePos=MyTools.reachableEnds(mapTiles, entrance);
		int front=0;
		for (int i=0;i<reachablePos.size();i++){
			if(reachablePos.get(i)[0]>front){
				front=reachablePos.get(i)[0];
			}
		}
		return front;
	}
	private boolean pathconnect(SaboteurMove m){
		if(m.getCardPlayed() instanceof SaboteurTile){
			SaboteurTile s=(SaboteurTile)m.getCardPlayed();
			if(s.getPath()[0][1]==1 && s.getPath()[1][1]==1 && s.getPath()[2][1]==1){
				return true;
			}
		}
		return false;
	}


		
	
		
		
	
}
