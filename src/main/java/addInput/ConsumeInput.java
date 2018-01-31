package addInput;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Collections;
import java.util.Properties;

public class ConsumeInput {

    private final static String TOPIC = "input";
    private final static String INPUT_SERVERS = "localhost:2181";

    static void consumeInput() throws InterruptedException {
        final Consumer<Long, String> consumer = createConsumer();
        ProduceOutput op = new ProduceOutput();
        final int giveUp = 100;   
        int noRecordsCount = 0;

        while (true) {
            final ConsumerRecords<Long, String> consumerRecords =
                    consumer.poll(1000);

            if (consumerRecords.count()==0) {
                noRecordsCount++;
                if (noRecordsCount > giveUp) break;
                else continue;
            }

            consumerRecords.forEach(record -> {
                try {
					op.addValue(Integer.parseInt(record.value()));
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            });

            consumer.commitAsync();
        }
        consumer.close();
    }
    
    private static Consumer<Long, String> createConsumer() {
        final Properties props = new Properties();
        		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, INPUT_SERVERS);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "KafkaExampleConsumer");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, LongDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        // Create the consumer using props.
        final Consumer<Long, String> consumer = new KafkaConsumer<>(props);

        // Subscribe to the topic.
        consumer.subscribe(Collections.singletonList(TOPIC));
        
        return consumer;
    }
}
