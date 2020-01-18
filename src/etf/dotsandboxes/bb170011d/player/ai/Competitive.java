package etf.dotsandboxes.bb170011d.player.ai;

import etf.dotsandboxes.bb170011d.engine.GameBoardState;
import etf.dotsandboxes.bb170011d.graphics.GameBoard;

/**
 * Implementation of an AI strategy for an Advanced player.
 * Builds a minimax tree for the game board state and
 * finds the optimal move for the given depth of the minimax tree
 */
public class Competitive extends Advanced implements ArtificialIntelligencePlayer {
    @Override
    public String getNextMove(GameBoardState gameBoardState) {
        int [] nextMove = super.minimax(gameBoardState, super.getMaxDepth(), Integer.MIN_VALUE, Integer.MAX_VALUE, true);
        return GameBoard.getStringFromIndices(nextMove[0], nextMove[1]);
    }
}
