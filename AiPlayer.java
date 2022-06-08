import java.util.*;

/**
 * This is the AiPlayer class. It simulates a minimax player for the max
 * connect four game.
 * The constructor essentially does nothing.
 * 
 * @author james spargo
 *
 */

public class AiPlayer {

    public int depth;
    public GameBoard gameBoardS;

    public AiPlayer(int depth, GameBoard currentGame) {
        this.depth = depth;
        this.gameBoardS = currentGame;
    }

    /**
     * This method plays a piece randomly on the board
     * 
     * @param currentGame The GameBoard object that is currently being used to
     *                    play the game.
     * @return an integer indicating which column the AiPlayer would like
     *         to play in.
     */
    public int findBestPlay(GameBoard currentGame) throws CloneNotSupportedException {
        int playC = maxconnect4.INVALID;
        if (currentGame.getCurrentTurn() == maxconnect4.ONE) {
            int max_val = Integer.MAX_VALUE;
            for (int i = 0; i < GameBoard.columns; i++) {
                if (currentGame.isValidPlay(i)) {
                    GameBoard nextMoveBoard = new GameBoard(currentGame.getGameBoard());
                    nextMoveBoard.playPiece(i);
                    int value = Max_Utility(nextMoveBoard, depth, Integer.MIN_VALUE, Integer.MAX_VALUE);
                    if (max_val > value) {
                        playC = i;
                        max_val = value;
                    }
                }
            }
        } else {
            int v = Integer.MIN_VALUE;
            for (int i = 0; i < GameBoard.columns; i++) {
                if (currentGame.isValidPlay(i)) {
                    GameBoard nextMoveBoard = new GameBoard(currentGame.getGameBoard());
                    nextMoveBoard.playPiece(i);
                    int value = Min_Utility(nextMoveBoard, depth, Integer.MIN_VALUE, Integer.MAX_VALUE);
                    if (v < value) {
                        playC = i;
                        v = value;
                    }
                }
            }
        }
        return playC;
    }

    /**
     * This method calculates the max value.
     * 
     * @param gameBoard   The GameBoard object that is currently being used to play
     *                    the game.
     * @param depth       depth to which computer will search for making best
     *                    possible next move
     * @param alpha_value maximum utility value for MAX player
     * @param beta_value  maximum utility value for MIN player
     * @return an integer indicating which column the AiPlayer would like to play
     *         in.
     * @throws CloneNotSupportedException
     */

    private int Max_Utility(GameBoard gameBoard, int depth, int alpha_value, int beta_value)
            throws CloneNotSupportedException {
        // TODO Auto-generated method stub
        if (!gameBoard.isBoardFull() && depth > 0) {
            int val2 = Integer.MIN_VALUE;
            for (int i = 0; i < GameBoard.columns; i++) {
                if (gameBoard.isValidPlay(i)) {
                    GameBoard board4NextMove = new GameBoard(gameBoard.getGameBoard());
                    board4NextMove.playPiece(i);
                    int value = Min_Utility(board4NextMove, depth - 1, alpha_value, beta_value);
                    if (val2 < value) {
                        val2 = value;
                    }
                    if (val2 >= beta_value) {
                        return val2;
                    }
                    if (alpha_value < val2) {
                        alpha_value = val2;
                    }
                }
            }
            return val2;
        } else {
            // this is a terminal state
            return gameBoard.getScore(maxconnect4.TWO) - gameBoard.getScore(maxconnect4.ONE);
        }
    }

    /**
     * This method calculates the min value.
     * 
     * @param gameBoard The GameBoard object that is currently being used to play
     *                  the game.
     * @param depth     depth to which computer will search for making best
     *                  possible next move
     * @param a         maximum utility value for MAX player
     * @param b         maximum utility value for MIN player
     * @return an integer indicating which column the AiPlayer would like to play
     *         in.
     * @throws CloneNotSupportedException
     */

    private int Min_Utility(GameBoard gameBoard, int depth, int a, int b)
            throws CloneNotSupportedException {

        if (!gameBoard.isBoardFull() && depth > 0) {
            int val1 = Integer.MAX_VALUE;
            for (int i = 0; i < GameBoard.columns; i++) {
                if (gameBoard.isValidPlay(i)) {
                    GameBoard board4NextMove = new GameBoard(gameBoard.getGameBoard());
                    board4NextMove.playPiece(i);
                    int value = Max_Utility(board4NextMove, depth - 1, a, b);
                    if (val1 > value) {
                        val1 = value;
                    }
                    if (val1 <= a) {
                        return val1;
                    }
                    if (b > val1) {
                        b = val1;
                    }
                }
            }
            return val1;
        } else {
            // this is a terminal state
            return gameBoard.getScore(maxconnect4.TWO) - gameBoard.getScore(maxconnect4.ONE);
        }
    }

}
