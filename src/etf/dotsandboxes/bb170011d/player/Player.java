package etf.dotsandboxes.bb170011d.player;

import etf.dotsandboxes.bb170011d.engine.GameBoardState;

import java.util.concurrent.SynchronousQueue;

/**
 * Class representing a human player in the Game
 */
public class Player implements AbstractPlayer {

    private SynchronousQueue<String> nextMove = new SynchronousQueue<>();

    /** Blocks if no next move is known yet
     * @return String representing the next move to be played
     */
    @Override
    public String getNextMove(GameBoardState gameBoardState) {
        try {
            return nextMove.take();
        } catch (InterruptedException e) {
            return null;
        }
    }

    /** Might not have effect if called when no move is expected from the player
     * @param nextMove String representing the next move to be played
     */
    public void setNextMove(String nextMove) {
        this.nextMove.offer(nextMove);
    }

}
