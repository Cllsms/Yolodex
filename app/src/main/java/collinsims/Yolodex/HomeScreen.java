package collinsims.Yolodex;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

public class HomeScreen extends Activity {

    public SharedPreferences pref;
    public PreferenceChangeListener listener;
    public ArrayList<String> businesses;
    private ArrayAdapter<String> adapter;
    static final int BUSINESS_TITLE_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        listener = new PreferenceChangeListener();
        pref.registerOnSharedPreferenceChangeListener(listener);
        final GlobalVariables globalVariable = (GlobalVariables) getApplicationContext();
        Spinner spinner = (Spinner) findViewById(R.id.business_spinner);
        spinner.setOnItemSelectedListener(new spinnerAdapter());
        try {
            globalVariable.Load_Businesses();
            businesses = globalVariable.getBusinesses();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Create an ArrayAdapter using the string array and a default spinner layout
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, businesses);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner

        /*if(!pref.getString("business","").equals("")){
            Log.d("Preference Business -----> ",pref.getString("business",""));
            try {
                globalVariable.Save_Business(pref.getString("business",""));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/
        //if(!globalVariable.hasItems()) adapter.add("Please Choose A Business");

        spinner.setAdapter(adapter);

        // Font path
        String fontPath = "fonts/Existence-Light.otf";

        // text view label
        TextView txtYolodex = (TextView) findViewById(R.id.titleMainScreen);

        // Loading Font Face
        Typeface tf = Typeface.createFromAsset(getAssets(), fontPath);

        // Applying font
        txtYolodex.setTypeface(tf);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch(id){
            case R.id.action_settings:
                Intent intent2 = new Intent(this, SettingsActivity.class);
                startActivity(intent2);
                break;
            case R.id.addBusiness:
                Intent intent = new Intent(this, AddNewBusiness.class);
                intent.putExtra("newBusiness","");
                startActivityForResult(intent, BUSINESS_TITLE_REQUEST);
                break;
            case R.id.editBusinesses:
                Intent intent1 = new Intent(this, EditBusinesses.class);
                startActivity(intent1);
                break;
        }
        return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case (BUSINESS_TITLE_REQUEST) : {
                if (resultCode == Activity.RESULT_OK) {
                    String title = data.getStringExtra("newBusiness");
                    Log.d("Business Title ------> ", title);
                    Add_Data(title);
                }
                break;
            }
        }
    }

    public void Add_Data(String name){
        Log.d("Add", "Add New Business");
        businesses.add(name);
        try {
            GlobalVariables.Save_Business();
        } catch (IOException e) {
            e.printStackTrace();
        }
        adapter.notifyDataSetChanged();
    }

    public void showCalendar(View v){
        Intent intent = new Intent(this, CalendarActivity.class);
        startActivity(intent);
        onPause();
    }

    public void showContacts(View v){
        Intent intent = new Intent(this, ContactsActivity.class);
        startActivity(intent);
        onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        pref.unregisterOnSharedPreferenceChangeListener(listener);
    }

    private class PreferenceChangeListener implements SharedPreferences.OnSharedPreferenceChangeListener {
        @Override
        public void onSharedPreferenceChanged(SharedPreferences prefs, String key) {
            if (key.equals("business")) {
                Log.v("PreferenceChange", "**** KEY test_preference_key modified ****");
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("onPause -----> ","onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("onResume -----> ", "onResume");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("onStop -----> ", "onStop");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d("onRestoreInstanceState ------> ", "onRestoreInstanceState");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d("onSaveInstanceState -----> ","onSaveInstanceState");
    }
}
