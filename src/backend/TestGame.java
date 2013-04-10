package backend;

import java.io.File;


public class TestGame {
	public static void main(String[] args) {
		Arena a = Arena.getInstance();
		String curDir = System.getProperty("user.dir");
	    System.out.println ("Le r�pertoire courant est: "+curDir);
		File dir = new File(curDir+"/");
		
		Human ja = new Human("anais", dir);
		Computer jb = new Computer();	    
		
		ja.restore();
		a.setPlayer1(ja);
		a.setPlayer2(jb);
		
		ja.setAttack(Attack.POWERFUL);
		System.out.println("Joueur A = "+ja.getAttack());
		System.out.println(a.round());
		System.out.println("\nJoueur B = "+jb.getAttack());
		System.out.println("Joueur A = "+ja.getHealth()+"\nJoueur B = "+jb.getHealth());
		System.out.println(ja.getStats());
		

		ja.setAttack(Attack.NORMAL);
		System.out.println("Joueur A = "+ja.getAttack());
		System.out.println(a.round());
		System.out.println("\nJoueur B = "+jb.getAttack());
		System.out.println("Joueur A = "+ja.getHealth()+"\nJoueur B = "+jb.getHealth());
		System.out.println(ja.getStats());
		
		ja.setAttack(Attack.NORMAL);
		System.out.println("Joueur A = "+ja.getAttack());
		System.out.println(a.round());
		System.out.println("\nJoueur B = "+jb.getAttack());
		System.out.println("Joueur A = "+ja.getHealth()+"\nJoueur B = "+jb.getHealth());
		System.out.println(ja.getStats());
		
		ja.setAttack(Attack.SUBTILE);
		System.out.println("Joueur A = "+ja.getAttack());
		System.out.println(a.round());
		System.out.println("\nJoueur B = "+jb.getAttack());
		System.out.println("Joueur A = "+ja.getHealth()+"\nJoueur B = "+jb.getHealth());
		System.out.println(ja.getStats());
		
		ja.setAttack(Attack.NORMAL);
		System.out.println("Joueur A = "+ja.getAttack());
		System.out.println(a.round());
		System.out.println("\nJoueur B = "+jb.getAttack());
		System.out.println("Joueur A = "+ja.getHealth()+"\nJoueur B = "+jb.getHealth());
		System.out.println(ja.getStats());
		
		ja.setAttack(Attack.SUBTILE);
		System.out.println("Joueur A = "+ja.getAttack());
		System.out.println(a.round());
		System.out.println("\nJoueur B = "+jb.getAttack());
		System.out.println("Joueur A = "+ja.getHealth()+"\nJoueur B = "+jb.getHealth());
		System.out.println(ja.getStats());
		
		ja.setAttack(Attack.POWERFUL);
		System.out.println("Joueur A = "+ja.getAttack());
		System.out.println(a.round());
		System.out.println("\nJoueur B = "+jb.getAttack());
		System.out.println("Joueur A = "+ja.getHealth()+"\nJoueur B = "+jb.getHealth());
		System.out.println(ja.getStats());
	}
}
