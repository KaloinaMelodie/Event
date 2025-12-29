package com.example.event.v_categorieDevis;

import source.ObjectBdd;

public class V_categorieDevis extends ObjectBdd {

    String idCategoriePlace;
    String categoriePlace;
    String idDevis;
    Double montantTotal;
    Double montantTotalVrai;

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

    public String getIdDevis() {
        return idDevis;
    }

    public void setIdDevis(String idDevis) {
        this.idDevis = idDevis;
    }

    public Double getMontantTotal() {
        return montantTotal;
    }

    public void setMontantTotal(Double montantTotal) {
        this.montantTotal = montantTotal;
    }

    public Double getMontantTotalVrai() {
        return montantTotalVrai;
    }

    public void setMontantTotalVrai(Double montantTotalVrai) {
        this.montantTotalVrai = montantTotalVrai;
    }
        

    @Override
    public String getIdName() {
        return "idDevis";
    }
}
