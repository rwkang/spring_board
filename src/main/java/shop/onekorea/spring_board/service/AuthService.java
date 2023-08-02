package shop.onekorea.spring_board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    // 2023.08.31 Added. Backend Security - 비밀 번호 암호화
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

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
            return ResponseDto.setFailed("비밀 번호를 다시 확인하시오!");

        // 1. Client.클라이언트에서 회원 가입한 모든 정보를, [UserEntity] 생성자.UserEntity()를 활용하여, "userEntity"에 담는다.
        UserEntity userEntity = new UserEntity(requestDto);

        // 2023.08.01 Added. 비밀 번호 암호화
        String encodedPassword = passwordEncoder.encode(password);
        userEntity.setPassword(encodedPassword);
        // => board_spring DB user table password 컬럼 넓이 수정: 기존 20 => 수정 200

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

        // 2023.08.01 Conclusion. SignUp.회원 가입 Request 정보를 받아서, password.비밀 번호를 암호화한 후에는,
        // 위의 email+password 조합을 쓸 수 없고, 'email' 1개로 검색하여, 해당 하는 정보를 가져온다.
        // 사실은 이런 로직을 나는 처음부터 생각했었기 때문에, 저쪽 아래에서도 이와 같이 "email"을 먼저 확인하고, "password"를 확인하는 방식으로 처리했었다.

        try {
            userEntity = userRepository.findByEmail(email);
            // email check
            if (userEntity == null) return ResponseDto.setFailed("등록된 이메일이 않네요. 이메일을 다시 확인하시오!");
            // password check
            if (!passwordEncoder.matches(password, userEntity.getPassword()))
                return ResponseDto.setFailed("비빌 번호가 맞지 않네요. 비밀 번호를 다시 확인하시오!비�� ��호가 ��지 않��요. �����");
        } catch (Exception e) {
            return ResponseDto.setFailed(e.getMessage()); // Database Error
        }

        System.out.println("=====> AuthService.serviceSignIn.userEntity: " + userEntity.toString());


        /* // 2023.08.31 Added. 아래 내용을 위와 같이 변경해야 한다. password.비밀 번호를 암호화한 후.

        // 20230.07.27 Conclusion. 강의에서는 "email+password" 조합으로 검색하여 처리하였으나, 나는 먼저 "email" 만으로 처리한다.
        try {
            // 먼저 "userEntity.사원 정보" 모두를, "email"을 기준으로 받아서, "password" 정보를 "비워" 둔다. ***중요***
            userEntity = userRepository.findById(email).get();
            if (userEntity == null) return ResponseDto.setFailed("등록된 이메일이 않네요. 이메일을 다시 확인하시오!");
        } catch (Exception e) {
            return ResponseDto.setFailed(e.getMessage());
        }
        System.out.println("=====> AuthService.serviceSignIn.userEntity: " + userEntity.toString());

        // 사실 여기서는 "password"만 확인해도 되는데...
        try {
            // boolean isExisted = userRepository.findByEmailAndPassword(email, password); // NG
            // boolean isExisted = userRepository.getByPassword(password); // NG
            // boolean isExisted = userRepository.findByPassword(password); // NG

            // boolean isExisted = userRepository.existsByEmailAndPassword(email, password); OK

             boolean isExisted = userRepository.existsByPassword(password); // OK // 비밀 번호 암호화 후 사용 불가.
             if (!isExisted) return ResponseDto.setFailed("비빌 번호가 맞지 않네요. 비밀 번호를 다시 확인하시오!"); // 비밀 번호 암호화 후 사용 불가.

        } catch (Exception e) {
           return ResponseDto.setFailed(e.getMessage());
        }
        */

        System.out.println("=====> AuthService.serviceSignIn.password: " + password);

        userEntity.setPassword(""); // 비밀 번호는 Client.클라이언트 쪽으로 넘어갈 때, "비워서" 넘어가게 해야 한다

        String token = tokenProvider.generateToken(email);//        int expiration = 60 * 60 * 24 * 7; //
//        int expiration = 60 * 60 * 24 * 30; //
        int expiration = 60 * 60 * 1000; // 1시간

        SignInResponseDto signInResponseDto = new SignInResponseDto(token, expiration, userEntity);
        System.out.println("=====> AuthService.serviceSignIn.signInResponseDto: " + signInResponseDto.toString());

        return ResponseDto.setSuccess("Sign In Success!!!", signInResponseDto);

    }



}
