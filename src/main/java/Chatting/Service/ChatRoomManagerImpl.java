package Chatting.Service;

import Chatting.Dao.ChatRoomDao;
import Chatting.VO.ChatRoom;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class ChatRoomManagerImpl implements ChatRoomManager {
    @Autowired
    @Qualifier("chatRoomDaoRedis")
    ChatRoomDao chatRoomDao;

    public void addChatRoom(String roomName) {
        chatRoomDao.addChatRoom(ChatRoom.create(roomName));
    }

    public void deleteChatRoom(ChatRoom chatRoom) {
        chatRoomDao.deleteChatRoom(chatRoom);
    }

    public List<ChatRoom> getChatRooms(String roomName) {
        return chatRoomDao.getChatRooms(roomName);
    }

    public void addUser(String roomID, String userName) {
        chatRoomDao.increaseUserCount(roomID);
        chatRoomDao.setUser(roomID, userName);
    }

    public void deleteUser(String roomID, String userName) {
        chatRoomDao.decreaseUserCount(roomID);
        chatRoomDao.deleteUser(roomID, userName);
    }

    public Set<String> getUsers(String roomID) {
        return chatRoomDao.getUsers(roomID);
    }

    public int getUserCount(String roomID) {
        return chatRoomDao.getUserCount(roomID);
    }
}
