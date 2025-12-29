/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package script;

import java.io.StringWriter;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Node;

/**
 *
 * @author kaloina.melo
 */
public class Funct {

    public static String toHtml(Node node)throws Exception {
        StringWriter writer = new StringWriter();

// Get a TransformerFactory and create a new Transformer
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

// Set the output properties to generate HTML
        transformer.setOutputProperty(OutputKeys.METHOD, "html");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

// Create a DOMSource from the Node object
        DOMSource source = new DOMSource(node);

// Create a StreamResult to write the HTML output to
        StreamResult result = new StreamResult(writer);

// Transform the DOMSource to the StreamResult
        transformer.transform(source, result);

// Get the HTML output as a String
        return writer.toString();
    }
}
