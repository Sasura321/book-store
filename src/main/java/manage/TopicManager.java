package manage;

import java.sql.*;
import java.util.*;

import javax.naming.*;
import javax.sql.DataSource;

import bean.Topic;

public class TopicManager {
	
	private Connection con=null;
	private PreparedStatement ps=null;
	private ResultSet rs=null;

    /**
     * 通过JNDI获取数据库连接
     */
	public void openConnection()
	{
		try {
			Context ct=new InitialContext();
			DataSource ds=(DataSource)ct.lookup("java:comp/env/jdbc/booksDS");
			con=ds.getConnection();
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}
	}

    /**
     * 获取所有主题
     * @return 获取到的所有主题
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public List getAllTopics() 
    {
    	List list=new ArrayList();
    	String sql="select * from topic";
    	openConnection();
    	try {
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()) 
			{
				Topic topic=new Topic();
				topic.setTid(String.valueOf(rs.getObject("tid")));
				topic.setTname(rs.getString("tname"));
				list.add(topic);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
    		closeResource();
    	}
    	return list;
    }

    /**
     * 根据tid获取某一个主题
     * @param tid 主题号
     * @return 获取到的主题
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public Map getTopicByTid(String tid) 
    {
    	Map topic=null;
    	openConnection();
    	String sql="select * from topic where tid=?";
    	try {
			ps=con.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(tid));
			rs=ps.executeQuery();
			while(rs.next()) 
			{
				topic=new HashMap();
				topic.put("tid", String.valueOf(rs.getObject("tid")));
				topic.put("tname",rs.getString("tname"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return topic;
    }

    /**
     * 根据tname获取某一个主题
     * @param tname 书名
     * @return 获取到的主题信息
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public Map getTopicByTname(String tname) 
    {
    	Map topic=null;
    	openConnection();
    	String sql="select * from topic where tname=?";
    	try {
			ps=con.prepareStatement(sql);
			ps.setString(1, tname);
			rs=ps.executeQuery();
			while(rs.next()) 
			{
				topic=new HashMap();
				topic.put("tid", String.valueOf(rs.getObject("tid")));
				topic.put("tname", rs.getString("tname"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
    		closeResource();
    	}
    	return topic;
    }

    /**
     * 添加一个主题
     * @param topic 主题对象
     * @return 结果集
     */
    public int insertTopic(Topic topic){
    	int res=0;
    	openConnection();
    	String sql = "insert into topic values (null,?)";
    	try {
    		ps = con.prepareStatement(sql);
    		ps.setString(1, topic.getTname());
    		res= ps.executeUpdate();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} finally{
    		closeResource();
    	}				
    	return res;
    }

    /**
     * 修改一个主题
     * @param tid 主题号
     * @param topic 主题对象
     * @return 结果集
     */
    public int updateTopicByTid(String tid, Topic topic){
    	int res=0;
    	openConnection();
    	String sql = "update topic set tname=? where tid=?";
    	try {
    		ps = con.prepareStatement(sql);
    		ps.setString(1,topic.getTname());
    		ps.setInt(2,Integer.parseInt(tid));
    		res= ps.executeUpdate();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} finally{
    		closeResource();
    	}				
    	return res;
    }

    /**
     * 根据tid删除一个主题
     * @param tid 主题号
     * @return 结果集
     */
    public int deleteTopicByTid(String tid){
    	int res=0;
    	openConnection();
    	String sql = "delete from topic where tid=?";
    	try{
    		ps=con.prepareStatement(sql);
    		ps.setInt(1,Integer.parseInt(tid));
    		res= ps.executeUpdate();
    	}catch (SQLException e) {
    		e.printStackTrace();
    	}finally{
    		closeResource();
    	}
    	return res;
    }

    /**
     * 释放资源
     * @return
     */
    private boolean closeResource() 
	{
		try 
		{
			if(rs!=null)
			    rs.close();
			if(ps!=null)
				ps.close();
			if(con!=null)
			    con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
