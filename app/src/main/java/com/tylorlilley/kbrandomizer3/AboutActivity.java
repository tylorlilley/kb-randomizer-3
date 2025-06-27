package com.tylorlilley.kbrandomizer3;

import android.content.Intent;
import android.os.Bundle;

public class AboutActivity extends BasicActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_about);
    }

    protected void selectedRandomizerOption() {
        finish();
    }

    protected void selectedSettingsOption() {
        finish();
        startActivity(new Intent(this, SettingsActivity.class));
    }

    protected void selectedAboutOption() {
        closeOptionsMenu();
    }

}
