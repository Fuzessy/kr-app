package hu.kr.server.mindmaster.service;

import hu.fuz.mastermind.GuessResult;
import hu.fuz.mastermind.MasterMindGame;
import hu.kr.server.mindmaster.model.MasterMindGuessRequestModel;
import hu.kr.server.mindmaster.model.MasterMindGuessResponseModel;
import hu.kr.server.mindmaster.model.MasterMindResponseModel;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class MindMasterService {

    private Map<String,MasterMindGame> games = new HashMap<>();

    public MasterMindResponseModel startGame(String userName) {
        MasterMindGame MasterMindGame = new MasterMindGame();
        MasterMindGame.generatemasterSet();
        games.put(userName, MasterMindGame);

        MasterMindResponseModel responseModel = new MasterMindResponseModel();
        responseModel.message = "Kedves " + userName +"! kezdődhet a játék!";
        responseModel.userName = userName;
        return responseModel;
    }

    public MasterMindGuessResponseModel guess(MasterMindGuessRequestModel request) {
        MasterMindGame game = games.get(request.userName);
        GuessResult result = game.guess(request.guess);

        MasterMindGuessResponseModel response = new MasterMindGuessResponseModel();

        response.userName = request.userName;
        response.whiteCount = result.getWhiteCount();
        response.blackCount = result.getBlackCount();
        return  response;
    }
}
