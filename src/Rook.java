public class Rook extends ChessPiece {

    public Rook(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public String getSymbol() {
        return "R";
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int colum, int toLine, int toColumn) {
        if (isCoordinateNotValid(line, toLine, colum, toColumn)) return false;
        if ((colum == toColumn && line != toLine) || (colum != toColumn && line == toLine)) return true;
        return false;
    }
}
