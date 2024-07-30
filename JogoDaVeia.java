import java.util.Scanner;

public class JogoDaVeia {
    private static char[][] board = new char[3][3];
    private static int totalGames = 0;
    private static int player1Wins = 0;
    private static int player2Wins = 0;
    private static String player1;
    private static String player2;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Mensagem de boas-vindas e solicitação dos nomes dos jogadores
        System.out.println("Bem-vindos ao Jogo da Velha!");
        System.out.print("Nome do Jogador 1: ");
        player1 = scanner.nextLine();
        System.out.print("Nome do Jogador 2: ");
        player2 = scanner.nextLine();

        boolean play = true;
        while (play) {
            totalGames++;
            resetBoard();
            char currentPlayer = 'X';

            while (true) {
                printBoard();
                System.out.println("Digite 'q' para sair.");
                System.out.printf("Vez de %s (%c): \n", currentPlayer == 'X' ? player1 : player2, currentPlayer);
                System.out.print("Entre com a linha e coluna (1-3) separado por espaço: ");
                String input = scanner.nextLine();

                if (input.equalsIgnoreCase("q")) {
                    play = false;
                    break;
                }

                int row, col;
                try {
                    String[] parts = input.split(" ");
                    row = Integer.parseInt(parts[0]) - 1;
                    col = Integer.parseInt(parts[1]) - 1;
                } catch (Exception e) {
                    System.out.println("Entrada inválida. Tente novamente.");
                    continue;
                }

                if (row < 0 || row > 2 || col < 0 || col > 2 || board[row][col] != ' ') {
                    System.out.println("Movimento inválido. Tente novamente.");
                    continue;
                }

                board[row][col] = currentPlayer;

                if (checkWin(currentPlayer)) {
                    printBoard();
                    System.out.printf("Parabéns, %s! Você venceu!\n", currentPlayer == 'X' ? player1 : player2);
                    if (currentPlayer == 'X') {
                        player1Wins++;
                    } else {
                        player2Wins++;
                    }
                    break;
                }

                if (isBoardFull()) {
                    printBoard();
                    System.out.println("É um empate!");
                    break;
                }

                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            }

            if (play) {
                System.out.println("Deseja jogar novamente? (s/n)");
                play = scanner.nextLine().equalsIgnoreCase("s");
            }
        }

        // Mostrar placar final
        System.out.println("Placar final:");
        System.out.printf("%s: %d vitórias\n", player1, player1Wins);
        System.out.printf("%s: %d vitórias\n", player2, player2Wins);
        System.out.printf("Total de partidas jogadas: %d\n", totalGames);
    }

    private static void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    private static void printBoard() {
        System.out.println("  1 2 3");
        for (int i = 0; i < 3; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j]);
                if (j < 2) System.out.print("|");
            }
            System.out.println();
            if (i < 2) System.out.println("  -----");
        }
    }

    private static boolean checkWin(char player) {
        // Verificar linhas, colunas e diagonais
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) return true;
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player) return true;
        }
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) return true;
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) return true;
        return false;
    }

    private static boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') return false;
            }
        }
        return true;
    }
}