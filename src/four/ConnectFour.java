package four;

import javax.swing.*;
import java.awt.*;

public class ConnectFour extends JFrame {
    public ConnectFour() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setTitle("Connect Four");
        setLocationRelativeTo(null);

        JPanel boardPanel = new JPanel(new GridLayout(6, 7));

        for (int row = 5; row >= 0; row--) { // Start from the last row (bottom row)
            for (int column = 0; column < 7; column++){
                JButton cell = new JButton();
                String cellName = (char) ('A' + column) + Integer.toString(row + 1);
                cell.setName("Button" + cellName);
                cell.setText(cellName);
                boardPanel.add(cell);
            }
        }

        add(boardPanel);
        setVisible(true);
    }
}
