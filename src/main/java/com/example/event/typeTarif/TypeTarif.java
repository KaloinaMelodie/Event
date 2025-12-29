package com.example.event.typeTarif;

import source.ObjectBdd;

public class TypeTarif extends ObjectBdd {

    Integer idTypeTarif;
    String typeTarif;

    public Integer getIdTypeTarif() {
        return idTypeTarif;
    }

    public void setIdTypeTarif(Integer idTypeTarif) {
        this.idTypeTarif = idTypeTarif;
    }

    public String getTypeTarif() {
        return typeTarif;
    }

    public void setTypeTarif(String typeTarif) {
        this.typeTarif = typeTarif;
    }

    @Override
    public String getIdName() {
        return "idTypeTarif";
    }
}
