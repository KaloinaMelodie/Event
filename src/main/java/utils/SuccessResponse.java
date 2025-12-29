/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author patricia.razafimboah
 */
public class SuccessResponse extends Response{

    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object o) {
        this.data = o;
    }

    public SuccessResponse(String status, Object o) {
        setStatus(status);
        setData(o);
    }
}
