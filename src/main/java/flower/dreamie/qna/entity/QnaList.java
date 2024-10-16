package flower.dreamie.qna.entity;

import flower.dreamie.login.entity.User;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "question")
public class QnaList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //기본 키 자동 생성(MySQL AUTO_INCREMENT에 대응)

    @Column(nullable = false)
    private Long question_id;

//    @Column(nullable = false)
//    private Long user_id;

    @Column(nullable = false, length = 20)
    private String title;

    @Column(nullable = false, length = 200)
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ShowType show_type;

    @Column(nullable = false)
    private LocalDateTime write_at;

    @Column(nullable = true)
    private LocalDateTime edit_at;

    // 포맷된 날짜를 저장할 임시 필드 (DB에는 저장되지 않음)
    @Transient
    private String formattedWriteAt;

    // 글 작성 시 자동으로 작성일과 수정일을 현재 시각으로 설정
    @PrePersist
    public void onCreate() {
        this.write_at = LocalDateTime.now();
        this.edit_at = LocalDateTime.now();
    }

    // 글 수정 시 수정일을 현재 시각으로 업데이트
    @PreUpdate
    public void onUpdate() {
        this.edit_at = LocalDateTime.now();
    }

    public enum ShowType {
        True,
        False
    }

    @ManyToOne  //현재 엔티티와 다른 엔티티들을 연결
    @JoinColumn(name = "user_id", nullable = false) // 외래 키 user_id로 User와 연결
    private User user;
}
