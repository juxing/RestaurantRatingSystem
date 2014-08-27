<%@page language="java"%>
<%@page import="mzhang8.HW8RestValidatorBean"%>
<jsp:useBean id="rValBean" class="mzhang8.HW8RestValidatorBean" scope="page"/>
<jsp:setProperty name="rValBean" property="*"/>
<%rValBean.validate();%>

<%if(rValBean.getErrMsg() == null) {%>
<html>
  <head>
    <title>Restaurant Information</title>
  </head>
  <body bgcolor="#EEEEEE">
    <center><h2>SWE 642 HW8 Restaurant Information -- Ming Zhang</h2></center>
    <hr>
    <div align=center>
    <table border=0>
      <tr bgcolor="#C0C0C0">
        <td align=middle>Restaurant Name:</td>
        <td align=middle><%=rValBean.getRestaurantName()%><br>(<%=rValBean.getFoodType()%>)</td>
      </tr>
      <tr bgcolor="#DDDDDD">
        <td align=middle>Restaurant Address:</td>
        <td align=middle><%=rValBean.getStreet()%><br><%=rValBean.getCity()%>, <%=rValBean.getState()%>, <%=rValBean.getZipCode()%></td>
      </tr>
      <tr bgcolor="#C0C0C0">
        <td align=middle>Visit Date:</td>
        <td align=middle><%=rValBean.getYear()%>-<%=rValBean.getMonth()%>-<%=rValBean.getDay()%></td>
      </tr>
      <tr bgcolor="#DDDDDD">
        <td align=middle>Your Rate:</td>
        <td align=middle><%=rValBean.getRate()%></td>
      </tr>
      <tr bgcolor="#C0C0C0">
        <td align=middle>Comments:</td>
        <td align=middle><textarea rows="10" bgcolor="#123456"><%=rValBean.getComment()%></textarea></td>
      </tr>
      <tr bgcolor="#DDDDDD">
        <td align=middle>Privacy:</td>
        <td align=middle><%=rValBean.getPrivacy()%></td>
      </tr>
      <tr bgcolor="#C0C0C0">
        <td align=middle>Owner:</td>
        <td align=middle><%=rValBean.getOwner()%></td>
      </tr>
    </table>
    <input type="button" value="Home" onclick="javascript:location.href='http://apps-swe642.vse.gmu.edu:8080/swe642/jsp/mzhang8/HW8.jsp'"/> 
    </div>
  </body>
</html>

<%} else {%>
<html>
  <head>
    <title>SWE 642 HW8 Restaurant</title>
  </head>
  <body>
    <h1 align="center">SWE 642 HW8 Restaurant -- Ming Zhang</h1>
    <h2 align="center"><u>Please Enter Restaurant Information</u></h2>
    <hr> 
    <p><font color="#FF0011">
      <%=rValBean.getErrMsg()%>
    <hr>    
    <div align="center">
    <form method="post" action="http://apps-swe642.vse.gmu.edu:8080/swe642/jsp/mzhang8/HW8Validate.jsp">
      <table>
        <tr>
          <td>Restaurant Name</td>
          <td><input type="text" name="restaurantName" value="<%=rValBean.getRestaurantName()%>"></td>
        </tr>
        <tr>
          <td>Food Type</td>
          <td>
            <select size="1" name="foodType">
            <%if(rValBean.getFoodType().equals("American Food")) {%>
              <option selected value="American Food">American Food</option>
              <option value="Chinese Food">Chinese Food</option>
              <option value="Japanese Food">Japanese Food</option>
              <option value="Mexico Food">Mexico Food</option>
              <option value="Italian Food">Itanlian Food</option>
              <option value="French Food">French Food</option>
              <option value="Other">Other</option>
              
            <%} else if(rValBean.getFoodType().equals("Chinese Food")) {%>
              <option value="American Food">American Food</option>
              <option selected value="Chinese Food">Chinese Food</option>
              <option value="Japanese Food">Japanese Food</option>
              <option value="Mexico Food">Mexico Food</option>
              <option value="Italian Food">Itanlian Food</option>
              <option value="French Food">French Food</option>
              <option value="Other">Other</option>
              
            <%} else if(rValBean.getFoodType().equals("Japanese Food")) {%>
              <option value="American Food">American Food</option>
              <option value="Chinese Food">Chinese Food</option>
              <option selected value="Japanese Food">Japanese Food</option>
              <option value="Mexico Food">Mexico Food</option>
              <option value="Italian Food">Itanlian Food</option>
              <option value="French Food">French Food</option>
              <option value="Other">Other</option>
              
            <%} else if(rValBean.getFoodType().equals("Mexico Food")) {%>
              <option value="American Food">American Food</option>
              <option value="Chinese Food">Chinese Food</option>
              <option value="Japanese Food">Japanese Food</option>
              <option selected value="Mexico Food">Mexico Food</option>
              <option value="Italian Food">Itanlian Food</option>
              <option value="French Food">French Food</option>
              <option value="Other">Other</option>
              
            <%} else if(rValBean.getFoodType().equals("Italian Food")) {%>
              <option value="American Food">American Food</option>
              <option value="Chinese Food">Chinese Food</option>
              <option value="Japanese Food">Japanese Food</option>
              <option value="Mexico Food">Mexico Food</option>
              <option selected value="Italian Food">Itanlian Food</option>
              <option value="French Food">French Food</option>
              <option value="Other">Other</option>
              
            <%} else if(rValBean.getFoodType().equals("French Food")) {%>
              <option value="American Food">American Food</option>
              <option value="Chinese Food">Chinese Food</option>
              <option value="Japanese Food">Japanese Food</option>
              <option value="Mexico Food">Mexico Food</option>
              <option value="Italian Food">Itanlian Food</option>
              <option selected value="French Food">French Food</option>
              <option value="Other">Other</option>
            
            <%} else if(rValBean.getFoodType().equals("Other")) {%>
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
          <td><input type="text" name="street" value="<%=rValBean.getStreet()%>"></td>
        </tr>
        <tr>
          <td>City</td>
          <td><input type="text" name="city" value="<%=rValBean.getCity()%>"></td>
        </tr>
        <tr>
          <td>State</td>
          <td><input type="text" name="state" value="<%=rValBean.getState()%>"></td>
        </tr>
        <tr>
          <td>Zip Code</td>
          <td><input type="text" name="zipCode" value="<%=rValBean.getZipCode()%>"></td>
        </tr>
        <tr>
          <td>Year</td>
          <td><input type="text" name="year" value="<%=rValBean.getYear()%>"></td>
        </tr>
        <tr>
          <td>Month</td>
          <td><input type="text" name="month" value="<%=rValBean.getMonth()%>"></td>
        </tr>
        <tr>
          <td>Day</td>
          <td><input type="text" name="day" value="<%=rValBean.getDay()%>"></td>
        </tr>
        <tr bgcolor="#333388">
          <td></td>
          <td align="center"><font color="#FFFFFF"><b>Good</b></font></td>
          <td align="center" width="26%"><font color="#FFFFFF"><b>Fair</b></font></td>
          <td align="center" width="26%"><FONT color="#FFFFFF"><b>Bad</b></font></td>
        </tr>
        <tr>
          <td>Rate</td>
          <%if(rValBean.getRate().equals("Good")) {%>
          <td align="center"><input type="radio" value="Good" checked name="rate"></td>
          <td align="center"><input type="radio" value="Fair" name="rate"></td>
          <td align="center"><input type="radio" value="Bad" name="rate"></td>
          
          <%} else if(rValBean.getRate().equals("Fair")) {%>
          <td align="center"><input type="radio" value="Good" name="rate"></td>
          <td align="center"><input type="radio" value="Fair" checked name="rate"></td>
          <td align="center"><input type="radio" value="Bad" name="rate"></td>
          
          <%} else if(rValBean.getRate().equals("Bad")) {%>
          <td align="center"><input type="radio" value="Good" name="rate"></td>
          <td align="center"><input type="radio" value="Fair" name="rate"></td>
          <td align="center"><input type="radio" value="Bad" checked name="rate"></td>
          
          <%}%>
        </tr>
        <tr>
          <td>What To Say:</td>
          <td><textarea rows="10" name="comment"><%=rValBean.getComment()%></textarea></td>
        </tr>
        <tr>
          <td>Privacy</td>
          <td>
          <select size="1" name="privacy">
          <%if(rValBean.getPrivacy().equals("Public")) {%>
          <option selected value="Public">Public</option>
          <option value="Private">Private</option>
                   
          <%} else if(rValBean.getPrivacy().equals("Private")) {%>
          <option selected value="Private">Private</option>
          <option value="Public">Public</option>
          
          <%}%>
          </select>
          </td>
        </tr>     
        
        <input type="hidden" name="owner" value="<%=rValBean.getOwner()%>"/>
           
        <tr>
          <td><input type="submit" value="Submit" name="submit"></td>
          <td><input type="reset" value="Reset" name="reset"></td>
        </tr>
      </table>
    </form>
    </div>
    <hr>
    <div align="center">
    <form method="get" action="http://apps-swe642.vse.gmu.edu:8080/swe642/jsp/mzhang8/HW8Review.jsp">
      <input type="submit" value="View Reviews" name="View Reviews">
    </form>
    </div>
  </body>
</html>    
<%}%>

