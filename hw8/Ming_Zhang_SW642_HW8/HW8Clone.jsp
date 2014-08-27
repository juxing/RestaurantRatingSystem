
<%@page language="java"%>
<%@page import="mzhang8.HW8RestCloneBean"%>
<%@page import="mzhang8.HW8RestInfo"%>
<jsp:useBean id="rCloneBean" class="mzhang8.HW8RestCloneBean" scope="page"/>
<jsp:setProperty name="rCloneBean" property="*"/>

<html>       
  <head>
    <title>SWE 642 HW8 Restaurant Clone</title>
  </head>           
  
  <%HW8RestInfo r = rCloneBean.getCloneReview();%>
      
  <body>        
    <h1 align="center">SWE 642 HW8 Restaurant Clone -- Ming Zhang</h1>
    <h2 align="center"><u>Please Enter Restaurant Information</u></h2>
    <hr>
    Below Restaurant Information has been cloned!
    <div align="center">       
    <form method="post" action="http://apps-swe642.vse.gmu.edu:8080/swe642/jsp/mzhang8/HW8Clone.jsp">
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
             
      </table>
    </form>
    <input type="button" value="Home" onclick="javascript:location.href='http://apps-swe642.vse.gmu.edu:8080/swe642/jsp/mzhang8/HW8.jsp'"/>
  </body>   

</html>
    
    