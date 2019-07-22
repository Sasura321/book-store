<%@ page language="java" import="java.util.*,java.sql.*,bean.*,manage.*" pageEncoding="utf-8"%>
<jsp:include page="top.jsp" />

<%
		TopicManager tm=new TopicManager();
        List list=tm.getAllTopics();
%>

<div id="main">
  <jsp:include page="left.html" />
  <div id="opt_area">
    <ul class="classlist">
      <%
		Iterator iterator = list.iterator();
		Topic tempTopic = null;
		while(iterator.hasNext()){
			tempTopic = (Topic)iterator.next();
      %>
      <li> &#160;&#160;&#160;&#160; <%=tempTopic.getTname() %> &#160;&#160;&#160;&#160;
          <a href='topic_update?tid=<%=tempTopic.getTid()%>'>修改</a> &#160;&#160;&#160;&#160;
          <a href='topic_delete?tid=<%=tempTopic.getTid()%>'>删除</a> </li>
      <% } %>
    </ul>
  </div>
</div>
<jsp:include page="bottom.html" />
