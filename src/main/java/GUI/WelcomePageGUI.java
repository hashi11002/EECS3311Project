package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomePageGUI extends JFrame {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            WelcomePageGUI welcomePage = new WelcomePageGUI();
        });
    }

    public WelcomePageGUI() {
        setTitle("Welcome Page");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));

        JButton adminButton = createButton("Admin Login", this::openAdminLogin);
        JButton clientButton = createButton("Client Ordering", this::openClientOrdering);

        panel.add(adminButton);
        panel.add(clientButton);

        add(panel);
        setLocationRelativeTo(null);
        setVisible(true); 
    }

    private JButton createButton(String text, ActionListener listener) {
        JButton button = new JButton(text);
        button.addActionListener(listener);
        return button;
    }

    private void openAdminLogin(ActionEvent e) {
        LoginGUI adminLoginGUI = new LoginGUI();
        adminLoginGUI.setVisible(true);
        dispose(); 
    }

    private void openClientOrdering(ActionEvent e) {
        PlacingOrderGUI clientOrdering = new PlacingOrderGUI();
        clientOrdering.setVisible(true);
        dispose(); 
    }
}
