package com.example.event.categoriePlaceDevis;

import source.ObjectBdd;

public class CategoriePlaceDevis extends ObjectBdd {

    String idCategoriePlaceDevis;
    String idCategoriePlace;
    String idDevis;
    Double prix;

    public String getIdCategoriePlaceDevis() {
        return idCategoriePlaceDevis;
    }

    public void setIdCategoriePlaceDevis(String idCategoriePlaceDevis) {
        this.idCategoriePlaceDevis = idCategoriePlaceDevis;
    }

    public String getIdCategoriePlace() {
        return idCategoriePlace;
    }

    public void setIdCategoriePlace(String idCategoriePlace) {
        this.idCategoriePlace = idCategoriePlace;
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

    @Override
    public String getIdName() {
        return "idCategoriePlaceDevis";
    }

    @Override
    public String getIndexPk() {
        return "CPD";
    }
    
    
}
