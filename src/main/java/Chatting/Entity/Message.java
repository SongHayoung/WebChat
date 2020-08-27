package Chatting.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    private static final long serialVersionUID = 1L;
    private String content;
    private String sender;
    private String dateFormatted;
    private String chatRoomId;
    private MessageType messageType;

    public enum MessageType {
        JOIN, QUIT, MESSAGE
    }
}
