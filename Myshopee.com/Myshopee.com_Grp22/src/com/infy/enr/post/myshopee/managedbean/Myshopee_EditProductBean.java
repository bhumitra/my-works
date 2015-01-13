package com.infy.enr.post.myshopee.managedbean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import com.infy.enr.post.myshopee.support.ErrorLogger;
import com.infy.enr.post.myshopee.transferobject.Myshopee_ProductTO;
import com.infy.enr.post.myshopee.wrapper.Myshopee_Wrapper;

/**
 * @author anoop_sundaresh
 *
 */
public class Myshopee_EditProductBean {

	private String productName;
	private Integer productId;
	private List<SelectItem> productList = new ArrayList<SelectItem>();
	private Integer price;
	private Integer quantityInStock;
	private Character category;
	private String message;

	/**
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * @param productName
	 *            the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * @return the productId
	 */
	public Integer getProductId() {
		return productId;
	}

	/**
	 * @param productId
	 *            the productId to set
	 */
	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	/**
	 * @return the productList
	 */
	public List<SelectItem> getProductList() {
		return productList;
	}

	/**
	 * @param productList
	 *            the productList to set
	 */
	public void setProductList(List<SelectItem> productList) {
		this.productList = productList;
	}

	/**
	 * @return the price
	 */
	public Integer getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(Integer price) {
		this.price = price;
	}

	/**
	 * @return the quantityInStock
	 */
	public Integer getQuantityInStock() {
		return quantityInStock;
	}

	/**
	 * @param quantityInStock
	 *            the quantityInStock to set
	 */
	public void setQuantityInStock(Integer quantityInStock) {
		this.quantityInStock = quantityInStock;
	}

	/**
	 * @return the category
	 */
	public Character getCategory() {
		return category;
	}

	/**
	 * @param category
	 *            the category to set
	 */
	public void setCategory(Character category) {
		this.category = category;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param meassage
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @param event
	 */
	public void getProductByCategory(ValueChangeEvent event) {
		productList = new ArrayList<SelectItem>();
		Character category = (Character) event.getNewValue();
		try {
			List<Myshopee_ProductTO> list = new Myshopee_Wrapper()
			.getProductFromCategory(category);
			for (int i = 0; i < list.size(); i++) {
				Myshopee_ProductTO product = list.get(i);
				productList.add(new SelectItem(product.getProductId(), product
						.getProductName()));

			}
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(),
					"getProductByCategory()", e.getMessage());
			message = e.getMessage();
		}
	}

	/**
	 * @return
	 */
	public String getProductById() {
		try {
			message="";
			Myshopee_ProductTO productTO = new Myshopee_Wrapper().getProductById(productId);
			this.setProductId(productTO.getProductId());
			this.setProductName(productTO.getProductName());
			this.setPrice(productTO.getPricePerUnit());
			this.setCategory(productTO.getCategory());
			this.setQuantityInStock(productTO.getQtyInStock());
			return "found";

		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "getProductById()",
					e.getMessage());
			message = e.getMessage();
			return "failure";
		}
	}

	/**
	 * @return
	 */
	public String editProduct() {
		Myshopee_ProductTO productTO = new Myshopee_ProductTO();
		try {
			message="";
			productTO.setAssociatedPoints(price*10);
			productTO.setCategory(category);
			productTO.setPricePerUnit(price);
			productTO.setProductId(productId);
			productTO.setProductName(productName);
			productTO.setQtyInStock(quantityInStock);
			int id = new Myshopee_Wrapper().editProduct(productTO);
			if (id == 1) {
				return "success";
			}
			else{
				return "failure";
			}
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "editProduct()", e
					.getMessage());
			message = e.getMessage();
			return "failure";
		}
	}
}
