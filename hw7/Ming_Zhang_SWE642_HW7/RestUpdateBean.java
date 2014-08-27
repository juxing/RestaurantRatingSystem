package mzhang8;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class RestUpdateBean {
	private String update = null;
	
	private String restaurantName = null;
	private String foodType = null;
	private String street = null;
	private String city = null;
	private String state = null;
	private String zipCode = null;
	private String year = null;
	private String month = null;
	private String day = null;
	private String rate = null;
	private String comment = null;
	
	private boolean bname = true;
	private boolean bstreet = true;
	private boolean bcity = true;
	private boolean bstate = true;
	private boolean bzcode = true;
	private boolean byear = true;
	private boolean bmonth = true;
	private boolean bday = true;
	
	private String errMsg = null;
	
	private ArrayList<RestInfo> reviews = null;
	
	public RestUpdateBean() throws IOException {
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
	
	public void setUpdate(String s) {
		update = s;
	}
	
	public void setRestaurantName(String name) {
		restaurantName = name;
	}
	
	public void setFoodType(String ft) {
		foodType = ft;
	}
	
	public void setStreet(String st) {
		street = st;
	}
	
	public void setCity(String c) {
		city = c;
	}
	
	public void setState(String s) {
		state = s;
	}
	
	public void setZipCode(String zc) {
		zipCode = zc;
	}
	
	public void setYear(String y) {
		year = y;
	}
	
	public void setMonth(String m) {
		month = m;
	}
	
	public void setDay(String d) {
		day = d;
	}
	
	public void setRate(String r) {
		rate = r;
	}
	
	public void setComment(String c) {
		comment = c;
	}
	
	public String getUpdate() {
		return update;
	}
	
	public String getRestaurantName() {
		return restaurantName;
	}
	
	public String getFoodType() {
		return foodType;
	}
	
	public String getStreet() {
		return street;
	}
	
	public String getCity() {
		return city;
	}
	
	public String getState() {
		return state;
	}
	
	public String getZipCode() {
		return zipCode;
	}
		
	public String getYear() {
		return year;
	}
	
	public String getMonth() {
		return month;
	}
	
	public String getDay() {
		return day;
	}
	
	public String getRate() {
		return rate;
	}
	
	public String getComment() {
		return comment;
	}
	
	public String getErrMsg() {
		return errMsg;
	}
	
	public RestInfo getUpdateReview() {
		int index = Integer.parseInt(update);
		return reviews.get(index);
	}
	
	private boolean checkYear(String y) {   	
    	if(y.length() != 4)
    		return false;
    	
    	for (int i = 0; i < y.length(); i++){
    		 if (!Character.isDigit(y.charAt(i))){
    			 return false;
    		 }
    	}
    	return true;
    }
    
    private boolean checkMonth(String m) {   	
    	if((m.length() != 1) && (m.length() != 2))
    		return false;
    	
    	for (int i = 0; i < m.length(); i++){
    		 if (!Character.isDigit(m.charAt(i))){
    			 return false;
    		 }
    	}
    	
    	int mm = Integer.parseInt(m);
    	if((mm > 12) || (mm < 1))
    		return false;
    	
    	return true;
    }
    
    private boolean checkDay(String d) {   	
    	if((d.length() != 1) && (d.length() != 2))
    		return false;
    	
    	for (int i = 0; i < d.length(); i++){
    		 if (!Character.isDigit(d.charAt(i))){
    			 return false;
    		 }
    	}
    	
    	int dd = Integer.parseInt(d);
    	if((dd > 31) || (dd < 1))
    		return false;
    	
    	return true;
    }
    
    private boolean checkZipCode(String zc) {   	    	
    	for (int i = 0; i < zc.length(); i++){
    		 if (!Character.isDigit(zc.charAt(i))){
    			 return false;
    		 }
    	}
   	
    	return true;
    }
	
	public void validate() throws IOException {
		if(restaurantName == null) {
			restaurantName = "Restaurant name is missing!";
			bname = false;
		}
		
		if(street == null) {
			street = "Street is missing!";
			bstreet = false;
		}
		
		if(city == null) {
			city = "City is missing!";
			bcity = false;
		}
		
		if(state == null) {
			state = "State is missing!";
			bstate = false;
		}
		
		if(zipCode == null) {
			zipCode = "Zip code is missing!";
			bzcode = false;
		}
		else {
			if(!checkZipCode(zipCode)) {
				zipCode = "Zip code should be numbers!";
				bzcode = false;
			}
		}
		
		if(year == null) {
			year = "Year is missing!";
			byear = false;
		}
		else {
			if(!checkYear(year)) {
				year = "Year should be 4-digit number!";
				byear = false;
			}
		}
		
		if(month == null) {
			month = "Month is missing!";
			bmonth = false;
		}
		else {
			if(!checkMonth(month)) {
				month = "Month should be 1~2-digit number between 1-12!";
				bmonth = false;
			}
		}
		
		if(day == null) {
			day = "Day is missing!";
			bday = false;
		}
		else {
			if(!checkDay(day)) {
				day = "Day should be 1~2-digit number between 1-31!";
				bday = false;
			}
		}
		
		if(bname && bstreet && bcity && bstate && bzcode && byear && bmonth && bday) {
			String fileName = "/data/swe642fall2013/swe642/resources/mzhang8/restaurants.xml";
	    	ArrayList<RestInfo> reviews = null;
	    	XMLData data = new XMLData();
	    	if(!((new File(fileName)).exists())) {
	    		reviews = new ArrayList<RestInfo>();
	    		FileWriter datafile = new FileWriter (fileName);
	    		
	    		//If do not add this, can create xml file, but cannot add any content, cannot fix this bug now.
	    		datafile.write("Hello.");
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
	    	
	    	RestInfo newReview = new RestInfo(restaurantName, foodType, street, city, state, zipCode, year, month, day, rate, comment);
	    	int pos = Integer.parseInt(update);
	    	reviews.remove(pos);	    	
	    	reviews.add(pos, newReview);
	    		    	
	    	data.writePrefixToFile(fileName);
	    	for(int i = 0; i < reviews.size(); i++) {
	    		String content = data.getXML(reviews.get(i));
	    		data.writeToFile(fileName, content);
	    	}
	    	data.writePostfixToFile(fileName);
		}
		else {
			StringBuilder sb = new StringBuilder();
			sb.append("Please correct below errors:<br>");
			if(bname == false)
				sb.append(restaurantName + "<br>");
    		if(bstreet == false)
    			sb.append(street + "<br>");
    		if(bcity == false)
    			sb.append(city + "<br>");
    		if(bstate == false)
    			sb.append(state + "<br>");
    		if(bzcode == false)
    			sb.append(zipCode + "<br>");
    		if(byear == false)
    			sb.append(year + "<br>");
    		if(bmonth == false)
    			sb.append(month + "<br>");
    		if(bday == false)
    			sb.append(day + "<br>");
    		
    		errMsg = sb.toString();
		}
		
	}
}
