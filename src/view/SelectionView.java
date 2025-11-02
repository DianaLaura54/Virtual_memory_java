package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SelectionView extends JFrame {
    private JScrollPane scrollPane_1;
    private JScrollPane scrollPane;
    private JTextArea processTextArea;
    private JTextArea textArea;
    private JScrollPane scrollPane_1_1;
    private JTextArea textArea_1;
    private JScrollPane scrollPane_1_2;
    private JTextArea textArea_2;
    private JLabel lblNewLabel;
    private JLabel lblNewLabel_1;
    private JLabel lblNewLabel_2;
    private JLabel lblNewLabel_1_1;
    private JLabel lblNewLabel_3;
    private JTextField textField;
    private JTextField textField_1;
    private JLabel lblNewLabel_4;
    private JLabel lblNewLabel_4_1;
    private JButton btnNewButton_1;
    private JLabel lblNewLabel_5;
    private JLabel lblNewLabel_6;
    private JLabel lblNewLabel_7;
    private JLabel lblNewLabel_6_1;
    private JTextArea textArea_3;
    private JTextArea textArea_3_1;
    private JTextField textField_2;
    private JLabel lblNewLabel_4_1_1;
    private JTextArea textArea_3_2;
    private JButton btnNewButton_1_1;

    public SelectionView() {
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(100, 100, 1200, 800);
        this.getContentPane().setBackground(new Color(255, 215, 0));
        this.getContentPane().setLayout(null);

        // Main title
        lblNewLabel_3 = new JLabel("Virtual Memory Simulator");
        lblNewLabel_3.setFont(new Font("Arial", Font.BOLD, 26));
        lblNewLabel_3.setBounds(380, 10, 420, 40);
        this.getContentPane().add(lblNewLabel_3);

        // Left panel - Processes and virtual addresses
        lblNewLabel = new JLabel("Processes (Page + Offset)");
        lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        lblNewLabel.setBounds(20, 55, 280, 25);
        this.getContentPane().add(lblNewLabel);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 85, 280, 380);
        this.getContentPane().add(scrollPane);

        processTextArea = new JTextArea();
        processTextArea.setFont(new Font("Arial", Font.PLAIN, 14));
        processTextArea.setBackground(new Color(255, 250, 205));
        scrollPane.setViewportView(processTextArea);

        // Middle-top panel - TLB
        lblNewLabel_1 = new JLabel("TLB (Page : Frame)");
        lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 16));
        lblNewLabel_1.setBounds(320, 55, 260, 25);
        this.getContentPane().add(lblNewLabel_1);

        // Time display
        lblNewLabel_7 = new JLabel("Time:");
        lblNewLabel_7.setFont(new Font("Arial", Font.PLAIN, 14));
        lblNewLabel_7.setBounds(580, 55, 50, 25);
        this.getContentPane().add(lblNewLabel_7);

        textArea_3_2 = new JTextArea();
        textArea_3_2.setFont(new Font("Arial", Font.PLAIN, 14));
        textArea_3_2.setBounds(635, 55, 60, 25);
        textArea_3_2.setBackground(new Color(255, 250, 205));
        textArea_3_2.setEditable(false);
        this.getContentPane().add(textArea_3_2);

        scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(320, 85, 270, 165);
        this.getContentPane().add(scrollPane_1);

        textArea = new JTextArea();
        textArea.setFont(new Font("Arial", Font.PLAIN, 14));
        textArea.setBackground(new Color(255, 250, 205));
        scrollPane_1.setViewportView(textArea);

        // Middle-bottom panel - Page Tables
        lblNewLabel_2 = new JLabel("Page Tables (Page : Frame : Valid)");
        lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 16));
        lblNewLabel_2.setBounds(320, 260, 300, 25);
        this.getContentPane().add(lblNewLabel_2);

        scrollPane_1_1 = new JScrollPane();
        scrollPane_1_1.setBounds(320, 290, 270, 175);
        this.getContentPane().add(scrollPane_1_1);

        textArea_1 = new JTextArea();
        textArea_1.setFont(new Font("Arial", Font.PLAIN, 14));
        textArea_1.setBackground(new Color(255, 250, 205));
        scrollPane_1_1.setViewportView(textArea_1);

        // Right panel - Physical Memory
        lblNewLabel_1_1 = new JLabel("Physical Memory (Frame : Page)");
        lblNewLabel_1_1.setFont(new Font("Arial", Font.PLAIN, 16));
        lblNewLabel_1_1.setBounds(610, 85, 320, 25);
        this.getContentPane().add(lblNewLabel_1_1);

        scrollPane_1_2 = new JScrollPane();
        scrollPane_1_2.setBounds(610, 115, 270, 350);
        this.getContentPane().add(scrollPane_1_2);

        textArea_2 = new JTextArea();
        textArea_2.setFont(new Font("Arial", Font.PLAIN, 14));
        textArea_2.setBackground(new Color(255, 250, 205));
        scrollPane_1_2.setViewportView(textArea_2);

        // Bottom controls
        btnNewButton_1_1 = new JButton("Find Info");
        btnNewButton_1_1.setFont(new Font("Arial", Font.PLAIN, 16));
        btnNewButton_1_1.setBounds(20, 480, 135, 45);
        btnNewButton_1_1.setBackground(new Color(255, 250, 205));
        this.getContentPane().add(btnNewButton_1_1);

        btnNewButton_1 = new JButton("Place Page");
        btnNewButton_1.setFont(new Font("Arial", Font.PLAIN, 16));
        btnNewButton_1.setBounds(165, 480, 135, 45);
        btnNewButton_1.setBackground(new Color(255, 250, 205));
        this.getContentPane().add(btnNewButton_1);

        // Input fields
        lblNewLabel_4 = new JLabel("Process:");
        lblNewLabel_4.setFont(new Font("Arial", Font.PLAIN, 14));
        lblNewLabel_4.setBounds(20, 540, 70, 25);
        this.getContentPane().add(lblNewLabel_4);

        textField = new JTextField();
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        textField.setBounds(90, 540, 70, 28);
        textField.setBackground(new Color(255, 250, 205));
        this.getContentPane().add(textField);
        textField.setColumns(10);

        lblNewLabel_4_1 = new JLabel("Page:");
        lblNewLabel_4_1.setFont(new Font("Arial", Font.PLAIN, 14));
        lblNewLabel_4_1.setBounds(170, 540, 50, 25);
        this.getContentPane().add(lblNewLabel_4_1);

        textField_1 = new JTextField();
        textField_1.setFont(new Font("Arial", Font.PLAIN, 14));
        textField_1.setColumns(10);
        textField_1.setBounds(220, 540, 70, 28);
        textField_1.setBackground(new Color(255, 250, 205));
        this.getContentPane().add(textField_1);

        lblNewLabel_4_1_1 = new JLabel("Offset:");
        lblNewLabel_4_1_1.setFont(new Font("Arial", Font.PLAIN, 14));
        lblNewLabel_4_1_1.setBounds(20, 580, 70, 25);
        this.getContentPane().add(lblNewLabel_4_1_1);

        textField_2 = new JTextField();
        textField_2.setFont(new Font("Arial", Font.PLAIN, 14));
        textField_2.setColumns(10);
        textField_2.setBounds(90, 580, 70, 28);
        textField_2.setBackground(new Color(255, 250, 205));
        this.getContentPane().add(textField_2);

        // Statistics panel
        lblNewLabel_5 = new JLabel("Statistics");
        lblNewLabel_5.setFont(new Font("Arial", Font.BOLD, 18));
        lblNewLabel_5.setBounds(610, 480, 150, 30);
        this.getContentPane().add(lblNewLabel_5);

        lblNewLabel_6 = new JLabel("Hits:");
        lblNewLabel_6.setFont(new Font("Arial", Font.PLAIN, 14));
        lblNewLabel_6.setBounds(610, 520, 60, 25);
        this.getContentPane().add(lblNewLabel_6);

        textArea_3 = new JTextArea();
        textArea_3.setFont(new Font("Arial", Font.PLAIN, 14));
        textArea_3.setBounds(670, 520, 70, 25);
        textArea_3.setBackground(new Color(255, 250, 205));
        textArea_3.setEditable(false);
        this.getContentPane().add(textArea_3);

        lblNewLabel_6_1 = new JLabel("Misses:");
        lblNewLabel_6_1.setFont(new Font("Arial", Font.PLAIN, 14));
        lblNewLabel_6_1.setBounds(610, 555, 60, 25);
        this.getContentPane().add(lblNewLabel_6_1);

        textArea_3_1 = new JTextArea();
        textArea_3_1.setFont(new Font("Arial", Font.PLAIN, 14));
        textArea_3_1.setBounds(670, 555, 70, 25);
        textArea_3_1.setBackground(new Color(255, 250, 205));
        textArea_3_1.setEditable(false);
        this.getContentPane().add(textArea_3_1);

        this.setVisible(true);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public void setTextArea(String TextArea) {
        this.processTextArea.setText(TextArea);
    }

    public void setTextArea2(String TextArea) {
        this.textArea.setText(TextArea);
    }

    public void setTextArea3(String TextArea) {
        this.textArea_1.setText(TextArea);
    }

    public void setTextArea4(String TextArea) {
        this.textArea_2.setText(TextArea);
    }

    public void setTextArea_3_2(String TextArea) {
        this.textArea_3_2.setText(TextArea);
    }

    public void setTextArea_3(int TextArea_3) {
        this.textArea_3.setText(String.valueOf(TextArea_3));
    }

    public void setTextArea_3_1(int TextArea_3_1) {
        this.textArea_3_1.setText(String.valueOf(TextArea_3_1));
    }

    public int gettextField1() {
        return Integer.parseInt(textField.getText());
    }

    public int gettextField2() {
        return Integer.parseInt(textField_1.getText());
    }

    public int gettextField3() {
        return Integer.parseInt(textField_2.getText());
    }

    public void addCreateListener2(ActionListener action) {
        btnNewButton_1.addActionListener(action);
    }

    public void addCreateListener3(ActionListener action) {
        btnNewButton_1_1.addActionListener(action);
    }
}