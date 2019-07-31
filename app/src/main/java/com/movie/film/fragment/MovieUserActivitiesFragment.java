package com.movie.film.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.movie.film.activities.MovieAsyncListenerManyItem;
import com.movie.film.activities.MovieMainActivity;
import com.movie.film.activities.R;
import com.movie.film.business.MovieClient;
import com.movie.film.business.service.MovieControllerFactory;
import com.movie.film.business.service.MovieUserActivitiesController;
import com.movie.film.model.Results;
import com.movie.film.model.MovieActivityRequest;
import com.movie.film.model.MovieData;
import org.springframework.util.StringUtils;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public  class MovieUserActivitiesFragment extends MovieMaintabFragment implements MovieAsyncListenerManyItem {
    private static final String CLAZZ = MovieUserActivitiesFragment.class.getName();
    private ExpenseAdapter adapter;
    private View rootView = null;
    private TextView emptyView;
    private final String clazz = MovieUserActivitiesFragment.class.getName();
    protected ProgressBar progressBar;
    private MovieClient.MovieServiceType serviceType;
    private MovieUserActivitiesFragment userActivitiesFragment;


    public MovieUserActivitiesFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.movie_fragment_expense_activities, container, false);
        userActivitiesFragment = this;
        this.onDataLoadStart(rootView);
        return rootView;
    }

    @Override
    public void onActivityCreated( Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @SuppressLint("NewApi")
    private void makeRequest() {
        final MovieActivityRequest activityRequest = new MovieActivityRequest();
        final String userData = ((MovieMainActivity) this.getActivity()).getLoggedInUser();
        Results Movie = new Results();
        //film.setEmployee(userData);
       // film.setContent("expense");
        // activityRequest.setMovie(Movie);
        MovieUserActivitiesController userActivitiesController = (MovieUserActivitiesController) MovieControllerFactory.createControllerManyItem(MovieControllerFactory.MovieControllerType.USER_ACTIVITIES, this);
        //userActivitiesController.setActivity((MovieMainActivity) this.getActivity());
        userActivitiesController.setServiceType(MovieClient.MovieServiceType.USER_ACTIVITIES);
        userActivitiesController.setRequest(activityRequest);
        userActivitiesController.execute(activityRequest);
    }

    public void setEmptyView() {
        if (progressBar != null) {
            progressBar.setVisibility(View.GONE);
        }
        emptyView = new TextView(getContext());
        emptyView.setLayoutParams(
                new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT));


        emptyView.setText("You have no transaction history. Make your first transaction Today!");
        emptyView.setGravity(Gravity.CENTER_HORIZONTAL);
        emptyView.setPadding(20, 60, 20, 20);
        emptyView.setTextSize(18);
        emptyView.setVisibility(View.GONE);
        emptyView.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        //section
        emptyView.setTextColor(getResources().getColor(R.color.colorPrimary));

        ((ViewGroup) mainFragmentLayout.getParent()).addView(emptyView);
        mainFragmentLayout.setEmptyView(emptyView);
    }

    public void setAdapter(ArrayList<Results> data) {

        if (data != null) {
            //Log.d("COUNT", "" + data.size());
            //List<Movie> ary = Arrays.asList(data);

            Log.d(clazz, (data.get(0) != null ? data.get(0).getVote_average() : "") + "");
            initializeAdapter(data);
        }
    }

    private void initializeAdapter(List<Results> ary) {
        adapter = new ExpenseAdapter(ary, (MovieMainActivity) this.getActivity());
        mainFragmentLayout.setAdapter(adapter);
        if (emptyView != null && ary != null && ary.size() > 0) {
            emptyView.setVisibility(View.GONE);
        }
    }

    public void refleshAdapter(Results billPaymentData) {
        if (adapter == null) {
            initializeAdapter(new ArrayList<Results>());
        }
        if (billPaymentData != null) {
            adapter.updateAdapter(billPaymentData);
        }
    }

    @Override
    public void onDataLoadStart(View view) {
        super.onDataLoadStart(view);
        makeRequest();
    }

    @Override
    public void onCollectionDataLoadFinished(ArrayList<Results> data) {
        super.onCollectionDataLoadFinished(data);
        this.setAdapter(data);
    }




    public class ExpenseAdapter extends BaseAdapter {
        private List<Results> activities = new ArrayList<>();
        private MovieMainActivity context = new MovieMainActivity();
        private LayoutInflater inflater = null;

        public ExpenseAdapter(List<Results> activities, MovieMainActivity context) {
            super();
            this.activities = activities;
            this.context = context;
            this.inflater = (LayoutInflater) context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        public void updateAdapter(Results newTrans) {
            activities.add(0, newTrans);
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return activities.size();
        }

        @Override
        public Object getItem(int position) {
            return activities.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {



            try {





                ListItemHolder listItemHolder;

                if (convertView == null) {
                    //inflate the layout
                    convertView = inflater.inflate(R.layout.movie_activity_list_item, parent, false);

                    //setup the viewholder
                    listItemHolder = new ListItemHolder();
                    listItemHolder.employee_name = (TextView) convertView.findViewById(R.id.employee_name);
                    listItemHolder.expense_type = (TextView) convertView.findViewById(R.id.expense_type);
                    listItemHolder.expense_amount = (ImageView) convertView.findViewById(R.id.expense_amount);
                    listItemHolder.expense_date = (TextView) convertView.findViewById(R.id.expense_date);
                    listItemHolder.expense_status = (TextView) convertView.findViewById(R.id.expense_status);
                    listItemHolder.itemView = convertView;
                    //cache the view holder
                    convertView.setTag(listItemHolder);
                } else {
                    listItemHolder = (ListItemHolder) convertView.getTag();
                }

                final Results activity = (Results) getItem(position);
                if (activity != null && context != null) {

                   // ObjectMapper mapper = new ObjectMapper();
                    //Movie node = mapper.readValue(activity.getMovie().toString(),MovieResult.class);
                    String serviceType = String.valueOf(activity.getId());
                    if (!StringUtils.isEmpty(serviceType)) {
                        listItemHolder.expense_status.setTextColor(Color.parseColor("#35535A"));
                            Log.e("Fetching", activity.getAdult());
                            listItemHolder.expense_status.setText(activity.getVote_average());
                            listItemHolder.employee_name.setText(activity.getTitle());
                            Log.e("Fetching",activity.toString());
                          listItemHolder.expense_date.setText(activity.getRelease_date());

                        //Bitmap bitmap = (Bitmap) BitmapFactory.decodeStream( activity.getPoster_path() );
                        //Bitmap bitmap = BitmapFactory.decodeFile(activity.getPoster_path());
                          listItemHolder.expense_amount.setImageURI(Uri.parse("https://api.themoviedb.org/3/movie/"+activity.getId()+"/images?api_key=45d139dbb35cd83f615ff5c35a7c65b7&language=en-US"));

                    }
                }
            } catch (Exception e) {
                String errorMessage = "Error while listing user transactions";
                //(e, errorMessage);
            }

            return convertView;
        }

        public  Drawable loadImageFromWebOperations(String url) {
            try {
                InputStream is = (InputStream) new URL(url).getContent();
                Drawable d = Drawable.createFromStream(is, "src name");
                return d;
            } catch (Exception e) {
                return null;
            }
        }



    }






    //view holder to cache our listitem layout
    static class ListItemHolder {
        View itemView;
        TextView employee_name;
        TextView expense_type;
        ImageView expense_amount;
        TextView expense_date;
        TextView expense_status;


    }


    @Override
    public void onTaskStarted() {

    }



    @Override
    public void onTaskCompleted(ArrayList<Results> data) {
        try {
             //userActivitiesFragment = (MovieUserActivitiesFragment) adapter.getItem(1);
            Log.e("OnTaskCompleted","before Data");
            if (data != null) {
                Log.e("OnTaskCompleted","middle Data");
                this.onCollectionDataLoadFinished(data);

            }
            //else if (data != null && data.size() == 0) {
           //     this.setEmptyView();
           // }
        } catch (Throwable error) {
            if (error.getMessage() != null && error.getMessage().length() > 0) {
                Log.d(CLAZZ, error.getMessage());
            }
        }

    }

    @Override
    public void onTaskCompleted(Object data) {

    }

    @Override
    public void onTaskCompleted(MovieData data) {

    }


}
