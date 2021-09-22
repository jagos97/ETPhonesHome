/**
 * 
 */
package etphoneshome.managers;

import java.util.ArrayList;

import etphoneshome.player.Player;


/**
 * This class is responsible for handling all the Players highscores and arranging them in order from highest to lowest.
 * Using the getPLayerList method will return a copy of the arraylist.
 * AddPlayer will add the player to the list given there is space or if they are  in the top 10 highest scores
 * organizeList will rearrange the arraylist from highest scoring player to lowest.
 *
 */
public class PlayerManager {
	
	/**
	 * arrayList that contains all the players
	 */
	private ArrayList<Player> playerList = new ArrayList<Player>();
	

	/**
	 * returns a copy of the playerList of the {@code PlayerManager}
	 * @return copy of playerList
	 */
	public ArrayList<Player> getPlayerList(){
		ArrayList<Player> copy = new ArrayList<Player>();
		for(Player player : this.playerList) {
			copy.add(player);
		}
		return copy;
	}
	
	/**
	 * adds a player if there is room or if they are in top 10 scores
	 * @param player player to add to the {@code PlayerManager}
	 */
	public void addPlayer(Player player) {
		if(playerList.size() == 10) {
			organizeList();
			if(playerList.get(9).getScore() <= player.getScore()) {
			playerList.remove(9);
			playerList.add(player);
			organizeList();
			}
		}
		else {
			playerList.add(player);
			organizeList();
		}
	}
	
	
	/**
	 * removes the player from the {@code PlayerManager}
	 * @param player player to remove
	 */
	public void removePlayer(Player player) {
		playerList.remove(player);
	}
	
	/**
	 * rearranges the list from highest to lowest scores
	 */
	public void organizeList() {
		int highscore;
		for(int i = 0; i < playerList.size(); i ++) {
			for(int a = 0; i+a < playerList.size(); a ++) {
				highscore = playerList.get(i).getScore();
				if(playerList.get(i+a).getScore() > highscore) {
					Player copy = new Player(playerList.get(i));
					playerList.remove(i);
					playerList.add(copy);
					a= 0;
				}
			}
		}
		
	}
	
	public static void main(String[] args) {
		Player player1 = new Player(50000,"Alex");
		Player player2 = new Player(20000,"bob");
		Player player3 = new Player(10000,"Clarence");
		Player player4 = new Player(545890,"Dean");
		Player player5 = new Player(30000,"Ellen");
		Player player6 = new Player(40000,"Frank");
		Player player7 = new Player(2,"Greg");
		Player player8 = new Player(999999,"Helen");
		Player player9 = new Player(65464,"Ivette");
		Player player10 = new Player(1,"Jessica");
		Player player11 = new Player(0,"Kerry");
		Player player12 = new Player(10000,"Lucy");
		
		
		PlayerManager playerlist = new PlayerManager();
		playerlist.addPlayer(player1);
		playerlist.addPlayer(player2);
		playerlist.addPlayer(player3);
		playerlist.addPlayer(player4);
		playerlist.addPlayer(player5);
		playerlist.addPlayer(player6);
		playerlist.addPlayer(player7);
		playerlist.addPlayer(player8);
		playerlist.addPlayer(player9);
		playerlist.addPlayer(player10);
		//should not be in list
		playerlist.addPlayer(player11);
		
		//should be added in list
		playerlist.addPlayer(player12);
		
		for(Player player : playerlist.getPlayerList()) {
			System.out.println(player.toString());
		}
		System.out.println(":");
		
		playerlist.organizeList();
		
		for(Player player : playerlist.getPlayerList()) {
			System.out.println(player.toString());
		}
	
	
	
	
	
	}
	
}
