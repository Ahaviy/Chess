import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class debuggingTest {

    @Test
    void test(){
        ChessBoard chessBoard = new ChessBoard();
        chessBoard.board[3][3] = new Bishop(ColorPiece.WHITE);
        chessBoard.board[4][4] = new Pawn(ColorPiece.WHITE);
        assert (!chessBoard.board[3][3].canMoveToPosition(chessBoard,3,3,5,5));
    }
}
