package fr.duellistes.android;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ModeActivity extends Activity {
	//MediaPlayer player;
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		if (getIntent().getBooleanExtra("EXIT", false)) {
	         finish();
	    }
		setTitle("Duellistes");
		setContentView(R.layout.activity_mode);
		
		Button solo_btn = (Button) findViewById(R.id.btn_solo);
		Button multi_btn = (Button) findViewById(R.id.btn_multi);
		Button quit_btn = (Button) findViewById(R.id.btn_quit);
		
		solo_btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent playerIntent = new Intent(ModeActivity.this,ChooseActivity.class);
				playerIntent.putExtra("multi", false);
				ModeActivity.this.startActivity(playerIntent);				
			}
		});
		multi_btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent playerIntent = new Intent(ModeActivity.this,ChooseActivity.class);
				playerIntent.putExtra("multi", true);
				ModeActivity.this.startActivity(playerIntent);				
			}
		});
		
		quit_btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
		    	Intent intent = new Intent(getApplicationContext(), ModeActivity.class);
		    	intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		    	intent.putExtra("EXIT", true);
		    	startActivity(intent);
			}
		});
		
		
		/*if(player != null) {
			Log.d("ANAIS", "Not null");
		    player.release();
		    player = null;
		  }
		
			Log.d("ANAIS", "Null");
		player = MediaPlayer.create(this, R.raw.twilight);	*/	
		Intent service = new Intent(this, BackgroundSoundService.class);
		startService(service);
		
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		//player.pause();
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		//player.start();
	}

	@Override
	protected void onStop() {
		super.onStop();
	  /*if(player != null) {
	    //player.stop();
	    player.release();
	    player = null;
	  }*/
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		Intent service = new Intent(this, BackgroundSoundService.class);
		stopService(service);
	  /*if(player != null) {
	    player.stop();
	    player.release();
	    player = null;
	  }*/
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_mode, menu);
		return true;
	}
	
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    
	    switch (item.getItemId()) {
	    	case R.id.howToPlay:
	    		Intent backIntent = new Intent(ModeActivity.this,HowToPlay.class);
	    		startActivity(backIntent);
	    		return true;
		    default:
		        return super.onOptionsItemSelected(item);
	    }
	}
}
