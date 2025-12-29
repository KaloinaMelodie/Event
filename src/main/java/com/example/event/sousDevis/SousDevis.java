package com.example.event.sousDevis;

import com.example.event.client.Client;
import com.example.event.lieu.Lieu;
import com.example.event.prestation.Prestation;
import com.example.event.uniteTarif.UniteTarif;
import com.example.event.v_artiste.V_artiste;
import com.example.event.v_lieu.V_lieu;
import com.example.event.v_prestation.V_prestation;
import java.util.ArrayList;
import java.util.HashMap;
import source.ObjectBdd;

public class SousDevis extends ObjectBdd {

    String idSousDevis;
    String idDevis;
    String designation;
    String idPrestation;
    String idArtiste;
    String idLieu;
    Double prix;
    Double quantite;
    Double duree;
    private V_prestation prestation;
    private V_artiste artiste;
    private V_lieu lieu;
    HashMap<String, String> jidLieu;

    public String getIdSousDevis() {
        return idSousDevis;
    }

    public void setIdSousDevis(String idSousDevis) {
        this.idSousDevis = idSousDevis;
    }

    public String getIdDevis() {
        return idDevis;
    }

    public void setIdDevis(String idDevis) {
        this.idDevis = idDevis;
    }

    public String getDesignation() throws Exception {
        if (this.getIdPrestation() != null) {
            V_prestation prest = getPrestation();
            return prest.getPrestation() + " " + ((prest.getIdTypeTarif() != null) ? new Prestation().getJidTypeTarif().get(prest.getIdTypeTarif().toString()) : "");
        }
        if (this.getIdArtiste() != null) {
            V_artiste art = getArtiste();
            return art.getNomArtiste();
        }
        if (this.getIdLieu() != null) {
            V_lieu lieu = getLieu();
            return lieu.getNomLieu();
        }
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getIdPrestation() {
        return idPrestation;
    }

    public void setIdPrestation(String idPrestation) {
        this.idPrestation = idPrestation;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public Double getQuantite() {
        return quantite;
    }

    public void setQuantite(Double quantite) {
        this.quantite = quantite;
    }

    public Double getDuree() {
        return duree;
    }

    public void setDuree(Double duree) {
        this.duree = duree;
    }

    public String getIdArtiste() {
        return idArtiste;
    }

    public void setIdArtiste(String idArtiste) {
        this.idArtiste = idArtiste;
    }

    public String getIdLieu() {
        return idLieu;
    }

    public void setIdLieu(String idLieu) {
        this.idLieu = idLieu;
    }

    public V_prestation getPrestation() throws Exception {
        if (prestation == null && this.getIdPrestation() != null) {
            prestation = new V_prestation();
            prestation.setIdPrestation(this.getIdPrestation());
            prestation = (V_prestation) prestation.findById();
        }
        return prestation;
    }

    public void setPrestation(V_prestation prestation) {
        this.prestation = prestation;
    }

    public V_artiste getArtiste() throws Exception {
        if (artiste == null && this.getIdArtiste() != null) {
            artiste = new V_artiste();
            artiste.setIdArtiste(this.getIdArtiste());
            artiste = (V_artiste) artiste.findById();
        }
        return artiste;
    }

    public V_lieu getLieu() throws Exception {
        if (lieu == null && this.getIdLieu() != null) {
            lieu = new V_lieu();
            lieu.setIdLieu(this.getIdLieu());
            lieu = (V_lieu) lieu.findById();
        }
        return lieu;
    }

    public double getMontant() throws Exception {
        double montant = 0;
        // misy prestation
        if (this.getIdPrestation() != null) {
            V_prestation prest = getPrestation();
            if (prest.getIsFixe() == 1) {
                montant = this.getPrix() * this.getQuantite() * this.getDuree();
            } else {
                montant = this.getPrix() * this.getQuantite();
            }
        }
        // misy artiste
        if (this.getIdArtiste() != null) {
            // V_artiste artiste = getArtiste();
            montant = this.getPrix() * this.getQuantite() * this.getDuree();
        }
        if (this.getIdLieu() != null) {
            // V_artiste artiste = getArtiste();
            montant = this.getPrix() * this.getQuantite();
        }
        return montant;
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

    @Override
    public String getIdName() {
        return "idSousDevis";
    }

    @Override
    public String getIndexPk() {
        return "SDV";
    }

}
