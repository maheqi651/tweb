<%@page import="com.easymap.management.newapi.FunctionManager"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%> 
<%@ page import="java.util.*,com.easymap.ticket.model.*,com.easymap.ticket.tools.*,com.easymap.management.newapi.*,com.easymap.management.api.*,com.easymap.management.user.User,com.easymap.management.entity.*" %>
<%
    String username=request.getParameter("username"); 
    String pass=request.getParameter("password");
    System.out.println("username:"+username);
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
    	request.getSession().removeAttribute("user");
    	response.sendRedirect("index.html");
    }
%>

<html>
<head>
    <!-- <base href="public/" /> -->
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
    <meta http-equiv="X-UA-Compatible" content="IE=9" />
    <title>广州公安数据标准管理系统</title>
 
	<!-- bootstrap样式  -->
    <link href="css/bootstrap-theme.css" rel="stylesheet">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="css/font/font-awesome-4.3.0/css/font-awesome.css"/>
    <script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
	<style type="text/css">
        .box {
            background-image: url("image/bg.png");
            margin: 0 auto;
            position: relative;
            width: 100%;
            height: 100%;
        }

        .login-box {
            width: 100%;
            max-width:600px;
           /*  height: 400px; */
            position: absolute;
            top: 50%;

			margin-top: 120px;
            /*设置负值，为要定位子盒子的一半高度*/
            -moz-box-shadow:2px 1px 15px #333333;
            -webkit-box-shadow:2px 1px 15px #333333;
            box-shadow:2px 1px 15px #333333;

        }
        @media screen and (min-width:500px){
            .login-box {
                /*left: 50%;*/
                left:28%;
                left: 4%\0; /* IE8、IE9共用*/
                /*设置负值，为要定位子盒子的一半宽度*/
                /*margin-left: -250px;*/
            }
        }

        .form {
            width: 100%;
            max-width:500px;
            height: 275px;
            margin: 25px auto 0px auto;
            padding-top: 25px;
        }
        .login-content {
            height: 280px;
            width: 100%;
            max-width:600px;
            background-color: rgba(255, 250, 2550, 0.6);

            background-color: #adc4db;
            background-color: #adc4db\0; /* IE8、IE9共用*/
            background-color: #adc4db\9; /* IE6、IE7、IE8、IE9共用*/
            float: left;

        }

        .input-group {
            margin: 0px 0px 30px 0px !important;
        }
        .form-control,
        .input-group {
            height: 40px;
        }

        .form-group {
            margin-bottom: 0px !important;
        }
        .login-title {
            padding: 20px 10px;
            background-color: rgba(0, 0, 0, .6);

            background-color: black;
            background-color:  black\0; /* IE8、IE9共用*/
            background-color: black\9;       /* IE6、IE7、IE8、IE9共用*/
        }
        .login-title h1 {
            margin-top: 10px !important;
        }
        .login-title small {
            color: #fff;
        }

        .link p {
            line-height: 20px;
            margin-top: 30px;
        }
        .btn-sm {
            padding: 8px 24px !important;
            font-size: 16px !important;
        }
    </style>
</head>
<body class="box">
<div >
    <div class="login-box" style="margin-top: -200px;">
        <div class="login-title  ">
           <img src="image/logo_L1.png">
    </div>
        <div class="login-content">
            <div class="form">
                 <form id="login.jsp"> 
                  
                    <div class="form-group">
                        <div class="col-xs-12" style="margin-top:-30px">
                            <div class="input-group">
                                <span class="input-group-addon"><span class="fa fa-user fa-2x"></span></span>
                                <input type="text" id="username" name="username" style="height:45px;"   class="form-control" placeholder="用户名">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-12  ">
                            <div class="input-group">
                                <span class="input-group-addon"><span class="fa fa-lock fa-2x"></span></span>
                                <input type="password" name="password" id="password" style="height:45px;"   class="form-control" placeholder="密码">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-2 col-xs-offset-3">
                            <button id="loginsubmit" type="submit" class="btn btn-sm btn-primary"  > 登录</button>
                            <!-- <img src="images/icon-login.png" style="cursor: pointer;"> -->
                        </div>
                        <div class="col-xs-2 col-xs-offset-1">
                            <button class="btn btn-sm btn-primary">取消</button>
                            <!-- <img src="images/icon-cancel.png" style="cursor: pointer;"> -->
                        </div>
                        
                    </div>
                 </form>  
            </div>
        </div>
    </div>
</div>
</body>
</html>