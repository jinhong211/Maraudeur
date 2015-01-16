package MarauderPNS.readXML;

import MarauderPNS.map.Square;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Garance on 15/01/2015.
 */
public class ReadXMLMap {
    Document doc;
    ReadXML readXML;

    public ReadXMLMap() {
        readXML = new ReadXML();
        readFile();
    }

    public void readFile() {
        doc = readXML.readFile("map");
        //Chekcing the root element
        assert (doc.getDocumentElement().getNodeName().equalsIgnoreCase("map"));
    }
    public ArrayList<int[]> processRaws() {
         ArrayList<int[]> raws = new ArrayList<>();

            NodeList nList = doc.getElementsByTagName("line");
/*
A la fin, je vais te renvoyer une liste de tableau à 3 éléments
Une méthode processRaws
une méthode processCols*/
            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    int lineNo = Integer.parseInt(eElement.getAttribute("id"));
                    //For each time we find a "debut" in a line, we want to create a new array[3]
                    for (int j = 0; j<eElement.getElementsByTagName("debut").getLength();j++) {
                        int theWalls[] = new int[3];
                        theWalls[0] = lineNo;
                        theWalls[1] = Integer.parseInt(eElement.getElementsByTagName("debut").item(0).getTextContent());
                        theWalls[2] = Integer.parseInt(eElement.getElementsByTagName("fin").item(0).getTextContent());
                        raws.add(theWalls);
                    }
                }
            }
        return raws;
    }

    public ArrayList<int[]> processCols() {
        ArrayList<int[]> cols = new ArrayList<>();

        NodeList nList = doc.getElementsByTagName("col");

        for (int i = 0; i < nList.getLength(); i++) {
            Node nNode = nList.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                int lineNo = Integer.parseInt(eElement.getAttribute("id"));
                //For each time we find a "debut" in a line, we want to create a new array[3]
                for (int j = 0; j<eElement.getElementsByTagName("debut").getLength();j++) {
                    int theWalls[] = new int[3];
                    theWalls[0] = lineNo;
                    theWalls[1] = Integer.parseInt(eElement.getElementsByTagName("debut").item(0).getTextContent());
                    theWalls[2] = Integer.parseInt(eElement.getElementsByTagName("fin").item(0).getTextContent());;
                    cols.add(theWalls);
                }
            }
        }
        return cols;
    }
}


