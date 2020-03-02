package bd.diu.sourav.days.Activities;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;

import bd.diu.sourav.days.Fragments.AboutFragment;
import bd.diu.sourav.days.Fragments.DefaultFragment;
import bd.diu.sourav.days.Fragments.StatsFragment;
import bd.diu.sourav.days.R;
import io.realm.Realm;


public class MainActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener navlistner =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectFrag = null;

                    switch (menuItem.getItemId()) {
                        case R.id.nav_home:
                            selectFrag = new DefaultFragment();
                            setTitle("Days");
                            break;
                        case R.id.nav_stats:
                            selectFrag = new StatsFragment();
                            setTitle("Stats");
                            break;
                        case R.id.nav_about:
                            selectFrag = new AboutFragment();
                            setTitle("About");
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_cont, selectFrag).commit();
                    return true;
                }
            };

    @Override
    protected void onResume() {
        super.onResume();
        loadDefaultFragment();
        CoordinatorLayout coordinatorLayout = findViewById(R.id.coordinator);
        Snackbar snackbar = Snackbar
                .make(coordinatorLayout, "Data Loaded Successfully", Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.refresh, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Realm.init(this);
        setContentView(R.layout.activity_main);
        changeNavBarColor();
        loadDefaultFragment();
        BottomNavigationView bottomNav = findViewById(R.id.btm_nav);
        bottomNav.setOnNavigationItemSelectedListener(navlistner);
    }

    private void changeNavBarColor() {
        if (android.os.Build.VERSION.SDK_INT >= 27) {
            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.NavColor));
        }
    }

    // Loads a default fragment at beginning
    private void loadDefaultFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_cont, new DefaultFragment()).commit();
    }

    // Changes the navigation bar according to API level
    public void openEditor(View view) {
        Intent intent = new Intent(this, TextInputActivity.class);
        this.startActivity(intent);
    }

}
