package com.tylorlilley.kbrandomizer3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

abstract public class BasicActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_randomizer) {
            selectedRandomizerOption();
        }
        else if (id == R.id.action_settings) {
            selectedSettingsOption();
        }
        else if (id == R.id.action_about) {
            selectedAboutOption();
        }


        return super.onOptionsItemSelected(item);
    }

    abstract protected void selectedRandomizerOption();
    abstract protected void selectedSettingsOption();
    abstract protected void selectedAboutOption();

}
