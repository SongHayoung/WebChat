package Chatting.VO;

import lombok.Builder;
import lombok.Data;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Data
@Builder
public class Message {
    private static final long serialVersionUID = 1L;
    private static final String DATE_FORMAT = "MMM dd yyyy HH:mm:ss";

    @Builder.Default
    private DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
    private String content;
    private String sender;
    private String dateFormatted;
    private String chatRoomId;
}
