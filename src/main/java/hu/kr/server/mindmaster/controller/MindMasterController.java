package hu.kr.server.mindmaster.controller;

import hu.kr.server.mindmaster.model.MasterMindGuessRequestModel;
import hu.kr.server.mindmaster.model.MasterMindGuessResponseModel;
import hu.kr.server.mindmaster.model.MasterMindResponseModel;
import hu.kr.server.mindmaster.service.MindMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("mindmaster")
public class MindMasterController {

    @Autowired
    private MindMasterService mindMasterService;

    @GetMapping("start/{username}")
    public MasterMindResponseModel startGameWithPathVariable(@PathVariable("username") String userName){
        return mindMasterService.startGame(userName);
    }

    @GetMapping("start")
    public MasterMindResponseModel startGameWithPathParam(@RequestParam("username") String userName){
        return mindMasterService.startGame(userName);
    }

    @PostMapping("guess")
    public MasterMindGuessResponseModel guess(@RequestBody MasterMindGuessRequestModel masterMindGuessRequestModel){
        return mindMasterService.guess(masterMindGuessRequestModel);
    }
}
