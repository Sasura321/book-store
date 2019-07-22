<%@ page language="java" import="manage.*,java.sql.*,java.util.*" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<jsp:include page="index_top.jsp"></jsp:include>

<%
    request.setCharacterEncoding("utf-8");
    response.setCharacterEncoding("utf-8");
    BookManager bm = new BookManager();
    int total_book = bm.countBooks();  //书总数目
    int page_book = 15;                //每一页显示书本数目
    int total_page = total_book/page_book + 1;            //总页数
    String page_no_str = request.getParameter("page_no"); //当前第几页
    int page_no = 1;                  //默认当前显示第一页
    if(page_no_str != null)
        page_no = Integer.valueOf(page_no_str);
    String tid = request.getParameter("tid");
    List book_list;
    if(tid == null)
        //显示所有图书中的第一页内容
        book_list = bm.getAllbooksByPage(page_book, page_no);
    else
        //显示选中主题下的第一页内容
        book_list = bm.getAllbooksByPageAndTid(page_book, page_no, tid);
    List topic_list = bm.getAllTopics();
%>

<head>
    <link href="css/main.css" rel="stylesheet" type="text/css" />
    <link href="css/read.css" rel="stylesheet" type="text/css" />
</head>

<div id="container">
    <jsp:include page="index_sidebar.jsp" />
    <div class="main">
        <div class="class_type">
            <img src="Images/tushuguanli.gif" alt="图书管理" />
        </div>
        <div class="content1">
            <ul class="class_date">
                <li id='class_month'>
                <%
			Iterator iterator = topic_list.iterator();
			Map tempTopic = null;
			int i = 0;
			while(iterator.hasNext()){
				tempTopic = (Map)iterator.next();
				if(i != 0 && i%11 == 0){
            %>
                <li id='class_month'>
                    <%
                        }
                    %>
                    <a href="index.jsp?tid=<%=tempTopic.get("tid") %>">
                        <b><%=tempTopic.get("tname") %> </b></a>
                    <%
                        if(i != 0 && i%11 == 0){
                    %>
                </li>
                <%
                    }
                    i++;
                %>

            <% } %>

            </ul>
            <ul class="classlist">
                <%
                    iterator = book_list.iterator();
                    Map tempBook = null;
                    while(iterator.hasNext()){
                        tempBook = (Map)iterator.next();
                %>
                <li><a href="book_read.jsp?bid=<%=tempBook.get("bid") %>">
                    <%=tempBook.get("bname") %></a>
                    <span><%=tempBook.get("bcreatedate") %></span> </li>
                <%}%>
                <%
                    if(tid == null){
                        if(page_no == 1){
                %>
                <p align="right"> 当前页数:[<%=page_no %>/<%=total_page %>]&nbsp;
                    <a href="index.jsp?page_no=2">下一页</a>
                    <a href="index.jsp?page_no=<%=total_page %>">末页</a> </p>
                <%
                }else if(page_no == total_page){
                %>
                <p align="right"> 当前页数:[<%=total_page %>/<%=total_page %>]&nbsp;
                    <a href="index.jsp?page_no=1">首页</a>
                    <a href="index.jsp?page_no=<%=page_no-1 %>">上一页</a></p>
                <%
                }else{
                %>
                <p align="right"> 当前页数:[<%=page_no %>/<%=total_page %>]&nbsp;
                    <a href="index.jsp?page_no=1">首页</a>
                    <a href="index.jsp?page_no=<%=page_no-1 %>">上一页</a>
                    <a href="index.jsp?page_no=<%=page_no+1 %>">下一页</a>
                    <a href="index.jsp?page_no=<%=total_page %>">末页</a></p>
                <%
                        }
                    }
                %>
            </ul>
        </div>
        <jsp:include page="index_rightbar.html" />
    </div>
</div>
<jsp:include page="index_bottom.html" />
