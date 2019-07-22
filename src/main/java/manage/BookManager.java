package manage;

import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import bean.Book;

public class BookManager {
	
	private Connection con=null;
	private PreparedStatement ps=null;
	private ResultSet rs=null;
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
     * 获取所有的图书信息
     * @return 图书信息集合
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ArrayList getAllBooks() 
	{
		ArrayList list=new ArrayList();
		String sql="select * from books,topic"+ " where books.btid = topic.tid"
				+ " order by btid,bcreatedate desc";
		openConnection();
		try {
			Statement st=con.createStatement();
			rs=st.executeQuery(sql);
			while(rs.next()) 
			{
				Map book=new HashMap();
				book.put("tname",rs.getString("tname"));
				book.put("bid",rs.getString("bid"));
				book.put("btid",rs.getString("btid"));
				book.put("bname",rs.getString("bname"));
				book.put("bpublisher",rs.getString("bpublisher"));
				book.put("bauthor",rs.getString("bauthor"));
				book.put("bprice",rs.getFloat("bprice"));
				book.put("bsummary",rs.getString("bsummary"));
				book.put("bcreatedate",rs.getString("bcreatedate"));
				list.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

    /**
     * 获取所有的图书信息主题
     * @param page_info_no 当前页面信息
     * @param page_no 总页面序号
     * @return 所有主题集合
     */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List getAllbooksByPage(int page_info_no,int page_no) 
	{
		List list=new ArrayList();
		openConnection();
		String sql="select * from books,topic "+"where books.btid=topic.tid "+
		" order by bcreatedate desc limit "+
		(page_no-1)*page_info_no+","+page_info_no;
		try 
		{
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()) 
			{
				Map books=new HashMap();
				books.put("bid", String.valueOf(rs.getObject("bid")));
				books.put("btid", String.valueOf(rs.getObject("btid")));
				books.put("bname", rs.getString("bname"));
				books.put("tname", rs.getString("tname"));
				books.put("bcreatedate", rs.getString("bcreatedate"));
				books.put("bauthor", rs.getString("bauthor"));
				books.put("bprice", rs.getFloat("bprice"));
				books.put("bpublisher", rs.getString("bpublisher"));
				books.put("bsummary", rs.getString("bsummary"));			
				list.add(books);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeResource();
		}
		
		return list;
	}

    /**
     * 通过页面序号获取图书
     * @param page_info_no 当前页面信息
     * @param page_no 总页面序号
     * @param tid 主题号
     * @return 所获取的全部信息
     */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List getAllbooksByPageAndTid(int page_info_no,int page_no,String tid) {
		List list=new ArrayList();
		String sql="select * from books,topic where books.btid=topic.tid "
				+" and topic.tid="+tid+" order by bcreatedate desc limit "+
				(page_no-1)*page_info_no+","+page_info_no;
		openConnection();
		try{			
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();	
			while(rs.next()){
				Map book = new HashMap();
				book.put("tid", String.valueOf(rs.getObject("tid")));
				book.put("bid", String.valueOf(rs.getObject("bid")));
				book.put("btid", String.valueOf(rs.getObject("btid")));
				book.put("bname", rs.getString("bname"));
				book.put("bauthor", rs.getString("bauthor"));
				book.put("bcreatedate", rs.getString("bcreatedate"));
				book.put("bpublisher", rs.getString("bpublisher"));
				book.put("bprice", rs.getFloat("bprice"));			
				book.put("bsummary", rs.getString("bsummary"));				
				list.add(book);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeResource();
		}
		return list;
	}

    /**
     * 统计图书总数
     * @return 图书总数
     */
    public int countBooks()
    {
    	int count=0;
    	String sql="select count(*) from books";
    	openConnection();
    	try{			
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();	
			while(rs.next()){
				count = rs.getInt(1);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeResource();
		}
		return count;
    }

    /**
     * 通过书号获取对应图书信息
     * @param bid 图书号
     * @return 获取到的所有图书
     */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map getBooksByBid(String bid) 
	{
		List list=new ArrayList();
		Map books=null;
		openConnection();
		String sql="select * from books,topic where books.btid=topic.tid "
				+"and bid="+bid;
		try{			
			Statement st=con.createStatement();
			rs = st.executeQuery(sql)	;	
			while(rs.next()){
				books = new HashMap();
				books.put("bid", String.valueOf(rs.getObject("bid")));
				books.put("btid", String.valueOf(rs.getObject("btid")));
				books.put("bname", rs.getString("bname"));
				books.put("tname", rs.getString("tname"));
				books.put("bcreatedate", rs.getString("bcreatedate"));
				books.put("bauthor", rs.getString("bauthor"));
				books.put("bprice", rs.getString("bprice"));
				books.put("bpublisher", rs.getString("bpublisher"));
				books.put("bsummary", rs.getString("bsummary"));				
				list.add(books);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeResource();
		}
		return books;
		
	}

    /**
     * 通过书名获取对应的图书信息
     * @param bname 书名
     * @return 获取到的所有图书
     */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List getBookByBname(String bname) 
	{
		List list=new ArrayList();
		Map books=null;
		openConnection();
		String sql="select * from books where bname=?";
	
		try{			
			ps=con.prepareStatement(sql);
			ps.setString(1, bname);
			rs = ps.executeQuery();
			while(rs.next()){
				books = new HashMap();
				books.put("bid", rs.getInt("bid"));
				books.put("bname", rs.getString("bname"));	
				list.add(books);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeResource();
		}
		return list;
		
	}

    /**
     * 通过主题号获取对应的图书
     * @param tid 主题号
     * @return 获取到的所有图书
     */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List getBookByTid(String tid) 
	{
		List list=new ArrayList();
		openConnection();
		String sql="select * from books,topic "
				+"where books.btid=topic.tid and topic.tid="+tid
				+" order by bcreatedate desc";
		try{			
			ps= con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				Map books = new HashMap();
				books.put("bid", String.valueOf(rs.getObject("bid")));
				books.put("btid", String.valueOf(rs.getObject("btid")));
				books.put("bname", rs.getString("bname"));
				books.put("tname", rs.getString("tname"));
				books.put("bcreatedate", rs.getString("bcreatedate"));
				books.put("bauthor", rs.getString("bauthor"));
				books.put("bprice", rs.getString("bprice"));
				books.put("bpublisher", rs.getString("bpublisher"));
				books.put("bsummary", rs.getString("bsummary"));					
				list.add(books);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeResource();
		}
		return list;
	}

    /**
     * 获取所有的主题
     * @return 获取到的所有主题
     */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List getAllTopics() 
	{
		List list=new ArrayList();
		String sql="select * from topic ";
		openConnection();
		try {
			Statement st=con.createStatement();
			rs=st.executeQuery(sql);
			while(rs.next()) 
			{
				Map topic=new HashMap();
				topic.put("tid",rs.getString("tid"));
				topic.put("tname",rs.getString("tname"));
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
     * 通过图书号删除对应的图书信息
     * @param bid 图书号
     * @return 结果集
     */
	public int deleteBookByBid(String bid) 
	{
		int res=0;
		openConnection();
		String sql="delete * from books where bid=?";
		try {
			ps=con.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(bid));
		    res=ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeResource();
		}
		return res;
	}

    /**
     * 插入新的图书信息
     * @param book 要插入的图书对象
     * @return 结果集
     */
	public int insertBooks(Book book) 
	{
		int res=0;
		String sql="insert into books"+
	" values(null,?,?,?,?,?,?,?)";
		openConnection();
		try {
			ps=con.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(book.getBtid()));
			ps.setString(2,book.getBname());
			ps.setString(3, book.getBauthor());
			ps.setString(4, book.getBpublisher());
			ps.setString(5, book.getBcreatedate());
			ps.setString(6,book.getBsummary());
			ps.setFloat(7,book.getBprice());
			res=ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeResource();
		}
		return res;
	}

    /**
     * 通过图书号进行图书信息的更改操作
     * @param bid 图书号
     * @param book 要更新的图书对象
     * @return 结果集
     */
	public int updateBooksByBid(String bid,Book book) 
	{
		int res=0;
		String sql="update books set btid=?,bname=?,bauthor=?,bpublisher=?,bcreatedate=?"
				+",bsummary=?,bprice=?"+"where bid="+bid;
		openConnection();
		try {
			ps=con.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(book.getBtid()));
			ps.setString(2, book.getBname());
			ps.setString(3,book.getBauthor());
			ps.setString(4, book.getBpublisher());
			ps.setString(5, book.getBcreatedate());
			ps.setString(6, book.getBsummary());
			ps.setFloat(7, book.getBprice());
			res=ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeResource();
		}
		return res;
	}

    /**
     * 关闭数据源
     * @return 成功true / 失败false
     */
	private boolean closeResource() 
	{
		try {
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
