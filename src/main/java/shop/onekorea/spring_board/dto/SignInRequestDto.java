package shop.onekorea.spring_board.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import shop.onekorea.spring_board.entity.UserEntity;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignInRequestDto {

    @NotBlank
    private String email;
    @NotBlank
    private String password;

}
