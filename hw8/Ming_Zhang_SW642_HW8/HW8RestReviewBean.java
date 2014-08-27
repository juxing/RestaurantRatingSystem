package mzhang8;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.TreeMap;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class HW8RestReviewBean {
	private ArrayList<HW8RestInfo> reviews = null;
	private String sortOrder = null;
	private String rateFilter = null;
	private String foodTypeFilter = null;
	private String restNameFilter = null;
	
	public void setSortOrder(String st) {
		sortOrder = st;
	}
	
	public void setRateFilter(String st) {
		rateFilter = st;
	}
	
	public void setFoodTypeFilter(String st) {
		foodTypeFilter = st;
	}
	
	public void setRestNameFilter(String st) {
		restNameFilter = st;
	}
	
	public String getSortOrder() {
		return sortOrder;
	}
	
	public String getRateFilter() {
		return rateFilter;
	}
	
	public String getFoodTypeFilter() {
		return foodTypeFilter;
	}
	
	public String getRestNameFilter() {
		return restNameFilter;
	}
	
	public HW8RestReviewBean() throws IOException {
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
	
	public ArrayList<HW8RestInfo> getReviews() {
		return reviews;
	}
	
	public LinkedHashMap<HW8RestInfo, Integer> getSortByNameReviews() {
		LinkedHashMap<HW8RestInfo, Integer> res = new LinkedHashMap<HW8RestInfo, Integer>();
		TreeMap<String, Object> sortName = new TreeMap<String, Object>();
		Iterator<HW8RestInfo> it = reviews.iterator();
		while(it.hasNext()) {
			HW8RestInfo r = it.next();
			String rname = r.getRName();
			sortName.put(rname, null);
		}

		Iterator<String> mapIdx = sortName.keySet().iterator();
	    while(mapIdx.hasNext()) {
	    	String key = mapIdx.next();			  
			for(int i = 0; i < reviews.size(); i++) {
				HW8RestInfo rest = reviews.get(i);
				if(rest.getRName().equals(key))
					res.put(rest, i);
			}
		}
	    
	    return res;
	}
	
	public LinkedHashMap<HW8RestInfo, Integer> getSortByTypeReviews() {
		LinkedHashMap<HW8RestInfo, Integer> res = new LinkedHashMap<HW8RestInfo, Integer>();
		TreeMap<String, Object> sortName = new TreeMap<String, Object>();
		Iterator<HW8RestInfo> it = reviews.iterator();
		while(it.hasNext()) {
			HW8RestInfo r = it.next();
			String ftype = r.getFType();
			sortName.put(ftype, null);
		}

		Iterator<String> mapIdx = sortName.keySet().iterator();
	    while(mapIdx.hasNext()) {
	    	String key = mapIdx.next();
	    	for(int i = 0; i < reviews.size(); i++) {
				HW8RestInfo rest = reviews.get(i);
				if(rest.getFType().equals(key))
					res.put(rest, i);
			}
		}
	    
	    return res;
	}
	
	public LinkedHashMap<HW8RestInfo, Integer> getSortByRateReviews() {
		LinkedHashMap<HW8RestInfo, Integer> res = new LinkedHashMap<HW8RestInfo, Integer>();
		for(int i = 0; i < reviews.size(); i++) {
			HW8RestInfo rest = reviews.get(i);
			if(rest.getRate().equals("Good"))
				res.put(rest, i);
		}

		for(int i = 0; i < reviews.size(); i++) {
			HW8RestInfo rest = reviews.get(i);
			if(rest.getRate().equals("Fair"))
				res.put(rest, i);
		}

		for(int i = 0; i < reviews.size(); i++) {
			HW8RestInfo rest = reviews.get(i);
			if(rest.getRate().equals("Bad"))
				res.put(rest, i);
		}
		
		return res;
	}
	
	public LinkedHashMap<HW8RestInfo, Integer> getRateFilteReviews() {
		LinkedHashMap<HW8RestInfo, Integer> res = new LinkedHashMap<HW8RestInfo, Integer>();
		for(int i = 0; i < reviews.size(); i++) {
			HW8RestInfo rest = reviews.get(i);
			if(rest.getRate().equals(rateFilter))
				res.put(rest, i);
		}
		
		return res;
	}
	
	public LinkedHashMap<HW8RestInfo, Integer> getFoodTypeFilteReviews() {
		LinkedHashMap<HW8RestInfo, Integer> res = new LinkedHashMap<HW8RestInfo, Integer>();
		for(int i = 0; i < reviews.size(); i++) {
			HW8RestInfo rest = reviews.get(i);
			if(rest.getFType().equals(foodTypeFilter))
				res.put(rest, i);
		}
		
		return res;
	}
	
	public LinkedHashMap<HW8RestInfo, Integer> getRestNameFilteReviews() {
		LinkedHashMap<HW8RestInfo, Integer> res = new LinkedHashMap<HW8RestInfo, Integer>();
		for(int i = 0; i < reviews.size(); i++) {
			HW8RestInfo rest = reviews.get(i);
			if(rest.getRName().indexOf(restNameFilter) != -1 )
				res.put(rest, i);
		}
		
		return res;
	}
}

