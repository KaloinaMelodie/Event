package com.example.event.typePrestation;

import java.util.HashMap;
import source.ObjectBdd;

public class TypePrestation extends ObjectBdd {

    String idTypePrestation;
    String typePrestation;
    Integer isFixePrestation;
    HashMap<String, String> jisFixePrestation;

    public String getIdTypePrestation() {
        return idTypePrestation;
    }

    public void setIdTypePrestation(String idTypePrestation) {
        this.idTypePrestation = idTypePrestation;
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

    public HashMap<String, String> getJisFixePrestation() throws Exception {
        if (jisFixePrestation == null) {
            HashMap<String, String> data = new HashMap<String, String>() {
                {

                    this.put("1", "oui");
                    this.put("0", "non");

                }
            };
            jisFixePrestation = data;
        }
        return jisFixePrestation;
    }

    @Override
    public String getIdName() {
        return "idTypePrestation";
    }
}
