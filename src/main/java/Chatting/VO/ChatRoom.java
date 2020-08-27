package Chatting.VO;

import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Data
@Getter
@Setter
@AllArgsConstructor
public class ChatRoom implements Serializable {
    private static final long serialVersionUID = 1L;
    private String roomName;
    private String roomId;

    public ChatRoom(String roomName) {
        this.roomName = roomName;
        this.roomId = UUID.randomUUID().toString();
    }
}
