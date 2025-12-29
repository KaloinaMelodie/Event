/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package script;

import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 *
 * @author kaloina.melo
 */
public class Constante {

    //type: text,textarea,password,number,select,file,checkbox,radio,datemask,datetime,daterange,datetimerange,timepicker,colorpicker,ckeditor
    public static HashMap<Integer, ArrayList<String>> defaultType = new HashMap<Integer, ArrayList<String>>() {
        {
            this.put(InputTypes.NUMBER, new ArrayList<String>(Arrays.asList("Integer")));
            this.put(InputTypes.TEXT, new ArrayList<String>(Arrays.asList("String", "Double", "Float", "BigDecimal")));
            this.put(InputTypes.DATEMASK, new ArrayList<String>(Arrays.asList("Date")));
            this.put(InputTypes.TIMEPICKER, new ArrayList<String>(Arrays.asList("Time")));
            this.put(InputTypes.DATETIME, new ArrayList<String>(Arrays.asList("Timestamp", "LocalDateTime")));

        }
    };

}
