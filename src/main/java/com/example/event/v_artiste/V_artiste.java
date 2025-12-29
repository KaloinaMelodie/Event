package com.example.event.v_artiste;

import source.ObjectBdd;

public class V_artiste extends ObjectBdd {

    String idArtiste;
    String photoArtiste;
    String nomArtiste;
    Double tarifArtiste;
    Integer idUniteTarifArtiste;
    String uniteTarif;
    Double enHeure;

    public String getIdArtiste() {
        return idArtiste;
    }

    public void setIdArtiste(String idArtiste) {
        this.idArtiste = idArtiste;
    }

    public String getPhotoArtiste() {
        return photoArtiste;
    }

    public void setPhotoArtiste(String photoArtiste) {
        this.photoArtiste = photoArtiste;
    }

    public String getNomArtiste() {
        return nomArtiste;
    }

    public void setNomArtiste(String nomArtiste) {
        this.nomArtiste = nomArtiste;
    }

    public Double getTarifArtiste() {
        return tarifArtiste;
    }

    public void setTarifArtiste(Double tarifArtiste) {
        this.tarifArtiste = tarifArtiste;
    }

    public Integer getIdUniteTarifArtiste() {
        return idUniteTarifArtiste;
    }

    public void setIdUniteTarifArtiste(Integer idUniteTarifArtiste) {
        this.idUniteTarifArtiste = idUniteTarifArtiste;
    }

    public String getUniteTarif() {
        return uniteTarif;
    }

    public void setUniteTarif(String uniteTarif) {
        this.uniteTarif = uniteTarif;
    }

    public Double getEnHeure() {
        return enHeure;
    }

    public void setEnHeure(Double enHeure) {
        this.enHeure = enHeure;
    }
    

    @Override
    public String getIdName() {
        return "idArtiste";
    }
}
