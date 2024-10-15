package flower.dreamie.community.repository;

import flower.dreamie.community.entity.Comment;
import flower.dreamie.community.entity.Community; // Community를 임포트해야 합니다.
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByCommunity(Community community); // Community 객체를 기준으로 조회
}
