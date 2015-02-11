

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="myshopee_productdetails")
public class Myshopee_Product {
    @Id
    @SequenceGenerator(name="Seq_ProductId", sequenceName="Seq_ProductId", initialValue=5007, allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="Seq_ProductId")
    private int productId;
    @Column(length=30,nullable=false)
    private String productName;
    @Column(nullable=false)
    private int associated_points;
    @Column(nullable=false)
    private int qtyInStock;
    @Column(nullable=false)
    private int pricePerUnit;
    @Column(nullable=false)
    private char category;
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
	 * @return the associated_points
	 */
	public int getAssociated_points() {
		return associated_points;
	}
	/**
	 * @param associated_points the associated_points to set
	 */
	public void setAssociated_points(int associated_points) {
		this.associated_points = associated_points;
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
}
