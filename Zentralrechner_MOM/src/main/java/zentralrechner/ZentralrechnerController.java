package zentralrechner;

import mom.JmsConsumer;
import mom.MOMReceiver;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class ZentralrechnerController {

    public static String messages = "<AlleWarenhaeuser>";
    private MOMReceiver rec;
    //private JmsConsumer cons;


    @RequestMapping("/")
    public String warehouseMain() {
    	String mainPage = "This is the centralsystem application! (DEZSYS_CENTRALSYSTEM_REST) <br/><br/>" +
                          "<a href='http://localhost:8081/zentralrechner/receive'>Receive Data</a><br/>";
        return mainPage;
    }

    @RequestMapping(value="/zentralrechner/receive", produces = MediaType.APPLICATION_XML_VALUE)
    public String warehouseTransfer() {
        rec = new MOMReceiver();
        //cons = new JmsConsumer();
        return messages+"</AlleWarenhaeuser>";
    }

}