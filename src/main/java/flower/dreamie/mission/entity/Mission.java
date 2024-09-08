package flower.dreamie.mission.entity;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "mission")
public class Mission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mission_id")
    private long mission_id;

    @Column(nullable = false)
    private long challanges_id;

    @Column(name = "user_id", nullable = false)
    private long userId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Succese succese;

    public enum Succese{
        진행중,
        완료
    }

    @Column(nullable = false)
    private Date succese_at;

    public long getMission_id() {
        return mission_id;
    }

    public void setMission_id(long mission_id) {
        this.mission_id = mission_id;
    }

    public long getChallanges_id() {
        return challanges_id;
    }

    public void setChallanges_id(long challanges_id) {
        this.challanges_id = challanges_id;
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
}
