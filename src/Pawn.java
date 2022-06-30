public class Pawn extends ChessPiece {
    public Pawn(String color) {
        super(color);
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
        if (isNotValid(line) || isNotValid(colum) || isNotValid(toLine) || isNotValid(toColumn)) return false;
        if (colum != toColumn) return false;
        if (color.equals("White")) {
            if ((line == 1 && toLine == 3) || ((toLine - line) == 1)) return true;
        } else {
            if ((line == 6 && toLine == 4) || ((line - toLine) == 1)) return true;
        }
        return false;
    }
}