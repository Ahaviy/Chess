import org.junit.jupiter.api.Test;

class ChessBoardTest {

    @Test
    void moveToPosition() {
        ChessBoard chessBoard = new ChessBoard();
        chessBoard.board[7][0] = new King(ColorPiece.BLACK);
        chessBoard.board[2][0] = new Rook(ColorPiece.BLACK);
        chessBoard.board[2][7] = new King(ColorPiece.WHITE);
        chessBoard.board[2][6] = new Pawn(ColorPiece.WHITE);
        assert (!chessBoard.moveToPosition(2, 6, 3, 6));
        chessBoard.board[2][7] = null;
        chessBoard.board[5][7] = new King(ColorPiece.WHITE);
        ChessPiece temp = chessBoard.board[2][6];
        assert (chessBoard.moveToPosition(2, 6, 3, 6));
        assert (chessBoard.board[2][6] == null);
        assert (chessBoard.board[3][6].equals(temp));
        chessBoard = new ChessBoard();
        chessBoard.newGame();
        chessBoard.moveToPosition(0, 6, 2, 7);
        chessBoard.moveToPosition(6, 1, 4, 1);
        chessBoard.moveToPosition(1, 6, 3, 6);
        chessBoard.moveToPosition(4, 1, 3, 1);
        chessBoard.moveToPosition(1, 2, 3, 2);
        ChessBoard copyBoard = chessBoard.copyThisChessBoard();
        temp = chessBoard.board[3][1];
        assert (chessBoard.moveToPosition(3, 1, 2, 2));
        assert (chessBoard.board[3][1] == null);
        assert (chessBoard.board[2][2].equals(temp));
        assert (chessBoard.board[3][2] == null);
        copyBoard.moveToPosition(6, 5, 4, 5);
        copyBoard.moveToPosition(1, 5, 3, 5);
        assert (!copyBoard.moveToPosition(3, 1, 2, 2));
    }

    @Test
    void isCheckMate() {
        ChessBoard chessBoard = new ChessBoard();
        chessBoard.board[1][0] = new Pawn(ColorPiece.WHITE);
        chessBoard.board[1][1] = new Pawn(ColorPiece.WHITE);
        chessBoard.board[1][2] = new Pawn(ColorPiece.WHITE);
        chessBoard.board[7][0] = new King(ColorPiece.BLACK);
        chessBoard.board[0][1] = new King(ColorPiece.WHITE);
        chessBoard.board[0][7] = new Rook(ColorPiece.BLACK);
        assert (chessBoard.isCheckMate());
        chessBoard.board[1][0] = null;
        assert (!chessBoard.isCheckMate());
        chessBoard = new ChessBoard();
        chessBoard.newGame();
        chessBoard.moveToPosition(1, 4, 3, 4);
        chessBoard.moveToPosition(6, 4, 4, 4);
        chessBoard.moveToPosition(0, 3, 2, 5);
        chessBoard.moveToPosition(7, 1, 5, 0);
        chessBoard.moveToPosition(0, 5, 3, 2);
        chessBoard.moveToPosition(6, 1, 4, 1);
        assert (!chessBoard.isCheckMate());
        chessBoard.moveToPosition(2, 5, 6, 5);
        assert (chessBoard.isCheckMate());
    }

    @Test
    void castling0() {
        ChessBoard chessBoard = new ChessBoard();
        chessBoard.newGame();
        chessBoard.board[0][1] = null;
        chessBoard.board[0][2] = null;
        assert (!chessBoard.castling0());
        chessBoard.board[0][3] = null;
        chessBoard.board[0][2] = new Bishop(ColorPiece.WHITE);
        assert (!chessBoard.castling0());
        chessBoard.board[0][2] = null;
        chessBoard.board[0][1] = new Horse(ColorPiece.WHITE);
        assert (!chessBoard.castling0());
        chessBoard.board[0][1] = null;
        chessBoard.board[0][4].check = false;
        assert (!chessBoard.castling0());
        chessBoard.board[0][4].check = true;
        chessBoard.board[0][0].check = false;
        assert (!chessBoard.castling0());
        chessBoard.board[0][0].check = true;
        chessBoard.board[3][2] = new Rook(ColorPiece.BLACK);
        chessBoard.board[3][3] = new Rook(ColorPiece.BLACK);
        chessBoard.board[3][4] = new Rook(ColorPiece.BLACK);
        chessBoard.board[1][2] = null;
        assert (!chessBoard.castling0());
        chessBoard.board[1][2] = new Pawn(ColorPiece.WHITE);
        chessBoard.board[1][3] = null;
        assert (!chessBoard.castling0());
        chessBoard.board[1][3] = new Pawn(ColorPiece.WHITE);
        chessBoard.board[1][4] = null;
        assert (!chessBoard.castling0());
        chessBoard.board[1][4] = new Pawn(ColorPiece.WHITE);
        assert (chessBoard.castling0());
        assert (chessBoard.board[0][0] == null && chessBoard.board[0][4] == null);
        assert (chessBoard.board[0][2] != null && chessBoard.board[0][2].isKing() && chessBoard.board[0][2].isWhite()
                && !chessBoard.board[0][2].check);
        assert (chessBoard.board[0][3] != null && chessBoard.board[0][3].isRook() && chessBoard.board[0][3].isWhite()
                && !chessBoard.board[0][3].check);
        chessBoard.board[7][1] = null;
        chessBoard.board[7][2] = null;
        assert (!chessBoard.castling0());
        chessBoard.board[7][3] = null;
        chessBoard.board[7][2] = new Bishop(ColorPiece.BLACK);
        assert (!chessBoard.castling0());
        chessBoard.board[7][2] = null;
        chessBoard.board[7][1] = new Horse(ColorPiece.BLACK);
        assert (!chessBoard.castling0());
        chessBoard.board[7][1] = null;
        chessBoard.board[7][4].check = false;
        assert (!chessBoard.castling0());
        chessBoard.board[7][4].check = true;
        chessBoard.board[7][0].check = false;
        assert (!chessBoard.castling0());
        chessBoard.board[7][0].check = true;
        chessBoard.board[4][2] = new Rook(ColorPiece.WHITE);
        chessBoard.board[4][3] = new Rook(ColorPiece.WHITE);
        chessBoard.board[4][4] = new Rook(ColorPiece.WHITE);
        chessBoard.board[6][2] = null;
        assert (!chessBoard.castling0());
        chessBoard.board[6][2] = new Pawn(ColorPiece.BLACK);
        chessBoard.board[6][3] = null;
        assert (!chessBoard.castling0());
        chessBoard.board[6][3] = new Pawn(ColorPiece.BLACK);
        chessBoard.board[6][4] = null;
        assert (!chessBoard.castling0());
        chessBoard.board[6][4] = new Pawn(ColorPiece.BLACK);
        assert (chessBoard.castling0());
        assert (chessBoard.board[7][0] == null && chessBoard.board[7][4] == null);
        assert (chessBoard.board[7][2] != null && chessBoard.board[7][2].isKing() && chessBoard.board[7][2].isBlack()
                && !chessBoard.board[7][2].check);
        assert (chessBoard.board[7][3] != null && chessBoard.board[7][3].isRook() && chessBoard.board[7][3].isBlack()
                && !chessBoard.board[7][3].check);
    }

    @Test
    void castling7() {
        ChessBoard chessBoard = new ChessBoard();
        chessBoard.newGame();
        chessBoard.board[0][6] = null;
        assert (!chessBoard.castling7());
        chessBoard.board[0][5] = null;
        chessBoard.board[0][6] = new Horse(ColorPiece.WHITE);
        assert (!chessBoard.castling7());
        chessBoard.board[0][6] = null;
        chessBoard.board[0][4].check = false;
        assert (!chessBoard.castling7());
        chessBoard.board[0][4].check = true;
        chessBoard.board[0][7].check = false;
        assert (!chessBoard.castling7());
        chessBoard.board[0][7].check = true;
        chessBoard.board[3][6] = new Rook(ColorPiece.BLACK);
        chessBoard.board[3][5] = new Rook(ColorPiece.BLACK);
        chessBoard.board[3][4] = new Rook(ColorPiece.BLACK);
        chessBoard.board[1][4] = null;
        assert (!chessBoard.castling7());
        chessBoard.board[1][4] = new Pawn(ColorPiece.WHITE);
        chessBoard.board[1][5] = null;
        assert (!chessBoard.castling7());
        chessBoard.board[1][5] = new Pawn(ColorPiece.WHITE);
        chessBoard.board[1][6] = null;
        assert (!chessBoard.castling7());
        chessBoard.board[1][6] = new Pawn(ColorPiece.WHITE);
        assert (chessBoard.castling7());
        assert (chessBoard.board[0][7] == null && chessBoard.board[0][4] == null);
        assert (chessBoard.board[0][6] != null && chessBoard.board[0][6].isKing() && chessBoard.board[0][6].isWhite()
                && !chessBoard.board[0][6].check);
        assert (chessBoard.board[0][5] != null && chessBoard.board[0][5].isRook() && chessBoard.board[0][5].isWhite()
                && !chessBoard.board[0][5].check);
        chessBoard.board[7][6] = null;
        assert (!chessBoard.castling7());
        chessBoard.board[7][5] = null;
        chessBoard.board[7][6] = new Horse(ColorPiece.BLACK);
        assert (!chessBoard.castling7());
        chessBoard.board[7][6] = null;
        chessBoard.board[7][4].check = false;
        assert (!chessBoard.castling7());
        chessBoard.board[7][4].check = true;
        chessBoard.board[7][7].check = false;
        assert (!chessBoard.castling7());
        chessBoard.board[7][7].check = true;
        chessBoard.board[4][6] = new Rook(ColorPiece.WHITE);
        chessBoard.board[4][5] = new Rook(ColorPiece.WHITE);
        chessBoard.board[4][4] = new Rook(ColorPiece.WHITE);
        chessBoard.board[6][6] = null;
        assert (!chessBoard.castling7());
        chessBoard.board[6][6] = new Pawn(ColorPiece.BLACK);
        chessBoard.board[6][5] = null;
        assert (!chessBoard.castling7());
        chessBoard.board[6][5] = new Pawn(ColorPiece.BLACK);
        chessBoard.board[6][4] = null;
        assert (!chessBoard.castling7());
        chessBoard.board[6][4] = new Pawn(ColorPiece.BLACK);
        assert (chessBoard.castling7());
        assert (chessBoard.board[7][7] == null && chessBoard.board[7][4] == null);
        assert (chessBoard.board[7][6] != null && chessBoard.board[7][6].isKing() && chessBoard.board[7][6].isBlack()
                && !chessBoard.board[7][6].check);
        assert (chessBoard.board[7][5] != null && chessBoard.board[7][5].isRook() && chessBoard.board[7][5].isBlack()
                && !chessBoard.board[7][5].check);
    }


    @Test
    void isStaleMate() {
        ChessBoard chessBoard = new ChessBoard();
        chessBoard.board[7][7] = new King(ColorPiece.BLACK);
        chessBoard.board[6][7] = new Pawn(ColorPiece.WHITE);
        chessBoard.board[4][7] = new Horse(ColorPiece.WHITE);
        chessBoard.board[3][4] = new Bishop(ColorPiece.WHITE);
        chessBoard.board[3][0] = new Pawn(ColorPiece.BLACK);
        chessBoard.board[1][0] = new King(ColorPiece.WHITE);
        assert (!chessBoard.isStaleMate());
        chessBoard.moveToPosition(1,0,2,0);
        assert (chessBoard.isStaleMate());
    }
}