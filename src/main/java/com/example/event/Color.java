/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.event;

import source.ObjectBdd;

/**
 *
 * @author kaloina.melo
 */
public class Color extends ObjectBdd{
    Integer idColor;
    String color;

    public Integer getIdColor() {
        return idColor;
    }

    public void setIdColor(Integer idColor) {
        this.idColor = idColor;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }        
   

    @Override
    public String getIdName() {
        return "idColor";
    }
    
}
