package fr.duellistes.android;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

public class BackgroundSoundService extends Service {
	private static final String TAG = null;
	MediaPlayer player;

	public IBinder onBind(Intent arg0) {
		Log.d("ANAIS", "BackgroundSoundService onBind");
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		player = MediaPlayer.create(this, R.raw.phenom);
		player.setLooping(true); // Set looping
		player.setVolume(100, 100);
		Log.d("ANAIS", "BackgroundSoundService create");
	}

	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.d("ANAIS", "BackgroundSoundService onStartCommand");
		player.start();
		return 1;
	}

	public void onStart(Intent intent, int startId) {
		Log.d("ANAIS", "BackgroundSoundService onStart");
		// TODO
	}

	public IBinder onUnBind(Intent arg0) {
		Log.d("ANAIS", "BackgroundSoundService onUnBind");
		// TODO Auto-generated method stub
		return null;
	}

	public void onStop() {

	}

	public void onPause() {

	}

	@Override
	public void onDestroy() {
		Log.d("ANAIS", "BackgroundSoundService onDestroy");
		player.stop();
		player.release();
	}

	@Override
	public void onLowMemory() {
		Log.d("ANAIS", "BackgroundSoundService onLowMemory");
		player.release();
	}
}
