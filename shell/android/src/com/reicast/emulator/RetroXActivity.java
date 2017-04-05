package com.reicast.emulator;

import com.reicast.emulator.config.Config;
import com.reicast.emulator.debug.GenerateLogs;
import com.reicast.emulator.emu.JNIdc;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.KeyEvent;

public class RetroXActivity extends Activity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// TODO set error handler

		String homePath  = getIntent().getStringExtra("homePath");  // location for dc_boot.bin and dc_flash.bin
		JNIdc.config(homePath);
		
		// Read intent from RetroX
		String gamePath = getIntent().getStringExtra("gamePath");
		onGameSelected(Uri.parse(gamePath));
		
		
		// TODO read other intent data
		
		// Flush the intent to prevent multiple calls
		getIntent().setData(null);
        setIntent(null);
        Config.externalIntent = true;

	}
	
	// TODO check if this can be ported to RetroXUtils
	public void generateErrorLog() {
		new GenerateLogs(RetroXActivity.this).execute(getFilesDir().getAbsolutePath());
	}

	public void onGameSelected(Uri uri) {
		Config.nativeact = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getBoolean(Config.pref_nativeact, Config.nativeact);
		Class<? extends Activity> activityClass = 
				(Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD && Config.nativeact) ?
				GL2JNINative.class :
				GL2JNIActivity.class;
		
		Intent intent = new Intent("com.reicast.EMULATOR", uri, getApplicationContext(), activityClass);
		intent.putExtra("retrox", true);
		intent.putExtra("infoTop"   , getIntent().getStringExtra("infoTop"));
		intent.putExtra("infoBottom", getIntent().getStringExtra("infoBottom"));
		startActivity(intent);
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		return super.onKeyDown(keyCode, event);
	}
	
}
