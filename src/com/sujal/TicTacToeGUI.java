import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToeGUI extends JFrame implements ActionListener {
    private JButton[][] buttons = new JButton[3][3];
    private char currentPlayer = 'X';
    private int moveCount = 0;

    public TicTacToeGUI() {
        setTitle("Tic Tac Toe");
        setSize(400, 400);
        setLayout(new GridLayout(3, 3));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialize buttons
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton(" ");
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 60));
                buttons[i][j].addActionListener(this);
                add(buttons[i][j]);
            }
        }

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton)e.getSource();
        if (!clicked.getText().equals(" ")) return;

        clicked.setText(String.valueOf(currentPlayer));
        moveCount++;

        if (checkWinner()) {
            JOptionPane.showMessageDialog(this, "Player " + currentPlayer + " wins!");
            resetBoard();
        } else if (moveCount == 9) {
            JOptionPane.showMessageDialog(this, "It's a draw!");
            resetBoard();
        } else {
            currentPlayer = currentPlayer == 'X' ? 'O' : 'X';
        }
    }

    private boolean checkWinner() {
        // Check rows, columns, and diagonals
        for (int i = 0; i < 3; i++) {
            if (match(buttons[i][0], buttons[i][1], buttons[i][2])) return true;
            if (match(buttons[0][i], buttons[1][i], buttons[2][i])) return true;
        }
        return match(buttons[0][0], buttons[1][1], buttons[2][2]) ||
                match(buttons[0][2], buttons[1][1], buttons[2][0]);
    }

    private boolean match(JButton b1, JButton b2, JButton b3) {
        return !b1.getText().equals(" ") &&
                b1.getText().equals(b2.getText()) &&
                b2.getText().equals(b3.getText());
    }

    private void resetBoard() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                buttons[i][j].setText(" ");
        currentPlayer = 'X';
        moveCount = 0;
    }

    public static void main(String[] args) {
        new TicTacToeGUI();
    }
}
