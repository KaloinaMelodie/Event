/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import source.ObjectBdd;

/**
 *
 * @author patricia.razafimboah
 * @param <T>
 */
public interface InterfaceLog<T extends ObjectBdd<T>> {
    public abstract String getIdentifiant();
    public abstract String getMdp();
    public abstract T checkLog()throws Exception;
}
