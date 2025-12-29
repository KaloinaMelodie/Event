package com.example.event.v_lieu;

import source.ObjectBdd;

public class V_lieu extends ObjectBdd {

    String idLieu;
    Integer idTypeLieu;
    String nomLieu;
    String photoLieu;
    String typeLieu;

    public String getIdLieu() {
        return idLieu;
    }

    public void setIdLieu(String idLieu) {
        this.idLieu = idLieu;
    }

    public Integer getIdTypeLieu() {
        return idTypeLieu;
    }

    public void setIdTypeLieu(Integer idTypeLieu) {
        this.idTypeLieu = idTypeLieu;
    }

    public String getNomLieu() {
        return nomLieu;
    }

    public void setNomLieu(String nomLieu) {
        this.nomLieu = nomLieu;
    }
    

    public String getPhotoLieu() {
        return photoLieu;
    }

    public void setPhotoLieu(String photoLieu) {
        this.photoLieu = photoLieu;
    }

    public String getTypeLieu() {
        return typeLieu;
    }

    public void setTypeLieu(String typeLieu) {
        this.typeLieu = typeLieu;
    }
    

    @Override
    public String getIdName() {
        return "idLieu";
    }
}
