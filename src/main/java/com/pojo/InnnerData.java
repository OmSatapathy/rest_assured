package com.pojo;

public class InnnerData {
	
	private String name;
	private LoptopDetails data;
	
	public InnnerData(String name, LoptopDetails data) {
		super();
		this.name = name;
		this.data = data;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LoptopDetails getData() {
		return data;
	}
	public void setData(LoptopDetails data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "InnnerData [name=" + name + ", data=" + data + "]";
	}

}
