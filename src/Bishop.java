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
