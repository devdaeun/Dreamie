package flower.dreamie.chat.controller;

import flower.dreamie.chat.entity.ChatMessage;
import flower.dreamie.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class StompChatController {
    private final SimpMessagingTemplate template;
    private final ChatService chatService;

    @MessageMapping(value = "/chat/enter")
    public void enter(ChatMessage message) {
        message.setMessage(message.getSender() + "님이 채팅방에 참여하였습니다.");
        template.convertAndSend("/sub/chat/room/" + message.getChat_room_id(), message);
    }

    @MessageMapping(value = "/chat/message")
    public void message(ChatMessage message) {
        // 모든 클라이언트에게 메시지 전송
        template.convertAndSend("/sub/chat/room/" + message.getChat_room_id(), message);
    }
}
