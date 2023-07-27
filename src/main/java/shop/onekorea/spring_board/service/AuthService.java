package shop.onekorea.spring_board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import shop.onekorea.spring_board.dto.ResponseDto;
import shop.onekorea.spring_board.dto.SignInRequestDto;
import shop.onekorea.spring_board.dto.SignInResponseDto;
import shop.onekorea.spring_board.dto.SignUpRequestDto;
import shop.onekorea.spring_board.entity.UserEntity;
import shop.onekorea.spring_board.repository.UserRepository;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    public ResponseDto<?> sighUpDto(SignUpRequestDto requestDto) {
        String email = requestDto.getEmail();
        String password = requestDto.getPassword();
        String passwordConfirmation = requestDto.getPasswordConfirmation();

        // email 중복 확인
        // 단, "Repository"는 반드시 "try ~ catch" 구문으로 사용해야 한다.
        try {
            if (userRepository.existsById(email))
                return ResponseDto.setFailed("이미 가입한 이메일입니다. 이메일을 다시 확인하시오!");
        } catch (Exception e) {
            return ResponseDto.setFailed(e.getMessage()); // DB Error
        }

        if (!password.equals(passwordConfirmation))
            return ResponseDto.setFailed("비빌 번호가 틀립니다. 다시 확인하시오!");

        // 1. Client.클라이언트에서 회원 가입한 모든 정보를, [UserEntity] 생성자.UserEntity()를 활용하여, "userEntity"에 담는다.
        UserEntity userEntity = new UserEntity(requestDto);

        // 2. 위에서 "userEntity"에 담은 것을, Repository interface의 각각의 "메소드"로 전달한다.
        //    단, "Repository"는 반드시 "try ~ catch" 구문으로 사용해야 한다.
        try {
            System.out.println("=====> userEntity: " + userEntity.toString());
            userRepository.save(userEntity);
            return ResponseDto.setSuccess("Sign Up Success!!!", userEntity);

        } catch (Exception e) {
            return ResponseDto.setFailed(e.getMessage());
        }

    }

//    public SignInResponseDto doSignIn(SignInRequestDto requestDto) {
//        String email = requestDto.getEmail();
//        String password = requestDto.getPassword();
//
//        try {
//            UserRepository.isExistsByEmailAndPassword(email, password);
//        } catch (Exception e) {
//            return ResponseDto.setFailed(e.getMessage());
//        }
//
//
//
//        return SignInResponseDto;
//
//    }

}
