package MarauderPNS.simulation;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
/**
 * Created by Garance on 15/01/2015.
 */
public class ReadXMLConfig {

    public ReadXMLConfig() {

    }
    public void readFile() {
    try {

        File fXmlFile = new File("./../../../../../config.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(fXmlFile);

        doc.getDocumentElement().normalize();

        //Chekcing the root element
        if (doc.getDocumentElement().getNodeName().equalsIgnoreCase("maurauder")) {

        NodeList nList = doc.getElementsByTagName("staff");

/*
<company>
	<staff id="1001">
		<firstname>yong</firstname>
		<lastname>mook kim</lastname>
		<nickname>mkyong</nickname>
		<salary>100000</salary>
	</staff>
	<staff id="2001">
		<firstname>low</firstname>
		<lastname>yin fong</lastname>
		<nickname>fong fong</nickname>
		<salary>200000</salary>
	</staff>
</company>
 */
        for (int temp = 0; temp < nList.getLength(); temp++) {

            Node nNode = nList.item(temp);

            //  System.out.println("\nCurrent Element :" + nNode.getNodeName());

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                Element eElement = (Element) nNode;

                //    System.out.println("Staff id : " + eElement.getAttribute("id"));
                //    System.out.println("First Name : " + eElement.getElementsByTagName("firstname").item(0).getTextContent());
                //    System.out.println("Last Name : " + eElement.getElementsByTagName("lastname").item(0).getTextContent());
                //   System.out.println("Nick Name : " + eElement.getElementsByTagName("nickname").item(0).getTextContent());
                //   System.out.println("Salary : " + eElement.getElementsByTagName("salary").item(0).getTextContent());

            }
        }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}
}
