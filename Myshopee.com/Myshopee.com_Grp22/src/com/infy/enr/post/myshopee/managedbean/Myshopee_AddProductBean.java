/**
 * This class contains the instance variables to hold the values from the form and
 * methods to invoke the add Product related functionalities
 * @author sandeepk01
 * Date : 13 December 2011
 * Version : 1.0 
 */

package com.infy.enr.post.myshopee.managedbean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import com.infy.enr.post.myshopee.transferobject.Myshopee_ProductTO;
import com.infy.enr.post.myshopee.wrapper.Myshopee_Wrapper;

public class Myshopee_AddProductBean {
      private String productName;
      private Integer price;
      private Integer quantityInStock;
      private char category;
      private String message;
      private Integer productid;
      private List<SelectItem> categorylist;
      
    public Myshopee_AddProductBean(){
    	categorylist = new ArrayList<SelectItem>();
    	categorylist.add(new SelectItem("E","Electronics"));
    	categorylist.add(new SelectItem("A","Accessories"));
    	categorylist.add(new SelectItem("H","Household"));
    	categorylist.add(new SelectItem("B","Books"));
    	categorylist.add(new SelectItem("F","Food Items"));
    }
    /**
     * Action method for adding new product
     * @return
     */
    public String addProduct(){
    	String msg="fail";
    	try{
    		Myshopee_ProductTO pto = new Myshopee_ProductTO();
    		pto.setProductName(productName);
    		pto.setPricePerUnit(price);
    		pto.setQtyInStock(quantityInStock);
    		pto.setCategory(category);
    		pto.setAssociatedPoints(price*10);
    		int id = new Myshopee_Wrapper().addProduct(pto);
    		message = "Product added successfully with Product Id : "+id;
    		msg = "success";
    	}
    	catch(Exception e){
    		message= e.getMessage();
    	}
    	return msg;
    }
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Integer getQuantityInStock() {
		return quantityInStock;
	}
	public void setQuantityInStock(Integer quantityInStock) {
		this.quantityInStock = quantityInStock;
	}
	public char getCategory() {
		return category;
	}
	public void setCategory(char category) {
		this.category = category;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Integer getProductid() {
		return productid;
	}
	public void setProductid(Integer productid) {
		this.productid = productid;
	}
	public List<SelectItem> getCategorylist() {
		return categorylist;
	}
	public void setCategorylist(List<SelectItem> categorylist) {
		this.categorylist = categorylist;
	}
}
