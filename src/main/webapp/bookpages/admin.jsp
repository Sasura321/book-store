<%@page language="java" import="java.util.* ,bean.*,manage.*" pageEncoding="utf-8"%>
<%@page import="java.util.Date"%>
<jsp:include page="top.jsp" />

<%
    response.setCharacterEncoding("utf-8");
    BookManager bm = new BookManager();
    int page_book=15;
    String page_no_str=request.getParameter("page_no");
    int page_no=1;
    if(page_no_str!=null)
    {
        page_no=Integer.valueOf(page_no_str);
    }
    List topic_list=bm.getAllTopics();
%>

<head>
    <link href="css/main.css" rel="stylesheet" type="text/css" />
    <link href="css/admin.css" rel="stylesheet" type="text/css" />
</head>

<div id="main">
    <jsp:include page="left.html" />
    <div id="opt_area">
        <script language="javascript">
            function clickdel(){
                return confirm("删除请点击确认");
            }
        </script>

        <ul class="classlist"> <%
            Iterator iterator = topic_list.iterator();
            Map tempBook = null;
            String tempBtname = "";

            while(iterator.hasNext()){
                tempBook = (Map)iterator.next();
                String tid=(String)tempBook.get("tid");%>

                <li class='space'><strong><%=tempBook.get("tname") %></strong></li>
                <% List book_list = bm.getAllbooksByPageAndTid(page_book, page_no, tid);
                Iterator it=book_list.iterator();
                Map temp=new HashMap();

                while(it.hasNext()){
                    temp=(Map)it.next();%>
                    <li>
                        <%=temp.get("bname")%>
                        <span>作者：<%=temp.get("bauthor") %> &#160;&#160;&#160;&#160;
                        <a href='../Do_update_book?bid=<%= temp.get("bid") %>&btid=<%=temp.get("btid") %>
                        &bname=<%=temp.get("bname") %>&bauthor=<%=temp.get("bauthor") %>
                        &bpublisher=<%=temp.get("bpublisher") %>&bsummary=<%=temp.get("bsummary") %>
                        &bprice=<%=temp.get("bprice") %>&bcreatedate=<%=temp.get("bcreatedate") %>'>修改</a>
                        &#160;&#160;&#160;&#160;
                        <a href='../Do_delete_book?bid=<%=temp.get("bid")%>' onclick='return clickdel()'>删除</a>
                        </span>
                    </li>

                <% }
            } %>

        </ul>
    </div>
</div>
<jsp:include page="bottom.html" />
