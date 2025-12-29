package com.example.event.taxe;

import source.ObjectBdd;

public class Taxe extends ObjectBdd {

    String idTaxe;
    Double pourcentage;

    public String getIdTaxe() {
        return idTaxe;
    }

    public void setIdTaxe(String idTaxe) {
        this.idTaxe = idTaxe;
    }

    public Double getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(Double pourcentage) {
        this.pourcentage = pourcentage;
    }

    public double getPourc() {
        return (getPourcentage() != null) ? getPourcentage() * 100 : 0;
    }

    @Override
    public String getIdName() {
        return "idTaxe";
    }
}
