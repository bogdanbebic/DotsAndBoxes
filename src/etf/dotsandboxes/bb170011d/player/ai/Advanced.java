package etf.dotsandboxes.bb170011d.player.ai;

import etf.dotsandboxes.bb170011d.engine.GameBoardState;
import etf.dotsandboxes.bb170011d.graphics.GameBoard;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Advanced implements ArtificialIntelligencePlayer {
    private int maxDepth = 3;

    public void setMaxDepth(int maxDepth) {
        this.maxDepth = maxDepth;
    }

    @SuppressWarnings("Duplicates")
    @Override
    public String getNextMove(GameBoardState gameBoardState) {
        Optional<int []> pointsMove = gameBoardState.getPointsMove();
        if (pointsMove.isPresent()) {
            int [] nextMove = pointsMove.get();
            return GameBoard.getStringFromIndices(nextMove[0], nextMove[1]);
        }

        int [] nextMove = minimax(gameBoardState, this.maxDepth, Integer.MIN_VALUE, Integer.MAX_VALUE, true);
        return GameBoard.getStringFromIndices(nextMove[0], nextMove[1]);
    }

    private int [] minimax(GameBoardState gameBoardState, int depth, int alpha, int beta, boolean maximizing) {
        List<int []> possibleMoves = gameBoardState.getPossibleMoves();
        int score;
        int bestRow = -1;
        int bestCol = -1;

        if (possibleMoves.isEmpty() || depth == 0) {
            score = gameBoardState.evaluate();
            return new int[] {bestRow, bestCol, score};
        }

        for (int [] move : possibleMoves) {
            boolean nextMaximizing = maximizing;
            if (!gameBoardState.isPointsMove(move[0], move[1]))
                nextMaximizing = !maximizing;

            gameBoardState.setFilled(move[0], move[1], true);
            score = Objects.requireNonNull(
                    minimax(gameBoardState, depth - 1, alpha, beta, nextMaximizing)
            )[2];

            if (maximizing) {
                if (score > alpha) {
                    alpha = score;
                    bestRow = move[0];
                    bestCol = move[1];
                }
            }

            if (!maximizing) {
                if (score < beta) {
                    beta = score;
                    bestRow = move[0];
                    bestCol = move[1];
                }
            }

            // undo move
            gameBoardState.setFilled(move[0], move[1], false);
            if (alpha > beta)
                break;  // cut-off
        }

        return new int[] {bestRow, bestCol, maximizing ? alpha : beta};
    }
}
