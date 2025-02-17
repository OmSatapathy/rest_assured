package com.pojos;

import java.util.List;

public class Searchpage {
	
	private int page;
	private int per_page;
	private int total;
	private String name;
	private List<EmpDetails> data;
	


	public void setData(List<EmpDetails> list) {
		this.data = list;
	}

	public int getPage() {
		return page;
	}

	public int getPer_page() {
		return per_page;
	}

	public int getTotal() {
		return total;
	}

	public String getName() {
		return name;
	}

	public List<EmpDetails> getData() {
		return data;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public void setPer_page(int per_page) {
		this.per_page = per_page;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Searchpage [page=" + page + ", per_page=" + per_page + ", total=" + total + ", name=" + name + ", data="
				+ data + "]";
	}


	
	

}
