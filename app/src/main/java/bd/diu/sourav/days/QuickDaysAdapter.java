package bd.diu.sourav.days;


import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import bd.diu.sourav.days.Realm.DaysModel;

public class QuickDaysAdapter extends BaseItemDraggableAdapter<DaysModel, BaseViewHolder> {
    private DateAndTimeConverter dateAndTimeConverter;

    public QuickDaysAdapter(List<DaysModel> data) {
        super(R.layout.list_layout, data);
        dateAndTimeConverter = new DateAndTimeConverter();
    }

    @Override
    protected void convert(BaseViewHolder helper, DaysModel item) {
        helper.setText(R.id.date, item.getDate());
        helper.setText(R.id.text, item.getBody());
        helper.setText(R.id.time, item.getTime());
    }

}
