# kafkaAddition
 
This application allows you to PUT an integer to the following URL: http://localhost:8080/calculator/add shown in the following example: http://localhost:8080/calculator/add?value=8. This will add a value of 8 to the current running kafka total.

Follow these steps to get started:
1. Download kafka and go into your kafka directory.
2. Start a ZooKeeper server:
	> bin/zookeeper-server-start.sh config/zookeeper.properties
3. Start the Kafka server:
	> bin/kafka-server-start.sh config/server.properties
4. Create a topic called “input”:
	> bin/kafka-topics.sh --create --zookeeper localhost:9092 --replication-factor 1 --partitions 1 --topic input
5. Create a topic called “output”:
	> bin/kafka-topics.sh --create --zookeeper localhost:9092 --replication-factor 1 --partitions 1 --topic output
6. Pass a value to the "input" topic via http://localhost:8080/calculator/add?value=x
7. Get the running total from the "output" topic via http://localhost:8080/calculator/total
