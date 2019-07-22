package manageServlet;

import manage.BookManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class TopicManage
 */
@WebServlet("/Do_delete_book")
public class Do_delete_book extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Do_delete_book() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out=response.getWriter();
		String bid=request.getParameter("bid");
		BookManager bm=new BookManager();
		int res=bm.deleteBookByBid(bid);
        if (res>0){
            String top="<script type=\"text/javascript\">\n"+"alert(\"已经成功删除一个书目，点击确认返回新闻列表\");\n"+
                    "open(\"bookpages/admin.jsp\",\"_self\");\n"+"</script>";
            out.println(top);
        }else{
            String top="<script type=\"text/javascript\">\n"+"alert(\"删除书目失败！请联系管理员查找原因！\");\n"+
                    "open(\"html/index.jsp\",\"_self\");\n"+"</script>";
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
