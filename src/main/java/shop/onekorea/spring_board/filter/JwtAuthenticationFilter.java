package shop.onekorea.spring_board.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import shop.onekorea.spring_board.security.TokenProvider;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    // 2023.07.30 Conclusion. Request가 들어왔을 때, Request Header의 Authorization 필드의 Bearer Token을 가져와서,
    // 토큰을 검증하고, 검증 결과를 SecurityContext에 추가하여,
    // Payload 안에 있는, 즉 여기 앱에서는 UserEmail에 대한 값을, Thread.쓰레드 내에서 지속적으로 유지할 수 있도록 해 준다.
    @Autowired
    private TokenProvider tokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try {


            String token = parseBearerToken(request);

            if (token != null && !token.equalsIgnoreCase("null")) { // equalsIgnoreCase: 대 소문자 구분 없이 ~~~

                // 토큰 검증해서 payload의 email을 가져 옴.
                String userEmail = tokenProvider.validate(token);

                // SecurityContext에 추가할 객체  // 강의에서는 변수명을 authentication으로 사용했다.
                // AbstractAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userEmail, null, AuthorityUtils.NO_AUTHORITIES);
                AbstractAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userEmail, null, AuthorityUtils.NO_AUTHORITIES);
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // SecurityContext에 AbstractAuthenticationToken 객제를 추가해서 해당 Thread가 지속적으로 인증 정보를 가질 수 있도록 해 준다.
                SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
                securityContext.setAuthentication(authenticationToken);
                SecurityContextHolder.setContext(securityContext);


            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        filterChain.doFilter(request, response);

    }

    // Request Header의 Authorization 필드의 Bearer Token을 가져오는 메소드.
    private String parseBearerToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) { // 반드시 맨 끝 띄어쓰기, 공란" "도 꼭 넣어 주어야 한다.
            return bearerToken.substring(7); // Bearer Token의 7자리를 제거하고, 제거된 String
        }
        return null;
    }

}
