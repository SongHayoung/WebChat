package Config.Handler;

import Chatting.Jwt.JwtTokenProvider;
import Chatting.Service.ChatRoomManager;
import Kafka.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;

@Component
public class StompHandler implements ChannelInterceptor {
    @Autowired
    private ChatRoomManager chatRoomManager;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    Sender sender;

    private static final String TOPIC = "chat";

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);

        if(accessor.getCommand() == StompCommand.CONNECT) {
            jwtTokenProvider.validateToken(accessor.getFirstNativeHeader("jwtToken"));
        } else if(accessor.getCommand() == StompCommand.SUBSCRIBE) {
            String userName = accessor.getFirstNativeHeader("simpUser");
            String roomID = accessor.getFirstNativeHeader("topic");
            chatRoomManager.addUser(roomID, userName);
            sender.send(TOPIC, Chatting.Entity.Message.builder()
                    .messageType(Chatting.Entity.Message.MessageType.JOIN)
                    .chatRoomId(roomID).content(userName + "님이 입장하셨습니다.").sender("ADMIN").build());
        } else if(accessor.getCommand() == StompCommand.DISCONNECT) {
            String userName = accessor.getFirstNativeHeader("simpUser");
            String roomID = accessor.getFirstNativeHeader("topic");
            chatRoomManager.deleteUser(roomID, userName);
            sender.send(TOPIC, Chatting.Entity.Message.builder()
                    .messageType(Chatting.Entity.Message.MessageType.QUIT)
                    .chatRoomId(roomID).content(userName + "님이 퇴장하셨습니다.").sender("ADMIN").build());
        }
        return message;
    }
}
