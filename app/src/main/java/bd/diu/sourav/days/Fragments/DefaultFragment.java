package bd.diu.sourav.days.Fragments;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.callback.ItemDragAndSwipeCallback;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.listener.OnItemSwipeListener;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import bd.diu.sourav.days.Activities.ReaderActivity;
import bd.diu.sourav.days.DateAndTimeConverter;
import bd.diu.sourav.days.QuickDaysAdapter;
import bd.diu.sourav.days.R;
import bd.diu.sourav.days.Realm.DaysModel;
import bd.diu.sourav.days.Realm.RealmRepository;

public class DefaultFragment extends Fragment {
    private List<DaysModel> days = new ArrayList<>();
    private RecyclerView recyclerView;
    private QuickDaysAdapter quickDaysAdapter;
    private String dltName;
    private int deletePos;
    private DaysModel tempItem;
    private int itemID;
    private Intent intent;
    private RealmRepository realmRepository;
    private DateAndTimeConverter dateAndTimeConverter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_default,container,false);
        dateAndTimeConverter = new DateAndTimeConverter();
        initRecyclerView(view);
        realmRepository = realmRepository.getInstance();
        quickDaysAdapter.addData(realmRepository.getAllData());
        quickDaysAdapter.notifyDataSetChanged();

        // Adding callback methods for swipe listners

        OnItemSwipeListener onItemSwipeListener = new OnItemSwipeListener() {
            @Override
            public void onItemSwipeStart(RecyclerView.ViewHolder viewHolder, int pos) {
                dltName = days.get(viewHolder.getAdapterPosition()).getBody();
                tempItem = days.get(viewHolder.getAdapterPosition());
                deletePos = viewHolder.getAdapterPosition();
                itemID = days.get(deletePos).getId();


                Log.i("SwipeTest","About to delete on Pos "+ deletePos);
                Log.i("SwipeTest", "About to delete ID: " + days.get(viewHolder.getAdapterPosition()).getId());
                Log.i("SwipeTest", "About to delete ID: " + days.get(deletePos).getId() + days.get(deletePos).getBody());

            }

            @Override
            public void clearView(RecyclerView.ViewHolder viewHolder, int pos) {}

            @Override
            public void onItemSwiped(RecyclerView.ViewHolder viewHolder, int pos) {

                realmRepository.deleteData(itemID);
                Snackbar snackbar = Snackbar
                        .make(Objects.requireNonNull(getActivity()).
                                findViewById(R.id.coordinator), dltName + " removed!", Snackbar.LENGTH_LONG);
                snackbar.setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // undo is selected, restore the deleted item
                        realmRepository.addData(tempItem.getBody(), tempItem.getDatetime(), tempItem.getDate(), tempItem.getTime());
                        quickDaysAdapter.addData(deletePos, tempItem);
                    }
                });
                snackbar.setActionTextColor(Color.YELLOW);
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
                intent = new Intent(getActivity(), ReaderActivity.class);
                intent.putExtra("text", days.get(position).getBody());
                intent.putExtra("date", dateAndTimeConverter.splitDate(days.get(position).getDatetime()));
                intent.putExtra("time", dateAndTimeConverter.splitTime(days.get(position).getDatetime()));
                startActivity(intent);
            }
        });
        return view;

    }


    private void initRecyclerView(View view) {
        recyclerView = view.findViewById(R.id.recycler_view);
        quickDaysAdapter = new QuickDaysAdapter(days);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(
                Objects.requireNonNull(getActivity()),
                DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(quickDaysAdapter);
    }

}
