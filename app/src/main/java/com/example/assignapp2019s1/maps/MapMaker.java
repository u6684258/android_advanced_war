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
    public static final String PATH = "/Users/ran/StudioProjects/assignapp2019s1/app/src/main/java/com/example/assignapp2019s1/maps/";
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
        HashMap<String,String> map2 = new HashMap<>();
        map2.put("A0", "Water");
        map2.put("A1", "Water");
        map2.put("A2", "Grass");
        map2.put("A3", "Grass");
        map2.put("A4", "Road");
        map2.put("A5", "Grass");
        map2.put("A6", "Workshop");
        map2.put("A7", "City");
        map2.put("B0", "Water");
        map2.put("B1", "Water");
        map2.put("B2", "Water");
        map2.put("B3", "Mountain");
        map2.put("B4", "Road");
        map2.put("B5", "Grass");
        map2.put("B6", "Grass");
        map2.put("B7", "Workshop");
        map2.put("C0", "City");
        map2.put("C1", "Grass");
        map2.put("C2", "Water");
        map2.put("C3", "Mountain");
        map2.put("C4", "Road");
        map2.put("C5", "Grass");
        map2.put("C6", "Grass");
        map2.put("C7", "Grass");
        map2.put("D0", "Grass");
        map2.put("D1", "Grass");
        map2.put("D2", "Water");
        map2.put("D3", "Water");
        map2.put("D4", "Road");
        map2.put("D5", "Grass");
        map2.put("D6", "Grass");
        map2.put("D7", "Grass");
        map2.put("E0", "Grass");
        map2.put("E1", "Grass");
        map2.put("E2", "Mountain");
        map2.put("E3", "Water");
        map2.put("E4", "Road");
        map2.put("E5", "Grass");
        map2.put("E6", "Grass");
        map2.put("E7", "Grass");
        map2.put("F0", "Forest");
        map2.put("F1", "Grass");
        map2.put("F2", "Grass");
        map2.put("F3", "Water");
        map2.put("F4", "Road");
        map2.put("F5", "Grass");
        map2.put("F6", "Grass");
        map2.put("F7", "Grass");
        map2.put("G0", "Grass");
        map2.put("G1", "Grass");
        map2.put("G2", "Grass");
        map2.put("G3", "Road");
        map2.put("G4", "Road");
        map2.put("G5", "Forest");
        map2.put("G6", "Grass");
        map2.put("G7", "Grass");
        map2.put("H0", "City");
        map2.put("H1", "Grass");
        map2.put("H2", "Grass");
        map2.put("H3", "Road");
        map2.put("H4", "Water");
        map2.put("H5", "City");
        map2.put("H6", "Grass");
        map2.put("H7", "Grass");
        map2.put("I0", "Grass");
        map2.put("I1", "Grass");
        map2.put("I2", "Grass");
        map2.put("I3", "Road");
        map2.put("I4", "Water");
        map2.put("I5", "Grass");
        map2.put("I6", "Grass");
        map2.put("I7", "Grass");
        map2.put("J0", "Forest");
        map2.put("J1", "Grass");
        map2.put("J2", "Mountain");
        map2.put("J3", "Road");
        map2.put("J4", "Road");
        map2.put("J5", "Road");
        map2.put("J6", "Road");
        map2.put("J7", "Road");
        map2.put("K0", "Road");
        map2.put("K1", "Road");
        map2.put("K2", "Road");
        map2.put("K3", "Road");
        map2.put("K4", "Water");
        map2.put("K5", "Grass");
        map2.put("K6", "Grass");
        map2.put("K7", "Forest");
        map2.put("L0", "Grass");
        map2.put("L1", "Grass");
        map2.put("L2", "Grass");
        map2.put("L3", "Road");
        map2.put("L4", "Water");
        map2.put("L5", "Water");
        map2.put("L6", "Grass");
        map2.put("L7", "City");
        map2.put("M0", "Grass");
        map2.put("M1", "Grass");
        map2.put("M2", "Grass");
        map2.put("M3", "Road");
        map2.put("M4", "Mountain");
        map2.put("M5", "Water");
        map2.put("M6", "Water");
        map2.put("M7", "Grass");
        map2.put("N0", "Grass");
        map2.put("N1", "Grass");
        map2.put("N2", "Grass");
        map2.put("N3", "Road");
        map2.put("N4", "Road");
        map2.put("N5", "Mountain");
        map2.put("N6", "Water");
        map2.put("N7", "Grass");
        map2.put("O0", "Workshop");
        map2.put("O1", "Grass");
        map2.put("O2", "Grass");
        map2.put("O3", "Grass");
        map2.put("O4", "Road");
        map2.put("O5", "Mountain");
        map2.put("O6", "Water");
        map2.put("O7", "Water");
        map2.put("P0", "City");
        map2.put("P1", "Workshop");
        map2.put("P2", "Grass");
        map2.put("P3", "Grass");
        map2.put("P4", "Road");
        map2.put("P5", "Mountain");
        map2.put("P6", "Grass");
        map2.put("P7", "Grass");


        MapMaker data = new MapMaker(map2);
        data.save(PATH + "map2.xml");
        MapMaker dataload = load(PATH + "map2.xml");
        dataload.show();
    }


}
