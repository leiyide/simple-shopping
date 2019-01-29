package com.lyd.entity;

public class Product {
	//id
	private int id;
	//姓名
	private String name;
	//描述
	private String description;
	//价格
	private String price;
	//备货
	private String stock;
	//父级id
	private int hpcId;
	//子级id
	private int childId;
	//文件名
	private String file;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getStock() {
		return stock;
	}
	public void setStock(String stock) {
		this.stock = stock;
	}
	public int getHpcId() {
		return hpcId;
	}
	public void setHpcId(int hpcId) {
		this.hpcId = hpcId;
	}
	public int getChildId() {
		return childId;
	}
	public void setChildId(int childId) {
		this.childId = childId;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
}
