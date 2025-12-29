package com.example.event.devis;

import com.example.event.categoriePlaceLieuDevis.CategoriePlaceLieuDevis;
import com.example.event.lieu.Lieu;
import com.example.event.prestation.Prestation;
import com.example.event.sousDevis.SousDevis;
import com.example.event.spectacle.Spectacle;
import com.example.event.typePrestation.TypePrestation;
import com.example.event.v_categorieplacelieudevis.V_categoriePlaceLieuDevis;
import com.example.event.v_prestation.V_prestation;
import connexion.EtablirConnex;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import source.ObjectBdd;

/**
 *
 * @author patricia.razafimboah
 */
public class DevisService {

    public static void create(ObjectBdd obj, Connection co) throws Exception {
        obj.create(co);
    }

    public static void update(ObjectBdd obj, Connection co) throws Exception {
        obj.update(co);
    }

    public static void delete(ObjectBdd obj, Connection co) throws Exception {
        obj.delete(co);
    }

    public static HashMap<String, ArrayList<V_prestation>> selectListTypeV_prestation(FicheDevis fiche, Connection co) throws Exception {
        ArrayList<TypePrestation> list = new TypePrestation().select(co, null);
        HashMap<String, ArrayList<V_prestation>> data = new HashMap<>();
        for (TypePrestation type : list) {
            String sql = "select * from v_prestation where \"idTypePrestation\" = '" + type.getIdTypePrestation() + "' and \"idPrestation\" not in (select \"idPrestation\" from sousdevis where \"idDevis\" = '" + fiche.getIdDevis() + "' and \"idPrestation\" is not null)";
            data.put(type.getIdTypePrestation(), new V_prestation().select(co, sql));
        }
        return data;
    }

    public static HashMap<String, ArrayList<V_categoriePlaceLieuDevis>> selectListTypeV_placeLieuDevis(FicheDevis fiche, Connection co) throws Exception {
        ArrayList<SousDevis> list = new SousDevis().select(co, "select * from sousdevis where \"idLieu\" is not null and \"idDevis\" = '" + fiche.getIdDevis() + "'");
        HashMap<String, ArrayList<V_categoriePlaceLieuDevis>> data = new HashMap<>();
        for (SousDevis sousDevis : list) {
            String sql = "select * from V_categoriePlaceLieuDevis where \"idDevis\" = '" + sousDevis.getIdDevis() + "' and \"idLieu\" = '" + sousDevis.getIdLieu() + "'";
            data.put(sousDevis.getIdLieu(), new V_categoriePlaceLieuDevis().select(co, sql));
        }
        return data;
    }

    public static HashMap<String, Double> selectListTypeSousDevis(FicheDevis fiche, Connection co) throws Exception {
        HashMap<String, Double> data = new HashMap<>();
        for (SousDevis sous : fiche.getListeSousDevis()) {
            if (sous.getIdArtiste() != null) {
                if (data.get("Artiste") != null) {
                    data.put("Artiste", data.get("Artiste") + sous.getMontant());
                } else {
                    data.put("Artiste", sous.getMontant());
                }
            }
            if (sous.getIdLieu() != null) {
                if (data.get("Lieu") != null) {
                    data.put("Lieu", data.get("Lieu") + sous.getMontant());
                } else {
                    data.put("Lieu", sous.getMontant());
                }
            }
            if (sous.getIdPrestation() != null) {
                Prestation prestation = new Prestation();
                prestation.setIdPrestation(sous.getIdPrestation());
                prestation = (Prestation) prestation.findById();
                String type = prestation.getJidTypePrestation().get(prestation.getIdTypePrestation());
                if (data.get(type) != null) {
                    data.put(type, data.get(type) + sous.getMontant());
                } else {
                    data.put(type, sous.getMontant());
                }
            }
        }
        return data;
    }

    public static Devis doBis(FicheDevis fiche, Spectacle spectacle) throws Exception {
        Connection con = EtablirConnex.getConnection();
        con.setAutoCommit(false);
        //new instance devis
        Devis devis = new Devis();

        Spectacle specTaloha = new Spectacle();
        specTaloha.setIdSpectacle(fiche.getDevis().getIdSpectacle());
        specTaloha = (Spectacle) specTaloha.findById();

        // new instance spectacle avec nom+_bis
        spectacle.setTitre(specTaloha.getTitre() + "_bis");
        spectacle.generatePk();
        spectacle.create(con);

        devis.setIdSpectacle(spectacle.getIdSpectacle());
        devis.setIdEmploye(fiche.getDevis().getIdEmploye());
        devis.setIdClient(fiche.getDevis().getIdClient());
        devis.generatePk();
        devis.create(con);

        // insert list sousDevis
        for (SousDevis sous : fiche.getListeSousDevis()) {
            SousDevis newSous = new SousDevis();
            newSous = sous;
            newSous.setDesignation(sous.getDesignation() + "_bis");
            newSous.setIdDevis(devis.getIdDevis());
            newSous.setIdSousDevis(null);
            //newSous.generatePk();
            newSous.create(con);
        }

        // prix place
        String sqlV_categorie = "select * from V_categoriePlaceLieuDevis where \"idDevis\"= '"+fiche.getIdDevis()+"'";
        System.out.println(sqlV_categorie);
            for (Object obj : new V_categoriePlaceLieuDevis().select(null, sqlV_categorie)) {
                V_categoriePlaceLieuDevis v_categorie = (V_categoriePlaceLieuDevis) obj;
                CategoriePlaceLieuDevis categoriePlace = new CategoriePlaceLieuDevis();
                categoriePlace.setIdLieu(v_categorie.getIdLieu());
                categoriePlace.setIdCategoriePlace(v_categorie.getIdCategoriePlace());
                categoriePlace.setPrix(v_categorie.getPrix());
                categoriePlace.setNbrePlaceVendu(0);
                categoriePlace.setIdDevis(devis.getIdDevis());
                categoriePlace.create(con);
            }
        

        con.commit();
        con.close();
        return devis;
    }

    public static void main(String[] gygy) throws Exception {
        FicheDevis fiche = new FicheDevis("DEV000003");
        HashMap<String, ArrayList<V_prestation>> list = selectListTypeV_prestation(fiche, null);
        for (Map.Entry<String, ArrayList<V_prestation>> entry : list.entrySet()) {
            System.out.println(entry.getValue());
        }
    }
}
