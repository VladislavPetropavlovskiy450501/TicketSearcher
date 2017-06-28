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

        ((TextView) view.findViewById(R.id.cf)).setText(g.getCityNameFrom());
        ((TextView) view.findViewById(R.id.ct)).setText(g.getCityNameTo());
        ((TextView) view.findViewById(R.id.df)).setText(g.getDepDate());
        ((TextView) view.findViewById(R.id.dt)).setText(g.getArrDate());
        ((TextView) view.findViewById(R.id.tf)).setText(g.getDepTime());
        ((TextView) view.findViewById(R.id.tt)).setText(g.getArrTime());
        ((TextView) view.findViewById(R.id.ccf)).setText(g.getIataFrom());
        ((TextView) view.findViewById(R.id.cct)).setText(g.getIataTo());
        ((TextView) view.findViewById(R.id.acf)).setText(g.getAirportNameFrom());
        ((TextView) view.findViewById(R.id.act)).setText(g.getAirportNameTo());
        ((TextView) view.findViewById(R.id.dur)).setText(g.getDurationStr());
        ((TextView) view.findViewById(R.id.ac1)).setText(g.getAirlineCode());
        ((TextView) view.findViewById(R.id.ac2)).setText(g.getAirlineCode2());
        ((TextView) view.findViewById(R.id.acm1)).setText(g.getAirlineName());
        ((TextView) view.findViewById(R.id.acm2)).setText(g.getAirlineName2());
        ((TextView) view.findViewById(R.id.fn1)).setText(g.getFlightNumber());
        ((TextView) view.findViewById(R.id.fn2)).setText(g.getFlightNumber2());
        ((TextView) view.findViewById(R.id.acr1)).setText(g.getAircraft());
        ((TextView) view.findViewById(R.id.acr2)).setText(g.getAircraft2());
        ((TextView) view.findViewById(R.id.pr)).setText(g.getPrice());
        ((TextView) view.findViewById(R.id.cur)).setText(g.getPriceCurrency());

        if (g.getAirlineCode2()==null) {
            ((TextView) view.findViewById(R.id.ac2)).setVisibility(View.GONE);
            ((TextView) view.findViewById(R.id.fn2)).setVisibility(View.GONE);
            ((TextView) view.findViewById(R.id.acm2)).setVisibility(View.GONE);
            ((TextView) view.findViewById(R.id.acr2)).setVisibility(View.GONE);
            ((TextView) view.findViewById(R.id.textView25)).setVisibility(View.GONE);
            ((TextView) view.findViewById(R.id.textView28)).setVisibility(View.GONE);
        }
        return view;

    }

    Trip getTrip(int position) {
        return ((Trip) getItem(position));
    }



}
