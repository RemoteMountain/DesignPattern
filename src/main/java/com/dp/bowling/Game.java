package com.dp.bowling;


public class Game {
    private int itsCurrentFrame = 0;
    private Boolean firsrThrowInFrame = true;

    private Scorer itsScorer = new Scorer();

    public int score() {
        return scoreForFrame(itsCurrentFrame);
    }

    public void add(int pins) {
        itsScorer.addThrow(pins);
        adjustCurrentFrame(pins);

    }

    private void adjustCurrentFrame(int pins) {
        if (lastBallInFrame(pins)) {
            advanceFrame();
        } else {
            firsrThrowInFrame = true;
        }
    }

    private boolean lastBallInFrame(int pins) {
        return strike(pins) || !firsrThrowInFrame;
    }

    private boolean strike(int pins) {
        return (firsrThrowInFrame && pins == 10);
    }

    private void advanceFrame() {
        itsCurrentFrame = Math.min(10, itsCurrentFrame + 1);
    }

    public int scoreForFrame(int frame) {
        return itsScorer.scoreForFrame(frame);
    }

}
