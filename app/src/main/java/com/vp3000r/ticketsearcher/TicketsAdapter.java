package com.vp3000r.ticketsearcher;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by P on 21.06.17.
 */
public class TicketsAdapter extends BaseAdapter {


    Context context;
    ArrayList<Trip> trips;
    LayoutInflater inflater;

    public TicketsAdapter(Context context, ArrayList<Trip> trips){
        this.context = context;
        this.trips = trips;
        this.inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return trips.size();
    }

    @Override
    public Object getItem(int position) {
        return trips.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view  = convertView;

        if (view == null) {
            view = this.inflater.inflate(R.layout.trip_item, parent, false);
        }

        Trip g = getTrip(position);

        ((TextView) view.findViewById(R.id.cityNameFrom)).setText(g.getCityNameFrom());
        ((TextView) view.findViewById(R.id.cityNameTo)).setText(g.getCityNameTo());


        return view;

    }

    Trip getTrip(int position) {
        return ((Trip) getItem(position));
    }



}
