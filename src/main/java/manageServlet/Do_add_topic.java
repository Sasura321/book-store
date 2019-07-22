package manageServlet;

import bean.Topic;
import manage.TopicManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * Servlet implementation class PageManage
 */
@WebServlet("/Do_add_topic")
public class Do_add_topic extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Do_add_topic() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("rawtypes")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out=response.getWriter();
		String tname = request.getParameter("tname");
	    TopicManager tm=new TopicManager();
	    Map topic=tm.getTopicByTname(tname);
	    if (topic!=null){
	    	
	    	String top="<script type=\"text/javascript\">\n"+"alert(\"当前主题已存在，请输入不同的主题！\");\n"+
	         "open(\"../bookpages/topic_add.jsp\",\"_self\");\n"+"</script>";
	    	out.println(top);
	    	
		}else{
		    Topic t=new Topic();
	        t.setTname(tname);
	        int r=tm.insertTopic(t);
	        if (r>0){
              
	        	String top="<script type=\"text/javascript\">\n"+"alert(\"当前主题创建成功，点击确认返回主题列表！\");\n"+
	       	         "open(\"../bookpages/topic_list.jsp\",\"_self\");\n"+"</script>";
	        	out.println(top);
		          }
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
