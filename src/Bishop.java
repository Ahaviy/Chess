public class Bishop extends ChessPiece {


    public Bishop(String color) {
        super(color);
    }

    public Bishop(Color color) {
        super(color);
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public String getSymbol() {
        return "B";
    }

    @Override
    public boolean is(Piece piece) {
        if (piece == null) return false;
        return piece == Piece.BISHOP;
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int colum, int toLine, int toColumn) {
        if (isCoordinatesNotValid(line, toLine, colum, toColumn)) return false;
        if (chessBoard.board[toLine][toColumn] != null && isSameColor(chessBoard.board[toLine][toColumn])) return false;
        return canAttackToPosition(chessBoard, line, colum, toLine, toColumn);
    }

    @Override
    public boolean canAttackToPosition(ChessBoard chessBoard, int line, int colum, int toLine, int toColumn) {
        if (isCoordinatesNotValid(line, toLine, colum, toColumn)) return false;

        if (line - toLine == colum - toColumn) { //Проверка возрастающей диагонали
            if (line > toLine) { //левая часть
                for (int checkingLine = toLine + 1; checkingLine < line; checkingLine++) {
                    int checkingColum = checkingLine - line + colum;
                    if (chessBoard.board[checkingLine][checkingColum] != null) return false; //если на пути есть фигура
                }
            } else { //правая часть
                for (int checkingLine = toLine - 1; checkingLine > line; checkingLine--) {
                    int checkingColum = checkingLine - line + colum;
                    if (chessBoard.board[checkingLine][checkingColum] != null) return false; //если на пути есть фигура
                }
            }
            return true;
        }
        if (line - toLine == toColumn - colum) { //Поверка убывающей диагонали
            if (line > toLine) { //левая часть
                for (int checkingLine = toLine + 1; checkingLine < line; checkingLine++) {
                    int checkingColum = line - checkingLine + colum;
                    if (chessBoard.board[checkingLine][checkingColum] != null) return false; //если на пути есть фигура
                }
            } else { //правая часть
                for (int checkingLine = toLine - 1; checkingLine > line; checkingLine--) {
                    int checkingColum = line - checkingLine + colum;
                    if (chessBoard.board[checkingLine][checkingColum] != null) return false; //если на пути есть фигура
                }
            }
            return true;
        }
        return false;




    }
}
