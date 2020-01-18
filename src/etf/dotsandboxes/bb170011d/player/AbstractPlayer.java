package etf.dotsandboxes.bb170011d.player;

import etf.dotsandboxes.bb170011d.engine.GameBoardState;

/**
 * Interface which is required by the Game for a player
 */
@FunctionalInterface
public interface AbstractPlayer {
    /**
     * @param gameBoardState represents the cloned current state of the game board
     * @return String representing the AbstractPlayer's next move
     */
    String getNextMove(GameBoardState gameBoardState);
}
