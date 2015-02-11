

public class Myshopee_ProductTO {
      //Declaring member variables
	private int productId;
	private String productName;
	private int associatedPoints;
	private int qtyInStock;
	private int pricePerUnit;
	private char category;
	private boolean checked;
	/**
	 * @return the productId
	 */
	public int getProductId() {
		return productId;
	}
	/**
	 * @param productId the productId to set
	 */
	public void setProductId(int productId) {
		this.productId = productId;
	}
	/**
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}
	/**
	 * @param productName the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}
	/**
	 * @return the associatedPoints
	 */
	public int getAssociatedPoints() {
		return associatedPoints;
	}
	/**
	 * @param associatedPoints the associatedPoints to set
	 */
	public void setAssociatedPoints(int associatedPoints) {
		this.associatedPoints = associatedPoints;
	}
	/**
	 * @return the qtyInStock
	 */
	public int getQtyInStock() {
		return qtyInStock;
	}
	/**
	 * @param qtyInStock the qtyInStock to set
	 */
	public void setQtyInStock(int qtyInStock) {
		this.qtyInStock = qtyInStock;
	}
	/**
	 * @return the pricePerUnit
	 */
	public int getPricePerUnit() {
		return pricePerUnit;
	}
	/**
	 * @param pricePerUnit the pricePerUnit to set
	 */
	public void setPricePerUnit(int pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}
	/**
	 * @return the category
	 */
	public char getCategory() {
		return category;
	}
	/**
	 * @param category the category to set
	 */
	public void setCategory(char category) {
		this.category = category;
	}
	/**
	 * @return the checked
	 */
	public boolean isChecked() {
		return checked;
	}
	/**
	 * @param checked the checked to set
	 */
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	
}
