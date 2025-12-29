package com.example.event.devis;

import com.example.event.client.Client;
import com.example.event.spectacle.Spectacle;
import com.example.event.typeTarif.TypeTarif;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import source.ObjectBdd;

public class Devis extends ObjectBdd {

    String idDevis;
    String idClient;
    String idEmploye;   
    String idSpectacle;
    Timestamp create_at;
    Timestamp update_at;
    HashMap<String, String> jidClient;
    HashMap<String, String> jidSpectacle;

    public String getIdDevis() {
        return idDevis;
    }

    public void setIdDevis(String idDevis) {
        this.idDevis = idDevis;
    }

    public String getIdClient() {
        return idClient;
    }

    public void setIdClient(String idClient) {
        this.idClient = idClient;
    }

    public String getIdEmploye() {
        return idEmploye;
    }

    public void setIdEmploye(String idEmploye) {
        this.idEmploye = idEmploye;
    }

    public Timestamp getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Timestamp create_at) {
        this.create_at = create_at;
    }

    public Timestamp getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(Timestamp update_at) {
        this.update_at = update_at;
    }
    public String getIdSpectacle() {
        return idSpectacle;
    }

    public void setIdSpectacle(String idSpectacle) {
        this.idSpectacle = idSpectacle;
    }

    public HashMap<String, String> getJidClient() throws Exception {
        if (jidClient == null) {
            HashMap<String, String> data = new HashMap<String, String>() {
                {
                    ArrayList<Client> list = new Client().select(null, null);
                    for (Client unite : list) {
                        this.put(unite.getIdClient().toString(), unite.getEmail());
                    }
                }
            };
            jidClient = data;
        }
        return jidClient;
    }

    
    
    public HashMap<String, String> getJidSpectacle() throws Exception {
        if (jidSpectacle == null) {
            HashMap<String, String> data = new HashMap<String, String>() {
                {
                    // "select * from spectacle where \"idSpectacle\" not in(select \"idSpectacle\" from devis)"
                    ArrayList<Spectacle> list = new Spectacle().select(null, null);
                    for (Spectacle unite : list) {
                        this.put(unite.getIdSpectacle().toString(), unite.getTitre());
                    }
                }
            };
            jidSpectacle = data;
        }
        return jidSpectacle;
    }
    
    @Override
    public String getIdName() {
        return "idDevis";
    }

    @Override
    public String getIndexPk() {
        return "DVI"; 
    }
    
}
