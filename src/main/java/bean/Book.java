package bean;

public class Book {
	
	private String bid;
	private String btid;
	private String btname;
	private String bname;
	private String bpublisher;
	private String bauthor;
	private int  bprice;
	private String bsummary;
	private String bcreatedate;
	
	public Book() {}
	public Book(String btid,String bname,String bpublisher,String bauthor,int bprice,String bsummary,String bcreatedate)
	{
		this.btid=btid;
		this.bname=bname;
		this.bpublisher=bpublisher;
		this.bauthor=bauthor;
		this.bprice=bprice;
		this.bsummary=bsummary;
		this.bcreatedate=bcreatedate;
	}
	public String getBtname() 
	{
		return btname;
	}
	public void setBtname(String btname) 
	{
		this.btname=btname;
	}
	public String getBtid() {
		return btid;
	}
	public void setBtid(String btid) {
		this.btid = btid;
	}
	public String getBcreatedate() {
		return bcreatedate;
	}
	public void setBcreatedate(String bcreatedate) {
		this.bcreatedate = bcreatedate;
	}
	public String getBsummary() {
		return bsummary;
	}
	public void setBsummary(String bsummary) {
		this.bsummary = bsummary;
	}
	public String getBid() {
		return bid;
	}
	public void setBid(String bid) {
		this.bid = bid;
	}
	public String getBname() {
		return bname;
	}
	public void setBname(String bname) {
		this.bname = bname;
	}
	public String getBpublisher() {
		return bpublisher;
	}
	public void setBpublisher(String bpublisher) {
		this.bpublisher = bpublisher;
	}
	public String getBauthor() {
		return bauthor;
	}
	public void setBauthor(String bauthor) {
		this.bauthor = bauthor;
	}
	public int getBprice() {
		return bprice;
	}
	public void setBprice(int bprice) {
		this.bprice = bprice;
	}
	
	

}
