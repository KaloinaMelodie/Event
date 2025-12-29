/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package script;

import java.io.*;
import java.util.logging.Logger;
import javax.script.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.*;

public class ScriptExecutor {

    public static void printNode(Node node) throws Exception {
        // Create a new transformer
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        // Configure the transformer to output XML with indentation and no XML declaration
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");

        // Serialize the node to a string
        StringWriter writer = new StringWriter();
        transformer.transform(new DOMSource(node), new StreamResult(writer));
        String nodeString = writer.toString();

        // Print the serialized node
        System.out.println(nodeString);
    }

    public static void main(String[] args) throws Exception {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("JavaScript");

        // Set up the DOM document object
        String html = "<html><body><p id='tay'>Hello World!</p><div><p id='tai'>Hi you</p></div><script>function hi(){"
                + "var logger = java.util.logging.Logger.getLogger('ScriptExecutor');logger.severe('hello ty');"
                + "var element=document.getElementById('tay');"
                + "    logger.severe(element.innerHTML);"
                + "}</script></body></html>";
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new InputSource(new StringReader(html)));
        engine.put("document", doc);
        NodeList nodes = doc.getElementsByTagName("p");
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            System.out.println("nody name " + node.getNodeName());
            System.out.println("nody " + node.getTextContent());
            ScriptExecutor.printNode(node);
            //node.setTextContent("Hello java!");
            for (int e = 0; e < node.getAttributes().getLength(); e++) {
                Node n = node.getAttributes().item(e);
                System.out.println("attribut name " + n.getNodeName());
                System.out.println("attribut value " + n.getNodeValue());
            }
        }
        Node ady = doc.createTextNode("<div>hi</div>");
        Node ady1 = doc.createElement("div");
        ady1.setTextContent("hi2");
        NodeList nodys = doc.getElementsByTagName("body");
        Node body = nodys.item(0);
        body.appendChild(ady1);
        Node scr = doc.getElementsByTagName("script").item(0);
        System.out.println(doc.getElementById("tay"));
        XPathFactory xpathFactory = XPathFactory.newInstance();
        XPath xpath = xpathFactory.newXPath();
        Node node = (Node) xpath.evaluate("//*[@id='tay']", doc, XPathConstants.NODE);
        System.out.println(node.getTextContent());
        Node node1 = (Node) xpath.evaluate("//*[@id='tai']", doc, XPathConstants.NODE);
        System.out.println(node1.getTextContent());

//        System.out.println(doc.getFirstChild().getFirstChild().getFirstChild().getAttributes().getNamedItem("id"));
//        System.out.println(engine.get("document"));
        // Define the JavaScript function
        String script = "function changeText() { "
                + "var logger = java.util.logging.Logger.getLogger('ScriptExecutor');\n"
                //                        +"var element = document.createElement('p'); "
                //                        + "element.setAttribute('id','titi');"
                + "var element=document.getElementById('tay');"
                + "element.innerHTML='HIII';"
                + "    logger.severe(element.innerHTML);"
                //                        "if (element) { " +
                //                        "  element.innerHTML = 'Hello Java!'; " +
                //                        "} else { " +
                //                        "    logger.severe('not found ehhh');"+
                //                        "}"
                + "}";

        // Execute the JavaScript function
        engine.eval(scr.getTextContent());
        Invocable inv = (Invocable) engine;
        //inv.invokeFunction("hi");

        // Get the updated HTML string
        TransformerFactory tfactory = TransformerFactory.newInstance();
        Transformer transformer = tfactory.newTransformer();
        StringWriter writer = new StringWriter();
        transformer.transform(new DOMSource((Node) engine.get("document")), new StreamResult(writer));
        String updatedHtml = writer.toString();
        System.out.println(updatedHtml);
        Node textarea = doc.createElement("textarea");
        textarea.setTextContent("Hello");
        System.out.println(textarea);
    }

}
