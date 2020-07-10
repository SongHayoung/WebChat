package Chatting.Dao;

import Chatting.VO.ChatRoom;

import java.util.List;

public interface ChatRoomDao {
    void addChatRoom(ChatRoom chatRoom);
    boolean isDuplicatedRoomId(String roomId);
    void deleteChatRoom(String roomId);
    List<ChatRoom> getChatRooms(String roomName);
    ChatRoom getChatRoom(String roomId);
}
