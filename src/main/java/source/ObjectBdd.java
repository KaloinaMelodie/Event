/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package source;

import connexion.EtablirConnex;
import connexion.SetResult;
import exception.RequestException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import org.postgresql.util.PGInterval;
import script.Champ;
import script.Form;
import script.InputTypes;
import static source.Base.*;
import static source.Request.treatString;

/**
 *
 * @author ASUS
 * @param <T>
 */
public abstract class ObjectBdd<T extends Object> {

    String tname;
    String idName;
    String indexPk;
    String seqName;
    Integer lengthPk;

    public abstract String getIdName();

    public int getIntPart() {
        int intpart = 0;
        Object id;
        try {
            id = getId();
            if (id != null && id.getClass().equals(String.class)) {
                String strid = String.valueOf(id);
                String[] tab = strid.split("");
                for (int i = 0; i < strid.length(); i++) {
                    if (!tab[i].equals("0") && Base.isNumeric(tab[i])) {
                        intpart = Integer.valueOf(strid.substring(i));
                        break;
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return intpart;
    }

    public Object getId() throws Exception {
        return getFieldValue(this.getIdName());
    }

    public void generatePk() throws Exception {
        String indexPk = getIndexPk();
        int intPart = Base.getSc(this.getSeqName());
        int lengthPk = this.getLengthPk();
        Field idField = this.getFieldByName(this.getIdName());
        //mety foana io na type String na Integer, makePk return intPart if indexPk null
        this.setFieldValue(idField, Base.makePk(indexPk, intPart, lengthPk), null);
    }

    //override to specify
    public String getIndexPk() {
        String str = this.getClass().getSimpleName();
        indexPk = str.substring(0, 3).toUpperCase();
        return indexPk;
    }

    public void setIndexPk(String indexPk) {
        this.indexPk = indexPk;
    }

    //override to specify
    public String getSeqName() {
        seqName = "seq" + this.getClass().getSimpleName();
        return seqName;
    }

    public void setSeqName(String seqName) {
        this.seqName = seqName;
    }

    //override to specify
    public Integer getLengthPk() {
        lengthPk = 6;
        return lengthPk;
    }

    public void setLengthPk(Integer lengthPk) {
        this.lengthPk = lengthPk;
    }

    public Field[] getFields() throws Exception {
        return Reflection.getFieldList(this.getClass());
    }

    public Field getFieldByName(String colname) throws Exception {
        for (Field field : getFields()) {
            if (field.getName().compareToIgnoreCase(colname) == 0) {
                return field;
            }
        }
        return null;
    }

    public Col[] getBaseCol() {
        return (Col[]) Heaven.getListcoltable().get(this);
    }

    public Object getFieldValue(Field field) throws Exception {
        String methodName = "get" + maj(field.getName());
        return getClass().getMethod(methodName).invoke(this);
    }

    public Object getFieldValue(String colname) throws Exception {
        Field field = getFieldByName(colname);
        return getFieldValue(field);
    }

    public void setFieldValue(Field field, Object value, Class paramType) throws Exception {
        String methodName = "set" + maj(field.getName());
        Method method = null;
        if (paramType == null) {
            paramType = field.getType();
        }
        if (paramType == Integer.class) {
            Double val = Double.parseDouble(value.toString());
            value = paramType.cast(val.intValue());
        }
        if (paramType == Double.class) {
            Double val = Double.parseDouble(value.toString());
            value = val;
        }
        if (paramType == String.class) {
            value = (value != null) ? value.toString() : null;
        }
        if (paramType == Timestamp.class) {
            Timestamp timestamp = (value.getClass() == String.class) ? Timestamp.valueOf(convertToDateTimeFormat(value.toString()))
                    : (value.getClass() == Timestamp.class) ? (Timestamp) value : new Timestamp(((java.sql.Date) value).getTime());
            value = timestamp;
        }
        if (paramType == Time.class) {
            value = (value.getClass() == String.class) ? java.sql.Time.valueOf(addSecondsIfNeeded(value.toString())) : (Time) value;
        }
        // type field Calendar && type value Timestamp or java.sql.Date
        if (paramType == java.util.Calendar.class) {
            Timestamp timestamp = (value.getClass() == String.class) ? Timestamp.valueOf(convertToDateTimeFormat(value.toString()))
                    : (value.getClass() == Timestamp.class) ? (Timestamp) value : new Timestamp(((java.sql.Date) value).getTime());
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(timestamp.getTime());
            value = calendar;
        }
        // type field Calendar && type value Timestamp or java.sql.Date
        if (paramType == LocalDateTime.class) {
            System.out.println(value);
            Timestamp timestamp = (value.getClass() == String.class) ? Timestamp.valueOf(convertToDateTimeFormat(value.toString()))
                    : (value.getClass() == Timestamp.class) ? (Timestamp) value : new Timestamp(((java.sql.Date) value).getTime());
            value = timestamp.toLocalDateTime();
        }
        // type field java.util.Date && type value Timestamp or java.sql.Date
        if (paramType == java.util.Date.class) {
            java.sql.Date sqlDate = (value.getClass() == String.class) ? java.sql.Date.valueOf(value.toString())
                    : (value.getClass() == java.sql.Date.class) ? (java.sql.Date) value : new java.sql.Date(((Timestamp) value).getTime());
            long sqlDateInMillis = sqlDate.getTime();
            value = new java.util.Date(sqlDateInMillis);
        }
        if (paramType == Duration.class) {
            value = Base.PGIntervalToDuration((PGInterval) value);
        }
        if (paramType == PGInterval.class) {
            value = (PGInterval) value;
        }
        if (paramType == BigDecimal.class) {
            value = new BigDecimal(value.toString());
        }
        method = getClass().getMethod(methodName, paramType);

        Object[] args = {value};
        method.invoke(this, args);
    }

    public void setFieldValue(String colname, Object value, Class paramType) throws Exception {
        Field field = getFieldByName(colname);
        setFieldValue(field, value, paramType);
    }

    public void create(Connection co) throws Exception {
        boolean isCoNull = false;
        if (co == null) {
            isCoNull = true;
            co = EtablirConnex.getConnection();
        }
        Col[] columns = getBaseCol();
        ArrayList<String> variables = new ArrayList();
        ArrayList<String> values = new ArrayList();
        Statement stat = null;
        try {
            for (Col col : columns) {
                Field field = getFieldByName(col.getColname());
                //System.out.println(col.getColname());
                Optional optionalStr = Optional.ofNullable(this.getFieldValue(field));
                String value = (optionalStr.isPresent()) ? optionalStr.get().toString() : null;
                if (value != null) {
                    variables.add(Request.fixeString(col.getColname()));
                    values.add(treatString(value, field.getType()));
                }
            }
            String strVariables = String.join(",", variables.toArray(new String[variables.size()]));
            String strValues = String.join(",", values.toArray(new String[values.size()]));
            String sql = "insert into " + this.getTname() + " (" + strVariables + ") values (" + strValues + ")";
            System.out.println(sql);
            stat = co.createStatement();
            stat.executeUpdate(sql);
        } finally {
            if (stat != null) {
                stat.close();
            }
            if (isCoNull && co != null) {
                co.close();
                System.gc();
            }
        }
    }

    public void update(Connection co) throws Exception {
        boolean isCoNull = false;
        if (co == null) {
            isCoNull = true;
            co = EtablirConnex.getConnection();
        }
        Col[] columns = getBaseCol();
        ArrayList<String> criterium = new ArrayList();
        Statement stat = null;
        String idvalue = null;
        try {
            for (Col col : columns) {
                Field field = getFieldByName(col.getColname());
                Optional optionalStr = Optional.ofNullable(this.getFieldValue(field));
                String value = (optionalStr.isPresent()) ? optionalStr.get().toString() : null;
                if (value != null) {
                    if (col.getColname().compareToIgnoreCase(this.getIdName()) == 0) {
                        idvalue = treatString(value, field.getType());
                    } else {
                        criterium.add(Request.fixeString(col.getColname()) + " = " + treatString(value, field.getType()));
                    }
                }
            }
            String strCriteria = String.join(",", criterium.toArray(new String[criterium.size()]));
            if (idvalue == null) {
                throw new RequestException("UPDATE ERROR: ID of the object must not be null");
            }
            String strId = Request.fixeString(this.getIdName()) + " = " + idvalue;
            String sql = "update " + this.getTname() + " set " + strCriteria + " where " + strId;
            System.out.println(sql);
            stat = co.createStatement();
            stat.executeUpdate(sql);
        } finally {
            if (stat != null) {
                stat.close();
            }
            if (isCoNull && co != null) {
                co.close();
                System.gc();
            }
        }
    }

    public void delete(Connection co) throws Exception {
        boolean isCoNull = false;
        if (co == null) {
            isCoNull = true;
            co = EtablirConnex.getConnection();
        }
        Col[] columns = getBaseCol();
        ArrayList<String> criterium = new ArrayList();
        Statement stat = null;
        Field idfield = getFieldByName(this.getIdName());
        String idvalue = null;
        try {
            Optional optionalStr = Optional.ofNullable(this.getFieldValue(idfield));
            String value = (optionalStr.isPresent()) ? optionalStr.get().toString() : null;
            if (value == null) {
                throw new RequestException("UPDATE ERROR: ID of the object must not be null");
            }
            idvalue = treatString(value, idfield.getType());
            String strId = Request.fixeString(this.getIdName()) + " = " + idvalue;
            String sql = "delete from " + this.getTname() + " where " + strId;
            System.out.println(sql);
            stat = co.createStatement();
            stat.executeUpdate(sql);
        } finally {
            if (stat != null) {
                stat.close();
            }
            if (isCoNull && co != null) {
                co.close();
                System.gc();
            }
        }
    }

    public ArrayList select(Connection co, String sql) throws Exception {
        boolean isCoNull = false;
        if (co == null) {
            isCoNull = true;
            co = EtablirConnex.getConnection();
        }
        if (sql == null) {
            System.out.println(this.getClass());
            sql = "select * from " + getTname() + " where 1<2 " + Request.makeWhere(-1, new Reflection(this.getClass()), this);
        }
        System.out.println(sql);
        Statement stat = null;
        ResultSet rs = null;
        ArrayList retour = new ArrayList();
        try {
            stat = co.createStatement();
            rs = stat.executeQuery(sql);
            SetResult sr = new SetResult(rs, sql);
            Object[][] data = sr.getData(sr.nbrColumn(), sr.nbrData());
            for (int i = 0; i < data.length; i++) {
                int e = 0;
                ObjectBdd clone = this.getClass().getConstructor().newInstance();
                // izay col selectena @ sql ihany no alaina
                for (Col col : sr.getListCol()) {
                    Object val = data[i][e];
                    if (val != null) {
//                        System.out.println(col.getColname());
//                        System.out.println(val);
//                        System.out.println(val.getClass());

                        clone.setFieldValue(col.getColname(), val, null);

                    }
                    e++;
                }
                retour.add(clone);
            }
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
        return retour;
    }

    public String[] getAttrList() throws Exception {
        ArrayList<String> list = new ArrayList<>();
        for (Col col : getBaseCol()) {
            list.add(col.getColname());
        }
        return list.toArray(new String[list.size()]);
    }

    // where part fotsiny sao efa misy sql any 
    public String getFiltresql(Form form, Map<String, String[]> parameterMap) throws Exception {
        String sql = " and 1<2 ";
        for (String parameterName : parameterMap.keySet()) {
            String[] parameterValues = parameterMap.get(parameterName);
            if (parameterValues[0] != "") {
                String param = parameterName;
                param = parameterName.replaceFirst(form.getPrefix(), "");
                if (form.isModifmode()) {
                    param = parameterName.replaceFirst("modif", "");
                }
                Champ champ = form.getChamps().get(param);
                if (champ != null) {
                    String fieldName = champ.getFieldName();
                    Field field = getFieldByName(fieldName);
                    fieldName = Request.fixeString(fieldName);
                    if (champ.getType() == InputTypes.TEXT || champ.getType() == InputTypes.TEXTAREA) {
                        sql += " and " + fieldName + " ilike '%" + parameterValues[0] + "%'";
                        continue;
                    }
                    if (champ.getType() == InputTypes.DATERANGE || champ.getType() == InputTypes.DATETIMERANGE) {
                        String[] parts = parameterValues[0].split(" - ");
                        sql += " and " + fieldName + " >= '" + parts[0] + "' and " + fieldName + " <= '" + parts[1] + "'";
                        continue;
                    }
                    if (champ.getType() == InputTypes.NUMBER) {
                        sql += " and " + fieldName + " = " + parameterValues[0];
                        continue;
                    }
                    if (champ.getType() == InputTypes.CHECKBOX) {
                        sql += " and (" + fieldName + " = " + Request.treatString(parameterValues[0], field.getType());
                        for (int i = 1; i < parameterValues.length; i++) {
                            sql += " or " + fieldName + " = " + Request.treatString(parameterValues[i], field.getType());
                        }
                        sql += ") ";
                        continue;
                    } else {
                        sql += " and " + fieldName + " = '" + parameterValues[0] + "'";
                    }
                }

            }
            System.out.println(parameterName + " = " + Arrays.toString(parameterValues));
        }
        System.out.println(sql);
        return sql;
    }

    public ObjectBdd findById() throws Exception {
        Class cls = getClass();
        ObjectBdd instance = (ObjectBdd) cls.newInstance();
        instance.setFieldValue(this.getIdName(), this.getId(), null);
        ObjectBdd e = null;
        ArrayList<ObjectBdd> list = instance.select(null, null);
        if (!list.isEmpty() & list.size() == 1) {
            e = (ObjectBdd) list.get(0);
        }
        return e;
    }

    public void setFieldsVal(Map<String, String[]> parameterMap, boolean modifmode, String prefix) throws Exception {
        for (String parameterName : parameterMap.keySet()) {
            String[] parameterValues = parameterMap.get(parameterName);
            if (parameterValues[0] != "") {
                String param = parameterName;
                param = parameterName.replaceFirst(prefix, "");
                if (modifmode) {
                    param = param.replaceFirst("modif", "");
                }
                System.out.println(param);
                Field field = getFieldByName(param);
                if (field != null) {
                    setFieldValue(field, parameterValues[0], null);
                }
            }
        }
    }

    //override to specify
    public String getTname() {
        if (tname == null) {
            tname = getClass().getSimpleName();
        }
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

}
