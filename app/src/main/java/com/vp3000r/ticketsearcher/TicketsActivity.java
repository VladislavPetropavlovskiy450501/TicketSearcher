package com.vp3000r.ticketsearcher;

import android.app.DatePickerDialog;
import android.app.DownloadManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
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
    /*
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_tickets);
        }*/
    private EditText txtRegWinBD;
    private DatePickerDialog dateBirdayDatePicker;
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

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void onClick(View w) {
        switch (w.getId()) {
            case R.id.txtRegWindowBD:
                // это шаг 3, функцией show() мы говорим, что календарь нужно отобразить
                dateBirdayDatePicker.show();
                break;
            case R.id.button:
                EditText editText = (EditText) findViewById(R.id.txtRegWindowBD);
                String value = editText.getText().toString();
                char buf[] = new char[8];

                value.getChars(6,9,buf,0);
                value.getChars(3,4,buf,4);
                value.getChars(0,1,buf,6);
                  String dateStr = buf.toString();
                getTickets(dateStr);
                break;
        }
    }


    private void initDateBirthdayDatePicker() {
        Calendar newCalendar = Calendar.getInstance(); // объект типа Calendar мы будем использовать для получения даты
        final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy"); // это строка нужна для дальнейшего преобразования даты в строку
        //создаем объект типа DatePickerDialog и инициализируем его конструктор обработчиком события выбора даты и данными для даты по умолчанию
        dateBirdayDatePicker = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            // функция onDateSet обрабатывает шаг 2: отображает выбранные нами данные в элементе EditText
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newCal = Calendar.getInstance();
                newCal.set(year, monthOfYear, dayOfMonth);
                txtRegWinBD.setText(dateFormat.format(newCal.getTime()));
            }
        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
    }



    public void getTickets(String dateStr) {
        JsonArrayRequest stringRequest = new JsonArrayRequest(Request.Method.POST, "http://android-dev-tests.ru/sapi/v1/flight/tickets/MOWSIP"+dateStr+"?apikey=177a01bf5813336afd59e6551216f6ed", null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                             putDataToAdapter(response);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }



                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(null, "Error: " + error.getMessage());

            }
        }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("Login","user2");
                params.put("password","Bshj7Hj9rwt");
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private ListView mTickList;

    private TicketsAdapter mAdapter;
    private ArrayList<Trip> mTrips = new ArrayList<>();
    private void putDataToAdapter(JSONArray array) throws JSONException {
        int mTickCount = array.length();

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
