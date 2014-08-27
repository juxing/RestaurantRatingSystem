<%
  session = request.getSession(false);
  if(session != null) {
    String userName = (String)session.getAttribute("UserName");
    if(userName == null) { %>
      <jsp:forward page="/servlet/mzhang8.HW8RestLogin"/>
<% } } %>

<html>
  <head>
    <title>SWE 642 HW8 Restaurant</title>
  </head>
  <body>  
    <h1 align="center">SWE 642 HW8 Restaurant -- Ming Zhang</h1>
    <h2 align="center"><u>Please Enter Restaurant Information</u></h2>
    <hr>
    <div align="center">
    <form method="post" action="http://apps-swe642.vse.gmu.edu:8080/swe642/jsp/mzhang8/HW8Validate.jsp">
      <table>
        <tr>
          <td>Restaurant Name</td>
          <td><input type="text" name="restaurantName"></td>
        </tr>
        <tr>
          <td>Food Type</td>
          <td>
            <select size="1" name="foodType">
              <option selected value="American Food">American Food</option>
              <option value="Chinese Food">Chinese Food</option>
              <option value="Japanese Food">Japanese Food</option>
              <option value="Mexico Food">Mexico Food</option>
              <option value="Italian Food">Itanlian Food</option>
              <option value="French Food">French Food</option>
              <option value="Other">Other</option>
            </select>
          </td>
        </tr>
        <tr>
          <td>Street</td>
          <td><input type="text" name="street"></td>
        </tr>
        <tr>
          <td>City</td>
          <td><input type="text" name="city"></td>
        </tr>
        <tr>
          <td>State</td>
          <td><input type="text" name="state"></td>
        </tr>
        <tr>
          <td>Zip Code</td>
          <td><input type="text" name="zipCode"></td>
        </tr>
        <tr>
          <td>Year</td>
          <td><input type="text" name="year"></td>
        </tr>
        <tr>
          <td>Month</td>
          <td><input type="text" name="month"></td>
        </tr>
        <tr>
          <td>Day</td>
          <td><input type="text" name="day"></td>
        </tr>
        <tr bgcolor="#333388">
          <td></td>
          <td align="center"><font color="#FFFFFF"><b>Good</b></font></td>
          <td align="center" width="26%"><font color="#FFFFFF"><b>Fair</b></font></td>
          <td align="center" width="26%"><FONT color="#FFFFFF"><b>Bad</b></font></td>
        </tr>
        <tr>
          <td>Rate</td>
          <td align="center"><input type="radio" value="Good" checked name="rate"></td>
          <td align="center"><input type="radio" value="Fair" name="rate"></td>
          <td align="center"><input type="radio" value="Bad" name="rate"></td>
        </tr>
        <tr>
          <td>What To Say:</td>
          <td><textarea rows="10" name="comment"></textarea></td>
        </tr>
        
        <tr>
          <td>Privacy</td>
          <td>
            <select size="1" name="privacy">
              <option selected value="Public">Public</option>
              <option value="Private">Private</option>
            </select>
          </td>
        </tr>
        <input type="hidden" name="owner" value="<%=(String)session.getAttribute("UserName")%>"/>
        
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
      <input type="submit" value="View Own Reviews" name="View Reviews">
    </form>
    
    <form method="get" action="http://apps-swe642.vse.gmu.edu:8080/swe642/jsp/mzhang8/HW8ReviewOthers.jsp">
      <input type="submit" value="View Other Reviews" name="View Reviews">
    </form>
    </div>
  </body>
</html>

    