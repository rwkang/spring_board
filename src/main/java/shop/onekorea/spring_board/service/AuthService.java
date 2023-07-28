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
import shop.onekorea.spring_board.security.TokenProvider;

import java.sql.PreparedStatement;

@Service
public class AuthService {

    @Autowired private UserRepository userRepository;
    @Autowired private TokenProvider tokenProvider;

    public ResponseDto<?> serviceSighUp(SignUpRequestDto requestDto) {
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


    public ResponseDto<SignInResponseDto> serviceSignIn(SignInRequestDto requestDto) {
        System.out.println("=====> AuthService.serviceSignIn.requestDto: " + requestDto.toString());

        String email = requestDto.getEmail();
        String password = requestDto.getPassword();

        // 2023.07.28 Conclusion. getBy~, findBy~, existsBy~ 속도는 어는 것이 빠를까?
        // https://velog.io/@_koiil/JPA-existById-vs.-getById-vs.-findById
        // 1. 존재 여부를 확인할 때는 existBy
        // 2. Id 값만 사용할 엔티티가 필요한 경우에는 getBy
        // 3. Id 이외의 데이터도 사용하는 엔티티의 경우에는 findBy를 사용하면 되겠습니다!

//        // 강의에서는 여기서 먼저 "email, password" 존재를 확인하였으나, 어차피 2번 검색한다면, 아래와 같이 하는 것이 좋을 듯...
//        try {
//            boolean isExisted = userRepository.existsByEmailAndPassword(email, password);
//            if (!isExisted) return ResponseDto.setFailed("이메일이 없거나 비빌 번호가 맞지 않네요. 이메일과 비밀 번호를 다시 확인하시오!");
//        } catch (Exception e) {
//            return ResponseDto.setFailed(e.getMessage());
//        }

        UserEntity userEntity = null;
        try {
            // 먼저 "userEntity.사원 정보" 모두를, "email"을 기준으로 받아서, "password" 정보를 "비워" 둔다. ***중요***
            userEntity = userRepository.findById(email).get();
            if (userEntity == null) return ResponseDto.setFailed("등록된 이메일이 않네요. 이메일을 다시 확인하시오!");
        }catch (Exception e) {
            return ResponseDto.setFailed(e.getMessage());
        }
        System.out.println("=====> AuthService.serviceSignIn.userEntity: " + userEntity.toString());

        // 사실 여기서는 "password"만 확인해도 되는데...
        try {
            // boolean isExisted = userRepository.existsByEmailAndPassword(email, password); OK
//            boolean isExisted = userRepository.findByEmailAndPassword(email, password); // NG
//            boolean isExisted = userRepository.getByPassword(password); // NG
             boolean isExisted = userRepository.existsByPassword(password); // OK
//             boolean isExisted = userRepository.findByPassword(password); // NG
            if (!isExisted) return ResponseDto.setFailed("비빌 번호가 맞지 않네요. 비밀 번호를 다시 확인하시오!");
        } catch (Exception e) {
           return ResponseDto.setFailed(e.getMessage());
        }

        System.out.println("=====> AuthService.serviceSignIn.password: " + password);

        userEntity.setPassword(""); // 비밀 번호는 Client.클라이언트 쪽으로 넘어갈 때, "비워서" 넘어가게 해야 한다

        String token = tokenProvider.generateToken(email);//        int expiration = 60 * 60 * 24 * 7; //
        int expiration = 60 * 60 * 24 * 30; //

        SignInResponseDto signInResponseDto = new SignInResponseDto(token, expiration, userEntity);
        System.out.println("=====> AuthService.serviceSignIn.signInResponseDto: " + signInResponseDto.toString());

        return ResponseDto.setSuccess("Sign In Success!!!", signInResponseDto);

    }



}
