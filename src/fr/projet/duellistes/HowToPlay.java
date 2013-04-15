package fr.projet.duellistes;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class HowToPlay extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_how_to_play);
		
		TextView tv = (TextView) findViewById(R.id.howToPlay);
		tv.setText("Choisissez votre mode de jeu : 1 ou 2 joueurs.\n    - Si vous choisissez 1 joueur :  \n      Vous avez la possibilité de sélectionner un joueur existant ou" +
				" de créer un nouveau joueur.\n Lors de la création d'un nouveau joueur, le nom du joueur ne peut pas déjà exister. \n    - Si vous choisissez le mode 2 joueurs:" +
				"\nVous pouvez pour chacun créer ou choisir un joueur.\nSur l'écran de sélection des joueurs, vous avez également la possibilité de supprimer un joueur si vous appuyez plus d'une seconde sur le nom d'un joueur.\n\n"+
				"    - L'attaque Normale gagne sur l'attaque Subtile et inflige 1 point de dégats à l'adversaire.\n"+
				"    - L(attaque Subtile gagne sur l'attaque puissant et inflige 1 point de dégats à l'adversaire.\n"+
				"    - L'attaque Puissante gagne sur l'attaque normale et inflige 2 points de dégats à l'adversaire.\n"+
				"Lorsqu'un des deux combattant a 0 pv, son adversaire remporte la partie.\n"+
				"Il est possible de regarder ses statistiques sur la page de combat en appuyant sur la touche menu puis Statistiques.\n");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_how_to_play, menu);
		return true;
	}

}
