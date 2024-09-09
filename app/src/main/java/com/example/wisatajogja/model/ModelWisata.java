package com.example.wisatajogja.model;

import java.io.Serializable;

public class ModelWisata implements Serializable {

    private String idWisata, txtNamaWisata, txtAlamatWisata, txtOpenTime, GambarWisata, KategoriWisata, Koordinat, DeskripsiWisata, Rating, Ulasan;

    public String getIdWisata() {
        return idWisata;
    }

    public void setIdWisata(String idWisata) {
        this.idWisata = idWisata;
    }

    public String getTxtNamaWisata() {
        return txtNamaWisata;
    }

    public void setTxtNamaWisata(String txtNamaWisata) {
        this.txtNamaWisata = txtNamaWisata;
    }

    public String getGambarWisata() {
        return GambarWisata;
    }

    public void setGambarWisata(String gambarWisata) {
        GambarWisata = gambarWisata;
    }

    public String getKategoriWisata() {
        return KategoriWisata;
    }

    public void setKategoriWisata(String kategoriWisata) {
        KategoriWisata = kategoriWisata;
    }

    public String getKoordinat() {
        return Koordinat;
    }

    public void setKoordinat(String koordinat) {
        this.Koordinat = koordinat;
    }

    public String getDeskripsiWisata() {
        return DeskripsiWisata;
    }

    public void setDeskripsiWisata(String deskripsiWisata) {
        this.DeskripsiWisata = deskripsiWisata;
    }

    public String getTxtAlamatWisata() {
        return txtAlamatWisata;
    }

    public void setTxtAlamatWisata(String txtAlamatWisata) {
        this.txtAlamatWisata = txtAlamatWisata;
    }

    public String getTxtOpenTime() {
        return txtOpenTime;
    }

    public void setTxtOpenTime(String txtOpenTime) {
        this.txtOpenTime = txtOpenTime;
    }

    public String getRating() {
        return Rating;
    }

    public void setRating(String rating) {
        this.Rating = rating;
    }

    public String getUlasan() {
        return Ulasan;
    }

    public void setUlasan(String ulasan) {
        this.Ulasan = ulasan;
    }
}
