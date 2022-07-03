public class King extends ChessPiece {
    public King(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public String getSymbol() {
        return "K";
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int colum, int toLine, int toColumn) {
        if (isCoordinatesNotValid(line, toLine, colum, toColumn)) return false;
        if (isUnderAttack(chessBoard, toLine, toColumn)) return false;
        if ((line - toLine >= -1 && line - toLine <= 1) && (colum - toColumn >= -1 && colum - toColumn <= 1)) {
            if ((colum == toColumn && line != toLine) || (colum != toColumn && line == toLine)) return true;
            if ((line - toLine == colum - toColumn) || (line - toLine == toColumn - colum)) return true;
        }
        return false;
    }

    public boolean canMoveToPositionWithoutVerificationIsUnderAttack(ChessBoard chessBoard, int line, int colum, int toLine, int toColumn) {
        if (isCoordinatesNotValid(line, toLine, colum, toColumn)) return false;
        if ((line - toLine >= -1 && line - toLine <= 1) && (colum - toColumn >= -1 && colum - toColumn <= 1)) {
            if ((colum == toColumn && line != toLine) || (colum != toColumn && line == toLine)) return true;
            if ((line - toLine == colum - toColumn) || (line - toLine == toColumn - colum)) return true;
        }
        return false;
    }

    public boolean isUnderAttack(ChessBoard chessBoard, int line, int column) {
        for (int checkL = 0; checkL < 8; checkL++) {
            for (int checkC = 0; checkC < 8; checkC++) {
                if (chessBoard.board[checkL][checkC] != null) {
                    if (!this.color.equals(chessBoard.board[checkL][checkC].getColor())) {
                        if (chessBoard.board[checkL][checkC].getSymbol().equals("K")) {
                            if (((King) chessBoard.board[checkL][checkC]).canMoveToPositionWithoutVerificationIsUnderAttack(chessBoard, checkL, checkC, line, column))
                                return true;
                        } else {
                            if (chessBoard.board[checkL][checkC].canMoveToPosition(chessBoard, checkL, checkC, line, column))
                                return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
