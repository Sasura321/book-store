<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>三味书屋</title>
<link href="css/main.css" rel="stylesheet" type="text/css" />
<script language="javascript">
	function check(){
		var login_username = document.getElementById("uname");
		var login_password = document.getElementById("upwd");
		if(login_username.value == ""){
			alert("用户名不能为空！请重新填入！");
			login_username.focus();	
			return false;
		}else if(login_password.value == ""){
			alert("密码不能为空！请重新填入！");
			login_password.focus();
			return false;
		}
		return true;
	}
	
	function focusOnLogin(){
		var login_username = document.getElementById("uname");
		login_username.focus();	
	}
</script>
</head>

<body onload="focusOnLogin()">
<div id="header">
  <div id="top_login">
 <%
	String user = (String)session.getAttribute("uname");
	if(user == null){
%>
    <form action="../Do_login" method="post" onsubmit="return check()">
      <input type="text" name="uname" value="" class="login_input" />
      <label> 密&#160;&#160;码 </label>
      <input type="password" name="upwd" value="" class="login_input" />
      <input type="submit" class="login_sub" value="登录" />
      <label id="error"> </label>
      <img src="Images/friend_logo.gif" alt="Google" id="friend_logo" />
    </form>
<% 
	}else{
%>
	欢迎您：<%=user %> &nbsp; &nbsp; &nbsp;<a href="bookpages/admin.jsp">登录控制台</a> &nbsp;
      <a href="do_logout.jsp">退出</a>
<%	}%>
      <%--div:块--%>
  </div>
  <div id="nav">
    <div id="logo"> <img src="Images/logo.PNG" alt="图书中心" /> </div><br/>
    <div > <img src="Images/hbj12.PNG" alt="hbj12" /> </div>
    <!--mainnav end-->
  </div>
  
</div>
</body>
</html>