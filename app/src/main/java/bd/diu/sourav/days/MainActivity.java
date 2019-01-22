package bd.diu.sourav.days;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;

import net.sqlcipher.database.SQLiteDatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;


public class MainActivity extends AppCompatActivity {

    TextView title;
    Intent intent;
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        changeNavBarColor();
        loadDefaultFragment();
        BottomNavigationView bottomNav = findViewById(R.id.btm_nav);
        bottomNav.setOnNavigationItemSelectedListener(navlistner);
        SQLiteDatabase.loadLibs(getApplicationContext());

        title = findViewById(R.id.title);
        title.setText("Days");

    }

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
        intent = new Intent(this, Editor.class);
        this.startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        DatabaseHelper.getInstance(getApplicationContext()).db.close();
        Log.i("Database", "Closed");
    }
}
