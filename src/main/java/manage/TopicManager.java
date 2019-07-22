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
     * ͨ��JNDI��ȡ���ݿ�����
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
     * ��ȡ��������
     * @return ��ȡ������������
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
     * ����tid��ȡĳһ������
     * @param tid �����
     * @return ��ȡ��������
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
     * ����tname��ȡĳһ������
     * @param tname ����
     * @return ��ȡ����������Ϣ
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
     * ���һ������
     * @param topic �������
     * @return �����
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
     * �޸�һ������
     * @param tid �����
     * @param topic �������
     * @return �����
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
     * ����tidɾ��һ������
     * @param tid �����
     * @return �����
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
     * �ͷ���Դ
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
