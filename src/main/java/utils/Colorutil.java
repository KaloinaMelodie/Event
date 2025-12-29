/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author kaloina.melo
 */
public class Colorutil {
    public static String prixventecolor(Double prix){
        if(prix!=null){
            if(prix>=0&&prix<=1000000){
                return "red";
            }
            if(prix>1000000&&prix<=8000000){
                return "yellow";
            }
            if(prix>8000000){
                return "green";
            }
        }
        return "grey";
    }
}
