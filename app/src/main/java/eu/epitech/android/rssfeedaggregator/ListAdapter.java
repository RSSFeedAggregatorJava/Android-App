package eu.epitech.android.rssfeedaggregator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import io.swagger.client.model.InlineResponse2001;

public class ListAdapter extends BaseAdapter {

    private Context mContext;
    private List<InlineResponse2001> mList;

    ListAdapter(Context context, List<InlineResponse2001> result) {
        mContext = context;
        mList = result;
    }

    public void setList(List<InlineResponse2001> list) {
        mList = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (mList == null)
            return 0;
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        if (mList == null)
            return null;
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (mList == null)
            return null;
        if (convertView == null)
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false);
        TextView tv = (TextView) convertView.findViewById(R.id.title);
        tv.setText(mList.get(position).getTitle());
        return convertView;
    }
}
