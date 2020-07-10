package Chatting.Service;

import Chatting.Dao.ChatRoomDao;
import Chatting.VO.ChatRoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class ChatRoomManagerImpl implements ChatRoomManager {
    @Autowired ChatRoomDao chatRoomDao;
    public String addChatRoom(String roomName) {
        String roomId;
        do {
            roomId = createUniqueRoomId();
        }while(chatRoomDao.isDuplicatedRoomId(roomId));

        chatRoomDao.addChatRoom(ChatRoom.builder().roomId(roomId).roomName(roomName).build());

        return roomId;
    }

    public void deleteChatRoom(String roomId) {
        chatRoomDao.deleteChatRoom(roomId);
    }

    public List<ChatRoom> getChatRooms(String roomName) {
        return chatRoomDao.getChatRooms(roomName);
    }

    private String createUniqueRoomId() {
        int leftLimit = 97; // 'a' 부터
        int rightLimit = 122; // 'z' 까지
        int targetStringLength = 5;
        int targetIntLength = 5;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        for (int i = 0; i < targetIntLength; i++) {
            buffer.append(((int) (Math.random() * 10)));
        }

        return buffer.toString();
    }
}
