package backend;

import java.io.File;
import java.util.Random;

public class Computer extends Player {
	private IAStrategie strategie;
	
	public Computer(){
		super();
		this.name = "Computer";
		
	}
	
	public void setAttackComp(){
		Random rand = new Random();
		int max = 1;
		int min = 1;
		
		int randomNum = rand.nextInt(max - min + 1) + min;	
		
		switch(randomNum)
		{
		case 1:
			strategie = new IARandomStrategie();
			break;
		case 2:
			strategie = new IARandomStrategie();
			break;
		case 3:
			strategie = new IARandomStrategie();
			break;
		default:
			strategie = new IARandomStrategie();
			break;
		}
		
		setAttack(this.strategie.choiceAttack());
	}
	
	@Override
	public void save(File dir) {
		// Nothing

	}

	@Override
	public void restore(File dir) {
		// Nothing

	}

}
