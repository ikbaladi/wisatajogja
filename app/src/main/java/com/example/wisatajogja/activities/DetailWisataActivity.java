package com.example.wisatajogja.activities;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.wisatajogja.R;
import com.example.wisatajogja.api.Api;
import com.example.wisatajogja.model.ModelWisata;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import org.json.JSONException;
import org.json.JSONObject;

public class DetailWisataActivity extends AppCompatActivity {

    Toolbar tbDetailWisata;
    ImageView imgWisata;
    ModelWisata modelWisata;

    TextView tvNamaWisata, tvAddressWisata, tvOpenTime, tvDescWisata, txtNamaWisata, txtAddressWisata, txtOpenTime, txtDeskripsi;
    String idWisata, NamaWisata, AddressWisata, DeskripsiWisata, OpenTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_wisata);

        tbDetailWisata = findViewById(R.id.tbDetailWisata);
        tbDetailWisata.setTitle("Detail Wisata");
        setSupportActionBar(tbDetailWisata);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        modelWisata = (ModelWisata) getIntent().getSerializableExtra("detailWisata");
        if (modelWisata != null) {
            idWisata = modelWisata.getIdWisata();


            //get String
            NamaWisata = modelWisata.getTxtNamaWisata();
            AddressWisata = modelWisata.getTxtAlamatWisata();
            OpenTime = modelWisata.getTxtOpenTime();
            DeskripsiWisata = modelWisata.getDeskripsiWisata();

            //set Id
            txtNamaWisata = findViewById(R.id.tvNamaWisata);
            txtAddressWisata = findViewById(R.id.tvAddressWisata);
            txtOpenTime = findViewById(R.id.tvOpenTime);
            txtDeskripsi = findViewById(R.id.tvDescWisata);
            imgWisata = findViewById(R.id.imgWisata);

            //show String to Text
            txtNamaWisata.setText(NamaWisata);
            txtAddressWisata.setText(AddressWisata);
            txtOpenTime.setText(OpenTime);
            txtDeskripsi.setText(DeskripsiWisata);

            //get Image
            Glide.with(this)
                    .load(modelWisata.getGambarWisata())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imgWisata);
            getDetailWisata();
        }
    }

    private void getDetailWisata() {
        AndroidNetworking.get(Api.DetailWisata)
                .addPathParameter("id", idWisata)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {

                                //get String Api
                                NamaWisata = response.getString("nama");
                                AddressWisata = response.getString("alamat");
                                OpenTime = response.getString("jam_buka_tutup");
                                DeskripsiWisata = response.getString("deskripsi");

                                //set Text
                                tvNamaWisata.setText(NamaWisata);
                                tvAddressWisata.setText(AddressWisata);
                                tvOpenTime.setText(OpenTime);
                                tvDescWisata.setText(DeskripsiWisata);

                            } catch (JSONException e) {
                                e.printStackTrace();
//                                Toast.makeText(DetailWisataActivity.this,
//                                        "Gagal menampilkan data!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(DetailWisataActivity.this,
                                "Tidak ada jaringan internet!", Toast.LENGTH_SHORT).show();
                    }
                });
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
