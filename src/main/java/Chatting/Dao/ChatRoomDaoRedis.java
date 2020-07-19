package Chatting.Dao;

import Chatting.VO.ChatRoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ChatRoomDaoRedis implements ChatRoomDao{
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    private HashOperations<String, String, ChatRoom> opsHashChatRoom;
    static private final String CHATROOM = "CHATROOM:";

    @PostConstruct
    private void init() {
        opsHashChatRoom = redisTemplate.opsForHash();
    }

    public void addChatRoom(ChatRoom chatRoom) {
        opsHashChatRoom.put(CHATROOM + chatRoom.getRoomName(), chatRoom.getRoomId(), chatRoom);
    }

    public void deleteChatRoom(ChatRoom chatRoom) {
        opsHashChatRoom.delete(CHATROOM + chatRoom.getRoomName(), chatRoom.getRoomId());
    }

    public List<ChatRoom> getChatRooms(String roomName) {
        return new ArrayList<>(opsHashChatRoom.entries(CHATROOM + roomName).values());
    }
}
