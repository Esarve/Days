package bd.diu.sourav.days.Activities;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import bd.diu.sourav.days.R;
import bd.diu.sourav.days.Realm.RealmRepository;

public class TextInputActivity extends AppCompatActivity {
    private static final String TAG = "TextInputActivity";
    private DateFormat dateFormat;
    private RealmRepository realmRepository;


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
        TextView textView = findViewById(R.id.date_view);
        textView.setText(getDate());
        realmRepository = new RealmRepository();
    }

    public void saveData(View view) {
        runSaveData();
        finish();
    }

    private void runSaveData(){
        EditText editText = findViewById(R.id.textField);
        String text;
        try {
            text = editText.getText().toString();
        } catch (Exception e) {
            e.printStackTrace();
            text = "";
        }

        String date = getDate();
        String time = getTime();
        long timestamp = (System.currentTimeMillis() / 1000);
        realmRepository.addData(text, timestamp, date, time);
        Log.d(TAG, "runSaveData: Recorded Timestamp: " + timestamp);

    }


    @SuppressLint("SimpleDateFormat")
    private String getDate(){
        dateFormat = new SimpleDateFormat("d MMM");
        return dateFormat.format(Calendar.getInstance().getTime());
    }

    @SuppressLint("SimpleDateFormat")
    private String getTime(){
        dateFormat = new SimpleDateFormat("h:mm a");
        return dateFormat.format(Calendar.getInstance().getTime());
    }
}
