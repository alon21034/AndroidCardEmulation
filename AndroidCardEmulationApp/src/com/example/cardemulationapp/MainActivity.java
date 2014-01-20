package com.example.cardemulationapp;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.view.Menu;

public class MainActivity extends Activity {

	PendingIntent pendingIntent;
	IntentFilter[] filters;
	String[][] techLists;
	NfcAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		adapter = NfcAdapter.getDefaultAdapter(this);
		pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this,
				getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
		filters = new IntentFilter[] { new IntentFilter(
				NfcAdapter.ACTION_TECH_DISCOVERED) };
		techLists = new String[][] { { "android.nfc.tech.IsoPcdA" } };
		
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (adapter != null) {
			adapter.enableForegroundDispatch(this, pendingIntent, filters,
					techLists);
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (adapter != null) {
			adapter.disableForegroundDispatch(this);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
