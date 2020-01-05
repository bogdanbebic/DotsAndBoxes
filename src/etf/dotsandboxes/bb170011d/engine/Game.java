package etf.dotsandboxes.bb170011d.engine;

import etf.dotsandboxes.bb170011d.player.AbstractPlayer;
import etf.dotsandboxes.bb170011d.player.Player;

import java.io.*;

public class Game implements Runnable, AutoCloseable {
    private StringBuilder allMoves = new StringBuilder();
    private Thread thread;

    private AbstractPlayer player1 = new Player();
    private AbstractPlayer player2 = new Player();
    private AbstractPlayer activePlayer = player1;

    public void setPlayer1(AbstractPlayer player1) {
        this.player1 = player1;
    }

    public void setPlayer2(AbstractPlayer player2) {
        this.player2 = player2;
    }

    private void toggleActivePlayer() {
        if (this.activePlayer == this.player1)
            this.activePlayer = this.player2;
        else
            this.activePlayer = this.player1;
    }

    /**
     * Creates and starts a thread of execution
     * which gets the moves of players and controls the game flow
     */
    public void start() {
        this.allMoves = new StringBuilder();
        this.thread = new Thread(this);
        this.thread.start();
    }

    /** Saves the current game state
     * @param filepath Path to the file in which to save the current game state
     */
    public void save(String filepath) {
        try (PrintWriter printWriter = new PrintWriter(filepath)) {
            printWriter.print(this.allMoves.toString());
        } catch (FileNotFoundException ignored) {
            // maybe show a dialog to the user
        }
    }

    /** Loads a game state from a file
     * @param filepath Path from which to load the game state
     */
    public void load(String filepath) {
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
    public void close() {
        if (this.thread != null)
            this.thread.interrupt();
    }

    /**
     * @return boolean representing if the game state is finished
     */
    private boolean isFinished() {
        // TODO: implement
        return this.thread != null && !this.thread.isInterrupted();
    }

    /**
     * @return boolean representing whether activePlayer has finished his move
     */
    private boolean isEndOfMove() {
        // TODO: implement
        return true;
    }

    /** Plays the move on the board. Changes the isEndOfMove game state
     * Adds the move to the record of all moves in the current game
     * @param move String representing the move to be played
     */
    private void playMove(String move) {
        this.allMoves.append(move).append(System.lineSeparator());
        // TODO: implement
        System.out.println(move);
    }

    /**
     * Contains the game loop. Gets the next move and plays it.
     * Possibly waits on a move if activePlayer is a human player
     */
    @Override
    public void run() {
        while (!this.isFinished()) {
            this.playMove(this.activePlayer.getNextMove());
            if (this.isEndOfMove())
                this.toggleActivePlayer();
        }
    }
}
