package four;

import javax.swing.*;
import java.awt.*;

public class ConnectFour extends JFrame {


    private final JButton[][] cells = new JButton[6][7];
    private boolean isNextMoveX = true;
    private boolean gameFinished = false;


    public ConnectFour() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(350, 400);
        setTitle("Connect Four");
        setLocationRelativeTo(null);

        JPanel boardPanel = new JPanel(new GridLayout(6, 7));

        for (int row = cells.length - 1; row >= 0; row--) {
            for (int column = 0; column < cells[1].length; column++) {

                cells[row][column] = new JButton();
                cells[row][column].setOpaque(true);
                cells[row][column].setFocusPainted(false);
                cells[row][column].setForeground(Color.WHITE);
                cells[row][column].setName("Button" + (char) ('A' + column) + (row + 1));

                cells[row][column].addActionListener(e -> {
                    JButton clickedCell = (JButton) e.getSource();
                    int columnClicked = getColumnNumberFromButtonName(clickedCell.getName());
                    placePiece(columnClicked);
                });

                boardPanel.add(cells[row][column]);
            }
        }

        initializeTextAndColor(cells);
        add(boardPanel);

        JButton resetButton = new JButton("Reset");
        resetButton.setName("ButtonReset");
        resetButton.setForeground(Color.WHITE);
        resetButton.setBackground(new Color(15, 76, 117));
        resetButton.addActionListener(e -> resetGame(cells));
        add(resetButton, BorderLayout.SOUTH);

        setVisible(true);
    }


    private void initializeTextAndColor(JButton[][] cells) {

        Color baselineColor1 = new Color(15, 76, 117);
        Color baselineColor2 = new Color(50, 130, 184);

        for(int row = 0; row < cells.length; row++) {
            for(int column = 0; column < cells[0].length; column++){

                cells[row][column].setText(" ");

                if ((row + column) % 2 == 0) {
                    cells[row][column].setBackground(baselineColor1);
                } else {
                    cells[row][column].setBackground(baselineColor2);
                }
            }
        }
    }

    private void resetGame(JButton[][] cells) {
        initializeTextAndColor(cells);
        gameFinished = false;
        isNextMoveX = true;
    }

    private int getColumnNumberFromButtonName(String buttonName) {
        return buttonName.charAt(6) - 'A';
    }

    private void placePiece(int column) {

        if (gameFinished) return;

        int row = getFirstEmptySpot(column);
        if (row != -1) {
            cells[row][column].setText(isNextMoveX ? "X" : "O");

            if (checkForWin(row, column)) {
                gameFinished = true;
            } else {
                isNextMoveX = !isNextMoveX;
            }
        }
    }

    private int getFirstEmptySpot(int column) {
        for (int row = 0; row < 6; row++) {
            if (cells[row][column].getText().equals(" ")) {
                return row;
            }
        }
        return -1;
    }

    private boolean checkForWin(int row, int column) {

        String symbol = isNextMoveX ? "X" : "O";

        int count = 0;
        int[][] winningCells = new int[4][2];

        for (int c = 0; c < 7; c++) {
            if (cells[row][c].getText().equals(symbol)) {
                winningCells[count][0] = row;
                winningCells[count][1] = c;
                count++;
                if (count == 4) {
                    highlightWinningCells(winningCells);
                    return true;
                }
            } else {
                count = 0;
                winningCells = new int[4][2];
            }
        }

        count = 0;
        winningCells = new int[4][2];

        for (int r = 0; r < 6; r++) {
            if (cells[r][column].getText().equals(symbol)) {
                winningCells[count][0] = r;
                winningCells[count][1] = column;
                count++;
                if (count == 4) {
                    highlightWinningCells(winningCells);
                    return true;
                }
            } else {
                count = 0;
                winningCells = new int[4][2];
            }
        }

        count = 0;
        winningCells = new int[4][2];
        int startRow = row;
        int startColumn = column;

        while (startRow < 5 && startColumn > 0) {
            startRow++;
            startColumn--;
        }
        while (startRow >= 0 && startColumn < 7) {
            if (cells[startRow][startColumn].getText().equals(symbol)) {
                winningCells[count][0] = startRow;
                winningCells[count][1] = startColumn;
                count++;
                if (count == 4) {
                    highlightWinningCells(winningCells);
                    return true;
                }
            } else {
                count = 0;
                winningCells = new int[4][2];
            }
            startRow--;
            startColumn++;
        }


        count = 0;
        winningCells = new int[4][2];
        startRow = row;
        startColumn = column;

        while (startRow > 0 && startColumn > 0) {
            startRow--;
            startColumn--;
        }
        while (startRow < 6 && startColumn < 7) {
            if (cells[startRow][startColumn].getText().equals(symbol)) {
                winningCells[count][0] = startRow;
                winningCells[count][1] = startColumn;
                count++;
                if (count == 4) {
                    highlightWinningCells(winningCells);
                    return true;
                }
            } else {
                count = 0;
                winningCells = new int[4][2];
            }
            startRow++;
            startColumn++;
        }

        return false;
    }

    private void highlightWinningCells(int[][] winningCells) {

        Color winningColor = new Color(27, 38, 44);

        for (int i = 0; i < 4; i++) {
            int row = winningCells[i][0];
            int column = winningCells[i][1];
            cells[row][column].setBackground(winningColor);
        }
    }

}





