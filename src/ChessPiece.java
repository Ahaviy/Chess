public abstract class ChessPiece {

    protected String color;
    boolean check = true;

    public abstract String getColor();

    public abstract String getSymbol();

    public abstract boolean canMoveToPosition(ChessBoard chessBoard, int line, int colum, int toLine, int toColumn);

    public abstract boolean canAttackToPosition(ChessBoard chessBoard, int line, int colum, int toLine, int toColumn);

    public abstract boolean is(Piece piece);

    public boolean is(Color pieceColor) {
        if (pieceColor == null) return false;
        if (pieceColor == Color.WHITE && color.equals("White")) return true;
        if (pieceColor == Color.BLACK && color.equals("Black")) return true;
        return false;
    }

    public boolean isSameColor(ChessPiece piece) {
        if (piece == null) return false;
        if (this.color.equals(piece.getColor())) return true;
        return false;
    }

    public ChessPiece(String color) {
        this.color = color;
    }

    public ChessPiece(Color color) {
        this.color = color.getColor();
    }

    public Color getColorPiece() {
        return is(Color.WHITE) ? Color.WHITE : Color.BLACK;
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
