package com.example.event.v_prestation;

import source.ObjectBdd;

public class V_prestation extends ObjectBdd {

    String idPrestation;
    String prestation;
    String idTypePrestation;
    Integer idUniteTarif;
    Integer idTypeTarif;
    Double tarif;
    Integer isFixe;
    Integer place;
    String typePrestation;
    Integer isFixePrestation;
    String uniteTarif;
    Double enHeure;
    String typeTarif;
    Double tarifEnHeure;

    public String getIdPrestation() {
        return idPrestation;
    }

    public void setIdPrestation(String idPrestation) {
        this.idPrestation = idPrestation;
    }

    public String getPrestation() {
        return prestation;
    }

    public void setPrestation(String prestation) {
        this.prestation = prestation;
    }

    public String getIdTypePrestation() {
        return idTypePrestation;
    }

    public void setIdTypePrestation(String idTypePrestation) {
        this.idTypePrestation = idTypePrestation;
    }

    public Integer getIdUniteTarif() {
        return idUniteTarif;
    }

    public void setIdUniteTarif(Integer idUniteTarif) {
        this.idUniteTarif = idUniteTarif;
    }

    public Integer getIdTypeTarif() {
        return idTypeTarif;
    }

    public void setIdTypeTarif(Integer idTypeTarif) {
        this.idTypeTarif = idTypeTarif;
    }

    public Double getTarif() {
        return tarif;
    }

    public void setTarif(Double tarif) {
        this.tarif = tarif;
    }

    public Integer getIsFixe() {
        return isFixe;
    }

    public void setIsFixe(Integer isFixe) {
        this.isFixe = isFixe;
    }

    public Integer getPlace() {
        return place;
    }

    public void setPlace(Integer place) {
        this.place = place;
    }

    public String getTypePrestation() {
        return typePrestation;
    }

    public void setTypePrestation(String typePrestation) {
        this.typePrestation = typePrestation;
    }

    public Integer getIsFixePrestation() {
        return isFixePrestation;
    }

    public void setIsFixePrestation(Integer isFixePrestation) {
        this.isFixePrestation = isFixePrestation;
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

    public String getTypeTarif() {
        return typeTarif;
    }

    public void setTypeTarif(String typeTarif) {
        this.typeTarif = typeTarif;
    }

    public Double getTarifEnHeure() {
        return tarifEnHeure;
    }

    public void setTarifEnHeure(Double tarifEnHeure) {
        this.tarifEnHeure = tarifEnHeure;
    }

    @Override
    public String getIdName() {
        return "idPrestation";
    }

    @Override
    public String toString() {
        return "V_prestation{" + "idPrestation=" + idPrestation + ", prestation=" + prestation + ", idTypePrestation=" + idTypePrestation + ", idUniteTarif=" + idUniteTarif + ", idTypeTarif=" + idTypeTarif + ", tarif=" + tarif + ", isFixe=" + isFixe + ", place=" + place + ", typePrestation=" + typePrestation + ", isFixePrestation=" + isFixePrestation + ", uniteTarif=" + uniteTarif + ", enHeure=" + enHeure + ", typeTarif=" + typeTarif + ", tarifEnHeure=" + tarifEnHeure + '}';
    }
    
}
