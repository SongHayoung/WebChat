package Kafka;

import Chatting.VO.Message;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class Receiver {
    private static final Logger logger = LoggerFactory.getLogger(Receiver.class);

    @Autowired
    SimpMessagingTemplate template;

    @KafkaListener(id = "main-listener", topics = "send")
    public void receive(Message message) throws Exception {
        logger.info("message='{}'",message);
        HashMap<String, String> msg = new HashMap<>();
        msg.put("timestamp", message.getDateFormatted());
        msg.put("content", message.getContent());
        msg.put("sender", message.getSender());

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(msg);

        this.template.convertAndSend("/topic/" + message.getChatRoomId(), json);
    }
}
