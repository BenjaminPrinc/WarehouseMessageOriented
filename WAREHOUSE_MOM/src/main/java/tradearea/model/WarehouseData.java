package tradearea.model;

import tradearea.product.ProductData;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WarehouseData {
	
	private String warehouseID;
	private String warehouseName;
	private String timestamp;
	private String warehouseAddress;
	private int warehousePostalCode;
	private String warehouseCity;
	private String warehouseCountry;
	private ProductData[] productData;

	/**
	 * Constructor
	 */
	public WarehouseData() {
		
		this.timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());

	}
	
	/**
	 * Setter and Getter Methods
	 */
	public String getWarehouseID() {
		return warehouseID;
	}

	public void setWarehouseID(String warehouseID) {
		this.warehouseID = warehouseID;
	}

	public String getWarehouseName() {
		return warehouseName;
	}

	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public void setWarehouseAddress(String warehouseAddress) {
		this.warehouseAddress = warehouseAddress;
	}

	public void setWarehouseCity(String warehouseCity) {
		this.warehouseCity = warehouseCity;
	}

	public void setWarehouseCountry(String warehouseCountry) {
		this.warehouseCountry = warehouseCountry;
	}

	public void setWarehousePostalCode(int warehousePostalCode) {
		this.warehousePostalCode = warehousePostalCode;
	}

	public String getWarehouseAddress() {
		return warehouseAddress;
	}

	public String getWarehouseCity() {
		return warehouseCity;
	}

	public String getWarehouseCountry() {
		return warehouseCountry;
	}

	public int getWarehousePostalCode() {
		return warehousePostalCode;
	}

	public void setProductData(ProductData[] products) {
		this.productData = products;
	}

	public ProductData[] getProductData() {
		return productData;
	}

	/**
	 * Methods
	 */
	@Override
	public String toString() {
		String info = String.format("Warehouse Info: ID = %s, timestamp = %s", warehouseID, timestamp );
		return info;
	}
}
