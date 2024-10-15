package flower.dreamie.community.entity;

import flower.dreamie.login.entity.User;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "Comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long comment_id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;  // user와의 연관관계

    @ManyToOne
    @JoinColumn(name = "community_id", nullable = false)
    private Community community; // community와의 연관관계 (필드명 수정)

    @Column(nullable = false, length = 150)
    private String content;  // 댓글 내용

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime created_at;  // 생성 시간 자동 설정

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updated_at;

}
