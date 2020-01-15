package etf.dotsandboxes.bb170011d.engine;

import etf.dotsandboxes.bb170011d.Main;
import etf.dotsandboxes.bb170011d.graphics.GameBoard;
import etf.dotsandboxes.bb170011d.player.AbstractPlayer;
import etf.dotsandboxes.bb170011d.player.Player;
import etf.dotsandboxes.bb170011d.player.ai.Beginner;

import java.awt.*;
import java.io.*;

/**
 * Implements the game loop and game logic
 * Contains a thread of execution for the game loop
 * Contains various methods regarding the game state, such as
 * loading, saving, setting players, etc.
 */
public class Game implements Runnable, AutoCloseable {
    /**
     * Contains all of the played moves in a single game
     */
    private StringBuilder allMoves = null;
    /**
     * Thread of execution for a single game
     */
    private Thread thread;

    private GameBoard board = Main.board;
    private boolean endOfMove = true;

    private AbstractPlayer player1 = new Player();
    private AbstractPlayer player2 = new Beginner();
    private AbstractPlayer activePlayer = player1;

    private int points1 = 0;
    private int points2 = 0;

    private Color color1 = Color.blue;
    private Color color2 = Color.red;
    private Color activeColor = color1;

    private void incrementPoints(int increment) {
        if (this.activePlayer == this.player1)
            this.points1 += increment;
        else
            this.points2 += increment;
    }

    public void setBoard(GameBoard board) {
        this.board = board;
    }

    public void setPlayer1(AbstractPlayer player1) {
        this.player1 = player1;
    }

    public void setPlayer2(AbstractPlayer player2) {
        this.player2 = player2;
    }

    public Color getActiveColor() {
        return this.activeColor;
    }

    public Color getColor1() {
        return this.color1;
    }

    public Color getColor2() {
        return this.color2;
    }

    private void toggleActivePlayer() {
        Main.gamePanel.toggleActivePlayer();
        if (this.activePlayer == this.player1) {
            this.activePlayer = this.player2;
            this.activeColor = this.color2;
        }
        else {
            this.activePlayer = this.player1;
            this.activeColor = this.color1;
        }
    }

    /**
     * Creates and starts a thread of execution
     * which gets the moves of players and controls the game flow
     */
    public void start() {
        if (this.allMoves == null)
            this.allMoves = new StringBuilder();
        this.thread = new Thread(this);
        this.thread.start();
    }

    /**
     * Saves the current game state
     * @param filepath Path to the file in which to save the current game state
     */
    public void save(String filepath) {
        try (PrintWriter printWriter = new PrintWriter(filepath)) {
            printWriter.print(this.allMoves.toString());
        } catch (FileNotFoundException ignored) {
            // maybe show a dialog to the user
        }
    }

    /**
     * Loads a game state from a file
     * @param filepath Path from which to load the game state
     */
    public void load(String filepath) {
        this.allMoves = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filepath))) {
            bufferedReader.lines().forEach(move -> {
                Game.this.playMove(move);
                if (Game.this.isEndOfMove())
                    Game.this.toggleActivePlayer();
            });
        } catch (IOException ignored) {
            // maybe show a dialog to the user
        }
    }

    /**
     * Safely quits the game
     */
    @Override
    public void close() {
        if (this.thread != null)
            this.thread.interrupt();
    }

    /**
     * @return boolean representing if the game state is finished
     */
    private boolean isFinished() {
        return this.points1 + this.points2 == this.board.getNumberOfPlayerNodes();
    }

    /**
     * @return boolean representing whether activePlayer has finished his move
     */
    private boolean isEndOfMove() {
        return this.endOfMove;
    }

    /**
     * Plays the move on the board. Changes the isEndOfMove game state
     * Adds the move to the record of all moves in the current game
     * @param move String representing the move to be played
     */
    private void playMove(String move) {
        this.allMoves.append(move).append(System.lineSeparator());
        int i = GameBoard.getRowsIndexFromString(move);
        int j = GameBoard.getColsIndexFromString(move);
        this.board.setColor(i, j, this.activeColor);
        int numberOfPointsAcquired = this.board.setFilled(i, j, true);
        this.endOfMove = numberOfPointsAcquired == 0;
        if (!this.endOfMove)
            this.incrementPoints(numberOfPointsAcquired);

        Main.gamePanel.setPoints(this.points1, this.points2);
    }

    /**
     * Offers a move to the active human player (if such exists)
     * Should be called directly by the human player move listeners (GUI)
     * @param move Move which the player will potentially play
     */
    public void offerPlayerMove(String move) {
        int i = GameBoard.getRowsIndexFromString(move);
        int j = GameBoard.getColsIndexFromString(move);
        if (this.board.isFilled(i, j))
            return;
        if (this.activePlayer instanceof Player)
            ((Player)this.activePlayer).setNextMove(move);
    }

    /**
     * Contains the game loop. Gets the next move and plays it.
     * Possibly waits on a move if activePlayer is a human player
     * Should not be called directly
     */
    @Override
    public void run() {
        while (!this.isFinished()) {
            this.playMove(this.activePlayer.getNextMove(new GameBoardState(this.board)));
            if (this.isEndOfMove())
                this.toggleActivePlayer();
        }

        Main.gamePanel.endGame();
    }
}
