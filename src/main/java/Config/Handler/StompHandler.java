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

    private static final String TOPIC = "send";

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);

        if(accessor.getCommand() == StompCommand.CONNECT) {
            jwtTokenProvider.validateToken(accessor.getFirstNativeHeader("jwtToken"));
        } else if(accessor.getCommand() == StompCommand.SUBSCRIBE) {
            System.out.println("full message : " + message);
            String userName = (String) accessor.getFirstNativeHeader("simpUser");
            String roomID = (String) message.getHeaders().get("simpDestination");
            chatRoomManager.addUser(roomID, userName);
            sender.send(TOPIC, Chatting.VO.Message.builder().chatRoomId(roomID.split("/")[2]).content(userName + "입장").sender("ADMIN").build());
        } else if(accessor.getCommand() == StompCommand.DISCONNECT) {
            String userName = (String) accessor.getFirstNativeHeader("simpUser");
            String roomID = (String) message.getHeaders().get("simpDestination");
            chatRoomManager.deleteUser(roomID, userName);
            sender.send(TOPIC, Chatting.VO.Message.builder().chatRoomId(roomID.split("/")[2]).content(userName + "퇴장").sender("ADMIN").build());
        }
        return message;
    }
}
