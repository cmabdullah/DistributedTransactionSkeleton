package com.cm.kafka;

import javax.security.auth.callback.Callback;
import java.io.Serializable;

public interface KafkaProducer<K extends Serializable, V extends Serializable> {
	void send(String topicName, K key, V message, Callback callback);
}
