package bd.diu.sourav.days;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
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

import java.util.ArrayList;
import java.util.List;

public class DefaultFragment extends Fragment implements RecyclerItemTouchHelper.RecyclerItemTouchHelperListener  {
    Sqlite sqlite;
    private List<Days> days = new ArrayList<>();
    RecyclerView recyclerView;
    DaysAdapter daysAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        sqlite = new Sqlite(getActivity());
        View view = inflater.inflate(R.layout.fragment_default,container,false);

        // setting up recyclerview and SHITS

        recyclerView = view.findViewById(R.id.recycler_view);
        daysAdapter = new DaysAdapter(days);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(daysAdapter);
        daysAdapter.update(sqlite.getData());
        daysAdapter.notifyDataSetChanged();
        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);
        return view;

    }


    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (viewHolder instanceof DaysAdapter.MyViewHolder) {
            // get the removed item name to display it in snack bar
            String name = days.get(viewHolder.getAdapterPosition()).getDate();

            // backup of removed item for undo purpose
            final Days deletedItem = days.get(viewHolder.getAdapterPosition());
            final int deletedIndex = viewHolder.getAdapterPosition();

            // removes item from Database
            sqlite.remove(days.get(viewHolder.getAdapterPosition()).getId());
            // remove the item from recycler view
            daysAdapter.removeItem(viewHolder.getAdapterPosition());



            // showing snack bar with Undo option
            Snackbar snackbar = Snackbar
                    .make(getActivity().findViewById(R.id.coordinator), name + " removed from cart!", Snackbar.LENGTH_LONG);
            snackbar.setAction("UNDO", new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    // undo is selected, restore the deleted item
                    sqlite.addData(deletedItem.getDate(),deletedItem.getText(),deletedItem.getTime());
                    daysAdapter.restoreItem(deletedItem, deletedIndex);
                }
            });
            snackbar.setActionTextColor(Color.YELLOW);
            snackbar.show();
        }
    }

}
