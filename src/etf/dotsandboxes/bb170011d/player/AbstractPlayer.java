package etf.dotsandboxes.bb170011d.player;

import etf.dotsandboxes.bb170011d.engine.GameBoardState;

@FunctionalInterface
public interface AbstractPlayer {
    String getNextMove(GameBoardState gameBoardState);
}
