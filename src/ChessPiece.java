public abstract class ChessPiece {
    String color;
    boolean check = true;

    public ChessPiece(String color) {
        this.color = color;
    }

    public abstract String getColor();

    public abstract boolean canMoveToPosition(ChessBoard chessBoard, int line, int colum, int toLine, int toColumn);

    public abstract String getSymbol();

    protected boolean isNotValid(int coordinate) {
        return coordinate < 0 || coordinate >= 8;
    }
}
