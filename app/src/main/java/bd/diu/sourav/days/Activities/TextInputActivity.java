package bd.diu.sourav.days.Activities;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import bd.diu.sourav.days.R;
import bd.diu.sourav.days.Sqlite;

public class TextInputActivity extends AppCompatActivity {
    Sqlite database = new Sqlite(this);
    EditText editText;
    TextView textView;
    DateFormat dateFormat;

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_input);
        if (android.os.Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(getResources().getColor(R.color.NavColor));
        }
        textView = findViewById(R.id.date_view);
        textView.setText(getDate());
    }

    public void saveData(View view) {
        runSaveData();
        finish();
    }

    private void runSaveData(){
        editText = findViewById(R.id.textField);
        String text;
        try {
            text = editText.getText().toString();
        } catch (Exception e) {
            e.printStackTrace();
            text = "";
        }

        String date = getDate();
        String time = getTime();

        database.addData(date,text,time);
    }


    private String getDate(){
        dateFormat = new SimpleDateFormat("MMMM d");
        return dateFormat.format(Calendar.getInstance().getTime());
    }

    private String getTime(){
        dateFormat = new SimpleDateFormat("h:mm a");
        return dateFormat.format(Calendar.getInstance().getTime());
    }



}
