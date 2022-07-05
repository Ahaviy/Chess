public class PieceTest {

    protected boolean checkNotValid(ChessPiece piece) {
        ChessBoard chessBoard = new ChessBoard();
        chessBoard.board[3][3] = piece;

        //Проверка собственных координат
        if (chessBoard.board[3][3].canMoveToPosition(chessBoard, 3, 3, 3, 3)) {
            return false;
        }

        //Проверка четырех диапозонов вне доски
        for (int toLine = -10; toLine < 0; toLine++) {
            for (int toColum = -10; toColum < 8; toColum++) {
                if (chessBoard.board[3][3].canMoveToPosition(chessBoard, 3, 3, toLine, toColum)) {
                    return false;
                }
            }
        }
        for (int toLine = -10; toLine < 8; toLine++) {
            for (int toColum = 8; toColum < 18; toColum++) {
                if (chessBoard.board[3][3].canMoveToPosition(chessBoard, 3, 3, toLine, toColum)) {
                    return false;
                }
            }
        }
        for (int toLine = 0; toLine < 18; toLine++) {
            for (int toColum = -10; toColum < 0; toColum++) {
                if (chessBoard.board[3][3].canMoveToPosition(chessBoard, 3, 3, toLine, toColum)) {
                    return false;
                }
            }
        }
        for (int toLine = 8; toLine < 18; toLine++) {
            for (int toColum = 0; toColum < 18; toColum++) {
                if (chessBoard.board[3][3].canMoveToPosition(chessBoard, 3, 3, toLine, toColum)) {
                    return false;
                }
            }
        }
        return true;
    }

    protected boolean checkMovies(ChessBoard chessBoard, int line, int colum, int[][] validMovies) {
        //Перебираем все клетки доски
        for (int toLine = 0; toLine < 8; toLine++) {
            for (int toColum = 0; toColum < 8; toColum++) {
                if (chessBoard.board[line][colum].canMoveToPosition(chessBoard, line, colum, toLine, toColum)) {
                    //проверка есть ли в списке validMovies текущие координаты
                    boolean isValidMovies = false;
                    for (int[] validMovie : validMovies) {
                        if (validMovie[0] == toLine && validMovie[1] == toColum) {
                            isValidMovies = true;
                            break;
                        }
                    }
                    if (!isValidMovies) {
                        System.out.println("Coordinates not in the list: " + toLine + ", " + toColum);
                        return false;
                    }
                }
            }
        }

        for (int[] validMovie : validMovies) {
            if (!chessBoard.board[line][colum].canMoveToPosition(chessBoard, line, colum, validMovie[0], validMovie[1])) {
                System.out.println("Coordinates in the list not valid: " + validMovie[0] + ", " + validMovie[1]);
                return false;
            }
        }
        return true;
    }

    protected boolean checkAttack(ChessBoard chessBoard, int line, int colum, int[][] validAttacks) {
        //Перебираем все клетки доски
        for (int toLine = 0; toLine < 8; toLine++) {
            for (int toColum = 0; toColum < 8; toColum++) {
                if (chessBoard.board[line][colum].canAttackToPosition(chessBoard, line, colum, toLine, toColum)) {
                    //проверка есть ли в списке validAttacks текущие координаты
                    boolean isValidAttacks = false;
                    for (int[] validAttack : validAttacks) {
                        if (validAttack[0] == toLine && validAttack[1] == toColum) {
                            isValidAttacks = true;
                            break;
                        }
                    }
                    if (!isValidAttacks) {
                        System.out.println("Coordinates not in the list: " + toLine + ", " + toColum);
                        return false;
                    }
                }
            }
        }
        for (int[] validAttack : validAttacks) {
            if (!chessBoard.board[line][colum].canMoveToPosition(chessBoard, line, colum, validAttack[0], validAttack[1])) {
                System.out.println("Coordinates in the list not valid: " + validAttack[0] + ", " + validAttack[1]);
                return false;
            }
        }
        return true;
    }

}
