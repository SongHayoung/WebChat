package Chatting.Dao;

import Config.RootConfig;
import Config.StompWebSocketConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class, StompWebSocketConfig.class})
@Transactional
public class RedisTest {
    @Autowired
    private ChatRoomDaoRedis chatRoomDaoRedis;

    @Test
    public void getChatRoomWithMembers() {
        for(int i = 0; i < 10000; i++)
            chatRoomDaoRedis.getChatRoomsWithMembers("미국");
    }

    @Test
    public void getChatRoomWithCursor() {
        for(int i = 0; i < 10000; i++)
            chatRoomDaoRedis.getChatRooms("미국");
    }

    @Test
    public void getChatRoomWithMembers2() {
        for(int i = 0; i < 10000; i++)
            chatRoomDaoRedis.getChatRoomsWithMembers("미국");
    }

    @Test
    public void getChatRoomWithCursor2() {
        for(int i = 0; i < 10000; i++)
            chatRoomDaoRedis.getChatRooms("미국");
    }
}
