package fr.duellistes.android;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class StatsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_stats);
		setTitle("Statistiques");
		
		if (getIntent().getBooleanExtra("EXIT", false)) {
	         finish();
	    }
		
		Bundle extras = getIntent().getExtras(); 
		Boolean multi = extras.getBoolean("multi");
		int NbCombats = extras.getInt("NbCombats");
		int NbVic = extras.getInt("NbVic");
		int NbVicSuivies = extras.getInt("NbVicSuivies");
		int NbVicSuiviesRec = extras.getInt("NbVicSuiviesRec");
		String nameJ1 = extras.getString("nameJ1");
		
		Resources res = getResources();
	    String sNbCombats = res.getString(R.string.txt_nbFights, NbCombats);
	    String sNbVic = res.getString(R.string.txt_nbVic, NbVic);
	    String sNbVicSuivies = res.getString(R.string.txt_nbVicFollowed, NbVicSuivies);
	    String sNbVicSuiviesRec = res.getString(R.string.txt_nbVicFollowedRec, NbVicSuiviesRec);
	    TextView vue0 = (TextView)findViewById(R.id.txt_player);
	    TextView vue1 = (TextView)findViewById(R.id.txt_nbFights);
	    TextView vue2 = (TextView)findViewById(R.id.txt_nbVic);
	    TextView vue3 = (TextView)findViewById(R.id.txt_nbVicFollowed);
	    TextView vue4 = (TextView)findViewById(R.id.txt_nbVicFollowedRec);
	    vue0.setText(nameJ1);
	    vue1.setText(sNbCombats);
	    vue2.setText(sNbVic);
	    vue3.setText(sNbVicSuivies);
	    vue4.setText(sNbVicSuiviesRec);
	    
	    
	    TextView vue5bis = (TextView)findViewById(R.id.txt_player2);
	    TextView vue5 = (TextView)findViewById(R.id.txt_nbFights2);
	    TextView vue6 = (TextView)findViewById(R.id.txt_nbVic2);
	    TextView vue7 = (TextView)findViewById(R.id.txt_nbVicFollowed2);
	    TextView vue8 = (TextView)findViewById(R.id.txt_nbVicFollowedRec2);
	    
	    if(multi){

			int NbCombats2 = extras.getInt("NbCombats2");
			int NbVic2 = extras.getInt("NbVic2");
			int NbVicSuivies2 = extras.getInt("NbVicSuivies2");
			int NbVicSuiviesRec2 = extras.getInt("NbVicSuiviesRec2");
			String nameJ2 = extras.getString("nameJ2");
			
		    String sNbCombats2 = res.getString(R.string.txt_nbFights2, NbCombats2);
		    String sNbVic2 = res.getString(R.string.txt_nbVic2, NbVic2);
		    String sNbVicSuivies2 = res.getString(R.string.txt_nbVicFollowed2, NbVicSuivies2);
		    String sNbVicSuiviesRec2 = res.getString(R.string.txt_nbVicFollowedRec2, NbVicSuiviesRec2);
		    
		    vue5bis.setText(nameJ2);
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
