package com.cm.kafka;

public interface KafkaConsumer<K extends String, V extends String> {
	void receive(K key, V message);
}