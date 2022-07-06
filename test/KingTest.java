import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KingTest {

    @Test
    void getColor() {
        King whiteKing = new King(ColorPiece.WHITE);
        King blackKing = new King(ColorPiece.BLACK);
        assertEquals(whiteKing.getColor(), "White");
        assertEquals(blackKing.getColor(), "Black");
    }

    @Test
    void getSymbol() {
        King king = new King(ColorPiece.WHITE);
        assertEquals(king.getSymbol(), "K");
    }

    @Test
    void canMoveToPosition() {
        //Проверка недопустимых параметров
        assert (PieceTest.checkNotValid(new King(ColorPiece.WHITE)));
        //Проверка на чистой доске
        ChessBoard chessBoard = new ChessBoard();
        chessBoard.board[3][3] = new King(ColorPiece.WHITE);
        int[][] validMovies = new int[][]{{2,3},{4,3},{3,2},{3,4},{4,4},{2,2},{4,2},{2,4}};
        assert (PieceTest.checkMovies(chessBoard, 3, 3, validMovies));
        //Проверка с фигурами
        chessBoard.board[2][3] = new Pawn(ColorPiece.BLACK);
        chessBoard.board[4][2] = new Pawn(ColorPiece.WHITE);
        chessBoard.board[5][4] = new Rook(ColorPiece.BLACK);
        validMovies = new int[][]{{2,3},{4,3},{3,2},{2,2}};
        assert (PieceTest.checkMovies(chessBoard, 3, 3, validMovies));
        chessBoard = new ChessBoard();
        chessBoard.board[3][3] = new King(ColorPiece.BLACK);
        chessBoard.board[1][2] = new Pawn(ColorPiece.WHITE);
        chessBoard.board[1][3] = new Pawn(ColorPiece.WHITE);
        chessBoard.board[5][5] = new Horse(ColorPiece.WHITE);
        validMovies = new int[][]{{3,2},{4,4},{4,2}};
        assert (PieceTest.checkMovies(chessBoard, 3, 3, validMovies));
        chessBoard = new ChessBoard();
        chessBoard.board[3][3] = new King(ColorPiece.BLACK);
        chessBoard.board[1][2] = new Queen(ColorPiece.WHITE);
        chessBoard.board[5][4] = new Bishop(ColorPiece.WHITE);
        validMovies = new int[][]{{4,4},{2,4}};
        assert (PieceTest.checkMovies(chessBoard, 3, 3, validMovies));
    }

    @Test
    void isUnderAttack() {
        ChessBoard chessBoard = new ChessBoard();
        chessBoard.board[3][3] = new King(ColorPiece.WHITE);
        chessBoard.board[1][3] = new King(ColorPiece.BLACK);
        int[][] validMovies = new int[][]{{4,3},{3,2},{3,4},{4,4},{4,2}};
        assert (PieceTest.checkMovies(chessBoard, 3, 3, validMovies));
        int[][] validAttacks= new int[][]{{2,3},{4,3},{3,2},{3,4},{4,4},{2,2},{4,2},{2,4}};
        assert (PieceTest.checkAttack(chessBoard,3,3,validAttacks));
    }
}