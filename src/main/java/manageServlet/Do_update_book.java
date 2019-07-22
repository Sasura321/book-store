package manageServlet;

import bean.Book;
import manage.BookManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class Do_update_book
 */
@WebServlet("/Do_update_book")
public class Do_update_book extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Do_update_book() {
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
		int i=0;
		BookManager bm = new BookManager();
		PrintWriter out=response.getWriter();
		String bid=request.getParameter("bid");
		String btid = request.getParameter("btid");
		String bname = request.getParameter("bname");
		String bauthor = request.getParameter("bauthor");
		String bpublisher = request.getParameter("bpublisher");
		String bsummary = request.getParameter("bsummary");
		String bp = request.getParameter("bprice");
		int bprice=Integer.parseInt(bp);
		String bcreatedate = request.getParameter("bcreatedate");
        Book book=new Book(btid,bname,bpublisher,bauthor,bprice,bsummary,bcreatedate);
        i=bm.updateBooksByBid(bid, book);
		String top=null;

        if (i > 0) {
            top="<script type=\"text/javascript\">\n"+"alert(\"已经成功修改书目，点击确认返回图书列表\");\n"
                    +"open(\"../bookpages/admin.jsp\",\"_self\");\n"+"</script>";
            out.println(top);

        } else {
            top="<script type=\"text/javascript\">\n"+"alert(\"修改书目失败！请联系管理员查找原因！\");\n"
                    +"open(\"../bookpages/admin.jsp\",\"_self\");\n"+"</script>";
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
