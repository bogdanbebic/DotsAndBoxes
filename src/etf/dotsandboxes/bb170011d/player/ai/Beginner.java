package etf.dotsandboxes.bb170011d.player.ai;

import etf.dotsandboxes.bb170011d.engine.GameBoardState;
import etf.dotsandboxes.bb170011d.graphics.GameBoard;

import java.util.List;
import java.util.Optional;
import java.util.Random;

public class Beginner implements ArtificialIntelligencePlayer {
    private Random random = new Random();

    @Override
    public String getNextMove(GameBoardState gameBoardState) {
        Optional<int []> pointsMove = gameBoardState.getPointsMove();
        if (pointsMove.isPresent()) {
            int [] nextMove = pointsMove.get();
            return GameBoard.getStringFromIndices(nextMove[0], nextMove[1]);
        }

        List<int []> possibleMoves = gameBoardState.getPossibleMoves();
        int [] nextMove = possibleMoves.get(random.nextInt(possibleMoves.size()));
        return GameBoard.getStringFromIndices(nextMove[0], nextMove[1]);
    }
}
