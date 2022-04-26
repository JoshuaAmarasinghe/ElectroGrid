<%@page import="model.feedback" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<% 
	    
	//Insert Feedback----------------------------------
	if (request.getParameter("userid") != null)
	 {
	 feedback feedbackObj = new feedback();
	 String stsMsg = feedbackObj.inserFeedback(request.getParameter("userid"),
	
	 request.getParameter("previousbillno"),
	 request.getParameter("amount"),
	 request.getParameter("payedamount"),
	 request.getParameter("balance"),
	 request.getParameter("newbillno"),
	 request.getParameter("newamount"),
	 request.getParameter("total"),
	 request.getParameter("comment"));
	 session.setAttribute("statusMsg", stsMsg);
	 }
	
	//Delete Feedback----------------------------------
	if (request.getParameter("id") != null)
	{
	feedback feedbackObj = new feedback();
	String stsMsg = feedbackObj.deleteFeedback(request.getParameter("id"));
	session.setAttribute("statusMsg", stsMsg);
	}   
	
	%>
	
	   
	<!DOCTYPE html>
	<html>
	<head>
	<meta charset="ISO-8859-1">
	<title>Feedback Management</title>
	</head>
	
	<body>
	
	<h1>Feedback Management</h1>
	
	
	<form method="post" action="feedback.jsp">
	 
	 USER ID: <input name="userid" type="text"><br><br> 
	 PREVIOUS BILL NO:   <input name="previousbillno" type="text"><br><br> 
	 AMOUNT:     <input name="amount" type="text"><br><br> 
	 PAYED AMOUNT:     <input name="payedamount" type="text"><br><br>
	 BALANCE:     <input name="balance" type="text"><br><br>
	 NEW BILL NO:     <input name="newbillno" type="text"><br><br>
	 NEW AMOUNT:     <input name="newamount" type="text"><br><br>
	 TOTAL:     <input name="total" type="text"><br><br>
	 COMMENT:     <input name="comment" type="text"><br><br>
	  
	 
	 <input name="btnSubmit" type="submit" value="Submit"><br><br>
	</form>
	
	<%
	 out.print(session.getAttribute("statusMsg"));
	%>
	
	<br>
	
	<%
	 feedback feedbackObj = new feedback();
	 out.print(feedbackObj.readFeedback());
	%>
	

</body>
</html>