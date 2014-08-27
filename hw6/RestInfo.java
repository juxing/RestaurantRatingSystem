package mzhang8;

public class RestInfo {	
	private String rName;
	private String fType;
	private String street;
	private String city;
	private String state;
	private String zCode;
	private String year;
	private String month;
	private String day;
	private String rate;
	private String comment;
	
	public RestInfo(String n, String t, String s, String c, String sta, String z, String y, String m, String d, String r, String com) {
		rName = n;
		fType = t;
		street = s;
		city = c; 
		state = sta;
		zCode = z;
		year = y;
		month = m;
		day = d;
		rate = r;
		comment = com;		
	}
	
	public String getRName() {
		return rName;
	}
	
	public String getFType() {
		return fType;
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
	
	public String getZCode() {
		return zCode;
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
}
