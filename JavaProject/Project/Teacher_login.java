package Project;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Teacher_login extends JPanel {
    public Teacher_login() {
        setLayout(new FlowLayout());
        this.setLayout(null);

        JLabel username_txt = new JLabel("username:");
        JTextField username = new JTextField(20);
        username.setBounds(150, 200, 250, 20);
        username_txt.setBounds(70, 200, 100, 20);

        JLabel password_txt = new JLabel("password:");
        JPasswordField password = new JPasswordField(20);
        password.setBounds(150, 220, 250, 20);
        password_txt.setBounds(70, 220, 100, 20);

        JLabel title = new JLabel("Welcome Sejong University Teacher !");
        title.setBounds(150, 150, 300, 20);

        JButton loginbtn = new JButton("Login");
        loginbtn.setBounds(200, 250, 100, 30);

        JButton backbtn = new JButton("<<");
        backbtn.setBounds(10, 10, 30, 30);
        backbtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(Teacher_login.this);
                frame.getContentPane().removeAll();
                Primary primary = new Primary();
                frame.getContentPane().add(primary);
                frame.revalidate();
                frame.repaint();
            }
        });

        loginbtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                File username_file = new File("AllowID_Teacher/username.txt");
                File password_file = new File("AllowID_Teacher/password.txt");
                boolean username_confirm = false;
                boolean password_confirm = false;
                int index_username = 0;
                int index_password = 0;
                int final_username = 0;
                int final_password = 0;
                try (Scanner myReaderUser = new Scanner(username_file);
                     Scanner myReaderPass = new Scanner(password_file)) {

                    while (myReaderUser.hasNextLine()) {
                        String data_username = myReaderUser.nextLine();
                        String textFieldValue_username = username.getText();

                        if (data_username.equals(textFieldValue_username)) {
                            System.out.println("Username: " + data_username);
                            username_confirm = true;
                            final_username = index_username;
                        }
                        index_username++;
                    }

                    while (myReaderPass.hasNextLine()) {
                        String data_password = myReaderPass.nextLine();
                        String textFieldValue_password = new String(password.getPassword());

                        if (data_password.equals(textFieldValue_password) && (index_password==final_username)){
                            password_confirm = true;
                            final_password = index_password;
                        }
                        index_password++;
                    }
                    System.out.println(final_password);
                    System.out.println(final_username);
                    if ((username_confirm && password_confirm) &&(final_password == final_username)) {
                        JOptionPane.showMessageDialog(null, "Access Granted", "Complete", JOptionPane.INFORMATION_MESSAGE);
                        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(Teacher_login.this);
                        frame.getContentPane().removeAll();
                        Teacher_Manager teacher_Manager = new Teacher_Manager();
                        frame.getContentPane().add(teacher_Manager);
                        frame.revalidate();
                        frame.repaint();
                    } else {
                        JOptionPane.showMessageDialog(null, "Username or Password is wrong", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
            }
        });

        add(title);
        add(username_txt);
        add(username);
        add(password_txt);
        add(password);
        add(loginbtn);
        add(backbtn);
    }
}
