import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HorseTest extends PieceTest {

    @Test
    void getColor() {
        Horse whiteHorse = new Horse("White");
        Horse blackHorse = new Horse("Black");
        assertEquals(whiteHorse.getColor(), "White");
        assertEquals(blackHorse.getColor(), "Black");
    }

    @Test
    void getSymbol() {
        Horse horse = new Horse("White");
        assertEquals(horse.getSymbol(), "H");
    }

    @Test
    void canMoveToPosition() {
        //Проверка недопустимых параметров
        assert (checkNotValid(new Horse("White")));

        //Проверка на чистой доске
        ChessBoard chessBoard = new ChessBoard("White");
        chessBoard.board[3][3] = new Horse("White");
        int[][] validMovies = new int[][]{{4, 5}, {4, 1}, {5, 4}, {5, 2}, {2, 5}, {2, 1}, {1, 4}, {1, 2}};
        assert (checkMovies(chessBoard, validMovies));

        //Проверка с фигурами
        chessBoard.board[4][5] = new Horse("Black");
        chessBoard.board[2][1] = new Rook("Black");
        chessBoard.board[4][1] = new Pawn("White");
        chessBoard.board[5][2] = new Horse("White");
        validMovies = new int[][]{{4, 5}, {5, 4}, {2, 5}, {2, 1}, {1, 4}, {1, 2}};
        assert (checkMovies(chessBoard, validMovies));
    }
}