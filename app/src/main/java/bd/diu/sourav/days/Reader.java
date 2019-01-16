package bd.diu.sourav.days;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Objects;

public class Reader extends AppCompatActivity {

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
