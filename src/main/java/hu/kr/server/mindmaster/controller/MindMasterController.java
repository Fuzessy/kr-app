package hu.kr.server.mindmaster.controller;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("mindmaster")
public class MindMasterController {

    @GetMapping("start/{username}")
    public String startGameWithPathVariable(@PathVariable("username") String userName){
        return "Hello " + userName;
    }

    @GetMapping("start")
    public String startGameWithPathParam(@RequestParam("username") String userName){
        return "Hello " + userName + "!";
    }
}
