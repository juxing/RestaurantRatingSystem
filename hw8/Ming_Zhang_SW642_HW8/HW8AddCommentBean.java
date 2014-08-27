package mzhang8;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class HW8AddCommentBean {
	private String add = null;	
	private String addComment = null;
	private String writer = null;
	private ArrayList<HW8RestInfo> reviews = null;
	
	public HW8AddCommentBean() throws IOException {
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
	
	public void setAdd(String a) {
		add = a;
	}
	
	public void setAddComment(String ac) {
		addComment = ac;
	}
	
	public void setWriter(String w) {
		writer = w;
	}
	
	public void addNewComment() throws IOException {
		if(addComment == null)
			return;
		int index = Integer.parseInt(add);
		HW8RestInfo oldReview = reviews.get(index);
		String oldComment = oldReview.getComment();
		String newComment = oldComment + "\n\n" + addComment + " -- " + writer;			
		oldReview.setComment(newComment);
		writeRestInfos(reviews);
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


