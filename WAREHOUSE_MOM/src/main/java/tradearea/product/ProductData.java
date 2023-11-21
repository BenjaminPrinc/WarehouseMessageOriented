package tradearea.product;

import java.util.Random;
import java.util.UUID;

public class ProductData {

    private String productID;
    private String productName;
    private String productCategory;
    private int productQuantity;
    private String productUnit;

    private final String[][] randomProduct = {
            {"Apfel", "Obst", "Kiste 1kg"}, {"Banane", "Obst", "Kiste 1kg"}, {"Brot", "Backwaren", "Sackerl 2kg"}, {"Kornspitz", "Backwaren", "Sackerl 1kg"}, {"Kartoffel", "Gemüse", "Netz 5kg"}, {"Cola", "Softdrink", "Palette 5kg"}, {"Sprite", "Softdrink", "Palette 5kg"}
    };

    public String getTempProductName(int index) {
        return randomProduct[index][0];
    }

    public String getTempProductCategory(int index) {
        return randomProduct[index][1];
    }

    public String getTempProductUnit(int index) {
        return randomProduct[index][2];
    }

    private int randomInt(int low, int high) {
        Random r = new Random();
        return r.nextInt(high-low)+low;
    }

    public ProductData() {
        this.productID = UUID.randomUUID().toString().substring(0,4);
        int r = randomInt(0 ,7);
        this.productName = getTempProductName(r);
        this.productCategory = getTempProductCategory(r);
        this.productQuantity = randomInt(5, 100);
        this.productUnit = getTempProductUnit(r);
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public String getProductUnit() {
        return productUnit;
    }

    public void setProductUnit(String productUnit) {
        this.productUnit = productUnit;
    }

}
