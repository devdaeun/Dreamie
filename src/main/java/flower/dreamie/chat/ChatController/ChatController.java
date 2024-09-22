package flower.dreamie.chat.ChatController;

import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@Log4j2
public class ChatController {

    @GetMapping("/chat")
    public String chatGET(HttpSession session, Model model) {
        String id = (String) session.getAttribute("id");
        model.addAttribute("id", id);
        return "chat/chat";
    }
}

