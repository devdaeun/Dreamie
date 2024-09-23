package flower.dreamie.challenges.entity;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "challenges")
public class Challenges {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "challenges_id")
    private long challengesId;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, length = 200)
    private String content;

    @Column(nullable = false)
    private Date start_at;

    @Column(nullable = false)
    private Date end_at;

    public Date getStart_at() {
        return start_at;
    }

    public void setStart_at(Date start_at) {
        this.start_at = start_at;
    }

    public Date getEnd_at() {
        return end_at;
    }

    public void setEnd_at(Date end_at) {
        this.end_at = end_at;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getChallenges_id() {
        return challengesId;
    }

    public void setChallenges_id(long challengesId) {
        this.challengesId = challengesId;
    }
}
