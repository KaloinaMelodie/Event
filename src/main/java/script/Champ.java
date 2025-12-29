/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package script;

import annots.Input;
import java.io.StringReader;
import java.lang.reflect.Field;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import source.Base;
import source.ObjectBdd;

/**
 *
 * @author patricia.razafimboah
 */
public class Champ {
    
    String fieldName;
    ObjectBdd obj;
    Element mainform;
    Element content;
    String id;
    String js = "";
    int type;
    Object value;
    boolean addDefValue = false;
    boolean modifmode = false;
    Document doc;
    
    String prefix = "";
    
    public Champ(String fieldName, ObjectBdd obj, Document doc) throws Exception {
        this.fieldName = fieldName;
        this.obj = obj;
        this.doc = doc;
    }
    //initMainform

    public String getFieldName() {
        return fieldName;
    }
    
    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }
    
    public ObjectBdd getObj() {
        return obj;
    }
    
    public void setObj(ObjectBdd obj) {
        this.obj = obj;
    }
    
    public Element getMainform() {
        return mainform;
    }
    
    public void setMainform(Element mainform) {
        this.mainform = mainform;
    }
    
    public boolean isModifmode() {
        return modifmode;
    }
    
    public void setModifmode(boolean modifmode) {
        if (modifmode == true) {
            setAddDefValue(true);
        }
        this.modifmode = modifmode;
    }
    
    public String getId() {
        if (this.id == null) {
            if (modifmode) {
                return getPrefix()+"modif" + this.getFieldName();
            }
            return getPrefix() + this.getFieldName();
        }
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getJs() {
        return js;
    }
    
    public void setJs(String js) {
        this.js = js;
    }
    
    public int getType() throws Exception {
        if (type == 0) {
            // if misy annotation
            Field field = obj.getFieldByName(fieldName);
            if (field.getAnnotation(Input.class) != null) {
                return field.getAnnotation(Input.class).type();
            }
            // if getJfieldName is not null
            try {
                obj.getFieldValue("j" + fieldName);
                return InputTypes.SELECT;
            } catch (java.lang.NullPointerException npe) {
                //nothing
            }
            Class cls = obj.getFieldByName(fieldName).getType();
            for (Map.Entry<Integer, ArrayList<String>> entry : script.Constante.defaultType.entrySet()) {
                Integer key = entry.getKey();
                ArrayList<String> value = entry.getValue();
                if (value.contains(cls.getSimpleName())) {
                    return key;
                }
            }
        }
        return type;
    }
    
    public void setType(int type) {
        this.type = type;
    }
    
    public boolean isAddDefValue() {
        if (isModifmode()) {
            addDefValue = true;
        }
        return addDefValue;
    }
    
    public void setAddDefValue(boolean addDefValue) {
        this.addDefValue = addDefValue;
    }
    
    public Object getValue() throws Exception {
        if (value == null) {
            return obj.getFieldValue(fieldName);
        }
        return value;
    }
    
    public void setValue(Object value) {
        this.value = value;
    }
    
    public Element getContent() {
        return content;
    }
    
    public void setContent(Element content) {
        this.content = content;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }    
    
    public String getHtml() throws Exception {
//        initContent();
//        initMainform();
        return Funct.toHtml(mainform);
    }    
    
    public void initMainform() throws Exception {
        initContent();
        mainform = doc.createElement("div");
        mainform.setAttribute("class", "form-group");
        Element label = doc.createElement("label");
        //label
        label.setTextContent(fieldName);
        mainform.appendChild(label);
        mainform.appendChild(content);
        
    }
    
    public void initContent() throws Exception {
        
        if (InputTypes.TEXT == getType()) {
            content = doc.createElement("input");
            content.setAttribute("type", "text");
            content.setAttribute("class", "form-control");
            content.setAttribute("id", getId());
            content.setAttribute("name", getId());
            if (isAddDefValue()) {
                if (this.getValue() != null) {
                    content.setAttribute("value", this.getValue().toString());
                }
            }
        }
        if (InputTypes.TEXTAREA == getType()) {
            
            content = doc.createElement("textarea");
            content.setAttribute("class", "form-control");
            content.setAttribute("id", getId());
            content.setAttribute("name", getId());
            if (isAddDefValue()) {
                if (this.getValue() != null) {
                    content.setTextContent(this.getValue().toString());
                }
            }
        }
        if (InputTypes.PASSWORD == getType()) {
            
            content = doc.createElement("input");
            content.setAttribute("type", "password");
            content.setAttribute("class", "form-control");
            content.setAttribute("id", getId());
            content.setAttribute("name", getId());
            if (isAddDefValue()) {
                if (this.getValue() != null) {
                    content.setAttribute("value", this.getValue().toString());
                }
            }
        }
        if (InputTypes.NUMBER == getType()) {
            
            content = doc.createElement("input");
            content.setAttribute("type", "number");
            content.setAttribute("class", "form-control");
            content.setAttribute("id", getId());
            content.setAttribute("name", getId());
            if (isAddDefValue()) {
                if (this.getValue() != null) {
                    content.setAttribute("value", this.getValue().toString());
                }
            }
        }
        if (InputTypes.FILE == getType()) {
            content = doc.createElement("input");
            content.setAttribute("type", "file");
            content.setAttribute("class", "form-control");
            content.setAttribute("id", getId());
            content.setAttribute("name", getId());
            if (isAddDefValue()) {
                if (this.getValue() != null) {
                    content.setAttribute("value", this.getValue().toString());
                }
            }
        }
        if (InputTypes.SELECT == getType()) {
            
            content = doc.createElement("select");
            content.setAttribute("type", "select");
            content.setAttribute("class", "form-control");
            content.setAttribute("id", getId());
            content.setAttribute("name", getId());
            Element optionvide = doc.createElement("option");
            optionvide.setAttribute("value", "");
            optionvide.setTextContent(fieldName);
            content.appendChild(optionvide);
            HashMap<String, String> data = (HashMap<String, String>) obj.getFieldValue("j" + fieldName);
            for (Map.Entry<String, String> entry : data.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                Element option = doc.createElement("option");
                option.setAttribute("value", key);
                option.setTextContent(value);
                if (isAddDefValue()) {
                    if (this.getValue() != null) {
                        if (key.toString().equals(this.getValue().toString())) {
                            this.setJs(getJs() + " document.getElementById('" + getId() + "').value = '" + key.toString() + "';");
                            System.out.println(js);
                        }
                    }
                }
                content.appendChild(option);
            }
        }
        if (InputTypes.CHECKBOX == getType()) {
            
            content = doc.createElement("div");
            HashMap<String, String> data = (HashMap<String, String>) obj.getFieldValue("j" + fieldName);
            for (Map.Entry<String, String> entry : data.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                Element item = doc.createElement("div");
                item.setAttribute("class", "checkbox");
                Element label = doc.createElement("label");
                Node context = doc.createTextNode(value);
                
                Element input = doc.createElement("input");
                input.setAttribute("type", "checkbox");
                input.setAttribute("value", key);
                input.setAttribute("id", getId());
                input.setAttribute("name", getId());
                if (isAddDefValue()) {
                    if (this.getValue() != null) {
                        if (key.toString().equals(this.getValue().toString())) {
                            input.setAttribute("checked", "");
                        }
                    }
                }
                label.appendChild(input);
                label.appendChild(context);
                item.appendChild(label);
                
                content.appendChild(item);
            }
            
        }
        if (InputTypes.RADIO == getType()) {
            
            content = doc.createElement("div");
            HashMap<String, String> data = (HashMap<String, String>) obj.getFieldValue("j" + fieldName);
            for (Map.Entry<String, String> entry : data.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                Element item = doc.createElement("div");
                item.setAttribute("class", "radio");
                Element label = doc.createElement("label");
                Node context = doc.createTextNode(value);
                
                Element input = doc.createElement("input");
                input.setAttribute("type", "radio");
                input.setAttribute("value", key);
                input.setAttribute("id", getId());
                input.setAttribute("name", getId());
                if (isAddDefValue()) {
                    if (this.getValue() != null) {
                        if (key.toString().equals(this.getValue().toString())) {
                            input.setAttribute("checked", "");
                        }
                    }
                }
                label.appendChild(input);
                label.appendChild(context);
                item.appendChild(label);
                
                content.appendChild(item);
            }
        }
        if (InputTypes.DATEMASK == getType()) {
            
            content = doc.createElement("div");
            content.setAttribute("class", "input-group");
            // context
            Element div = doc.createElement("div");
            div.setAttribute("class", "input-group-addon");
            Element i = doc.createElement("i");
            i.setAttribute("class", "fa fa-calendar");
            div.appendChild(i);
            Node context = div;
            // end context
            content.appendChild(context);
            Element input = doc.createElement("input");
            input.setAttribute("type", "text");
            input.setAttribute("class", "form-control");
            //input.setAttribute("placeholder", "yyyy-mm-dd");
            input.setAttribute("id", getId());
            input.setAttribute("name", getId());
            setJs("$('#" + getId() + "').inputmask('yyyy-mm-dd', { 'placeholder': 'yyyy-mm-dd' });");
            System.out.println(js);
            if (isAddDefValue()) {
                if (this.getValue() != null) {
                    String value = this.getValue().toString();
                    if (this.getValue().getClass().equals(java.util.Date.class)) {
                        value = Base.dateToString((java.util.Date) this.getValue());
                    }
                    input.setAttribute("value", value);
                }
            }
            content.appendChild(input);
        }
        if (InputTypes.DATETIME == getType()) {
            content = doc.createElement("input");
            content.setAttribute("type", "datetime-local");
            content.setAttribute("class", "form-control");
            content.setAttribute("id", getId());
            content.setAttribute("name", getId());
            if (isAddDefValue()) {
                if (this.getValue() != null) {
                    String value = this.getValue().toString();
                    content.setAttribute("value", value);
                }
            }
        }
        if (InputTypes.DATERANGE == getType()) {
            
            content = doc.createElement("div");
            content.setAttribute("class", "input-group");
            // context
            Element div = doc.createElement("div");
            div.setAttribute("class", "input-group-addon");
            Element i = doc.createElement("i");
            i.setAttribute("class", "fa fa-calendar");
            div.appendChild(i);
            Node context = div;
            // end context
            content.appendChild(context);
            Element input = doc.createElement("input");
            input.setAttribute("type", "text");
            input.setAttribute("class", "form-control");            
            input.setAttribute("id", getId());
            input.setAttribute("name", getId());
            setJs("$('#" + getId() + "').daterangepicker({ format: 'YYYY-MM-DD' });");
            System.out.println(js);
            if (isAddDefValue()) {
                if (this.getValue() != null) {
                    String value = this.getValue().toString();
                    if (this.getValue().getClass().equals(java.util.Date.class)) {
                        value = Base.dateToString((java.util.Date) this.getValue());
                        value = value + " - " + value;
                    }
                    input.setAttribute("value", value);
                }
            }
            content.appendChild(input);
        }
        if (InputTypes.DATETIMERANGE == getType()) {
            
            content = doc.createElement("div");
            content.setAttribute("class", "input-group");
            // context
            Element div = doc.createElement("div");
            div.setAttribute("class", "input-group-addon");
            Element i = doc.createElement("i");
            i.setAttribute("class", "fa fa-clock-o");
            div.appendChild(i);
            Node context = div;
            // end context
            content.appendChild(context);
            Element input = doc.createElement("input");
            input.setAttribute("type", "text");
            input.setAttribute("class", "form-control");            
            input.setAttribute("id", getId());
            input.setAttribute("name", getId());
            setJs("$('#" + getId() + "').daterangepicker({ timePicker: true, timePickerIncrement: 5, format: 'YYYY-MM-DD h:mm ' });");
            System.out.println(js);
            if (isAddDefValue()) {
                if (this.getValue() != null) {
                    String value = this.getValue().toString();
                    if (this.getValue() instanceof LocalDateTime || this.getValue() instanceof Timestamp) {
                        value = value + " - " + value;
                    }
                    input.setAttribute("value", value);
                }
            }
            content.appendChild(input);
        }
        if (InputTypes.COLORPICKER == getType()) {
            content = doc.createElement("div");
            content.setAttribute("class", "input-group");
            // context
            Element div = doc.createElement("div");
            div.setAttribute("class", "input-group-addon");
            Element i = doc.createElement("i");
            div.appendChild(i);
            Node context = div;
            // end context
            Element input = doc.createElement("input");
            input.setAttribute("type", "text");
            input.setAttribute("class", "form-control");
            input.setAttribute("id", getId());
            input.setAttribute("name", getId());
            setJs("$('#" + getId() + "').colorpicker();");
            if (isAddDefValue()) {
                if (this.getValue() != null) {
                    String value = this.getValue().toString();
                    input.setAttribute("value", value);
                }
            }
            content.appendChild(input);
            content.appendChild(context);
        }
        if (InputTypes.TIMEPICKER == getType()) {
            content = doc.createElement("div");
            content.setAttribute("class", "input-group");
            // context
            Element div = doc.createElement("div");
            div.setAttribute("class", "input-group-addon");
            Element i = doc.createElement("i");
            i.setAttribute("class", "fa fa-clock-o");
            div.appendChild(i);
            Node context = div;
            // end context
            Element input = doc.createElement("input");
            input.setAttribute("type", "text");
            input.setAttribute("class", "form-control");
            input.setAttribute("id", getId());
            input.setAttribute("name", getId());
            setJs("$('#" + getId() + "').timepicker({showInputs: false,showMeridian: false, defaultTime:false });");
            if (isAddDefValue()) {
                if (this.getValue() != null) {
                    String value = this.getValue().toString();
                    this.setJs(getJs() + " document.getElementById('" + getId() + "').value = '" + value + "';");
                    System.out.println(js);
                }
            }
            content.appendChild(input);
            content.appendChild(context);
        }
        if (InputTypes.CKEDITOR == getType()) {
            content = doc.createElement("textarea");
            content.setAttribute("class", "form-control");
            content.setAttribute("id", getId());
            content.setAttribute("name", getId());
            setJs("CKEDITOR.replace('" + getId() + "'); ");
            if (isAddDefValue()) {
                if (this.getValue() != null) {
                    content.setTextContent(this.getValue().toString());
                }
            }
        }
    }
    
    public static void main(String[] gygy) throws Exception {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("JavaScript");
        String html = "<html></html>";
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new InputSource(new StringReader(html)));
        if (engine != null) {
            engine.put("document", doc);
        }
        
//        Test test = new Test();
//        test.setTexte("exemple");
//        test.setNbre(0);
//        test.setIdcheck("2");
//        test.setDaty(new Date());
//        //test.setDatetime(new Timestamp(System.currentTimeMillis()));
//        test.setDatetime(LocalDateTime.now());
//        test.setColor("#f3ff");
//        test.setTime(Time.valueOf(LocalTime.now()));
//        Champ champ = new Champ("img", test, doc);
//        champ.setType(InputTypes.FILE);
////        champ.setModifmode(true);
//        //champ.setValue("2023-05-12 12:00 AM - 2023-05-12 12:00 AM");
//        champ.initMainform();
//        champ.setAddDefValue(true);
//        System.out.println(champ.getType());
//        System.out.println(champ.getHtml());
        
    }
}
