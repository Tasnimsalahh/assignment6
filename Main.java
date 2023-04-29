
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class Main {
    public static void main(String[] args) {

        if (args.length == 1) {
            // Check input file path
            String filePath = args[0];
            if (!filePath.endsWith(".arxml")) {
                throw new NotVaildAutosarFileException("The input file is not a valid AUTOSAR file.");
            }

            // Parse input file
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder;
            try {
                dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(new File(filePath));
                doc.getDocumentElement().normalize();

                // Check if the file is empty
                Element root = doc.getDocumentElement();
                if (!root.hasChildNodes()) {
                    throw new EmptyAutosarFileException("The input file is empty.");
                }

                // Get all the container elements
                ArrayList<Element> containers = new ArrayList<>();
                Node node = root.getFirstChild();
                while (node != null) {
                    if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("CONTAINER")) {
                        containers.add((Element) node);
                    }
                    node = node.getNextSibling();
                }

                // Sort the container elements by their SHORT-NAME
                Collections.sort(containers, new Comparator<Element>() {
                    public int compare(Element e1, Element e2) {
                        String name1 = e1.getElementsByTagName("SHORT-NAME").item(0).getTextContent();
                        String name2 = e2.getElementsByTagName("SHORT-NAME").item(0).getTextContent();
                        return name1.compareTo(name2);
                    }
                });

                // Write the sorted containers to a new file
                String outputFilePath = filePath.substring(0, filePath.length() - 6) + "_mod.arxml";
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                Document outputDoc = dBuilder.newDocument();
                Element root_element = outputDoc.createElement("AUTOSAR");
                outputDoc.appendChild(root_element);

                for (Element container: containers) {
                    Node importedNode = outputDoc.importNode(container, true);
                    root_element.appendChild(importedNode);
                }

                DOMSource source = new DOMSource(outputDoc);
                StreamResult result = new StreamResult(new File(outputFilePath));
                transformer.transform(source, result);
            } catch (ParserConfigurationException | SAXException | IOException | TransformerException e) {
                e.printStackTrace();
            }
        }
    }


}

class NotVaildAutosarFileException extends RuntimeException {
    public NotVaildAutosarFileException(String message) {
        super(message);
    }
}

class EmptyAutosarFileException extends RuntimeException {
    public EmptyAutosarFileException(String message) {
        super(message);
    }
}