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
    @Column(name = "upload_file_id")  // 기본 키를 upload_file_id로 설정
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
    @JoinColumn(name = "community_id", nullable = false)  // 외래 키로 사용
    private Community community;

    @Override
    public String toString() {
        return "UploadFile{" +
                "fileName='" + fileName + '\'' +
                ", size=" + size +
                ", insert_date=" + insert_date +
                '}';
    }
    public void updateFileDetails(String newFileName, String newFilePath) {
        this.fileName = newFileName;
        this.filePath = newFilePath;
    }


}
