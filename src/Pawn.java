public class Pawn extends ChessPiece {

    boolean canBeEatenOnTheAisle = false;

    public Pawn(String color) {
        super(color);
    }

    public Pawn(ColorPiece colorPiece) {
        super(colorPiece);
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public String getSymbol() {
        return "P";
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int colum, int toLine, int toColumn) {
        if (isCoordinatesNotValid(line, toLine, colum, toColumn)) return false;
        if (colum == toColumn) {
            if (isWhite()) {
                if (line == 1 && toLine == 3 && chessBoard.board[2][colum] == null
                        && chessBoard.board[3][colum] == null) return true;
                if ((toLine - line) == 1 && chessBoard.board[toLine][toColumn] == null) return true;
            } else {
                if (line == 6 && toLine == 4 && chessBoard.board[5][colum] == null
                        && chessBoard.board[4][colum] == null) return true;
                if ((line - toLine) == 1 && chessBoard.board[toLine][toColumn] == null) return true;
            }
            return false;
        } else {
            if (canTakeOnTheAisle(chessBoard, line, colum, toLine, toColumn)) return true; //Проверка взятия на проходе
            if ((colum - toColumn) == 1 || (toColumn - colum) == 1) {
                if (isWhite()) {
                    if ((toLine - line) == 1 && chessBoard.board[toLine][toColumn] != null
                            && !chessBoard.board[toLine][toColumn].isWhite()) return true;
                } else {
                    if ((line - toLine) == 1 && chessBoard.board[toLine][toColumn] != null
                            && chessBoard.board[toLine][toColumn].isWhite()) return true;
                }
            }
            return false;
        }
    }

    @Override
    public boolean canAttackToPosition(ChessBoard chessBoard, int line, int colum, int toLine, int toColumn) {
        if (isCoordinatesNotValid(line, toLine, colum, toColumn)) return false;
        if ((colum - toColumn) == 1 || (toColumn - colum) == 1) {
            if (isWhite()) {
                if ((toLine - line) == 1) return true;
            } else {
                if ((line - toLine) == 1) return true;
            }
        }
        return false;
    }


    public boolean canTakeOnTheAisle(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (chessBoard.board[line][column].isWhite()) {
            if (line == 4 && toLine == 5 //Пешка стоит на 4-й линии
                    && chessBoard.board[4][toColumn] != null //На целевой колонке и 4-й линии есть фигура
                    && chessBoard.board[4][toColumn].isPawn() // это пешка
                    && chessBoard.board[4][toColumn].isBlack() //она черная
                    && ((Pawn) chessBoard.board[4][toColumn]).canBeEatenOnTheAisle)  //имеет флаг что
                //ходила в предыдущий ход первым ходом на 2 клетки
                return true;
        } else {
            if (line == 3 && toLine == 2 //Пешка стоит на 3-й линии
                    && chessBoard.board[3][toColumn] != null //На целевой колонке и 3-й линии есть фигура
                    && chessBoard.board[3][toColumn].isPawn() // это пешка
                    && chessBoard.board[3][toColumn].isWhite() //она белая
                    && ((Pawn) chessBoard.board[3][toColumn]).canBeEatenOnTheAisle)  //имеет флаг что
                //ходила в предыдущий ход первым ходом на 2 клетки
                return true;
        }
        return false;
    }

}
