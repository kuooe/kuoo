package kr.kuooe.Vopet.user.service.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import kr.kuooe.Vopet.user.service.vo.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserId(String userId);

    boolean existsByUserId(String userId);

    boolean existsByUserMail(String userMail);
    
    // 추가적인 쿼리나 기능이 필요하다면 여기에 추가할 수 있습니다.
}