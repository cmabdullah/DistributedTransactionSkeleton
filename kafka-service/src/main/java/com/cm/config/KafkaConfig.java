package com.cm.config;

import java.util.Map;
import java.util.Properties;

public class KafkaConfig {

	Properties configProperties = new Properties();

	public KafkaConfig(Map<String, String>  configPropertiesMap) {
		configPropertiesMap.entrySet().stream()
				.forEach(producerConfig ->
						configProperties.setProperty(
								producerConfig.getKey(), producerConfig.getValue()));
	}
}
