package com.example.event.v_categorieplacelieudevis;

import source.ObjectBdd;

public class V_categoriePlaceLieuDevis extends ObjectBdd {

    String idDevis;
    String idLieu;
    String idCategoriePlaceLieuDevis;
    String idCategoriePlace;
    String categoriePlace;
    Integer nbrePlace;
    Double prix;
    Double montant;
    Integer nbrePlaceVendu;
    Double montantVrai;

    public String getIdDevis() {
        return idDevis;
    }

    public void setIdDevis(String idDevis) {
        this.idDevis = idDevis;
    }

    public String getIdLieu() {
        return idLieu;
    }

    public void setIdLieu(String idLieu) {
        this.idLieu = idLieu;
    }

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

    public String getCategoriePlace() {
        return categoriePlace;
    }

    public void setCategoriePlace(String categoriePlace) {
        this.categoriePlace = categoriePlace;
    }

    public Integer getNbrePlace() {
        return nbrePlace;
    }

    public void setNbrePlace(Integer nbrePlace) {
        this.nbrePlace = nbrePlace;
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

    public Integer getNbrePlaceVendu() {
        return nbrePlaceVendu;
    }

    public void setNbrePlaceVendu(Integer nbrePlaceVendu) {
        this.nbrePlaceVendu = nbrePlaceVendu;
    }

    public Double getMontantVrai() {
        return montantVrai;
    }

    public void setMontantVrai(Double montantVrai) {
        this.montantVrai = montantVrai;
    }
    
    
    
    

    @Override
    public String getIdName() {
        return "idCategoriePlaceLieuDevis";
    }
}
