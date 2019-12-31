import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;

public class XmlProcessor {

    public void deleteNodefromXml() {
        try {
            File file = new File("file/tmp.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            File output = new File("file/bezNip.xml");
            PrintWriter pw = new PrintWriter(output);

            Document doc = builder.parse(file);

            doc.getElementsByTagName("ks:wpisy").item(0).getAttributes().removeNamedItem("stanNaDzien");

            NodeList nodeList = doc.getElementsByTagName("oso:NIP");
            NodeList nodeListOp = doc.getElementsByTagName("typ:OswiadczeniePierwsze");
            NodeList nodeListDr = doc.getElementsByTagName("typ:OswiadczenieDrugie");
            NodeList nodeListTr = doc.getElementsByTagName("typ:OswiadczenieTrzecie");
            NodeList nodeListCz = doc.getElementsByTagName("typ:OswiadczenieCzwarte");
            NodeList nodeListOsT = doc.getElementsByTagName("typ:OswiadczenieTresc");
            NodeList nodeListOs = doc.getElementsByTagName("typ:OswiadczenieData");
            NodeList nodeListOsD = doc.getElementsByTagName("typ:Oswiadczenie");
            int size = nodeList.getLength();
           for(int i = 0; i< size;i++) {
               Node n = nodeList.item(0);
               Element e = (Element) n;
               e.getParentNode().removeChild(e);
           }
            size = nodeListOp.getLength();
            for(int i = 0; i< size;i++) {
                Node n = nodeListOp.item(0);
                Element e = (Element) n;
                e.getParentNode().removeChild(e);
            }
            size = nodeListDr.getLength();
            for(int i = 0; i< size;i++) {
                Node n = nodeListDr.item(0);
                Element e = (Element) n;
                e.getParentNode().removeChild(e);
            }
            size = nodeListTr.getLength();
            for(int i = 0; i< size;i++) {
                Node n = nodeListTr.item(0);
                Element e = (Element) n;
                e.getParentNode().removeChild(e);
            }
            size = nodeListCz.getLength();
            for(int i = 0; i< size;i++) {
                Node n = nodeListCz.item(0);
                Element e = (Element) n;
                e.getParentNode().removeChild(e);
            }
            size = nodeListOsD.getLength();
            for(int i = 0; i< size;i++) {
                Node n = nodeListOsD.item(0);
                Element e = (Element) n;
                e.getParentNode().removeChild(e);
            }
            size = nodeListOsT.getLength();
            for(int i = 0; i< size;i++) {
                Node n = nodeListOsT.item(0);
                Element e = (Element) n;
                e.getParentNode().removeChild(e);
            }
            size = nodeListTr.getLength();
            for(int i = 0; i< size;i++) {
                Node n = nodeListTr.item(0);
                Element e = (Element) n;
                e.getParentNode().removeChild(e);
            }
            size = nodeListOs.getLength();
            for(int i = 0; i< size;i++) {
                Node n = nodeListOs.item(0);
                Element e = (Element) n;
                e.getParentNode().removeChild(e);
            }
            System.out.println(doc.getElementsByTagName("oso:NIP").getLength());
            doc.normalize();
            try {
                Transformer tf = TransformerFactory.newInstance().newTransformer();
                tf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
                tf.setOutputProperty(OutputKeys.INDENT, "yes");
                Writer out = new StringWriter();
                try {
                    tf.transform(new DOMSource(doc), new StreamResult(out));
                } catch (TransformerException e) {
                    e.printStackTrace();
                }
                //System.out.println(out.toString());
                pw.println(out.toString());
            } catch (TransformerConfigurationException e) {
                e.printStackTrace();
            }
            pw.close();
        }
        catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void deleteNodeFromSolrXml() {
        try {
            File file = new File("file/tmpSolr.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            File output = new File("file/bezWskazan.xml");
            PrintWriter pw = new PrintWriter(output);

            Document doc = builder.parse(file);

            //doc.getElementsByTagName("ks:wpisy").item(0).getAttributes().removeNamedItem("stanNaDzien");

            NodeList nodeList = doc.getElementsByTagName("wskazania");
            int size = nodeList.getLength();
            for(int i = 0; i< size;i++) {
                Node n = nodeList.item(i);
                Node n1 = n.getParentNode().getParentNode();
                NodeList n2 = n1.getChildNodes();
                int n2Size = n2.getLength();
                for(int j = 0; j <= n2Size ; j++) {
                    if(n2.item(j) != null)
                    if (n2.item(j).getNodeName().equals("rodzajLekuEnum")) {
                        Node tmpNode = n2.item(j);
                        System.out.println(tmpNode.getNodeName());
                        if(tmpNode.getTextContent().equals("PL")) {
                            System.out.println(tmpNode.getNodeName() + ": " + tmpNode.getTextContent());
                            Element e = (Element) n;
                            e.getParentNode().removeChild(e);
                        }
                        /*
                        Element e = (Element) n;
                        e.getParentNode().removeChild(e);

                         */
                    }
                }
            }
            doc.normalize();
            try {
                Transformer tf = TransformerFactory.newInstance().newTransformer();
                tf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
                tf.setOutputProperty(OutputKeys.INDENT, "yes");
                Writer out = new StringWriter();
                try {
                    tf.transform(new DOMSource(doc), new StreamResult(out));
                } catch (TransformerException e) {
                    e.printStackTrace();
                }
                System.out.println(out.toString());
                pw.println(out.toString());
            } catch (TransformerConfigurationException e) {
                e.printStackTrace();
            }
            pw.close();
        }
        catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void newDeleteWskazaniaFromSorl() {
        try {
            File file = new File("file/tmpSolr.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            File output = new File("file/bezWskazanPL_v20191105.xml");
            PrintWriter pw = new PrintWriter(output);

            Document doc = builder.parse(file);

            //doc.getElementsByTagName("ks:wpisy").item(0).getAttributes().removeNamedItem("stanNaDzien");

            NodeList nodeList = doc.getElementsByTagName("rodzajLekuEnum");
            int size = nodeList.getLength();
            for(int i = 0; i< size;i++) {
                Node n = nodeList.item(i);
                Element e = (Element) n.getParentNode();
                if(n.getTextContent().equals("PL")) {
                    NodeList wskazania = e.getElementsByTagName("wskazania");
                    int wskazaniaSize = wskazania.getLength();
                    System.out.println(wskazaniaSize);
                    for(int j = 0; j<wskazaniaSize;j++) {
                        Node wskazniaNode = wskazania.item(j);
                        if(wskazniaNode != null) {
                            Node wskazaniaParnet = wskazniaNode.getParentNode();
                            wskazaniaParnet.removeChild(wskazniaNode);
                            Node newWskazaniaNode = doc.createElement("wskazania");
                            wskazaniaParnet.appendChild(newWskazaniaNode);
                        }
                    }
                }
                /*
                Node n1 = n.getParentNode().getParentNode();
                NodeList n2 = n1.getChildNodes();
                int n2Size = n2.getLength();
                for(int j = 0; j <= n2Size ; j++) {
                    if(n2.item(j) != null)
                        if (n2.item(j).getNodeName().equals("rodzajLekuEnum")) {
                            Node tmpNode = n2.item(j);
                            System.out.println(tmpNode.getNodeName());
                            if(tmpNode.getTextContent().equals("PL")) {
                                System.out.println(tmpNode.getNodeName() + ": " + tmpNode.getTextContent());
                                Element e = (Element) n;
                                e.getParentNode().removeChild(e);
                            }

                        Element e = (Element) n;
                        e.getParentNode().removeChild(e);


                        }
                }
                */
            }
            doc.normalize();
            try {
                Transformer tf = TransformerFactory.newInstance().newTransformer();
                tf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
                tf.setOutputProperty(OutputKeys.INDENT, "yes");
                Writer out = new StringWriter();
                try {
                    tf.transform(new DOMSource(doc), new StreamResult(out));
                } catch (TransformerException e) {
                    e.printStackTrace();
                }
                System.out.println(out.toString());
                pw.println(out.toString());
            } catch (TransformerConfigurationException e) {
                e.printStackTrace();
            }
            pw.close();
        }
        catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
