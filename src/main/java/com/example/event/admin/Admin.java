package com.example.event.admin;

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

public class Admin extends DefaultLog {

    String idAdmin;
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
    
    public Admin login() throws Exception {
        Connection con = null;
        Statement stat = null;

        try {
            con = EtablirConnex.getConnection();
            con.setAutoCommit(false);
            //check validite login            
            Admin temp = (Admin) this.checkIdentMdp(null, "Oops! Your username or password is incorrect. Please try again.");
            //set dateexpiration
            String sql = "update admin set dateexpiration=current_timestamp+'00:10:00' where \"idAdmin\" ='" + temp.getIdAdmin() + "'";
            System.out.println(sql);
            stat = con.createStatement();
            stat.executeUpdate(sql);
            //generate token
            temp = (Admin) this.select(null, "select * from admin where \"idAdmin\" ='" + temp.getIdAdmin() + "'").get(0);
            String currentexpiration = temp.getDateexpiration().toString();
            String newtoken = Base.hash(this.getIdAdmin() + currentexpiration, "SHA-1");
            String sql1 = "update admin set token='" + newtoken + "' where \"idAdmin\" ='" + temp.getIdAdmin() + "'";
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
        
        String sql = "update admin set token='', datedeconnect = current_timestamp where \"idAdmin\" ='"+this.getIdAdmin()+"'";
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
        String sql="SELECT * from admin where  token='"+token+"' and dateexpiration>current_timestamp";
        Connection con = EtablirConnex.getConnection();
        try {
            System.out.println(sql);
            if(new Admin().select(con, sql).isEmpty()){
                throw new NotAuthorizedException("Session timeout. Please reconnect !", "201");
            }
        } catch (Exception e) {
            throw e;
        } finally {
            con.close(); System.gc();
        }        
    }

    public String getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(String idAdmin) {
        this.idAdmin = idAdmin;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = Base.nameFormat(nom);
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = Base.nameFormat(prenom);
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        // DON'T FORGET
        this.identifiant = identifiant.toLowerCase();
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
        return "idAdmin";
    }
}
