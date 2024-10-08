package flower.dreamie.community.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "community")
public class Community {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(nullable = false)
    private Long community_id;

    @Column(nullable = false)
    private Long user_id;

    @Column(nullable = false, length = 20)
    private String title;

    @Column(nullable = false, length = 200)
    private String content;

    @Column(nullable = false)
    private LocalDateTime write_at;

    @Column(nullable = true)
    private LocalDateTime edit_at;

    // UploadFile과의 관계 설정
    @OneToOne(cascade = CascadeType.ALL) // 연관된 UploadFile이 삭제될 때 함께 삭제되도록 설정
    @JoinColumn(name = "upload_file_id") // 외래 키 설정
    private UploadFile uploadFile; // UploadFile 객체를 참조하는 필드 추가

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
