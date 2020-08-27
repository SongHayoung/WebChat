package Chatting.Dao;

import Chatting.Entity.ChatRoom;

import java.util.List;
import java.util.Set;

public interface ChatRoomDao {
    void addChatRoom(ChatRoom chatRoom);
    void deleteChatRoom(ChatRoom chatRoom);
    List<ChatRoom> getChatRooms(String roomName);
    Set<String> getUsers(String roomID);
    void setUser(String roomID, String userName);
    void deleteUser(String roomID, String userName);
    int getUserCount(String roomID);
    void increaseUserCount(String roomID);
    void decreaseUserCount(String roomID);
}
