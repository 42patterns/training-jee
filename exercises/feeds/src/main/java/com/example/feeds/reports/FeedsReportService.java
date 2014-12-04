package com.example.feeds.reports;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;

@Stateless
public class FeedsReportService {

	@Resource(name = "ReportQueue")
	private Queue queue;
 
	@Resource
    private ConnectionFactory connectionFactory;
 
	public void log(FeedsReportDTO dto) throws JMSException {		
        Connection connection = connectionFactory.createConnection();
        connection.start();
 
        // Create a Session
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
 
        // Create a MessageProducer from the Session to the Topic or Queue
        MessageProducer producer = session.createProducer(queue);
        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
 
        // Create a message
        ObjectMessage message = session.createObjectMessage(dto);
 
        // Tell the producer to send the message
        producer.send(message);
	}

}
