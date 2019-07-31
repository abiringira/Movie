package com.movie.film.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.ScrollView;

import com.movie.film.activities.R;
import com.movie.film.model.Results;


import java.util.ArrayList;

import in.srain.cube.views.GridViewWithHeaderAndFooter;

  public class MovieMaintabFragment extends Fragment implements MovieonRequiredDataLoaded {
    private ProgressBar progressBar;
    protected AbsListView mainFragmentLayout;
    private View view;
    private ScrollView scrollView;
    protected MovieProductAdapter adapter;
    public enum  TypeOfDataToAdapt {PRODUCTS};

     public  MovieMaintabFragment () {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.movie_fragment_products,container,false);
        scrollView = (ScrollView) view.findViewById(R.id.gridview_container);
        this.onDataLoadStart(view);
        return view;
    }

    @Override
    public void onDataLoadStart(View view) {
        if (view != null) {
            this.view = view;
            progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
            if (view.getId() == R.id.grid_container) {
               mainFragmentLayout = (GridViewWithHeaderAndFooter) view.findViewById(R.id.gridview);
            }
            else if(view.getId()==R.id.list_activities) {
                mainFragmentLayout = (ListView) view.findViewById(R.id.listView_activity);
            }
            if (progressBar != null)
                progressBar.setVisibility(View.VISIBLE);
            if (mainFragmentLayout != null)
                mainFragmentLayout.setVisibility(View.GONE);
        }

    }

    protected void resizeGridViewToEnableScrolling() {
        if (mainFragmentLayout != null) {
            mainFragmentLayout.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    scrollView.requestDisallowInterceptTouchEvent(true);
                    return false;
                }
            });
            setListViewHeightBasedOnChildren((GridViewWithHeaderAndFooter) mainFragmentLayout);
        }
    }
      @Override
      public void onCollectionDataLoadFinished(ArrayList<Results> pcData) {
          setUpLayoutFinishProgressBar();
      }

    @Override
    public void onDataLoadFinished() {
         setUpLayoutFinishProgressBar();
    }



      public static void setListViewHeightBasedOnChildren(GridView gridView) {
        ListAdapter gridAdapter = gridView.getAdapter();
        if (gridAdapter == null)
            return;
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(gridView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        int totalHeight = 0;
        int rowPadding = 20;
        View view = null;
        for (int i = 0; i < gridAdapter.getCount(); i++) {
            view = gridAdapter.getView(i, view, gridView);
            if (i == 0)
                view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, AbsListView.LayoutParams.WRAP_CONTENT));
                view.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
                totalHeight += view.getMeasuredHeight() + rowPadding;
        }
        ViewGroup.LayoutParams params = gridView.getLayoutParams();
        // NOTE: We need a better way of estimating the number of items per row and
        // then here we should only compute the height for the number of rows
        // right now, we're under the impression that we will only have one item per row and it's a gridview.
        // To error on the side of caution, we will assume that we will have two items per row
        // And then compute the height based on that.
        // TODO: Compute this dynamically with the right screen width.
        double rowItemCount = 2.0;
        int rows = (int)Math.round(gridAdapter.getCount() / rowItemCount);//ceil
        int count = gridAdapter.getCount();
        if (count > 0) {
            totalHeight = (int)Math.ceil((totalHeight * (rows+((count%2==0?2:1))))/ count);
        }
        //TODO: need to do more researching to handle this situation
        params.height = totalHeight;
        gridView.setLayoutParams(params);
    }

      private void setUpLayoutFinishProgressBar() {
          if (progressBar != null)
              progressBar.setVisibility(View.GONE);
          if (mainFragmentLayout != null)
              mainFragmentLayout.setVisibility(View.VISIBLE);
      }

     public void setUpMainView() {}

}
