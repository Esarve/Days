package bd.diu.sourav.days;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (android.os.Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(getResources().getColor(R.color.NavColor));
        }

        BottomNavigationView bottomNav = findViewById(R.id.btm_nav);
        bottomNav.setOnNavigationItemSelectedListener(navlistner);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_cont, new DefaultFragment()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navlistner =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectFrag = null;

                    switch (menuItem.getItemId()){
                        case R.id.nav_home:
                            selectFrag = new DefaultFragment();
                            setTitle(R.string.app_name);
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

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_cont,selectFrag).commit();
                    return true;
                }
            };



    public void openEditor(View view){
        intent = new Intent(this, TextInput.class);
        this.startActivity(intent);
    }
}
