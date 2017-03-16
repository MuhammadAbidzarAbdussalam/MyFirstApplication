package com.zarslamgants25.updatesport;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class DetailArtikelActivity extends AppCompatActivity {

    private WebView webViewArtikel;
    private ImageView imageView;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private String id_detail, judul_detail, conten_detail, url_gambar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_artikel);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Are you sure to share this articel ?", Snackbar.LENGTH_LONG)
                        .setAction("Yes", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent sendArtikel = new Intent(Intent.ACTION_SEND);
                                sendArtikel.setType("text/plain");
                                sendArtikel.putExtra(Intent.EXTRA_SUBJECT, judul_detail);
                                sendArtikel.putExtra(Intent.EXTRA_TEXT, conten_detail);
                                startActivity(Intent.createChooser(sendArtikel, "share"));
                            }
                        }).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        webViewArtikel = (WebView) findViewById(R.id.webViewDetail);
        imageView = (ImageView) findViewById(R.id.imageViewDetail);
            collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);

        Intent menerimaIntent = getIntent();
        id_detail = menerimaIntent.getStringExtra("id_artikel");
        RequestDetailServer("http://31.220.53.18/muhammadabidzar/update_sport/detailArtikel.php?send_id_articel=" + id_detail);


    }

    private void RequestDetailServer(String urlrequest) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlrequest, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject json = new JSONObject(response);
                    JSONObject dataDetail = json.getJSONObject("hasil");
                    judul_detail = dataDetail.getString("judul_artikel");
                    conten_detail = dataDetail.getString("conten_artikel");
                    url_gambar = dataDetail.getString("url_gambar");

                    setContent(judul_detail, conten_detail, url_gambar);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "gagal bunggg", Toast.LENGTH_LONG).show();

            }
        });
        requestQueue.add(stringRequest);
    }

    private void setContent(String judul_detail, String conten_detail, String url_gambar) {
        Picasso.with(getApplicationContext())
                .load(url_gambar)
                .placeholder(R.mipmap.ic_launcher)
                .into(imageView);
        collapsingToolbarLayout.setTitle(judul_detail);
        String contentwvContent = "<html><body>" + conten_detail + "</body></html>";
        webViewArtikel.loadData(contentwvContent, "text/html", null);
    }
}
