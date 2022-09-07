package model;

public class Product_In_An_Order {
	
	private int orderId;
	private int productId;
	private int amount;
	private double price;
	
	public Product_In_An_Order(int orderId, int productId, int amount, double price) {
		super();
		this.orderId = orderId;
		this.productId = productId;
		this.amount = amount;
		this.price = price;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
}
