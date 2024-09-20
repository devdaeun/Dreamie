package flower.dreamie.notice.repository;

import flower.dreamie.notice.entity.NoticeList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<NoticeList, Long> {
//기본적인 crud(create, read, update, delete) 기능이 자동으로 제공됨
// 필요한 추가적인 쿼리 메서드가 있으면 여기서 작성 가능
}
//오잉 다시 커밋


