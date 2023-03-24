package com.cm.kafka;

import javax.security.auth.callback.Callback;
import java.io.Serializable;
import java.util.logging.Logger;

public class KafkaProducerImpl implements KafkaProducer {
	Logger logger = Logger.getLogger(KafkaProducerImpl.class.getName());
	@Override
	public void send(String topicName, Serializable key, Serializable message, Callback callback) {
		logger.info(topicName.toString());
	}
}
