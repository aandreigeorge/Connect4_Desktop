package four;

import javax.swing.*;
import java.awt.*;

public class ConnectFour extends JFrame {

    private boolean isNextMoveX = true;

    public ConnectFour() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setTitle("Connect Four");
        setLocationRelativeTo(null);

        JPanel boardPanel = new JPanel(new GridLayout(6, 7));

        for (int row = 5; row >= 0; row--) {
            for (int column = 0; column < 7; column++){
                JButton cell = new JButton();
                cell.setName("Button" + (char) ('A' + column) + (row + 1));
                cell.setText(" ");
                boardPanel.add(cell);

                cell.addActionListener(e -> {
                    JButton clickedCell = (JButton) e.getSource();
                    if (clickedCell.getText().equals(" ")) {
                        clickedCell.setText(isNextMoveX ? "X" : "O");
                        isNextMoveX = !isNextMoveX;
                    }
                });
            }
        }

        add(boardPanel);
        setVisible(true);
    }
}


