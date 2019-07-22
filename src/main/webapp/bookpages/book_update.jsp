<%@ page language="java" import="java.util.*,java.sql.*,bean.*,manage.*" pageEncoding="utf-8"%>
<jsp:include page="top.jsp" />

<%
	String bid=request.getParameter("bid");
	BookManager bm = new BookManager();
	Map tempBook=bm.getBooksByBid(bid);
	List list = bm.getAllTopics();
%>

<link href="css/main.css" rel="stylesheet" type="text/css" />
<link href="css/admin.css" rel="stylesheet" type="text/css" />
<link href="css/read.css" rel="stylesheet" type="text/css" />

<script type="text/javascript">
    function check(){
		var btid = document.getElementById("btid");
		var bname = document.getElementById("bname");
		var bauthor = document.getElementById("bauthor");
		var bpublisher = document.getElementById("bpublisher");
		var bsummary = document.getElementById("bsummary");
		var bprice = document.getElementById("bauthor");
		var bcreatedate = document.getElementById("bcreatedate");

		if(btid.value == ""){
			alert("类型不能为空！！");
			btid.focus();
			return false;
		} else if(bname.value == "") {
			alert("书名不能为空！！");
			bname.focus();
			return false;
		} else if(bauthor.value == "") {
			alert("作者不能为空！！");
			bauthor.focus();
			return false;
		} else if(bpublisher.value == "") {
			alert("出版社不能为空！！");
			bpublisher.focus();
			return false;
		} else if(bsummary.value == "") {
			alert("摘要不能为空！！");
			bsummary.focus();
			return false;
		} else if(bprice.value == "") {
			alert("价格不能为空！！");
			bprice.focus();
			return false;
		} else if(bcreatedate.value == "") {
			alert("发行时间不能为空！！");
			bcreatedate.focus();
			return false;
		}
		return true;
	}
</script>

<div id="main">
  <div id="opt_area">
    <h1 id="opt_type"> 修改书目：</h1>
    <form action="../Do_update_book" method="post" onsubmit="return check()">
      <p>
        <label> 主题 &nbsp; &nbsp;</label>
        <select name="btid">

        <%
		Iterator iterator = list.iterator();
		Map tempTopic = null;
		while(iterator.hasNext()){
			tempTopic = (Map)iterator.next();
        %>

      	<option value='<%=tempTopic.get("tid")%>'><%=tempTopic.get("tname") %></option>
      	<%}%>          
        </select>
      </p>

      <p>
        <label> 书名  &nbsp; &nbsp;</label>
        <input name="bname" type="text" class="opt_input" />
      </p>
      <p>
        <label> 作者  &nbsp; &nbsp; </label>
        <input name="bauthor" type="text" class="opt_input" />
      </p>
      <p>
        <label> 出版社 &nbsp; </label>
        <input name="bpublisher" type="text" class="opt_input" />
      </p>
      <p>
        <label> 价格  &nbsp; &nbsp;</label>
        <input name="bprice" cols="70" rows="10"></input>
      </p>
      <p>
        <label>发行时间 </label>
        <input name="bcreatedate" tcols="70" rows="10"></input>
      </p>
      <p>
        <label> 摘要 &nbsp; &nbsp; </label><br/>
        <textarea name="bsummary" cols="70" rows="10" ></textarea>
      </p>
      
      <input type="submit" value="提交" class="opt_sub" />
      <input type="reset" value="重置" class="opt_sub" />
    </form>
  </div>
</div>
<jsp:include page="bottom.html" />

