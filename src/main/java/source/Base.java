package source;

import java.lang.reflect.*;
import java.util.*;
import java.sql.*;
import connexion.*;
import source.*;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import javax.swing.JComboBox;
import org.postgresql.util.PGInterval;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Base {

    public static String formatD(Double d) {
        return (d != null) ? String.format("%,.2f", d) : "";
    }

    public static String formatDec(BigDecimal bd) throws Exception {
        DecimalFormat decimalFormat = new DecimalFormat("#,###.000");
        return (bd != null) ? decimalFormat.format(bd) : "";
    }

    public static String maj(String s) {
        return s.substring(0, 1).toUpperCase().concat(s.substring(1));
    }

    public static String makePk(String indexPk, int intPart, int lenghtIntPart) {
        String retour = String.valueOf(intPart);
        if (indexPk != null) {
            //fill between indexPk & intPart with 0
            String integer = String.valueOf(intPart);
            for (int i = 0; i < (lenghtIntPart - String.valueOf(intPart).length()); i++) {
                integer = "0" + integer;
            }
            retour = indexPk + integer;
        } else {
            retour = Integer.toString(intPart);
        }
        return retour;
    }

    public static Duration PGIntervalToDuration(PGInterval interval) {
        Period period = Period.of(1, 2, 3);

// calculate the total number of months in the period
        long totalMonths = period.toTotalMonths();

// calculate the equivalent duration in seconds
        Duration dur = Duration.ofSeconds(totalMonths * 30 * 24 * 60 * 60);
        System.out.println("dur " + dur);
        Duration duration = Duration.ofDays(interval.getDays())
                .plusHours(interval.getHours())
                .plusMinutes(interval.getMinutes())
                .plusSeconds((long) interval.getSeconds());

        return duration;
    }

    public static boolean isNumeric(String str) {
        try {
            double num = Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static String nameFormat(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }

    // MD5 || SHA-1
    public static String hash(String str, String cryptofunc) {
        String md5 = null;
        try {
            MessageDigest md = MessageDigest.getInstance(cryptofunc);
            md.update(str.getBytes());
            byte[] digest = md.digest();
            md5 = bytesToHex(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return md5;
    }

    public static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    public static Field getFieldWithAnnotation(Class clss, Class annotationClass) throws Exception {
        Field[] fields = clss.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(annotationClass)) {
                return field;
            }
        }
        return null;
    }

    public static int getSc(String sc) throws Exception {
        int retour = 0;
        Connection co = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        String sqlseq = EtablirConnex.getProps().getProperty("sqlseq");

        try {
            if (sqlseq.length() != 0) {
                co = (new EtablirConnex()).getConnection();
                stat = co.prepareStatement(sqlseq);
                stat.setString(1, sc);
                rs = stat.executeQuery();
                while (rs.next()) {
                    retour = rs.getInt(1);
                }
            }
            return retour;
        } catch (Exception e) {
            throw e;
        } finally {
            if (stat != null) {
                stat.close();
            }
            if (rs != null) {
                rs.close();
            }
            if (co != null) {
                co.close();
                System.gc();
            }

        }
    }

    public static void removeAllChildElements(Element element) {
        NodeList childNodes = element.getChildNodes();

        // Iterate through the child nodes in reverse order
        for (int i = childNodes.getLength() - 1; i >= 0; i--) {
            Node childNode = childNodes.item(i);

            // Remove the child element if it is an element node
            if (childNode.getNodeType() == Node.ELEMENT_NODE) {
                element.removeChild(childNode);
            }
        }
    }

    public static String convertToDateTimeFormat(String inputDateTime) {
        // Define input formats
        String[] inputFormats = {"yyyy-MM-dd'T'HH:mm", "yyyy-MM-dd HH:mm"};

        // Define output format
        String outputFormat = "yyyy-MM-dd HH:mm:ss";

        // Iterate through input formats
        for (String format : inputFormats) {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            try {
                // Parse the input string to a Date object
                java.util.Date date = sdf.parse(inputDateTime);

                // Create a new SimpleDateFormat for the output format
                SimpleDateFormat outputSdf = new SimpleDateFormat(outputFormat);

                // Format the Date object to the output format
                return outputSdf.format(date);
            } catch (ParseException e) {
                // Ignore the exception and try the next format
            }
        }

        // Return the original input if no matching format is found
        return inputDateTime;
    }

    public static String dateToString(java.util.Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = formatter.format(date);
        return formattedDate;
    }

    public static String addSecondsIfNeeded(String timeStr) {
        if (timeStr.length() == 5) { // format hh:mm
            timeStr += ":00"; // add :ss component
        }
        return timeStr;
    }

    public static int diff_daty(String date1, String date2) throws Exception {
        Connection co = (new EtablirConnex()).getConnection();
        String sql = "select to_date('" + date2 + "','yyyy-mm-dd') - to_date('" + date1 + "','yyyy-mm-dd') ";
        PreparedStatement stat = co.prepareStatement(sql);
        ResultSet rs = stat.executeQuery();
        SetResult sr = new SetResult(rs, sql);
        Object[][] data = sr.getData(sr.nbrColumn(), sr.nbrData());
        stat.close();
        co.close();
        return Integer.valueOf(data[0][0].toString());
    }

    public static String datyPlus(String date, int nbreJour) throws Exception {
        Connection co = (new EtablirConnex()).getConnection();
        String sql = "select to_date('" + date + "','yyyy-mm-dd')+" + nbreJour;
        PreparedStatement stat = co.prepareStatement(sql);
        System.out.println(stat.toString());
        ResultSet rs = stat.executeQuery();
        SetResult sr = new SetResult(rs, sql);
        Object[][] data = sr.getData(sr.nbrColumn(), sr.nbrData());
        stat.close();
        co.close();
        return data[0][0].toString();
    }

    public static java.sql.Date stringToDate(String daty) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            java.sql.Date date = new java.sql.Date(sdf.parse(daty).getTime());
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static java.sql.Timestamp stringToDateTime(String daty) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd;HH:mm");
        try {
            java.sql.Timestamp date = new java.sql.Timestamp(sdf.parse(daty).getTime());
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String dateTimeUtilToSql(String date) {
        String daty = date.split("T")[0];
        String time = date.split("T")[1];
        return daty + ";" + time;
    }

    public static String getDaty(int weekOfYear, int dayOfWeek, int year) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.WEEK_OF_YEAR, weekOfYear);
        cal.set(Calendar.DAY_OF_WEEK, dayOfWeek);
        cal.set(Calendar.YEAR, year);
        return sdf.format(cal.getTime()).toString();
    }

    public static int getYear(String daty) {
        return Integer.valueOf(daty.split("-")[0]);
    }

    public static int getMois(String daty) {
        return Integer.valueOf(daty.split("-")[1]);
    }

    public static String dateAddTime(String date, String time) throws Exception {
        String sql = "select date '" + date + "' + time '" + time + "'";
        String retour = null;
        Connection co = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            co = (new EtablirConnex()).getConnection();
            stat = co.prepareStatement(sql);
            rs = stat.executeQuery();
            SetResult sr = new SetResult(rs, sql);
            Object[][] data = sr.getData(sr.nbrColumn(), sr.nbrData());
            retour = data[0][0].toString();
        } catch (Exception e) {
            throw e;
        } finally {
            co.close();
            stat.close();
            rs.close();
        }
        return retour;
    }

    public static String getTimeFromDateTime(String datetime) throws Exception {
        String sql = "select '" + datetime + "'::time";
        String retour = null;
        Connection co = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            co = (new EtablirConnex()).getConnection();
            stat = co.prepareStatement(sql);
            rs = stat.executeQuery();
            SetResult sr = new SetResult(rs, sql);
            Object[][] data = sr.getData(sr.nbrColumn(), sr.nbrData());
            retour = data[0][0].toString();
        } catch (Exception e) {
            throw e;
        } finally {
            co.close();
            stat.close();
            rs.close();
        }
        return retour;
    }

    public static float[] YMD(String date1, String date2) throws Exception {
        Connection co = (new EtablirConnex()).getConnection();
        String sql = "SELECT DATE_PART('year', AGE('" + date1 + "', '" + date2 + "')) AS years, DATE_PART('month', AGE('" + date1 + "', '" + date2 + "')) AS months,DATE_PART('day', AGE('" + date1 + "', '" + date2 + "')) AS days";

        PreparedStatement stat = co.prepareStatement(sql);
        System.out.println(stat.toString());
        ResultSet rs = stat.executeQuery();
        SetResult sr = new SetResult(rs, sql);
        Object[][] data = sr.getData(sr.nbrColumn(), sr.nbrData());
        stat.close();
        co.close();
        float[] retour = {Float.valueOf(data[0][0].toString()), Float.valueOf(data[0][1].toString()), Float.valueOf(data[0][2].toString())};
        return retour;
    }

    public static float timeToFloat(String time) {
        int hour = Integer.valueOf(Base.splity(time, ":", 0));
        int min = Integer.valueOf(Base.splity(time, ":", 1));
        return hour + (Float.valueOf(min) / 60);
    }

    public static String timeMoinsTime(String a, String b) throws Exception {
        String sql = "select time '" + a + "' - time '" + b + "'";
        System.out.println(sql);
        String retour = null;
        Connection co = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            co = (new EtablirConnex()).getConnection();
            stat = co.prepareStatement(sql);
            rs = stat.executeQuery();
            SetResult sr = new SetResult(rs, sql);
            Object[][] data = sr.getData(sr.nbrColumn(), sr.nbrData());
            retour = data[0][0].toString();
        } catch (Exception e) {
            throw e;
        } finally {
            co.close();
            stat.close();
            rs.close();
        }
        return retour;

    }

    public static String splity(String s, String by, int id) {
        return s.split(by)[id];
    }

    public static String timePlusTime(String time1, String time2) throws Exception {
        String retour = null;
        int[] tmp1 = {Integer.valueOf(splity(time1, ":", 0)), Integer.valueOf(splity(time1, ":", 1)), Integer.valueOf(splity(time1, ":", 2))};
        int[] tmp2 = {Integer.valueOf(splity(time2, ":", 0)), Integer.valueOf(splity(time2, ":", 1)), Integer.valueOf(splity(time2, ":", 2))};
        int[] tmp = {0, 0, 0};

        for (int i = 2; i >= 0; i--) {
            while (tmp[i] != tmp1[i]) {
                tmp2[i]++;
                tmp[i]++;
                if (i == 2 & tmp2[2] == 60) {
                    tmp2[2] = 0;
                    tmp2[1]++;
                }
                if ((i == 2 || i == 1) & tmp2[1] == 60) {
                    tmp2[1] = 0;
                    tmp2[0]++;
                }

            }
        }
        String[] t = {"", "", ""};
        for (int i = 0; i < 3; i++) {
            t[i] = Integer.toString(tmp2[i]);
            if (Integer.toString(tmp2[i]).length() == 1) {
                t[i] = "0" + Integer.toString(tmp2[i]);
            }
        }
        return t[0] + ":" + t[1] + ":" + t[2];
    }

    public static String getDateTimePlusTime(String dateTime, String time) throws Exception {
        String sql = "select timestamp '" + dateTime + "' + time '" + time + "'";
        String retour = null;
        Connection co = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            co = (new EtablirConnex()).getConnection();
            stat = co.prepareStatement(sql);
            rs = stat.executeQuery();
            SetResult sr = new SetResult(rs, sql);
            Object[][] data = sr.getData(sr.nbrColumn(), sr.nbrData());
            retour = data[0][0].toString();
        } catch (Exception e) {
            throw e;
        } finally {
            co.close();
            stat.close();
            rs.close();
        }
        return retour;
    }

    public int getWeekOfYear(String daty) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(stringToDate(daty));
        return cal.get(Calendar.WEEK_OF_YEAR);
    }

//    public Object hashToObj(HashMap content,Class cls)throws Exception{
//        Object obj=cls.newInstance();
//        for(Object ob : content.entrySet()) {
//            Map.Entry<String, String> entry=(Map.Entry<String, String>)ob;
//            System.out.println(entry.toString());
//                try{
//                    String value= String.valueOf(entry.getValue());
//                    String key=entry.getKey();
//                     Field f=null;
//                    try{
//                       f=cls.getDeclaredField(key);
//                    }catch(java.lang.NoSuchFieldException nsfe){
//                        f=cls.getSuperclass().getDeclaredField(key);
//                        continue;// rehefa id le type de lasa manao nullpointer ny eo @ 593 satria tokony method rehefa superclass fa tsy declaredmethod
//                    }
//                    Class clsf=f.getType();
//                    if(clsf.equals(String.class)){
//                        getRightMethod(cls.getMethods() ,"set",maj(key)).invoke(obj,value);
//                    }
//                    else{if(isNumeric(value)){
//                        if(intable(value)){
//                            cls.getMethod( "set"+maj(key),int.class).invoke(obj,Integer.valueOf(value));
//                        }else{
//                            getRightMethod(cls.getMethods() ,"set",maj(key)).invoke(obj,Double.valueOf(value));
//                        }
//                    }}
//                }catch(Exception ex){ex.printStackTrace();System.out.println("ENTRYYY ERROR "+entry.toString());throw ex;}
//
//            }
//        return obj;
//    }
//    public ArrayList mapToArray(ArrayList l,Class cls) throws Exception{
//        ArrayList retour=new ArrayList();
//
//        for(Object obj:l){            
//            HashMap content=(HashMap)obj;     
//            retour.add(hashToObj(content,cls));
//        }
//        return retour;
//    }
    public static Map<String, String[]> cloneMap(Map<String, String[]> main) {
        HashMap<String, String[]> clonedMap = new HashMap<>();
        for (Map.Entry<String, String[]> entry : main.entrySet()) {
            String key = entry.getKey();
            String[] value = entry.getValue();
            String[] clonedValue = Arrays.copyOf(value, value.length);
            clonedMap.put(key, clonedValue);
        }
        return clonedMap;
    }
}
