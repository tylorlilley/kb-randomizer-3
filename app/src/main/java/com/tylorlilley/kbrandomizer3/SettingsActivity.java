package com.tylorlilley.kbrandomizer3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

public class SettingsActivity extends BasicActivity {

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_settings);

        // Initialize SharedPreferences
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        editor = pref.edit();
        updateCheckBoxes();
    }

    protected void selectedRandomizerOption() {
        finish();
    }

    protected void selectedSettingsOption() {
        closeOptionsMenu();
    }

    protected void selectedAboutOption() {
        finish();
        startActivity(new Intent(this, AboutActivity.class));
    }

    private boolean isLastBox() {
        int boxesChecked = 0;
        if (pref.getBoolean(getString(R.string.base_game), false)) boxesChecked++;
        if (pref.getBoolean(getString(R.string.nomads), false)) boxesChecked++;
        if (pref.getBoolean(getString(R.string.crossroads), false)) boxesChecked++;
        if (pref.getBoolean(getString(R.string.marshlands), false)) boxesChecked++;
        if (pref.getBoolean(getString(R.string.harvest), false)) boxesChecked++;
        return (boxesChecked < 2);
    }

    private void updateCheckBoxes() {
        ((CheckBox)findViewById(R.id.baseGameBox)).setChecked(pref.getBoolean(getString(R.string.base_game), false));
        ((CheckBox)findViewById(R.id.nomadsBox)).setChecked(pref.getBoolean(getString(R.string.nomads), false));
        ((CheckBox)findViewById(R.id.crossroadsBox)).setChecked(pref.getBoolean(getString(R.string.crossroads), false));
        ((CheckBox)findViewById(R.id.marshlandsBox)).setChecked(pref.getBoolean(getString(R.string.marshlands), false));
        ((CheckBox)findViewById(R.id.harvestBox)).setChecked(pref.getBoolean(getString(R.string.harvest), false));
        ((CheckBox)findViewById(R.id.capitolsBox)).setChecked(pref.getBoolean(getString(R.string.capitols), false));
        ((CheckBox)findViewById(R.id.cavesBox)).setChecked(pref.getBoolean(getString(R.string.caves), false));
        ((CheckBox)findViewById(R.id.islandBox)).setChecked(pref.getBoolean(getString(R.string.island), false));
        ((CheckBox)findViewById(R.id.emperorsChoiceBox)).setChecked(pref.getBoolean(getString(R.string.emperor_s_choice), false));
        ((CheckBox)findViewById(R.id.useWithAssignedBox)).setChecked(pref.getBoolean(getString(R.string.use_with_assigned_board), false));
        ((CheckBox)findViewById(R.id.useWithEmperorsChoiceBox)).setChecked(pref.getBoolean(getString(R.string.use_with_emperor_s_choice), false));
        ((CheckBox)findViewById(R.id.alwaysUseBox)).setChecked(pref.getBoolean(getString(R.string.always_use), false));
    }


    public void updateExpansionSettings(View v) {
        CheckBox currentBox = (CheckBox)v;
        if (!currentBox.isChecked() && isLastBox()) {
            // TODO: Display Error Message
            currentBox.setChecked(true);
        }
        updateGenericSettings(v);
    }

    public void updatePromoSettings(View v) {
        CheckBox currentBox = (CheckBox)v;
        CheckBox useEmperorsChoiceBox = (CheckBox)findViewById(R.id.emperorsChoiceBox);
        CheckBox useWithEmperorsChoiceBox = (CheckBox)findViewById(R.id.useWithEmperorsChoiceBox);

        if (!currentBox.isChecked() && currentBox == useEmperorsChoiceBox) {
            // TODO: Display Error Message
            useWithEmperorsChoiceBox.setChecked(false);
        }
        updateGenericSettings(v);
    }

    public void updatePromoOptionSettings(View v) {
        CheckBox currentBox = (CheckBox)v;

        CheckBox useEmperorsChoiceBox = (CheckBox)findViewById(R.id.emperorsChoiceBox);
        CheckBox useWithAssignedBox = (CheckBox)findViewById(R.id.useWithAssignedBox);
        CheckBox useWithEmperorsChoiceBox = (CheckBox)findViewById(R.id.useWithEmperorsChoiceBox);
        CheckBox alwaysUseBox = (CheckBox)findViewById(R.id.alwaysUseBox);

        // Uncheck Other options
        if (currentBox != alwaysUseBox) { alwaysUseBox.setChecked(false); }
        if (currentBox != useWithAssignedBox) { useWithAssignedBox.setChecked(false); }
        if (currentBox != useWithEmperorsChoiceBox) { useWithEmperorsChoiceBox.setChecked(false); }

        if (currentBox.isChecked() && currentBox == useWithEmperorsChoiceBox && !useEmperorsChoiceBox.isChecked()) {
            // TODO: Display Error Message
            currentBox.setChecked(false);
        }
        updateGenericSettings(v);

        updateGenericSettings(alwaysUseBox);
        updateGenericSettings(useWithAssignedBox);
        updateGenericSettings(useWithEmperorsChoiceBox);
    }

    public void updateGenericSettings(View v) {
        CheckBox currentBox = (CheckBox)v;
        editor.putBoolean(currentBox.getText().toString(), (currentBox.isChecked()));
        editor.apply();
    }

}
