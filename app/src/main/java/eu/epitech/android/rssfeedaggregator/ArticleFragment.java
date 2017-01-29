package eu.epitech.android.rssfeedaggregator;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import io.swagger.client.ApiClient;
import io.swagger.client.ApiException;
import io.swagger.client.Configuration;
import io.swagger.client.api.ArticleApi;
import io.swagger.client.auth.ApiKeyAuth;
import io.swagger.client.model.Article;

public class ArticleFragment extends Fragment {

    static public final String ARG_FEED_ID = "feedid";
    static public final String ARG_ARTICLE_ID = "articleid";

    private GetArticleInfo mGetArticleInfo;

    private int mFeedId;
    private int mArticleId;
    private String mApiKey;
    private TextView mDesc;
    private TextView mAuthor;
    private TextView mPubDate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_article, container, false);

        mFeedId = getArguments().getInt(ARG_FEED_ID);
        mArticleId = getArguments().getInt(ARG_ARTICLE_ID);
        mApiKey = DatabaseManager.getInstance().getApiKey();
        mDesc = (TextView)view.findViewById(R.id.desc);
        mAuthor = (TextView)view.findViewById(R.id.author);
        mPubDate = (TextView)view.findViewById(R.id.pub_date);
        mGetArticleInfo = new GetArticleInfo();
        mGetArticleInfo.execute((Void)null);
        return view;
    }

    public int getFeedId() {
        return mFeedId;
    }

    public class GetArticleInfo extends AsyncTask<Void, Void, Article> {

        @Override
        protected Article doInBackground(Void... params) {
            Article result;

            try {
                ApiClient defaultClient = Configuration.getDefaultApiClient();

                ApiKeyAuth api_key = (ApiKeyAuth) defaultClient.getAuthentication("api_key");
                api_key.setApiKey(mApiKey);

                ArticleApi apiInstance = new ArticleApi();
                result = apiInstance.articlesFeedIdArticleIdGet(String.valueOf(mFeedId), String.valueOf(mArticleId));
                DatabaseManager.getInstance().setArticle(result, mFeedId);
            } catch (ApiException e) {
                e.printStackTrace();
                result = DatabaseManager.getInstance().getArticle(mFeedId, mArticleId);
            }
            return result;
        }

        @Override
        protected void onPostExecute(final Article res) {
            if (res != null) {
                ((MainActivity) getActivity()).changeToolbarTitle(res.getTitle());
                mDesc.setText(res.getDescription());
                mAuthor.setText(res.getAuthor());
                mPubDate.setText(res.getPubdate().toString());
            }
            mGetArticleInfo = null;
        }

        @Override
        protected void onCancelled() {
            mGetArticleInfo = null;
        }
    }
}
