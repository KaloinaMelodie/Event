package com.example.event.categoriePlaceLieuDevis;

import source.ObjectBdd;

public class CategoriePlaceLieuDevis extends ObjectBdd {

    String idCategoriePlaceLieuDevis;
    String idCategoriePlace;
    String idLieu;
    String idDevis;
    Double prix;
    Integer nbrePlaceVendu;

    public String getIdCategoriePlaceLieuDevis() {
        return idCategoriePlaceLieuDevis;
    }

    public void setIdCategoriePlaceLieuDevis(String idCategoriePlaceLieuDevis) {
        this.idCategoriePlaceLieuDevis = idCategoriePlaceLieuDevis;
    }

    public String getIdCategoriePlace() {
        return idCategoriePlace;
    }

    public void setIdCategoriePlace(String idCategoriePlace) {
        this.idCategoriePlace = idCategoriePlace;
    }

    public String getIdLieu() {
        return idLieu;
    }

    public void setIdLieu(String idLieu) {
        this.idLieu = idLieu;
    }

    public String getIdDevis() {
        return idDevis;
    }

    public void setIdDevis(String idDevis) {
        this.idDevis = idDevis;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public Integer getNbrePlaceVendu() {
        return nbrePlaceVendu;
    }

    public void setNbrePlaceVendu(Integer nbrePlaceVendu) {
        this.nbrePlaceVendu = nbrePlaceVendu;
    }
    
    

    @Override
    public String getIdName() {
        return "idCategoriePlaceLieuDevis";
    }
}
