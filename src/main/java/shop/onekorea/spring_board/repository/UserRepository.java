package shop.onekorea.spring_board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shop.onekorea.spring_board.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> { // 여기서 "UserEntity"는 엔터티명.

    // 2023.07.28 Conclusion. 여기서 특히 주의할 사항은, "메소드 명"을 아무렇게나 사용해서는 안 된다는 것이다. 에러 발생 함
    // public boolean isExistedByEmailAndPassword(String email, String password); // 이런 "메소드 명"은 에러 나네

    // 2023.07.28 Conclusion. getBy~, findBy~, existsBy~ 속도는 어는 것이 빠를까?
    // https://velog.io/@_koiil/JPA-existById-vs.-getById-vs.-findById
    // 1. 존재 여부를 확인할 때는 existBy
    // 2. Id 값만 사용할 엔티티가 필요한 경우에는 getBy => 그런데, 아래처럼 [password]로 getByPassword를 사용하면, 받아오질 못하네.
    // 3. Id 이외의 데이터도 사용하는 엔티티의 경우에는 findBy를 사용하면 되겠습니다! => 그런데, 아래처럼 [password]로 findByPassword를 사용하면, 받아오질 못하네.

    public boolean existsByEmailAndPassword(String email, String password); // OK
//    public boolean findByEmailAndPassword(String email, String password); // NG
//    public boolean getByPassword(String password); // NG
    public boolean existsByPassword(String password); // OK
//    public boolean findByPassword(String password); // NG

}
