import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PawnTest{

    @Test
    void getColor() {
        Pawn whitePawn = new Pawn(ColorPiece.WHITE);
        Pawn blackPawn = new Pawn(ColorPiece.BLACK);
        assertEquals(whitePawn.getColor(), "White");
        assertEquals(blackPawn.getColor(), "Black");
    }

    @Test
    void getSymbol() {
        Pawn whitePawn = new Pawn(ColorPiece.WHITE);
        assertEquals(whitePawn.getSymbol(), "P");
    }

    @Test
    void canMoveToPosition() {
        //Проверка недопустимых параметров
        assert (PieceTest.checkNotValid(new Pawn(ColorPiece.WHITE)));

        //Проверка на чистой доске
        ChessBoard chessBoard = new ChessBoard();
        chessBoard.board[1][5] = new Pawn(ColorPiece.WHITE);
        int[][] validMovies = new int[][]{{2, 5}, {3, 5}};
        assert (PieceTest.checkMovies(chessBoard, 1, 5, validMovies));
        chessBoard = new ChessBoard();
        chessBoard.board[4][7] = new Pawn(ColorPiece.WHITE);
        validMovies = new int[][]{{5, 7}};
        assert (PieceTest.checkMovies(chessBoard, 4, 7, validMovies));
        chessBoard = new ChessBoard();
        chessBoard.board[6][1] = new Pawn(ColorPiece.BLACK);
        validMovies = new int[][]{{5, 1}, {4, 1}};
        assert (PieceTest.checkMovies(chessBoard, 6, 1, validMovies));
        chessBoard = new ChessBoard();
        chessBoard.board[2][3] = new Pawn(ColorPiece.BLACK);
        validMovies = new int[][]{{1, 3}};
        assert (PieceTest.checkMovies(chessBoard, 2, 3, validMovies));

        //Проверка с фигурами
        chessBoard = new ChessBoard();
        chessBoard.board[1][6] = new Pawn(ColorPiece.WHITE);
        chessBoard.board[2][5] = new Pawn(ColorPiece.BLACK);
        chessBoard.board[3][6] = new Horse(ColorPiece.WHITE);
        validMovies = new int[][]{{2, 5}, {2, 6}};
        assert (PieceTest.checkMovies(chessBoard, 1, 6, validMovies));
        chessBoard = new ChessBoard();
        chessBoard.board[4][3] = new Pawn(ColorPiece.WHITE);
        chessBoard.board[5][4] = new Bishop(ColorPiece.BLACK);
        chessBoard.board[4][2] = new Pawn(ColorPiece.BLACK);
        validMovies = new int[][]{{5, 4}, {5, 3}};
        assert (PieceTest.checkMovies(chessBoard, 4, 3, validMovies));
        ((Pawn) chessBoard.board[4][2]).canBeEatenOnTheAisle = true;
        validMovies = new int[][]{{5, 4}, {5, 3}, {5, 2}};
        assert (PieceTest.checkMovies(chessBoard, 4, 3, validMovies));
        chessBoard = new ChessBoard();
        chessBoard.board[6][6] = new Pawn(ColorPiece.BLACK);
        chessBoard.board[5][5] = new Pawn(ColorPiece.WHITE);
        chessBoard.board[5][7] = new Pawn(ColorPiece.BLACK);
        chessBoard.board[5][6] = new Horse(ColorPiece.WHITE);
        validMovies = new int[][]{{5, 5}};
        assert (PieceTest.checkMovies(chessBoard, 6, 6, validMovies));
        chessBoard = new ChessBoard();
        chessBoard.board[3][3] = new Pawn(ColorPiece.BLACK);
        chessBoard.board[3][2] = new Pawn(ColorPiece.WHITE);
        validMovies = new int[][]{{2, 3}};
        assert (PieceTest.checkMovies(chessBoard, 3, 3, validMovies));
        ((Pawn) chessBoard.board[3][2]).canBeEatenOnTheAisle = true;
        validMovies = new int[][]{{2, 3}, {2, 2}};
        assert (PieceTest.checkMovies(chessBoard, 3, 3, validMovies));
    }

    @Test
    void canAttackToPosition() {
        ChessBoard chessBoard = new ChessBoard();
        chessBoard.board[1][6] = new Pawn(ColorPiece.WHITE);
        int[][] validAttacks = new int[][]{{2, 5}, {2,7}};
        assert (PieceTest.checkAttack(chessBoard, 1, 6, validAttacks));
        chessBoard.board[2][5] = new Rook(ColorPiece.WHITE);
        chessBoard.board[2][7] = new Bishop(ColorPiece.BLACK);
        assert (PieceTest.checkAttack(chessBoard, 1, 6, validAttacks));
        chessBoard.board[6][3] = new Pawn(ColorPiece.BLACK);
        validAttacks = new int[][]{{5, 4}, {5,2}};
        assert (PieceTest.checkAttack(chessBoard, 6, 3, validAttacks));
        chessBoard.board[5][4] = new Horse(ColorPiece.WHITE);
        chessBoard.board[5][2] =  new Queen(ColorPiece.BLACK);
        assert (PieceTest.checkAttack(chessBoard, 6, 3, validAttacks));
    }
}