package Chatting.Entity;

import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@RequiredArgsConstructor
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

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof ChatRoom))
            return false;
        ChatRoom chatRoom = (ChatRoom)obj;
        return chatRoom.roomId.equals(this.roomId);
    }

    @Override
    public int hashCode() {
        return roomId.hashCode() * 31 + roomName.hashCode();
    }
}
