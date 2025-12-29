/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package source;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author kaloina.melo
 */
public class Reflection {

    Class cls;
    HashMap<String, Field> fields = new HashMap();
    HashMap<String, Method> methods = new HashMap();
    HashMap<String, Method> getters = new HashMap();
    HashMap<String, Method> setters = new HashMap();
    //field level
    HashMap<Integer, Field[]> fieldsLevel;

    public Reflection(Class cls) throws Exception {
        this.cls = cls;
        initFieldLevel();
    }

    public void initFieldLevel() throws Exception {
        HashMap<Integer, Field[]> temp = new HashMap<Integer, Field[]>() {
            @Override
            public Field[] get(Object key) {
                Integer k = (Integer) key;
                // if lower to 0 then get all field in all level
                if (k < 0) {
                    ArrayList<Field> flist = new ArrayList();
                    for (int i = 0; i < this.size(); i++) {
                        Field second[] = this.get(i);
                        flist.addAll(Arrays.asList(second));
                    }
                    Field[] retour = flist.toArray(new Field[flist.size()]);
                    return retour;
                }
                return super.get(key);
            }
        };
        Field[] field1 = cls.getDeclaredFields();
        temp.put(0, field1);
        int index = 1;
        Class p = cls.getSuperclass();
        while (1 < 2) {
            if (p.equals(ObjectBdd.class) || p.equals(Object.class)) {
                break;
            }
            Field second[] = p.getDeclaredFields();
            temp.put(index, second);
            p = p.getSuperclass();
            index++;
        }
        fieldsLevel = temp;
    }

    public void initFields() throws Exception {
        Field[] list = getFieldList(cls);
        for (Field field : list) {
            fields.put(field.getName(), field);
        }
    }

    public void initMethods() throws Exception {
        Field[] list = getFieldList(cls);
        for (Field field : list) {
            fields.put(field.getName(), field);
        }
    }

    public void initGetters() throws Exception {
        Field[] list = getFieldList(cls);
        for (Field field : list) {
            fields.put(field.getName(), field);
        }
    }

    public HashMap<Integer, Field[]> getFieldsLevel() {
        return fieldsLevel;
    }

    public void setFieldsLevel(HashMap<Integer, Field[]> fieldsLevel) {
        this.fieldsLevel = fieldsLevel;
    }

    public static Field[] getFieldList(Class clss) throws Exception {
        Field[] field1 = clss.getDeclaredFields();

        Vector flist = new Vector();
        List temp = Arrays.asList(field1);
        flist.addAll(temp);
        Class p = clss.getSuperclass();
        while (1 < 2) {
            if (p.equals(ObjectBdd.class)) {
                break;
            }
            Field second[] = p.getDeclaredFields();
            flist.addAll(Arrays.asList(second));
            p = p.getSuperclass();
        }
        Field[] retour = new Field[flist.size()];
        flist.copyInto(retour);
        return retour;
    }

}
