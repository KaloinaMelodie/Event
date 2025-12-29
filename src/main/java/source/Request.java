/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package source;

import connexion.EtablirConnex;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Vector;
import java.util.regex.Pattern;

/**
 *
 * @author kaloina.melo
 */
public class Request {

    public static String makeWhere(int levelField, Reflection reflect, ObjectBdd obj) throws Exception {
        int key = levelField;
        Field[] lf = null;
        try {
            lf = reflect.getFieldsLevel().get(key);
        } catch (NullPointerException npe) {
            key = 0;
            lf = reflect.getFieldsLevel().get(0);
        }

        String[] ls;
        ArrayList<String> al = new ArrayList();
        for (Field f : lf) {
            // if j...
            if (f.getName().startsWith("j") & obj.getFieldByName(f.getName().replaceFirst("j", "")) != null) {
                continue;
            }
            // if private
            if (Modifier.isPrivate(f.getModifiers())) {
                continue;
            }
            Optional optionalStr = Optional.ofNullable(obj.getFieldValue(f));
            String value = (optionalStr.isPresent()) ? optionalStr.get().toString() : null;
            if (value != null) {
                al.add(" and " + fixeString(f.getName()) + " = " + treatString(value, f.getType()));
            }
            //System.out.println(f);
        }
        ls = al.toArray(new String[al.size()]);
        String concat = String.join("", ls);
        return concat;
    }

    public static String treatString(String str, Class fieldType) {
        if (str == null) {
            return str;
        }
        //if it's a number
        if (fieldType.getSuperclass().equals(Number.class)) {
            return str;
        }
        //if it's a string and not found in the "Constante.strWithoutQuote"
        List<String> list = Arrays.asList(Constante.strWithoutQuote);
        //trim+upper formattage donnee
        return (!list.contains(str.trim().toUpperCase())) ? "'" + str.trim()/*.toUpperCase()*/ + "'" : str;
    }

    public static String fixeString(String str) {
        return (Pattern.compile("[A-Z]").matcher(str).find()) ? "\"" + str + "\"" : str;
    }

    public static void main(String[] gygy) throws Exception {
//        Emp emp = new Emp();
//        emp.setEmpNo("EMP1");
//        //emp.setEname("Ename");
//        Request.makeWhere(0,new Reflection(Emp.class),emp);
//        System.out.println(Request.decodeString("&gt;&#8805;"));
//        Request.treatString("",);
//        System.out.println(Double.class.getSuperclass().equals(Number.class));
//        Base.hash("Hello, world!", "MD5");
//        String str = "123.0d";
//        try {
//            double num = Double.parseDouble(str);
//            System.out.println(str + " is a number");
//        } catch (NumberFormatException e) {
//            System.out.println(str + " is not a number");
//        }

    }
}
