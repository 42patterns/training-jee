package com.example.feeds.reports;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@MessageDriven(mappedName = "ReportQueue", activationConfig = {
		@ActivationConfigProperty(propertyName = "acknowledgeMode", 
				propertyValue = "Auto-acknowledge"),
		@ActivationConfigProperty(propertyName = "destinationType", 
				propertyValue = "javax.jms.Queue") 
})
public class FeedsReportConsumer implements MessageListener {

	@Resource
	private ConnectionFactory connectionFactory;

	@Resource(name = "ReportQueue")
	private Queue queue;

	@PersistenceContext
	EntityManager em;
	
	@Override
	public void onMessage(Message message) {

		final ObjectMessage objectMessage = (ObjectMessage) message;
		try {
			final FeedsReportDTO dto = (FeedsReportDTO) objectMessage
					.getObject();

			em.persist(new FeedsReportEntity(dto));
			em.flush();

		} catch (JMSException e) {
			throw new IllegalStateException(e);
		}
	}

}
