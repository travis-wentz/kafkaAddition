package producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

public class Produce {
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	public Produce() {
		
	}
	 
	public void sendMessage(String topicName, String msg) {
	    kafkaTemplate.send(topicName, msg);
	}
}