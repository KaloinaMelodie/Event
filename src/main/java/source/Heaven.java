/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package source;

/**
 *
 * @author patricia.razafimboah
 */
public class Heaven {
    static HashColTable listcoltable = new HashColTable();    

    public static HashColTable getListcoltable() {
        return listcoltable;
    }

    public static void setListcoltable(HashColTable listcoltable) {
        Heaven.listcoltable = listcoltable;
    }
    
    //delete all stock data in listcoltable
    public static void scan(){
        setListcoltable(new HashColTable());
    }
}
