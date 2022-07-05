public class Bishop extends ChessPiece {
    public Bishop(String color) {
        super(color);
    }

    public Bishop(ColorPiece colorPiece) {
        super(colorPiece);
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
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int colum, int toLine, int toColumn) {
        if (isCoordinatesNotValid(line, toLine, colum, toColumn)) return false;
        if (chessBoard.board[toLine][toColumn] != null && isSameColor(chessBoard.board[toLine][toColumn])) return false;
        if (line - toLine == colum - toColumn) {
            if (line - toLine > 0) {
                for (int checkingLine = toLine + 1; checkingLine < line; checkingLine++) {
                    int checkingColum = checkingLine - line + colum;
                    if (chessBoard.board[checkingLine][checkingColum] != null) return false; //если на пути есть фигура
                }
            } else {
                for (int checkingLine = toLine - 1; checkingLine > line; checkingLine--) {
                    int checkingColum = checkingLine - line + colum;
                    if (chessBoard.board[checkingLine][checkingColum] != null) return false; //если на пути есть фигура
                }
            }
            return true;
        }
        if (line - toLine == toColumn - colum) {
            if (line - toLine > 0) {
                for (int checkingLine = toLine + 1; checkingLine < line; checkingLine++) {
                    int checkingColum = line - checkingLine + colum;
                    if (chessBoard.board[checkingLine][checkingColum] != null) return false; //если на пути есть фигура
                }
            } else {
                for (int checkingLine = toLine - 1; checkingLine > line; checkingLine--) {
                    int checkingColum = line - checkingLine + colum;
                    if (chessBoard.board[checkingLine][checkingColum] != null) return false; //если на пути есть фигура
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean canAttackToPosition(ChessBoard chessBoard, int line, int colum, int toLine, int toColumn) {
        return canMoveToPosition(chessBoard, line, colum, toLine, toColumn);
    }
}
