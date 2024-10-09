package flower.dreamie.chat.repository;

import flower.dreamie.chat.entity.ChatMessage;
import flower.dreamie.chat.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("chatMessageRepository")
public interface ChatMessageRepository extends JpaRepository<ChatMessage, String> {
}
