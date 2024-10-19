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
    @Column(name = "upload_file_id")
    private Long upload_file_id;

    @Column(name = "file_name", nullable = false)
    private String fileName;

    @Column(name = "size", nullable = false)
    private long size;

    @Column(name = "insert_date", nullable = false)
    private LocalDateTime insert_date;

    @Column(name = "file_path", nullable = false)
    private String filePath;

    @PrePersist
    public void onCreate() {
        this.insert_date = LocalDateTime.now();
    }

    @ManyToOne
    @JoinColumn(name = "community_id", nullable = false)
    private Community community;

    public void updateFileDetails(String newFileName, String newFilePath) {
        this.fileName = newFileName;
        this.filePath = newFilePath;
    }
}
