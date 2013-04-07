package backend;

public enum Attack {
	NORMAL,
	POWERFUL,
	SUBTILE;
	
	static public Attack valueOf(int choiceAttack){
		switch(choiceAttack){
		case 0:
			return Attack.SUBTILE;
		case 1:
			return Attack.NORMAL;
		case 2:
			return Attack.POWERFUL;
		default:
			return Attack.NORMAL;
		}		
	}
}
