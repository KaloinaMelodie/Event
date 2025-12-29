/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package script;

import java.io.StringReader;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import source.Col;
import source.ObjectBdd;

/**
 *
 * @author patricia.razafimboah
 */
public class Form {

    boolean modifmode = false;
    boolean adddef = false;
    HashMap<String, Champ> champs;
    Document doc;
    ObjectBdd obj;   
    String prefix;

    public Form(ObjectBdd obj, HashMap<String, Integer> columns) throws Exception {
        initDoc();
        this.obj = obj;
        initChamps(columns);
    }
    public Form(ObjectBdd obj, HashMap<String, Integer> columns, boolean modifmode) throws Exception {
        initDoc();
        this.obj = obj;
        initChamps(columns);
        this.setModifmode(modifmode);
        initChampsmainform();
    }
    
    

    public void initChamps(HashMap<String, Integer> columns) throws Exception {
        champs = new HashMap<String, Champ>();
        if (columns != null) {
            for (Map.Entry<String, Integer> col : columns.entrySet()) {
                String key = col.getKey();
                Integer value = col.getValue();
                Champ champ = new Champ(key, getObj(), getDoc());
                champ.setType(value);
                champ.initMainform();
                champs.put(key, champ);
            }
        } else {
            for (String str : getObj().getAttrList()) {
                Champ champ = new Champ(str, getObj(), getDoc());
                //System.out.println(champ.getFieldName()+" "+champ.getType());
                champ.initMainform();
                champs.put(str, champ); 
            }
        }
    }
    public String getHtml()throws Exception{
        String html = "";
        for(Map.Entry<String,Champ> champ : getChamps().entrySet()){
            Champ c = champ.getValue();
            
            //System.out.println(champ.getKey()+" "+c.getType());
            html+=c.getHtml()+"\n";
        }
        return html;
    }
    public String getJs()throws Exception{
        String js = "";
        for(Map.Entry<String,Champ> champ : getChamps().entrySet()){
            Champ c = champ.getValue();
            
            //System.out.println(champ.getKey()+" "+c.getType());
            js+=c.getJs()+"\n";
        }
        return js;
    }

    public void initDoc() throws Exception {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("JavaScript");
        String html = "<html></html>";
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new InputSource(new StringReader(html)));
        if (engine != null) {
        engine.put("document", doc);
        }
        this.doc = doc;
    }

    public boolean isAdddef() {
        return adddef;
    }

    public void setAdddef(boolean adddef) {
        for (Map.Entry<String, Champ> col : getChamps().entrySet()) {
            String key = col.getKey();
            Champ value = col.getValue();
            value.setAddDefValue(adddef);
        }
        this.adddef = adddef;
    }
        

    public boolean isModifmode() {
        return modifmode;
    }

    public void setModifmode(boolean modifmode)throws Exception {
        for (Map.Entry<String, Champ> col : getChamps().entrySet()) {
            String key = col.getKey();
            Champ value = col.getValue();
            value.setModifmode(modifmode);
        }
        this.modifmode = modifmode;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        for (Map.Entry<String, Champ> col : getChamps().entrySet()) {
            String key = col.getKey();
            Champ value = col.getValue();
            value.setPrefix(prefix);
        }
        this.prefix = prefix;
    }        

    
    public void addCol(String coll){
        for (Map.Entry<String, Champ> col : getChamps().entrySet()) {
            String key = col.getKey();
            Champ value = col.getValue();
            value.getMainform().setAttribute("class","form-group "+coll);
        }        
    }
    public void initChampsmainform()throws Exception{
        for (Map.Entry<String, Champ> col : getChamps().entrySet()) {
            String key = col.getKey();
            Champ value = col.getValue();
            value.initMainform();
        }      
    }

    public HashMap<String, Champ> getChamps() {
        return champs;
    }

    public void setChamps(HashMap<String, Champ> champs) {
        this.champs = champs;
    }

    public Document getDoc() {
        return doc;
    }

    public void setDoc(Document doc) {
        this.doc = doc;
    }

    public ObjectBdd getObj() {
        return obj;
    }

    public void setObj(ObjectBdd obj) {
        this.obj = obj;
    }
    

    public static void main(String[] gygy) throws Exception {
//        Test test = new Test();
//        test.setTexte("exemple");
//        test.setNbre(0);
//        test.setIdcheck("2");
//        test.setDaty(new Date());
//        //test.setDatetime(new Timestamp(System.currentTimeMillis()));
//        test.setDatetime(LocalDateTime.now());
//        test.setColor("#f3ff");
//        test.setTime(Time.valueOf(LocalTime.now()));

//        HashMap<String, Integer> columns = new HashMap<String, Integer>() {
//            {
//                put("texte", InputTypes.TEXT);
//                put("img", 0);
//                put("daty", 0);
//            }
//        };
//        Form form = new Form(test, columns);    
//        form.getChamps().remove(test.getIdName());
//        System.out.println(form.getHtml());
//        System.out.println(form.getJs());    
        
        //etape lalana ny adddef sy modifmode TSY MAINTSY misy initChampsmainform aorinany
//        Form form = new Form(test, null);
        // tsy maintsy fait avant le initmainform des champs
//        form.setAdddef(true);
//        form.setModifmode(true);
//        form.setPrefix("prefix");
        // ou mampiasa l'autre constructor avec le modifmode true
//        form.initChampsmainform();
        // modification specifique pour chaque champ par exemple
//        form.getChamps().remove(test.getIdName());

    }
}
