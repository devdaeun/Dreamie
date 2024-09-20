package flower.dreamie.notice.entity;

import flower.dreamie.login.entity.User;
import flower.dreamie.qna.entity.QnaList;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "notice")
public class NoticeList{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long notice_id;

//    @Column(nullable = false)
//    private Long user_id;

    @Column(length = 20, nullable = false)
    private String title;

    @Column(length = 200, nullable = false)
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ShowType show_type;

    @Column(nullable = false)
    private LocalDateTime write_at;

    @Column(nullable = false)
    private LocalDateTime edit_at;

    @PrePersist
    public void onCreate() {
        this.write_at = LocalDateTime.now();
        this.edit_at = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        this.edit_at = LocalDateTime.now();
    }

    public enum ShowType {
        True,
        False
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
