public enum Piece {
    BISHOP("B"), HORSE("H"), KING("K"), PAWN("P"), QUEEN("Q"), ROOK("R");

    private String symbol;

    Piece(String symbol) {
        this.symbol = symbol;
    }
}
