import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe extends JFrame implements ActionListener {

    private JButton[][] buttons;
    private boolean xTurn = true;
    private JLabel statusLabel;

    public TicTacToe() {
        setTitle("Tic Tac Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(200, 200);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
    
        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        

        JPanel boardPanelWrapper = new JPanel(new BorderLayout());
        boardPanelWrapper.setBorder(new EmptyBorder(10, 10, 10, 10)); 
        JPanel boardPanel = new JPanel(new GridLayout(3, 3));
        boardPanel.setBackground(Color.BLACK); 
        buttons = new JButton[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 40));
                buttons[i][j].setForeground(Color.WHITE); 
                buttons[i][j].setBackground(Color.BLACK);
                buttons[i][j].addActionListener(this);
                boardPanel.add(buttons[i][j]);
            }
        }

        statusLabel = new JLabel("X's turn");
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);

        add(boardPanel, BorderLayout.CENTER);
        add(statusLabel, BorderLayout.SOUTH);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();

        if (button.getText().equals("")) {
            if (xTurn) {
                button.setText("X");
                statusLabel.setText("O's turn");
            } else {
                button.setText("O");
                statusLabel.setText("X's turn");
            }
            xTurn = !xTurn;
            checkForWinner();
        }
    }

    private void checkForWinner() {
        String winner = "";

                    // Horizontal and Vertical Check
        for (int i = 0; i < 3; i++) {
            if (buttons[i][0].getText().equals(buttons[i][1].getText()) &&
                    buttons[i][0].getText().equals(buttons[i][2].getText()) &&
                    !buttons[i][0].getText().equals("")) {
                winner = buttons[i][0].getText();
            }
            if (buttons[0][i].getText().equals(buttons[1][i].getText()) &&
                    buttons[0][i].getText().equals(buttons[2][i].getText()) &&
                    !buttons[0][i].getText().equals("")) {
                winner = buttons[0][i].getText();
            }
        }

                   // Diagonal Check
        if (buttons[0][0].getText().equals(buttons[1][1].getText()) &&
                buttons[0][0].getText().equals(buttons[2][2].getText()) &&
                !buttons[0][0].getText().equals("")) {
            winner = buttons[0][0].getText();
        }
        if (buttons[0][2].getText().equals(buttons[1][1].getText()) &&
                buttons[0][2].getText().equals(buttons[2][0].getText()) &&
                !buttons[0][2].getText().equals("")) {
            winner = buttons[0][2].getText();
        }

        if (!winner.equals("")) {
            statusLabel.setText(winner + " wins!");
            disableButtons();
        } else if (isBoardFull()) {
            statusLabel.setText("It's a draw!");
        }
    }

    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }

    private void disableButtons() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setEnabled(false);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TicTacToe();
        });
    }
}
