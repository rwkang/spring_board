package shop.onekorea.spring_board.security;

import ch.qos.logback.core.joran.util.beans.BeanDescriptionFactory;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date; // [import java.sql.Date]가 아님에 주의.
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
public class TokenProvider {

    // JWT token 생성 및 검증을 위한 키 변수 정의.
//    private static final String SECURITY_KEY = "jwtseckey!@";
    private static final String TOKEN_PREFIX = "Bearer ";

    // 2023.07.28 Conclusion. JWT token 생성.
    // 만료 날짜: 현재 일자 + 1시간으로 설정.
    public String generateToken(String email) {
        Date expiration = (Date) Date.from(Instant.now().plus(1, ChronoUnit.HOURS));
        System.out.println("=====> TokenProvider.generateToken.expiration: " + expiration.toString());

        return Jwts.builder()
                // 암호화에 사용할 알고리즘, token key
                .signWith(SignatureAlgorithm.HS512, TOKEN_PREFIX)
                // JWT 제목, 생성일
                .setSubject(email).setIssuedAt(new java.util.Date())
                // JWT 만료일
                .setExpiration(expiration)
                // JWT 싱성
                .compact();
    }

    // JWT 검증 == 복호화
    public String decrypt(String token) {
        // 파라메터로 받은 toker key를 사용하여, 복호화 한다.
        Claims claims = Jwts.parser().setSigningKey(TOKEN_PREFIX).parseClaimsJws(token).getBody();

        return claims.getSubject();
        // return Jwts.parser().setSigningKey(TOKEN_PREFIX).parseClaimsJws(token).getBody().getSubject();
    }

}
