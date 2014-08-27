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

public class HW6Clone extends HttpServlet {
	public void doGet (HttpServletRequest req, HttpServletResponse res) {		
		Enumeration paraNames = req.getParameterNames();
    	String para = (String)paraNames.nextElement();
    	String[] values = req.getParameterValues(para);
    	int cloneIndex = Integer.parseInt(values[0]);
    	//out.print(values[0] + " new");
    	
    	ArrayList<RestInfo> reviews = null;
		try {
			reviews = readRestInfos();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	RestInfo cloneReview = reviews.get(cloneIndex);
    	RestInfo newReview = new RestInfo(cloneReview.getRName(), cloneReview.getFType(), cloneReview.getStreet(), cloneReview.getCity(), cloneReview.getState(),
    			cloneReview.getZCode(), cloneReview.getYear(), cloneReview.getMonth(), cloneReview.getDay(), cloneReview.getRate(), cloneReview.getComment());	    	
    	reviews.add(cloneIndex+1, newReview);
    	try {
			writeRestInfos(reviews);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	res.setContentType ("text/html");
		PrintWriter out = null;
		try {
			out = res.getWriter ();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		drawPrefix(out);
		drawForm(out, newReview, values[0]);
		drawPostfix(out);   	
	}
	
	public void doPost (HttpServletRequest req, HttpServletResponse res) {
		
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
        out.println("Below restaurant review has been cloned!");
	}
	
	private void drawPostfix(PrintWriter out) {       
        out.println ("</body>");       
        out.println ("</html>");
	}
	
    private void drawForm(PrintWriter out, RestInfo res, String position) {        
        out.println ("<div align=\"center\">");
        
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
        
        out.println ("</table>");
        
        out.println ("</div>");        
    }

}
