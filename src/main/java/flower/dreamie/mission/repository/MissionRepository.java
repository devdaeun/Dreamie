package flower.dreamie.mission.repository;

import flower.dreamie.mission.entity.Mission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MissionRepository extends JpaRepository<Mission, Long> {
    @Query("SELECT m FROM Mission m LEFT JOIN Challenges c ON m.challenges_id = c.challengesId WHERE m.userId = :userId")
    List<Mission> findByUserChallenge(@Param("userId") long userId);
}
