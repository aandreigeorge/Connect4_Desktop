package four;

import javax.swing.*;
import java.awt.*;

public class ConnectFour extends JFrame {

    private boolean isNextMoveX = true;
    private final JButton[][] cells = new JButton[6][7];

    public ConnectFour() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setTitle("Connect Four");
        setLocationRelativeTo(null);

        JPanel boardPanel = new JPanel(new GridLayout(6, 7));

        for (int row = 5; row >= 0; row--) {
            for (int column = 0; column < 7; column++) {
                cells[row][column] = new JButton();
                cells[row][column].setName("Button" + (char) ('A' + column) + (row + 1));
                cells[row][column].setText(" ");
                boardPanel.add(cells[row][column]);

                cells[row][column].addActionListener(e -> {
                    JButton clickedCell = (JButton) e.getSource();
                    int columnClicked = getColumnNumberFromButtonName(clickedCell.getName());
                    placePiece(columnClicked);
                });
            }
        }

        add(boardPanel);
        setVisible(true);
    }

    private int getColumnNumberFromButtonName(String buttonName) {
        return buttonName.charAt(6) - 'A';
    }

    private void placePiece(int column) {
        int row = getFirstEmptyRow(column);
        if (row != -1) {
            cells[row][column].setText(isNextMoveX ? "X" : "O");
            isNextMoveX = !isNextMoveX;
        }
    }

    private int getFirstEmptyRow(int column) {
        for (int row = 0; row <= 5; row++) {
            if (cells[row][column].getText().equals(" ")) {
                return row;
            }
        }
        return -1;
    }

}