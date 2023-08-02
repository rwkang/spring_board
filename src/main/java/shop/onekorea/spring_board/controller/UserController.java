package shop.onekorea.spring_board.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shop.onekorea.spring_board.dto.PatchUserRequestDto;
import shop.onekorea.spring_board.dto.PatchUserResponseDto;
import shop.onekorea.spring_board.dto.ResponseDto;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @PatchMapping("/")
    public ResponseDto<PatchUserResponseDto> patchUser(@RequestBody PatchUserRequestDto requestBody, @AuthenticationPrincipal String email) {
        return null;
    }

}
