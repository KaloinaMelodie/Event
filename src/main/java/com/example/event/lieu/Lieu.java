package com.example.event.lieu;

import com.example.event.typeLieu.TypeLieu;
import java.util.ArrayList;
import java.util.HashMap;
import script.InputTypes;
import source.ObjectBdd;

public class Lieu extends ObjectBdd {

    String idLieu;
    Integer idTypeLieu;
    String nomLieu;
    @annots.Input(type = InputTypes.FILE)
    String photoLieu;
    HashMap<String, String> jidTypeLieu;

    public String getIdLieu() {
        return idLieu;
    }

    public void setIdLieu(String idLieu) {
        this.idLieu = idLieu;
    }

    public Integer getIdTypeLieu() {
        return idTypeLieu;
    }

    public void setIdTypeLieu(Integer idTypeLieu) {
        this.idTypeLieu = idTypeLieu;
    }

    public String getNomLieu() {
        return nomLieu;
    }

    public void setNomLieu(String nomLieu) {
        this.nomLieu = nomLieu;
    }

    public String getPhotoLieu() {
        return photoLieu;
    }

    public void setPhotoLieu(String photoLieu) {
        this.photoLieu = photoLieu;
    }

    public HashMap<String, String> getJidTypeLieu() throws Exception {
        if (jidTypeLieu == null) {
            HashMap<String, String> data = new HashMap<String, String>() {
                {
                    ArrayList<TypeLieu> list = new TypeLieu().select(null, null);
                    for (TypeLieu unite : list) {
                        this.put(unite.getIdTypeLieu().toString(), unite.getTypeLieu());
                    }
                }
            };
            jidTypeLieu = data;
        }
        return jidTypeLieu;
    }

    @Override
    public String getIdName() {
        return "idLieu";
    }
}
