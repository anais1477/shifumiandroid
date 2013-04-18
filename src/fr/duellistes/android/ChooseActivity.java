package fr.duellistes.android;


import java.io.File;
import java.util.ArrayList;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ChooseActivity extends Activity {
	private String nameJ1;
	private String nameJ2;
	private int nbPlayerSet;
	private boolean multi;
	
	protected void afficherListe(){
		
			ArrayList<String> namesList = new ArrayList<String>();			
			
			String[] tmp;
			File dir = getApplicationContext().getDir("DuellistesStats", Context.MODE_PRIVATE);
			File[] files = dir.listFiles();
			
			for (int i=0; i< files.length; i++) {
			    if (!files[i].isDirectory() && files[i].getName().contains("_stats.dat")); 
			    {
			       tmp = files[i].getName().split("_");
			       Log.d("ANAIS", "nme"+tmp[0]);
			       namesList.add(tmp[0]);	
			    }
			}
					
			
			Resources res = getResources();
			String title;
			if(nbPlayerSet == 1)
				title = res.getString(R.string.txt_player, 2);
			else
				title = res.getString(R.string.txt_player, 1);
			
		    TextView vue1 = (TextView)findViewById(R.id.txt_player);
		    vue1.setText(title);
		    
		    Button btn = (Button)findViewById(R.id.createPlayer);
		    btn.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Bundle extras = getIntent().getExtras(); 
					int nbPlayerSet = extras.getInt("nbPlayerSet");
					boolean multi = extras.getBoolean("multi");
					
					Intent creationIntent = new Intent(ChooseActivity.this, CreationActivity.class);
					creationIntent.putExtra("nbPlayerSet",nbPlayerSet);
					creationIntent.putExtra("multi",multi);
					creationIntent.putExtra("nameJ1",nameJ1);
					creationIntent.putExtra("nameJ2",nameJ2);
	                startActivity(creationIntent);
					
				}
			});
		    
		    
			
			ListView lvListe = (ListView)findViewById(R.id.listNames);
			final ListView lv = lvListe;
			lvListe.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,namesList));
			
			lvListe.setOnItemClickListener(new AdapterView.OnItemClickListener() {

				  @Override
				  public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
					  	Bundle extras = getIntent().getExtras();
					  	
					  	if(extras != null)
					  	{
					  		nbPlayerSet = extras.getInt("nbPlayerSet");
					  		multi = extras.getBoolean("multi");
					  	}
					  	else
					  	{
					  		nbPlayerSet = 0;
					  		multi = false;
					  	}
						
					  	
						if(nbPlayerSet == 0 && multi == true){
							nameJ1 = lv.getItemAtPosition(position).toString();
							Intent chooseIntent = new Intent(ChooseActivity.this, ChooseActivity.class);
							chooseIntent.putExtra("nbPlayerSet",nbPlayerSet+1);
							chooseIntent.putExtra("multi",multi);
							chooseIntent.putExtra("nameJ1",nameJ1);
			                startActivity(chooseIntent);
						}
						else if(nbPlayerSet == 1 && multi == true)
						{
							nameJ2 = lv.getItemAtPosition(position).toString();
							if(nameJ1.equals(nameJ2)){
								Context context = getApplicationContext();
								Toast toast = Toast.makeText(context, "Le deuxième joueur ne peut pas être le même que le premier", Toast.LENGTH_LONG);
								toast.show();
								
							}
							else{
								Intent areneIntent = new Intent(ChooseActivity.this, AreneActivity.class);
								areneIntent.putExtra("nbPlayerSet",0);
							    areneIntent.putExtra("nameJ1",nameJ1);
							    areneIntent.putExtra("nameJ2",nameJ2);
							    areneIntent.putExtra("multi",multi);
				                startActivity(areneIntent);
							}
						}
						else if(multi == false){
						    nameJ1 = lv.getItemAtPosition(position).toString();
						    Intent areneIntent = new Intent(ChooseActivity.this, AreneActivity.class);
						    areneIntent.putExtra("nameJ1",nameJ1);
						    areneIntent.putExtra("multi",multi);
			                startActivity(areneIntent);
						}
				  }
				});
			
			registerForContextMenu(lv);
		
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_choose);
		setTitle("Duellistes");
		
		if (getIntent().getBooleanExtra("EXIT", false)) {
	         finish();
	    }		
		
		Bundle extras = getIntent().getExtras(); 
		if(extras != null){
			nbPlayerSet = extras.getInt("nbPlayerSet");
			multi = extras.getBoolean("multi");
			nameJ1= extras.getString("nameJ1");
		}
			
		afficherListe();
		
		
	}
	
	@Override
	public void onBackPressed() {
	   Log.d("CDA", "onBackPressed Called");
	   Intent backIntent = new Intent(ChooseActivity.this,ModeActivity.class);
	   backIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	   startActivity(backIntent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_choose, menu);
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
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
	                                ContextMenuInfo menuInfo) {
	  super.onCreateContextMenu(menu, v, menuInfo);
	  MenuInflater inflater = getMenuInflater();
	  inflater.inflate(R.menu.context_menu_choose, menu);
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
		String selected = ((TextView) info.targetView).getText().toString();
	  switch (item.getItemId()) {
		  case R.id.delete:			 
			  File dir = getApplicationContext().getDir("DuellistesStats", Context.MODE_PRIVATE);
			  File file = new File(dir,  selected +"_stats.dat");
			  file.delete();
			  afficherListe();
			  return true;
		  default:
		    return super.onContextItemSelected(item);
	  }
	}

}
