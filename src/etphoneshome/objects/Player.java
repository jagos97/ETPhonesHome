package etphoneshome.objects;


/**
 * this class will handle the previous players of the game and the current one for highscore purposes
 * Using getters and setters you can get and set the name and score
 *
 */
public class Player {
	
	/**
	 * score and name of the {@code Player}
	 */
	private int score;
	private String name;
	
	
	/**
	 * constructor that sets then= name and score of the {@code Player}
	 * @param score score the {@code Player} achieved
	 * @param name name of the {@code Player} 
	 */
	public Player(int score, String name) {
		this.setScore(score);
		this.setName(name);
	}

	/**
	 * creates a copy of the another {@code Player}
	 * @param player player to copy
	 */
	public Player(Player player) {
		this.setScore(player.getScore());
		this.setName(player.getName());
	}

	/**
	 * @return the score
	 */
	public int getScore() {
		return score;
	}


	/**
	 * sets the score if it is 0 or higher
	 * @param score the score to set
	 */
	public void setScore(int score) {
		if (score >= 0) {
			this.score = score;
		}
	}


	/**
	 * @return the name
	 */
	public String getName() {
		String copy = this.name;
		return copy;
	}


	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * returns a string representation of the {@code Player}
	 */
	public String toString() {
		String a = String.format("%1$-" + 20 + "s", this.getName());
		a += this.getScore();
		
		return a;
	}
	
	public static void main(String[] args) {
		Player player1 = new Player(300, "Johnny");
		Player player2 = new Player(400, "Alex");
		Player copy = new Player(player2);
		
		System.out.println("Score should be 300, is: "  + player1.getScore() );
		System.out.println("Score should be 400, is: "  + player2.getScore() );
		System.out.println("Score should be 400, is: "  + copy.getScore() );
		System.out.println("Name should be Johnny is: "  + player1.getName() );
		System.out.println("Name should be Alex, is: "  + player2.getName() );
		System.out.println("Name should be Alex, is: "  + copy.getName() );
		
		player2.setName("encapsulation");
		System.out.println("Name should be encapsulation is: " + player2.getName());
		System.out.println("Name should be Alex, is: "  + copy.getName() );
		
		System.out.println("Testing formatted string to show highscores");
		System.out.println(player1.toString());
		System.out.println(player2.toString());
		System.out.println(copy.toString());
	}
	
}
