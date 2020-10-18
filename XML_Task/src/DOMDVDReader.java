import javax.xml.parsers.*;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.xml.sax.*;
import org.w3c.dom.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class DOMDVDReader {
    public NodeList getNodeList(String filepath) {
        DocumentBuilderFactory dbf  = DocumentBuilderFactory.newInstance();
        dbf.setValidating(true);

        DocumentBuilder db = null;
        try {
            db =  dbf.newDocumentBuilder();
            db.setErrorHandler(new  ErrorHandler() {
                public void error(SAXParseException spe) {
                    System.err.println(spe);
                }
                public void fatalError(SAXParseException spe){
                    System.err.println(spe);
                }
                public void warning(SAXParseException spe) {
                    System.out.println(spe);
                }
            });
        } catch (ParserConfigurationException  pce) {
            System.err.println(pce);
            System.exit(1);
        }

        Document doc = null;
        try {
            doc = db.parse(new File(filepath));
        }  catch (SAXException  se) {
            System.err.println(se);
        }  catch (IOException  ioe) {
            System.err.println(ioe);
        }
        NodeList nodeList =  doc.getDocumentElement().getChildNodes();
        return nodeList;
    }

    // count by decade
    public void count(NodeList nodeList, Map<String, Integer> countMap) {
        if (nodeList == null) {
            return;
        }
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node child_node = nodeList.item(i);
            if (child_node.getNodeType() == Node.ELEMENT_NODE) {
                Element el = (Element)child_node;
                if (el.getTagName().equals("release_year")) {
                    String temp = el.getTextContent();
                    temp = temp.substring(0,3) + '0'; // convert year to decade
                    if (countMap.get(temp) == null) {
                        countMap.put(temp, 1);
                    } else {
                        Integer cur = countMap.get(temp);
                        countMap.put(temp, ++cur);
                    }
                }
            }
            count(child_node.getChildNodes(), countMap);
        }
    }
    public void xmlWriter(String fn, Map<String, Integer> countMap) {
        XMLStreamWriter xw =  null;
        XMLOutputFactory xof = XMLOutputFactory.newInstance();
        try {
            xw =  xof.createXMLStreamWriter(new FileWriter(fn));
            xw.writeStartDocument("1.0");
            xw.writeDTD("<!DOCTYPE  DVD SYSTEM \"dvd-2.dtd\">");
            xw.writeStartElement("DVD");
            xw.writeStartElement("summary");

            for (Map.Entry<String, Integer>  item : countMap.entrySet()) {
                xw.writeStartElement("count");
                xw.writeAttribute("decade",  item.getKey());
                xw.writeCharacters(String.valueOf(item.getValue()));
                xw.writeEndElement();
            }
            xw.writeEndElement();  // close the summary tag
            xw.writeEndElement();  // close the DVD tag
            xw.writeEndDocument();
            xw.flush();
            xw.close();

        } catch  (
                XMLStreamException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void  main(String[] args) {
        DOMDVDReader domDVDReader  = new DOMDVDReader();
        NodeList nodeList = domDVDReader.getNodeList("dvd.xml");
        Map<String, Integer> countMap = new HashMap<>();
        domDVDReader.count(nodeList, countMap);
        System.out.println(countMap);
        domDVDReader.xmlWriter("dvd-2.xml", countMap);
    }
}