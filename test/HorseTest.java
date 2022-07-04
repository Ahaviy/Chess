import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HorseTest extends PieceTest {

    @Test
    void getColor() {
        Horse whiteHorse = new Horse(ColorPiece.WHITE);
        Horse blackHorse = new Horse(ColorPiece.BLACK);
        assertEquals(whiteHorse.getColor(), "White");
        assertEquals(blackHorse.getColor(), "Black");
    }

    @Test
    void getSymbol() {
        Horse horse = new Horse(ColorPiece.WHITE);
        assertEquals(horse.getSymbol(), "H");
    }

    @Test
    void canMoveToPosition() {
        //Проверка недопустимых параметров
        assert (checkNotValid(new Horse(ColorPiece.WHITE)));

        //Проверка на чистой доске
        ChessBoard chessBoard = new ChessBoard();
        chessBoard.board[3][3] = new Horse(ColorPiece.WHITE);
        int[][] validMovies = new int[][]{{4, 5}, {4, 1}, {5, 4}, {5, 2}, {2, 5}, {2, 1}, {1, 4}, {1, 2}};
        assert (checkMovies(chessBoard, 3, 3, validMovies));

        //Проверка с фигурами
        chessBoard.board[4][5] = new Horse(ColorPiece.BLACK);
        chessBoard.board[2][1] = new Rook(ColorPiece.BLACK);
        chessBoard.board[4][1] = new Pawn(ColorPiece.WHITE);
        chessBoard.board[5][2] = new Horse(ColorPiece.WHITE);
        validMovies = new int[][]{{4, 5}, {5, 4}, {2, 5}, {2, 1}, {1, 4}, {1, 2}};
        assert (checkMovies(chessBoard, 3, 3, validMovies));
    }

    @Test
    void canAttackToPosition() {
        ChessBoard chessBoard = new ChessBoard();
        chessBoard.board[3][3] = new Horse(ColorPiece.BLACK);
        chessBoard.board[1][4] = new Horse(ColorPiece.BLACK);
        chessBoard.board[2][5] = new Rook(ColorPiece.BLACK);
        chessBoard.board[4][1] = new Pawn(ColorPiece.WHITE);
        chessBoard.board[4][5] = new Horse(ColorPiece.WHITE);
        int[][] validMovies = new int[][]{{4, 5}, {4, 1}, {5, 4}, {5, 2}, {2, 1}, {1, 2}};
        assert (checkAttack(chessBoard, 3, 3, validMovies));
    }
}