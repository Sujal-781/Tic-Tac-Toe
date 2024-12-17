package com.sujal;

import java.util.InputMismatchException;
import java.util.Scanner;

public class tic {
    private static final char EMPTY = ' ';
    private static final char PLAYER_X = 'X';
    private static final char PLAYER_O = 'O';

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter board size (e.g., 4 for a 4x4 board): ");
        int boardSize = sc.nextInt();
        int winCondition = 3; // Number of consecutive marks needed to win, can be adjusted

        char[][] board = initializeBoard(boardSize);
        char currentPlayer = PLAYER_X;
        boolean gameOver = false;
        int moveCount = 0;

        while (!gameOver) {
            printBoard(board);
            System.out.println("Player " + currentPlayer + ", enter your move (row and column): ");

            int row = -1, col = -1;
            boolean validInput = false;
            while (!validInput) {
                try {
                    row = sc.nextInt();
                    col = sc.nextInt();
                    if (isValidMove(board, row, col)) {
                        board[row][col] = currentPlayer;
                        validInput = true;
                    } else {
                        System.out.println("Invalid move. The cell is either occupied or out of bounds. Try again.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter numeric values.");
                    sc.next(); // Clear the invalid input
                }
            }

            moveCount++;
            if (hasWon(board, currentPlayer, winCondition)) {
                printBoard(board);
                System.out.println("Player " + currentPlayer + " has won!");
                gameOver = true;
            } else if (moveCount >= boardSize * boardSize) {
                printBoard(board);
                System.out.println("Game Drawn!");
                gameOver = true;
            } else {
                currentPlayer = (currentPlayer == PLAYER_X) ? PLAYER_O : PLAYER_X;
            }
        }
        sc.close();
    }

    private static char[][] initializeBoard(int size) {
        char[][] board = new char[size][size];
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                board[row][col] = EMPTY;
            }
        }
        return board;
    }

    private static void printBoard(char[][] board) {
        int size = board.length;
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                System.out.print(board[row][col]);
                if (col < size - 1) System.out.print(" | ");
            }
            System.out.println();
            if (row < size - 1) {
                System.out.println(new String(new char[size * 4 - 1]).replace('\0', '-'));
            }
        }
    }

    private static boolean isValidMove(char[][] board, int row, int col) {
        int size = board.length;
        return row >= 0 && row < size && col >= 0 && col < size && board[row][col] == EMPTY;
    }

    private static boolean hasWon(char[][] board, char player, int winCondition) {
        int size = board.length;
        // Check rows and columns
        for (int i = 0; i < size; i++) {
            if (checkLine(board, player, i, 0, 0, 1, winCondition) ||
                    checkLine(board, player, 0, i, 1, 0, winCondition)) {
                return true;
            }
        }
        // Check diagonals
        return checkLine(board, player, 0, 0, 1, 1, winCondition) ||
                checkLine(board, player, 0, size - 1, 1, -1, winCondition);
    }

    private static boolean checkLine(char[][] board, char player, int startRow, int startCol, int rowIncrement, int colIncrement, int winCondition) {
        int size = board.length;
        int count = 0;
        for (int i = 0; i < winCondition; i++) {
            int row = startRow + i * rowIncrement;
            int col = startCol + i * colIncrement;
            if (row < 0 || row >= size || col < 0 || col >= size || board[row][col] != player) {
                return false;
            }
            count++;
        }
        return count == winCondition;
    }
}
