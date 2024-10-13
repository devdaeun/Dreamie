package flower.dreamie.chat.service;

import flower.dreamie.chat.entity.ChatMessage;
import flower.dreamie.chat.entity.ChatRoom;
import flower.dreamie.chat.repository.ChatMessageRepository;
import flower.dreamie.chat.repository.ChatRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {
    @Autowired
    private ChatRoomRepository chatRoomRepository;

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    public ChatRoom createChatRoom(ChatRoom chatRoom) {
        return chatRoomRepository.save(chatRoom);
    }

    public ChatRoom getChatRoomById(long room_id) {
        return chatRoomRepository.findById(String.valueOf(room_id)).orElse(null);
    }

    public List<ChatRoom> getAllRooms() {
        return chatRoomRepository.findAllByOrderByCreatedAtDesc(); // 생성일 기준으로 정렬된 채팅방 리스트
    }

    public void RemoveRooms(String roomId){
        chatRoomRepository.deleteById(roomId);
    }

    public ChatMessage saveChatMessage(ChatMessage message){
        return chatMessageRepository.save(message);
    }
}
