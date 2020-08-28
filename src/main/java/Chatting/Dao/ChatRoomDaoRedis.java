package Chatting.Dao;

import Chatting.Entity.ChatRoom;
import io.lettuce.core.api.sync.RedisSetCommands;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class ChatRoomDaoRedis implements ChatRoomDao{
    @Resource(name = "redisTemplate")
    private ValueOperations<String, String> valueOperations;
    @Resource(name = "redisTemplate")
    private SetOperations<String, String> setOperations;
    @Autowired
    private RedisTemplate redisTemplate;

    static private final String CHATROOM = "CHATROOM:";
    static private final String USER_COUNT = "USERCOUNT:";
    static private final String USER_LIST = "USERLIST:";
    static private final String CHATS = "CHATS";
    static private final byte[] CHATS_BYTE = CHATS.getBytes();

    public void addChatRoom(ChatRoom chatRoom) {
        setOperations.add(CHATS, chatRoom.getRoomName());
        setOperations.add(CHATROOM + chatRoom.getRoomName(), chatRoom.getRoomId());
    }

    public void deleteChatRoom(ChatRoom chatRoom) {
        setOperations.remove(CHATROOM + chatRoom.getRoomName(), chatRoom.getRoomId());
    }

    public List<ChatRoom> getChatRoomsWithMembers(String roomName) {
        Set<String> keys = getChatRoomNameMatches(roomName);
        LinkedList<ChatRoom> chatRooms = new LinkedList<>();
        for(String key : keys)
            chatRooms.addAll(getChatSpecificChatRoom(key));
        return chatRooms;
    }

    private List<ChatRoom> getChatSpecificChatRoom(String roomName) {
        return setOperations.members(CHATROOM + roomName).stream()
                .map(roomId -> new ChatRoom(roomName, roomId))
                .collect(Collectors.toList());
    }

    public List<ChatRoom> getChatRooms(String roomName) {
        Set<String> keys = getChatRoomNameMatches(roomName);
        List<ChatRoom> chatRooms = new LinkedList<>();
        for(String key : keys)
            chatRooms.addAll(getChatSpecificChatRoomCursor(key));
        return chatRooms;
    }

    private Set<ChatRoom> getChatSpecificChatRoomCursor(String roomName) {
        Cursor<String> cursor = setOperations.scan(CHATROOM + roomName, ScanOptions.scanOptions().match("*").build());
        Set<ChatRoom> chatRoomList = new HashSet<>();
        while(cursor.hasNext()) {
            chatRoomList.add(new ChatRoom(roomName, cursor.next()));
        }
        return chatRoomList;
    }

    @SuppressWarnings("unchecked")
    private Set<String> getChatRoomNameMatches(String roomName) {
       return (Set<String>) redisTemplate.execute(new RedisCallback<Set<String>>() {
           @Override
           public Set<String> doInRedis(RedisConnection redisConnection) {
               Set<String> keysTmp = new HashSet<>();
               Cursor<byte[]> cursor = redisConnection.sScan(CHATS_BYTE, ScanOptions.scanOptions().match("*" + roomName + "*").build());
               while(cursor.hasNext()) {
                   StringBuffer stringBuffer = new StringBuffer(new String(cursor.next(), StandardCharsets.UTF_8));
                   stringBuffer.deleteCharAt(0);
                   stringBuffer.deleteCharAt(stringBuffer.length() - 1);
                   keysTmp.add(stringBuffer.toString());
               }
               return keysTmp;
           }
        });
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
