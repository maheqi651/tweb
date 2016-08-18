<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%> 
    <%@ page import="java.util.*,com.easymap.ticket.model.*,com.easymap.ticket.tools.*,com.easymap.management.newapi.*,com.easymap.management.api.*,com.easymap.management.user.User,com.easymap.management.entity.*" %>
<%
    String username=request.getParameter("username"); 
    System.out.println("username:"+username);
    String pass=request.getParameter("password"); 
    /* UserManager um = new UserManager(Constants.EzManagerUrl);
    User user=um.findUserByUserIdAndPassWord(username,pass);
    FunctionManager fm=new FunctionManager(Constants.EzManagerUrl);
    List<Function> listf=fm.getFunctionByUserId(username);
    boolean flag=false;
    for(Function f:listf)
    {
    	if("可视化情报分析".equals(f.getName()))
    	{
    		flag=true;
    		break;
    	}
    } */
    
    
    UserManager um = new UserManager(Constants.EzManagerUrl);
    User user=um.findUserByUserIdAndPassWord(username,pass);
    FunctionManager fm=new FunctionManager(Constants.EzManagerUrl);
    List<Function> listf=fm.getFunctionByUserId(username);
    boolean flag=false;
    if(username!=null)
    { 
    	com.easymap.ticket.model.User users=new com.easymap.ticket.model.User();
    	users.setUsername(user.getId());
    	users.setOrgCode(user.getOrgId());
    	request.getSession().setAttribute("user",users);
    	response.sendRedirect("index.html");
    }else{
    	response.sendRedirect("index.html");
    }
%>

<html>
<head>
    
</head>
<body class="box">
 
</body>
</html>