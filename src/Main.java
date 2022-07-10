import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ChessBoard board = new ChessBoard();
        board.newGame();
        Scanner scanner = new Scanner(System.in);
        System.out.println("""
                Чтобы проверить игру надо вводить команды:
                'exit' - для выхода
                'replay' - для перезапуска игры
                'castling0' или 'castling7' - для рокировки по соответствующей линии
                'move 1 1 2 3' - для передвижения фигуры с позиции 1 1 на 2 3(поле это двумерный массив от 0 до 7)
                Проверьте могут ли фигуры ходить друг сквозь друга, корректно ли съедают друг друга, можно ли поставить шах и сделать рокировку?""");
        System.out.println();
        board.printBoard();
        boolean endGame = false;
        while (!endGame) {
            String s = scanner.nextLine();
            if (s.equals("exit")) break;
            else if (s.equals("replay")) {
                System.out.println("Заново");
                board.newGame();
                board.printBoard();
            } else {
                if (s.equals("castling0")) {
                    if (board.castling0()) {
                        System.out.println("Рокировка удалась");
                        board.printBoard();
                    } else {
                        System.out.println("Рокировка не удалась");
                    }
                } else if (s.equals("castling7")) {
                    if (board.castling7()) {
                        System.out.println("Рокировка удалась");
                        board.printBoard();
                    } else {
                        System.out.println("Рокировка не удалась");
                    }
                } else if (s.contains("move")) {
                    String[] a = s.split(" ");
                    try {
                        int line = Integer.parseInt(a[1]);
                        int column = Integer.parseInt(a[2]);
                        int toLine = Integer.parseInt(a[3]);
                        int toColumn = Integer.parseInt(a[4]);
                        if (board.moveToPosition(line, column, toLine, toColumn)) {
                            System.out.print("Успешно передвинулись");
                            if (board.isStaleMate()) {
                                System.out.print(", пат! - ничья");
                                System.out.println("Хотите сыграть ещё? (y/n)");
                                while (true) {
                                    s = scanner.nextLine();
                                    if (s.equals("y")) {
                                        board.newGame();
                                        break;
                                    }
                                    if (s.equals("n")) {
                                        endGame = true;
                                        break;
                                    }
                                }
                            }
                            if (board.isCheck(board.nowPlayerColorPiece())) {
                                if (board.isCheckMate()) {
                                    System.out.print(", мат!");
                                    String winner = board.nowPlayerColorPiece() == ColorPiece.WHITE ? "Черные " : "Белые ";
                                    System.out.println(winner + "выйграли!");
                                    System.out.println("Хотите сыграть ещё? (y/n)");
                                    while (true) {
                                        s = scanner.nextLine();
                                        if (s.equals("y")) {
                                            board.newGame();
                                            break;
                                        }
                                        if (s.equals("n")) {
                                            endGame = true;
                                            break;
                                        }
                                    }
                                } else {
                                    System.out.print(", шах!");
                                }
                            }
                            System.out.println();
                            board.printBoard();
                        } else System.out.println("Передвижение не удалось");
                    } catch (Exception e) {
                        System.out.println("Вы что-то ввели не так, попробуйте ещё раз");
                    }
                }
            }
        }
    }

}