import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BishopTest {

    @Test
    void getColor() {
        Bishop whiteBishop = new Bishop(ColorPiece.WHITE);
        Bishop blackBishop = new Bishop(ColorPiece.BLACK);
        assertEquals(whiteBishop.getColor(), "White");
        assertEquals(blackBishop.getColor(), "Black");
    }

    @Test
    void getSymbol() {
        Bishop bishop = new Bishop(ColorPiece.WHITE);
        assertEquals(bishop.getSymbol(), "B");
    }

    @Test
    void canMoveToPosition() {
        //Проверка недопустимых параметров
        assert (PieceTest.checkNotValid(new Bishop(ColorPiece.WHITE)));
        //Проверка на чистой доске
        ChessBoard chessBoard = new ChessBoard();
        chessBoard.board[3][3] = new Bishop(ColorPiece.WHITE);
        int[][] validMovies = new int[][]{{0, 0}, {1, 1}, {2, 2}, {4, 4}, {5, 5}, {6, 6}, {7, 7}, {4, 2}, {5, 1},
                {6, 0}, {2, 4}, {1, 5}, {0, 6}};
        assert (PieceTest.checkMovies(chessBoard, 3, 3, validMovies));
        //Проверка с фигурами
        chessBoard.board[4][4] = new Pawn(ColorPiece.WHITE);
        chessBoard.board[1][5] = new Bishop(ColorPiece.BLACK);
        chessBoard.board[5][1] = new Pawn(ColorPiece.WHITE);
        chessBoard.board[0][0] = new Horse(ColorPiece.BLACK);
        validMovies = new int[][]{{0, 0}, {1, 1}, {2, 2}, {4, 2}, {2, 4}, {1, 5}};
        assert (PieceTest.checkMovies(chessBoard, 3, 3, validMovies));
    }

    @Test
    void canAttackToPosition() {
        //Проверка недопустимых параметров
        assert (PieceTest.checkNotValid(new Bishop(ColorPiece.WHITE)));
        //Проверка с фигурами
        ChessBoard chessBoard = new ChessBoard();
        chessBoard.board[5][4] = new Bishop(ColorPiece.BLACK);
        chessBoard.board[7][2] = new Horse(ColorPiece.WHITE);
        chessBoard.board[6][3] = new Horse(ColorPiece.BLACK);
        chessBoard.board[2][7] = new Rook(ColorPiece.WHITE);
        int[][] validAttack = new int[][]{{6, 5}, {7, 6}, {4, 5}, {3, 6}, {2, 7}, {4, 3}, {3, 2}, {2, 1}, {1, 0}};
        assert(PieceTest.checkAttack(chessBoard, 5,4, validAttack));

    }
}