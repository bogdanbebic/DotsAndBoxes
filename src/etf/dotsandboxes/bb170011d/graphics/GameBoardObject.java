package etf.dotsandboxes.bb170011d.graphics;

/**
 * Interface representing a single game board object
 */
public interface GameBoardObject {
    /**
     * @return boolean representing if the GameBoardObject is filled
     */
    boolean isFilled();

    /**
     * Sets the filled flag of the GameBoardObject
     * @param filled next call of isFilled will return filled
     */
    void setFilled(boolean filled);
}
