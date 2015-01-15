package MarauderPNS.readXML;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

/**
 * Created by Garance on 15/01/2015.
 */
public class ReadXML {
    private File fXmlFile;

    public ReadXML() {

    }

    public Document readFile(String name) {
        try {
            fXmlFile = new File(name+".xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(getFile());

            doc.getDocumentElement().normalize();
            return doc;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public File getFile() {
        return fXmlFile;
    }

    public void setFile(File file) {
        fXmlFile = file;
    }
}
