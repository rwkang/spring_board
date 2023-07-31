package shop.onekorea.spring_board;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// 2023.07.30 Added. Security 디펜던시 주입 후, @SpringBootApplication() : 내용 추가.
// @SpringBootApplication ===> 아래와 같이 변경하고,
// "filter" 패키지를 추가하고, JwtAuthenticationFilter.java 파일을 잘 정리한 후,
// "config" 패키지를 추가하고, WebSecurityConfig.java 파일 또한 잘 정리한 후,
// "controller" 패키지에, BoardController.java 파일을 간단하게 맵핑한 후,
// 서버를 리부팅하여,
// postman: https://web.postman.co/workspace/My-Workspace~255027b6-9051-4b3b-8829-99c63831bf0f/request/14047405-9e3a2d71-3971-43e2-bee9-46c8dcbbbcd8
// 실행하면, 정상적으로 실행되면서, token을 받아 온다.
// 받아 온 토큰 값을 복사하여,
// postman: postman: https://web.postman.co/workspace/My-Workspace~255027b6-9051-4b3b-8829-99c63831bf0f/request/14047405-9e3a2d71-3971-43e2-bee9-46c8dcbbbcd8
// 을 열고,
// GET, localhost:8000/api/board, Authorization 팁을 선택하고, Type: Bearer 선택, Token: 어기에 토큰 값을 붙여넣기 하고, [Send] 버튼을 클릭하면,
// 정상적으로 로그인 처리가 되는 것을 확인할 수 있다.
@SpringBootApplication(exclude = {UserDetailsServiceAutoConfiguration.class})
public class SpringBoardApplication {

    // 2023.07.30 Added. Security 디펜던시 주입 후, 아래 내용 추가.


    public static void main(String[] args) {
        SpringApplication.run(SpringBoardApplication.class, args);
        System.out.println("=====> SpringBoardAppkication.main.shop.onekorea.spring_board");
    }

    // 2023.07.28 Added. SignIn
    @Bean
    public WebMvcConfigurer corsConfigurer() { // import org.springframework.web.
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOriginPatterns();
            }
        };
    }

    //@CrossOrigin(originPatterns = "http://localhost:3000")
    // 2023.07.26 Modified. 각각의 Controller에서 처리할 것이 아니라, Project.main() 파일, SpringBootApplication에서 바로 처리한다.
    @Bean
    public WebMvcConfigurer webMvcConfigurer() { // 꼭 import org.springframework.web.servlet.config.annotation.WebMvcConfigurer; 이것으로 import.
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOriginPatterns();
            }
        };
    }

}
