package flower.dreamie.motivation.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "motivation")
public class Motivation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int message_id;

    @Column(name = "content")
    private String content;

    @Column(name = "content_en")
    private String content_en;

    public int getMessage_id() {
        return message_id;
    }

    public void setMessage_id(int message_id) {
        this.message_id = message_id;
    }

    public String getContent() {  // 메서드 이름 수정
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent_en() {
        return content_en;
    }

    public void setContent_en(String content_en) {
        this.content_en = content_en;
    }

}
