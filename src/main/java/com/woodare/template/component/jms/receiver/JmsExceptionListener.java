package com.woodare.template.component.jms.receiver;

import javax.jms.ExceptionListener;
import javax.jms.JMSException;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * 
 * @author Luke
 *
 */
@Component("jmsExceptionListener")
public class JmsExceptionListener implements ExceptionListener {
	private final Logger log = Logger.getLogger(JmsExceptionListener.class);

	@Override
	public void onException(JMSException exception) {
		log.error("JMSException Thrown Happened", exception);
	}

}
