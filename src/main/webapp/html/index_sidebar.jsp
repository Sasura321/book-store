<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"  import="java.util.*,bean.*,manage.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8 " >
    <title>test</title>
</head>

<body>
<jsp:include page="../bookpages/book_search.jsp"/>
<div class="sidebar">
    <h1> <img src="Images/title_1.gif" alt="热门推荐" /> </h1>
    <div class="side_list">
        <ul>
            <%
                BookManager bm = new BookManager();
                List list = bm.getBookByTid("1");
                Iterator iterator = list.iterator();
                Map tempNew = null;

                int i = 0;
                while(i<3 && iterator.hasNext()){
                    tempNew = (Map)iterator.next();
                    i++;
            %>
            <li> <a href='book_read.jsp?bid=<%=tempNew.get("bid") %>'>
                <b> <%=tempNew.get("bname") %> </b></a> </li>
            <%}%>
        </ul>
    </div>
    <h1> <img src="Images/title_2.gif" alt="国内图书" /> </h1>
    <div class="side_list">
        <ul>
            <%
                list = bm.getBookByTid("2");
                iterator = list.iterator();
                i = 0;
                while(i < 3 && iterator.hasNext()){
                    tempNew = (Map)iterator.next();
                    i++;
            %>
            <li> <a href='book_read.jsp?bid=<%=tempNew.get("bid") %>'>
                <b> <%=tempNew.get("bname") %> </b></a> </li>
            <%}%>
        </ul>
    </div>
    <h1> <img src="Images/title_3.gif" alt="国外图书" /> </h1>
    <div class="side_list">
        <ul>
            <%
                list = bm.getBookByTid("3");
                iterator = list.iterator();
                i = 0;
                while(i < 3 && iterator.hasNext()){
                    tempNew = (Map)iterator.next();
                    i++;
            %>
            <li> <a href='book_read.jsp?bid=<%=tempNew.get("bid") %>'>
                <b> <%=tempNew.get("bname") %> </b></a> </li>
            <%}%>
        </ul>
    </div>
</div>
</body>
</html>
