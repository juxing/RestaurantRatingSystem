package mzhang8;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLData {
	private ArrayList<RestInfo> reviews = null;	    	
	private String rName = "";
	private String fType = "";
	private String street = "";
	private String city = "";
	private String state = "";
	private String zCode = "";
	private String year = "";
	private String month = "";
	private String day = "";
	private String rate = "";
	private String comment = "";
	private boolean recordStart = false;
	private String previousNodeName = "";
	
	public Document readFile(String fileName) throws ParserConfigurationException, SAXException, IOException {
		String uri = "file:" + (new File(fileName)).getAbsolutePath();
		return readDoc(uri);
	}
	
	public Document readDoc(String uri) throws ParserConfigurationException, SAXException, IOException {
		Document doc;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		doc = db.parse(uri);
		return doc;
	}
	
	public ArrayList<RestInfo> parse(Node node) {
		reviews = new ArrayList<RestInfo>();
		recordStart = false;
		previousNodeName = "";
		parseRec(node);
		reviews.add(new RestInfo(rName, fType, street, city, state, zCode, year, month, day, rate, comment));
		return reviews;
	}
	
	public void parseRec(Node node) {
		short nodeType = node.getNodeType();
		if(nodeType == Node.ELEMENT_NODE) {
			String nodeName = node.getNodeName();
			previousNodeName = nodeName;
			if(nodeName.equals("Restaurant")) {
				if(recordStart == false)
					recordStart = true;
				else {
					reviews.add(new RestInfo(rName, fType, street, city, state, zCode, year, month, day, rate, comment));
				}
			}
			parseChildren(node);
		}
		else if(nodeType == Node.TEXT_NODE) {
			if(previousNodeName.equals("RName"))
				rName = node.getNodeValue().trim();
			else if(previousNodeName.equals("FType"))
				fType = node.getNodeValue().trim();
			else if(previousNodeName.equals("Street"))
				street = node.getNodeValue().trim();
			else if(previousNodeName.equals("City"))
				city = node.getNodeValue().trim();
			else if(previousNodeName.equals("State"))
				state = node.getNodeValue().trim();
			else if(previousNodeName.equals("ZCode"))
				zCode = node.getNodeValue().trim();
			else if(previousNodeName.equals("Year"))
				year = node.getNodeValue().trim();
			else if(previousNodeName.equals("Month"))
				month = node.getNodeValue().trim();
			else if(previousNodeName.equals("Day"))
				day = node.getNodeValue().trim();
			else if(previousNodeName.equals("Rate"))
				rate = node.getNodeValue().trim();
			else if(previousNodeName.equals("Comment"))
				comment = node.getNodeValue().trim();
			
			previousNodeName = "";
		}
		else if(nodeType == Node.DOCUMENT_NODE) {
			parseChildren(node);
		}		
	}
	
	public void parseChildren(Node node) {
		NodeList children = node.getChildNodes();
		for(int i = 0; i < children.getLength(); i++)
			parseRec(children.item(i));
	}
	
	public void writePrefixToFile(String fileName) throws IOException {
		FileWriter data = new FileWriter(fileName);
		/*data.write("<?xml version=\"1.0\"?>\n");
		data.write("<xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" targetNamespace=\"http://www.w3schools.com\" xmlns=\"http://www.w3schools.com\" elementFormatDefault=\"qualified\">\n");*/
		data.write("<Restaurants>\n");
		data.close();
	}
	
	public void writePostfixToFile(String fileName) throws IOException {
		FileWriter data = new FileWriter(fileName, true);
		data.write("</Restaurants>");
		data.close();
	}
	
	public void writeToFile(String fileName, String mes) throws IOException {
		FileWriter data = new FileWriter(fileName, true);
		data.write(mes);
		data.close();
	}
	
	public String getXML(RestInfo ri) {
		StringBuffer sb = new StringBuffer(4096);
		sb.append("	<Restaurant>\n");
		sb.append("		<RName>");
		sb.append(ri.getRName());
		sb.append("</RName>\n");
		sb.append("		<FType>");
		sb.append(ri.getFType());
		sb.append("</FType>\n");
		sb.append("		<Street>");
		sb.append(ri.getStreet());
		sb.append("</Street>\n");
		sb.append("		<City>");
		sb.append(ri.getCity());
		sb.append("</City>\n");
		sb.append("		<State>");
		sb.append(ri.getState());
		sb.append("</State>\n");		
		sb.append("		<ZCode>");
		sb.append(ri.getZCode());
		sb.append("</ZCode>\n");
		sb.append("		<Year>");
		sb.append(ri.getYear());
		sb.append("</Year>\n");
		sb.append("		<Month>");
		sb.append(ri.getMonth());
		sb.append("</Month>\n");
		sb.append("		<Day>");
		sb.append(ri.getDay());
		sb.append("</Day>\n");
		sb.append("		<Rate>");
		sb.append(ri.getRate());
		sb.append("</Rate>\n");
		sb.append("		<Comment>");
		sb.append(ri.getComment());
		sb.append("</Comment>\n");
		sb.append("	</Restaurant>\n");
		
		return sb.toString();		
	}
}
