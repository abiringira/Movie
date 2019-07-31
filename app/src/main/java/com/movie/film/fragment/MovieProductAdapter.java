package com.movie.film.fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.movie.film.activities.R;

import java.util.List;

public class MovieProductAdapter extends BaseAdapter {
    private Context context;
    private List<String> productNames;
    private List<Integer> productImages;
    private LayoutInflater inflater = null;

    public MovieProductAdapter(Context cxt, List<String> productNames, List<Integer> productImages) {
        this.productNames = productNames;
        this.productImages = productImages;
        this.context = cxt;
        if (context != null) {
            inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        }

    }

    @Override
    public int getCount() {
        return productNames.size();
    }

    @Override
    public Object getItem(int position) {
        return position;

    }

    @Override
    public long getItemId(int position) {
        return position;

    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        if(view == null) {

            view = inflater.inflate(R.layout.movie_fragment_products_item,null);

        }
        TextView viewText = view.findViewById(R.id.textView1);
        ImageView image = view.findViewById(R.id.imageView1);
        viewText.setText(productNames.get(position));
        image.setImageResource(productImages.get(position));
        return view;
    }
}
