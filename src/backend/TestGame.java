package backend;

import java.io.File;

public class TestGame {
	public static void main(String[] args) {
		Arena a = Arena.getInstance();
		Human ja = new Human("anais");
		Computer jb = new Computer();

	    String curDir = System.getProperty("user.dir");
	    System.out.println ("Le répertoire courant est: "+curDir);
		File dir = new File(curDir+"/");
		
		ja.restore(dir);
		a.setPlayer1(ja);
		a.setPlayer2(jb);
		
		ja.setAttack(Attack.POWERFUL);
		System.out.println("Joueur A = "+ja.getAttack());
		System.out.println(a.round(dir));
		System.out.println("\nJoueur B = "+jb.getAttack());
		System.out.println("Joueur A = "+ja.getHealth()+"\nJoueur B = "+jb.getHealth());
		System.out.println(ja.getStats());
		

		ja.setAttack(Attack.NORMAL);
		System.out.println("Joueur A = "+ja.getAttack());
		System.out.println(a.round(dir));
		System.out.println("\nJoueur B = "+jb.getAttack());
		System.out.println("Joueur A = "+ja.getHealth()+"\nJoueur B = "+jb.getHealth());
		System.out.println(ja.getStats());
		
		ja.setAttack(Attack.NORMAL);
		System.out.println("Joueur A = "+ja.getAttack());
		System.out.println(a.round(dir));
		System.out.println("\nJoueur B = "+jb.getAttack());
		System.out.println("Joueur A = "+ja.getHealth()+"\nJoueur B = "+jb.getHealth());
		System.out.println(ja.getStats());
		
		ja.setAttack(Attack.SUBTILE);
		System.out.println("Joueur A = "+ja.getAttack());
		System.out.println(a.round(dir));
		System.out.println("\nJoueur B = "+jb.getAttack());
		System.out.println("Joueur A = "+ja.getHealth()+"\nJoueur B = "+jb.getHealth());
		System.out.println(ja.getStats());
		
		ja.setAttack(Attack.NORMAL);
		System.out.println("Joueur A = "+ja.getAttack());
		System.out.println(a.round(dir));
		System.out.println("\nJoueur B = "+jb.getAttack());
		System.out.println("Joueur A = "+ja.getHealth()+"\nJoueur B = "+jb.getHealth());
		System.out.println(ja.getStats());
		
		ja.setAttack(Attack.SUBTILE);
		System.out.println("Joueur A = "+ja.getAttack());
		System.out.println(a.round(dir));
		System.out.println("\nJoueur B = "+jb.getAttack());
		System.out.println("Joueur A = "+ja.getHealth()+"\nJoueur B = "+jb.getHealth());
		System.out.println(ja.getStats());
		
		ja.setAttack(Attack.POWERFUL);
		System.out.println("Joueur A = "+ja.getAttack());
		System.out.println(a.round(dir));
		System.out.println("\nJoueur B = "+jb.getAttack());
		System.out.println("Joueur A = "+ja.getHealth()+"\nJoueur B = "+jb.getHealth());
		System.out.println(ja.getStats());
	}
}
