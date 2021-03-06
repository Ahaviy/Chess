public class Horse extends ChessPiece {
    public Horse(String color) {
        super(color);
    }

    public Horse(ColorPiece colorPiece) {
        super(colorPiece);
    }

    @Override
    public String getColor() {
        return color;
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
        if (((line - toLine) == 2 || (toLine - line) == 2) && ((colum - toColumn) == 1 || (toColumn - colum) == 1))
            return true;
        if (((line - toLine) == 1 || (toLine - line) == 1) && ((colum - toColumn) == 2 || (toColumn - colum) == 2))
            return true;
        return false;
    }

    @Override
    public String getSymbol() {
        return "H";
    }


}
