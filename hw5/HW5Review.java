package mzhang8;

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

public class HW5Review extends HttpServlet {  //This servlet responsible for render sorted data.
   public void doGet (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	   //Load data from backup file. And render them in the order that they were added to this file.
	   String fileName = "/data/swe642fall2013/swe642/resources/mzhang8/Restaurants";
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
	   }
	   
	   res.setContentType ("text/html");
       PrintWriter out = null;
	   out = res.getWriter ();
	   
	   out.println ("<html>");   	
   	   out.println ("<head>");
   	   out.println ("<title>Restaurant Information</title>");
   	   out.println ("</head>");   	
   	   out.println ("<body bgcolor=\"#EEEEEE\">");   	
   	   out.println ("<center><h2>SWE 642 HW5 Restaurant Information -- Ming Zhang</h2></center>");
   	   out.println("<hr>");   	
   	   out.println("<div align=center>");
    
   	   if(reviews.size() == 0)
   		   out.println("No Reviews.");
   	   else {
   		   Iterator<HashMap<String, String>> it = reviews.iterator();
   		   while(it.hasNext()) {
   			   drawReview(it.next(), out);  	
   		   }
   	   }
   	   
   	   out.println ("</div>");
   	   
   	   out.println("<hr>");
   	   out.println("<div align=\"center\">");
   	   out.println ("<form method=\"post\" action=\"http://apps-swe642.vse.gmu.edu:8080/swe642/servlet/mzhang8.HW5Review\">");                          
   	   out.println("<select size=\"1\" name=\"Sort Order\">");
   	   out.println("<option selected value=\"ByName\">By Restaurant Name</option>");
   	   out.println("<option value=\"ByType\">By Food Type</option>");
   	   out.println("<option value=\"ByRate\">By Rate</option>");
   	   out.println("</select>");
   	   out.println ("<input type=\"submit\" value=\"Sort Reviews\" name=\"Sort Reviews\">"); 
   	   out.println ("</form>"); 
	   out.println ("</body>");
	   out.println ("</html>");	
       
       out.close ();
   }
   
   public void doPost (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	   //Render user with sorted reviews, in name, type or rate.
	   //Load backup data.
	   String fileName = "/data/swe642fall2013/swe642/resources/mzhang8/Restaurants";
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
	   }
   	   
   	   res.setContentType ("text/html");
       PrintWriter out = null;
	   out = res.getWriter ();
	   
	   out.println ("<html>");   	
	   out.println ("<head>");
	   out.println ("<title>Restaurant Information</title>");
	   out.println ("</head>");   	
	   out.println ("<body bgcolor=\"#EEEEEE\">");   	
	   out.println ("<center><h2>SWE 642 HW5 Restaurant Information -- Ming Zhang</h2></center>");
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
	    	        }
	    		}
	       }
	   }
   	   
       out.println ("</div>");
   	   
   	   out.println("<hr>");
   	   out.println("<div align=\"center\">");
   	   out.println ("<form method=\"post\" action=\"http://apps-swe642.vse.gmu.edu:8080/swe642/servlet/mzhang8.HW5Review\">");                          
   	   out.println("<select size=\"1\" name=\"Sort Order\">");
   	   out.println("<option selected value=\"ByName\">By Restaurant Name</option>");
   	   out.println("<option value=\"ByType\">By Food Type</option>");
   	   out.println("<option value=\"ByRate\">By Rate</option>");
   	   out.println("</select>");
   	   out.println ("<input type=\"submit\" value=\"Sort Reviews\" name=\"Sort Reviews\">"); 
   	   out.println ("</form>"); 
	   out.println ("</body>");
	   out.println ("</html>");	
       
       out.close ();
   }
   
   //Print each review.
   public void drawReview(HashMap<String, String> resInfo, PrintWriter pw) {
	   String rName = resInfo.get("Restaurant Name");
	   String fType = resInfo.get("Food Type");
	   String street = resInfo.get("Street");
	   String city = resInfo.get("City");
	   String state = resInfo.get("State");
	   String zCode = resInfo.get("Zip Code");
	   String year = resInfo.get("Year");
	   String month = resInfo.get("Month");
	   String day = resInfo.get("Day");
	   String rate = resInfo.get("Rate");
	   String comment = resInfo.get("Comment");
	   
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
   }
   
   //Print reviews sorted by name.
   public void drawByName(ArrayList<HashMap<String, String>> restaurants, PrintWriter pw) {
	   TreeMap<String, Object> sortName = new TreeMap<String, Object>();
	   Iterator<HashMap<String, String>> it = restaurants.iterator();
	   while(it.hasNext()) {
		   HashMap<String, String> r = it.next();
		   String rname = r.get("Restaurant Name");
		   sortName.put(rname, null);
	   }

	   Iterator<String> mapIdx = sortName.keySet().iterator();
       while(mapIdx.hasNext()) {
    	   String key = mapIdx.next();
		   Iterator<HashMap<String, String>> idx = restaurants.iterator();
		   while(idx.hasNext()) {
			   HashMap<String, String> rest = idx.next();
			   if(rest.get("Restaurant Name").equals(key))
				   drawReview(rest, pw);
		   }
	   }		   
   }
   
   //Print reviews sorted by type.
   public void drawByType(ArrayList<HashMap<String, String>> restaurants, PrintWriter pw) {
	   TreeMap<String, Object> sortName = new TreeMap<String, Object>();
	   Iterator<HashMap<String, String>> it = restaurants.iterator();
	   while(it.hasNext()) {
		   HashMap<String, String> r = it.next();
		   String ftype = r.get("Food Type");
		   sortName.put(ftype, null);
	   }

	   Iterator<String> mapIdx = sortName.keySet().iterator();
       while(mapIdx.hasNext()) {
    	   String key = mapIdx.next();
		   Iterator<HashMap<String, String>> idx = restaurants.iterator();
		   while(idx.hasNext()) {
			   HashMap<String, String> rest = idx.next();
			   if(rest.get("Food Type").equals(key))
				   drawReview(rest, pw);
		   }
	   }		   
   }
   
   //Print reviews sorted by rate.
   public void drawByRate(ArrayList<HashMap<String, String>> restaurants, PrintWriter pw) {
	   Iterator<HashMap<String, String>> it = restaurants.iterator();
	   while(it.hasNext()) {
		   HashMap<String, String> r = it.next();
		   if(r.get("Rate").equals("Good"))
			   drawReview(r, pw);
	   }
	   
	   it = restaurants.iterator();
	   while(it.hasNext()) {
		   HashMap<String, String> r = it.next();
		   if(r.get("Rate").equals("Fair"))
			   drawReview(r, pw);
	   }
	   
	   it = restaurants.iterator();
	   while(it.hasNext()) {
		   HashMap<String, String> r = it.next();
		   if(r.get("Rate").equals("Bad"))
			   drawReview(r, pw);
	   }
   }
}
