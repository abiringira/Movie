package com.movie.film.fragment;


import android.view.View;

import com.movie.film.activities.R;
import java.util.ArrayList;
import java.util.List;

public class MovieProductSurpportFragment extends MovieMaintabFragment {

    public MovieProductSurpportFragment () {

    }

    @Override
    public void onDataLoadStart(View view) {
        super.onDataLoadStart(view);
        this.onDataLoadFinished();
    }

    @Override
    public void onDataLoadFinished() {
        super.onDataLoadFinished();
        this.setUpMainView();
        resizeGridViewToEnableScrolling();
    }

    @Override
    public void setUpMainView() {
        List<String> productNames = new ArrayList<>();
        List<Integer> productIcon = new ArrayList<>();
        productNames.add("Plan your first resource");
        productIcon.add(R.drawable.main_icn);
        super.setUpMainView();
        if(getActivity() != null) {
            adapter = new MovieProductAdapter(getActivity(),productNames,productIcon);
           // adapter.set
            mainFragmentLayout.setAdapter(adapter);
        }
    }
}
