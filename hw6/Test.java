import java.io.FileWriter;
import java.io.IOException;


public class Test {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public void validateXMLByXSD(String xmlFileName, String xsdFileName) {
/*		boolean res;
		
		File xmlFile = new File(xmlFileName);
		File xsdFile = new File(xsdFileName);
		if(!(xmlFile.exists() && xsdFile.exists()))
			return false;
		
		final String SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
		final String XML_SCHEMA = "http://www.w3.org/2001/XMLSchema";
		final String SCHEMA_SOURCE = "http://java.sun.com/xml/jaxp/properties/schemaSource";
		
		SAXParserFactory factory = SAXParserFactory.newInstance();
		factory.setNamespaceAware(true);
		factory.setValidating(true);
		
		try {
			SAXParser parser = factory.newSAXParser();
			parser.setProperty(SCHEMA_LANGUAGE, XML_SCHEMA);
			parser.setProperty(SCHEMA_SOURCE, xsdFile);
		
			XMLReader xmlReader = parser.getXMLReader();
			xmlReader.setContentHandler(new DefaultHandler());
			//xmlReader.setErrorHandler(new MyErrorHandler(System.out));
			xmlReader.parse(xmlFileName);
			res = true;
			
		} catch (ParserConfigurationException | SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			res = false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			res = false;
		}
		
		return res;*/
		String fileName = "/data/swe642fall2013/swe642/resources/mzhang8/restaurants.xml";  //Backup file.
    	FileWriter data;
		try {
			data = new FileWriter (fileName);
			data.write("<Restaurants>\n");
			data.close();
			data.close ();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    	
		
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		new Test().validateXMLByXSD("/home/mzhang8/SWEWWW/hw6/restaurants.xml", "/home/mzhang8/SWEWWW/hw6/rest_schema.xsd");
	}

}
