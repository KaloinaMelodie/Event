/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.event.devis;

import com.example.event.sousDevis.SousDevis;
import com.example.event.spectacle.Spectacle;
import com.example.event.taxe.Taxe;
import com.example.event.v_categorieDevis.V_categorieDevis;
import com.example.event.v_categoriePlaceDevis.V_categoriePlaceDevis;
import java.util.ArrayList;

/**
 *
 * @author kaloina.melo
 */
public class FicheDevis {

    String idDevis;
    Devis devis;
    Boolean hasLieu;
    ArrayList<SousDevis> listeSousDevis;
    ArrayList<V_categoriePlaceDevis> listePlaceDevis;
    ArrayList<V_categorieDevis> listeCategorieDevis;    

    private Double recetteProvisoire;
    private Double recetteVrai;
    private Taxe taxe;
    private Spectacle spectacle;

    public FicheDevis(String idDevis) {
        this.idDevis = idDevis;
    }

    public String getIdDevis() {
        return idDevis;
    }

    public void setIdDevis(String idDevis) {
        this.idDevis = idDevis;
    }

    public ArrayList<V_categoriePlaceDevis> getListePlaceDevis() throws Exception {
        if (listePlaceDevis == null) {
            listePlaceDevis = new V_categoriePlaceDevis().select(null, "select * from v_categorieplacedevis where \"idDevis\" = '" + this.getIdDevis() + "'");
        }
        return listePlaceDevis;
    }

    // Calcul recette sum(nbreplace*prixPlace) = colonne montant @ V_categoriePlaceDevis
    public double getRecetteProvisoire() throws Exception {
        if (recetteProvisoire == null) {
            recetteProvisoire = 0d;
            for (V_categorieDevis v_categorieDevis : getListeCategorieDevis()) {
                recetteProvisoire += v_categorieDevis.getMontantTotal();
            }

        }
        return recetteProvisoire;
    }

    public double getRecetteVrai() throws Exception {
        if (recetteVrai == null) {
            recetteVrai = 0d;
            for (V_categorieDevis v_categorieDevis : getListeCategorieDevis()) {
                recetteVrai += v_categorieDevis.getMontantTotalVrai();
            }

        }
        return recetteVrai;
    }

    public void setListePlaceDevis(ArrayList<V_categoriePlaceDevis> listePlaceDevis) {
        this.listePlaceDevis = listePlaceDevis;
    }

    public ArrayList<SousDevis> getListeSousDevis() throws Exception {
        if (listeSousDevis == null) {
            listeSousDevis = new SousDevis().select(null, getSqlList());
        }
        return listeSousDevis;
    }

    public Devis getDevis() throws Exception {
        if (devis == null && this.getIdDevis() != null) {
            devis = new Devis();
            devis.setIdDevis(this.getIdDevis());
            devis = (Devis) devis.findById();
        }
        return devis;
    }

    public void setDevis(Devis devis) {
        this.devis = devis;
    }

    public void setListeSousDevis(ArrayList<SousDevis> listeSousDevis) {
        this.listeSousDevis = listeSousDevis;
    }

    public ArrayList<V_categorieDevis> getListeCategorieDevis() throws Exception {
        if (listeCategorieDevis == null) {
            listeCategorieDevis = new V_categorieDevis().select(null, "select * from v_categoriedevis where \"idDevis\" = '" + this.getIdDevis() + "'");
        }
        return listeCategorieDevis;
    }

    public void setListeCategorieDevis(ArrayList<V_categorieDevis> listeCategorieDevis) {
        this.listeCategorieDevis = listeCategorieDevis;
    }

    // Depense
    public double getMontantTotal() throws Exception {
        double montant = 0;
        if (this.getListeSousDevis() != null) {
            for (SousDevis sousDevis : this.getListeSousDevis()) {
                montant += sousDevis.getMontant();
            }
        }
        return montant;
    }

    public double getBeneficeProvisoire() throws Exception {
        return getRecetteProvisoire() - getMontantTotal();
    }

    public double getBeneficeVrai() throws Exception {
        return getRecetteVrai() - getMontantTotal();
    }

    public Taxe getTaxe() throws Exception {
        if (taxe == null) {
            ArrayList<Taxe> list = new Taxe().select(null, null);
            if (list.size() != 0) {
                taxe = list.get(0);
            }
        }
        return taxe;
    }

    public void setTaxe(Taxe taxe) {
        this.taxe = taxe;
    }

    public Spectacle getSpectacle() throws Exception{
        if (spectacle == null) {
            spectacle =  new Spectacle();
            spectacle.setIdSpectacle(this.getDevis().getIdSpectacle());
            spectacle = (Spectacle) spectacle.findById();
        }
        return spectacle;
    }

    public void setSpectacle(Spectacle spectacle) {
        this.spectacle = spectacle;
    }

    public double getBeneficeNet() throws Exception {
        double tax = (getTaxe() != null) ? getTaxe().getPourcentage() : 0d;
        return getBeneficeVrai() - (getBeneficeVrai() * tax);
    }

    public Boolean isHasLieu() throws Exception {
        if (hasLieu == null) {
            if (new SousDevis().select(null, "select * from sousDevis where \"idDevis\"='" + this.getIdDevis() + "' and \"idLieu\" is not null").size() == 0) {
                hasLieu = false;
                return hasLieu;
            }
            hasLieu = true;
        }
        return hasLieu;
    }

    public String getSqlList() {
        return "select * from sousdevis where \"idDevis\" = '" + this.getIdDevis() + "'";
    }

}
