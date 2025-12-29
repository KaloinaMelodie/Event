package com.example.event.categoriePlace;

import source.ObjectBdd;

public class CategoriePlace extends ObjectBdd {

    String idCategoriePlace;
    String categoriePlace;
    Integer nbrePlace;

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

    @Override
    public String getIdName() {
        return "idCategoriePlace";
    }

    @Override
    public String getIndexPk() {
        return "CPL";
    }
    
}
