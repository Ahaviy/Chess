import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QueenTest {

    @Test
    void getColor() {
        Queen whiteQueen = new Queen(ColorPiece.WHITE);
        Queen blackQueen = new Queen(ColorPiece.BLACK);
        assertEquals(whiteQueen.getColor(), "White");
        assertEquals(blackQueen.getColor(), "Black");
    }

    @Test
    void getSymbol() {
        Queen queen = new Queen(ColorPiece.WHITE);
        assertEquals(queen.getSymbol(), "Q");
    }

    @Test
    void canMoveToPosition() {
        //Проверка недопустимых параметров
        assert (PieceTest.checkNotValid(new Queen(ColorPiece.WHITE)));
        //Проверка на чистой доске
        ChessBoard chessBoard = new ChessBoard();
        chessBoard.board[4][2] = new Queen(ColorPiece.BLACK);
        int[][] validMovies = new int[][]{{3, 1}, {2, 0}, {4, 1}, {4, 0}, {5, 1}, {5, 2}, {6, 2}, {7, 2}, {5, 3},
                {6, 4}, {7, 5}, {4, 3}, {4, 4}, {4, 5}, {4, 6}, {4, 7}, {3, 3}, {2, 4}, {1, 5}, {0, 6}, {3, 2}, {3, 1},
                {0, 2}, {1, 2}, {2, 2}, {3, 2}, {6, 0}, {5, 1}};
        assert (PieceTest.checkMovies(chessBoard, 4, 2, validMovies));
        //Проверка с фигурами
        chessBoard.board[3][2] = new Pawn(ColorPiece.BLACK);
        chessBoard.board[4][1] = new Rook(ColorPiece.WHITE);
        chessBoard.board[5][2] = new Bishop(ColorPiece.WHITE);
        chessBoard.board[4][6] = new Horse(ColorPiece.BLACK);
        chessBoard.board[3][1] = new Rook(ColorPiece.WHITE);
        chessBoard.board[3][3] = new Pawn(ColorPiece.BLACK);
        chessBoard.board[6][4] = new Horse(ColorPiece.WHITE);
        chessBoard.board[5][1] = new Queen(ColorPiece.BLACK);
        validMovies = new int[][]{{3, 1}, {4, 1}, {5, 2}, {5, 3}, {6, 4}, {4, 3}, {4, 4}, {4, 5}, {3, 1}};
        assert (PieceTest.checkMovies(chessBoard, 4, 2, validMovies));
    }

    @Test
    void canAttackToPosition() {
        //Проверка недопустимых параметров
        assert (PieceTest.checkNotValid(new Queen(ColorPiece.WHITE)));
        //Проверка с фигурами
        ChessBoard chessBoard = new ChessBoard();
        chessBoard.board[4][2] = new Queen(ColorPiece.WHITE);
        chessBoard.board[2][2] = new Horse(ColorPiece.BLACK);
        chessBoard.board[6][2] = new Horse(ColorPiece.BLACK);
        chessBoard.board[4][1] = new Bishop(ColorPiece.WHITE);
        chessBoard.board[4][5] = new Rook(ColorPiece.WHITE);
        chessBoard.board[5][1] = new Rook(ColorPiece.WHITE);
        chessBoard.board[6][4] = new Pawn(ColorPiece.WHITE);
        chessBoard.board[7][5] = new Queen(ColorPiece.BLACK);
        chessBoard.board[3][1] = new Horse(ColorPiece.BLACK);
        int[][] validAttacks = new int[][]{{3, 1}, {5, 2}, {6, 2}, {5, 3}, {4, 3}, {4, 4}, {3, 3}, {2, 4}, {1, 5},
                {0, 6}, {3, 2}, {3, 1}, {2, 2}, {3, 2}, {4, 1}, {4, 5}, {5, 1}, {6, 4}};
        assert (PieceTest.checkAttack(chessBoard, 4, 2, validAttacks));


    }
}