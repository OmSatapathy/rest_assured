package com.pojo;

public class LoptopDetails {

	private int year;
	private int price;
	private String cpu_model;
	private String hardDish;

	public LoptopDetails(int year, int price, String cpu_model, String hardDish) {
		this.year = year;
		this.price = price;
		this.cpu_model = cpu_model;
		this.hardDish = hardDish;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getCpu_model() {
		return cpu_model;
	}

	public void setCpu_model(String cpu_model) {
		this.cpu_model = cpu_model;
	}

	public String getHardDish() {
		return hardDish;
	}

	public void setHardDish(String hardDish) {
		this.hardDish = hardDish;
	}

	@Override
	public String toString() {
		return "LoptopDetails [year=" + year + ", price=" + price + ", cpu_model=" + cpu_model + ", hardDish="
				+ hardDish + "]";
	}

}
