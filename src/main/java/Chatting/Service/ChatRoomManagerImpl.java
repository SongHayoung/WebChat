package Chatting.Service;

import Chatting.Dao.ChatRoomDao;
import Chatting.VO.ChatRoom;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@RequiredArgsConstructor
@Service
public class ChatRoomManagerImpl implements ChatRoomManager {
    @Autowired ChatRoomDao chatRoomDao;

    public void addChatRoom(String roomName) {
        chatRoomDao.addChatRoom(ChatRoom.create(roomName));
    }

    public void deleteChatRoom(String roomId) {
        chatRoomDao.deleteChatRoom(roomId);
    }

    public List<ChatRoom> getChatRooms(String roomName) {
        return chatRoomDao.getChatRooms(roomName);
    }
}
