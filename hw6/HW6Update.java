package mzhang8;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class HW6Update extends HttpServlet {
	public void doGet (HttpServletRequest req, HttpServletResponse res) {		
		Enumeration paraNames = req.getParameterNames();
    	String para = (String)paraNames.nextElement();
    	String[] values = req.getParameterValues(para);
    	int updateIndex = Integer.parseInt(values[0]);
    	//out.print(values[0] + " new");
    	
    	ArrayList<RestInfo> reviews = null;
		try {
			reviews = readRestInfos();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	RestInfo updateReview = reviews.get(updateIndex);
    	
    	res.setContentType ("text/html");
		PrintWriter out = null;
		try {
			out = res.getWriter ();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		drawPrefix(out);
		drawForm(out, updateReview, values[0]);
		drawPostfix(out);   	
	}
	
	public void doPost (HttpServletRequest req, HttpServletResponse res) {
    	String para;
    	Enumeration paraNames = req.getParameterNames();
    	String rName = null;
    	String fType = null;
    	String street = null;
    	String city = null;
    	String state = null;
    	String zCode = null;
    	String year = null;
    	String month = null;
    	String day = null;
    	String rate = null;
    	String comment = null;
    	//To detect if there is any error.
    	boolean bname = true;
    	boolean bstreet = true;
    	boolean bcity = true;
    	boolean bstate = true;
    	boolean bzcode = true;
    	boolean byear = true;
    	boolean bmonth = true;
    	boolean bday = true;
    	int pos = -1;
    	
    	//Detect errors.
    	while(paraNames.hasMoreElements()) {
    		para = (String)paraNames.nextElement();
    		if(!para.equalsIgnoreCase("submit")) {
    	        String[] values = req.getParameterValues(para);
    	        if(values != null) {
    	        	if(para.equals("Restaurant Name")) {
    	        		if(values[0].equals("")) {
    	        			rName = "Restaurant name is missing!";
    	        			bname = false;
    	        		}
    	        		else {
    	        			rName = values[0];
    	        		}
    	        	}
    	        	else if(para.equals("Food Type")) {
    	        		fType = values[0];
    	        	}
    	        	else if(para.equals("Street")) {
    	        		if(values[0].equals("")) {
    	        			street = "Street is missing!";
    	        			bstreet = false;
    	        		}
    	        		else {
    	        			street = values[0];
    	        		}
    	        	}
    	        	else if(para.equals("City")) {
    	        		if(values[0].equals("")) {
    	        			city = "City is missing!";
    	        			bcity = false;
    	        		}
    	        		else {
    	        			city = values[0];
    	        		}
    	        	}
    	        	else if(para.equals("State")) {
    	        		if(values[0].equals("")) {
    	        			state = "State is missing!";
    	        			bstate = false;
    	        		}
    	        		else {
    	        			state = values[0];
    	        		}
    	        	}
    	        	else if(para.equals("Zip Code")) {
    	        		if(values[0].equals("")) {
    	        			zCode = "Zip code is missing!";
    	        			bzcode = false;
    	        		}
    	        		else {
    	        			if(checkZipCode(values[0]))
    	        				zCode = values[0];
    	        			else {
    	        				zCode = "Zip code should be numbers!";
    	        				bzcode = false;
    	        			}
    	        		}
    	        	}
    	        	else if(para.equals("Year")) {
    	        		if(values[0].equals("")) {
    	        			year = "Year is missing!";
    	        			byear = false;
    	        		}
    	        		else {
    	        			if(checkYear(values[0]))
    	        				year = values[0];
    	        			else {
    	        				year = "Year should be 4-digit number!";
    	        				byear = false;
    	        			}
    	        		}
    	        	}
    	        	else if(para.equals("Month")) {
    	        		if(values[0].equals("")) {
    	        			month = "Month is missing!";
    	        			bmonth = false;
    	        		}
    	        		else {
    	        			if(checkMonth(values[0]))
    	        				month = values[0];
    	        			else {
    	        				month = "Month should be 2-digit number between 1-12!";
    	        				bmonth = false;
    	        			}
    	        		}
    	        	}
    	        	else if(para.equals("Day")) {
    	        		if(values[0].equals("")) {
    	        			day = "Day is missing!";
    	        			bday = false;
    	        		}
    	        		else {
    	        			if(checkDay(values[0]))
    	        				day = values[0];
    	        			else {
    	        				day = "Day should be 2-digit number between 1-31!";
    	        				bday = false;
    	        			}
    	        		}
    	        	}
    	        	else if(para.equals("Rate")) {
    	        		rate = values[0];
    	        	}
    	        	else if(para.equals("Comment")) {
    	        		comment = values[0];
    	        	}  
    	        	else if(para.equals("Update")) {
    	        		pos = Integer.parseInt(values[0]);
    	        	}
    		    }
    	    }
    	}

    	res.setContentType ("text/html");
    	PrintWriter toClient = null;
		try {
			toClient = res.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	//If no error, post it, render user with a format table, and then store the reviews to a file.
    	if(bname && bstreet && bcity && bstate && bzcode && byear && bmonth && bday) {  
    		RestInfo tmp = new RestInfo(rName, fType, street, city, state, zCode, year, month, day, rate, comment);
    		drawResult(toClient, tmp);
	    	toClient.close();

	    	//Store review information to a file.
	    	ArrayList<RestInfo> reviews = null;
	    	try {
				reviews = readRestInfos();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	reviews.remove(pos);	    	
	    	reviews.add(pos, tmp);
	    	try {
				writeRestInfos(reviews);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	    		    	
    	}
    	else { //If any error in filling in form, notify user with read characters.
    		RestInfo tmp = new RestInfo(rName, fType, street, city, state, zCode, year, month, day, rate, comment);
    	
    		StringBuilder sb = new StringBuilder();
    		if(bname == false)
    			sb.append(rName + "<br>");
    		if(bstreet == false)
    			sb.append(street + "<br>");
    		if(bcity == false)
    			sb.append(city + "<br>");
    		if(bstate == false)
    			sb.append(state + "<br>");
    		if(bzcode == false)
    			sb.append(zCode + "<br>");
    		if(byear == false)
    			sb.append(year + "<br>");
    		if(bmonth == false)
    			sb.append(month + "<br>");
    		if(bday == false)
    			sb.append(day + "<br>");
    		
    		drawErrorInfo(toClient, sb, tmp, String.valueOf(pos));
	    	toClient.close();
    	}
	}
	
	private ArrayList<RestInfo> readRestInfos() throws IOException {
		String fileName = "/data/swe642fall2013/swe642/resources/mzhang8/restaurants.xml";  //Backup file.
		ArrayList<RestInfo> reviews = null;
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
		
		return reviews;
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
	
	private void drawPrefix(PrintWriter out) {
		out.println ("<html>");       
        out.println ("<head>");
        out.println ("<title>SWE 642 HW6 Restaurant Update</title>");
        out.println ("</head>");        
        out.println ("<body>");        
        out.println ("<h1 align=\"center\">SWE 642 HW6 Restaurant Update-- Ming Zhang</h1>");
        out.println ("<h2 align=\"center\"><u>Please Enter Restaurant Information</u></h2>");
        out.println ("<hr>");
	}
	
	private void drawPostfix(PrintWriter out) {       
        out.println ("</body>");       
        out.println ("</html>");
	}
	
    private void drawForm(PrintWriter out, RestInfo res, String position) {        
        out.println ("<div align=\"center\">");
        out.println ("<form method=\"post\" action=\"http://apps-swe642.vse.gmu.edu:8080/swe642/servlet/mzhang8.HW6Update\">");
        
        out.println ("<table>");
        
        out.println ("<tr>");
        out.println ("<td>Restaurant Name</td>");
        out.println ("<td><input type=\"text\" name=\"Restaurant Name\" value=\"" + res.getRName() + "\"></td>");
        out.println ("</tr>");
        
        out.println ("<tr>");
        out.println ("<td>Food Type</td>");
        out.println ("<td>");
        out.println ("<select size=\"1\" name=\"Food Type\">");
        
        if(res.getFType().equals("American Food")) {
        	out.println ("<option selected value=\"American Food\">American Food</option>");
            out.println ("<option value=\"Chinese Food\">Chinese Food</option>");
            out.println ("<option value=\"Japanese Food\">Japanese Food</option>");
            out.println ("<option value=\"Mexico Food\">Mexico Food</option>");
            out.println ("<option value=\"Italian Food\">Itanlian Food</option>");
            out.println ("<option value=\"French Food\">French Food</option>");
            out.println ("<option value=\"Other\">Other</option>");
        }
        else if(res.getFType().equals("Chinese Food")) {
        	out.println ("<option value=\"American Food\">American Food</option>");
            out.println ("<option selected value=\"Chinese Food\">Chinese Food</option>");
            out.println ("<option value=\"Japanese Food\">Japanese Food</option>");
            out.println ("<option value=\"Mexico Food\">Mexico Food</option>");
            out.println ("<option value=\"Italian Food\">Itanlian Food</option>");
            out.println ("<option value=\"French Food\">French Food</option>");
            out.println ("<option value=\"Other\">Other</option>");
        }
        else if(res.getFType().equals("Japanese Food")) {
        	out.println ("<option value=\"American Food\">American Food</option>");
            out.println ("<option value=\"Chinese Food\">Chinese Food</option>");
            out.println ("<option selected value=\"Japanese Food\">Japanese Food</option>");
            out.println ("<option value=\"Mexico Food\">Mexico Food</option>");
            out.println ("<option value=\"Italian Food\">Itanlian Food</option>");
            out.println ("<option value=\"French Food\">French Food</option>");
            out.println ("<option value=\"Other\">Other</option>");
        }
        else if(res.getFType().equals("Mexico Food")) {
        	out.println ("<option value=\"American Food\">American Food</option>");
            out.println ("<option value=\"Chinese Food\">Chinese Food</option>");
            out.println ("<option value=\"Japanese Food\">Japanese Food</option>");
            out.println ("<option selected value=\"Mexico Food\">Mexico Food</option>");
            out.println ("<option value=\"Italian Food\">Itanlian Food</option>");
            out.println ("<option value=\"French Food\">French Food</option>");
            out.println ("<option value=\"Other\">Other</option>");
        }
        else if(res.getFType().equals("Italian Food")) {
        	out.println ("<option value=\"American Food\">American Food</option>");
            out.println ("<option value=\"Chinese Food\">Chinese Food</option>");
            out.println ("<option value=\"Japanese Food\">Japanese Food</option>");
            out.println ("<option value=\"Mexico Food\">Mexico Food</option>");
            out.println ("<option selected value=\"Italian Food\">Itanlian Food</option>");
            out.println ("<option value=\"French Food\">French Food</option>");
            out.println ("<option value=\"Other\">Other</option>");
        }
        else if(res.getFType().equals("French Food")) {
        	out.println ("<option value=\"American Food\">American Food</option>");
            out.println ("<option selected value=\"Chinese Food\">Chinese Food</option>");
            out.println ("<option value=\"Japanese Food\">Japanese Food</option>");
            out.println ("<option value=\"Mexico Food\">Mexico Food</option>");
            out.println ("<option value=\"Italian Food\">Itanlian Food</option>");
            out.println ("<option selected value=\"French Food\">French Food</option>");
            out.println ("<option value=\"Other\">Other</option>");
        }
        else if(res.getFType().equals("Other Food")) {
        	out.println ("<option value=\"American Food\">American Food</option>");
            out.println ("<option value=\"Chinese Food\">Chinese Food</option>");
            out.println ("<option value=\"Japanese Food\">Japanese Food</option>");
            out.println ("<option value=\"Mexico Food\">Mexico Food</option>");
            out.println ("<option value=\"Italian Food\">Itanlian Food</option>");
            out.println ("<option value=\"French Food\">French Food</option>");
            out.println ("<option selected value=\"Other\">Other</option>");
        }
        
        out.println ("</select>");
        out.println ("</td>");
        out.println ("</tr>");
        
        out.println ("<tr>");
        out.println ("<td>Street</td>");
        out.println ("<td><input type=\"text\" name=\"Street\" value=\"" + res.getStreet() + "\"></td>");
        out.println ("</tr>");
        
        out.println ("<tr>");
        out.println ("<td>City</td>");
        out.println ("<td><input type=\"text\" name=\"City\" value=\"" + res.getCity() + "\"></td>");
        out.println ("</tr>");
        
        out.println ("<tr>");
        out.println ("<td>State</td>");
        out.println ("<td><input type=\"text\" name=\"State\" value=\"" + res.getState() + "\"></td>");
        out.println ("</tr>");
        
        out.println ("<tr>");
        out.println ("<td>Zip Code</td>");
        out.println ("<td><input type=\"text\" name=\"Zip Code\" value=\"" + res.getZCode() + "\"></td>");
        out.println ("</tr>");
        
        out.println ("<tr>");
        out.println ("<td>Year</td>");
        out.println ("<td><input type=\"text\" name=\"Year\" value=\"" + res.getYear() + "\"></td>");
        out.println ("</tr>");
        
        out.println ("<tr>");
        out.println ("<td>Month</td>");
        out.println ("<td><input type=\"text\" name=\"Month\" value=\"" + res.getMonth() + "\"></td>");
        out.println ("</tr>");
        
        out.println ("<tr>");
        out.println ("<td>Day</td>");
        out.println ("<td><input type=\"text\" name=\"Day\" value=\"" + res.getDay() + "\"></td>");
        out.println ("</tr>");
        
        out.println ("<tr bgcolor=\"#333388\">");
        out.println ("<td></td>");
        out.println ("<td align=\"center\"><font color=\"#FFFFFF\"><b>Good</b></font></td>");
        out.println ("<td align=\"center\" width=\"26%\"><font color=\"#FFFFFF\"><b>Fair</b></font></td>");
        out.println ("<td align=\"center\" width=\"26%\"><FONT color=\"#FFFFFF\"><b>Bad</b></font></td>");
        out.println ("</tr>");
       
        out.println ("<tr>");
        out.println ("<td>Rate</td>");
        
        if(res.getRate().equals("Good")) {
        	out.println ("<td align=\"center\"><input type=\"radio\" value=\"Good\" checked name=\"Rate\"></td>");
            out.println ("<td align=\"center\"><input type=\"radio\" value=\"Fair\" name=\"Rate\"></td>");
            out.println ("<td align=\"center\"><input type=\"radio\" value=\"Bad\" name=\"Rate\"></td>");
        }
        else if(res.getRate().equals("Fair")) {
        	out.println ("<td align=\"center\"><input type=\"radio\" value=\"Good\" name=\"Rate\"></td>");
            out.println ("<td align=\"center\"><input type=\"radio\" value=\"Fair\" checked name=\"Rate\"></td>");
            out.println ("<td align=\"center\"><input type=\"radio\" value=\"Bad\" name=\"Rate\"></td>");
        }
        else if(res.getRate().equals("Bad")) {
        	out.println ("<td align=\"center\"><input type=\"radio\" value=\"Good\" name=\"Rate\"></td>");
            out.println ("<td align=\"center\"><input type=\"radio\" value=\"Fair\" name=\"Rate\"></td>");
            out.println ("<td align=\"center\"><input type=\"radio\" value=\"Bad\" checked name=\"Rate\"></td>");
        }
               
        out.println ("</tr>");
        
        out.println ("<tr>");
        out.println ("<td>What To Say:</td>");
        out.println ("<td><textarea rows=\"10\" name=\"Comment\">" + res.getComment() + "</textarea></td>");
        out.println ("</tr>");
        
        out.println ("<tr>");
        out.println ("<td><input type=\"hidden\" value=\"" + position + "\" name=\"Update\">");
        out.println ("<td><input type=\"submit\" value=\"Submit\" name=\"submit\"></td>");
        out.println ("<td><input type=\"reset\" value=\"Reset\" name=\"reset\"></td>");
        out.println ("</tr>");
        
        out.println ("</table>");
        
        out.println ("</form>");         
        out.println ("</div>");        
    }
    
    private void drawResult(PrintWriter toClient, RestInfo res) {
    	toClient.println ("<html>");
    	
    	toClient.println ("<head>");
    	toClient.println ("<title>Restaurant Information</title>");
    	toClient.println ("</head>");
    	
    	toClient.println ("<body bgcolor=\"#EEEEEE\">");
    	
    	toClient.println ("<center><h2>SWE 642 HW6 Restaurant Information -- Ming Zhang</h2></center>");
    	toClient.println("<hr>");
    	
    	toClient.println("<div align=center>");
    	toClient.println ("<table border=0>");   	
    	toClient.println ("<tr bgcolor=\"#C0C0C0\">");
    	toClient.println ("<td align=middle>Restaurant Name:</td>");
    	toClient.println ("<td align=middle>" + res.getRName() + "<br>(" + res.getFType() + ")" + "</td>");    	
    	toClient.println ("</tr>");
    	
    	toClient.println ("<tr bgcolor=\"#DDDDDD\">");   	
    	toClient.println ("<td align=middle>Restaurant Address:</td>");
    	toClient.println ("<td align=middle>" + res.getStreet() + "<br>" + res.getCity() + ", " + res.getState() + ", " + res.getZCode() + "</td>");
    	toClient.println ("</tr>");
    	
    	toClient.println ("<tr bgcolor=\"#C0C0C0\">");   	
    	toClient.println ("<td align=middle>Visit Date:</td>");
    	toClient.println ("<td align=middle>" + res.getYear() + "-" + res.getMonth() + "-" + res.getDay() + "</td>");
    	toClient.println ("</tr>");
    	
    	toClient.println ("<tr bgcolor=\"#DDDDDD\">");   	
    	toClient.println ("<td align=middle>Your Rate:</td>");
    	toClient.println ("<td align=middle>" + res.getRate() + "</td>");
    	toClient.println ("</tr>");
    	
    	toClient.println ("<tr bgcolor=\"#C0C0C0\">");   	
    	toClient.println ("<td align=middle>Comments:</td>");
    	toClient.println ("<td align=middle><textarea rows=\"10\" bgcolor=\"#123456\">" + res.getComment() + "</textarea></td>");
    	toClient.println ("</tr>");
     	
    	toClient.println ("</table>");
    	toClient.println ("</div>");
    	toClient.println ("</body>");
    	toClient.println ("</html>");	    	
    }
    
    private void drawErrorInfo(PrintWriter toClient, StringBuilder sb, RestInfo res, String position) {
    	drawPrefix(toClient);
		drawErrorMsg(toClient, sb);		
		drawForm(toClient, res, position);
		drawPostfix(toClient);
    }
    
    private void drawErrorMsg(PrintWriter out, StringBuilder sb) {
    	out.println("<p><font color=\"#FF0011\">");
    	out.println("Please correct below info:<br>");
    	out.println(sb.toString());
        out.println("<hr>");
    }
    
    //Check year format.
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
    
     //Check month format.
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
    
     //Check day format.
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
    
    //Check zip code format.
    private boolean checkZipCode(String zc) {   	    	
    	for (int i = 0; i < zc.length(); i++){
    		 if (!Character.isDigit(zc.charAt(i))){
    			 return false;
    		 }
    	}
   	
    	return true;
    }
}
