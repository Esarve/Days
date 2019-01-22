package bd.diu.sourav.days;

import android.os.Build;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

public class Editor extends AppCompatActivity {
    EditText editText;
    TextView dateView;
    Button save;
    DateFormat dateFormat;
    Boolean openUpdator;
    String text;
    String date;
    String time;
    int id;

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);
        if (android.os.Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(getResources().getColor(R.color.NavColor));
        }

        dateView = findViewById(R.id.date_view);
        editText = findViewById(R.id.textField);
        save = findViewById(R.id.save);

        openUpdator = getIntent().getBooleanExtra("task",false);

        if (openUpdator){
            Log.i("editor", "opening update mode");
            edit();
        }else {
            Log.i("editor", "opening Add mode");
            add();
        }

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });
    }

    public void saveData() {
        try {
            text = editText.getText().toString();
        } catch (Exception e) {
            text = "";
        }

        if (openUpdator){
            Log.i("editor", "Updating Data");
            DatabaseHelper.getInstance(getApplicationContext()).updateData(date,text,time,id);
        }else {
            Log.i("editor", "Adding Data");
            DatabaseHelper.getInstance(getApplicationContext()).addData(date,text,time);
        }
        finish();
    }

    private String getDate(){
        dateFormat = new SimpleDateFormat("MMMM d");
        return dateFormat.format(Calendar.getInstance().getTime());
    }

    private String getTime(){
        dateFormat = new SimpleDateFormat("h:mm a");
        return dateFormat.format(Calendar.getInstance().getTime());
    }

    private void edit(){
        text = Objects.requireNonNull(getIntent().getExtras()).getString("text");
        date = Objects.requireNonNull(getIntent().getExtras()).getString("date");
        time = Objects.requireNonNull(getIntent().getExtras()).getString("time");
        id = Objects.requireNonNull(getIntent().getIntExtra("id",9999999));

        Log.i("Database",String.format("Recieved in Editor Date: %s Text: %s Time: %s ID: %s",date,text,time,id));

        editText.setText(text);
        dateView.setText(date);
    }

    private void add(){
        dateView.setText(getDate());
        date = getDate();
        time = getTime();
    }


}
