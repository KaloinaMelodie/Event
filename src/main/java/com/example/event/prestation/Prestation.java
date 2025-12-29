package com.example.event.prestation;

import com.example.event.typePrestation.TypePrestation;
import com.example.event.typeTarif.TypeTarif;
import com.example.event.uniteTarif.UniteTarif;
import java.util.ArrayList;
import java.util.HashMap;
import source.ObjectBdd;

public class Prestation extends ObjectBdd {

    String idPrestation;
    String prestation;
    String idTypePrestation;
    Integer idUniteTarif;
    Integer idTypeTarif;
    Double tarif;
    Integer isFixe;
    Integer place;
    HashMap<String, String> jisFixe;
    HashMap<String, String> jidTypePrestation;
    HashMap<String, String> jidUniteTarif;
    HashMap<String, String> jidTypeTarif;

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
    
    public HashMap<String, String> getJidTypePrestation() throws Exception {
        if (jidTypePrestation == null) {
            HashMap<String, String> data = new HashMap<String, String>() {
                {
                    ArrayList<TypePrestation> list = new TypePrestation().select(null, null);
                    for (TypePrestation unite : list) {
                        this.put(unite.getIdTypePrestation().toString(), unite.getTypePrestation());
                    }
                }
            };
            jidTypePrestation = data;
        }
        return jidTypePrestation;
    }
    public HashMap<String, String> getJidTypeTarif() throws Exception {
        if (jidTypeTarif == null) {
            HashMap<String, String> data = new HashMap<String, String>() {
                {
                    ArrayList<TypeTarif> list = new TypeTarif().select(null, null);
                    for (TypeTarif unite : list) {
                        this.put(unite.getIdTypeTarif().toString(), unite.getTypeTarif());
                    }
                }
            };
            jidTypeTarif = data;
        }
        return jidTypeTarif;
    }
    public HashMap<String, String> getJidUniteTarif() throws Exception {
        if (jidUniteTarif == null) {
            HashMap<String, String> data = new HashMap<String, String>() {
                {
                    ArrayList<UniteTarif> list = new UniteTarif().select(null, null);
                    for (UniteTarif unite : list) {
                        this.put(unite.getIdUniteTarif().toString(), unite.getUniteTarif());
                    }
                }
            };
            jidUniteTarif = data;
        }
        return jidUniteTarif;
    }
    
    public HashMap<String, String> getJisFixe() throws Exception {
        if (jisFixe == null) {
            HashMap<String, String> data = new HashMap<String, String>() {
                {

                    this.put("1", "oui");
                    this.put("0", "non");

                }
            };
            jisFixe = data;
        }
        return jisFixe;
    }

    @Override
    public String getIdName() {
        return "idPrestation";
    }
}
