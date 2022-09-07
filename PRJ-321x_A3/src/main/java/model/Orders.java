package model;

import java.util.*;

public class Orders {
	
	private int orderId;
	private Date orderDate;
	private String userEmail;
	private String phoneNumber;
	private String address;
	private int status;
	private String discount;
	private double price;
	private ArrayList<Product_In_An_Order> listOfProducts;
	
	public Orders() {
		super();
	}

	public Orders(int orderId, Date orderDate, String userEmail, String phoneNumber, String address, int status,
			double price, ArrayList<Product_In_An_Order> listOfProducts) {
		super();
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.userEmail = userEmail;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.status = status;
		this.price = price;
		this.listOfProducts = listOfProducts;
	}

	public Orders(String userEmail, String phoneNumber, String address, int status, Date orderDate, String discount) {
		super();
		this.userEmail = userEmail;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.status = status;
		this.orderDate = orderDate;
		this.discount = discount;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public ArrayList<Product_In_An_Order> getListOfProducts() {
		return listOfProducts;
	}

	public void setListOfProducts(ArrayList<Product_In_An_Order> listOfProducts) {
		this.listOfProducts = listOfProducts;
	}
	
}
