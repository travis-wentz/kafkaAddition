package addition;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import java.util.Properties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/*
 * This method functions as an endpoint to PUT a value. 
 * It creates a kafka producer and adds the given value to the current kafka topic.
 */
@RestController
public class AdditionController {
	private final static String TOPIC = "input";
	private final static String BOOTSTRAP_SERVERS = "localhost:2181";

	/**
	 * Gets the int value given in the URL.
	 * Calls runProducer() and tells you if it succeeded.
	 * @param int value
	 * @return final status of runProducer()
	 * @throws Exception
	 */
	@RequestMapping("/calculator/add")
	public String add(@RequestParam(value = "value", defaultValue = "0") int value) throws Exception {
		if(runProducer(value)) { return ("A value of " + value + " was added to " + TOPIC + " topic."); }
		return "Something went wrong. No value was added.";
	}

	/**
	 * Takes in an int value, creates a producer, adds the value to the producer.
	 * @param valueToAdd
	 * @return success or failure boolean
	 * @throws Exception
	 */
	private static boolean runProducer(final int valueToAdd) throws Exception {
		final Producer<Long, String> producer = createProducer();
		boolean success = false;
		
		try { //try to create a record and send the record to producer
			final ProducerRecord<Long, String> record = new ProducerRecord<>(TOPIC, Integer.toString(valueToAdd));
			producer.send(record);
			success = true;
		} finally {
			producer.flush();
			producer.close();
		}
		
		return success;
	}

	/**
	 * Creates a kafka producer.
	 * @return Producer
	 */
	private static Producer<Long, String> createProducer() {
		Properties props = new Properties();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
		props.put(ProducerConfig.CLIENT_ID_CONFIG, "KafkaExampleProducer");
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class.getName());
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		return new KafkaProducer<>(props);
	}
}
