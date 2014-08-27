package mzhang8;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class HW3 extends HttpServlet
{

   public void doGet  (HttpServletRequest req, HttpServletResponse res)
          throws ServletException, IOException
   {

        res.setContentType ("text/html");
        PrintWriter out = res.getWriter ();

        out.println ("<html>");
        out.println ("<head>");
        out.println ("<title>SWE 642 HW2</title>");
        out.println ("<script language=\"javascript\" src=\"http://apps-swe642.vse.gmu.edu:8080/swe642/resources/mzhang8/HW2.js\"></script>");
        out.println ("</head>");
        out.println ("<body>");
        out.println ("<h1 align=\"center\">HW2 -- Ming Zhang</h1>");
        out.println ("<p>");
        out.println ("Readme:");
        out.println ("<ul><font size=-1>");
        out.println ("<li>Year should be 4 digits number, Month should be 2 digits number(1 ~ 12), Day should be 2 digits number(1 ~ 31).");
        out.println ("<li>Name should only contain chars, numbers, spaces.");
        out.println ("<li>Check date button will check the date inputs, and print them to Date output.");
        out.println ("<li>Check name button will check the name input, and print it to Name output.");
        out.println ("</ul>");
        out.println ("<hr>");
        out.println ("<center>");
        out.println ("<form onsubmit=\"return submitIt(this)\" method=\"post\" action=\"http://cs.gmu.edu:8080/offutt/servlet/formHandler\">");
        out.println ("<table>");
        out.println ("<tr>");
        out.println ("<td bgcolor=pink align=middle>Year</td>");
        out.println ("<td><input type=\"text\" name=\"Year\" id=\"Year\"></td>");
        out.println ("<td bgcolor=pink align=middle>Month</td>");
        out.println ("<td><input type=\"text\" name=\"Month\" id=\"Month\"></td>");
        out.println ("<td bgcolor=pink align=middle>Day</td>");
        out.println ("<td><input type=\"text\" name=\"Day\" id=\"Day\"></td>");
        out.println ("</tr>");
        out.println ("<tr>");
        out.println ("<td bgcolor=pink align=middle>Name</td>");
        out.println ("<td><input type=\"text\" name=\"Name\" id=\"Name\"></td>");
        out.println ("</tr>");
        out.println ("<tr>");
        out.println ("<td>Date output</td>");
        out.println ("<td><input type=\"text\" name=\"Dateoutput\" id=\"Dateoutput\" value=\"Null\"></td>");
        out.println ("<td>Name output</td>");
        out.println ("<td><input type=\"text\" name=\"Nameoutput\" id=\"Nameoutput\" value=\"Null\"></td>");
        out.println ("</tr>");
        out.println ("<tr>");
        out.println ("<td><button type=\"button\" onclick=\"checkDate()\">Check date</button></td>");
        out.println ("<td><button type=\"button\" onclick=\"checkName()\">Check name</button></td>");
        out.println ("</tr>");
        out.println ("<tr>");
        out.println ("<td><input type=\"submit\" value=\"Submit\" name=\"submit\"></td>");
        out.println ("<td><input type=\"reset\" value=\"Reset\" name=\"reset\"></td>");
        out.println ("</tr>");
        out.println ("</table>");
        out.println ("</form>");
        out.println ("</center>");
        out.println ("</body>");
        out.println ("</html>");

        out.close ();

    }
}
