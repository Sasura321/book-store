<%@ page language="java" import="java.util.*,bean.*,manage.*" pageEncoding="utf-8"%>
<jsp:include page="index_top.jsp" />

<script type="text/javascript">
	function checkComment(){
		var cauthor = document.getElementById("cauthor");
		var content = document.getElementById("ccontent");
		if(cauthor.value == ""){
			alert("用户名不能为空！！");
			return false;
		}else if(content.value == ""){
			alert("评论内容不能为空！！");
			return false;
		}
		return true;
	}
</script>

<link href="css/read.css" rel="stylesheet" type="text/css" />
<%
	BookManager bm = new BookManager();
   String bid = request.getParameter("bid");
	Map map = bm.getBooksByBid(bid);
	String cip = request.getRemoteAddr();
	CommentManager cmr = new CommentManager();
	List comments = cmr.getCommentsByBid(bid);
%>

<div id="container">
  <jsp:include page="index_sidebar.jsp"></jsp:include>
  <div class="main">
    <div class="class_type"> </div>
    <div class="content">
      <ul class="classlist">
        <table width="80%" align="center">
          <tr width="100%">
            <td colspan="2" align="center"><%=map.get("btid") %></td>
          </tr>
          <tr>
            <td colspan="2"><hr />
            </td>
          </tr>
          <tr>
            <td colspan="2" align="center">作者：<%=map.get("bauthor") %>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              类型：<a href="index.jsp?tid=<%=map.get("btid")%>"><%=map.get("tname") %></a>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
             发布时间：<%=map.get("bcreatedate") %>
             </td>
          </tr>
          <tr>
            <td colspan="2" align="center"><strong>摘要：<%=map.get("bsummary") %></strong></td>
          </tr>
          <tr>
            <td colspan="2" align="center"></td>
          </tr>
          <tr>
            <td colspan="2"><%=map.get("bcontent") %></td>
          </tr>
          <tr>
            <td colspan="2"><hr />
            </td>
          </tr>
        </table>
      </ul>
      <ul class="classlist">
        <table width="80%" align="center">
        <% 
        	if(comments.size() <= 0){
        %>
	          <td colspan="6"> 暂无评论！ </td>
	          <tr>
	            <td colspan="6"><hr />
	            </td>
	          </tr>
         <%
         	 }else{
         	 Iterator iterator = comments.iterator();
         	 Comment comment = null;
         	 while(iterator.hasNext()){
         	 comment = (Comment)iterator.next();
         %>
         	<tr>
            <td> 留言人： </td>
            <td><%=comment.getCauthor() %></td>
            <td> IP： </td>
            <td><%=comment.getCip() %></td>
            <td> 留言时间： </td>
            <td><%=comment.getCdate() %></td>
          </tr>
          <tr>
            <td colspan="6"><%=comment.getCcontent() %></td>
          </tr>
          <tr>
            <td colspan="6"><hr />
            </td>
          </tr>
         <% 
         		}
         	}
         %>
        </table>
      </ul>
      <ul class="classlist">
        <form action="util/do_add_comment.jsp?nid=<%=map.get("nid")%>" method="post" onSubmit="return checkComment()">
          <table width="80%" align="center">
            <tr>
              <td> 评 论 </td>
            </tr>
            <tr>
              <td> 用户名： </td>
              <td><input id="cauthor" name="cauthor" value="这家伙很懒什么也没留下"/>
                IP：
                <input name="cip" value="<%=cip %>" readonly="readonly"/>
              </td>
            </tr>
            <tr>
              <td colspan="2"><textarea name="ccontent" cols="70" rows="10"></textarea>
              </td>
            </tr>
            <td><input name="submit" value="发  表" type="submit"/>
              <a href="index.jsp">返回</a>
              </td>
          </table>
        </form>
      </ul>
    </div>
  </div>
</div>
<jsp:include page="index_bottom.html" />
