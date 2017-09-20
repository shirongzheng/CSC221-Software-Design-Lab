/* CSC22100-R   Assignment 5(Final Project) - Artificial Life & GUI Simulation

    Shirong Zheng

    12/26/2016
 */

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Scanner;

public class SimulationGUI {

    private JFrame frame;
    private JTextField textField_1;
    private JTextField textField_2;
    public static Earth earth=new Earth();
    public static int round=0;
    public static int time=0;

    public SimulationGUI() {
        initialize();
    }

    private void initialize() {


        frame = new JFrame("Artificial Life");
        frame.setBounds(120, 120, 980, 970);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(20, 45, 940, 700);
        frame.getContentPane().add(panel);
        panel.setLayout(null);

        JTextArea txtrPleaseEnterThe = new JTextArea();
        txtrPleaseEnterThe.setEditable(false);
        txtrPleaseEnterThe.setText("Enter the number of the cycles jump per time:");
        txtrPleaseEnterThe.setBounds(24, 16, 360, 29);
        panel.add(txtrPleaseEnterThe);

        final JTextArea textArea = new JTextArea();
        textArea.setBounds(24, 84, 730, 500);

        panel.add(textArea);


        JButton Submit = new JButton("Initial ");

        Submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                String userInput = textField_1.getText();

                earth.initializeEarth();
                earth.growGrass();

                Scanner input=new Scanner(System.in);

                time= Integer.parseInt(userInput);

                textArea.append("Initial state: ");
                textArea.append("\n");

                for(int i=0;i<900;i++){
                    if(i!=0&&i%30==0)
                    {
                        textArea.append("\n");
                    }
                    textArea.append(earth.printEarth().get(i)+"     ");

                }

            }
        });

        Submit.setBounds(530, 15, 120, 27);
        panel.add(Submit);

        textField_1 = new JTextField();
        textField_1.setBounds(415, 15, 96, 27);
        panel.add(textField_1);
        textField_1.setColumns(10);

        JButton btnNext = new JButton("Next");
        btnNext.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                textArea.setText("");
                earth.resetAttribute();
                earth.growGrass();
                earth.Eat();
                earth.Move();
                earth.Eat();
                earth.Reproduce();
                earth.Time();
                earth.Die();
                round+=1;
                textArea.append("Cycle: "+round*time);
                textArea.append("\n");

                for(int i=0;i<900;i++){
                    if(i!=0&&i%30==0)
                    {
                        textArea.append("\n");
                    }
                    textArea.append(earth.printEarth().get(i)+"     ");

                }
                try {
                    Thread.sleep(400);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                for(int i=0;i<900;i++){
                    if(i!=0&&i%30==0)
                    {
                        textArea.append("\n");
                    }
                    textArea.append(earth.printEarth().get(i));

                }

                System.out.println("Ended this round!");
            }
        });
        btnNext.setBounds(663, 10, 123, 29);
        panel.add(btnNext);

        textField_2 = new JTextField();
        textField_2.setBounds(24, 605, 730, 27);
        textField_2.setText(" sand: .     Grass: *      Herbivore: &      Carnivore: @");
        panel.add(textField_2);
        textField_2.setColumns(10);

    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SimulationGUI window = new SimulationGUI();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


}

