package hu.kr.server.mindmaster.service;

import hu.kr.mindmaster.GuessResult;
import hu.kr.mindmaster.MindMasterGame;
import hu.kr.server.mindmaster.model.MasterMindGuessRequestModel;
import hu.kr.server.mindmaster.model.MasterMindGuessResponseModel;
import hu.kr.server.mindmaster.model.MasterMindResponseModel;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class MindMasterService {

    private Map<String,MindMasterGame> games = new HashMap<>();

    public MasterMindResponseModel startGame(String userName) {
        MindMasterGame mindMasterGame = new MindMasterGame();
        mindMasterGame.generatemasterSet();
        games.put(userName, mindMasterGame);

        MasterMindResponseModel responseModel = new MasterMindResponseModel();
        responseModel.message = "Kedves " + userName +"! kezdődhet a játék!";
        responseModel.userName = userName;
        return responseModel;
    }

    public MasterMindGuessResponseModel guess(MasterMindGuessRequestModel request) {
        MindMasterGame game = games.get(request.userName);
        GuessResult result = game.guess(request.guess);

        MasterMindGuessResponseModel response = new MasterMindGuessResponseModel();

        response.userName = request.userName;
        response.whiteCount = result.getWhiteCount();
        response.blackCount = result.getBlackCount();
        return  response;
    }
}
