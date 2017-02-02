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
import android.widget.TextView;

import java.util.List;

import io.swagger.client.ApiClient;
import io.swagger.client.ApiException;
import io.swagger.client.Configuration;
import io.swagger.client.api.ArticleApi;
import io.swagger.client.api.FeedApi;
import io.swagger.client.auth.ApiKeyAuth;
import io.swagger.client.model.Feed;
import io.swagger.client.model.InlineResponse2002;

public class ArticleListFragment extends Fragment {

    static public final String ARG_FEED_ID = "id";

    private GetFeedInfoTask mGetFeedInfoTask = null;
    private GetArticleListTask mGetArticleListTask = null;

    private SwipeRefreshLayout mSwipeLayout;
    private String mApiKey;
    private View mProgressView;
    private Context mContext;
    private ListView mListView;
    private List<InlineResponse2002> mDataList;
    private ArticleListAdapter mListAdapter = null;
    private View mInfoLayout;
    private TextView mFeedDesc;
    private TextView mPubDate;
    private TextView mWebLink;
    private TextView mRssUrl;
    private int mFeedId;

    public ArticleListFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_article_list, container, false);

        mFeedId = getArguments().getInt(ARG_FEED_ID);
        mApiKey = DatabaseManager.getInstance().getApiKey();
        mProgressView = view.findViewById(R.id.article_list_progress);
        mContext = getActivity();
        mInfoLayout = view.findViewById(R.id.info_layout);
        mFeedDesc = (TextView)view.findViewById(R.id.feed_desc);
        mPubDate = (TextView)view.findViewById(R.id.pub_date);
        mWebLink = (TextView)view.findViewById(R.id.web_link);
        mRssUrl = (TextView)view.findViewById(R.id.rss_url);
        initiateSwipeLayout(view);
        initiateListView(view);

        return view;
    }

    private void initiateListView(View view) {
        mListView = (ListView) view.findViewById(R.id.feed_list);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ((MainActivity)getActivity()).openArticleFragment(mFeedId, mDataList.get(position).getId());
            }
        });
        showProgress(true);
        mGetFeedInfoTask = new GetFeedInfoTask();
        mGetFeedInfoTask.execute((Void)null);
        mGetArticleListTask = new GetArticleListTask();
        mGetArticleListTask.execute(true);
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
                    if (mGetFeedInfoTask == null) {
                        mGetFeedInfoTask = new GetFeedInfoTask();
                        mGetFeedInfoTask.execute((Void)null);
                    }
                    if (mGetArticleListTask == null) {
                        mGetArticleListTask = new GetArticleListTask();
                        mGetArticleListTask.execute(false);
                    }
                }
                mSwipeLayout.setRefreshing(false);
            }
        });
        mSwipeLayout.setColorSchemeResources(android.R.color.holo_green_dark, android.R.color.holo_red_dark,
                android.R.color.holo_blue_dark, android.R.color.holo_orange_dark);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mInfoLayout.setVisibility(show ? View.GONE : View.VISIBLE);
            mInfoLayout.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mInfoLayout.setVisibility(show ? View.GONE : View.VISIBLE);
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
            mInfoLayout.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    public class GetFeedInfoTask extends AsyncTask<Void, Void, Feed> {

        @Override
        protected Feed doInBackground(Void... params) {
            Feed result;

            try {
                ApiClient defaultClient = Configuration.getDefaultApiClient();

                ApiKeyAuth api_key = (ApiKeyAuth) defaultClient.getAuthentication("api_key");
                api_key.setApiKey(mApiKey);

                FeedApi apiInstance = new FeedApi();
                result = apiInstance.feedsFeedIdGet((long)mFeedId);
                DatabaseManager.getInstance().setFeed(result);
            } catch (ApiException e) {
                e.printStackTrace();
                result = DatabaseManager.getInstance().getFeed(mFeedId);
            }
            return result;
        }

        @Override
        protected void onPostExecute(final Feed res) {
            if (res != null) {
                ((MainActivity) getActivity()).changeToolbarTitle(res.getTitle());
                mFeedDesc.setText(res.getDescription());
                mPubDate.setText(res.getPubDate());
                mWebLink.setText(res.getLink());
                mRssUrl.setText(res.getUrl());
            }
            mGetFeedInfoTask = null;
        }

        @Override
        protected void onCancelled() {
            mGetFeedInfoTask = null;
        }
    }

    public class GetArticleListTask extends AsyncTask<Boolean, Void, List<InlineResponse2002>> {

        Boolean mIsStartingGet;

        @Override
        protected List<InlineResponse2002> doInBackground(Boolean... params) {
            mIsStartingGet = params[0];
            List<InlineResponse2002> result;

            try {
                ApiClient defaultClient = Configuration.getDefaultApiClient();

                ApiKeyAuth api_key = (ApiKeyAuth) defaultClient.getAuthentication("api_key");
                api_key.setApiKey(mApiKey);

                ArticleApi apiInstance = new ArticleApi();
                result = apiInstance.articlesFeedIdGet(String.valueOf(mFeedId));
                DatabaseManager.getInstance().setArticleList(result, mFeedId);
            } catch (ApiException e) {
                e.printStackTrace();
                result = DatabaseManager.getInstance().getArticleList(mFeedId);
                if (result != null && result.isEmpty())
                    return null;
            }
            return result;
        }

        @Override
        protected void onPostExecute(final List<InlineResponse2002> res) {
            mDataList = res;
            if (mIsStartingGet) {
                mListAdapter = new ArticleListAdapter(mContext, res);
                mListView.setAdapter(mListAdapter);
            } else {
                if (mListAdapter != null)
                    mListAdapter.setList(res);
                else {
                    mListAdapter = new ArticleListAdapter(mContext, res);
                    mListView.setAdapter(mListAdapter);
                }
            }
            showProgress(false);
            mSwipeLayout.setRefreshing(false);
            mGetArticleListTask = null;
        }

        @Override
        protected void onCancelled() {
            mGetArticleListTask = null;
        }
    }
}
