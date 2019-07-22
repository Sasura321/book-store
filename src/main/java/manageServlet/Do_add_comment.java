package manageServlet;

import bean.Comment;
import manage.CommentManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet implementation class CommentManage
 */
@WebServlet("/Do_add_comment")
public class Do_add_comment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Do_add_comment() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings({ "unused", "rawtypes", "unchecked" })
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out=response.getWriter();
		CommentManager bm = new CommentManager();
		String cid=request.getParameter("cid");
		request.setAttribute("cid", cid);
		String cbid = request.getParameter("cbid");
		String ccontent = request.getParameter("ccontent");
		String cdate = request.getParameter("cdate");
		String cauthor = request.getParameter("cauthor");
		String cip = request.getParameter("cip");
		String bcreatedate = request.getParameter("bcreatedate");
		Comment comment=new Comment(cbid,ccontent,cdate,cip,cauthor);
		List list=new ArrayList();
		list.add(comment);
		if(bm.addComment(list)>0)
            if(bm.addComment(list)>0)
            {
                String top="<script type=\"text/javascript\">\n"+"alert(\"已经成功添加评论，点击确认返回原来页面\");\n"+
                        "open(\"../html/book_read.jsp?nid=<%=cnid%>\",\"_self\");\n"+"</script>";

                out.println(top);
            }else{

                String top="<script type=\"text/javascript\">\n"+"alert(\"未添加成功，点击确认返回原来页面\");\n"+
                        "open(\"../html/book_read.jsp?nid=<%=cnid%>\",\"_self\");\n"+"</script>";
                out.println(top);
            }
			
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
