public class Queen extends ChessPiece {
    public Queen(String color) {
        super(color);
    }

    public Queen(Color color) {
        super(color);
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public String getSymbol() {
        return "Q";
    }

    @Override
    public boolean is(Piece piece) {
        if (piece == null) return false;
        return piece == Piece.QUEEN;
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
        if (colum == toColumn) { //Проверка вертикали
            for (int checkingLine = Math.min(line, toLine) + 1; checkingLine < Math.max(line, toLine); checkingLine++) {
                if (chessBoard.board[checkingLine][colum] != null) return false; //если на пути есть фигура
            }
            return true;
        }
        if (line == toLine) { //Проверка горизантали
            for (int checkingColum = Math.min(colum, toColumn) + 1; checkingColum < Math.max(colum, toColumn); checkingColum++) {
                if (chessBoard.board[line][checkingColum] != null) return false; //если на пути есть фигура
            }
            return true;
        }
        if (Math.abs(line - toLine) == Math.abs(colum - toColumn)) { //Если диагональ
            for (int checkingLine = Math.min(line, toLine) + 1; checkingLine < Math.max(line, toLine); checkingLine++) {
                int checkingColum = (colum == toColumn - toLine + line) ?
                        colum + checkingLine - line : colum + line - checkingLine;
                if (chessBoard.board[checkingLine][checkingColum] != null) return false; //если на пути есть фигура
            }
            return true;
        }
        return false;
    }

}
