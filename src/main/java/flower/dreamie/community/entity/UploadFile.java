package flower.dreamie.community.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "upload_file")
public class UploadFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;  // 파일의 고유 식별자

    @Column(name = "file_name", nullable = false)
    private String fileName;

    @Column(name = "size", nullable = false)
    private long size;

    @Column(nullable = false)
    private LocalDateTime insert_date;

    // Community 엔터티와의 관계 (Many-to-One)
    @ManyToOne
    @JoinColumn(name = "community_id", nullable = false)  // 외래 키 설정
    private Community community;

    @PrePersist
    public void onCreate() {
        this.insert_date = LocalDateTime.now();
    }
}

