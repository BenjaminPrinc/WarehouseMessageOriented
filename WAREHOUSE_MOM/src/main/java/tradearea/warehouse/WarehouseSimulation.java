package tradearea.warehouse;

import tradearea.model.WarehouseData;
import tradearea.product.ProductData;

public class WarehouseSimulation {

	private double getRandomDouble( int inMinimum, int inMaximum ) {

		double number = ( Math.random() * ( (inMaximum-inMinimum) + 1 )) + inMinimum; 
		double rounded = Math.round(number * 100.0) / 100.0; 
		return rounded;
		
	}

	private int getRandomInt( int inMinimum, int inMaximum ) {

		double number = ( Math.random() * ( (inMaximum-inMinimum) + 1 )) + inMinimum; 
		Long rounded = Math.round(number); 
		return rounded.intValue();

	}
	
	public WarehouseData getData( String inID ) {
		
		WarehouseData data = new WarehouseData();
		data.setWarehouseID( inID );
		data.setWarehouseName( "Wien ReweLogistics" );
		data.setWarehouseAddress("Rewestraße 1");
		data.setWarehouseCity("Wien");
		data.setWarehouseCountry("Austria");
		data.setWarehousePostalCode(1010);

		ProductData p1 = new ProductData();
		ProductData p2 = new ProductData();
		ProductData p3 = new ProductData();
		ProductData p4 = new ProductData();
		ProductData p5 = new ProductData();

		data.setProductData(new ProductData[]{p1, p2, p3, p4, p5});

		return data;
		
	}

}
