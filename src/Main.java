import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int count=0;
        char[][] board = new char[3][3];
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                board[row][col] = ' ';
            }
        }
        char player = 'X';
        boolean gameOver = false;
        while (!gameOver) {
            count++;
            if(count>9)
            {
                printBoard(board);
                System.out.println("Game Drawn!!");
                break;
            }
            printBoard(board);
            System.out.println("Player " + player + " enter: ");
            int row = sc.nextInt();
            int col = sc.nextInt();
            if (board[row][col] == ' ') {
                board[row][col] = player;
                gameOver = hasWon(board, player);
                if (gameOver) {
                    printBoard(board);
                    System.out.println("Plyer " + player + " has won.");

                } else {
                    player = player == 'X' ? 'O' : 'X';
                }
            } else {
                System.out.println("Invalid Move. Try Again!!");

            }

        }
    }
    public static void printBoard(char[][] board){
        for(int row=0;row<board.length;row++){
            for(int col=0;col<board[row].length;col++) {
                if(col<2)
                    System.out.print(board[row][col]+ " | ");
                else
                    System.out.print(board[row][col]+ "  ");
            }
            System.out.println();
        }
    }
    public static boolean hasWon(char[][] board,char player){
        for(int row=0;row<board.length;row++){
            if(board[row][0]==player&&board[row][1]==player&&board[row][2]==player){
                return true;
            }
        }
        for(int col=0;col<board.length;col++){
            if(board[0][col]==player&&board[1][col]==player&&board[2][col]==player){
                return true;
            }
        }
        if(board[0][0]==player&&board[1][1]==player&&board[2][2]==player)
            return true;
        if(board[0][2]==player&&board[1][1]==player&&board[2][0]==player)
            return true;
        return false;
    }

}
