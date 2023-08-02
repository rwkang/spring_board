package shop.onekorea.spring_board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shop.onekorea.spring_board.entity.BoardEntity;

import java.util.Date;
import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Integer> {

    public List<BoardEntity> findTop3ByCreatedAfterOrderByThumbsUpCountDesc(Date boardCreated); // DESC: 역순으로 나오게.
    // => SELECT * FROM board ORDER BY thumbs_up_count DESC;
    // public List<BoardEntity> findTop3ByBoardWriteDateAfterOrderByLikesCntDesc(Date boardCreated); // 14강
    // => SELECT * FROM board ORDER BY board_write_date DESC;

    public List<BoardEntity> findByOrderByCreatedDesc();
    // => SELECT * FROM board ORDER BY created DESC;
    // public List<BoardEntity> findByOrderByBoardWriteDateDesc(); // 14강
    // => SELECT * FROM board ORDER BY board_write_date DESC;

    public List<BoardEntity> findByTitleContains(String title);
    // => SELECT * FROM board WHERE title LIKE '%?%';
    //public List<BoardEntity> findByBoardTitleContains(String title); // 15강
    // => SELECT * FROM board WHERE board_title LIKE '%?%';


}
