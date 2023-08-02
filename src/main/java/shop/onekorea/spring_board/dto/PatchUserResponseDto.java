package shop.onekorea.spring_board.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import shop.onekorea.spring_board.entity.UserEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatchUserResponseDto {

    private UserEntity user;

}
