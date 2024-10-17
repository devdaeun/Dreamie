package flower.dreamie.chat.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.socket.WebSocketSession;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "chat_rooms")
public class ChatRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private long roomId;

    @Column(nullable = false)
    private String room_name;


    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt; // 생성일 추가

    @Column(name = "user_id", nullable = false)
    private long userId;

    // 추가한 생성자
    public ChatRoom(long roomId) {
        this.roomId = roomId;
    }

}
