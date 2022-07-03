public class Queen extends ChessPiece{
    public Queen(String color) {
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
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int colum, int toLine, int toColumn) {
        if (isCoordinatesNotValid(line, toLine, colum, toColumn)) return false;
        if ((colum == toColumn && line != toLine) || (colum != toColumn && line == toLine)) return true;
        if ((line - toLine == colum - toColumn) || (line - toLine == toColumn - colum)) return true;
        return false;
    }

    @Override
    public boolean canAttackToPosition(ChessBoard chessBoard, int line, int colum, int toLine, int toColumn) {
        return false;
    }
}
