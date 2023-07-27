package shop.onekorea.spring_board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shop.onekorea.spring_board.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> { // 여기서 "UserEntity"는 엔터티명.
    public boolean isExistsByEmailAndPassword(String email, String password);

}
