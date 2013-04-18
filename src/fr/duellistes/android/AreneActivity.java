package fr.duellistes.android;


import java.io.File;

import fr.duellistes.backend.Arena;
import fr.duellistes.backend.Attack;
import fr.duellistes.backend.Computer;
import fr.duellistes.backend.Human;
import fr.duellistes.backend.Player;
import fr.duellistes.backend.Result;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class AreneActivity extends Activity {
	private Attack attack1;
	private Attack attack2;
	private int nbPlayerSet;
	private boolean multi;
	private Player j1;
	private Player j2;
	
	private OnClickListener attackNormale = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			nbPlayerSet++;
			if(nbPlayerSet == 2)
				attack2 = Attack.NORMAL;
			else{
				attack1 = Attack.NORMAL;				
			}
			
			if((multi && attack2 != null && attack1 != null) || (!multi && attack1 != null))
		    	combat(multi);
			
			updatePv(j1, j2);	
		}
	};
	
	private OnClickListener attackSubtile = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			nbPlayerSet++;
			if(nbPlayerSet == 2)
				attack2 = Attack.SUBTILE;
			else{
				attack1 = Attack.SUBTILE;				
			}
			if((multi && attack2 != null) || (!multi && attack1 != null))
		    	combat(multi);
			updatePv(j1, j2);	
		}
	};
	
	private OnClickListener attackPuissante = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			nbPlayerSet++;
			if(nbPlayerSet == 2)
				attack2 = Attack.POWERFUL;
			else{
				attack1 = Attack.POWERFUL;				
			}
			if((multi && attack2 != null) || (!multi && attack1 != null))
		    	combat(multi);
			
			updatePv(j1, j2);	
			}
	};
	
	public void combat(Boolean multi){
		
		Arena a = Arena.getInstance();	
		j1 = a.getPlayer1();
		j2 = a.getPlayer2();	
		int sa = j1.getHealth();
		int sb = j2.getHealth();
		j1.setAttack(attack1);
		j2.setAttack(attack2);
		Result result = a.round();
		
		attack1 = null;
		attack2 = null;
		updatePv(j1, j2);	
				
		TextView attaque = (TextView) findViewById(R.id.txt_resultsFight);
		CharSequence old = attaque.getText();
		attaque.setText(old + "\n"
				+ j1.getName() +" : "+j1.getAttack()+ "          VS             "+j2.getName()+" : "+ j2.getAttack()+"\n" 
				+ j1.getName()+" a perdu "+ (sa - j1.getHealth())+" pv                      "+ j2.getName()+" a perdu "+(sb - j2.getHealth())+" pv\n"
				+ result + "\n" );
		if(result == Result.VICTORY_1){
			AlertDialog alertDialog = new AlertDialog.Builder(AreneActivity.this).create();
			alertDialog.setTitle("Résultat");
			alertDialog.setMessage("Victoire de "+j1.getName());
		      alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
		          public void onClick(DialogInterface dialog, int which) {
		     
		        } });

			alertDialog.show();
		}
		else if(result == Result.VICTORY_2){
			AlertDialog alertDialog = new AlertDialog.Builder(AreneActivity.this).create();
			alertDialog.setTitle("Résultat");
			alertDialog.setMessage("Victoire de "+j2.getName());
		      alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
		          public void onClick(DialogInterface dialog, int which) {
		     
		        } });

			alertDialog.show();
		}
		nbPlayerSet=0;
		
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_arena);
		setTitle("Duellistes");
		
		File dir = getApplicationContext().getDir("DuellistesStats", Context.MODE_PRIVATE); 
		
		
		if (getIntent().getBooleanExtra("EXIT", false)) {
	         finish();
	    }
		
		Bundle extras = getIntent().getExtras(); 
		String nameJ1 = extras.getString("nameJ1");
		String nameJ2 = extras.getString("nameJ2");
		multi = extras.getBoolean("multi");
		
		Arena a = Arena.getInstance();
		j1 = new Human(nameJ1, dir);
		if(multi)
			j2 = new Human(nameJ2, dir);
		else
			j2 = new Computer();
		
		a.setPlayer1(j1);
		a.setPlayer2(j2);
		updatePv(j1, j2);
		a.getPlayer1().restore();
		a.getPlayer2().restore();
		a.getPlayer1().save();
		if(multi)
			a.getPlayer2().save();
		
		ImageView  attckNormale = (ImageView ) findViewById(R.id.btn_normalAttack);
		ImageView  attckSubtile = (ImageView ) findViewById(R.id.btn_subtileAttack);
		ImageView  attckPuissante = (ImageView ) findViewById(R.id.btn_powerfulAttack);
		
		attckNormale.setOnClickListener(attackNormale);
		attckPuissante.setOnClickListener(attackPuissante);
		attckSubtile.setOnClickListener(attackSubtile);	
		
		
	    TextView vue3 = (TextView)findViewById(R.id.txt_fight);
	    vue3.setText("Le combat commence !");	
	    
	}
	
	public void updatePv(Player j1, Player j2){
		Resources res = getResources();
	    String hpJ1 = res.getString(R.string.txt_hpJ1, j1.getName(), j1.getHealth());
	    String hpJ2 = res.getString(R.string.txt_hpJ2, j2.getName(), j2.getHealth());
	    TextView vue1 = (TextView)findViewById(R.id.txt_hpJ1);
	    TextView vue2 = (TextView)findViewById(R.id.txt_hpJ2);
	    vue1.setText(hpJ1);
	    vue2.setText(hpJ2);
	    
	    if(attack1 == null)
	    {
	    	vue1.setBackgroundColor(0xFFED983E);
	    	vue2.setBackgroundColor(0xFF6899DE);
	    }
	    else if(attack2 == null && attack1 !=null){
	    	vue2.setBackgroundColor(0xFFED983E);
	    	vue1.setBackgroundColor(0xFF6899DE);
	    }
		
	}
	
	@Override
	public void onBackPressed() {
	   Log.d("CDA", "onBackPressed Called");
	   Intent backIntent = new Intent(AreneActivity.this,ChooseActivity.class);
	   backIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	   backIntent.putExtra("multi", multi);
	   backIntent.putExtra("nbPlayerSet", nbPlayerSet);	   
	   startActivity(backIntent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    
	    switch (item.getItemId()) {
	    	case R.id.accueil:
	    		Intent backIntent = new Intent(AreneActivity.this,ModeActivity.class);
	    		backIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	    		backIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	    		startActivity(backIntent);
	    		return true;
		    case R.id.stats_menu:
		    	Arena a = Arena.getInstance();
		    	Intent statIntent = new Intent(AreneActivity.this,StatsActivity.class);
		    	
		    	/*statIntent.putExtra("NbCombats", a.getPlayer1().getStats().getNbTotalFights());
		    	statIntent.putExtra("NbVic", a.getPlayer1().getStats().getNbTotalVictories());
		    	statIntent.putExtra("NbVicSuivies", a.getPlayer1().getStats().getNbFollowingVictories());
		    	statIntent.putExtra("NbVicSuiviesRec", a.getPlayer1().getStats().getNbBestFollowingVictories());*/
		    	statIntent.putExtra("multi",multi);
		    	statIntent.putExtra("nameJ1", a.getPlayer1().getName());
		    	/*if(multi){
			    	statIntent.putExtra("NbCombats2", a.getPlayer2().getStats().getNbTotalFights());
			    	statIntent.putExtra("NbVic2", a.getPlayer2().getStats().getNbTotalVictories());
			    	statIntent.putExtra("NbVicSuivies2", a.getPlayer2().getStats().getNbFollowingVictories());
			    	statIntent.putExtra("NbVicSuiviesRec2", a.getPlayer2().getStats().getNbBestFollowingVictories());
			    	statIntent.putExtra("nameJ2", a.getPlayer2().getName());
		    	}*/
		 	    startActivity(statIntent);
		        return true;
		    case R.id.quitter:
			    	Intent intent = new Intent(getApplicationContext(), ModeActivity.class);
			    	intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			    	intent.putExtra("EXIT", true);
			    	startActivity(intent);
		           return true;
		    default:
		        return super.onOptionsItemSelected(item);
	    }
	}

}
