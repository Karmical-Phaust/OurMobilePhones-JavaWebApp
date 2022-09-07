package model;

import java.util.*;

public class Cart {
	
	private List<Product> items;

	public Cart() {
		this.items = new ArrayList<>();
	}
	
	public boolean isEmpty() {
		return this.items.isEmpty();
	}
	
	
	public void addToCart(Product p) {
		
		for (Product x : items) {
			
			if (x.getId() == p.getId()) {
				
				x.setNumbersOfProduct(x.getNumbersOfProduct() + 1);
				
				return;
				
			}
			
		}
		
		this.items.add(p);
		
	}
	
	
	public void removeFromCart(int id) {
		
		Iterator<Product> item = this.items.iterator();
		while (item.hasNext()) {
			
			Product x = item.next();
			
			if (x.getId() == id) {
				
				if (x.getNumbersOfProduct() >=1) {
					x.setNumbersOfProduct(x.getNumbersOfProduct() - 1);
				}
				
				if (x.getNumbersOfProduct() == 0) {
					item.remove();
					return;
				}
			}
			
		}
		
	}
	
	public void removeAllFromCart() {
		
		this.items.clear();
		
	}
	
	
	public double totalPrice() {
		
		double sum = 0;
		
		for (Product x : items) {
			
			sum += (x.getPrice()*x.getNumbersOfProduct());
			
		}
		
		return Math.round(sum*100.0)/100.0;
		
	}


	public List<Product> getItems() {
		return items;
	}
	
}
