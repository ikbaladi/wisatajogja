package com.example.wisatajogja.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.wisatajogja.R;
import com.example.wisatajogja.adapter.HotelAdapter;
import com.example.wisatajogja.api.Api;
import com.example.wisatajogja.model.ModelHotel;
import com.example.wisatajogja.decoration.LayoutMarginDecoration;
import com.example.wisatajogja.utilities.Tools;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HotelActivity extends AppCompatActivity implements HotelAdapter.onSelectData {

    RecyclerView rvHotel;
    HotelAdapter hotelAdapter;
    ProgressDialog progressDialog;
    List<ModelHotel> modelHotel = new ArrayList<>();
    Toolbar tbHotel;
    LayoutMarginDecoration gridMargin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel);

        tbHotel = findViewById(R.id.toolbar_hotel);
        tbHotel.setTitle("Daftar Penginapan di Yogyakarta");
        setSupportActionBar(tbHotel);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Mohon Tunggu");
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Sedang menampilkan data...");

        rvHotel = findViewById(R.id.rvHotel);
        GridLayoutManager mLayoutManager = new GridLayoutManager(this,
                2, RecyclerView.VERTICAL, false);
        rvHotel.setLayoutManager(mLayoutManager);
        gridMargin = new LayoutMarginDecoration(2, Tools.dp2px(this, 4));
        rvHotel.addItemDecoration(gridMargin);
        rvHotel.setHasFixedSize(true);

        getHotel();
    }

    private void getHotel() {
        progressDialog.show();
        AndroidNetworking.get(Api.Hotel)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            progressDialog.dismiss();
                            JSONArray playerArray = response.getJSONArray("hotel");
                            for (int i = 0; i < playerArray.length(); i++) {
                                JSONObject temp = playerArray.getJSONObject(i);
                                ModelHotel dataApi = new ModelHotel();
                                dataApi.setTxtNamaHotel(temp.getString("nama"));
                                dataApi.setTxtAlamatHotel(temp.getString("alamat"));
                                dataApi.setTxtKabupaten(temp.getString("kabupaten"));
                                dataApi.setTxtNoTelp(temp.getString("nomor_telp"));
                                dataApi.setKoordinat(temp.getString("kordinat"));
                                dataApi.setGambarHotel(temp.getString("gambar_url"));
                                modelHotel.add(dataApi);
                                showHotel();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(HotelActivity.this,
                                    "Gagal menampilkan data!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        progressDialog.dismiss();
                        Toast.makeText(HotelActivity.this,
                                "Tidak ada jaringan internet!", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void showHotel() {
        hotelAdapter = new HotelAdapter(HotelActivity.this, modelHotel, this);
        rvHotel.setAdapter(hotelAdapter);
    }

    @Override
    public void onSelected(ModelHotel modelHotel) {
        Intent intent = new Intent(HotelActivity.this, com.example.wisatajogja.activities.DetailHotelActivity.class);
        intent.putExtra("detailHotel", modelHotel);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
