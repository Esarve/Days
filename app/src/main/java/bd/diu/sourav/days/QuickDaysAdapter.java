package bd.diu.sourav.days;


import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class QuickDaysAdapter extends BaseItemDraggableAdapter <Days, BaseViewHolder> {
    public QuickDaysAdapter(List<Days> data) {
        super(R.layout.list_layout, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Days item) {
        helper.setText(R.id.date, item.getDate());
        helper.setText(R.id.text, item.getText());
        helper.setText(R.id.time,item.getTime());
    }

}