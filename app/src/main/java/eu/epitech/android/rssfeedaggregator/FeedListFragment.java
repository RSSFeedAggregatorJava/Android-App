package eu.epitech.android.rssfeedaggregator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
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

import io.swagger.client.model.Feed;

public class FeedListFragment extends Fragment {

    private GetFeedListTask mGetFeedListTask = null;

    private SwipeRefreshLayout mSwipeLayout;
    private ListView mListView;
    private FeedListAdapter mListAdapter;
    private List<Feed> mDataList;
    private String mApiKey;
    private View mProgressView;
    private Context mContext;

    public FeedListFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feed_list, container, false);

        mContext = getActivity();
        mProgressView = view.findViewById(R.id.feed_list_progress);
        mApiKey = DatabaseManager.getInstance().getApiKey();
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
                refreshView();
                return false;
            }
        });
        showProgress(true);
        mGetFeedListTask = new GetFeedListTask();
        mGetFeedListTask.execute(true);
    }

    private void initiateSwipeLayout(View view) {
        mSwipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.swiperefresh);
        mSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (!Utils.isConnectedToInternet(mContext))
                    Utils.createSnackBar((CoordinatorLayout) getActivity().findViewById(R.id.main_layout),
                            getString(R.string.error_internet_connection_snackbar_update));
                else {
                    if (mGetFeedListTask == null) {
                        mGetFeedListTask = new GetFeedListTask();
                        mGetFeedListTask.execute(false);
                    }
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

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mSwipeLayout.setVisibility(show ? View.GONE : View.VISIBLE);
            mSwipeLayout.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mSwipeLayout.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mSwipeLayout.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    public class GetFeedListTask extends AsyncTask<Boolean, Void, List<Feed>> {

        Boolean mIsStartingGet;

        @Override
        protected List<Feed> doInBackground(Boolean... params) {
            mIsStartingGet = params[0];

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
        protected void onPostExecute(final List<Feed> list) {
            if (mIsStartingGet) {
                mListAdapter = new FeedListAdapter(mContext, list);
                mListView.setAdapter(mListAdapter);
            } else {
                if (mListAdapter != null)
                    mListAdapter.setList(list);
                else {
                    mListAdapter = new FeedListAdapter(mContext, list);
                    mListView.setAdapter(mListAdapter);
                }
            }
            showProgress(false);
            mSwipeLayout.setRefreshing(false);
            mGetFeedListTask = null;
        }

        @Override
        protected void onCancelled() {
            showProgress(false);
            mSwipeLayout.setRefreshing(false);
            mGetFeedListTask = null;
        }
    }
}
