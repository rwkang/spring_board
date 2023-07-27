package shop.onekorea.spring_board.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 회원 가입
// ResponseDto<D> => result, message, data { token, exprTime(토큰 만료 시간) }

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequestDto { // 회원 가입
    private String email;
    private String password;
    private String passwordConfirmation;
    private String nickname;
    private String phoneNo;
    private String address;
    private String addressDetail;

}
