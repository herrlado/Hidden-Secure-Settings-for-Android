package org.herrlado.android.settings;

import android.Manifest.permission;
import android.app.Activity;
import android.os.Bundle;
import android.provider.Settings;
import android.provider.Settings.SettingNotFoundException;
import android.renderscript.Program.TextureType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

public class HiddenSecureSettingActivity extends Activity implements OnClickListener {

    /**
     * The interval in milliseconds after which Wi-Fi is considered idle.
     * When idle, it is possible for the device to be switched from Wi-Fi to
     * the mobile data network.
     * @hide
     */
    public static final String WIFI_IDLE_MS = "wifi_idle_ms";
	
	/**
     * See {@link Settings.Secure#WIFI_IDLE_MS}. This is the default value if a
     * Settings.Secure value is not present. This timeout value is chosen as
     * the approximate point at which the battery drain caused by Wi-Fi
     * being enabled but not active exceeds the battery drain caused by
     * re-establishing a connection to the mobile data network.
     */
    private static final long DEFAULT_IDLE_MS = 15 * 60 * 1000; /* 15 minutes */


	private static final String TAG = "HiddenSecureSettings";
	
	
	EditText wifi_idle_ms;
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
     
        wifi_idle_ms = (EditText) findViewById(R.id.wifi_idle_ms);
        findViewById(R.id.ok).setOnClickListener(this);
    }
    
    @Override
    protected void onResume() {
    	long l = Settings.Secure.getLong(getContentResolver(), WIFI_IDLE_MS, DEFAULT_IDLE_MS);
    	wifi_idle_ms.setText(String.valueOf(l));
    	super.onResume();
    }

    
    private void persistInput(){
    	String value = wifi_idle_ms.getText().toString();
		if(TextUtils.isEmpty(value)){
			Settings.Secure.putLong(getContentResolver(), WIFI_IDLE_MS, DEFAULT_IDLE_MS);
		}
    }
    
	@Override
	public void onClick(View arg0) {
		if(arg0.getId() == R.id.ok){
			persistInput();
		}
	}
    
}