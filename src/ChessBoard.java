import java.util.Scanner;

public class ChessBoard {

    public ChessPiece[][] board = new ChessPiece[8][8]; // creating a field for game
    String nowPlayer;

    public ChessBoard() {
        this.nowPlayer = "White";
    }

    public ChessBoard(String nowPlayer) {
        this.nowPlayer = nowPlayer;
    }

    public String nowPlayerColor() {
        return this.nowPlayer;
    }

    public ColorPiece nowPlayerColorPiece() {
        return nowPlayer.equals("White") ? ColorPiece.WHITE : ColorPiece.BLACK;
    }

    public void newGame() {
        nowPlayer = "White";
        board[0][0] = new Rook(ColorPiece.WHITE);
        board[0][1] = new Horse(ColorPiece.WHITE);
        board[0][2] = new Bishop(ColorPiece.WHITE);
        board[0][3] = new Queen(ColorPiece.WHITE);
        board[0][4] = new King(ColorPiece.WHITE);
        board[0][5] = new Bishop(ColorPiece.WHITE);
        board[0][6] = new Horse(ColorPiece.WHITE);
        board[0][7] = new Rook(ColorPiece.WHITE);
        board[1][0] = new Pawn(ColorPiece.WHITE);
        board[1][1] = new Pawn(ColorPiece.WHITE);
        board[1][2] = new Pawn(ColorPiece.WHITE);
        board[1][6] = new Pawn(ColorPiece.WHITE);
        board[1][7] = new Pawn(ColorPiece.WHITE);
        board[1][4] = new Pawn(ColorPiece.WHITE);
        board[1][5] = new Pawn(ColorPiece.WHITE);
        board[1][3] = new Pawn(ColorPiece.WHITE);

        board[7][0] = new Rook(ColorPiece.BLACK);
        board[7][1] = new Horse(ColorPiece.BLACK);
        board[7][2] = new Bishop(ColorPiece.BLACK);
        board[7][3] = new Queen(ColorPiece.BLACK);
        board[7][4] = new King(ColorPiece.BLACK);
        board[7][5] = new Bishop(ColorPiece.BLACK);
        board[7][6] = new Horse(ColorPiece.BLACK);
        board[7][7] = new Rook(ColorPiece.BLACK);
        board[6][0] = new Pawn(ColorPiece.BLACK);
        board[6][1] = new Pawn(ColorPiece.BLACK);
        board[6][2] = new Pawn(ColorPiece.BLACK);
        board[6][3] = new Pawn(ColorPiece.BLACK);
        board[6][4] = new Pawn(ColorPiece.BLACK);
        board[6][5] = new Pawn(ColorPiece.BLACK);
        board[6][6] = new Pawn(ColorPiece.BLACK);
        board[6][7] = new Pawn(ColorPiece.BLACK);
    }

    public void printBoard() {  //print board in console
        System.out.println("Turn " + nowPlayer);
        System.out.println();
        System.out.println("Player 2(Black)");
        System.out.println();
        System.out.println("\t0\t1\t2\t3\t4\t5\t6\t7");
        for (int i = 7; i > -1; i--) {
            System.out.print(i + "\t");
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == null) {
                    System.out.print(".." + "\t");
                } else {
                    System.out.print(board[i][j].getSymbol()
                            + board[i][j].getColor().substring(0, 1).toLowerCase() + "\t");
                }
            }
            System.out.println();
            System.out.println();
        }
        System.out.println("Player 1(White)");
    }

    /**
     * Возвращает копию доски (для проверок на мат, на пат и на шах после хода - так ходить нельзя)
     */
    public ChessBoard copyThisChessBoard() {
        ChessBoard chessBoard = new ChessBoard(nowPlayer);
        for (int line = 0; line < 8; line++) {
            for (int column = 0; column < 8; column++) {
                chessBoard.board[line][column] = board[line][column];
            }
        }
        return chessBoard;
    }


    /**
     * Поптыка совершить ход
     * @param line     - строка - координата фигуры совершащий ход
     * @param column   - столбец - координата фигуры совершащий ход
     * @param toLine   - строка - координата клетки на которую совершается ход
     * @param toColumn - столбец - координата клетки на которую совершается ход
     * @return true - ход совершен корректно
     * @return false - не удалось совершить ход
     */
    public boolean moveToPosition(int line, int column, int toLine, int toColumn) {
        if (!checkPos(line) || !checkPos(column) || !checkPos(toLine) || !checkPos(toColumn)) return false;
        if (!nowPlayer.equals(board[line][column].getColor())) return false;
        if (!board[line][column].canMoveToPosition(this, line, column, toLine, toColumn)) return false;
        ChessBoard testForCheck = copyThisChessBoard();
        testForCheck.makeMove(line, column, toLine, toColumn, false, true); //если после хода шах
        if (testForCheck.isCheck(nowPlayerColorPiece())) return false;  //своему королю то ход невозможен
        makeMove(line, column, toLine, toColumn, false, false);
        return true;
    }

    /**
     * Проверяет есть ли шах
     *
     * @param color цвет проверяемого короля
     * @return true - король под шахом
     * @return false - шаха нет
     */
    public boolean isCheck(ColorPiece color) { //check = шах в данном месте синоним
        int[] kingCoords = findKing(color); //ищем короля
        if (kingCoords[0] == -1)
            return false; //хотя по логике надо выкидывать исключение, если короля нет на доске
        return ((King) board[kingCoords[0]][kingCoords[1]])
                .isUnderAttack(this, kingCoords[0], kingCoords[1]);
    }


    /**
     * Проверка на патовое сотояние у игрока который должен совершать ход
     *
     * @return true - получен пат
     * @return false - пата нет
     */
    public boolean isStaleMate() {
        //Ищем короля цвета текущего игрока
        ColorPiece color = nowPlayerColorPiece();
        int[] kingCoords = findKing(color);
        if (kingCoords[0] == -1)
            return false; //хотя по логике надо выкидывать исключение, если короля нет на доске
        if (((King) board[kingCoords[0]][kingCoords[1]]) //проверяем под шахом ли он
                .isUnderAttack(this, kingCoords[0], kingCoords[1])) return false;
        //перебираем все клетки доски, ищем все фигуры того же цвета
        for (int line = 0; line < 8; line++) {
            for (int column = 0; column < 8; column++) {
                if (board[line][column] != null && board[line][column].getColorPiece() == color) {
                    //для каждой фигуры проверяем все возможные ходы
                    for (int toLine = 0; toLine < 8; toLine++) {
                        for (int toColumn = 0; toColumn < 8; toColumn++) {
                            if (board[line][column].canMoveToPosition(this, line, column, toLine, toColumn)) {
                                //для каждого хода проверяем ведет ли не к шаху
                                ChessBoard testBoard = copyThisChessBoard();
                                testBoard.makeMove(line, column, toLine, toColumn, false, true);
                                if (!testBoard.isCheck(color)) return false;
                            }
                        }
                    }
                }
            }
        }
        return true; //Если все возможные ходы ведут к шаху, то пат
    }

    /**
     * Проверка на мат
     *
     * @return true - мат
     * @return false - мата нет
     */
    public boolean isCheckMate() {
        //Ищем короля цвета текущего игрока
        ColorPiece color = nowPlayerColorPiece();
        int[] kingCoords = findKing(color);
        if (kingCoords[0] == -1)
            return false; //хотя по логике надо выкидывать исключение, если короля нет на доске
        if (!((King) board[kingCoords[0]][kingCoords[1]]) //проверяем под шахом ли он
                .isUnderAttack(this, kingCoords[0], kingCoords[1])) return false;
        //перебираем все клетки доски, ищем все фигуры того же цвета
        for (int line = 0; line < 8; line++) {
            for (int column = 0; column < 8; column++) {
                if (board[line][column] != null && board[line][column].getColorPiece() == color) {
                    //для каждой фигуры проверяем все возможные ходы
                    for (int toLine = 0; toLine < 8; toLine++) {
                        for (int toColumn = 0; toColumn < 8; toColumn++) {
                            if (board[line][column].canMoveToPosition(this, line, column, toLine, toColumn)) {
                                //для каждого хода проверяем ведет ли он к уходу из под шаха
                                ChessBoard testBoard = copyThisChessBoard();
                                testBoard.makeMove(line, column, toLine, toColumn, false, true);
                                if (!testBoard.isCheck(color)) return false;
                            }
                        }
                    }
                }
            }
        }
        return true; //Если ни один из возможных ходов не увел из под шаха, то мат
    }

    /**
     * Попытка рокировки активного игрока в длинную сторону (с ладьёй на линии A)     *
     * @return true - рокировка успешно выполнена
     * @return false - рокировка невозможна
     */
    public boolean castling0() {
        if (nowPlayer.equals("White")) {
            //Рокировку можно делать, если соблюдаются следующие правила:
            //Ни король, ни ладья, с которой делается рокировка, не делали до этого ни одного хода
            if (board[0][0] == null || !board[0][0].isRook() || !board[0][0].isWhite() || !board[0][0].check)
                return false;
            if (board[0][4] == null || !board[0][4].isKing() || !board[0][4].isWhite() || !board[0][4].check)
                return false;
            //Между королем и ладьей нет других фигур.
            if (board[0][1] != null || board[0][2] != null || board[0][3] != null) return false;
            //Королю не объявлен шах, поле, через которое перепрыгивает король и поле, на которое он становится
            // не пробивается фигурами противника.
            if (((King) board[0][4]).isUnderAttack(this, 0, 4)
                    || ((King) board[0][4]).isUnderAttack(this, 0, 3)
                    || ((King) board[0][4]).isUnderAttack(this, 0, 2)) return false;
            makeMove(0, 4, 0, 0, true, false);
            return true;
        } else {
            //Рокировку можно делать, если соблюдаются следующие правила:
            //Ни король, ни ладья, с которой делается рокировка, не делали до этого ни одного хода
            if (board[7][0] == null || !board[7][0].isRook() || !board[7][0].isBlack() || !board[7][0].check)
                return false;
            if (board[7][4] == null || !board[7][4].isKing() || !board[7][4].isBlack() || !board[7][4].check)
                return false;
            //Между королем и ладьей нет других фигур.
            if (board[7][1] != null || board[7][2] != null || board[7][3] != null) return false;
            //Королю не объявлен шах, поле, через которое перепрыгивает король и поле, на которое он становится
            // не пробивается фигурами противника.
            if (((King) board[7][4]).isUnderAttack(this, 7, 4)
                    || ((King) board[7][4]).isUnderAttack(this, 7, 3)
                    || ((King) board[7][4]).isUnderAttack(this, 7, 2)) return false;
            makeMove(7, 4, 7, 0, true, false);
            return true;
        }
    }

    /**
     * Попытка рокировки активного игрока в короткую сторону (с ладьёй на линии H)     *
     * @return true - рокировка успешно выполнена
     * @return false - рокировка невозможна
     */
    public boolean castling7() {
        if (nowPlayer.equals("White")) {
            //Рокировку можно делать, если соблюдаются следующие правила:
            //Ни король, ни ладья, с которой делается рокировка, не делали до этого ни одного хода
            if (board[0][7] == null || !board[0][7].isRook() || !board[0][7].isWhite() || !board[0][7].check)
                return false;
            if (board[0][4] == null || !board[0][4].isKing() || !board[0][4].isWhite() || !board[0][4].check)
                return false;
            //Между королем и ладьей нет других фигур.
            if (board[0][6] != null || board[0][5] != null) return false;
            //Королю не объявлен шах, поле, через которое перепрыгивает король и поле, на которое он становится
            // не пробивается фигурами противника.
            if (((King) board[0][4]).isUnderAttack(this, 0, 4)
                    || ((King) board[0][4]).isUnderAttack(this, 0, 5)
                    || ((King) board[0][4]).isUnderAttack(this, 0, 6)) return false;
            makeMove(0, 4, 0, 7, true, false);
            return true;
        } else {
            //Рокировку можно делать, если соблюдаются следующие правила:
            //Ни король, ни ладья, с которой делается рокировка, не делали до этого ни одного хода
            if (board[7][7] == null || !board[7][7].isRook() || !board[7][7].isBlack() || !board[7][7].check)
                return false;
            if (board[7][4] == null || !board[7][4].isKing() || !board[7][4].isBlack() || !board[7][4].check)
                return false;
            //Между королем и ладьей нет других фигур.
            if (board[7][6] != null || board[7][5] != null) return false;
            //Королю не объявлен шах, поле, через которое перепрыгивает король и поле, на которое он становится
            // не пробивается фигурами противника.
            if (((King) board[7][4]).isUnderAttack(this, 7, 4)
                    || ((King) board[7][4]).isUnderAttack(this, 7, 5)
                    || ((King) board[7][4]).isUnderAttack(this, 7, 6)) return false;
            makeMove(7, 4, 7, 7, true, false);
            return true;
        }
    }

    /**
     * Изменение состояния доски в соотвесвии с совершаемым ходом
     * @param line     - строка - координата фигуры совершащий ход
     * @param column   - столбец - координата фигуры совершащий ход
     * @param toLine   - строка - координата клетки на которую совершается ход
     * @param toColumn - столбец - координата клетки на которую совершается ход
     * @param isCastling  - является ли ход рокировкой
     * @param checkForCheck  - является ли ход проверочным (не должен изменять параметрой фигур и давать запросы пользователю)
     */
    private void makeMove(int line, int column, int toLine, int toColumn, boolean isCastling, boolean checkForCheck) {
        //если пешка может съесть на проходе, то съедаеммую пешку переносим на клетку назад
        if (board[line][column].isPawn()
                && ((Pawn) board[line][column]).canTakeOnTheAisle(this, line, column, toLine, toColumn)) {
            if (board[line][column].isWhite()) {
                board[5][toColumn] = board[4][toColumn];
                board[4][toColumn] = null;
            } else {
                board[2][toColumn] = board[3][toColumn];
                board[3][toColumn] = null;
            }
        }
        if (!checkForCheck) {
            //Проверяем все клетки, ищем все пешки, обнуляем проверку на возможность взятия на проходе
            for (int Line = 0; Line < 7; Line++) {
                for (int Colum = 0; Colum < 7; Colum++) {
                    if (board[Line][Colum] != null && board[Line][Colum].isPawn()) {
                        ((Pawn) board[Line][Colum]).canBeEatenOnTheAisle = false;
                    }
                }
            }
        }

        if (isCastling) { //Если рокировка
            if (toColumn == 0) { //длинная рокировка
                board[line][2] = board[line][4];
                board[line][2].check = false;
                board[line][4] = null;
                board[line][3] = board[line][0];
                board[line][3].check = false;
                board[line][0] = null;
            } else { //короткая рокировка
                board[line][6] = board[line][4];
                board[line][6].check = false;
                board[line][4] = null;
                board[line][5] = board[line][7];
                board[line][5].check = false;
                board[line][7] = null;
            }
        } else {
            if (board[line][column].isPawn()) { //Проверки для пешек перед заменой на доске
                //если пешка достигает последней линии предворительно заменяется на выбранную фигуру
                //проверка на checkForCheck нужна что бы во время проверки на ход под шах не было выбора фигуры
                if (board[line][column].isWhite() && line == 6 && !checkForCheck) {
                    board[line][column] = getPieceFromPawn(ColorPiece.WHITE);
                }
                if (board[line][column].isBlack() && line == 1 && !checkForCheck) {
                    board[line][column] = getPieceFromPawn(ColorPiece.BLACK);
                }
                if ((line == 1 && toLine == 3) || (line == 6 && toLine == 4))
                    ((Pawn) board[line][column]).canBeEatenOnTheAisle = true;
            }
            //Перемещение фигуры
            board[toLine][toColumn] = board[line][column];
            if (!checkForCheck && (board[toLine][toColumn].isKing() || board[toLine][toColumn].isRook())) {
                board[toLine][toColumn].check = false;
            }
            board[line][column] = null;
        }
        nowPlayer = this.nowPlayerColor().equals("White") ? "Black" : "White"; //смена игрока
    }
    /**
     * Возращает фигуру в которую превращается пешка, которая достигает последней линии доски
     * */
    public ChessPiece getPieceFromPawn(ColorPiece color) {
        System.out.print("выберите фигуру (Q - ферзь, R - лодья, B - слон, H - конь): ");
        Scanner scanner = new Scanner(System.in);
        String s;
        do {
            s = scanner.next();
            s = s.toLowerCase();
        } while (!s.equals("q") && !s.equals("r") && !s.equals("b") && !s.equals("h"));
        System.out.println();
        return switch (s) {
            case "q" -> new Queen(color);
            case "r" -> new Rook(color);
            case "b" -> new Bishop(color);
            case "h" -> new Horse(color);
            default -> new Pawn(color);
        };
    }
    /**
     * Возвращает координаты на доске, где стоит король
     * @param color - цвет короля
     * */
    private int[] findKing(ColorPiece color) {
        int[] kingCoordinates = new int[]{-1, -1};
        for (int line = 0; line < 8; line++) {
            for (int column = 0; column < 8; column++) {
                if (board[line][column] != null && board[line][column].getColorPiece() == color
                        && board[line][column].isKing()) {
                    kingCoordinates[0] = line;
                    kingCoordinates[1] = column;
                    break;
                }
            }
        }
        return kingCoordinates;
    }

    private boolean checkPos(int pos) {
        return pos >= 0 && pos <= 7;
    }

}
