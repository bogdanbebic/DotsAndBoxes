package etf.dotsandboxes.bb170011d.engine;

import etf.dotsandboxes.bb170011d.graphics.GameBoard;

import java.awt.*;

public class Game {
    private static final Color player1Color = Color.blue;
    private static final Color player2Color = Color.red;

    enum Players {
        PLAYER1, PLAYER2
    }

    private Players activePlayer = Players.PLAYER1;

    private void toggleActivePlayer() {
        if (this.activePlayer == Players.PLAYER1)
            this.activePlayer = Players.PLAYER2;
        else
            this.activePlayer = Players.PLAYER1;
    }

    private GameBoard gameBoard;



}
