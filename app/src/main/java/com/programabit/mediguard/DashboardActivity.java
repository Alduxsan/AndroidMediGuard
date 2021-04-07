package com.programabit.mediguard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.util.Log;
import android.widget.TextView;

import com.programabit.mediguard.rest.GuardsViewModel;
import com.programabit.mediguard.rest.MedicDto;
import com.programabit.mediguard.rest.MedicViewModel;

public class DashboardActivity extends AppCompatActivity {
    MedicViewModel medicViewModel;
    TextView username;
    String myToken = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        username = findViewById(R.id.username);

        Intent intent = getIntent();

        if(intent.getExtras() != null){
            myToken = (intent.getStringExtra("data"));
            Log.i("app token value set: ",myToken);
            medicViewModel = new MedicViewModel(this.getApplication(), myToken);
            Log.i("medicviewmodel created","ok");

            MedicDto myself = medicViewModel.getMyself();

            Log.i("loged in user",myself.toString());
            //if(myself !=null){
                //Log.i("Token test", myself.getDepartamento());
                //username.setText("Welcome "+ myself.getNombre_apellido());
            //}
        }

        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        appToolbar(toolbar, R.string.activity_name_dashboard,false);
    }

    // AppBar (toolbar y menu):
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar_options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mSettings:
                Intent intentSettings = new Intent(this, UserSettingsActivity.class);
                startActivity(intentSettings);
                break;
            case R.id.mContact:
                Intent intentContact = new Intent(this, ContactActivity.class);
                startActivity(intentContact);
                break;
            case R.id.mAbout:
                Intent intentAbout = new Intent(this, AboutActivity.class);
                startActivity(intentAbout);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void appToolbar(Toolbar toolbar, int activity_name, boolean enable) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setSubtitle(activity_name);
        getSupportActionBar().setDisplayHomeAsUpEnabled(enable);
    }

    public String getMyTokenValue() {
        return this.myToken;
    }
}