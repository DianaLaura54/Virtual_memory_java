import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class view2 extends JFrame {
    private JButton btnNewButton;
    private  JTextField textField;
    private JLabel lblNewLabel_4;
    private JTextField textField_1;
    private JTextField textField_2;
    private JLabel lblNewLabel_4_1;
    private JLabel lblNewLabel_4_1_1;
    private JTextField textField_3;
    private JLabel lblNewLabel_4_1_2;
    private JTextField textField_4;
    private JLabel  lblNewLabel_4_1_2_1;
    private JLabel lblNewLabel_3;


    public view2()
    {
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(100, 100, 1572, 1032);
        this.getContentPane().setBackground(new Color(255, 215, 0));
        // this.getContentPane().setBorder(new EmptyBorder(5, 5, 5, 5));

        this.getContentPane().setLayout(null);


        lblNewLabel_3 = new JLabel("Virtual memory simulator");
        lblNewLabel_3.setFont(new Font("Arial", Font.PLAIN, 44));
        lblNewLabel_3.setBounds(544, 25, 547, 65);
        this.getContentPane().add(lblNewLabel_3);


        btnNewButton = new JButton("Please insert");
        btnNewButton.setFont(new Font("Arial", Font.PLAIN, 40));
        btnNewButton.setBounds(758, 785, 426, 84);
        btnNewButton.setBackground(new Color(255, 250, 205));
        this.getContentPane().add(btnNewButton);

        textField = new JTextField();
        textField.setFont(new Font("Arial", Font.PLAIN, 30));
        textField.setBounds(796, 256, 132, 35);
        textField.setBackground(new Color(255, 250, 205));
        this.getContentPane().add(textField);
        textField.setColumns(10);

         lblNewLabel_4 = new JLabel("nr processes");
        lblNewLabel_4.setFont(new Font("Arial", Font.PLAIN, 30));
        lblNewLabel_4.setBounds(810, 206, 183, 22);
        this.getContentPane().add(lblNewLabel_4);

        textField_1 = new JTextField();
        textField_1.setFont(new Font("Arial", Font.PLAIN, 30));
        textField_1.setColumns(10);
        textField_1.setBounds(187, 457, 132, 35);
        textField_1.setBackground(new Color(255, 250, 205));
        this.getContentPane().add(textField_1);

        textField_2 = new JTextField();
        textField_2.setFont(new Font("Arial", Font.PLAIN, 30));
        textField_2.setColumns(10);
        textField_2.setBounds(449, 457, 132, 35);
        textField_2.setBackground(new Color(255, 250, 205));
        this.getContentPane().add(textField_2);

         lblNewLabel_4_1 = new JLabel("physical page size");
        lblNewLabel_4_1.setFont(new Font("Arial", Font.PLAIN, 30));
        lblNewLabel_4_1.setBounds(123, 406, 263, 40);
        this.getContentPane().add(lblNewLabel_4_1);

        lblNewLabel_4_1_1 = new JLabel("offset bits for a process");
        lblNewLabel_4_1_1.setFont(new Font("Arial", Font.PLAIN, 30));
        lblNewLabel_4_1_1.setBounds(421, 406, 351, 40);
        this.getContentPane().add(lblNewLabel_4_1_1);

        textField_3 = new JTextField();
        textField_3.setFont(new Font("Arial", Font.PLAIN, 30));
        textField_3.setColumns(10);
        textField_3.setBounds(187, 256, 132, 35);
        textField_3.setBackground(new Color(255, 250, 205));
        this.getContentPane().add(textField_3);

        lblNewLabel_4_1_2 = new JLabel("virtual memory size");
        lblNewLabel_4_1_2.setFont(new Font("Arial", Font.PLAIN, 30));
        lblNewLabel_4_1_2.setBounds(138, 185, 263, 65);
        this.getContentPane().add(lblNewLabel_4_1_2);

        textField_4 = new JTextField();
        textField_4.setFont(new Font("Arial", Font.PLAIN, 30));
        textField_4.setColumns(10);
        textField_4.setBounds(503, 256, 132, 35);
        textField_4.setBackground(new Color(255, 250, 205));
        this.getContentPane().add(textField_4);

       lblNewLabel_4_1_2_1 = new JLabel("number of TLB");
        lblNewLabel_4_1_2_1.setFont(new Font("Arial", Font.PLAIN, 30));
        lblNewLabel_4_1_2_1.setBounds(479, 206, 200, 22);
        this.getContentPane().add(lblNewLabel_4_1_2_1);


        this.setVisible(true);

    }

    public int gettextField() {
        return Integer.parseInt(textField.getText());
    }
    public int gettextField1() {
        return Integer.parseInt(textField_1.getText());
    }

    public int gettextField2() {
        return Integer.parseInt(textField_2.getText());
    }

    public int gettextField3() {
        return Integer.parseInt(textField_3.getText());
    }

    public int gettextField4() {
        return Integer.parseInt(textField_4.getText());
    }

    public void addCreateListener(ActionListener action)
    {
        btnNewButton.addActionListener(action);
    }
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
