package com.example.assignapp2019s1.maps;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class MapMaker {
    private static final String MAP = "map";
    private static final String POS = "pos";
    private static final String TYPE = "type";
    public static final String PATH = "app/src/main/java/com/example/assignapp2019s1/maps/";
    HashMap<String, String> map = new HashMap<>();

    public MapMaker(HashMap map) {
        this.map = map;
    }
    public MapMaker() {
    }

    public HashMap<String, String> getMap() {
        return this.map;
    }

    static public MapMaker load(String filename) {
        File f = new File(filename);
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db;
        HashMap<String, String> out = new HashMap();

        try {
            // load the xml tree
            db = dbf.newDocumentBuilder();
            Document doc = db.parse(f);

            // parse the tree and obtain the map info
            Node whole = doc.getFirstChild();
            NodeList nl = whole.getChildNodes();
            String buffer = "";
            for (int i =0;i< nl.getLength();i++) {
                Node pos = nl.item(i);
                if (pos.getNodeName().equals(POS)) {
                    buffer = pos.getTextContent();
                } else if (pos.getNodeName().equals(TYPE)&&!buffer.isEmpty()) {
                    out.put(buffer, pos.getTextContent());
                    buffer = "";
                }
            }

        } catch (Exception e) {
            System.err.println(e);
        }
        MapMaker res = new MapMaker(out);
        return res;
    }

    public void save(String filename) {
        File f = new File(filename);
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db;
        try {
            // make the xml tree
            db = dbf.newDocumentBuilder();
            Document doc = db.newDocument();
            Element thisMap = doc.createElement(MAP);
            for (Map.Entry<String, String> entry : map.entrySet()) {
                Element en = doc.createElement(POS);
                en.appendChild(doc.createTextNode(entry.getKey()));
                thisMap.appendChild(en);

                Element ea = doc.createElement(TYPE);
                ea.appendChild(doc.createTextNode(entry.getValue()));
                thisMap.appendChild(ea);
            }
            doc.appendChild(thisMap);
            // save the xml file
            TransformerFactory transformerFactory = TransformerFactory
                    .newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            // set xml encoding to utf-8
            transformer.setOutputProperty(OutputKeys.ENCODING,"utf-8");

            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(f);
            transformer.transform(source, result);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

        public void show() {
        for (Map.Entry<String, String> entry: map.entrySet()) {
                System.out.println("Pos : " + entry.getKey() + "  Type : " + entry.getValue());
            }
    }

    public static void main(String[] args) {
        HashMap<String,String> map1 = new HashMap<>();
        map1.put("A0", "Grass");
        map1.put("A1", "City");
        map1.put("A2", "Road");
        map1.put("A3", "Forest");
        map1.put("A4", "Pipe");
        map1.put("A5", "Water");
        MapMaker data = new MapMaker(map1);
        data.save(PATH + "map1.xml");
        MapMaker dataload = load(PATH + "map1.xml");
        dataload.show();
    }


}
