package Chatting.Dao;

import Chatting.VO.ChatRoom;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ChatRoomDaoJdbc implements ChatRoomDao{
    @Autowired
    @Qualifier("sqlSession")
    private SqlSession sqlSession;

    private static final int DUPLICATE_KEY = 1;

    public void addChatRoom(ChatRoom chatRoom) {
        sqlSession.insert("insertChatRoom",chatRoom);
    }

    public boolean isDuplicatedRoomId(String roomId) {
        int exists = sqlSession.selectOne("isDuplicatedRoomId", roomId);
        return exists == DUPLICATE_KEY;
    }

    public void deleteChatRoom(ChatRoom chatRoom) {
        sqlSession.delete("deleteChatRoom", chatRoom.getRoomId());
    }

    public List<ChatRoom> getChatRooms(String roomName) {
        return sqlSession.selectList("getChatRooms", roomName);
    }

    public ChatRoom getChatRoom(String roomId) {
        return sqlSession.selectOne("getChatRoom", roomId);
    }
}
