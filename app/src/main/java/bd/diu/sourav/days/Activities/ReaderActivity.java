package bd.diu.sourav.days.Activities;

import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

import bd.diu.sourav.days.R;

public class ReaderActivity extends AppCompatActivity {

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

        String text = Objects.requireNonNull(getIntent().getExtras()).getString("text");
        String date = Objects.requireNonNull(getIntent().getExtras()).getString("date");
        String time = Objects.requireNonNull(getIntent().getExtras()).getString("time");

        viewText.setText(text);
        viewDate.setText(date);
        viewTime.setText(time);

    }
}
