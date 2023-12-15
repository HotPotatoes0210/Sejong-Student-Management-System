package Project;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Primary extends JPanel{
    public Primary(){
        setLayout(new FlowLayout());
        this.setLayout(null);
        JLabel label1 = new JLabel("Welcome to Sejong University System Management ");
        label1.setBounds(100, 20, 400, 200);
        JLabel label2 = new JLabel("Please press Start to login");
        label2.setBounds(180, 40, 400, 200);

        add(label1);
        add(label2);

        

        JButton teacher_btn = new JButton("Start");
        teacher_btn.setBounds(160,190,200,60);
        teacher_btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(Primary.this);
                frame.getContentPane().removeAll();

                Teacher_login teacher = new Teacher_login();
                frame.getContentPane().add(teacher); 
                frame.revalidate(); 
                frame.repaint(); 
            }
        });

        add(teacher_btn);
        
    }
}