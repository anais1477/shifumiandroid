package backend.duellistes_v2;

import java.io.File;

public abstract class Player {
	String name;
	private int health;
	private Attack attack;
	
	public Player(){
		this.health = 3;
		this.attack = null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public Attack getAttack() {
		return attack;
	}

	public void setAttack(Attack att) {
		this.attack = att;
	}
	
	public int calculateHealth(int damages){
		health = health - damages;
		return health;
	}
	
	public Statistics getStats(){
		return null;
	}
	
	public void reset(){
		this.health = 3;
		this.attack = null;
	}
	
	public boolean attackReady(){
		return (this != null);
	}
	
	public abstract void save(File dir);
	
	public abstract void restore(File dir);
	
}
