package mom;


import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import tradearea.warehouse.WarehouseService;

import javax.jms.*;

public class MOMSender {

    private static String user = ActiveMQConnection.DEFAULT_USER;
    private static String password = ActiveMQConnection.DEFAULT_PASSWORD;
    private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;
    private static String subject = "warehouse_linz";

    public MOMSender() {

        System.out.println( "Sender started." );

        // Create the connection.
        Session session = null;
        Connection connection = null;
        MessageProducer producer = null;
        Destination destination = null;

        try {

            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory( user, password, url );
            connection = connectionFactory.createConnection();
            connection.start();

            // Create the session
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            destination = session.createTopic( subject );

            // Create the producer.
            producer = session.createProducer(destination);
            producer.setDeliveryMode( DeliveryMode.NON_PERSISTENT );

            // Create the message
            WarehouseService ws = new WarehouseService();
            TextMessage message = session.createTextMessage(ws.getXMLWarehouseData());
            producer.send(message);
            System.out.println( message.getText() );

            connection.stop();

        } catch (Exception e) {

            System.out.println("[MessageProducer] Caught: " + e);
            e.printStackTrace();

        } finally {

            try { producer.close(); } catch ( Exception e ) {}
            try { session.close(); } catch ( Exception e ) {}
            try { connection.close(); } catch ( Exception e ) {}

        }
        System.out.println( "Sender finished." );

    } // end main

}