import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HorseTest {

    @Test
    void getColor() {
        Horse whiteHorse = new Horse(Color.WHITE);
        Horse blackHorse = new Horse(Color.BLACK);
        assertEquals(whiteHorse.getColor(), "White");
        assertEquals(blackHorse.getColor(), "Black");
    }

    @Test
    void getSymbol() {
        Horse horse = new Horse(Color.WHITE);
        assertEquals(horse.getSymbol(), "H");
    }

    @Test
    void canMoveToPosition() {
        //Проверка недопустимых параметров
        assert (PieceTest.checkNotValid(new Horse(Color.WHITE)));
        //Проверка на чистой доске
        ChessBoard chessBoard = new ChessBoard();
        chessBoard.board[3][3] = new Horse(Color.WHITE);
        int[][] validMovies = new int[][]{{4, 5}, {4, 1}, {5, 4}, {5, 2}, {2, 5}, {2, 1}, {1, 4}, {1, 2}};
        assert (PieceTest.checkMovies(chessBoard, 3, 3, validMovies));
        //Проверка с фигурами
        chessBoard.board[4][5] = new Horse(Color.BLACK);
        chessBoard.board[2][1] = new Rook(Color.BLACK);
        chessBoard.board[4][1] = new Pawn(Color.WHITE);
        chessBoard.board[5][2] = new Horse(Color.WHITE);
        validMovies = new int[][]{{4, 5}, {5, 4}, {2, 5}, {2, 1}, {1, 4}, {1, 2}};
        assert (PieceTest.checkMovies(chessBoard, 3, 3, validMovies));
    }

    @Test
    void canAttackToPosition() {
        ChessBoard chessBoard = new ChessBoard();
        chessBoard.board[3][3] = new Horse(Color.BLACK);
        chessBoard.board[1][4] = new Horse(Color.BLACK);
        chessBoard.board[2][5] = new Rook(Color.BLACK);
        chessBoard.board[4][1] = new Pawn(Color.WHITE);
        chessBoard.board[4][5] = new Horse(Color.WHITE);
        int[][] validMovies = new int[][]{{4, 5}, {4, 1}, {5, 4}, {5, 2}, {2, 1}, {1, 2}, {1, 4}, {2, 5}};
        assert (PieceTest.checkAttack(chessBoard, 3, 3, validMovies));
    }

    @Test
    void is() {
        ChessPiece horse = new Horse(Color.BLACK);
        for (Piece piece : Piece.values()){
            if (piece==Piece.HORSE) {
                assert (horse.is(piece));
            }else {
                assert (!horse.is(piece));
            }
        }
    }
}