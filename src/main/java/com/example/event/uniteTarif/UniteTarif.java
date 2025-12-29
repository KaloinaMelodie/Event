package com.example.event.uniteTarif;

import source.ObjectBdd;

public class UniteTarif extends ObjectBdd {

    Integer idUniteTarif;
    String uniteTarif;
    Double enHeure;

    public Integer getIdUniteTarif() {
        return idUniteTarif;
    }

    public void setIdUniteTarif(Integer idUniteTarif) {
        this.idUniteTarif = idUniteTarif;
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
        return "idUniteTarif";
    }
}
