package manageServlet;

import manage.BookManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Servlet implementation class Book_search
 */
@WebServlet("/Book_search")
public class Book_search extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Book_search() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("rawtypes")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out=response.getWriter();
		String bname = request.getParameter("bname");
		BookManager bm = new BookManager();
		List list=bm.getBookByBname(bname);
		Iterator it=list.iterator();
		while(it.hasNext()) 
		{
			Map book=(Map)it.next();
			String top=null;
			if(book.get("bid")!=null){
				top="<script type=\"text/javascript\">\n"
						+"open(\"html/book_read.jsp?bid="+book.get("bid")+"\",\"_self\");\n"+"</script>";
				out.println(top);
			}
			
		}
		if(!it.hasNext())
		{
			out.println("未查询到此书");
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
