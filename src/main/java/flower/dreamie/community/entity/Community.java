package flower.dreamie.community.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @Transient
    private String formattedWriteAt; // 데이터베이스에 저장되지 않음

    // UploadFile과의 관계: UploadFile이 연관관계의 주인
    @OneToMany(mappedBy = "community", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<UploadFile>  uploadFile = new ArrayList<>();

    // Comment과의 관계
    @OneToMany(mappedBy = "community", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

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

    @Override
    public String toString() {
        return "Community{" +
                "community_id=" + community_id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", write_at=" + write_at +
                '}';
    }
}
