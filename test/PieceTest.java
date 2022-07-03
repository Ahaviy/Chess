public class PieceTest {

    protected boolean isNotValid(ChessPiece piece) {
        ChessBoard chessBoard = new ChessBoard("White");
        chessBoard.board[3][3] = piece;
        int[][] notValidMovies = new int[][]{{-1, 5}, {4, -5}, {3, 9}, {8, 6}, {3, 3}};
        if (chessBoard.board[3][3].canMoveToPosition(chessBoard, 3, 3, 3, 3)) {
            return true;
        }
        for (int toLine = -10; toLine < 0; toLine++) {
            for (int toColum = -10; toColum < 0; toColum++) {
                if (chessBoard.board[3][3].canMoveToPosition(chessBoard, 3, 3, toLine, toColum)) {
                    return true;
                }
            }
            for (int toColum = 8; toColum < 18; toColum++) {
                if (chessBoard.board[3][3].canMoveToPosition(chessBoard, 3, 3, toLine, toColum)) {
                    return true;
                }
            }
        }
        for (int toLine = 8; toLine < 18; toLine++) {
            for (int toColum = -10; toColum < 0; toColum++) {
                if (chessBoard.board[3][3].canMoveToPosition(chessBoard, 3, 3, toLine, toColum)) {
                    return true;
                }
            }
            for (int toColum = 8; toColum < 18; toColum++) {
                if (chessBoard.board[3][3].canMoveToPosition(chessBoard, 3, 3, toLine, toColum)) {
                    return true;
                }
            }
        }
        return false;
    }

    protected boolean checkMovies(ChessBoard chessBoard, int[][] validMovies) {
        //Перебираем все клетки доски
        for (int toLine = 0; toLine < 8; toLine++) {
            for (int toColum = 0; toColum < 8; toColum++) {
                if (chessBoard.board[3][3].canMoveToPosition(chessBoard, 3, 3, toLine, toColum)) {
                    //проверка есть ли в списке validMovies текущие координаты
                    boolean isValidMovies = false;
                    for (int[] validMovie : validMovies) {
                        if (validMovie[0] == toLine && validMovie[1] == toColum) {
                            isValidMovies = true;
                            break;
                        }
                    }
                    if(!isValidMovies) return false;
                }

            }
        }
        return true;
    }
}
