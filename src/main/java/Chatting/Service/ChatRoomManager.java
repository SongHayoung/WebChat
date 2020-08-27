package Chatting.Service;

import Chatting.Entity.ChatRoom;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Transactional
public interface ChatRoomManager {
    void addChatRoom(String roomName);
    void deleteChatRoom(ChatRoom chatRoom);
    void addUser(String roomID, String userName);
    void deleteUser(String roomID, String userName);
    Set<String> getUsers(String roomID);
    int getUserCount(String roomID);
    List<ChatRoom> getChatRooms(String roomName);
}
