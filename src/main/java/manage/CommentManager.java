package manage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import bean.Comment;

public class CommentManager {

	private Connection con = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;

    /**
     * 通过JNDI获取数据库连接
     */
	private void openConnection(){
		try {
				Context ctx = new InitialContext();
				DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/booksDS");
				con = ds.getConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			}
	}

    /**
     * 添加评论
     * @param list 评论集合
     * @return 结果集
     */
	public int addComment(@SuppressWarnings("rawtypes") List list){
		openConnection();
		String sql = "insert into commentinfo values(null,?,?,?,?,?)";
		int i = 0;
		try{			
				ps = con.prepareStatement(sql);
				ps.setObject(1,list.get(0));
				ps.setObject(2,list.get(1));
				ps.setObject(3,list.get(2));
				ps.setObject(4,list.get(3));
				ps.setObject(5,list.get(4));
				i = ps.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeResource();
		}
		return i;
	}

    /**
     * 通过图书号bid查找对应的评论
     * @param bid 图书号
     * @return 获取到的评论
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public List getCommentsByBid(String bid){
        openConnection();
        List list = new ArrayList();
        SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd HH:mm");
        String sql = "select * from commentinfo"
                    + " where cbid ="
                    + bid;
                    //+ " order by cdate desc";
        try {
            Statement st=con.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
                Map comment=new HashMap();
                comment.put("cid",String.valueOf(rs.getInt("cid")));
                comment.put("cbid",String.valueOf(rs.getInt("cbid")));
                comment.put("cauthor",rs.getString("cauthor"));
                comment.put("cip",rs.getString("cip"));
                comment.put("ccontent",rs.getString("ccontent"));
                comment.put("cdate",sdf.format(rs.getObject("cdate")));
                list.add(comment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            closeResource();
        }
        return list;
    }

    /**
     * 释放资源
     * @return 成功true / 失败false
     */
	private boolean closeResource(){
		try {
				if(rs != null)
					rs.close();
				if(ps != null)
					ps.close();
				if(con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		return true;
	}
}
