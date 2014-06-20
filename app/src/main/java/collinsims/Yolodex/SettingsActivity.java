package collinsims.Yolodex;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class SettingsActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref_general);
    }


    @Override
    protected void onStop() {
        super.onStop();  // Always call the superclass method first
    }

}

