/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package source;

import connexion.EtablirConnex;
import connexion.SetResult;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kaloina.melo
 */
public class HashColTable extends HashMap<String, Object> {

    @Override
    public Object get(Object obj) {
        ObjectBdd bddobj = (ObjectBdd) obj;
        if (!super.containsKey(bddobj.getTname())) {
            try {
                super.put((String) bddobj.getTname(), getColOfTable(bddobj, null));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return super.get(bddobj.getTname());
    }

    public static Col[] getColOfTable(ObjectBdd bdd, Connection co) throws Exception {
        int conf = 0;
        if (co == null) {
            co = EtablirConnex.getConnection();
            conf = 1;
        }
        Statement st = null;
        ResultSet rs = null;
        try {
            st = co.createStatement();
            String sql = "select * from " + bdd.getTname();
            rs = st.executeQuery(sql);
            SetResult sr = new SetResult(rs, sql);
            Col[] retour = sr.getListCol();
            return retour;
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (st != null) {
                st.close();
            }
            if (rs != null) {
                rs.close();
            }
            if (conf == 1 && co != null) {
                co.close();
                System.gc();
            }            
        }
    }

    public static int getColtypeByColTypeName(String dataTypeName) throws Exception {
        int dataType = Types.OTHER; // default value
        if (dataTypeName.compareToIgnoreCase("int4") == 0) {
            dataTypeName = "integer";
        }
        if (dataTypeName.compareToIgnoreCase("float8") == 0) {
            dataTypeName = "double";
        }
        if (dataTypeName.compareToIgnoreCase("CHARACTER VARYING") == 0) {
            dataTypeName = "varchar";
        }
        if (dataTypeName.compareToIgnoreCase("DOUBLE PRECISION") == 0) {
            dataTypeName = "double";
        }
        Field dataTypeField = Types.class.getField(dataTypeName.toUpperCase().replace(' ', '_'));
        dataType = dataTypeField.getInt(null);

        return dataType;

    }

}
