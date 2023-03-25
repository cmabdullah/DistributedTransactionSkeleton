package com.cm.config;

import java.util.Map;
import java.util.Properties;

public class KafkaConfig {

	Properties producerConfigProperties = new Properties();

	void setProducerConfigProperties(Map<String, String> producerConfigPropertiesMap) {

		producerConfigPropertiesMap.entrySet().stream()
				.forEach(producerConfig ->
						producerConfigProperties.setProperty(
								producerConfig.getKey(), producerConfig.getValue()));
	}
}
