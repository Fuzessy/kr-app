package hu.kr.server.mindmaster.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("mindmaster")
public class MindMasterController {

    @GetMapping("welcome")
    public String startGame(){
        return "hello";
    }
}
