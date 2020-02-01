package hu.kr.mindmaster;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class MindMasterGame {
    private List<PegColor> masterSet;

    public void setMasterSet(List<PegColor> masterSet) {
        this.masterSet = masterSet;
    }

    public GuessResult guess(List<PegColor> guessPegs) {
        GuessResult guessResult = new GuessResult();


        List<PegColor> copyOfMaster = new ArrayList<>(masterSet);
        List<PegColor> copyOfGuess = new ArrayList<>(guessPegs);
        int whiteCount = getWhiteCount(copyOfMaster, copyOfGuess);
        int blackCount = getBlackCount(copyOfMaster, copyOfGuess);

        guessResult.setWhiteCount(whiteCount);
        guessResult.setBlackCount(blackCount);
        return guessResult;
    }

    private int getBlackCount(List<PegColor> copyOfMaster, List<PegColor> copyOfGuess) {
        Iterator<PegColor> guessIterator = copyOfGuess.iterator();
        int blackCount = 0;
        while (guessIterator.hasNext()){
            PegColor guessPeg = guessIterator.next();
            if(copyOfMaster.contains(guessPeg)){
                blackCount++;
            }
        }
        return blackCount;
    }

    private int getWhiteCount(List copyOfMaster, List copyOfGuess) {
        int sizeOfPegs = masterSet.size();
        int whiteCount = 0;
        for(int i = 0; i < sizeOfPegs; i++){
            if(copyOfGuess.get(i) == copyOfMaster.get(i)) {
                copyOfGuess.remove(i);
                copyOfMaster.remove(i);
                sizeOfPegs--;
                i--;
                whiteCount++;
            }
        }
        return whiteCount;
    }

}
