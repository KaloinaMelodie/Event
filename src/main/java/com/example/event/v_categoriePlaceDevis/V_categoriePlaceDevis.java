package com.example.event.v_categoriePlaceDevis;

import source.ObjectBdd;

public class V_categoriePlaceDevis extends ObjectBdd {

    String idDevis;
    String idCategoriePlaceDevis;
    String idCategoriePlace;
    String categoriePlace;
    Integer nbrePlace;
    Double prix;
    Double montant;

    public String getIdDevis() {
        return idDevis;
    }

    public void setIdDevis(String idDevis) {
        this.idDevis = idDevis;
    }

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

    public String getCategoriePlace() {
        return categoriePlace;
    }

    public void setCategoriePlace(String categoriePlace) {
        this.categoriePlace = categoriePlace;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }    

    public Integer getNbrePlace() {
        return nbrePlace;
    }

    public void setNbrePlace(Integer nbrePlace) {
        this.nbrePlace = nbrePlace;
    }
    

    @Override
    public String getIdName() {
        return "idCategoriePlaceDevis";
    }
}
