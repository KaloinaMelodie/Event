/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package source;

import java.lang.reflect.Field;
import java.sql.Types;

/**
 *
 * @author kaloina.melo
 */
public class Col {
    String colname;
    int coltype;
    String coltypename;
    int size;
    int precision;
    String typejava;    

    public Col() {
    }

    public Col(String colname, int coltype, String coltypename, int size, int precision, String typejava) {
        this.colname = colname;
        this.coltype = coltype;
        this.coltypename = coltypename;
        this.size = size;
        this.precision = precision;
        this.typejava = typejava;
    }

    public boolean isNumeric(){        
        if (Constante.numericTypes.contains(coltype)) {
            return true;
        }
        return false;
    }
    

    public String getColname() {
        return colname;
    }

    public void setColname(String colname) {
        this.colname = colname;
    }

    public int getColtype() {
        return coltype;
    }

    public void setColtype(int coltype) {
        this.coltype = coltype;
    }

    public String getColtypename() {
        return coltypename;
    }

    public void setColtypename(String coltypename) {
        this.coltypename = coltypename;
    }
    

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPrecision() {
        return precision;
    }

    public void setPrecision(int precision) {
        this.precision = precision;
    }

    public String getTypejava() {
        return typejava;
    }

    public void setTypejava(String typejava) {
        this.typejava = typejava;
    }


    @Override
    public String toString() {
        return "Col{" + "colname=" + colname + ", coltype=" + coltype + ", coltypename=" + coltypename + ", size=" + size + ", precision=" + precision + ", typejava=" + typejava + '}';
    }
    
       
    
    
}
