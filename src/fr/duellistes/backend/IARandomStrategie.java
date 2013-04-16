package fr.duellistes.backend;

import java.util.Random;

public class IARandomStrategie implements IAStrategie {

	
	@Override
	public Attack choiceAttack() {
		Random rand = new Random();
		int max = 3;
		int min = 1;
		Attack a = Attack.NORMAL;
		int randomNum = rand.nextInt(max - min + 1) + min;
		
		a = Attack.valueOf(randomNum);
		
		return a;
	}

}
