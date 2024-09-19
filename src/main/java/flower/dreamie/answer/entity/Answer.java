package flower.dreamie.answer.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "answer")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(nullable = false)
    private Long answer_id;

    @Column(name = "question_id", nullable = false)
    private Long question_id;

    @Column(nullable = false)
    private Long user_id;

    @Column(nullable = false, length = 200)
    private String content;

    @Column(nullable = false)
    private LocalDateTime write_at;

    @Column(nullable = true)
    private LocalDateTime edit_at;

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
}
