/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import annots.IdentLog;
import annots.MdpLog;
import connexion.EtablirConnex;
import exception.LogException;
import java.lang.reflect.Field;
import java.sql.Connection;
import source.Base;
import source.ObjectBdd;

/**
 *
 * @author patricia.razafimboah The default login checker utilizes the MD5
 * cryptographic method for encrypting passwords stored in the database.
 */
public abstract class DefaultLog extends ObjectBdd {

    public void checkLog() throws Exception {
        //check validite login
        //set dateexpiration
        //generate token
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public DefaultLog checkIdent(Connection co) throws Exception {
        boolean isCoNull = false;
        if (co == null) {
            isCoNull = true;
            co = EtablirConnex.getConnection();
        }
        try {
            Field ident = Base.getFieldWithAnnotation(this.getClass(), IdentLog.class);
            System.out.println(this.getFieldValue(ident));
            ObjectBdd clone = this.getClass().newInstance();
            clone.setFieldValue(ident, this.getFieldValue(ident).toString().toLowerCase(), null);
            if (clone.select(null, null).isEmpty()) {
                String messerror = ident.getAnnotation(IdentLog.class).error();
                throw new LogException(messerror);
            }
        } finally {
            if (isCoNull && co != null) {
                co.close();
                System.gc();
            }
        }
        return this;
    }

    public DefaultLog checkMdp(Connection co) throws Exception {
        boolean isCoNull = false;
        if (co == null) {
            isCoNull = true;
            co = EtablirConnex.getConnection();
        }
        DefaultLog clone = null;
        try {
            Field ident = Base.getFieldWithAnnotation(this.getClass(), IdentLog.class);
            Field mdp = Base.getFieldWithAnnotation(this.getClass(), MdpLog.class);
            System.out.println(this.getFieldValue(mdp));
            clone = this.getClass().newInstance();
            clone.setFieldValue(ident, this.getFieldValue(ident), null);
            String hashMdp = Base.hash(this.getFieldValue(mdp).toString(), mdp.getAnnotation(MdpLog.class).cryptofunc());
            System.out.println(hashMdp);
            clone.setFieldValue(mdp, hashMdp, null);
            if (clone.select(null, null).isEmpty()) {
                String messerror = mdp.getAnnotation(MdpLog.class).error();
                throw new LogException(messerror);
            }
            clone = (DefaultLog)clone.select(null, null).get(0);
        } finally {
            if (isCoNull && co != null) {
                co.close();
                System.gc();
            }
        }
        return clone;
    }

    public DefaultLog checkIdentMdp(Connection co, String errormessage) throws Exception {
        boolean isCoNull = false;
        if (co == null) {
            isCoNull = true;
            co = EtablirConnex.getConnection();
        }
        DefaultLog clone = null;
        try {
            clone = this.checkIdent(co).checkMdp(co);
        } catch (LogException le) {
            throw new LogException(errormessage);
        } finally {
            if (isCoNull && co != null) {
                co.close();
                System.gc();
            }
        }
        return clone;
    }
}
