package main.java;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class jmsSender {

    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory("tcp://localhost:61616");
        Connection connection = cf.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue("MUV_TRK_QUEUE");

        // bind produced to the desired key
        MessageProducer sender = session.createProducer(queue);
        TextMessage msg = session.createTextMessage("Conversion sent.");
        msg.setStringProperty("offerId", "2007");
        msg.setStringProperty("source", "SmartMessage");
        msg.setStringProperty("affSub2", "551199991234");
        msg.setStringProperty("affSub", "keyword");
        msg.setStringProperty("affId", "1034");
        sender.send(msg);
        System.out.println("Message sent!");

        // this will also close the session
        // or if you want to close just the session.close()
        connection.close();

    }
}
