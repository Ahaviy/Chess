public class Bishop extends ChessPiece {
    public Bishop(String color) {
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
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int colum, int toLine, int toColumn) {
        if (isNotValid(line) || isNotValid(colum) || isNotValid(toLine) || isNotValid(toColumn)) return false;
        if (line == toLine && colum == toColumn) return false;
        if ((line - toLine == colum - toColumn) || (line - toLine == toColumn - colum)) return true;
            return false;
    }
}
