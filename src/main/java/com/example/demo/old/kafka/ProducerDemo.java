package com.example.demo.old.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * @author zhengyd
 * @mail zhengyd@yinhai.com
 * @date 2020-09-25
 * @time 9:53
 */
public class ProducerDemo {
    public static final String BROKER_LIST = "47.96.19.112:9092";
    public static final String TOPIC = "test";

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BROKER_LIST);
        properties.put(ProducerConfig.ACKS_CONFIG, "all");
        //构建所需要发送的消息
        ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC, "Hello Kafka!");
        //配置生产者客户端参数并创建KafkaProducer示例
        try (KafkaProducer<String, String> producer = new KafkaProducer<>(properties)) {
            //发送消息
            producer.send(record, (metadata, exception) -> {
                if (exception != null) {
                    exception.printStackTrace();
                } else {
                    System.out.println(metadata.topic() + "-" + metadata.partition() + ":" + metadata.offset());
                }
            });
        }
    }

}
