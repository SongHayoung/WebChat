package Chatting.Dao;

import Chatting.VO.ChatRoom;

import java.util.List;

public interface ChatRoomDao {
    void addChatRoom(ChatRoom chatRoom);
    void deleteChatRoom(ChatRoom chatRoom);
    List<ChatRoom> getChatRooms(String roomName);
}
