package shop.onekorea.spring_board.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatchUserRequestDto {

    private String nickname;
    private String profile;

}
