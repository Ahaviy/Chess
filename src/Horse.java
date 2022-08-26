public class Horse extends ChessPiece {
    public Horse(String color) {
        super(color);
    }

    public Horse(Color color) {
        super(color);
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public boolean is(Piece piece) {
        if (piece == null) return false;
        return piece == Piece.HORSE;
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
        if ((Math.abs(line - toLine) == 2 && Math.abs(colum - toColumn) == 1)
                || (Math.abs(line - toLine) == 1 && Math.abs(colum - toColumn) == 2)) return true;
        return false;
    }

    @Override
    public String getSymbol() {
        return "H";
    }


}
