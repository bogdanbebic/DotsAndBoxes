package etf.dotsandboxes.bb170011d.player.ai;

import etf.dotsandboxes.bb170011d.engine.GameBoardState;
import etf.dotsandboxes.bb170011d.graphics.GameBoard;

public class Competitive extends Advanced implements ArtificialIntelligencePlayer {
    private int maxDepth = 3;

    @Override
    public String getNextMove(GameBoardState gameBoardState) {
        int [] nextMove = super.minimax(gameBoardState, this.maxDepth, Integer.MIN_VALUE, Integer.MAX_VALUE, true);
        return GameBoard.getStringFromIndices(nextMove[0], nextMove[1]);
    }
}
