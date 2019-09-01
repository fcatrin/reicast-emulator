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

		// re-enable if needed after testing JNIdc.config(getFilesDir().getAbsolutePath()); // TODO check if this requires something special
		
		// Read intent from RetroX
		String gamePath = getIntent().getStringExtra("PATH_GAME");
		onGameSelected(Uri.parse(gamePath));
		
		String biosPath  = getIntent().getStringExtra("PATH_BIOS");  // dc_boot.bin
		String flashPath = getIntent().getStringExtra("PATH_FLASH"); // dc_flash.bin
		
		// TODO read other intent data
		
		// Flush the intent to prevent multiple calls
		getIntent().setData(null);
        setIntent(null);
        // Config.externalIntent = true;

	}
	
	// TODO check if this can be ported to RetroXUtils
	public void generateErrorLog() {
		new GenerateLogs(RetroXActivity.this).execute(getFilesDir().getAbsolutePath());
	}

	public void onGameSelected(Uri uri) {
		boolean useNative = true; // Config.nativeact = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getBoolean(Config.pref_nativeact, Config.nativeact);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD && useNative) {
			startActivity(new Intent("com.reicast.EMULATOR", uri, getApplicationContext(),
					NativeGLActivity.class));
		} else {
			startActivity(new Intent("com.reicast.EMULATOR", uri, getApplicationContext(),
					GL2JNIActivity.class));
		}
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		return super.onKeyDown(keyCode, event);
	}
	
}
