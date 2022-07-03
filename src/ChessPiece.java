public abstract class ChessPiece {
    protected String color;
    boolean check = true;

    public ChessPiece(String color) {
        this.color = color;
    }

    public abstract String getColor();

    public abstract boolean canMoveToPosition(ChessBoard chessBoard, int line, int colum, int toLine, int toColumn);

    public abstract boolean canAttackToPosition(ChessBoard chessBoard, int line, int colum, int toLine, int toColumn);

    public abstract String getSymbol();

    public boolean isWhite() {
        if (color.equals("White")) return true;
        return false;
    }
    public boolean isSameColor(ChessPiece piece) {
        if (piece == null) return false;
        if (this.color.equals(piece.getColor())) return true;
        return false;
    }

    protected boolean isCoordinatesNotValid(int line, int toLine, int colum, int toColumn) {
        if (isOutOfBorder(line) || isOutOfBorder(colum) || isOutOfBorder(toLine) || isOutOfBorder(toColumn))
            return true;
        if (line == toLine && colum == toColumn) return true;
        return false;
    }

    private boolean isOutOfBorder(int coordinate) {
        if (coordinate >= 0 && coordinate < 8) return false;
        return true;
    }
}
