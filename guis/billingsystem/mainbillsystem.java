package gui;
import java.util.*;
public class mainbillsystem{

private List<items> itemlist;
private double taxrate;



public mainbillsystem() {
	this.itemlist=new ArrayList<>();
	this.taxrate= 0.16;
	
}
public void addItems(String name, Integer quantity, double price) {
	itemlist.add(new items(name,quantity,price));
}
public List<items> getItems(){
	return itemlist;
}
public double getTaxrate() {
	return taxrate;
}
public double computeItems() {
	double sum = 0;
	for(items i: itemlist) {
		sum+= i.getPrice()*i.getQuantity();
	}
	return sum;
}

public double computeTaxadditions() {
	double tax =computeItems()*taxrate;
	return tax;
}
public void clearitems() {
	itemlist.clear();
}

public boolean removeLastItem() {
    if (!itemlist.isEmpty()) {
        itemlist.remove(itemlist.size() - 1);
        return true;
    }
    return false;
}

}
class items{
	private  String itemname;
	private  Integer quantity;
	private double price;
	
	items(String itemname, Integer quantity, double price){
		this.itemname=itemname;
		this.quantity=quantity;
		this.price=price;
	}
	
	public String getItemname() {
		return itemname;
	}
	public int getQuantity() {
		return quantity;
	}
	public double getPrice() {
		return price;
	}
	
}