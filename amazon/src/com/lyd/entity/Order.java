package com.lyd.entity;

public class Order {
	//商品id
	private int pid;
	//订单id
	private int oid;
	//商id
	private int phid;
	//用户的id
	private int userid;
	//商品的数量
	private int quantity;
	//商品的价格
	private int price;
	//对应商品id的总价
	private int pidPrice;
	//对应用户id的总价
	private int userPrice;
	//库存
	private int stock;
	//商品名
	private String name;
	//用户名
	private String userName;
	//地址
	private String address;
	//订单时间
	private String createTime;
	//状态
	private int state;
	//类型
	private int type;
	//图片
	private String file;
	//父id
	private int parentid;
	//子id
	private int childid;
	
	//总数量
	private int sunQuantity;
	
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public int getOid() {
		return oid;
	}
	public void setOid(int oid) {
		this.oid = oid;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getPidPrice() {
		return pidPrice;
	}
	public void setPidPrice(int pidPrice) {
		this.pidPrice = pidPrice;
	}
	public int getUserPrice() {
		return userPrice;
	}
	public void setUserPrice(int userPrice) {
		this.userPrice = userPrice;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getPhid() {
		return phid;
	}
	public void setPhid(int phid) {
		this.phid = phid;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public int getParentid() {
		return parentid;
	}
	public void setParentid(int parentid) {
		this.parentid = parentid;
	}
	public int getChildid() {
		return childid;
	}
	public void setChildid(int childid) {
		this.childid = childid;
	}
	public int getSunQuantity() {
		return sunQuantity;
	}
	public void setSunQuantity(int sunQuantity) {
		this.sunQuantity = sunQuantity;
	}
	
}
