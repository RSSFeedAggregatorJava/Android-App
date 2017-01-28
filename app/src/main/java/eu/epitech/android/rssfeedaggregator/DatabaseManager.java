package eu.epitech.android.rssfeedaggregator;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseManager {

    public static DatabaseManager instance = null;

    private SQLiteDatabase mDatabase;
    private final String DATABASE_NAME = "RSSFeedAggregator.db";
    private final String DATABASE_PATH = "/data/data/eu.epitech.android.rssfeedaggregator/";
    private final String CREATE_ACCOUNT_TABLE = "CREATE TABLE IF NOT EXISTS account(id INTEGER PRIMARY KEY AUTOINCREMENT, api_key VARCHAR, account_id INTEGER);";
    private final String ACCOUNT_TABLE_NAME = "account";
    private final String API_KEY_FIELD = "api_key";
    private final String ACCOUNT_ID_FIELD = "account_id";

    private final String GET_API_KEY = "SELECT api_key FROM account LIMIT 1";

    public static DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }

    DatabaseManager() {
        mDatabase = SQLiteDatabase.openOrCreateDatabase(DATABASE_PATH + DATABASE_NAME, null);
        mDatabase.execSQL(CREATE_ACCOUNT_TABLE);
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

    public void deleteDatabase() {
        mDatabase.delete(ACCOUNT_TABLE_NAME, null, null);
    }
}
