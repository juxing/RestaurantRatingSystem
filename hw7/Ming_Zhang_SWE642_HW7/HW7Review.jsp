<%@page language="java"%>
<%@page import="mzhang8.RestReviewBean"%>
<%@page import="mzhang8.RestInfo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="java.util.Iterator"%>
<jsp:useBean id="rRevBean" class="mzhang8.RestReviewBean" scope="page"/>
<jsp:setProperty name="rRevBean" property="*"/>

<html>   	
  <head>
    <title>Restaurant Information</title>
  </head>
  <body bgcolor="#EEEEEE">  	
    <center><h2>SWE 642 HW7 Restaurant Information -- Ming Zhang</h2></center>
    <hr>  	
    <div align=center>
    
    <%LinkedHashMap<RestInfo, Integer> changeReviews = null;
      if(rRevBean.getSortOrder() != null) {
        if(rRevBean.getSortOrder().equals("ByName")) {
          changeReviews = rRevBean.getSortByNameReviews();
        } else if(rRevBean.getSortOrder().equals("ByType")) {
          changeReviews = rRevBean.getSortByTypeReviews();
        } else if(rRevBean.getSortOrder().equals("ByRate")) {
          changeReviews = rRevBean.getSortByRateReviews();
        }
      } else if(rRevBean.getRateFilter() != null) {
        changeReviews = rRevBean.getRateFilteReviews();
      } else if(rRevBean.getFoodTypeFilter() != null) {
        changeReviews = rRevBean.getFoodTypeFilteReviews();
      } else if(rRevBean.getRestNameFilter() != null) {
        changeReviews = rRevBean.getRestNameFilteReviews();
      } 
      
      if(changeReviews != null) {
        Iterator<RestInfo> it = changeReviews.keySet().iterator();
        while(it.hasNext()) {
          RestInfo r = it.next();
          int index = changeReviews.get(r);%>
          
    <p>
	<table border=0>   	
	  <tr bgcolor="#C0C0C0">
	    <td align=middle>Restaurant Name:</td>
	    <td align=middle><%=r.getRName()%><br>(<%=r.getFType()%>)</td>  	
      </tr>	   	
      <tr bgcolor="#DDDDDD">   	
	    <td align=middle>Restaurant Address:</td>
	   	<td align=middle><%=r.getStreet()%><br><%=r.getCity()%>, <%=r.getState()%>, <%=r.getZCode()%></td>
      </tr>	   	
      <tr bgcolor="#C0C0C0">   	
        <td align=middle>Visit Date:</td>
	   	<td align=middle><%=r.getYear()%>-<%=r.getMonth()%>-<%=r.getDay()%></td>
      </tr>	   	
	  <tr bgcolor="#DDDDDD">   	
	    <td align=middle>Your Rate:</td>
	   	<td align=middle><%=r.getRate()%></td>
	  </tr>	   	
	  <tr bgcolor="#C0C0C0">   	
	   	<td align=middle>Comments:</td>
	   	<td align=middle><textarea rows="10" bgcolor="#123456"><%=r.getComment()%></textarea></td>
	  </tr>	    	
	</table>	
	</p>
	
	<form method="post" action="http://apps-swe642.vse.gmu.edu:8080/swe642/jsp/mzhang8/HW7Update.jsp"> 	   	
	  <input type="hidden" value="<%=index%>" name="update">
	  <input type="submit" value="Update" name="Update"> 
	</form>
	   	
	<form method="post" action="http://apps-swe642.vse.gmu.edu:8080/swe642/jsp/mzhang8/HW7Clone.jsp">  	   	
	  <input type="hidden" value="<%=index%>" name="clone">
	  <input type="submit" value="Clone" name="Clone">
	</form>
	
	<%} }
	  else {        
        ArrayList<RestInfo> reviews = rRevBean.getReviews();
        for(int i = 0; i < reviews.size(); i++) {%>   
      
    <p>
	<table border=0>   	
	  <tr bgcolor="#C0C0C0">
	    <td align=middle>Restaurant Name:</td>
	    <td align=middle><%=reviews.get(i).getRName()%><br>(<%=reviews.get(i).getFType()%>)</td>  	
      </tr>	   	
      <tr bgcolor="#DDDDDD">   	
	    <td align=middle>Restaurant Address:</td>
	   	<td align=middle><%=reviews.get(i).getStreet()%><br><%=reviews.get(i).getCity()%>, <%=reviews.get(i).getState()%>, <%=reviews.get(i).getZCode()%></td>
      </tr>	   	
      <tr bgcolor="#C0C0C0">   	
        <td align=middle>Visit Date:</td>
	   	<td align=middle><%=reviews.get(i).getYear()%>-<%=reviews.get(i).getMonth()%>-<%=reviews.get(i).getDay()%></td>
      </tr>	   	
	  <tr bgcolor="#DDDDDD">   	
	    <td align=middle>Your Rate:</td>
	   	<td align=middle><%=reviews.get(i).getRate()%></td>
	  </tr>	   	
	  <tr bgcolor="#C0C0C0">   	
	   	<td align=middle>Comments:</td>
	   	<td align=middle><textarea rows="10" bgcolor="#123456"><%=reviews.get(i).getComment()%></textarea></td>
	  </tr>	    	
	</table>	
	</p>
	   	
	<form method="post" action="http://apps-swe642.vse.gmu.edu:8080/swe642/jsp/mzhang8/HW7Update.jsp"> 	   	
	  <input type="hidden" value="<%=i%>" name="update">
	  <input type="submit" value="Update" name="Update"> 
	</form>
	   	
	<form method="post" action="http://apps-swe642.vse.gmu.edu:8080/swe642/jsp/mzhang8/HW7Clone.jsp">  	   	
	  <input type="hidden" value="<%=i%>" name="clone">
	  <input type="submit" value="Clone" name="Clone">
	</form>
	
	<%} }%> 
	
	</div>
    <hr>
    <div align="center">
    <form method="post" action="http://apps-swe642.vse.gmu.edu:8080/swe642/jsp/mzhang8/HW7Review.jsp">
      <select size="1" name="sortOrder">
        <option selected value="ByName">By Restaurant Name</option>
        <option value="ByType">By Food Type</option>
        <option value="ByRate">By Rate</option>
      </select>
      <input type="submit" value="Sort Reviews" name="Sort Reviews">
    </form>
    
    <form method="post" action="http://apps-swe642.vse.gmu.edu:8080/swe642/jsp/mzhang8/HW7Review.jsp">
      <select size="1" name="rateFilter">
        <option selected value="Good">Good</option>
        <option value="Fair">Fair</option>
        <option value="Bad">Bad</option>
      </select>
      <input type="submit" value="Filte By Rate" name="Filte By Rate">
    </form>
    
    <form method="post" action="http://apps-swe642.vse.gmu.edu:8080/swe642/jsp/mzhang8/HW7Review.jsp">
      <select size="1" name="foodTypeFilter">
        <option selected value="American Food">American Food</option>
        <option value="Chinese Food">Chinese Food</option>
        <option value="Japanese Food">Japanese Food</option>
        <option value="Mexico Food">Mexico Food</option>
        <option value="Italian Food">Itanlian Food</option>
        <option value="French Food">French Food</option>
        <option value="Other">Other</option>
      </select>
      <input type="submit" value="Filte By Food Type" name="Filte By Food Type">
    </form>
    
    <form method="post" action="http://apps-swe642.vse.gmu.edu:8080/swe642/jsp/mzhang8/HW7Review.jsp">
      <input type="text" name="restNameFilter">
      <input type="submit" value="Filte By Rest Name" name="Filte By Rest name">
    </form>
    <input type="button" value="Home" onclick="javascript:location.href='http://apps-swe642.vse.gmu.edu:8080/swe642/jsp/mzhang8/HW7.jsp'"/> 
  </body>
</html>
	
    
      
    