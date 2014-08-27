package mzhang8;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class HW8RestCloneBean {
	private String clone = null;	
	private ArrayList<HW8RestInfo> reviews = null;
	
	public HW8RestCloneBean() throws IOException {
		String fileName = "/data/swe642fall2013/swe642/resources/mzhang8/HW8restaurants.xml";
		HW8XMLData data = new HW8XMLData();
		if(!((new File(fileName)).exists())) {
			reviews = new ArrayList<HW8RestInfo>();
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
	
	public HW8RestInfo getCloneReview() throws IOException {
		int index = Integer.parseInt(clone);
		HW8RestInfo cloneReview = reviews.get(index);
		HW8RestInfo newReview = new HW8RestInfo(cloneReview.getRName(), cloneReview.getFType(), cloneReview.getStreet(), cloneReview.getCity(), cloneReview.getState(),
    			cloneReview.getZCode(), cloneReview.getYear(), cloneReview.getMonth(), cloneReview.getDay(), cloneReview.getRate(), cloneReview.getComment(), cloneReview.getPrivacy(), cloneReview.getOwner());
		reviews.add(index+1, newReview);
		writeRestInfos(reviews);
		
		return reviews.get(index);
	}
	
	private void writeRestInfos(ArrayList<HW8RestInfo> reviews) throws IOException {
		String fileName = "/data/swe642fall2013/swe642/resources/mzhang8/HW8restaurants.xml";
		HW8XMLData data = new HW8XMLData();
		data.writePrefixToFile(fileName);
    	for(int i = 0; i < reviews.size(); i++) {
    		String content = data.getXML(reviews.get(i));
    		data.writeToFile(fileName, content);
    	}
    	data.writePostfixToFile(fileName);
	}
	
}

