package Chatting.Dao;

import Chatting.VO.ChatRoom;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Repository
public class ChatRoomDaoRedis implements ChatRoomDao{
    @Resource(name = "redisTemplate")
    private HashOperations<String, String, ChatRoom> opsHashChatRoom;
    @Resource(name = "redisTemplate")
    private ValueOperations<String, String> valueOperations;
    @Resource(name = "redisTemplate")
    private SetOperations<String, String> setOperations;
    static private final String CHATROOM = "CHATROOM:";
    static private final String USER_COUNT = "USERCOUNT:";
    static private final String USER_LIST = "USERLIST:";

    public void addChatRoom(ChatRoom chatRoom) {
        opsHashChatRoom.put(CHATROOM + chatRoom.getRoomName(), chatRoom.getRoomId(), chatRoom);
    }

    public void deleteChatRoom(ChatRoom chatRoom) {
        opsHashChatRoom.delete(CHATROOM + chatRoom.getRoomName(), chatRoom.getRoomId());
    }

    public List<ChatRoom> getChatRooms(String roomName) {
        return new ArrayList<>(opsHashChatRoom.entries(CHATROOM + roomName).values());
    }

    public Set<String> getUsers(String roomID) {
        return setOperations.members(USER_LIST + roomID);
    }

    public void setUser(String roomID, String userName) {
        setOperations.add(USER_LIST + roomID, userName);
    }

    public void deleteUser(String roomID, String userName) {
        setOperations.remove(USER_LIST + roomID, userName);
    }

    public int getUserCount(String roomID) {
        return Integer.parseInt(valueOperations.get(USER_COUNT + roomID));
    }

    public void increaseUserCount(String roomID) {
        valueOperations.increment(USER_COUNT + roomID);
    }

    public void decreaseUserCount(String roomID) {
        valueOperations.decrement(USER_COUNT + roomID);
    }
}