package flower.dreamie.motivation.repository;

import flower.dreamie.motivation.entity.Motivation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MotivationRepository extends JpaRepository<Motivation, Integer> {

    @Query(value = "SELECT * FROM motivation ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Motivation findRandomMotivation(); // 랜덤 명언을 가져오는 메서드
}
