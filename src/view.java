
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class view extends JFrame {
    private   JScrollPane scrollPane_1;
    private   JScrollPane scrollPane;
    private  JTextArea processTextArea;
    private JTextArea textArea;
    private JScrollPane scrollPane_1_1;
    private JTextArea textArea_1;
    private  JScrollPane scrollPane_1_2;
    private  JTextArea textArea_2;
    private JLabel lblNewLabel;
    private JLabel lblNewLabel_1;
    private JLabel lblNewLabel_2;
    private  JLabel lblNewLabel_1_1;
    private JLabel lblNewLabel_3;


    private  JTextField textField;
    private JTextField textField_1;
    private JLabel lblNewLabel_4;
    private JLabel lblNewLabel_4_1;
    private JButton  btnNewButton_1;
    private JLabel lblNewLabel_5;
    private JLabel lblNewLabel_6;
    private JLabel lblNewLabel_7;
    private JLabel lblNewLabel_6_1;
  private JTextArea textArea_3;
  private JTextArea textArea_3_1;
 private JTextField textField_2;
 private JLabel lblNewLabel_4_1_1;
 private JTextArea  textArea_3_2;
 private JButton btnNewButton_1_1;
  private JLabel lblNewLabel_8;
    private JLabel lblNewLabel_8_1;
    private JLabel lblNewLabel_8_2;
    private JLabel lblNewLabel_8_3;

    public view()
    {
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(100, 100, 1572, 1032);
        // this.getContentPane().setBorder(new EmptyBorder(5, 5, 5, 5));
        this.getContentPane().setBackground(new Color(255, 215, 0));
        this.getContentPane().setLayout(null);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(56, 151, 372, 658);
        this.getContentPane().add(scrollPane);

        processTextArea = new JTextArea();
        processTextArea.setFont(new Font("Arial", Font.PLAIN, 25));
        processTextArea.setBackground(new Color(255, 250, 205));
        scrollPane.setViewportView(processTextArea);

        scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(560, 236, 442, 257);
        this.getContentPane().add(scrollPane_1);

        textArea = new JTextArea();
        textArea.setFont(new Font("Arial", Font.PLAIN, 25));
        textArea.setBackground(new Color(255, 250, 205));
        scrollPane_1.setViewportView(textArea);

        scrollPane_1_1 = new JScrollPane();
        scrollPane_1_1.setBounds(560, 537, 442, 257);
        this.getContentPane().add(scrollPane_1_1);

        textArea_1 = new JTextArea();
        textArea_1.setFont(new Font("Arial", Font.PLAIN, 25));
        textArea_1.setBackground(new Color(255, 250, 205));
        scrollPane_1_1.setViewportView(textArea_1);

        scrollPane_1_2 = new JScrollPane();
        scrollPane_1_2.setBounds(1071, 55, 442, 833);
        this.getContentPane().add(scrollPane_1_2);

        textArea_2 = new JTextArea();
        textArea_2.setFont(new Font("Arial", Font.PLAIN, 25));
        textArea_2.setBackground(new Color(255, 250, 205));
        scrollPane_1_2.setViewportView(textArea_2);

        lblNewLabel = new JLabel("Processes and their virtual addresses");
        lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 25));
        lblNewLabel.setBounds(49, 67, 421, 53);
        this.getContentPane().add(lblNewLabel);

        lblNewLabel_1 = new JLabel("Translation lookaside buffer");
        lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 25));
        lblNewLabel_1.setBounds(560, 151, 421, 53);
        this.getContentPane().add(lblNewLabel_1);

        lblNewLabel_2 = new JLabel("Page tables");
        lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 25));
        lblNewLabel_2.setBounds(570, 486, 421, 53);
        this.getContentPane().add(lblNewLabel_2);

        lblNewLabel_1_1 = new JLabel("Physical memory");
        lblNewLabel_1_1.setFont(new Font("Arial", Font.PLAIN, 25));
        lblNewLabel_1_1.setBounds(1082, 0, 421, 53);
        this.getContentPane().add(lblNewLabel_1_1);

        lblNewLabel_3 = new JLabel("Virtual memory simulator");
        lblNewLabel_3.setFont(new Font("Arial", Font.PLAIN, 35));
        lblNewLabel_3.setBounds(544, 25, 449, 65);
        this.getContentPane().add(lblNewLabel_3);




        textField = new JTextField();
        textField.setFont(new Font("Arial", Font.PLAIN, 11));
        textField.setBounds(312, 923, 96, 46);
        textField.setBackground(new Color(255, 250, 205));
        this.getContentPane().add(textField);
        textField.setColumns(10);

        textField_1 = new JTextField();
        textField_1.setFont(new Font("Arial", Font.PLAIN, 11));
        textField_1.setColumns(10);
        textField_1.setBounds(438, 923, 96, 46);
        textField_1.setBackground(new Color(255, 250, 205));
        this.getContentPane().add(textField_1);

         lblNewLabel_4 = new JLabel("process");
        lblNewLabel_4.setFont(new Font("Arial", Font.PLAIN, 15));
        lblNewLabel_4.setBounds(322, 907, 69, 14);
        this.getContentPane().add(lblNewLabel_4);

        lblNewLabel_4_1 = new JLabel("page");
        lblNewLabel_4_1.setFont(new Font("Arial", Font.PLAIN, 15));
        lblNewLabel_4_1.setBounds(460, 907, 49, 14);
        this.getContentPane().add(lblNewLabel_4_1);

        btnNewButton_1 = new JButton("Place page");
        btnNewButton_1.setFont(new Font("Arial", Font.PLAIN, 30));
        btnNewButton_1.setBounds(312, 820, 189, 76);
        btnNewButton_1.setBackground(new Color(255, 250, 205));
        this.getContentPane().add(btnNewButton_1);

        btnNewButton_1_1 = new JButton("Find information");
        btnNewButton_1_1.setFont(new Font("Arial", Font.PLAIN, 30));
        btnNewButton_1_1.setBounds(10, 820, 279, 76);
        btnNewButton_1_1.setBackground(new Color(255, 250, 205));
        this.getContentPane().add(btnNewButton_1_1);


        lblNewLabel_5 = new JLabel("Statics");
        lblNewLabel_5.setFont(new Font("Arial", Font.PLAIN, 25));
        lblNewLabel_5.setBounds(876, 834, 175, 53);
        this.getContentPane().add(lblNewLabel_5);

         lblNewLabel_6 = new JLabel("Hit:");
        lblNewLabel_6.setFont(new Font("Arial", Font.PLAIN, 15));
        lblNewLabel_6.setBounds(864, 898, 81, 33);
        this.getContentPane().add(lblNewLabel_6);

         lblNewLabel_6_1 = new JLabel("Miss:");
        lblNewLabel_6_1.setFont(new Font("Arial", Font.PLAIN, 15));
        lblNewLabel_6_1.setBounds(864, 938, 81, 33);
        this.getContentPane().add(lblNewLabel_6_1);

         lblNewLabel_7 = new JLabel("Time:");
        lblNewLabel_7.setFont(new Font("Arial", Font.PLAIN, 26));
        lblNewLabel_7.setBounds(560, 106, 119, 34);
        this.getContentPane().add(lblNewLabel_7);

         textArea_3 = new JTextArea();
        textArea_3.setFont(new Font("Arial", Font.PLAIN, 15));
        textArea_3.setBounds(901, 898, 101, 33);
        textArea_3.setBackground(new Color(255, 250, 205));
        this.getContentPane().add(textArea_3);

       textArea_3_1 = new JTextArea();
        textArea_3_1.setFont(new Font("Arial", Font.PLAIN, 15));
        textArea_3_1.setBounds(901, 938, 101, 33);
        textArea_3_1.setBackground(new Color(255, 250, 205));
        this.getContentPane().add(textArea_3_1);



        lblNewLabel_4_1_1 = new JLabel("offset");
        lblNewLabel_4_1_1.setFont(new Font("Arial", Font.PLAIN, 15));
        lblNewLabel_4_1_1.setBounds(560, 908, 49, 14);
        this.getContentPane().add(lblNewLabel_4_1_1);

        textField_2 = new JTextField();
        textField_2.setFont(new Font("Arial", Font.PLAIN, 11));
        textField_2.setColumns(10);
        textField_2.setBounds(560, 923, 96, 46);
        textField_2.setBackground(new Color(255, 250, 205));
        this.getContentPane().add(textField_2);



         textArea_3_2 = new JTextArea();
        textArea_3_2.setFont(new Font("Arial", Font.PLAIN, 17));
        textArea_3_2.setBounds(644, 107, 101, 33);
        textArea_3_2.setBackground(new Color(255, 250, 205));
        this.getContentPane().add(textArea_3_2);


         lblNewLabel_8 = new JLabel("Page+offset");
        lblNewLabel_8.setFont(new Font("Arial", Font.PLAIN, 25));
        lblNewLabel_8.setBounds(144, 118, 300, 33);
        this.getContentPane().add(lblNewLabel_8);

        lblNewLabel_8_1 = new JLabel("Frame+page");
        lblNewLabel_8_1.setFont(new Font("Arial", Font.PLAIN, 25));
        lblNewLabel_8_1.setBounds(1213, 911, 300, 33);
        this.getContentPane().add(lblNewLabel_8_1);

         lblNewLabel_8_2 = new JLabel("Page+frame");
        lblNewLabel_8_2.setFont(new Font("Arial", Font.PLAIN, 25));
        lblNewLabel_8_2.setBounds(603, 198, 300, 33);
        this.getContentPane().add(lblNewLabel_8_2);

         lblNewLabel_8_3 = new JLabel("Page+frame+present bit");
        lblNewLabel_8_3.setFont(new Font("Arial", Font.PLAIN, 25));
        lblNewLabel_8_3.setBounds(644, 805, 300, 33);
        this.getContentPane().add(lblNewLabel_8_3);

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
    public int gettextField3()
    {
        return Integer.parseInt(textField_2.getText());
    }
    public void addCreateListener2(ActionListener action)
    {
        btnNewButton_1.addActionListener(action);
    }
    public void addCreateListener3(ActionListener action)
    {
        btnNewButton_1_1.addActionListener(action);
    }


}
