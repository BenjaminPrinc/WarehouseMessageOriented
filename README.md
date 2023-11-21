# MidEng 7.2 Warehouse Message Oriented Middleware
**Autor**: Benjamin Princ
**Datum**: 21.11.2023

## Aufgabenstellung
Diese Übung soll die Funktionsweise und Implementierung von eine Message Oriented Middleware (MOM) mit Hilfe des Frameworks Apache Active MQ demonstrieren. Message Oriented Middleware (MOM) ist neben InterProcessCommunication (IPC), Remote Objects (RMI) und Remote Procedure Call (RPC) eine weitere Möglichkeit um eine Kommunikation zwischen mehreren Rechnern umzusetzen.

## Umsetzung
1. MOMReceiver baut bei Webseite Aufruf eine Verbindung zu ApacheMQ auf.

MOMSender.java
```java
ConnectionFactory connectionFactory = new ActiveMQConnectionFactory( user, password, url );
            connection = connectionFactory.createConnection();
            connection.start();

            // Create the session
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            destination = session.createTopic( subject );

            // Create the producer.
            producer = session.createProducer(destination);
            producer.setDeliveryMode( DeliveryMode.NON_PERSISTENT );
```
2. Wird eine Message von MOMSender an ApacheMQ gesendet.

WarehouseController.java
```java
@RequestMapping("/warehouse/transfer")
public String warehouseTransfer() {
    MOMSender sender = new MOMSender();
    return service.getGreetings("Warehouse.Transfer!");
}
```

3. Der MOMReceiver empfängt die Nachricht, gibt sie auf der Konsole aus und zeigt sie auf der Webseite an.

MOMReceiver.java
```java
   TextMessage message = (TextMessage) consumer.receive();
    System.out.println("Message receiver");
    System.out.println(message.getText());
    ZentralrechnerController.messages = message.getText();
```

ZentralrechnerController.java
```java
public static String messages = "";
private MOMReceiver rec;

@RequestMapping(value="/zentralrechner/receive", produces = MediaType.APPLICATION_XML_VALUE)
public String warehouseTransfer() {
    rec = new MOMReceiver();
    return messages;
}
```
