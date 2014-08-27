package mzhang8;

import javax.servlet.*;
import javax.servlet.http.*;

import java.io.*;
import java.util.*;
import java.lang.*;

public class HW8RestLogin extends HttpServlet {
   private static String userName;
   private static String passWord;
   private static boolean invalidID = false;
   
   private static String loginPage = "http://apps-swe642.vse.gmu.edu:8080/swe642/servlet/mzhang8.HW8RestLogin";
   private static String restInfoPage = "http://apps-swe642.vse.gmu.edu:8080/swe642/jsp/mzhang8/HW8.jsp";
   private static String pwFile = "/data/swe642fall2013/swe642/resources/mzhang8/password";

   public void doGet (HttpServletRequest req, HttpServletResponse res)
	   throws ServletException, IOException {
	
	   res.setContentType ("TEXT/HTML");
	   PrintWriter out = res.getWriter ();
	
	   out.println ("<HTML>");
	   out.println ("");
	   out.println ("<HEAD>");
	   out.println (" <TITLE>SWE 642 HW8 Rest Login</TITLE>");
	   out.println ("</HEAD>");
	
	   out.println ("");
	   out.println ("<BODY>");
	
	   out.println ("<B><CENTER>Login form to the HW8 Restaurant Review system -- Ming Zhang</CENTER></B>");
	   if (invalidID) {
	      invalidID = false;
	      out.println ("<BR><FONT Color=\"red\">Invalid user ID, password pair. Please try again.</FONT><BR><BR>");
	   }
	   
	   out.println("<div align=\"center\">");
	   out.println("If don't have an account, could use anonymous/anonymous to login.\n" +
	   		"1. Press View Own Reviews button to view and update your own reviews.\n" +
	   		"2. Press View Other Reviews button to view other users' public reviews, and you can add a new comment.\n" +
	   		"3. If you login as anonymous, your new comment will be labeled as anonymous.");
	   //Login form
	   out.println ("<FORM Method=\"POST\"");
	   out.println ("      Action=\"" + loginPage + "\" ID=\"form1\" Name=\"form1\">");
	   out.println("<input type=\"hidden\" name=\"FormName\" value=\"login\"/>");
	   out.println ("<TABLE Cellspacing=\"0\" Cellpadding=\"3\" Border=\"0\">");
	   out.println ("<TR>");
	   out.println (" <TD>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</TD>");
	   out.println (" <TD>UserName:</TD>");
	   out.println (" <TD><INPUT Type=\"text\" Name=\"UserName\" Size=\"10\" MaxLength=\"20\"></TD>");
	   out.println ("</TR>");
	   out.println ("<TR>");
	   out.println (" <TD></TD>");
	   out.println (" <TD>Password:</TD>");
	   out.println (" <TD><INPUT Type=\"password\" Name=\"PassWord\" size=\"10\" maxlength=\"20\"></TD>");
	   out.println ("</TR>");
	   out.println ("<TR><TD></TD><TD Colspan=\"2\" Align=\"center\"><INPUT Type=\"submit\" Value=\"Login\"></TD></TR>");
	   out.println ("</TABLE>");
	   out.println ("</FORM>");
	   
	   //Register form
	   out.println ("<FORM Method=\"POST\"");
	   out.println ("      Action=\"" + loginPage + "\" ID=\"form2\" Name=\"form2\">");
	   out.println("<input type=\"hidden\" name=\"FormName\" value=\"register\"/>");
	   out.println ("<TABLE Cellspacing=\"0\" Cellpadding=\"3\" Border=\"0\">");
	   out.println ("<TR>");
	   out.println (" <TD>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</TD>");
	   out.println (" <TD>UserName:</TD>");
	   out.println (" <TD><INPUT Type=\"text\" Name=\"UserName\" Size=\"10\" MaxLength=\"20\"></TD>");
	   out.println ("</TR>");
	   out.println ("<TR>");
	   out.println (" <TD></TD>");
	   out.println (" <TD>Password:</TD>");
	   out.println (" <TD><INPUT Type=\"password\" Name=\"PassWord\" size=\"10\" maxlength=\"20\"></TD>");
	   out.println ("</TR>");
	   out.println ("<TR><TD></TD><TD Colspan=\"2\" Align=\"center\"><INPUT Type=\"submit\" Value=\"Register\"></TD></TR>");
	   out.println ("</TABLE>");
	   out.println ("</FORM>");
	   
	   out.println("</div>");
	
	   out.println (" <SCRIPT language=\"javascript\">");
	   out.println (" <!--");
	   out.println ("   document.form1.UserName.focus()");
	   out.println (" //-->");
	   out.println (" </SCRIPT>");
	
	   out.println ("</BODY>");
	   out.println ("</HTML>");
	
	   out.close ();
	}

	public void doPost (HttpServletRequest req, HttpServletResponse res)
	   throws ServletException, IOException {
	   if(req.getParameter("FormName").equals("login")) {
		   userName = req.getParameter ("UserName");
		   passWord   = req.getParameter ("PassWord");
		
		   HttpSession session = req.getSession(true);
		   session.setMaxInactiveInterval(60);
		   
		   HashMap<String, String> pairs = null;
		   if((new File(pwFile)).exists()) {
			   FileInputStream fin = new FileInputStream(pwFile);
			   ObjectInputStream oin = new ObjectInputStream(fin);
			   try {
				pairs = (HashMap<String, String>)oin.readObject();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			   oin.close();
			   
			   if(pairs.containsKey(userName) && (pairs.get(userName).equals(passWord))) {
				   session.setAttribute ("UserName", userName);
				   res.sendRedirect (restInfoPage);
			   }
			   else {
				   session.setAttribute ("UserIDDemo", "");
				   invalidID = true;
				   doGet (req, res); 
			   }
		   }
		   else {
			   session.setAttribute ("UserIDDemo", "");
			   invalidID = true;
			   doGet (req, res);
		   }
	   }
	   else if(req.getParameter("FormName").equals("register")) { 
		   userName = req.getParameter ("UserName");
		   passWord = req.getParameter ("PassWord");
		   
		   if(userName.equals("") || passWord.equals("")) {
			   doGet(req, res);
			   return;
		   }
			   		   
		   HashMap<String, String> pairs = null;
		   if((new File(pwFile)).exists()) {
				FileInputStream fin = new FileInputStream(pwFile);
				ObjectInputStream oin = new ObjectInputStream(fin);
				try {
					pairs = (HashMap<String, String>)oin.readObject();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				oin.close();
			}
			else {
				pairs = new HashMap<String, String>();
			}
		   
		   if(pairs.containsKey(userName)) {
			   doGet(req, res);
			   return;
		   }
		   
		   pairs.put(userName, passWord);
		   
		   FileOutputStream fout = new FileOutputStream(pwFile);
		   ObjectOutputStream oout = new ObjectOutputStream(fout);
		   oout.writeObject(pairs);
		   oout.close();
		   
		   HttpSession session = req.getSession (true);
		   session.setMaxInactiveInterval (60);		   
		   session.setAttribute ("UserName", userName);
		   res.sendRedirect (restInfoPage);
	   }
	   
	}
}
