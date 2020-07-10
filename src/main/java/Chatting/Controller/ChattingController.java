package Chatting.Controller;

import Chatting.Service.ChatRoomManager;
import Chatting.VO.ChatRoom;
import Chatting.VO.Message;
import Kafka.Receiver;
import Kafka.Sender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class ChattingController {
    @Autowired ChatRoomManager chatRoomManager;
    @Autowired Sender sender;
    @Autowired Receiver receiver;
    Logger logger = LoggerFactory.getLogger(ChattingController.class);

    private static final String TOPIC = "send";

    @PostMapping("/rooms")
    public String addChatRoom(@RequestBody HashMap<String, String> map) {
        chatRoomManager.addChatRoom(map.get("roomName"));
        return "Success";
    }

    @MessageMapping("/chat")
    public void sendMessage(Message message) throws Exception {
        logger.info(message.getContent());
        sender.send(TOPIC, message);
    }

    @GetMapping("/rooms/{roomName}")
    public List<ChatRoom> provideChatRoomList(@PathVariable("roomName") String roomName) {
        System.out.println(roomName);
        return chatRoomManager.getChatRooms(roomName);
    }
}
