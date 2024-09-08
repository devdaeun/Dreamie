package flower.dreamie.notice.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "notice")
public class Notice{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notice_id;

    @Column(nullable = false)
    private Long user_id;

    @Column(length = 20, nullable = false)
    private String title;

    @Column(length = 200, nullable = false)
    private String content;

    @Column(nullable = false)
    private LocalDateTime writeDate;

    @Column(nullable = false)
    private LocalDateTime editDate;

    // Getters and Setters

    public Long getNotice_id() {
        return notice_id;
    }

    public void setNotice_id(Long notice_id) {
        this.notice_id = notice_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getWriteDate() {
        return writeDate;
    }

    public void setWriteDate(LocalDateTime writeDate) {
        this.writeDate = writeDate;
    }

    public LocalDateTime getEditDate() {
        return editDate;
    }

    public void setEditDate(LocalDateTime editDate) {
        this.editDate = editDate;
    }
}