package com.example.wisatajogja.model;

import java.io.Serializable;

public class ModelKuliner implements Serializable {

    private String idKuliner, txtNamaKuliner, txtAlamatKuliner, txtOpenTime, Rating, Ulasan, Koordinat, GambarKuliner, KategoriKuliner, DeskripsiKuliner;

    public String getIdKuliner() {
        return idKuliner;
    }

    public void setIdKuliner(String idKuliner) {
        this.idKuliner = idKuliner;
    }

    public String getTxtNamaKuliner() {
        return txtNamaKuliner;
    }

    public void setTxtNamaKuliner(String txtNamaKuliner) {
        this.txtNamaKuliner = txtNamaKuliner;
    }

    public String getTxtAlamatKuliner() {
        return txtAlamatKuliner;
    }

    public void setTxtAlamatKuliner(String txtAlamatKuliner) {
        this.txtAlamatKuliner = txtAlamatKuliner;
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

    public String getKoordinat() {
        return Koordinat;
    }

    public void setKoordinat(String koordinat) {
        this.Koordinat = koordinat;
    }

    public String getGambarKuliner() {
        return GambarKuliner;
    }

    public void setGambarKuliner(String gambarKuliner) {
        this.GambarKuliner = gambarKuliner;
    }

    public String getKategoriKuliner() {
        return KategoriKuliner;
    }

    public void setKategoriKuliner(String kategoriKuliner) {
        this.KategoriKuliner = kategoriKuliner;
    }

    public String getDeskripsiKuliner() {
        return DeskripsiKuliner;
    }

    public void setDeskripsiKuliner(String deskripsiKuliner) {
        this.DeskripsiKuliner = deskripsiKuliner;
    }
}
