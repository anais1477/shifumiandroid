package fr.duellistes.android;

import fr.duellistes.backend.Arena;
import fr.duellistes.backend.Player;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class StatsActivity extends Activity {
	private Player j1;
	private Player j2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_stats);
		setTitle("Statistiques");
		
		if (getIntent().getBooleanExtra("EXIT", false)) {
	         finish();
	    }
		Arena a = Arena.getInstance();	
		j1 = a.getPlayer1();
		j2 = a.getPlayer2();
		
		
		int nbFightsJ1 = j1.getStats().getNbTotalFights();
		int nbVictoriesJ1 = j1.getStats().getNbTotalVictories();
		int nbFollowingVictoriesJ1 = j1.getStats().getNbFollowingVictories();
		int nbBestFollowingVictoriesJ1 = j1.getStats().getNbBestFollowingVictories();
		
		
		Bundle extras = getIntent().getExtras(); 
		Boolean multi = extras.getBoolean("multi");
		/*int NbCombats = extras.getInt("NbCombats");
		int NbVic = extras.getInt("NbVic");
		int NbVicSuivies = extras.getInt("NbVicSuivies");
		int NbVicSuiviesRec = extras.getInt("NbVicSuiviesRec");*/
		
		Resources res = getResources();
	    String sNbCombats = res.getString(R.string.txt_nbFights, nbFightsJ1);
	    String sNbVic = res.getString(R.string.txt_nbVic, nbVictoriesJ1);
	    String sNbVicSuivies = res.getString(R.string.txt_nbVicFollowed, nbFollowingVictoriesJ1);
	    String sNbVicSuiviesRec = res.getString(R.string.txt_nbVicFollowedRec, nbBestFollowingVictoriesJ1);
	    
	    TextView vue0 = (TextView)findViewById(R.id.txt_player);
	    TextView vue1 = (TextView)findViewById(R.id.txt_nbFights);
	    TextView vue2 = (TextView)findViewById(R.id.txt_nbVic);
	    TextView vue3 = (TextView)findViewById(R.id.txt_nbVicFollowed);
	    TextView vue4 = (TextView)findViewById(R.id.txt_nbVicFollowedRec);
	    
	    vue0.setText(j1.getName());
	    vue1.setText(sNbCombats);
	    vue2.setText(sNbVic);
	    vue3.setText(sNbVicSuivies);
	    vue4.setText(sNbVicSuiviesRec);

    	
	    TextView vue5bis = (TextView)findViewById(R.id.txt_player2);
	    TextView vue5 = (TextView)findViewById(R.id.txt_nbFights2);
	    TextView vue6 = (TextView)findViewById(R.id.txt_nbVic2);
	    TextView vue7 = (TextView)findViewById(R.id.txt_nbVicFollowed2);
	    TextView vue8 = (TextView)findViewById(R.id.txt_nbVicFollowedRec2);
	    
	    
	    if(j2.getClass().getSimpleName().equals("Human")){

			/*int NbCombats2 = extras.getInt("NbCombats2");
			int NbVic2 = extras.getInt("NbVic2");
			int NbVicSuivies2 = extras.getInt("NbVicSuivies2");
			int NbVicSuiviesRec2 = extras.getInt("NbVicSuiviesRec2");*/

			int nbFightsJ2 = j2.getStats().getNbTotalFights();
			int nbVictoriesJ2 = j2.getStats().getNbTotalVictories();
			int nbFollowingVictoriesJ2 = j2.getStats().getNbFollowingVictories();
			int nbBestFollowingVictoriesJ2 = j2.getStats().getNbBestFollowingVictories();
			
		    String sNbCombats2 = res.getString(R.string.txt_nbFights2, nbFightsJ2);
		    String sNbVic2 = res.getString(R.string.txt_nbVic2, nbVictoriesJ2);
		    String sNbVicSuivies2 = res.getString(R.string.txt_nbVicFollowed2, nbFollowingVictoriesJ2);
		    String sNbVicSuiviesRec2 = res.getString(R.string.txt_nbVicFollowedRec2, nbBestFollowingVictoriesJ2);
		    
		    vue5bis.setText(j2.getName());
		    vue5.setText(sNbCombats2);
		    vue6.setText(sNbVic2);
		    vue7.setText(sNbVicSuivies2);
		    vue8.setText(sNbVicSuiviesRec2);  	
	    	
	    }
	    else{	    	
	    	vue5bis.setVisibility(View.GONE); 
	    	vue5.setVisibility(View.GONE); 
	    	vue6.setVisibility(View.GONE); 
	    	vue7.setVisibility(View.GONE); 
	    	vue8.setVisibility(View.GONE); 	    	
	    }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_stats, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    
	    switch (item.getItemId()) {
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
