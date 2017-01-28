package eu.epitech.android.rssfeedaggregator;

import android.os.AsyncTask;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import io.swagger.client.model.InlineResponse2001;

public class FeedListFragment extends Fragment {

    private GetFeedListTask mGetFeedListTask;
    private SwipeRefreshLayout mSwipeLayout;
    private ListView mListView;
    private FeedListAdapter mListAdapter;
    private List<InlineResponse2001> mDataList;
    private String mApiKey;

    public FeedListFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feed_list, container, false);

        //TODO get the apikey from the database
        initiateSwipeLayout(view);
        initiateListView(view);

        return view;
    }

    private void initiateListView(View view) {
        mListView = (ListView) view.findViewById(R.id.feed_list);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //TODO open the next fragment passing mDataList.get(position)
            }
        });
        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                //TODO open an alert dialog to confirm you wanna remove this feed
                // if yes rm on the server and refresh the view
                return false;
            }
        });
        mGetFeedListTask = new GetFeedListTask();
        try {
            mDataList = mGetFeedListTask.execute((Void) null).get();
        } catch (Exception e) {
            mDataList = null;
        }
        mListAdapter = new FeedListAdapter(getActivity(), mDataList);
        mListView.setAdapter(mListAdapter);
    }

    private void initiateSwipeLayout(View view) {
        mSwipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.swiperefresh);
        mSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (!Utils.isConnectedToInternet(getActivity()))
                    Utils.createSnackBar((CoordinatorLayout) getActivity().findViewById(R.id.main_layout),
                            getString(R.string.error_internet_connection_snackbar_update));
                mGetFeedListTask = new GetFeedListTask();
                try {
                    mDataList = mGetFeedListTask.execute((Void) null).get();
                    mListAdapter.setList(mDataList);
                } catch (Exception e) {

                }
                mSwipeLayout.setRefreshing(false);
            }
        });
        mSwipeLayout.setColorSchemeResources(android.R.color.holo_green_dark, android.R.color.holo_red_dark,
                android.R.color.holo_blue_dark, android.R.color.holo_orange_dark);
    }

    public void refreshView() {
        mSwipeLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeLayout.setRefreshing(true);
            }
        });
    }

    public class GetFeedListTask extends AsyncTask<Void, Void, List<InlineResponse2001>> {

        @Override
        protected List<InlineResponse2001> doInBackground(Void... params) {
            //TODO get the list from the API
            //TODO set the list in the database
            //TODO if cannot get the list from the API, retrieve the list from the database
            try {
                // Simulate network access.
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                return null;
            }
            return null;
        }

        @Override
        protected void onPostExecute(final List<InlineResponse2001> list) {
            mGetFeedListTask = null;
        }

        @Override
        protected void onCancelled() {
            mGetFeedListTask = null;
        }
    }
}
