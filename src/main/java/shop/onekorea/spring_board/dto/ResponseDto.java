package shop.onekorea.spring_board.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

// 공통: 요청 후 응답용 DTO
@Data
@AllArgsConstructor(staticName = "set") // 고정된 이름으로 쓰겠다라고 정의하는 것.
public class ResponseDto<D> {

    private boolean result;
    private String message;
    private D data;

    // 요청한 것을 성공했을 때, Response
    public static <D> ResponseDto<D> setSuccess(String message, D data) {
//        return new ResponseDto<D>(true, message, data); // 이것도 가능하지만,
        return ResponseDto.set(true, message, data); // 위에서 "staticName"을 "set"으로 지정해줬기 때문에, 이렇게 사용해도 된다.
    }

    // 요청을 어떤 이유로든 실패했을 때,Response
    public static <D> ResponseDto<D> setFailed(String message) {
        return ResponseDto.set(false, message, null);
    }

}
