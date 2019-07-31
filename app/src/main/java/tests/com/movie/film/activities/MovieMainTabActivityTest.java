package tests.com.movie.film.activities;

import android.test.AndroidTestCase;
import android.test.suitebuilder.annotation.SmallTest;

import com.movie.film.activities.MovieMainActivity;


/**
 * Created by emmy on 10/4/17.
 */

public class MovieMainTabActivityTest extends AndroidTestCase {
    MovieMainActivity activity = new MovieMainActivity();

    /**
     * Test show transaction receiptcorrect positive case.
     */
    @SmallTest
      public void testShowMovieDetails() {


        try {
            activity.showMovieDetails();
        } catch (Exception error) {
            assertEquals(error.getMessage(),"Movie Details Missing");
        }

    }

     }

