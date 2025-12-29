package com.example.event.spectacle;

import java.sql.Timestamp;
import source.ObjectBdd;

public class Spectacle extends ObjectBdd {

    String idSpectacle;
    String titre;
    Timestamp dateHeure;

    public String getIdSpectacle() {
        return idSpectacle;
    }

    public void setIdSpectacle(String idSpectacle) {
        this.idSpectacle = idSpectacle;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Timestamp getDateHeure() {
        return dateHeure;
    }

    public void setDateHeure(Timestamp dateHeure) {
        this.dateHeure = dateHeure;
    }

    @Override
    public String getIdName() {
        return "idSpectacle";
    }

    @Override
    public String getIndexPk() {
        return "SPC";
    }

    @Override
    public String toString() {
        return "Spectacle{" + "idSpectacle=" + idSpectacle + ", titre=" + titre + ", dateHeure=" + dateHeure + '}';
    }
    

}
