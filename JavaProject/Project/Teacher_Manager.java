package Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Teacher_Manager extends JPanel {
    public Teacher_Manager(){
        setLayout(new FlowLayout());
        this.setLayout(null);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();   
        
        JLabel welcome_txt = new JLabel("Hello teacher ! , Today is " + dtf.format(now));
        welcome_txt.setBounds(100, 100, 400, 30);
        add(welcome_txt);

        JButton Search_btn = new JButton("Search for student");
        Search_btn.setBounds(150, 150, 200, 50);
        add(Search_btn);

        Search_btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                Search();
            }
        });


        JButton Add_Student = new JButton("Add new student");
        Add_Student.setBounds(150, 250, 200, 50);
        add(Add_Student);

        Add_Student.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                String student_search = JOptionPane.showInputDialog("Enter student ID that you want to add");
                File student_add = new File("StudentInfo/"+student_search+".txt");
                if(student_search == null || student_search == " "){
                    JOptionPane.showMessageDialog(null, "Please enter student ID", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else{
                    try {
                        if(student_add.createNewFile()){
                            FileWriter writer = new FileWriter("StudentInfo/"+student_search+".txt");
                            String student_name = JOptionPane.showInputDialog("Enter student name: ");
                            writer.write("Name: " + student_name);
                            String nationality = JOptionPane.showInputDialog("Enter student's nationality:");
                            writer.write("\nNationality: " + nationality);
                            String dayofbirth = JOptionPane.showInputDialog("Enter student's day of birth , Ex : 2005/10/20");
                            writer.write("\nDay of birth: " + dayofbirth);
                            String department = JOptionPane.showInputDialog("Enter student's department:");
                            writer.write("\nDepartment: " + department);
                            String GPA = JOptionPane.showInputDialog("Enter student's GPA: ");
                            writer.write("\nGPA: " + GPA + "/4.5");
                            writer.close();
                            JOptionPane.showMessageDialog(null, "Student has been added to the list", "Information", JOptionPane.INFORMATION_MESSAGE);
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "The student is already add ! Please choose another", "error", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (HeadlessException e1) {
                        e1.printStackTrace();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
        JButton Student_List= new JButton("Student List");
        Student_List.setBounds(150, 200, 200, 50);
        add(Student_List);
        Student_List.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                File studentinfo = new File("StudentInfo");
                File[] listOfStudent = studentinfo.listFiles();
                int student_count = studentinfo.list().length;
                System.out.println("The total student in the list is: " + student_count);
                StringBuilder info = new StringBuilder();
                for(int i = 0 ; i < listOfStudent.length; i++){
                    if(listOfStudent[i].isFile()){
                        System.out.print(listOfStudent[i]);
                        info.append(listOfStudent[i].getName().replaceFirst("[.][^.]+$", "")).append("\n");
                        System.out.println(listOfStudent[i].getName().replaceFirst("[.][^.]+$", ""));
                    }else if(listOfStudent[i].isDirectory()){
                        System.out.println("Directory " + listOfStudent[i].getName());
                    }
                }
                JOptionPane.showMessageDialog(null, "The Total student:" + student_count + "\n" +info.toString());
            }
        });


        JButton log_out = new JButton("Log out");
        log_out.setBounds(10, 10, 100, 30);
        add(log_out);
        log_out.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(Teacher_Manager.this);
                frame.getContentPane().removeAll();
                Teacher_login teacher = new Teacher_login();
                frame.getContentPane().add(teacher); 
                frame.revalidate(); 
                frame.repaint();
            }
        });

        JButton delete_student = new JButton("Delete student");
        delete_student.setBounds(150, 300, 200, 50);
        add(delete_student);
        delete_student.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                 String student_search = JOptionPane.showInputDialog("Enter student ID that you want to delete");
                if(student_search == null){
                    JOptionPane.showMessageDialog(null, "Please enter student ID", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else{
                    File student_info = new File("StudentInfo/"+student_search+".txt");
                    if(student_info.exists()){
                        int option = JOptionPane.showConfirmDialog(null, "Do you want to delete student " + student_search + "?","Confirm", JOptionPane.YES_OPTION);
                        if(option == JOptionPane.YES_OPTION){
                            student_info.delete();
                            JOptionPane.showMessageDialog(null, "Student " + student_search + " is deleted");
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Student is not found", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }   
            }
        });
    }

    public void Search(){
        String student_search = JOptionPane.showInputDialog("Enter student ID that you want to search");
            if(student_search == null){
                JOptionPane.showMessageDialog(null, "Please enter student ID", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else{
                File student_info = new File("StudentInfo/"+student_search+".txt");
                if(student_info.exists()){
                    try {
                        Scanner read_info = new Scanner(student_info);
                        StringBuilder infoMessage = new StringBuilder();

                        while(read_info.hasNext()){
                            infoMessage.append(read_info.nextLine()).append("\n");
                        }
                        JOptionPane.showMessageDialog(null, infoMessage.toString());
                    } catch (FileNotFoundException e1) {
                        e1.printStackTrace();
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "Student is not found", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
    }
}