package backend.duellistes_v2;

import java.io.File;

public class TestGame {
	public static void main(String[] args) {
		Arena a = Arena.getInstance();
		Human ja = new Human("anais");
		Computer jb = new Computer();
		//ja.restore(ja);
		a.setPlayer1(ja);
		a.setPlayer2(jb);
		File dir = new File("");
		ja.setAttack(Attack.POWERFUL);
		System.out.println(a.round(dir));
		
		System.out.println(ja.getStats());
		System.out.println("Joueur A = "+ja.getHealth()+"\nJoueur B = "+jb.getHealth());

		ja.setAttack(Attack.NORMAL);
		System.out.println(a.round(dir));
		System.out.println(ja.getStats());
		System.out.println("Joueur A = "+ja.getHealth()+"\nJoueur B = "+jb.getHealth());
		
		ja.setAttack(Attack.NORMAL);
		System.out.println(a.round(dir));
		System.out.println(ja.getStats());
		System.out.println("Joueur A = "+ja.getHealth()+"\nJoueur B = "+jb.getHealth());
		ja.setAttack(Attack.NORMAL);
		System.out.println(a.round(dir));
		System.out.println(ja.getStats());
		System.out.println(jb.getStats());
		System.out.println("Joueur A = "+ja.getHealth()+"\nJoueur B = "+jb.getHealth());
		ja.setAttack(Attack.NORMAL);
		System.out.println(a.round(dir));
		System.out.println(ja.getStats());
		System.out.println("Joueur A = "+ja.getHealth()+"\nJoueur B = "+jb.getHealth());
		ja.setAttack(Attack.NORMAL);
		jb.setAttack(Attack.SUBTILE);
		System.out.println(a.round(dir));
		System.out.println(ja.getStats());
		System.out.println("Joueur A = "+ja.getHealth()+"\nJoueur B = "+jb.getHealth());
		ja.setAttack(Attack.NORMAL);
		System.out.println(a.round(dir));
		System.out.println(ja.getStats());
		System.out.println("Joueur A = "+ja.getHealth()+"\nJoueur B = "+jb.getHealth());
	}
}
