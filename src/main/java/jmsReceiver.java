package main.java;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class jmsReceiver {

    public static void main(String[] args) throws JMSException {
        javax.jms.Connection connection = new ActiveMQConnectionFactory("tcp://localhost:61616").createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue("MUV_TRK_QUEUE");

        MessageConsumer receiver = session.createConsumer(queue);
        TextMessage msg = (TextMessage)receiver.receive();
        String offerId = msg.getStringProperty("offerId");
        String affSub2 = msg.getStringProperty("affSub2");
        String affId = msg.getStringProperty("affId");
        System.out.println(msg.getText() + offerId + "," + affSub2 + "," + affId);
        connection.close();

        /* if (msg == null) {
            TextMessage msg = (TextMessage)receiver.receive(10000);
        } */

        // TextMessage msg = (TextMessage)receiver.receiveNoWait(); // if there is a message pick it up, if there is no message it will return null
        // TextMessage msg = (TextMessage)receiver.receive(10000); // wait 10 seconds and terminate. after 10 seconds, if there is no message we will also get a null


    }


}
