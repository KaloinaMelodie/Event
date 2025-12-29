/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generate;

import connexion.EtablirConnex;
import connexion.SetResult;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import source.Base;
import source.Col;

/**
 *
 * @author kaloina.melo
 */
public class GenerateCls {

    public static String generate(String pack, String tname, String idname, Connection co) throws Exception {
        String file = "package "+pack+";" + "\n";
        file  += "import source.ObjectBdd;\n";
        file += "public class " + Base.maj(tname) + " extends ObjectBdd{\n";
        boolean isCoNull = false;
        if (co == null) {
            isCoNull = true;
            co = EtablirConnex.getConnection();
        }
        Statement stat = null;
        ResultSet rs = null;
        try {
            String sql = "select * from " + tname;
            stat = co.createStatement();
            rs = stat.executeQuery(sql);
            SetResult sr = new SetResult(rs, sql);
            for (Col col : sr.getListCol()) {
                file += Class.forName(col.getTypejava()).getSimpleName() + " " + col.getColname() + ";\n";
            }
            file += "\n @Override\n"
                    + "    public String getIdName() {\n"
                    + "        return \"" + idname + "\";\n"
                    + "    }\n"
                    + "}";
            return file;
        } finally {
            if (stat != null) {
                stat.close();
            }
            if (rs != null) {
                rs.close();
            }
            if (isCoNull && co != null) {
                co.close();
                System.gc();
            }
        }
    }

    public static void main(String[] gygy) throws Exception {
        String cls = "v_categorieDevis";
        String lowerCls = Character.toLowerCase(cls.charAt(0)) + cls.substring(1);
        System.out.println(GenerateCls.generate("com.example.event."+lowerCls,cls, "idDevis", null));
    }
}
