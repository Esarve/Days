package bd.diu.sourav.days;

import android.content.Intent;
import android.os.Build;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Objects;

public class Reader extends AppCompatActivity {
    String text;
    String date;
    String time;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reader);
        initiateView();

        if (android.os.Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(getResources().getColor(R.color.NavColor));
        }
    }

    private void initiateView(){
        TextView viewText = findViewById(R.id.displayText);
        TextView viewDate = findViewById(R.id.viewDate);
        TextView viewTime = findViewById(R.id.viewTime);

        text = Objects.requireNonNull(getIntent().getExtras()).getString("text");
        date = Objects.requireNonNull(getIntent().getExtras()).getString("date");
        time = Objects.requireNonNull(getIntent().getExtras()).getString("time");
        id = Objects.requireNonNull(getIntent().getIntExtra("id",9999999));

        Log.i("Database",String.format("Recieved in Reader Date: %s Text: %s Time: %s ID: %s",date,text,time,id));

        viewText.setText(text);
        viewDate.setText(date);
        viewTime.setText(time);
    }

    public void openEditor(View view){
        Intent intent = new Intent(getApplicationContext(), Editor.class);
        intent.putExtra("task",true);
        intent.putExtra("text",text);
        intent.putExtra("date",date);
        intent.putExtra("time",time);
        intent.putExtra("id",id);
        startActivity(intent);
    }
}
