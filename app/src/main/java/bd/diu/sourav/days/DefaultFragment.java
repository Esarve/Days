package bd.diu.sourav.days;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class DefaultFragment extends Fragment {
    Sqlite sqlite;
    private List<Days> days = new ArrayList<>();
    RecyclerView recyclerView;
    DaysAdapter daysAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        sqlite = new Sqlite(getActivity());
        View view = inflater.inflate(R.layout.fragment_default,container,false);
        recyclerView = view.findViewById(R.id.recycler_view);
        daysAdapter = new DaysAdapter(days);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(daysAdapter);
        daysAdapter.update(sqlite.getData());
        daysAdapter.notifyDataSetChanged();
        return view;

    }
}
