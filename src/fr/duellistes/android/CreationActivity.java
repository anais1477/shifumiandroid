package fr.duellistes.android;

import java.io.File;
import java.util.ArrayList;

import fr.duellistes.backend.Human;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CreationActivity extends Activity {
	private String nameJ1;
	private String nameJ2;

	
	private OnClickListener createJoueur = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			ArrayList<String> namesList = new ArrayList<String>();			
			
			String[] tmp;
			File dir = getApplicationContext().getDir("DuellistesStats", Context.MODE_PRIVATE);
			File[] files = dir.listFiles();
			
			for (int i=0; i< files.length; i++) {
			    if (!files[i].isDirectory() && files[i].getName().contains("_stats.dat")); 
			    {
			       tmp = files[i].getName().split("_");
			       namesList.add(tmp[0]);	
			    }
			}
			
			Bundle extras = getIntent().getExtras(); 
			int nbJoueurset = extras.getInt("nbJoueurset");
			Boolean multi = extras.getBoolean("multi");
			nameJ1 = extras.getString("nameJ1");		
			
			
		    
		    
			EditText nouveauJoueurText = (EditText) findViewById(R.id.editName);
			String sNouveauJoueurText = nouveauJoueurText.getText().toString();
			
			if (sNouveauJoueurText.length() == 0) {
				Context context = getApplicationContext();
				Toast toast = Toast.makeText(context, "Vous devez entrer un nom ! ", Toast.LENGTH_LONG);
				toast.show();
			} 
			else if(namesList.contains(sNouveauJoueurText)){
				Context context = getApplicationContext();
				Toast toast = Toast.makeText(context, "Ce joueur existe déjà ! Veuillez entrer un nom différent.", Toast.LENGTH_LONG);
				toast.show();
				
			}
			else 
			{				
				if(nbJoueurset == 0 && multi == true){
					nameJ1 = sNouveauJoueurText;
					Intent chooseIntent = new Intent(CreationActivity.this, ChooseActivity.class);
					chooseIntent.putExtra("nbJoueurset",nbJoueurset+1);
					chooseIntent.putExtra("multi",multi);
					chooseIntent.putExtra("nameJ1",nameJ1);
	                startActivity(chooseIntent);
	                Human ja = new Human(nameJ1, dir);
	                ja.save();
	                
				}
				else if(nbJoueurset == 1 && multi == true)
				{
					nameJ2 = sNouveauJoueurText;
					Log.i("ANAIS",nameJ1+nameJ2);
					if(nameJ1.equals(nameJ2)){
						Context context = getApplicationContext();
						Toast toast = Toast.makeText(context, "Le deuxième joueur ne peut pas être le même que le premier", Toast.LENGTH_LONG);
						toast.show();
						
					}
					else{
						Intent areneIntent = new Intent(CreationActivity.this, AreneActivity.class);
						areneIntent.putExtra("nbJoueurset",0);
					    areneIntent.putExtra("nameJ1",nameJ1);
					    areneIntent.putExtra("nameJ2",nameJ2);
					    areneIntent.putExtra("multi",multi);
		                startActivity(areneIntent);
		                Human jb = new Human(nameJ2, dir);
		                jb.save();
		                
					}
				}
				else{
				    Intent areneIntent = new Intent(CreationActivity.this, AreneActivity.class);
				    areneIntent.putExtra("nameJ1",sNouveauJoueurText);
				    areneIntent.putExtra("multi",multi);
	                startActivity(areneIntent);
	                Human ja = new Human(nameJ1, dir);
	                ja.save();
				}
			}
		}
	};
	
	

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_creation);
		setTitle("Duellistes");
		if (getIntent().getBooleanExtra("EXIT", false)) {
	         finish();
	    }
		
		Bundle extras = getIntent().getExtras(); 
		int nbJoueurset = extras.getInt("nbJoueurset");
		
		
		Resources res = getResources();
		String title;
		if(nbJoueurset == 1)
			title = res.getString(R.string.txt_player, 2);
		else
			title = res.getString(R.string.txt_player, 1);
		
		TextView vue1 = (TextView)findViewById(R.id.txt_player);
	    vue1.setText(title);
	    
		
		Button ok = (Button) findViewById(R.id.btn_create);
		ok.setOnClickListener(createJoueur);
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_creation, menu);
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
