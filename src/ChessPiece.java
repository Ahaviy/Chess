public abstract class ChessPiece {

    protected String color;
    boolean check = true;

    public ChessPiece(String color) {
        this.color = color;
    }

    public ChessPiece(ColorPiece colorPiece) {
        this.color = colorPiece.getColor();
    }

    public abstract String getColor();

    public abstract boolean canMoveToPosition(ChessBoard chessBoard, int line, int colum, int toLine, int toColumn);

    public abstract boolean canAttackToPosition(ChessBoard chessBoard, int line, int colum, int toLine, int toColumn);

    public abstract String getSymbol();

    public boolean isHorse() {
        return getSymbol().equals("H");
    }

    public boolean isPawn() {
        return getSymbol().equals("P");
    }

    public boolean isBishop() {
        return getSymbol().equals("B");
    }

    public boolean isRook() {
        return getSymbol().equals("R");
    }

    public boolean isQueen() {
        return getSymbol().equals("Q");
    }

    public boolean isKing() {
        return getSymbol().equals("K");
    }

    public boolean isWhite() {
        return color.equals("White");
    }

    public boolean isBlack() {
        return color.equals("Black");
    }

    public ColorPiece getColorPiece() {
        return isWhite() ? ColorPiece.WHITE : ColorPiece.BLACK;
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
