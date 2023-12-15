package Project;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Main {
    public static void main(String[] args) {
        JFrame frame =new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setPreferredSize(new Dimension(500,500));
        frame.setTitle("Sejong University Student Management");

        Primary primary = new Primary();
        frame.add(primary);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
        
    }
}
