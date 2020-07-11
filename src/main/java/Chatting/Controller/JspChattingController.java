package Chatting.Controller;

import Chatting.Service.ChatRoomManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/jsp")
public class JspChattingController {
    @Autowired ChatRoomManager chatRoomManager;

    @GetMapping("/rooms/{roomName}")
    public String addChatRoom(@PathVariable("roomName") String roomName, Model model) {
        model.addAttribute("list",chatRoomManager.getChatRooms(roomName));
        return "home.jsp";
    }
}
