package arquivos;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class LerXmlRedmine {
	
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		dbf.setNamespaceAware(false);

		DocumentBuilder docBuilder = dbf.newDocumentBuilder();

		Document doc = docBuilder.parse(new File("F:/activity.atom"));
		
		NodeList nList = doc.getElementsByTagName("entry");
		
		for (int i = 0; i < nList.getLength(); i++) {
			Node nNode = nList.item(i);
			//System.out.println("\nElemento corrente :" + nNode.getNodeName());
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				System.out.println("----------------------------");
				System.out.println("TAREFA "+(i+1)+": " + eElement.getElementsByTagName("title").item(0).getTextContent());
				System.out.println("URL: " + eElement.getElementsByTagName("id").item(0).getTextContent());
				
				
			}
		}

	}
}
