package com.example.event.artiste;

import com.example.event.uniteTarif.UniteTarif;
import java.util.ArrayList;
import java.util.HashMap;
import script.InputTypes;
import source.ObjectBdd;

public class Artiste extends ObjectBdd {

    String idArtiste;
    @annots.Input(type = InputTypes.FILE)
    String photoArtiste;
    String nomArtiste;
    Double tarifArtiste;
    Integer idUniteTarifArtiste;
    HashMap<String, String> jidUniteTarifArtiste;

    public String getIdArtiste() {
        return idArtiste;
    }

    public void setIdArtiste(String idArtiste) {
        this.idArtiste = idArtiste;
    }

    public String getPhotoArtiste() {
        return photoArtiste;
    }

    public void setPhotoArtiste(String photoArtiste) {
        this.photoArtiste = photoArtiste;
    }

    public String getNomArtiste() {
        return nomArtiste;
    }

    public void setNomArtiste(String nomArtiste) {
        this.nomArtiste = nomArtiste;
    }

    public Double getTarifArtiste() {
        return tarifArtiste;
    }

    public void setTarifArtiste(Double tarifArtiste) {
        this.tarifArtiste = tarifArtiste;
    }

    public Integer getIdUniteTarifArtiste() {
        return idUniteTarifArtiste;
    }

    public void setIdUniteTarifArtiste(Integer idUniteTarifArtiste) {
        this.idUniteTarifArtiste = idUniteTarifArtiste;
    }

    public HashMap<String, String> getJidUniteTarifArtiste() throws Exception {
        if (jidUniteTarifArtiste == null) {
            HashMap<String, String> data = new HashMap<String, String>() {
                {
                    ArrayList<UniteTarif> list = new UniteTarif().select(null, null);
                    for (UniteTarif unite : list) {
                        this.put(unite.getIdUniteTarif().toString(), unite.getUniteTarif());
                    }
                }
            };
            jidUniteTarifArtiste = data;
        }
        return jidUniteTarifArtiste;
    }

    @Override
    public String getIdName() {
        return "idArtiste";
    }
}
