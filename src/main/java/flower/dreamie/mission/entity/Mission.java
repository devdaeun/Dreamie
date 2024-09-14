package flower.dreamie.mission.entity;

import flower.dreamie.challenges.entity.Challenges;
import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "mission")
public class Mission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mission_id")
    private long mission_id;

    @ManyToOne
    @JoinColumn(name = "challenges_id", insertable = false, updatable = false)
    private Challenges challenge;

    @Column(nullable = false)
    private long challenges_id;

    @Column(name = "user_id", nullable = false)
    private long userId;

    public enum Succese{
        진행중,
        완료
    }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Succese succese = Succese.진행중;//기본값 설정하는법!

    @Column(nullable = true)
    private Date succese_at;


    public long getMission_id() {
        return mission_id;
    }

    public void setMission_id(long mission_id) {
        this.mission_id = mission_id;
    }

    public long getChallenges_id() {
        return challenges_id;
    }

    public void setChallanges_id(long challenges_id) {
        this.challenges_id = challenges_id;
    }

    public long getUser_id() {
        return userId;
    }

    public void setUser_id(long userId) {
        this.userId = userId;
    }

    public Succese getSuccese() {
        return succese;
    }

    public void setSuccese(Succese succese) {
        this.succese = succese;
    }

    public Date getSuccese_at() {
        return succese_at;
    }

    public void setSuccese_at(Date succese_at) {
        this.succese_at = succese_at;
    }

    public Challenges getChallenge() {
        return challenge;
    }

    public void setChallenge(Challenges challenge) {
        this.challenge = challenge;
    }

    public void setChallenges_id(long challenges_id) {
        this.challenges_id = challenges_id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
