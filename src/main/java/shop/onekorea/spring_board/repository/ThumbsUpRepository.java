package shop.onekorea.spring_board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shop.onekorea.spring_board.entity.ThumbsUpEntity;

@Repository
public interface ThumbsUpRepository extends JpaRepository<ThumbsUpEntity, Integer> {
}
