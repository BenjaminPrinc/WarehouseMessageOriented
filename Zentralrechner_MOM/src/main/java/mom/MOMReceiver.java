package mom;


import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import zentralrechner.ZentralrechnerApplication;
import zentralrechner.ZentralrechnerController;

import javax.jms.*;

public class MOMReceiver {

    private static String user = ActiveMQConnection.DEFAULT_USER;
    private static String password = ActiveMQConnection.DEFAULT_PASSWORD;
    private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;
    private static String subject = "warehouse_linz";

    public MOMReceiver() {

        System.out.println( "Receiver started." );

        // Create the connection.
        Session session = null;
        Connection connection = null;
        MessageConsumer consumer = null;
        Destination destination = null;

        try {

            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(user, password, url);
            connection = connectionFactory.createConnection();
            connection.start();

            // Create the session
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            destination = session.createTopic( subject );

            // Create the consumer
            consumer = session.createConsumer( destination );
            System.out.println("Consumer initialized");
            // Start receiving
            TextMessage message = (TextMessage) consumer.receive();
            System.out.println("Message receiver");
            System.out.println(message.getText());
            ZentralrechnerController.messages = message.getText();
            /*consumer.setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message message) {
                    System.out.println("onMessage Method");
                    try {
                        if (message instanceof TextMessage) {
                            TextMessage textMessage = (TextMessage) message;
                            System.out.println("Message received: " + textMessage.getText());
                            ZentralrechnerController.messages += textMessage.getText();
                            message.acknowledge();
                        } else {
                            System.out.println("Received message of unexpected type: " + message.getClass().getName());
                        }
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            });*/




        } catch (Exception e) {

            System.out.println("[MessageConsumer] Caught: " + e);
            e.printStackTrace();

        } finally {

            try { consumer.close(); } catch ( Exception e ) {}
            try { session.close(); } catch ( Exception e ) {}
            try { connection.close(); } catch ( Exception e ) {}

        }
        System.out.println( "Receiver finished." );

    } // end main

}