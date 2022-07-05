import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class debuggingTest {

    @Test
    void test(){
        ChessBoard chessBoard = new ChessBoard();
        chessBoard.board[6][1] = new Pawn(ColorPiece.BLACK);
        assert (chessBoard.board[6][1].canMoveToPosition(chessBoard,6,1,4,1));
    }
}
