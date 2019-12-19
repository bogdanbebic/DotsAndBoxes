package etf.dotsandboxes.bb170011d.engine;

public class PlayerNode implements GameBoardObject {
    private boolean filled = false;

    public boolean isFilled() {
        return this.filled;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
    }
}
