package consumer;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.Collections;
import java.util.Properties;

public class Consume {

	@KafkaListener(topics = "topicName")
	public void listen(String message) {
	    System.out.println("Received Messasge in group foo: " + message);
	}
}
