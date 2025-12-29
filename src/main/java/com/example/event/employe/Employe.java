package com.example.event.employe;

import annots.IdentLog;
import annots.MdpLog;
import connexion.EtablirConnex;
import exception.NotAuthorizedException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.Timestamp;
import source.Base;
import source.ObjectBdd;
import utils.DefaultLog;

public class Employe extends DefaultLog {

    String idEmploye;
    String nom;
    String prenom;
    @IdentLog(error = "The email address you entered is not valid. Please check it and try again.")   
    String identifiant;
    @MdpLog(cryptofunc = "MD5")
    String mdp;
    String token;
    Timestamp dateexpiration;
    Timestamp datedeconnect;
    
    @Override
    public void create(Connection co) throws Exception {
        if (this.getMdp() != null) {
            setMdp(Base.hash(getMdp(), "MD5"));
        }
        super.create(co);
    }
    
    public Employe login() throws Exception {
        Connection con = null;
        Statement stat = null;

        try {
            con = EtablirConnex.getConnection();
            con.setAutoCommit(false);
            //check validite login            
            Employe temp = (Employe) this.checkIdentMdp(null, "Oops! Your username or password is incorrect. Please try again.");
            //set dateexpiration
            String sql = "update employe set dateexpiration=current_timestamp+'00:10:00' where \"idEmploye\" ='" + temp.getIdEmploye() + "'";
            System.out.println(sql);
            stat = con.createStatement();
            stat.executeUpdate(sql);
            //generate token
            temp = (Employe) this.select(null, "select * from employe where \"idEmploye\" ='" + temp.getIdEmploye() + "'").get(0);
            String currentexpiration = temp.getDateexpiration().toString();
            String newtoken = Base.hash(this.getIdEmploye() + currentexpiration, "SHA-1");
            String sql1 = "update employe set token='" + newtoken + "' where \"idEmploye\" ='" + temp.getIdEmploye() + "'";
            stat = con.createStatement();
            System.out.println(sql1);
            stat.executeUpdate(sql1);
            con.commit();
            temp.setToken(newtoken);
            return temp;
        } catch (Exception ex) {
            con.rollback();
            throw ex;
        } finally {
            if (stat != null) {
                stat.close();
            }
            con.close(); System.gc();
        }
    }    

    public void logout() throws Exception {
        Connection con = EtablirConnex.getConnection();
        Statement stat = null;
        
        String sql = "update employe set token='', datedeconnect = current_timestamp where \"idEmploye\" ='"+this.getIdEmploye()+"'";
        try {
            stat = con.createStatement();
            System.out.println(sql);
            stat.executeUpdate(sql);
        } catch (Exception e) {
            throw e;
        } finally {
            if (stat != null) {
                stat.close();
            }
            con.close(); System.gc();
        }
    }
    
    public void checkconnex()throws Exception{
        String token = this.getToken().replace("Bearer ", "");
        System.out.println(token);
        String sql="SELECT * from employe where  token='"+token+"' and dateexpiration>current_timestamp";
        Connection con = EtablirConnex.getConnection();
        try {
            System.out.println(sql);
            if(new Employe().select(con, sql).isEmpty()){
                throw new NotAuthorizedException("Session timeout. Please reconnect !", "201");
            }
        } catch (Exception e) {
            throw e;
        } finally {
            con.close(); System.gc();
        }        
    }

    public String getIdEmploye() {
        return idEmploye;
    }

    public void setIdEmploye(String idEmploye) {
        this.idEmploye = idEmploye;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Timestamp getDateexpiration() {
        return dateexpiration;
    }

    public void setDateexpiration(Timestamp dateexpiration) {
        this.dateexpiration = dateexpiration;
    }

    public Timestamp getDatedeconnect() {
        return datedeconnect;
    }

    public void setDatedeconnect(Timestamp datedeconnect) {
        this.datedeconnect = datedeconnect;
    }
    

    @Override
    public String getIdName() {
        return "idEmploye";
    }
}
