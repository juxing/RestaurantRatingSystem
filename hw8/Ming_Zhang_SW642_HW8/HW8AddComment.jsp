<%@page language="java"%>
<%@page import="mzhang8.HW8AddCommentBean"%>
<%@page import="mzhang8.HW8RestInfo"%>
<jsp:useBean id="rAddBean" class="mzhang8.HW8AddCommentBean" scope="page"/>
<jsp:setProperty name="rAddBean" property="*"/>

<html>       
  <head>
    <title>SWE 642 HW8 Add Comment</title>
  </head>           
  
  <%rAddBean.addNewComment();
    String redirectURL = "http://apps-swe642.vse.gmu.edu:8080/swe642/jsp/mzhang8/HW8ReviewOthers.jsp";
    response.sendRedirect(redirectURL);
  %>
 
</html>
    
    