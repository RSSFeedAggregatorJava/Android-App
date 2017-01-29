package eu.epitech.android.rssfeedaggregator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Build;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import io.swagger.client.ApiClient;
import io.swagger.client.ApiException;
import io.swagger.client.Configuration;
import io.swagger.client.api.FeedApi;
import io.swagger.client.auth.ApiKeyAuth;
import io.swagger.client.model.InlineResponse2001;

public class FeedListFragment extends Fragment {

    private GetFeedListTask mGetFeedListTask = null;
    private DeleteFeedTask mDeleteFeedTask = null;

    private SwipeRefreshLayout mSwipeLayout;
    private ListView mListView;
    private ListAdapter mListAdapter;
    private List<InlineResponse2001> mDataList;
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
        ((MainActivity)getActivity()).changeToolbarTitle(getString(R.string.feed_list_title));
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
                ((MainActivity)getActivity()).openArticleListFragment(mDataList.get(position).getId());
            }
        });
        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                if (mDeleteFeedTask == null) {
                    deleteFeed(mDataList.get(position).getId());
                    refreshView();
                }
                return true;
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

    private void deleteFeed(final int id) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());

        dialogBuilder.setTitle(getString(R.string.delete_feed_dialog_title));
        dialogBuilder.setMessage(getString(R.string.delete_feed_dialog_message));
        dialogBuilder.setPositiveButton(getString(R.string.feed_dialog_positive), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                if (!Utils.isConnectedToInternet(getActivity()))
                    Utils.createSnackBar((CoordinatorLayout) getActivity().findViewById(R.id.main_layout),
                            getString(R.string.error_internet_connection_snackbar_addfeed));
                else {
                    mDeleteFeedTask = new DeleteFeedTask();
                    mDeleteFeedTask.execute(id);
                }
            }
        });
        dialogBuilder.setNegativeButton(getString(R.string.feed_dialog_negative), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
            }
        });
        AlertDialog b = dialogBuilder.create();
        b.show();
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

    public class GetFeedListTask extends AsyncTask<Boolean, Void, List<InlineResponse2001>> {

        Boolean mIsStartingGet;

        @Override
        protected List<InlineResponse2001> doInBackground(Boolean... params) {
            mIsStartingGet = params[0];
            List<InlineResponse2001> result;

            try {
                ApiClient defaultClient = Configuration.getDefaultApiClient();

                ApiKeyAuth api_key = (ApiKeyAuth) defaultClient.getAuthentication("api_key");
                api_key.setApiKey(mApiKey);

                FeedApi apiInstance = new FeedApi();
                result = apiInstance.feedsGet();
                DatabaseManager.getInstance().setFeedList(result);
            } catch (ApiException e) {
                e.printStackTrace();
                result = DatabaseManager.getInstance().getFeedList();
                if (result.isEmpty())
                    return null;
            }
            return result;
        }

        @Override
        protected void onPostExecute(final List<InlineResponse2001> list) {
            if (mIsStartingGet) {
                mListAdapter = new ListAdapter(mContext, list);
                mListView.setAdapter(mListAdapter);
            } else {
                if (mListAdapter != null)
                    mListAdapter.setList(list);
                else {
                    mListAdapter = new ListAdapter(mContext, list);
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

    public class DeleteFeedTask extends AsyncTask<Integer, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Integer... params) {
            try {
                ApiClient defaultClient = Configuration.getDefaultApiClient();

                ApiKeyAuth api_key = (ApiKeyAuth) defaultClient.getAuthentication("api_key");
                api_key.setApiKey(mApiKey);

                FeedApi apiInstance = new FeedApi();
                apiInstance.feedsDelete(params[0]);
            } catch (ApiException e) {
                e.printStackTrace();
                return false;
            }

            return true;
        }

        @Override
        protected void onPostExecute(final Boolean res) {
            mDeleteFeedTask = null;
            refreshView();
        }

        @Override
        protected void onCancelled() {
            mDeleteFeedTask = null;
        }
    }
}
