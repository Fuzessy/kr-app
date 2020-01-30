package hu.kr.mindmaster;

import java.util.Collections;
import java.util.List;

public class MindMasterGame {
    private List<PegColor> masterSet;

    public void setMasterSet(List<PegColor> masterSet) {
        this.masterSet = masterSet;
    }

    public GuessResult guess(List<PegColor> guessPegs) {
        GuessResult guessResult = new GuessResult();

        int whiteCount = 0;
        for(int i = 0; i < 4; i++){
            if(guessPegs.get(i) == masterSet.get(i))
                whiteCount ++;
        }

        guessResult.setWhiteCount(whiteCount);
        return guessResult;
    }

}
