# Middleware Engineering "Message Oriented Middleware"

*Autor:* **Benjamin Princ**<br>
*Datum:* **21.11.2023**

## Aufgabenstellung
Die detaillierte [Aufgabenstellung](TASK.md) beschreibt die notwendigen Schritte zur Realisierung.

## Arbeitsschritte
1. Gradle Konfigurationen angepasst.
2. Bestehendes Warehouse Projekt um das Package "mom" und die Klasse *MOMSender.java* erweitert.
3. Neues Projekt für Zentralrechner_MOM angelegt und konfiguriert.
4. Warehouse Objekt als XML String versenden.


## Implementierung
### Gradle
In der Datei build.gradle folgende Zeilen hinzugefügt:
```
configurations {
    compileOnly {
        extendsFrom annotationProcessor
    }
}

implementation 'org.springframework.boot:spring-boot-starter-activemq'
compileOnly 'org.projectlombok:lombok'
annotationProcessor 'org.projectlombok:lombok'
```

### MOMSender
In der Klasse [MOMSender](src/main/java/mom/MOMSender.java) wird zuerst eine Verbindung mithilfe der standard Zugangsdaten zum ActiveMQ Service hergestellt.

Zusätzlich wurde in der [WarehouseController](src/main/java/tradearea/warehouse/WarehouseController.java) Klasse der Zugriff auf die neue Methode ermöglicht:
```java
    @RequestMapping("/warehouse/transfer")
    public String warehouseTransfer() {
        MOMSender sender = new MOMSender();
        return service.getGreetings("Warehouse.Transfer!");
    }
```

### MOMReceiver
Um zwei Springboot Web Applikationen laufen zu lassen muss ein zweiter Port zugewiesen werden.
Zentralrechner, application.properties:
``
server.port=8081
``

### Warehouse-Objekt versenden
In der Klasse [WarehouseService](/src/main/java/tradearea/warehouse/WarehouseService.java) folgende Methode implementiert:
```java
public String getXMLWarehouseData() throws JsonProcessingException {
    WarehouseSimulation simulation = new WarehouseSimulation();
    WarehouseData wd = simulation.getData("1");
    
    XmlMapper xmlMapper = new XmlMapper();
    String xml = xmlMapper.writeValueAsString(wd);
    return xml;
}
```
Mithilfe dieser wird das Warehouse-Objekt als XML String an die Sender Methode übergeben.

### MOMReceiver Verbindungsaufbau
MOMReceiver baut bei Webseite Aufruf eine Verbindung zu ApacheMQ auf.

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
### Message senden
Wird eine Message von MOMSender an ApacheMQ gesendet.

WarehouseController.java
```java
@RequestMapping("/warehouse/transfer")
public String warehouseTransfer() {
    MOMSender sender = new MOMSender();
    return service.getGreetings("Warehouse.Transfer!");
}
```
### Message empfangen
Der MOMReceiver empfängt die Nachricht, gibt sie auf der Konsole aus und zeigt sie auf der Webseite an.

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
## Quellen
[1] https://www.baeldung.com/jackson-xml-serialization-and-deserialization