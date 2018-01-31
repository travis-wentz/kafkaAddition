package addInput;

import java.util.Properties;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

public class ProduceInput {

	private final static String TOPIC = "input";
	private final static String BOOTSTRAP_SERVERS = "localhost:2181";

	/**
	 * Takes in an int value, creates a producer, adds the value to the producer.
	 * @param valueToAdd
	 * @return success or failure boolean
	 * @throws Exception
	 */
	static boolean runProducer(final int valueToAdd) throws Exception {
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
