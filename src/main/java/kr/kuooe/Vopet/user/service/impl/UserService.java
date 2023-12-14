package kr.kuooe.Vopet.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.kuooe.Vopet.user.service.vo.User;

import java.util.Date;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(User user) {
        // 여기에서 유효성 검사 등을 수행할 수 있습니다.
        user.setUserRegDate(new Date()); // 등록일자 설정
        return userRepository.save(user);
    }

    public boolean isUserIdExists(String userId) {
        // 아이디 중복 체크 로직
        // 이미 등록된 아이디인 경우 true 반환
        // 그렇지 않은 경우 false 반환
        return userRepository.existsByUserId(userId);
    }

    public boolean isUserMailExists(String userMail) {
        // 이메일 중복 체크 로직
        // 이미 등록된 이메일인 경우 true 반환
        // 그렇지 않은 경우 false 반환
        return userRepository.existsByUserMail(userMail);
    }
    
    public boolean authenticateUser(String userId, String password) {
        // 실제 사용자 인증 로직을 구현합니다.
        // 여기에서는 간단하게 userId와 password가 일치하는 경우 로그인 성공으로 가정합니다.
        User user = userRepository.findByUserId(userId);
        return user != null && user.getUserPw().equals(password);
    }
    public User getUserByUserId(String userId) {
        return userRepository.findByUserId(userId);
    }
}