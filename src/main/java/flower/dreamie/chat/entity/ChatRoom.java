package flower.dreamie.chat.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.socket.WebSocketSession;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "chat_rooms")
public class ChatRoom {

    @Id
    private String room_id;

    @Column(nullable = false)
    private String user1;

    @Column(nullable = false)
    private String user2;

    public static ChatRoom create(String user1, String user2) {
        ChatRoom room = new ChatRoom();
        room.room_id = UUID.randomUUID().toString();
        room.user1 = user1;
        room.user2 = user2;
        return room;
    }
}
