package com.androidjson.one_signal;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    one_signal myApplication;
    TextView name;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );
        myApplication = one_signal.getmInstance();

        prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        name = findViewById(R.id.name);
        name.setText(prefs.getString("nameRecieved", "Hello World!")+"-"+prefs.getString("phoneRecieved", "00000000!")+"-"+prefs.getString("countryRecieved", "Australia"));
    }
}
