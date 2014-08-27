<%@page language="java"%>
<%@page import="mzhang8.HW8RestUpdateBean"%>
<%@page import="mzhang8.HW8RestInfo"%>
<jsp:useBean id="rUpdateBean" class="mzhang8.HW8RestUpdateBean" scope="page"/>
<jsp:setProperty name="rUpdateBean" property="*"/>

<%
  session = request.getSession(false);
  String userName;
  if(session != null) {
    userName = (String)session.getAttribute("UserName");
    if(userName == null) { %>
      <jsp:forward page="/servlet/mzhang8.HW8RestLogin"/>
<% } } %>

<html>       
  <head>
    <title>SWE 642 HW8 Restaurant Update</title>
  </head>           
  
  <%if(rUpdateBean.getFoodType() == null) {
      HW8RestInfo r = rUpdateBean.getUpdateReview();%>
      
  <body>        
    <h1 align="center">SWE 642 HW8 Restaurant Update -- Ming Zhang</h1>
    <h2 align="center"><u>Please Enter Restaurant Information</u></h2>
    <hr>
    <div align="center">       
    <form method="post" action="http://apps-swe642.vse.gmu.edu:8080/swe642/jsp/mzhang8/HW8Update.jsp">
      <table>
        <tr>
          <td>Restaurant Name</td>
          <td><input type="text" name="restaurantName" value="<%=r.getRName()%>"></td>
        </tr>
        <tr>
          <td>Food Type</td>
          <td>
            <select size="1" name="foodType">
            <%if(r.getFType().equals("American Food")) {%>
              <option selected value="American Food">American Food</option>
              <option value="Chinese Food">Chinese Food</option>
              <option value="Japanese Food">Japanese Food</option>
              <option value="Mexico Food">Mexico Food</option>
              <option value="Italian Food">Itanlian Food</option>
              <option value="French Food">French Food</option>
              <option value="Other">Other</option>
              
            <%} else if(r.getFType().equals("Chinese Food")) {%>
              <option value="American Food">American Food</option>
              <option selected value="Chinese Food">Chinese Food</option>
              <option value="Japanese Food">Japanese Food</option>
              <option value="Mexico Food">Mexico Food</option>
              <option value="Italian Food">Itanlian Food</option>
              <option value="French Food">French Food</option>
              <option value="Other">Other</option>
              
            <%} else if(r.getFType().equals("Japanese Food")) {%>
              <option value="American Food">American Food</option>
              <option value="Chinese Food">Chinese Food</option>
              <option selected value="Japanese Food">Japanese Food</option>
              <option value="Mexico Food">Mexico Food</option>
              <option value="Italian Food">Itanlian Food</option>
              <option value="French Food">French Food</option>
              <option value="Other">Other</option>
              
            <%} else if(r.getFType().equals("Mexico Food")) {%>
              <option value="American Food">American Food</option>
              <option value="Chinese Food">Chinese Food</option>
              <option value="Japanese Food">Japanese Food</option>
              <option selected value="Mexico Food">Mexico Food</option>
              <option value="Italian Food">Itanlian Food</option>
              <option value="French Food">French Food</option>
              <option value="Other">Other</option>
              
            <%} else if(r.getFType().equals("Italian Food")) {%>
              <option value="American Food">American Food</option>
              <option value="Chinese Food">Chinese Food</option>
              <option value="Japanese Food">Japanese Food</option>
              <option value="Mexico Food">Mexico Food</option>
              <option selected value="Italian Food">Itanlian Food</option>
              <option value="French Food">French Food</option>
              <option value="Other">Other</option>
              
            <%} else if(r.getFType().equals("French Food")) {%>
              <option value="American Food">American Food</option>
              <option value="Chinese Food">Chinese Food</option>
              <option value="Japanese Food">Japanese Food</option>
              <option value="Mexico Food">Mexico Food</option>
              <option value="Italian Food">Itanlian Food</option>
              <option selected value="French Food">French Food</option>
              <option value="Other">Other</option>
            
            <%} else if(r.getFType().equals("Other")) {%>
              <option value="American Food">American Food</option>
              <option value="Chinese Food">Chinese Food</option>
              <option value="Japanese Food">Japanese Food</option>
              <option value="Mexico Food">Mexico Food</option>
              <option value="Italian Food">Itanlian Food</option>
              <option value="French Food">French Food</option>
              <option selected value="Other">Other</option>
              
            <%}%>           
            </select>
          </td>
        </tr>
        <tr>
          <td>Street</td>
          <td><input type="text" name="street" value="<%=r.getStreet()%>"></td>
        </tr>
        <tr>
          <td>City</td>
          <td><input type="text" name="city" value="<%=r.getCity()%>"></td>
        </tr>
        <tr>
          <td>State</td>
          <td><input type="text" name="state" value="<%=r.getState()%>"></td>
        </tr>
        <tr>
          <td>Zip Code</td>
          <td><input type="text" name="zipCode" value="<%=r.getZCode()%>"></td>
        </tr>
        <tr>
          <td>Year</td>
          <td><input type="text" name="year" value="<%=r.getYear()%>"></td>
        </tr>
        <tr>
          <td>Month</td>
          <td><input type="text" name="month" value="<%=r.getMonth()%>"></td>
        </tr>
        <tr>
          <td>Day</td>
          <td><input type="text" name="day" value="<%=r.getDay()%>"></td>
        </tr>
        <tr bgcolor="#333388">
          <td></td>
          <td align="center"><font color="#FFFFFF"><b>Good</b></font></td>
          <td align="center" width="26%"><font color="#FFFFFF"><b>Fair</b></font></td>
          <td align="center" width="26%"><FONT color="#FFFFFF"><b>Bad</b></font></td>
        </tr>
        <tr>
          <td>Rate</td>
          <%if(r.getRate().equals("Good")) {%>
          <td align="center"><input type="radio" value="Good" checked name="rate"></td>
          <td align="center"><input type="radio" value="Fair" name="rate"></td>
          <td align="center"><input type="radio" value="Bad" name="rate"></td>
          
          <%} else if(r.getRate().equals("Fair")) {%>
          <td align="center"><input type="radio" value="Good" name="rate"></td>
          <td align="center"><input type="radio" value="Fair" checked name="rate"></td>
          <td align="center"><input type="radio" value="Bad" name="rate"></td>
          
          <%} else if(r.getRate().equals("Bad")) {%>
          <td align="center"><input type="radio" value="Good" name="rate"></td>
          <td align="center"><input type="radio" value="Fair" name="rate"></td>
          <td align="center"><input type="radio" value="Bad" checked name="rate"></td>
          
          <%}%>
        </tr>
        <tr>
          <td>What To Say:</td>
          <td><textarea rows="10" name="comment"><%=r.getComment()%></textarea></td>
        </tr>
        
        <tr>
          <td>Privacy</td>
          <td>
          <select size="1" name="privacy">
          <%if(r.getPrivacy().equals("Public")) {%>
          <option selected value="Public">Public</option>
          <option value="Private">Private</option>
                   
          <%} else if(r.getPrivacy().equals("Private")) {%>
          <option selected value="Private">Private</option>
          <option value="Public">Public</option>
          
          <%}%>
          </select>
          </td>
        </tr>
        <input type="hidden" name="owner" value="<%=r.getOwner()%>"/>
       
        <tr>
          <td><input type="hidden" value="<%=rUpdateBean.getUpdate()%>" name="update">
          <td><input type="submit" value="Submit" name="submit"></td>
          <td><input type="reset" value="Reset" name="reset"></td>
        </tr>
      </table>
    </form>
  </body>
    
    <%} else {
        rUpdateBean.validate();    
        if(rUpdateBean.getErrMsg() == null) {%>
    
	  <body bgcolor="#EEEEEE">
	    <center><h2>SWE 642 HW8 Restaurant Update -- Ming Zhang</h2></center>
	    <hr>
	    <div align=center>
	    <table border=0>
	      <tr bgcolor="#C0C0C0">
	        <td align=middle>Restaurant Name:</td>
	        <td align=middle><%=rUpdateBean.getRestaurantName()%><br>(<%=rUpdateBean.getFoodType()%>)</td>
	      </tr>
	      <tr bgcolor="#DDDDDD">
	        <td align=middle>Restaurant Address:</td>
	        <td align=middle><%=rUpdateBean.getStreet()%><br><%=rUpdateBean.getCity()%>, <%=rUpdateBean.getState()%>, <%=rUpdateBean.getZipCode()%></td>
	      </tr>
	      <tr bgcolor="#C0C0C0">
	        <td align=middle>Visit Date:</td>
	        <td align=middle><%=rUpdateBean.getYear()%>-<%=rUpdateBean.getMonth()%>-<%=rUpdateBean.getDay()%></td>
	      </tr>
	      <tr bgcolor="#DDDDDD">
	        <td align=middle>Your Rate:</td>
	        <td align=middle><%=rUpdateBean.getRate()%></td>
	      </tr>
	      <tr bgcolor="#C0C0C0">
	        <td align=middle>Comments:</td>
	        <td align=middle><textarea rows="10" bgcolor="#123456"><%=rUpdateBean.getComment()%></textarea></td>
	      </tr>
	      
	      <tr bgcolor="#DDDDDD">
            <td align=middle>Privacy:</td>
            <td align=middle><%=rUpdateBean.getPrivacy()%></td>
          </tr>
          <tr bgcolor="#C0C0C0">
            <td align=middle>Owner:</td>
            <td align=middle><%=rUpdateBean.getOwner()%></td>
          </tr>
      
	    </table>
	    <input type="button" value="Home" onclick="javascript:location.href='http://apps-swe642.vse.gmu.edu:8080/swe642/jsp/mzhang8/HW8.jsp'"/> 
	    </div>
	  </body>
	
	<%} else {%>
	
	<body>
    <h1 align="center">SWE 642 HW8 Restaurant Update -- Ming Zhang</h1>
    <h2 align="center"><u>Please Enter Restaurant Information</u></h2>
    <hr> 
    <p><font color="#FF0011">
      <%=rUpdateBean.getErrMsg()%>
    <hr>    
    <div align="center">
    <form method="post" action="http://apps-swe642.vse.gmu.edu:8080/swe642/jsp/mzhang8/HW8Update.jsp">
      <table>
        <tr>
          <td>Restaurant Name</td>
          <td><input type="text" name="restaurantName" value="<%=rUpdateBean.getRestaurantName()%>"></td>
        </tr>
        <tr>
          <td>Food Type</td>
          <td>
            <select size="1" name="foodType">
            <%if(rUpdateBean.getFoodType().equals("American Food")) {%>
              <option selected value="American Food">American Food</option>
              <option value="Chinese Food">Chinese Food</option>
              <option value="Japanese Food">Japanese Food</option>
              <option value="Mexico Food">Mexico Food</option>
              <option value="Italian Food">Itanlian Food</option>
              <option value="French Food">French Food</option>
              <option value="Other">Other</option>
              
            <%} else if(rUpdateBean.getFoodType().equals("Chinese Food")) {%>
              <option value="American Food">American Food</option>
              <option selected value="Chinese Food">Chinese Food</option>
              <option value="Japanese Food">Japanese Food</option>
              <option value="Mexico Food">Mexico Food</option>
              <option value="Italian Food">Itanlian Food</option>
              <option value="French Food">French Food</option>
              <option value="Other">Other</option>
              
            <%} else if(rUpdateBean.getFoodType().equals("Japanese Food")) {%>
              <option value="American Food">American Food</option>
              <option value="Chinese Food">Chinese Food</option>
              <option selected value="Japanese Food">Japanese Food</option>
              <option value="Mexico Food">Mexico Food</option>
              <option value="Italian Food">Itanlian Food</option>
              <option value="French Food">French Food</option>
              <option value="Other">Other</option>
              
            <%} else if(rUpdateBean.getFoodType().equals("Mexico Food")) {%>
              <option value="American Food">American Food</option>
              <option value="Chinese Food">Chinese Food</option>
              <option value="Japanese Food">Japanese Food</option>
              <option selected value="Mexico Food">Mexico Food</option>
              <option value="Italian Food">Itanlian Food</option>
              <option value="French Food">French Food</option>
              <option value="Other">Other</option>
              
            <%} else if(rUpdateBean.getFoodType().equals("Italian Food")) {%>
              <option value="American Food">American Food</option>
              <option value="Chinese Food">Chinese Food</option>
              <option value="Japanese Food">Japanese Food</option>
              <option value="Mexico Food">Mexico Food</option>
              <option selected value="Italian Food">Itanlian Food</option>
              <option value="French Food">French Food</option>
              <option value="Other">Other</option>
              
            <%} else if(rUpdateBean.getFoodType().equals("French Food")) {%>
              <option value="American Food">American Food</option>
              <option value="Chinese Food">Chinese Food</option>
              <option value="Japanese Food">Japanese Food</option>
              <option value="Mexico Food">Mexico Food</option>
              <option value="Italian Food">Itanlian Food</option>
              <option selected value="French Food">French Food</option>
              <option value="Other">Other</option>
            
            <%} else if(rUpdateBean.getFoodType().equals("Other")) {%>
              <option value="American Food">American Food</option>
              <option value="Chinese Food">Chinese Food</option>
              <option value="Japanese Food">Japanese Food</option>
              <option value="Mexico Food">Mexico Food</option>
              <option value="Italian Food">Itanlian Food</option>
              <option value="French Food">French Food</option>
              <option selected value="Other">Other</option>
              
            <%}%>
            </select>
          </td>
        </tr>
        <tr>
          <td>Street</td>
          <td><input type="text" name="street" value="<%=rUpdateBean.getStreet()%>"></td>
        </tr>
        <tr>
          <td>City</td>
          <td><input type="text" name="city" value="<%=rUpdateBean.getCity()%>"></td>
        </tr>
        <tr>
          <td>State</td>
          <td><input type="text" name="state" value="<%=rUpdateBean.getState()%>"></td>
        </tr>
        <tr>
          <td>Zip Code</td>
          <td><input type="text" name="zipCode" value="<%=rUpdateBean.getZipCode()%>"></td>
        </tr>
        <tr>
          <td>Year</td>
          <td><input type="text" name="year" value="<%=rUpdateBean.getYear()%>"></td>
        </tr>
        <tr>
          <td>Month</td>
          <td><input type="text" name="month" value="<%=rUpdateBean.getMonth()%>"></td>
        </tr>
        <tr>
          <td>Day</td>
          <td><input type="text" name="day" value="<%=rUpdateBean.getDay()%>"></td>
        </tr>
        <tr bgcolor="#333388">
          <td></td>
          <td align="center"><font color="#FFFFFF"><b>Good</b></font></td>
          <td align="center" width="26%"><font color="#FFFFFF"><b>Fair</b></font></td>
          <td align="center" width="26%"><FONT color="#FFFFFF"><b>Bad</b></font></td>
        </tr>
        <tr>
          <td>Rate</td>
          <%if(rUpdateBean.getRate().equals("Good")) {%>
          <td align="center"><input type="radio" value="Good" checked name="rate"></td>
          <td align="center"><input type="radio" value="Fair" name="rate"></td>
          <td align="center"><input type="radio" value="Bad" name="rate"></td>
          
          <%} else if(rUpdateBean.getRate().equals("Fair")) {%>
          <td align="center"><input type="radio" value="Good" name="rate"></td>
          <td align="center"><input type="radio" value="Fair" checked name="rate"></td>
          <td align="center"><input type="radio" value="Bad" name="rate"></td>
          
          <%} else if(rUpdateBean.getRate().equals("Bad")) {%>
          <td align="center"><input type="radio" value="Good" name="rate"></td>
          <td align="center"><input type="radio" value="Fair" name="rate"></td>
          <td align="center"><input type="radio" value="Bad" checked name="rate"></td>
          
          <%}%>
        </tr>
        <tr>
          <td>What To Say:</td>
          <td><textarea rows="10" name="comment"><%=rUpdateBean.getComment()%></textarea></td>
        </tr>
        
        <tr>
          <td>Privacy</td>
          <td>
          <select size="1" name="privacy">
          <%if(rUpdateBean.getPrivacy().equals("Public")) {%>
          <option selected value="Public">Public</option>
          <option value="Private">Private</option>
                   
          <%} else if(rUpdateBean.getPrivacy().equals("Private")) {%>
          <option selected value="Private">Private</option>
          <option value="Public">Public</option>
          
          <%}%>
          </select>
          </td>
        </tr> 
        <input type="hidden" name="owner" value="<%=rUpdateBean.getOwner()%>"/> 
       
        <tr>
          <td><input type="hidden" value="<%=rUpdateBean.getUpdate()%>" name="update">
          <td><input type="submit" value="Submit" name="submit"></td>
          <td><input type="reset" value="Reset" name="reset"></td>
        </tr>
      </table>
    </form>
    </div>
    <hr>
  </body>
	
  <%} }%>    
    
</html>
    
    