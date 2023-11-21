package tradearea.warehouse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.stereotype.Service;
import tradearea.model.WarehouseData;

@Service
public class WarehouseService {
	
	public String getGreetings( String inModule ) {
        return "Greetings from " + inModule;
    }

    public WarehouseData getWarehouseData( String inID ) {
    	
    	WarehouseSimulation simulation = new WarehouseSimulation();
        return simulation.getData( inID );
        
    }

    public String getXMLWarehouseData() throws JsonProcessingException {
        WarehouseSimulation simulation = new WarehouseSimulation();
        WarehouseData wd = simulation.getData("1");

        XmlMapper xmlMapper = new XmlMapper();
        String xml = xmlMapper.writeValueAsString(wd);
        return xml;
    }
    
}