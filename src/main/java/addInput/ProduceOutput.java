package addInput;

import java.util.Properties;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

public class ProduceOutput {

	private final static String TOPIC = "input";
	private final static String BOOTSTRAP_SERVERS = "localhost:3181";
	private int runningTotal = 0;
	
	public ProduceOutput() {
		
	}
	
	protected void addValue(int valueToAdd) throws Exception {
		runningTotal += valueToAdd;
		
		//might have to call this after consumer loop in ConsumeInput
		runProducer();
	}

	/**
	 * Takes in an int value, creates a producer, adds the value to the producer.
	 * @param valueToAdd
	 * @throws Exception
	 */
	private void runProducer() throws Exception {
		final Producer<Long, String> producer = createProducer();
		
		try { //try to create a record and send the record to producer
			final ProducerRecord<Long, String> record = new ProducerRecord<>(TOPIC, Integer.toString(runningTotal));
			producer.send(record);
		} finally {
			producer.flush();
			producer.close();
		}
	}

	/**
	 * Creates a kafka producer.
	 * @return Producer
	 */
	private Producer<Long, String> createProducer() {
		Properties props = new Properties();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
		props.put(ProducerConfig.CLIENT_ID_CONFIG, "KafkaExampleProducer");
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class.getName());
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		return new KafkaProducer<>(props);
	}
}
