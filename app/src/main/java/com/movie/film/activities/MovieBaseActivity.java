package com.movie.film.activities;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.gms.appinvite.AppInviteInvitation;
import com.movie.film.business.MovieClient;
import com.movie.film.business.MovieUserError;
import com.movie.film.model.Results;
import com.movie.film.model.MovieData;
import com.movie.film.model.MoviePingResult;

import java.util.ArrayList;


public class MovieBaseActivity extends AppCompatActivity implements MovieUserError, MovieAsyncListenerManyItem {
    private static final String TAG = MovieBaseActivity.class.getName();
    private android.support.v7.widget.Toolbar toolbar;
    protected MovieClient.MovieServiceType serviceType;
    protected ProgressDialog progressDialog;
    protected MoviePingResult pingResult = new MoviePingResult();
    private static final int REQUEST_INVITE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }


    public void straightLogout() {
        Intent intent = new Intent(this, MovieMainActivity.class);
        startActivity(intent);
        finish();
    }


    public void showMessage(String msg, String title) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(msg)
                .setTitle(title);
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Log.d(TAG, "Clicked on OK on Alert box");
                onBackPressed();

            }
        });
        Dialog alert = builder.create();
        alert.show();
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager connect = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connect.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            case R.id.menu_invite_friends:
                inviteFriend();
                return true;


        }

        return super.onOptionsItemSelected(item);
    }


    public void inviteFriendUsingFirebase() {
        Intent sendIntent = new AppInviteInvitation.IntentBuilder(getResources().getString(R.string.invite_title))
                .setMessage(getResources().getString(R.string.invite_message))
                .setCustomImage(Uri.parse(getResources().getString(R.string.google_image_url)))
                .setCallToActionText("Install!")
                .build();
        startActivityForResult(sendIntent, REQUEST_INVITE);
    }

    private void inviteFriend() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, getResources().getString(R.string.invite_message));
        sendIntent.setType("text/plain");
        Intent chooser = Intent.createChooser(sendIntent, getResources().getString(R.string.invite_title));
        if (sendIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(chooser);
        }
    }

    @Override
    public void presentError(MovieUserError error) {

    }

    @Override
    public void onTaskStarted() {

    }


    @Override
    public void onTaskCompleted(Object data) {

    }


    @Override
    public void onTaskCompleted(ArrayList<Results> data) {

    }



    @Override
    public void onTaskCompleted(MovieData data) {

    }



  /*  @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_INVITE) {
            if (resultCode == RESULT_OK) {
                // Get the invitation IDs of all sent messages
                String[] ids = AppInviteInvitation.getInvitationIds(resultCode, data);
                for (String id : ids) {
                    Log.d(TAG, "onActivityResult: sent invitation " + id);
                }
            } else {

                showMessage("Sending Invitation Failed","Invite");

            }
        }
    }*/
}
