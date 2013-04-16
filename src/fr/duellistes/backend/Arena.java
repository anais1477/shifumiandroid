package fr.duellistes.backend;

public class Arena {
	private static Arena instanceUnique;
	private Player player1;
	private Player player2;
	
	private Arena(){		
		
	}
	
	public static synchronized Arena getInstance(){
		if(instanceUnique == null)
			instanceUnique = new Arena();
		return instanceUnique;
	}

	public Player getPlayer1() {
		return player1;
	}

	public void setPlayer1(Player player1) {
		this.player1 = player1;
	}

	public Player getPlayer2() {
		return player2;
	}

	public void setPlayer2(Player player2) {
		this.player2 = player2;
	}
	
	/**
	 * @param attack1
	 * @param attack2
	 * @return
	 */
	public Attack attackWinner(Attack attack1,Attack attack2){
		if(attack1  == Attack.SUBTILE && attack2 == Attack.POWERFUL)
			return attack1;
		else if(attack1  == Attack.NORMAL && attack2 == Attack.SUBTILE)
			return attack1;
		else if(attack1  == Attack.POWERFUL && attack2 == Attack.NORMAL)
			return attack1;
		else if(attack1  == Attack.POWERFUL && attack2 == Attack.SUBTILE)
			return attack2;
		else if(attack1  == Attack.SUBTILE && attack2 == Attack.NORMAL)
			return attack2;
		else if(attack1  == Attack.NORMAL && attack2 == Attack.POWERFUL)
			return attack2;
		return null;
	}
	
	public Result round(){
		Result result = Result.IN_PROGRESS;
		
		if(player2 instanceof Computer)
			((Computer) player2).setAttackComp();
		
		Attack attack1 = player1.getAttack();
		Attack attack2 = player2.getAttack();
				
		Attack res = attackWinner(attack1, attack2);
		if(res == attack1 && (res == Attack.NORMAL || res == Attack.SUBTILE))
			player2.calculateHealth(1);
		else if(res == attack1 && res == Attack.POWERFUL)
			player2.calculateHealth(2);
		else if(res == attack2 && (res == Attack.NORMAL || res == Attack.SUBTILE))
			player1.calculateHealth(1);
		else if(res == attack2 && res == Attack.POWERFUL)
			player1.calculateHealth(2);
			
		if(player1.getHealth() <= 0)
		{
			result = Result.VICTORY_2;
			reset();
			player1.save();
			player2.save();
		}			
		else if (player2.getHealth() <= 0)
		{
			result = Result.VICTORY_1;
			reset();
			player1.save();
			player2.save();
		}
		
		return result;
	}
	
	public void reset(){
		player1.reset();
		player2.reset();
	}
	
}
