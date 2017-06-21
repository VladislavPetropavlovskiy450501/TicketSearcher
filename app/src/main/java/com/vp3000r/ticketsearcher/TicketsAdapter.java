package com.vp3000r.ticketsearcher;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

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
       /*
        if (view == null) {
            view = this.inflater.inflate(R.layout.game_item, parent, false);
        }

        Game g = getGame(position);

        ((TextView) view.findViewById(R.id.gameName)).setText(SGONAY_TEXT + g.getNumber());
        ((TextView) view.findViewById(R.id.gameDate)).setText(g.getDate());
        if (g.getScheme().equals("3")) {
            ((TextView) view.findViewById(R.id.gameTimeOut)).setText("");
        } else {
            ((TextView) view.findViewById(R.id.gameTimeOut)).setText(g.getTimeOut() + MINUTES_TEXT);
        }
        ((TextView) view.findViewById(R.id.gameTitle)).setText(g.getTitle());
*/
        return view;

    }

    Trip getTrip(int position) {
        return ((Trip) getItem(position));
    }



}
