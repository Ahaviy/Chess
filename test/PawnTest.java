import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PawnTest{

    @Test
    void getColor() {
        Pawn whitePawn = new Pawn(Color.WHITE);
        Pawn blackPawn = new Pawn(Color.BLACK);
        assertEquals(whitePawn.getColor(), "White");
        assertEquals(blackPawn.getColor(), "Black");
    }

    @Test
    void getSymbol() {
        Pawn whitePawn = new Pawn(Color.WHITE);
        assertEquals(whitePawn.getSymbol(), "P");
    }

    @Test
    void canMoveToPosition() {
        //Проверка недопустимых параметров
        assert (PieceTest.checkNotValid(new Pawn(Color.WHITE)));

        //Проверка на чистой доске
        ChessBoard chessBoard = new ChessBoard();
        chessBoard.board[1][5] = new Pawn(Color.WHITE);
        int[][] validMovies = new int[][]{{2, 5}, {3, 5}};
        assert (PieceTest.checkMovies(chessBoard, 1, 5, validMovies));
        chessBoard = new ChessBoard();
        chessBoard.board[4][7] = new Pawn(Color.WHITE);
        validMovies = new int[][]{{5, 7}};
        assert (PieceTest.checkMovies(chessBoard, 4, 7, validMovies));
        chessBoard = new ChessBoard();
        chessBoard.board[6][1] = new Pawn(Color.BLACK);
        validMovies = new int[][]{{5, 1}, {4, 1}};
        assert (PieceTest.checkMovies(chessBoard, 6, 1, validMovies));
        chessBoard = new ChessBoard();
        chessBoard.board[2][3] = new Pawn(Color.BLACK);
        validMovies = new int[][]{{1, 3}};
        assert (PieceTest.checkMovies(chessBoard, 2, 3, validMovies));

        //Проверка с фигурами
        chessBoard = new ChessBoard();
        chessBoard.board[1][6] = new Pawn(Color.WHITE);
        chessBoard.board[2][5] = new Pawn(Color.BLACK);
        chessBoard.board[3][6] = new Horse(Color.WHITE);
        validMovies = new int[][]{{2, 5}, {2, 6}};
        assert (PieceTest.checkMovies(chessBoard, 1, 6, validMovies));
        chessBoard = new ChessBoard();
        chessBoard.board[4][3] = new Pawn(Color.WHITE);
        chessBoard.board[5][4] = new Bishop(Color.BLACK);
        chessBoard.board[4][2] = new Pawn(Color.BLACK);
        validMovies = new int[][]{{5, 4}, {5, 3}};
        assert (PieceTest.checkMovies(chessBoard, 4, 3, validMovies));
        ((Pawn) chessBoard.board[4][2]).canBeEatenOnTheAisle = true;
        validMovies = new int[][]{{5, 4}, {5, 3}, {5, 2}};
        assert (PieceTest.checkMovies(chessBoard, 4, 3, validMovies));
        chessBoard = new ChessBoard();
        chessBoard.board[6][6] = new Pawn(Color.BLACK);
        chessBoard.board[5][5] = new Pawn(Color.WHITE);
        chessBoard.board[5][7] = new Pawn(Color.BLACK);
        chessBoard.board[5][6] = new Horse(Color.WHITE);
        validMovies = new int[][]{{5, 5}};
        assert (PieceTest.checkMovies(chessBoard, 6, 6, validMovies));
        chessBoard = new ChessBoard();
        chessBoard.board[3][3] = new Pawn(Color.BLACK);
        chessBoard.board[3][2] = new Pawn(Color.WHITE);
        validMovies = new int[][]{{2, 3}};
        assert (PieceTest.checkMovies(chessBoard, 3, 3, validMovies));
        ((Pawn) chessBoard.board[3][2]).canBeEatenOnTheAisle = true;
        validMovies = new int[][]{{2, 3}, {2, 2}};
        assert (PieceTest.checkMovies(chessBoard, 3, 3, validMovies));
    }

    @Test
    void canAttackToPosition() {
        ChessBoard chessBoard = new ChessBoard();
        chessBoard.board[1][6] = new Pawn(Color.WHITE);
        int[][] validAttacks = new int[][]{{2, 5}, {2,7}};
        assert (PieceTest.checkAttack(chessBoard, 1, 6, validAttacks));
        chessBoard.board[2][5] = new Rook(Color.WHITE);
        chessBoard.board[2][7] = new Bishop(Color.BLACK);
        assert (PieceTest.checkAttack(chessBoard, 1, 6, validAttacks));
        chessBoard.board[6][3] = new Pawn(Color.BLACK);
        validAttacks = new int[][]{{5, 4}, {5,2}};
        assert (PieceTest.checkAttack(chessBoard, 6, 3, validAttacks));
        chessBoard.board[5][4] = new Horse(Color.WHITE);
        chessBoard.board[5][2] =  new Queen(Color.BLACK);
        assert (PieceTest.checkAttack(chessBoard, 6, 3, validAttacks));
    }

    @Test
    void is() {
        ChessPiece pawn = new Pawn(Color.WHITE);
        for (Piece piece : Piece.values()){
            if (piece==Piece.PAWN) {
                assert (pawn.is(piece));
            }else {
                assert (!pawn.is(piece));
            }
        }
    }
}