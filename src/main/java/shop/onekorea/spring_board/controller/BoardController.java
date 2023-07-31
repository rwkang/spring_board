package shop.onekorea.spring_board.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/board")
public class BoardController {

    @GetMapping("/")
    public String getBoard() {
        return "/Controller/getBoard()까징 왔네요!!!";
    }

}
