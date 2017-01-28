package eu.epitech.android.rssfeedaggregator;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Toolbar mToolbar;
    LogoutTask mLogoutTask = null;
    AddFeedTask mAddFeedTask = null;
    String mApiKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        mApiKey = DatabaseManager.getInstance().getApiKey();

        /*getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);*/

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mAddFeedTask == null)
                    addFeed();
            }
        });
    }

    public void changeToolbarTitle(String title) {
        mToolbar.setTitle(title);
    }

    private void addFeed() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.add_feed_alert_dialog, null);
        dialogBuilder.setView(dialogView);

        final EditText edt = (EditText) dialogView.findViewById(R.id.add_feed);

        dialogBuilder.setTitle(getString(R.string.add_feed_dialog_title));
        dialogBuilder.setMessage(getString(R.string.add_feed_dialog_message));
        dialogBuilder.setPositiveButton(getString(R.string.feed_dialog_positive), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                if (!Utils.isConnectedToInternet(MainActivity.this))
                    Utils.createSnackBar((CoordinatorLayout) MainActivity.this.findViewById(R.id.main_layout),
                            getString(R.string.error_internet_connection_snackbar_addfeed));
                else {
                    mAddFeedTask = new AddFeedTask();
                    mAddFeedTask.execute(edt.getText().toString());
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.logout) {
            if (!Utils.isConnectedToInternet(this))
                Utils.createSnackBar((CoordinatorLayout) findViewById(R.id.main_layout),
                        getString(R.string.error_internet_connection_snackbar_logout));
            else {
                if (mLogoutTask == null) {
                    mLogoutTask = new LogoutTask();
                    mLogoutTask.execute((Void) null);
                }
            }
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    public class LogoutTask extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Void... params) {
            //TODO logout

            try {
                // Simulate network access.
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                return false;
            }

            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mLogoutTask = null;
            if (success) {
                DatabaseManager.getInstance().deleteDatabase();
                finish();
            }
        }

        @Override
        protected void onCancelled() {
            mLogoutTask = null;
        }
    }

    public class AddFeedTask extends AsyncTask<String, Void, Boolean> {

        @Override
        protected Boolean doInBackground(String... params) {
            //TODO add feed

            try {
                // Simulate network access.
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                return false;
            }

            return true;
        }

        @Override
        protected void onPostExecute(final Boolean res) {
            mAddFeedTask = null;
            if (res) {
                Utils.createSnackBar((CoordinatorLayout) MainActivity.this.findViewById(R.id.main_layout),
                        getString(R.string.feed_added));
                FeedListFragment frag = new FeedListFragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment, frag);
                transaction.commit();
            }
        }

        @Override
        protected void onCancelled() {
            mAddFeedTask = null;
        }
    }
}
