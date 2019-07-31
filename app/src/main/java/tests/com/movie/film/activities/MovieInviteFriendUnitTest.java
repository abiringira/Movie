package tests.com.movie.film.activities;


import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.SmallTest;

import com.movie.film.activities.MovieBaseActivity;

/**
 * Created by emmy on 11/13/17.
 */

   public class MovieInviteFriendUnitTest extends ActivityInstrumentationTestCase2<MovieBaseActivity> {

    MovieBaseActivity activity;

    private static final String CLAZZ = MovieBaseActivity.class.getName();


    public MovieInviteFriendUnitTest() {
        super(CLAZZ,MovieBaseActivity.class);
    }

    @SmallTest
    protected void setUp () throws Exception {
        super.setUp();
        setActivityInitialTouchMode(false);
        activity = getActivity();
    }

    @SmallTest
    public void testInviteFriendsUsingFirebase() {

              try {
                  activity.inviteFriendUsingFirebase();
              } catch (Exception error) {
                 assertEquals(error.getMessage(),"Sending Invitation Failed");
              }

           }


}
