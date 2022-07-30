import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RookTest {

    @Test
    void getColor() {
        Rook whiteRook = new Rook(Color.WHITE);
        Rook blackRook = new Rook(Color.BLACK);
        assertEquals(whiteRook.getColor(), "White");
        assertEquals(blackRook.getColor(), "Black");
    }

    @Test
    void getSymbol() {
        Rook rook = new Rook(Color.WHITE);
        assertEquals(rook.getSymbol(), "R");
    }

    @Test
    void canMoveToPosition() {
        //Проверка недопустимых параметров
        assert (PieceTest.checkNotValid(new Rook(Color.WHITE)));
        //Проверка на чистой доске
        ChessBoard chessBoard = new ChessBoard();
        chessBoard.board[4][3] = new Rook(Color.BLACK);
        int[][] validMovies = new int[][]{{4, 0}, {4, 1}, {4, 2}, {4, 4}, {4, 5}, {4, 6}, {4, 7}, {0, 3}, {1, 3},
                {2, 3}, {3, 3}, {5, 3}, {6, 3}, {7, 3}};
        assert (PieceTest.checkMovies(chessBoard, 4, 3, validMovies));
        //Проверка с фигурами
        chessBoard.board[4][1] = new Queen(Color.WHITE);
        chessBoard.board[4][6] = new Horse(Color.BLACK);
        chessBoard.board[3][3] = new Pawn(Color.WHITE);
        chessBoard.board[7][3] = new Bishop(Color.BLACK);
        validMovies = new int[][]{{4, 1}, {4, 2}, {4, 4}, {4, 5}, {3, 3}, {5, 3}, {6, 3}};
        assert (PieceTest.checkMovies(chessBoard, 4, 3, validMovies));
    }

    @Test
    void canAttackToPosition() {
        //Проверка недопустимых параметров
        assert (PieceTest.checkNotValid(new Rook(Color.WHITE)));
        //Проверка с фигурами
        ChessBoard chessBoard = new ChessBoard();
        chessBoard.board[3][5] = new Rook(Color.WHITE);
        chessBoard.board[1][5] = new Pawn(Color.BLACK);
        chessBoard.board[5][5] = new Horse(Color.WHITE);
        chessBoard.board[3][2] = new Queen(Color.BLACK);
        chessBoard.board[3][6] = new Bishop(Color.WHITE);
        int[][] validAttacks = new int[][]{{1, 5}, {2, 5}, {4, 5}, {3, 2}, {3, 3}, {3, 4}, {5, 5}, {3, 6}};
        assert (PieceTest.checkAttack(chessBoard, 3, 5, validAttacks));
    }

    @Test
    void is() {
        ChessPiece rook = new Rook(Color.WHITE);
        for (Piece piece : Piece.values()){
            if (piece==Piece.ROOK) {
                assert (rook.is(piece));
            }else {
                assert (!rook.is(piece));
            }
        }
    }
}