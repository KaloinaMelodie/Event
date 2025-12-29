/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generate;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Month;
import source.Base;
import source.ObjectBdd;
import source.Reflection;

/**
 *
 * @author kaloina.melo
 */
public class Vonjy {

    public static String thead(ObjectBdd obj) throws Exception {
        String start = "<th>";
        String end = "</th>";
        String result = "";
        for (String s : obj.getAttrList()) {
            result += start + s + end + "\n";
        }
        System.out.println(result);
        return result;
    }

    public static String tbody(ObjectBdd obj, String objName) throws Exception {
        String start = "<td>";
        String end = "</td>";
        String result = "";
        Reflection reflection = new Reflection(obj.getClass());
        Field[] listField = reflection.getFieldsLevel().get(0);
        for (Field s : listField) {
            // if j...
            if (s.getName().startsWith("j") & obj.getFieldByName(s.getName().replaceFirst("j", "")) != null) {
                continue;
            }
            String print = objName + ".get" + Base.maj(s.getName()) + "()";
            print = (s.getType().equals(Double.class)) ? "Base.formatD(" + print + ")" : (s.getType().equals(BigDecimal.class)) ? "Base.formatDec(" + print + ")" : print;
            result += start + "<%= " + print + " %>" + end + "\n";
        }

        System.out.println(result);
        return result;
    }

    public static void main(String[] gygy) throws Exception {
        //System.out.println(String.join(",", test.getAttrList()));
        
//        Andrana obj = new Andrana();
//
//        Vonjy.thead(obj);
//        System.out.println("");
//        Vonjy.tbody(obj, "cast");

//        int monthValue = 1; // set the month value here
//        String monthString = Month.of(monthValue).name();
//        System.out.println(monthString);
//
//        String datetimeString = "2023-05-16 12:00";
//        test.setFieldValue("datetime", datetimeString, null);
//        //test.setFieldValue("time", "23:59", null);
//        System.out.println(test.getDatetime());
//
//        System.out.println(test.getDaty());
//        Timestamp timestamp = Timestamp.valueOf(datetimeString);
//        System.out.println(timestamp);
    }
}
