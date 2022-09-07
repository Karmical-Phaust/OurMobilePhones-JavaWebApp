package model;

public class Product {
	
	private int id;
	private String name;
	private String description;
	private double price;
	private String img_src;
	private String type;
	private String brand;
	private int numbersOfProduct;
	
	public Product() {
		super();
	}

	public Product(int id, String name, String description, double price, String img_src, String type, String brand) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.img_src = img_src;
		this.type = type;
		this.brand = brand;
	}
	
	public Product(int id, String name, String description, double price, String img_src, String type, String brand, int numbersOfProduct) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.img_src = img_src;
		this.type = type;
		this.brand = brand;
		this.numbersOfProduct = numbersOfProduct;
	}

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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getImg_src() {
		return img_src;
	}

	public void setImg_src(String img_src) {
		this.img_src = img_src;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public int getNumbersOfProduct() {
		return numbersOfProduct;
	}

	public void setNumbersOfProduct(int numbersOfProduct) {
		this.numbersOfProduct = numbersOfProduct;
	}
	
}
