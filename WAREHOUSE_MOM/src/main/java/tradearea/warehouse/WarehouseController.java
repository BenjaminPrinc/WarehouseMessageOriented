package tradearea.warehouse;

import mom.MOMSender;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import tradearea.model.WarehouseData;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class WarehouseController {

    @Autowired
    private WarehouseService service;

	
    @RequestMapping("/")
    public String warehouseMain() {
    	String mainPage = "This is the warehouse application! (DEZSYS_WAREHOUSE_REST) <br/><br/>" +
                          "<a href='http://localhost:8080/warehouse/001/data'>Link to warehouse/001/data</a><br/>" +
                          "<a href='http://localhost:8080/warehouse/001/xml'>Link to warehouse/001/xml</a><br/>" +
                          "<a href='http://localhost:8080/warehouse/transfer'>Link to warehouse/transfer</a><br/>";
        return mainPage;
    }

    @RequestMapping(value="/warehouse/{inID}/data", produces = MediaType.APPLICATION_JSON_VALUE)
    public WarehouseData warehouseData( @PathVariable String inID ) {
        return service.getWarehouseData( inID );
    }

    @RequestMapping(value="/warehouse/{inID}/xml", produces = MediaType.APPLICATION_XML_VALUE)
    public WarehouseData warehouseDataXML( @PathVariable String inID ) {
        return service.getWarehouseData( inID );
    }

    @RequestMapping("/warehouse/transfer")
    public String warehouseTransfer() {
        MOMSender sender = new MOMSender();
        return service.getGreetings("Warehouse.Transfer!");
    }


}