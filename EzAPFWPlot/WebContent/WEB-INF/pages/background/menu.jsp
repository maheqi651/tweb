<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*,com.founderinternational.rscenter.entity.*"
	    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Insert title here</title>
</head>
<body>
	<% 
	 
	   List<Menu> m=(List<Menu>)request.getAttribute("result");
	    for(Menu mm :m)
	    {
	    	%>
	<%=mm.getName() %><br />
	<% 
	    }
	 %>
	<h1>11211</h1>
</body>
</html>