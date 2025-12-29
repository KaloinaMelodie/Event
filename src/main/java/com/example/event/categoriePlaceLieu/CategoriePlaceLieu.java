package com.example.event.categoriePlaceLieu;

import com.example.event.categoriePlace.CategoriePlace;
import com.example.event.lieu.Lieu;
import java.util.ArrayList;
import java.util.HashMap;
import source.ObjectBdd;

public class CategoriePlaceLieu extends ObjectBdd {

    String idCategoriePlaceLieu;
    String idCategoriePlace;
    String idLieu;
    Integer nbrePlace;
    HashMap<String, String> jidLieu;
    HashMap<String, String> jidCategoriePlace;

    public String getIdCategoriePlaceLieu() {
        return idCategoriePlaceLieu;
    }

    public void setIdCategoriePlaceLieu(String idCategoriePlaceLieu) {
        this.idCategoriePlaceLieu = idCategoriePlaceLieu;
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

    public Integer getNbrePlace() {
        return nbrePlace;
    }

    public void setNbrePlace(Integer nbrePlace) {
        this.nbrePlace = nbrePlace;
    }
    
     public HashMap<String, String> getJidLieu() throws Exception {
        if (jidLieu == null) {
            HashMap<String, String> data = new HashMap<String, String>() {
                {
                    ArrayList<Lieu> list = new Lieu().select(null, null);
                    for (Lieu unite : list) {
                        this.put(unite.getIdLieu().toString(), unite.getNomLieu());
                    }
                }
            };
            jidLieu = data;
        }
        return jidLieu;
    }
     public HashMap<String, String> getJidCategoriePlace() throws Exception {
        if (jidCategoriePlace == null) {
            HashMap<String, String> data = new HashMap<String, String>() {
                {
                    ArrayList<CategoriePlace> list = new CategoriePlace().select(null, null);
                    for (CategoriePlace unite : list) {
                        this.put(unite.getIdCategoriePlace().toString(), unite.getCategoriePlace());
                    }
                }
            };
            jidCategoriePlace = data;
        }
        return jidCategoriePlace;
    }

    @Override
    public String getIdName() {
        return "idCategoriePlaceLieu";
    }

    @Override
    public String getIndexPk() {
        return "CPL";
    }
    
    
}
