package Chatting.VO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

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
}
