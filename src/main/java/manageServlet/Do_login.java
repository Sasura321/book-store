package manageServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

/**
 * Servlet implementation class Do_login
 */
@WebServlet("/Do_login")
public class Do_login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Do_login() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		try{
			
			response.setContentType("text/html,charset=utf-8");
			PrintWriter out=response.getWriter();
			String uname = request.getParameter("uname");
			String password = request.getParameter("upwd");
			Class.forName("com.mysql.jdbc.Driver");
			String url="jdbc:mysql://localhost:3306/test1";
			Connection con=DriverManager.getConnection(url,"root","123456");
			String sql = "select * from userinfo where uname=? and upwd=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,uname);
			ps.setString(2,password);
			ResultSet rs = ps.executeQuery();
			if(!rs.next()){
			rs.close();
			ps.close();
			con.close();
		    String top="<script type=\"text/javascript\">\n"+"alert(\"用户名密码错误，请重新登录\");\n"
			+"open(\"html/index.jsp\",\"_self\");\n"+"</script>";
		    out.println(top);
			}else{
				rs.close();
				ps.close();
				con.close();
				request.setAttribute("uname",uname);
				response.sendRedirect("bookpages/admin.jsp");
			}
		} catch(SQLException | ClassNotFoundException e){
		    e.printStackTrace();
        }
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		doGet(request, response);
	}

}
