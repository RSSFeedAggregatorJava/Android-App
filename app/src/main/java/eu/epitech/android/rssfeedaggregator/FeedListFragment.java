package eu.epitech.android.rssfeedaggregator;

import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class FeedListFragment extends Fragment {

    private SwipeRefreshLayout mSwipeLayout;
    private ListView mListView;

    public FeedListFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_feed_list, container, false);

        mListView = (ListView) view.findViewById(R.id.feed_list);
        mSwipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.swiperefresh);
        mSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (!Utils.isConnectedToInternet(getActivity()))
                    Utils.createSnackBar((CoordinatorLayout) getActivity().findViewById(R.id.main_layout),
                            getString(R.string.error_internet_connection_snackbar_update));
                //TODO else get list of feed in the server
                mSwipeLayout.setRefreshing(false);
            }
        });
        mSwipeLayout.setColorSchemeResources(android.R.color.holo_green_dark, android.R.color.holo_red_dark,
                android.R.color.holo_blue_dark, android.R.color.holo_orange_dark);
        return view;
    }
}
