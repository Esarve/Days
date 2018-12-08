package bd.diu.sourav.days;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    Intent intent;
   /* Sqlite database = new Sqlite(this);
    private List<Days> days = new ArrayList<>();
    RecyclerView recyclerView;
    DaysAdapter daysAdapter;*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        setTitle(Html.fromHtml("<font color='#111111'>Days </font>"));
        changeNavBarColor();
        loadDefaultFragment();
        BottomNavigationView bottomNav = findViewById(R.id.btm_nav);
        bottomNav.setOnNavigationItemSelectedListener(navlistner);

        //database.addData("Jan 1","Today was a good day","10:10 AM");
        /*recyclerView = findViewById(R.id.recycler_view);

        daysAdapter = new DaysAdapter(days);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(daysAdapter);
        daysAdapter.update(database.getData());
        daysAdapter.notifyDataSetChanged();*/
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
    private BottomNavigationView.OnNavigationItemSelectedListener navlistner =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectFrag = null;

                    switch (menuItem.getItemId()){
                        case R.id.nav_home:
                            selectFrag = new DefaultFragment();
                            setTitle("Days");
//                            setTitle(Html.fromHtml("<font color='#111111'>Days </font>"));
                            break;
                        case R.id.nav_stats:
                            selectFrag = new StatsFragment();
                            setTitle("Stats");
//                            setTitle(Html.fromHtml("<font color='#111111'>Stats </font>"));;
                            break;
                        case R.id.nav_about:
                            selectFrag = new AboutFragment();
                            setTitle("About");
//                            setTitle(Html.fromHtml("<font color='#111111'>About </font>"));
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_cont,selectFrag).commit();
                    return true;
                }
            };


    private void changeNavBarColor(){
        if (android.os.Build.VERSION.SDK_INT >= 27) {
            getWindow().setNavigationBarColor(ContextCompat.getColor(this,R.color.NavColor));
        }
    }

    // Loads a default fragment at beginning
    private void loadDefaultFragment(){
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_cont, new DefaultFragment()).commit();
    }

    // Changes the navigation bar according to API level
    public void openEditor(View view){
        intent = new Intent(this, TextInput.class);
        this.startActivity(intent);
    }

    public void removeFromDB(Context context,int pos){
        Sqlite database = new Sqlite(context);
        database.remove(pos);
    }
}
