package bd.diu.sourav.days;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.snackbar.Snackbar;

import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.callback.ItemDragAndSwipeCallback;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.listener.OnItemSwipeListener;

import java.util.ArrayList;
import java.util.List;

public class DefaultFragment extends Fragment {
    private List<Days> days = new ArrayList<>();
    private RecyclerView recyclerView;
    private QuickDaysAdapter quickDaysAdapter;
    private String dltName;
    private int deletePos;
    private Days tempItem;
    private int itemID;
    private Intent intent;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_default,container,false);
        // setting up recyclerview and SHITS
        recyclerView = view.findViewById(R.id.recycler_view);
        quickDaysAdapter = new QuickDaysAdapter(days);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(quickDaysAdapter);
        quickDaysAdapter.addData(DatabaseHelper.getInstance(getActivity()).getData());
        quickDaysAdapter.notifyDataSetChanged();

        // Adding callback methods for swipe listners

        OnItemSwipeListener onItemSwipeListener = new OnItemSwipeListener() {
            @Override
            public void onItemSwipeStart(RecyclerView.ViewHolder viewHolder, int pos) {
                dltName = days.get(viewHolder.getAdapterPosition()).getDate();
                tempItem = days.get(viewHolder.getAdapterPosition());
                deletePos = viewHolder.getAdapterPosition();
                itemID = days.get(deletePos).getId();


                Log.i("SwipeTest","About to delete on Pos "+ deletePos);
                Log.i("SwipeTest", "About to delete ID: " + days.get(viewHolder.getAdapterPosition()).getId());
                Log.i("SwipeTest", "About to delete ID: " + days.get(deletePos).getId() + days.get(deletePos).getText());

            }

            @Override
            public void clearView(RecyclerView.ViewHolder viewHolder, int pos) {}

            @Override
            public void onItemSwiped(RecyclerView.ViewHolder viewHolder, int pos) {

                DatabaseHelper.getInstance(getActivity()).remove(itemID);
                Snackbar snackbar = Snackbar
                        .make(getActivity().findViewById(R.id.coordinator), dltName + " removed!", Snackbar.LENGTH_LONG);
                snackbar.setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // undo is selected, restore the deleted item
                        DatabaseHelper.getInstance(getActivity()).addData(tempItem.getDate(), tempItem.getText(), tempItem.getTime());
                        quickDaysAdapter.addData(deletePos, tempItem);
                    }
                });
                snackbar.setActionTextColor(Color.WHITE);
                snackbar.getView().setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.error,null));
                snackbar.show();
            }

            @Override
            public void onItemSwipeMoving(Canvas canvas, RecyclerView.ViewHolder viewHolder, float dX, float dY, boolean isCurrentlyActive) {}

        };

        ItemDragAndSwipeCallback itemDragAndSwipeCallback = new ItemDragAndSwipeCallback(quickDaysAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemDragAndSwipeCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

        quickDaysAdapter.enableSwipeItem();
        quickDaysAdapter.setOnItemSwipeListener(onItemSwipeListener);

        recyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Log.i("Click","Clicked on "+position);
                int id = days.get(position).getId();
                intent = new Intent(getActivity(),Reader.class);
                intent.putExtra("text", days.get(position).getText());
                intent.putExtra("date",days.get(position).getDate());
                intent.putExtra("time",days.get(position).getTime());
                intent.putExtra("id",id);
                intent.putExtra("task","add");
                startActivity(intent);
                Log.i("Database","Recived id: "+days.get(position).getId());
            }
        });
        return view;

    }

}
