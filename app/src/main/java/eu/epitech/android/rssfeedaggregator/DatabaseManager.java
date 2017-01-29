package eu.epitech.android.rssfeedaggregator;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import io.swagger.client.model.Feed;
import io.swagger.client.model.InlineResponse2001;

public class DatabaseManager {

    private static DatabaseManager instance = null;

    private SQLiteDatabase mDatabase;
    private final String DATABASE_NAME = "RSSFeedAggregator.db";
    private final String DATABASE_PATH = "/data/data/eu.epitech.android.rssfeedaggregator/";
    private final String CREATE_ACCOUNT_TABLE = "CREATE TABLE IF NOT EXISTS account(id INTEGER PRIMARY KEY AUTOINCREMENT, api_key VARCHAR, account_id INTEGER);";
    private final String ACCOUNT_TABLE_NAME = "account";
    private final String API_KEY_FIELD = "api_key";
    private final String ACCOUNT_ID_FIELD = "account_id";
    private final String CREATE_FEED_LIST_TABLE = "CREATE TABLE IF NOT EXISTS feed_list(id INTEGER PRIMARY KEY AUTOINCREMENT, feed_title VARCHAR, feed_id INTEGER);";
    private final String FEED_LIST_TABLE_NAME = "feed_list";
    private final String FEED_TITLE_FIELD = "feed_title";
    private final String FEED_ID_FIELD = "feed_id";
    private final String CREATE_FEED_TABLE = "CREATE TABLE IF NOT EXISTS feed(id INTEGER PRIMARY KEY AUTOINCREMENT, feed_title VARCHAR, feed_desc VARCHAR, pub_date VARCHAR, web_link VARCHAR, rss_url VARCHAR, feed_id INTEGER);";
    private final String FEED_TABLE_NAME = "feed";
    private final String FEED_DESC_FIELD = "feed_desc";
    private final String PUB_DATE_FIELD = "pub_date";
    private final String WEB_LINK_FIELD = "web_link";
    private final String RSS_URL_FIELD = "rss_url";

    private final String GET_API_KEY = "SELECT api_key FROM account LIMIT 1";
    private final String GET_FEED_LIST = "SELECT * FROM feed_list";
    private final String GET_FEED = "SELECT * FROM feed WHERE feed_id = ";

    public static DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }

    DatabaseManager() {
        mDatabase = SQLiteDatabase.openOrCreateDatabase(DATABASE_PATH + DATABASE_NAME, null);
        mDatabase.execSQL(CREATE_ACCOUNT_TABLE);
        mDatabase.execSQL(CREATE_FEED_LIST_TABLE);
        mDatabase.execSQL(CREATE_FEED_TABLE);
    }

    public String getApiKey() {
        Cursor cur = mDatabase.rawQuery(GET_API_KEY, null);
        cur.moveToFirst();
        if (cur.getCount() == 0)
            return null;
        return cur.getString(cur.getColumnIndex(API_KEY_FIELD));
    }

    public void setApiKey(String apiKey, int account_id) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(API_KEY_FIELD, apiKey);
        contentValues.put(ACCOUNT_ID_FIELD, account_id);
        mDatabase.insertOrThrow(ACCOUNT_TABLE_NAME, null, contentValues);
    }

    public void setFeedList(List<InlineResponse2001> list) {
        for (InlineResponse2001 res : list) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(FEED_TITLE_FIELD, res.getTitle());
            contentValues.put(FEED_ID_FIELD, res.getId());
            mDatabase.insertOrThrow(FEED_LIST_TABLE_NAME, null, contentValues);
        }
    }

    public List<InlineResponse2001> getFeedList() {
        ArrayList<InlineResponse2001> mList = new ArrayList<>();
        Cursor cur = mDatabase.rawQuery(GET_FEED_LIST, null);
        cur.moveToFirst();
        while (cur.moveToNext()) {
            InlineResponse2001 res = new InlineResponse2001();
            res.setTitle(cur.getString(cur.getColumnIndex(FEED_TITLE_FIELD)));
            res.setId(cur.getInt(cur.getColumnIndex(FEED_ID_FIELD)));
            mList.add(res);
        }
        return mList;
    }

    public void setFeed(Feed feed) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(FEED_TITLE_FIELD, feed.getTitle());
        contentValues.put(FEED_DESC_FIELD, feed.getDescription());
        contentValues.put(PUB_DATE_FIELD, feed.getPubDate());
        contentValues.put(WEB_LINK_FIELD, feed.getLink());
        contentValues.put(RSS_URL_FIELD, feed.getUrl());
        contentValues.put(FEED_ID_FIELD, feed.getId());
        mDatabase.insertOrThrow(FEED_TABLE_NAME, null, contentValues);
    }

    public Feed getFeed(int id) {
        Cursor cur = mDatabase.rawQuery(GET_FEED + id + ";", null);
        cur.moveToFirst();
        Feed feed = new Feed();
        feed.setTitle(cur.getString(cur.getColumnIndex(FEED_TITLE_FIELD)));
        feed.setDescription(cur.getString(cur.getColumnIndex(FEED_DESC_FIELD)));
        feed.setPubDate(cur.getString(cur.getColumnIndex(PUB_DATE_FIELD)));
        feed.setLink(cur.getString(cur.getColumnIndex(WEB_LINK_FIELD)));
        feed.setUrl(cur.getString(cur.getColumnIndex(RSS_URL_FIELD)));
        feed.setId(cur.getInt(cur.getColumnIndex(FEED_ID_FIELD)));
        return feed;
    }

    public void deleteDatabase() {
        mDatabase.delete(ACCOUNT_TABLE_NAME, null, null);
        mDatabase.delete(FEED_LIST_TABLE_NAME, null, null);
        mDatabase.delete(FEED_TABLE_NAME, null, null);
    }
}
