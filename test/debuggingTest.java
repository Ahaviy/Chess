import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class debuggingTest {

    @Test
    void test(){
        ChessBoard chessBoard = new ChessBoard();
        chessBoard.board[3][3] = new King(ColorPiece.BLACK);
        chessBoard.board[1][2] = new Pawn(ColorPiece.WHITE);
        chessBoard.board[1][3] = new Pawn(ColorPiece.WHITE);
        chessBoard.board[5][5] = new Horse(ColorPiece.WHITE);
        assert (!chessBoard.board[3][3].canMoveToPosition(chessBoard,3,3,2,2));
    }
}
