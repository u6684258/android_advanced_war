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
    public static final String PATH = "src/main/java/com/example/assignapp2019s1/maps/";
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
        map1.put("A0", "Water");
        map1.put("A1", "Water");
        map1.put("A2", "Grass");
        map1.put("A3", "Grass");
        map1.put("A4", "Road");
        map1.put("A5", "Grass");
        map1.put("A6", "Workshop");
        map1.put("A7", "HeadQuarters");
        map1.put("B0", "Water");
        map1.put("B1", "Water");
        map1.put("B2", "Water");
        map1.put("B3", "Mountain");
        map1.put("B4", "Road");
        map1.put("B5", "Grass");
        map1.put("B6", "Grass");
        map1.put("B7", "Workshop");
        map1.put("C0", "City");
        map1.put("C1", "Grass");
        map1.put("C2", "Water");
        map1.put("C3", "Mountain");
        map1.put("C4", "Road");
        map1.put("C5", "Grass");
        map1.put("C6", "Grass");
        map1.put("C7", "Grass");
        map1.put("D0", "Grass");
        map1.put("D1", "Grass");
        map1.put("D2", "Water");
        map1.put("D3", "Water");
        map1.put("D4", "Road");
        map1.put("D5", "Grass");
        map1.put("D6", "Grass");
        map1.put("D7", "Grass");
        map1.put("E0", "Grass");
        map1.put("E1", "Grass");
        map1.put("E2", "Mountain");
        map1.put("E3", "Road");
        map1.put("E4", "Road");
        map1.put("E5", "Grass");
        map1.put("E6", "Grass");
        map1.put("E7", "Grass");
        map1.put("F0", "Forest");
        map1.put("F1", "Grass");
        map1.put("F2", "Grass");
        map1.put("F3", "Water");
        map1.put("F4", "Road");
        map1.put("F5", "Grass");
        map1.put("F6", "Grass");
        map1.put("F7", "Grass");
        map1.put("G0", "Grass");
        map1.put("G1", "Grass");
        map1.put("G2", "Mountain");
        map1.put("G3", "Road");
        map1.put("G4", "Road");
        map1.put("G5", "Forest");
        map1.put("G6", "Grass");
        map1.put("G7", "Grass");
        map1.put("H0", "City");
        map1.put("H1", "Forest");
        map1.put("H2", "Grass");
        map1.put("H3", "Road");
        map1.put("H4", "Water");
        map1.put("H5", "City");
        map1.put("H6", "Grass");
        map1.put("H7", "Grass");
        map1.put("I0", "Grass");
        map1.put("I1", "Grass");
        map1.put("I2", "Grass");
        map1.put("I3", "Road");
        map1.put("I4", "Water");
        map1.put("I5", "Grass");
        map1.put("I6", "Grass");
        map1.put("I7", "Grass");
        map1.put("J0", "Forest");
        map1.put("J1", "City");
        map1.put("J2", "Mountain");
        map1.put("J3", "Road");
        map1.put("J4", "Road");
        map1.put("J5", "Road");
        map1.put("J6", "Road");
        map1.put("J7", "Road");
        map1.put("K0", "Road");
        map1.put("K1", "Road");
        map1.put("K2", "Road");
        map1.put("K3", "Road");
        map1.put("K4", "Water");
        map1.put("K5", "Grass");
        map1.put("K6", "Grass");
        map1.put("K7", "Forest");
        map1.put("L0", "Grass");
        map1.put("L1", "Grass");
        map1.put("L2", "Grass");
        map1.put("L3", "Road");
        map1.put("L4", "Water");
        map1.put("L5", "Water");
        map1.put("L6", "Grass");
        map1.put("L7", "City");
        map1.put("M0", "Grass");
        map1.put("M1", "Grass");
        map1.put("M2", "Grass");
        map1.put("M3", "Road");
        map1.put("M4", "Mountain");
        map1.put("M5", "Water");
        map1.put("M6", "Forest");
        map1.put("M7", "Grass");
        map1.put("N0", "Grass");
        map1.put("N1", "Grass");
        map1.put("N2", "Grass");
        map1.put("N3", "Road");
        map1.put("N4", "Road");
        map1.put("N5", "Mountain");
        map1.put("N6", "Mountain");
        map1.put("N7", "City");
        map1.put("O0", "Workshop");
        map1.put("O1", "Grass");
        map1.put("O2", "Grass");
        map1.put("O3", "Grass");
        map1.put("O4", "Road");
        map1.put("O5", "Mountain");
        map1.put("O6", "Water");
        map1.put("O7", "Water");
        map1.put("P0", "HeadQuarters");
        map1.put("P1", "Workshop");
        map1.put("P2", "Grass");
        map1.put("P3", "Grass");
        map1.put("P4", "Road");
        map1.put("P5", "Mountain");
        map1.put("P6", "Grass");
        map1.put("P7", "Grass");
        //pic: map1


        MapMaker data = new MapMaker(map1);
        data.save(PATH + "map1.xml");
        MapMaker dataload = load(PATH + "map1.xml");
        dataload.show();
    }


}
