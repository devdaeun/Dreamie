package flower.dreamie.login.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Data
@Table
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long user_id;

    @Column(nullable = false, length = 10)
    private String name;

    @Column(nullable = false, length = 10)
    private String id;

    @Column(nullable = false, length = 20)
    private String password;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(nullable = false, length = 20)
    private String work;

    @Column(nullable = false)
    private Long mission = 0L;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @ColumnDefault("active")
    private ActivationStatus activation;

    public enum UserRole {
        naver,
        google,
        일반,
        관리자
    }

    public enum ActivationStatus {
        active,
        deleted
    }
}
