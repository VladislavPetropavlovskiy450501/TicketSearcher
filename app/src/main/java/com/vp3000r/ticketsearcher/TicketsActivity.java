package com.vp3000r.ticketsearcher;

import android.app.DatePickerDialog;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;



public class TicketsActivity extends AppCompatActivity {

    private EditText txtRegWinBD;
    private DatePickerDialog dateDatePicker;
    private ListView mTickList;
    private TicketsAdapter mAdapter, mAdapterFT, mAdapterCT, mAdapterCS;
    private ArrayList<Trip> mTrips = new ArrayList<>();
    private int FastestIndex, ChippestIndex, StrightChippestIndex;
    private boolean AdapterState = false;


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tickets);
        //находим в layout-файле нужный нам EditText
        txtRegWinBD = (EditText) findViewById(R.id.txtRegWindowBD);
        //эта функция делает шаг 1: создает объект DatePickerDialog
        mTickList = (ListView) findViewById(R.id.trips_list);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

            ((Button) findViewById(R.id.button2)).setVisibility(View.GONE);
            ((Button) findViewById(R.id.button3)).setVisibility(View.GONE);
            ((Button) findViewById(R.id.button4)).setVisibility(View.GONE);
        ((TextView) findViewById(R.id.textView12)).setVisibility(View.GONE);

        ListView lw = (ListView) findViewById(R.id.trips_list);
        assert lw != null;
        lw.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                putExtrasInIntent (position);
            }
        }
        );
    }

    public void putExtrasInIntent (int position)
    {
        Intent intent = new Intent(TicketsActivity.this, ItemActivity.class);
        intent.putExtra("cnf",mTrips.get(position).getCityNameFrom());
        intent.putExtra("cnt",mTrips.get(position).getCityNameTo());
        intent.putExtra("dd",mTrips.get(position).getDepDate());
        intent.putExtra("ad",mTrips.get(position).getArrDate());
        intent.putExtra("dt",mTrips.get(position).getDepTime());
        intent.putExtra("at",mTrips.get(position).getArrTime());
        intent.putExtra("if",mTrips.get(position).getIataFrom());
        intent.putExtra("it",mTrips.get(position).getIataTo());
        intent.putExtra("anf",mTrips.get(position).getAirportNameFrom());
        intent.putExtra("ant",mTrips.get(position).getAirportNameTo());
        intent.putExtra("ds",mTrips.get(position).getDurationStr());
        intent.putExtra("ac1",mTrips.get(position).getAirlineCode());
        intent.putExtra("an1",mTrips.get(position).getAirlineName());
        intent.putExtra("fn1",mTrips.get(position).getFlightNumber());
        intent.putExtra("acr1",mTrips.get(position).getAircraft());
        intent.putExtra("prs",mTrips.get(position).getPriceStr());
        intent.putExtra("prc",mTrips.get(position).getPriceCurrency());
        intent.putExtra("ac2",mTrips.get(position).getAirlineCode2());
        intent.putExtra("fn2",mTrips.get(position).getFlightNumber2());
        intent.putExtra("an2",mTrips.get(position).getAirlineName2());
        intent.putExtra("acr2",mTrips.get(position).getAircraft2());
        startActivity(intent);
    }

    public static boolean hasConnection(final Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiInfo;
        wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (wifiInfo != null && wifiInfo.isConnected()) {
            return true;
        }
        wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (wifiInfo != null && wifiInfo.isConnected()) {
            return true;
        }
        wifiInfo = cm.getActiveNetworkInfo();
        if (wifiInfo != null && wifiInfo.isConnected()) {
            return true;
        }
        return false;
    }
    public void onClick(View w) {
        switch (w.getId()) {
            case R.id.button:
            {
                EditText editText = (EditText) findViewById(R.id.txtRegWindowBD);
                String value = editText.getText().toString();

                String val2 = value.substring(6,10)+value.substring(3,5)+value.substring(0,2);
                if (hasConnection(this)==true) {
                    getTickets(val2);
                }
                else
                {
                    Toast.makeText(this, "No internet connection", Toast.LENGTH_LONG).show();

                }

              //  getTickets(val2);
                break;

            }

            case R.id.txtRegWindowBD:
            {
                initDatePicker();
                dateDatePicker.show();

                break;
        }
            case R.id.button2:
            {
                putExtrasInIntent (FastestIndex);

                break;
            }
            case R.id.button3:
            {
                putExtrasInIntent (ChippestIndex);

                break;
            }
            case R.id.button4:
            {
                putExtrasInIntent (StrightChippestIndex);

                break;
            }

        }
    }



    private void initDatePicker() {
        Calendar newCalendar = Calendar.getInstance();
        final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

        dateDatePicker = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar c=Calendar.getInstance();

                int yearr=c.get(c.YEAR);
                int month=c.get(c.MONTH);
                int day=c.get(c.DAY_OF_MONTH);
                Calendar newCal = Calendar.getInstance();
                if (year>yearr || (year==yearr && monthOfYear>month) || (year==yearr && monthOfYear==month && dayOfMonth>=day))
                newCal.set(year, monthOfYear, dayOfMonth);

                txtRegWinBD.setText(dateFormat.format(newCal.getTime()));

            }
        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
    }



    public void getTickets(String dateStr) {
        String url = "http://android-dev-tests.ru/sapi/v1/flight/tickets/MOWSIP"+dateStr+"?apikey=177a01bf5813336afd59e6551216f6ed";

        JsonArrayRequest stringRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            mTrips.clear();
                            AdapterState = false;
                             putDataToAdapter(response);

                            if (AdapterState==true)
                            {
                                FastestIndex = findFastest(response);
                                StrightChippestIndex = findChippestStraight(response);
                                ChippestIndex = findChippest(response);
                                ((Button) findViewById(R.id.button2)).setVisibility(View.VISIBLE);
                                ((Button) findViewById(R.id.button3)).setVisibility(View.VISIBLE);
                                ((Button) findViewById(R.id.button4)).setVisibility(View.VISIBLE);
                                ((TextView) findViewById(R.id.textView12)).setVisibility(View.GONE);
                            }
                            else {
                                ((TextView) findViewById(R.id.textView12)).setVisibility(View.VISIBLE);
                                ((Button) findViewById(R.id.button2)).setVisibility(View.GONE);
                                ((Button) findViewById(R.id.button3)).setVisibility(View.GONE);
                                ((Button) findViewById(R.id.button4)).setVisibility(View.GONE);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {


            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Volley", "Error: " + error.getMessage());


            }
        }){
            @Override
            public Map<String,String> getHeaders () {
                Map<String, String> headers = new HashMap<>();
                String credentials = "user2:Bshj7Hj9rwt";
                String auth = "Basic "
                        + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
                headers.put("Content-Type", "application/json");
                headers.put("Authorization", auth);

                return headers;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private int findFastest(JSONArray array) throws JSONException {
        int mTickCount = array.length();
        int FastestTime;
        int FastestTimeIndex = 0;
        JSONObject object = array.getJSONObject(0);
        FastestTime = object.getInt("duration");
        for(int i = 1; i < mTickCount; i++) {
            object = array.getJSONObject(i);
            if (FastestTime > object.getInt("duration")) {
                FastestTime = object.getInt("duration");
                FastestTimeIndex = i;
            }
        }
        return FastestTimeIndex;

    }

    private int findChippest (JSONArray array) throws JSONException {
        int mTickCount = array.length();
        int MinPrice;
        int MinPriceIndex = 0;
        JSONObject object = array.getJSONObject(0);
        MinPrice = object.getInt("price");
        for (int i = 1; i < mTickCount; i++) {
            object = array.getJSONObject(i);
            if (MinPrice > object.getInt("price")) {
                MinPrice = object.getInt("price");
                MinPriceIndex = i;
            }
        }
            return MinPriceIndex;

    }

    private int findChippestStraight(JSONArray array) throws JSONException {
        int mTickCount = array.length();
        int MinPrice = 0;
        int MinPriceIndex = 0;
        boolean flag = true;
        int i = 0;
        JSONObject object;
        while (flag == true) {
            object = array.getJSONObject(i);
            if (object.getString("airlineCode2").equals("null")) {
                MinPrice = object.getInt("price");
                MinPriceIndex = i;
                flag = false;
            }
            i++;
        }
        for (; i < mTickCount; i++) {
            object = array.getJSONObject(i);
            if (MinPrice > object.getInt("price") && object.getString("airlineCode2").equals("null")) {
                MinPrice = object.getInt("price");
                MinPriceIndex = i;
            }
        }
            return MinPriceIndex;

    }

    private void putDataToAdapter(JSONArray array) throws JSONException {
        int mTickCount = array.length();
if (mTickCount > 0) AdapterState = true;

        for(int i = 0; i < mTickCount; i++) {


            JSONObject object = array.getJSONObject(i);
            mTrips.add(new Trip(object.getString("cityNameFrom"),
                    object.getString("cityNameTo"),
                    object.getString("iataFrom"),
                    object.getString("iataTo"),
                    object.getString("airportNameFrom"),
                    object.getString("airportNameTo"),
                    object.getString("depDate"),
                    object.getString("depTime"),
                    object.getString("arrDate"),
                    object.getString("arrTime"),
                    object.getInt("duration"),
                    object.getString("durationStr"),
                    object.getString("airlineCode"),
                    object.getString("airlineName"),
                    object.getString("flightNumber"),
                    object.getString("aircraft"),
                    object.getString("airlineCode2"),
                    object.getString("airlineName2"),
                    object.getString("flightNumber2"),
                    object.getString("aircraft2"),
                    object.getInt("price"),
                    object.getString("priceCurrency"),
                    object.getString("code")));
        }

        mAdapter = new TicketsAdapter(getApplicationContext(), mTrips);
        mTickList.setAdapter(mAdapter);
    }


    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Tickets Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.vp3000r.ticketsearcher/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Tickets Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.vp3000r.ticketsearcher/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }





}
