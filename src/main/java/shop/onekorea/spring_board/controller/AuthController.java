package shop.onekorea.spring_board.controller;

//import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import shop.onekorea.spring_board.dto.ResponseDto;
import shop.onekorea.spring_board.dto.SignInRequestDto;
import shop.onekorea.spring_board.dto.SignInResponseDto;
import shop.onekorea.spring_board.dto.SignUpRequestDto;
import shop.onekorea.spring_board.service.AuthService;

//@CrossOrigin(originPatterns = "http://localhost:3000")
// 2023.07.26 Modified. 각각의 Controller에서 처리할 것이 아니라, Project.main() 파일, SpringBootApplication에서 바로 처리한다.

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signUp")
    // public ResponseDto<SignInResponseDto> signIn(@RequestBody SignInResponseDto signInResponseDto) { // 이것으로 받으면 못 받는다.
    // public ResponseDto<SignInResponseDto> signIn(@RequestBody SignInRequestDto signInRequestDto) { // 위 라인과 같이 "SignInResponseDto" 이것이 아님에 특히 주의.
    // => <SignInResponseDto>를 <?>로 대체한다.
//    public ResponseDto<?> signUp(@RequestBody SignUpRequestDto signUpRequestDto) { // 위 라인과 같이 "SignInResponseDto" 이것이 아님에 특히 주의. 다른 Controller 코딩의 통일을 위하여, "requestBody"를 사용한다.
    public ResponseDto<?> doSignUp(@RequestBody SignUpRequestDto requestBody) { // 위 라인과 같이 "SignInResponseDto" 이것이 아님에 특히 주의.
        System.out.println("=====> signUp: " + requestBody.toString());
        // 2023.07.27 Conclusion. 여기서 Backend DB에 저장하게 한다. => 비로소 Service 폴더에서 그것을 담당하게 한다.
        // Service.서비스 처리 방법은 2가지인데,
        // 1. interface를 먼저 만들고, 그것을 구현하면서 처리하는 방식과
        // 2. 직접 class에서 처리하는 방식이 있다.

        ResponseDto<?> result = authService.serviceSighUp(requestBody);

        return result;
    }

    @PostMapping("/signIn")
    public ResponseDto<SignInResponseDto> doSignIn(@RequestBody SignInRequestDto requestBody) {
        System.out.println("=====> AuthController.doSignIn.requestBody: " + requestBody.toString());

        ResponseDto<SignInResponseDto> result = authService.serviceSignIn(requestBody);
        System.out.println("=====> AuthController.doSignIn.result: " + result.toString());

        return result;
    }


}
