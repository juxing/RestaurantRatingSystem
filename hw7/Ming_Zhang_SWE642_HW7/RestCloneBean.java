package mzhang8;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class RestCloneBean {
	private String clone = null;	
	private ArrayList<RestInfo> reviews = null;
	
	public RestCloneBean() throws IOException {
		String fileName = "/data/swe642fall2013/swe642/resources/mzhang8/restaurants.xml";
		XMLData data = new XMLData();
		if(!((new File(fileName)).exists())) {
			reviews = new ArrayList<RestInfo>();
			FileWriter datafile = new FileWriter (fileName);
		    datafile.close ();
		} else {
			try {
				reviews = data.parse(data.readFile(fileName));
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
	}
	
	public void setClone(String s) {
		clone = s;
	}
	
	public RestInfo getCloneReview() throws IOException {
		int index = Integer.parseInt(clone);
		RestInfo cloneReview = reviews.get(index);
		RestInfo newReview = new RestInfo(cloneReview.getRName(), cloneReview.getFType(), cloneReview.getStreet(), cloneReview.getCity(), cloneReview.getState(),
    			cloneReview.getZCode(), cloneReview.getYear(), cloneReview.getMonth(), cloneReview.getDay(), cloneReview.getRate(), cloneReview.getComment());
		reviews.add(index+1, newReview);
		writeRestInfos(reviews);
		
		return reviews.get(index);
	}
	
	private void writeRestInfos(ArrayList<RestInfo> reviews) throws IOException {
		String fileName = "/data/swe642fall2013/swe642/resources/mzhang8/restaurants.xml";
		XMLData data = new XMLData();
		data.writePrefixToFile(fileName);
    	for(int i = 0; i < reviews.size(); i++) {
    		String content = data.getXML(reviews.get(i));
    		data.writeToFile(fileName, content);
    	}
    	data.writePostfixToFile(fileName);
	}
	
}
