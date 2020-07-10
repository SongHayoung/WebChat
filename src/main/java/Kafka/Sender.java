package Kafka;

import Chatting.VO.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Sender {
    private static final Logger logger = LoggerFactory.getLogger(Sender.class);

    @Autowired
    private KafkaTemplate<String, Message> kafkaTemplate;

    public void send(String topic, Message data) {
        logger.info("sending data='{}' to topic='{}'", data, topic);
        kafkaTemplate.send(topic, data);
    }
}
