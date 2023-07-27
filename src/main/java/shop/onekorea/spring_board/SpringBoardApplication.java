package shop.onekorea.spring_board;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class SpringBoardApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoardApplication.class, args);
        System.out.println("=====> SpringBoardAppkication.main.shop.onekorea.spring_board");
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
