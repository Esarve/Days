package bd.diu.sourav.days;

import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.callback.ItemDragAndSwipeCallback;
import com.chad.library.adapter.base.listener.OnItemSwipeListener;

import java.util.ArrayList;
import java.util.List;

public class DefaultFragment extends Fragment {
    private Sqlite sqlite;
    private List<Days> days = new ArrayList<>();
    private RecyclerView recyclerView;
    private QuickDaysAdapter quickDaysAdapter;
    private String dltName;
    private int deletePos;
    private Days deletedItem;
    private int itemID;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        sqlite = new Sqlite(getActivity());
        View view = inflater.inflate(R.layout.fragment_default,container,false);

        // setting up recyclerview and SHITS

        recyclerView = view.findViewById(R.id.recycler_view);
        quickDaysAdapter = new QuickDaysAdapter(days);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(quickDaysAdapter);
        quickDaysAdapter.addData(sqlite.getData());
        quickDaysAdapter.notifyDataSetChanged();

        OnItemSwipeListener onItemSwipeListener = new OnItemSwipeListener() {
            @Override
            public void onItemSwipeStart(RecyclerView.ViewHolder viewHolder, int pos) {
                dltName = days.get(viewHolder.getAdapterPosition()).getDate();
                deletedItem = days.get(viewHolder.getAdapterPosition());
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
                sqlite.remove(itemID);
                Snackbar snackbar = Snackbar
                        .make(getActivity().findViewById(R.id.coordinator), dltName + " removed!", Snackbar.LENGTH_LONG);
                snackbar.setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // undo is selected, restore the deleted item
                        sqlite.addData(deletedItem.getDate(),deletedItem.getText(),deletedItem.getTime());
                        quickDaysAdapter.addData(deletePos, deletedItem);
                    }
                });
                snackbar.setActionTextColor(Color.YELLOW);
                snackbar.show();
            }

            @Override
            public void onItemSwipeMoving(Canvas canvas, RecyclerView.ViewHolder viewHolder, float dX, float dY, boolean isCurrentlyActive) {

            }
        };

        ItemDragAndSwipeCallback itemDragAndSwipeCallback = new ItemDragAndSwipeCallback(quickDaysAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemDragAndSwipeCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

        quickDaysAdapter.enableSwipeItem();
        quickDaysAdapter.setOnItemSwipeListener(onItemSwipeListener);
        return view;

    }

}
