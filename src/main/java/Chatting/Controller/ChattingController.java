package Chatting.Controller;

import Chatting.Service.ChatRoomManager;
import Chatting.Entity.ChatRoom;
import Chatting.Entity.Message;
import Kafka.Sender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@Controller
public class ChattingController {
    @Autowired ChatRoomManager chatRoomManager;
    @Autowired Sender sender;
    Logger logger = LoggerFactory.getLogger(ChattingController.class);

    private static final String TOPIC = "chat";

    @PostMapping("/rooms")
    @ResponseBody
    public String addChatRoom(@RequestBody HashMap<String, String> map) {
        chatRoomManager.addChatRoom(map.get("roomName"));
        return "Success";
    }

    @MessageMapping("/chat")
    public void sendMessage(Message message) {
        logger.info(message.getContent());
        message.setMessageType(Message.MessageType.MESSAGE);
        sender.send(TOPIC, message);
    }

    @GetMapping("/rooms/{roomName}")
    @ResponseBody
    public List<ChatRoom> provideChatRoomList(@PathVariable("roomName") String roomName) {
        return chatRoomManager.getChatRooms(roomName);
    }

    @GetMapping("/home")
    public String chatHome() {
        return "static/home.html";
    }

    @GetMapping("/join/{roomId}")
    public String joinChatRoom(@PathVariable("roomId") String roomId) {
        return "static/chat.html";
    }
}
