package Chatting.Service;

import Chatting.VO.ChatRoom;

import java.util.List;

public interface ChatRoomManager {
    void addChatRoom(String roomName);
    void deleteChatRoom(String roomId);
    List<ChatRoom> getChatRooms(String roomName);
}
