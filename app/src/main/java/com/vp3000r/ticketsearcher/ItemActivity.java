package com.vp3000r.ticketsearcher;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ItemActivity extends AppCompatActivity {
    private TextView mIdTxtView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        showData();
    }

    public void showData()
    {
        ((TextView) findViewById(R.id.cf)).setText(getIntent().getStringExtra("cnf"));
        ((TextView) findViewById(R.id.ct)).setText(getIntent().getStringExtra("cnt"));
        ((TextView) findViewById(R.id.df)).setText(getIntent().getStringExtra("dd"));
        ((TextView) findViewById(R.id.dt)).setText(getIntent().getStringExtra("ad"));
        ((TextView) findViewById(R.id.tf)).setText(getIntent().getStringExtra("dt"));
        ((TextView) findViewById(R.id.tt)).setText(getIntent().getStringExtra("at"));
        ((TextView) findViewById(R.id.ccf)).setText(getIntent().getStringExtra("if"));
        ((TextView) findViewById(R.id.cct)).setText(getIntent().getStringExtra("it"));
        ((TextView) findViewById(R.id.acf)).setText(getIntent().getStringExtra("anf"));
        ((TextView) findViewById(R.id.act)).setText(getIntent().getStringExtra("ant"));
        ((TextView) findViewById(R.id.dur)).setText(getIntent().getStringExtra("ds"));
        ((TextView) findViewById(R.id.ac1)).setText(getIntent().getStringExtra("ac1"));
        ((TextView) findViewById(R.id.acm1)).setText(getIntent().getStringExtra("an1"));
        ((TextView) findViewById(R.id.fn1)).setText(getIntent().getStringExtra("fn1"));
        ((TextView) findViewById(R.id.acr1)).setText(getIntent().getStringExtra("acr1"));
        ((TextView) findViewById(R.id.pr)).setText(getIntent().getStringExtra("prs"));
        ((TextView) findViewById(R.id.cur)).setText(getIntent().getStringExtra("prc"));

      /*  String aaa = getIntent().getStringExtra("acr2");
        String bbb = aaa;*/
        if (getIntent().getStringExtra("acr2").equals("null")) {

            ((TextView) findViewById(R.id.textView9)).setVisibility(View.GONE);
            ((TextView) findViewById(R.id.fn2)).setVisibility(View.GONE);
            ((TextView) findViewById(R.id.textView11)).setVisibility(View.GONE);
            ((TextView) findViewById(R.id.acr2)).setVisibility(View.GONE);
            ((TextView) findViewById(R.id.textView25)).setVisibility(View.GONE);
            ((TextView) findViewById(R.id.textView28)).setVisibility(View.GONE);
            ((TextView) findViewById(R.id.textView8)).setVisibility(View.GONE);
            ((TextView) findViewById(R.id.textView10)).setVisibility(View.GONE);
        }
        else
        {
            ((TextView) findViewById(R.id.textView9)).setText(getIntent().getStringExtra("ac2"));
            ((TextView) findViewById(R.id.textView11)).setText(getIntent().getStringExtra("an2"));
            ((TextView) findViewById(R.id.fn2)).setText(getIntent().getStringExtra("fn2"));
            ((TextView) findViewById(R.id.acr2)).setText(getIntent().getStringExtra("acr2"));
        }





    }

}
