package bd.diu.sourav.days;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * SERIOUSLY I HATE THIS CLASS
 * IDK WHAT IT DOES... JK I KNOW but why so many shits?
 */

public class DaysAdapter extends RecyclerView.Adapter<DaysAdapter.MyViewHolder>{

    private List<Days> dayList;

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView date, text, time;

        public MyViewHolder(View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.date);
            text = itemView.findViewById(R.id.text);
            time = itemView.findViewById(R.id.time);
        }
    }

    public DaysAdapter(List<Days> dayList) {
        this.dayList = dayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_layout,viewGroup, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
        Days days = dayList.get(position);
        myViewHolder.date.setText(days.getDate());
        myViewHolder.text.setText(days.getText());
        myViewHolder.time.setText(days.getTime());
    }

    @Override
    public int getItemCount() {
        return dayList.size();
    }

    // This one just updates the data
    // I HAVE NO IDEA HOW IT DOES THIS BUT IT WORKS  ¯\_(ツ)_/¯

    public void update(List<Days> dayList){
        this.dayList.clear();
        this.dayList.addAll(dayList);
        notifyDataSetChanged();
    }

}

