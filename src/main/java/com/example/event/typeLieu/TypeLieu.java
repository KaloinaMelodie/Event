package com.example.event.typeLieu;
import source.ObjectBdd;
public class TypeLieu extends ObjectBdd{
Integer idTypeLieu;
String typeLieu;

    public Integer getIdTypeLieu() {
        return idTypeLieu;
    }

    public void setIdTypeLieu(Integer idTypeLieu) {
        this.idTypeLieu = idTypeLieu;
    }

    public String getTypeLieu() {
        return typeLieu;
    }

    public void setTypeLieu(String typeLieu) {
        this.typeLieu = typeLieu;
    }

 @Override
    public String getIdName() {
        return "idTypeLieu";
    }
}