package com.programabit.mediguard.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.programabit.mediguard.R;
import com.programabit.mediguard.domain.AvaibleGuardViewModel;

public class AvaibleGuardsActivity extends AppCompatActivity {
    private AvaibleGuardViewModel guardsViewModel;
    private RecyclerView recyclerView;
    private String myToken;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_avaible_guards);

        final AvaibleGuardsListAdapter adapter = new AvaibleGuardsListAdapter(new AvaibleGuardsListAdapter.guardDiff());

        recyclerView = findViewById(R.id.recyclerViewGuards);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Intent intent = getIntent();

        if(intent.getExtras() != null) {
            myToken = (intent.getStringExtra("token"));
        }
        Log.i("My Guards Activity","got token");
        guardsViewModel = new ViewModelProvider(this,
                new AvaibleGuardsFactory(this.getApplication(), myToken)).get(AvaibleGuardViewModel.class);
        Log.i("My Guards Activity","set view model");
        guardsViewModel.getMyGuards().observe(this,
                myGuards->{adapter.submitList(myGuards);});
        Log.i("My Guards Activity","observing my guards list");

        // Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        appToolbar(toolbar, R.string.activity_name_avalible_guards,true);
    }

    // AppBar (toolbar y menu):
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar_options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.mSettings) {
            startActivity(new Intent(this, UserSettingsActivity.class));
        } else if (itemId == R.id.mContact) {
            startActivity(new Intent(this, ContactActivity.class));
        } else if (itemId == R.id.mAbout) {
            startActivity(new Intent(this, AboutActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    // AppBar toolbar:
    private void appToolbar(Toolbar toolbar, int activity_name, boolean enable) {
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null) {
            getSupportActionBar().setSubtitle(activity_name);
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(enable);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
