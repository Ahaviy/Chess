import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class debuggingTest {

    @Test
    void test(){
        ChessBoard chessBoard = new ChessBoard();
        chessBoard.board[1][6] = new Pawn(ColorPiece.WHITE);
        int[][] validAttacks = new int[][]{{2, 5}, {2,7}};
        assert (chessBoard.board[1][6].canAttackToPosition(chessBoard, 1,6,2,5));
    }
}
