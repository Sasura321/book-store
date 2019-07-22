<%@ page language="java" import="java.util.*,manageServlet.*,manage.*,bean.*" pageEncoding="utf-8"%>
<html>
 <%
	String loginUser = (String)session.getAttribute("uname");
	if(loginUser == null){
%>

<%		
	} 
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>图书管理系统后台</title>
<link href="../html/css/admin.css" rel="stylesheet" type="text/css" />
<div id="header">
  <div id="welcome">欢迎使用图书管理系统！</div>
  <div id="nav">
    <div id="logo"><img src="../html/Images/logo.PNG" alt="三味图书" /></div>
    <div ><img src="../html/Images/hbj12.PNG" alt="书籍是人类进步的阶梯" /></div>
  </div>
</div>
<div id="admin_bar">
    <div id="status">管理员：<a href="../Do_login" style="text-decoration:none;"> 登录  &#160;&#160;&#160;&#160;</a>
      <a href="../do_logout.jsp" style="text-decoration:none;">login out  &#160;&#160;&#160;&#160;</a>
        <a href="javascript:history.go(-1);" style="text-decoration:none;">返回上一页</a>
    </div>
  <div id="channel"> </div>
</div>
</head>
<body>
