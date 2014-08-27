package mzhang8;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class HW6Review extends HttpServlet {  //This servlet responsible for render sorted data.
   public void doGet (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	   //Load data from backup file. And render them in the order that they were added to this file.
	   /*String fileName = "/data/swe642fall2013/swe642/resources/mzhang8/Restaurants";
   	   DataStore reviewData = null;
   	   ArrayList<HashMap<String, String>> reviews = null;
   	   try {
   		   if((reviewData = DataStore.load(fileName)) == null)
   			   reviews = new ArrayList<HashMap<String, String>>();
		   else
		       reviews = reviewData.getResInfo();
	   } catch (ClassNotFoundException e) {
		   // TODO Auto-generated catch block
		   e.printStackTrace();
	   }*/   	   
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
	   
	   res.setContentType ("text/html");
       PrintWriter out = null;
	   out = res.getWriter ();
	   
	   out.println ("<html>");   	
   	   out.println ("<head>");
   	   out.println ("<title>Restaurant Information</title>");
   	   out.println ("</head>");   	
   	   out.println ("<body bgcolor=\"#EEEEEE\">");   	
   	   out.println ("<center><h2>SWE 642 HW6 Restaurant Information -- Ming Zhang</h2></center>");
   	   out.println("<hr>");   	
   	   out.println("<div align=center>");
    
   	   if(reviews.size() == 0)
   		   out.println("No Reviews.");
   	   else {
   		   /*Iterator<RestInfo> it = reviews.iterator();
   		   while(it.hasNext()) {
   			   drawReview(it.next(), out);  	
   		   }*/
   		   for(int i = 0; i < reviews.size(); i++)
   			   drawReview(reviews.get(i), out, i);
   	   }
   	   
   	   out.println ("</div>");
   	   
   	   out.println("<hr>");
   	   out.println("<div align=\"center\">");
   	   
   	   out.println ("<form method=\"post\" action=\"http://apps-swe642.vse.gmu.edu:8080/swe642/servlet/mzhang8.HW6Review\">");                          
   	   out.println("<select size=\"1\" name=\"Sort Order\">");
   	   out.println("<option selected value=\"ByName\">By Restaurant Name</option>");
   	   out.println("<option value=\"ByType\">By Food Type</option>");
   	   out.println("<option value=\"ByRate\">By Rate</option>");
   	   out.println("</select>");
   	   out.println ("<input type=\"submit\" value=\"Sort Reviews\" name=\"Sort Reviews\">"); 
   	   out.println ("</form>"); 
   	   
   	   out.println ("<form method=\"post\" action=\"http://apps-swe642.vse.gmu.edu:8080/swe642/servlet/mzhang8.HW6Review\">");                          
	   out.println("<select size=\"1\" name=\"RateFilter\">");
	   out.println("<option selected value=\"Good\">Good</option>");
	   out.println("<option value=\"Fair\">Fair</option>");
	   out.println("<option value=\"Bad\">Bad</option>");
	   out.println("</select>");
	   out.println ("<input type=\"submit\" value=\"Filte By Rate\" name=\"Filte By Rate\">"); 
	   out.println ("</form>"); 
	   
	   out.println ("<form method=\"post\" action=\"http://apps-swe642.vse.gmu.edu:8080/swe642/servlet/mzhang8.HW6Review\">");
	   out.println ("<select size=\"1\" name=\"FoodTypeFilter\">");
       out.println ("<option selected value=\"American Food\">American Food</option>");
       out.println ("<option value=\"Chinese Food\">Chinese Food</option>");
       out.println ("<option value=\"Japanese Food\">Japanese Food</option>");
       out.println ("<option value=\"Mexico Food\">Mexico Food</option>");
       out.println ("<option value=\"Italian Food\">Itanlian Food</option>");
       out.println ("<option value=\"French Food\">French Food</option>");
       out.println ("<option value=\"Other\">Other</option>");
       out.println ("</select>");
       out.println ("<input type=\"submit\" value=\"Filte By Food Type\" name=\"Filte By Food Type\">"); 
	   out.println ("</form>"); 
	   
	   out.println ("<form method=\"post\" action=\"http://apps-swe642.vse.gmu.edu:8080/swe642/servlet/mzhang8.HW6Review\">");
	   out.println ("<input type=\"text\" name=\"RestNameFilter\">");
	   out.println ("<input type=\"submit\" value=\"Filte By Rest Name\" name=\"Filte By Rest name\">"); 
	   out.println ("</form>"); 
	   
	   out.println ("</body>");
	   out.println ("</html>");	
       
       out.close ();
   }
   
   public void doPost (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	   //Render user with sorted reviews, in name, type or rate.
	   //Load backup data.
	   /*String fileName = "/data/swe642fall2013/swe642/resources/mzhang8/Restaurants";
   	   DataStore reviewData = null;
   	   ArrayList<HashMap<String, String>> reviews = null;
   	   try {
   		   if((reviewData = DataStore.load(fileName)) == null)
   			   reviews = new ArrayList<HashMap<String, String>>();
		   else
		       reviews = reviewData.getResInfo();
	   } catch (ClassNotFoundException e) {
		   // TODO Auto-generated catch block
		   e.printStackTrace();
	   }*/
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
   	   
   	   res.setContentType ("text/html");
       PrintWriter out = null;
	   out = res.getWriter ();
	   
	   out.println ("<html>");   	
	   out.println ("<head>");
	   out.println ("<title>Restaurant Information</title>");
	   out.println ("</head>");   	
	   out.println ("<body bgcolor=\"#EEEEEE\">");   	
	   out.println ("<center><h2>SWE 642 HW6 Restaurant Information -- Ming Zhang</h2></center>");
	   out.println("<hr>");   	
	   out.println("<div align=center>");
 
	   if(reviews.size() == 0)
		   out.println("No Reviews.");
	   else {  //Sort reviews.
		   String para;
	       Enumeration paraNames = req.getParameterNames();
	       while(paraNames.hasMoreElements()) {
	    		para = (String)paraNames.nextElement();
	    		if(!para.equalsIgnoreCase("submit")) {
	    	        String[] values = req.getParameterValues(para);
	    	        if(values != null) {
	    	        	if(para.equals("Sort Order")) {
	    	        		 if(values[0].equals("ByName"))
	    	        			 drawByName(reviews, out);
	    	        		 else if(values[0].equals("ByType"))
	    	        			 drawByType(reviews, out);
	    	        		 else if(values[0].equals("ByRate"))
	    	        			 drawByRate(reviews, out);
	    	        	}
	    	        	else if(para.equals("RateFilter")) {
	    	        		 filteByRate(values[0], reviews, out);
	    	        	}
	    	        	else if(para.equals("FoodTypeFilter")) {
	    	        		 filteByFoodType(values[0], reviews, out);
	    	        	}
	    	        	else if(para.equals("RestNameFilter")) {
	    	        		 filteByRestName(values[0], reviews, out);
	    	        	}
	    	        }
	    		}
	       }
	   }
   	   
       /*out.println ("</div>");
   	   
   	   out.println("<hr>");
   	   out.println("<div align=\"center\">");
   	   out.println ("<form method=\"post\" action=\"http://apps-swe642.vse.gmu.edu:8080/swe642/servlet/mzhang8.HW6Review\">");                          
   	   out.println("<select size=\"1\" name=\"Sort Order\">");
   	   out.println("<option selected value=\"ByName\">By Restaurant Name</option>");
   	   out.println("<option value=\"ByType\">By Food Type</option>");
   	   out.println("<option value=\"ByRate\">By Rate</option>");
   	   out.println("</select>");
   	   out.println ("<input type=\"submit\" value=\"Sort Reviews\" name=\"Sort Reviews\">"); 
   	   out.println ("</form>");*/ 
	   out.println ("</body>");
	   out.println ("</html>");	
       
       out.close ();
   }
   
   //Print each review.
   public void drawReview(RestInfo resInfo, PrintWriter pw, int position) {
	   String rName = resInfo.getRName();
	   String fType = resInfo.getFType();
	   String street = resInfo.getStreet();
	   String city = resInfo.getCity();
	   String state = resInfo.getState();
	   String zCode = resInfo.getZCode();
	   String year = resInfo.getYear();
	   String month = resInfo.getMonth();
	   String day = resInfo.getDay();
	   String rate = resInfo.getRate();
	   String comment = resInfo.getComment();
	   
	    pw.println("<p>");
	   	pw.println ("<table border=0>");   	
	   	pw.println ("<tr bgcolor=\"#C0C0C0\">");
	   	pw.println ("<td align=middle>Restaurant Name:</td>");
	   	pw.println ("<td align=middle>" + rName + "<br>(" + fType + ")" + "</td>");    	
	   	pw.println ("</tr>");
	   	
	   	pw.println ("<tr bgcolor=\"#DDDDDD\">");   	
	   	pw.println ("<td align=middle>Restaurant Address:</td>");
	   	pw.println ("<td align=middle>" + street + "<br>" + city + ", " + state + ", " + zCode + "</td>");
	   	pw.println ("</tr>");
	   	
	   	pw.println ("<tr bgcolor=\"#C0C0C0\">");   	
	   	pw.println ("<td align=middle>Visit Date:</td>");
	   	pw.println ("<td align=middle>" + year + "-" + month + "-" + day + "</td>");
	   	pw.println ("</tr>");
	   	
	   	pw.println ("<tr bgcolor=\"#DDDDDD\">");   	
	   	pw.println ("<td align=middle>Your Rate:</td>");
	   	pw.println ("<td align=middle>" + rate + "</td>");
	   	pw.println ("</tr>");
	   	
	   	pw.println ("<tr bgcolor=\"#C0C0C0\">");   	
	   	pw.println ("<td align=middle>Comments:</td>");
	   	pw.println ("<td align=middle><textarea rows=\"10\" bgcolor=\"#123456\">" + comment + "</textarea></td>");
	   	pw.println ("</tr>");
	    	
	   	pw.println ("</table>");	
	   	pw.println("</p>");
	   	
	   	//Draw update and clone button
	   	String pos = String.valueOf(position);
	   	
	   	pw.println ("<form method=\"get\" action=\"http://apps-swe642.vse.gmu.edu:8080/swe642/servlet/mzhang8.HW6Update\">");  	   	
	   	pw.println("<input type=\"hidden\" value=\"" + pos + "\" name=\"Update\">");
	   	pw.println ("<input type=\"submit\" value=\"Update\" name=\"Update\">"); 
	   	pw.println ("</form>");
	   	
	   	pw.println ("<form method=\"get\" action=\"http://apps-swe642.vse.gmu.edu:8080/swe642/servlet/mzhang8.HW6Clone\">");  	   	
	   	pw.println("<input type=\"hidden\" value=\"" + pos + "\" name=\"Clone\">");
	   	pw.println ("<input type=\"submit\" value=\"Clone\" name=\"Clone\">");
	   	pw.println ("</form>");
   }
   
   //Print reviews sorted by name.
   public void drawByName(ArrayList<RestInfo> restaurants, PrintWriter pw) {
	   TreeMap<String, Object> sortName = new TreeMap<String, Object>();
	   Iterator<RestInfo> it = restaurants.iterator();
	   while(it.hasNext()) {
		   RestInfo r = it.next();
		   String rname = r.getRName();
		   sortName.put(rname, null);
	   }

	   Iterator<String> mapIdx = sortName.keySet().iterator();
       while(mapIdx.hasNext()) {
    	   String key = mapIdx.next();
		   /*Iterator<RestInfo> idx = restaurants.iterator();
		   while(idx.hasNext()) {
			   RestInfo rest = idx.next();
			   if(rest.getRName().equals(key))
				   drawReview(rest, pw);
		   }*/
		   for(int i = 0; i < restaurants.size(); i++) {
			   RestInfo rest = restaurants.get(i);
			   if(rest.getRName().equals(key))
				   drawReview(rest, pw, i);
		   }
	   }		   
   }
   
   //Print reviews sorted by type.
   public void drawByType(ArrayList<RestInfo> restaurants, PrintWriter pw) {
	   TreeMap<String, Object> sortName = new TreeMap<String, Object>();
	   Iterator<RestInfo> it = restaurants.iterator();
	   while(it.hasNext()) {
		   RestInfo r = it.next();
		   String ftype = r.getFType();
		   sortName.put(ftype, null);
	   }

	   Iterator<String> mapIdx = sortName.keySet().iterator();
       while(mapIdx.hasNext()) {
    	   String key = mapIdx.next();
		   /*Iterator<RestInfo> idx = restaurants.iterator();
		   while(idx.hasNext()) {
			   RestInfo rest = idx.next();
			   if(rest.getFType().equals(key))
				   drawReview(rest, pw);
		   }*/
    	   for(int i = 0; i < restaurants.size(); i++) {
			   RestInfo rest = restaurants.get(i);
			   if(rest.getFType().equals(key))
				   drawReview(rest, pw, i);
		   }
	   }		   
   }
   
   //Print reviews sorted by rate.
   public void drawByRate(ArrayList<RestInfo> restaurants, PrintWriter pw) {
	   /*Iterator<RestInfo> it = restaurants.iterator();
	   while(it.hasNext()) {
		   RestInfo r = it.next();
		   if(r.getRate().equals("Good"))
			   drawReview(r, pw);
	   }*/
	   for(int i = 0; i < restaurants.size(); i++) {
		   RestInfo rest = restaurants.get(i);
		   if(rest.getRate().equals("Good"))
			   drawReview(rest, pw, i);
	   }
	   
	   /*it = restaurants.iterator();
	   while(it.hasNext()) {
		   RestInfo r = it.next();
		   if(r.getRate().equals("Fair"))
			   drawReview(r, pw);
	   }*/
	   for(int i = 0; i < restaurants.size(); i++) {
		   RestInfo rest = restaurants.get(i);
		   if(rest.getRate().equals("Fair"))
			   drawReview(rest, pw, i);
	   }
	   
	   /*it = restaurants.iterator();
	   while(it.hasNext()) {
		   RestInfo r = it.next();
		   if(r.getRate().equals("Bad"))
			   drawReview(r, pw);
	   }*/
	   for(int i = 0; i < restaurants.size(); i++) {
		   RestInfo rest = restaurants.get(i);
		   if(rest.getRate().equals("Bad"))
			   drawReview(rest, pw, i);
	   }
   }
   
   private void filteByRate(String rate, ArrayList<RestInfo> restaurants, PrintWriter pw) {
	   for(int i = 0; i < restaurants.size(); i++) {
		   RestInfo rest = restaurants.get(i);
		   if(rest.getRate().equals(rate))
			   drawReview(rest, pw, i);
	   }
   }
   
   private void filteByFoodType(String fType, ArrayList<RestInfo> restaurants, PrintWriter pw) {
	   for(int i = 0; i < restaurants.size(); i++) {
		   RestInfo rest = restaurants.get(i);
		   if(rest.getFType().equals(fType))
			   drawReview(rest, pw, i);
	   }
   }
   
   private void filteByRestName(String rName, ArrayList<RestInfo> restaurants, PrintWriter pw) {
	   for(int i = 0; i < restaurants.size(); i++) {
		   RestInfo rest = restaurants.get(i);
		   if(rest.getRName().indexOf(rName) != -1 )
			   drawReview(rest, pw, i);
	   }
   }
}
