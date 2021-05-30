import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.*;
import javax.swing.JLabel;

/**
 * the code is documentation enough
 */
public class Window {

    private JFrame frmTest;
    private Picture canvas = new Picture();
    private JTextField textFieldWidth;
    private JTextField textFieldHeight;
    private JTextField textfieldProb;
    private JTextField textFieldSpeed;
    
    private Map map;
    private Thread cycleThread;
    private boolean started;
    
    private int fensterleiste;
    private int randkorrektur;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Window window = new Window();
                    window.frmTest.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public Window() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        fensterleiste = 39;
        randkorrektur = 16;
        
        frmTest = new JFrame();
        frmTest.setTitle("Game of Life");
        frmTest.setBounds(300, 300, 335 + randkorrektur, 55 + fensterleiste);
        frmTest.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmTest.getContentPane().setLayout(null);
        
        textFieldWidth = new JTextField();
        textFieldWidth.setHorizontalAlignment(SwingConstants.CENTER);
        textFieldWidth.setText("50");
        textFieldWidth.setBounds(5, 5, 40, 20);
        frmTest.getContentPane().add(textFieldWidth);
        textFieldWidth.setColumns(10);
        
        textFieldHeight = new JTextField();
        textFieldHeight.setText("50");
        textFieldHeight.setHorizontalAlignment(SwingConstants.CENTER);
        textFieldHeight.setBounds(50, 5, 40, 20);
        frmTest.getContentPane().add(textFieldHeight);
        textFieldHeight.setColumns(10);
        
        textfieldProb = new JTextField();
        textfieldProb.setText("30");
        textfieldProb.setHorizontalAlignment(SwingConstants.CENTER);
        textfieldProb.setBounds(95, 5, 40, 20);
        frmTest.getContentPane().add(textfieldProb);
        textfieldProb.setColumns(10);
        
        textFieldSpeed = new JTextField();
        textFieldSpeed.setText("10");
        textFieldSpeed.setHorizontalAlignment(SwingConstants.CENTER);
        textFieldSpeed.setBounds(140, 5, 40, 20);
        frmTest.getContentPane().add(textFieldSpeed);
        textFieldSpeed.setColumns(10);
        
        canvas.setBackground(Color.BLACK);
        //canvas.setBounds(120, 10, 100, 100);
        frmTest.getContentPane().add(canvas);
        
        JButton btnNew = new JButton("New");
        btnNew.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                int width = Integer.parseInt(textFieldWidth.getText());
                int height = Integer.parseInt(textFieldHeight.getText());
                double prob = Double.parseDouble(textfieldProb.getText());
                if(width < 2) {
                    System.out.println("zu geringe Breite");
                }else if(width > 1000) {
                    System.out.println("zu grosse Breite");
                }else if(height < 2) {
                    System.out.println("zu geringe Hoehe");
                }else if(height > 1000) {
                    System.out.println("zu grosse Hoehe");
                }else if(prob < 1) {
                    System.out.println("zu geringe Wahrscheinlichkeit");
                }else if(prob > 99) {
                    System.out.println("zu grosse Wahrscheinlichket");
                }else{
                    canvas.setMap(width, height, prob);
                    map = canvas.getMap();
                    width = 10 * width;
                    height = 10* height;
                    canvas.setBounds(0, 55, width, height);
                    if(width < 350) width = 350;
                    frmTest.setBounds(300, 300, width + randkorrektur, height + 55 + fensterleiste);
                    canvas.repaint();
                }
            }
        });
        btnNew.setBackground(Color.WHITE);
        btnNew.setBounds(185, 5, 70, 20);
        frmTest.getContentPane().add(btnNew);
        
        JButton btnNext = new JButton("Next");
        btnNext.setBackground(Color.WHITE);
        btnNext.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                map.nextGeneration();
                canvas.repaint();
            }
        });
        btnNext.setBounds(185, 30, 70, 20);
        frmTest.getContentPane().add(btnNext);
        
        JButton btnStart = new JButton("Start");
        btnStart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if(!started) {
                    started = true;
                    cycleThread = new Thread() {
             
                        public void run() {
                            while(started) {
                                map.nextGeneration();
                                canvas.repaint();
                                try { Thread.sleep(1000 / Integer.parseInt(textFieldSpeed.getText())); }
                                catch(InterruptedException e) {}
                            }
                            started = false;
                        }
         
                    };
                    cycleThread.start();
                }
            }
        });
        btnStart.setBackground(Color.WHITE);
        btnStart.setBounds(260, 5, 70, 20);
        frmTest.getContentPane().add(btnStart);
        
        JButton btnStop = new JButton("Stop");
        btnStop.setBackground(Color.WHITE);
        btnStop.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                started = false;
            }
        });
        btnStop.setBounds(260, 30, 70, 20);
        frmTest.getContentPane().add(btnStop);
        
        JLabel lblWidth = new JLabel("Width");
        lblWidth.setBounds(5, 30, 40, 20);
        frmTest.getContentPane().add(lblWidth);
        
        JLabel lblHeight = new JLabel("Height");
        lblHeight.setBounds(50, 30, 40, 20);
        frmTest.getContentPane().add(lblHeight);
        
        JLabel lblProb = new JLabel("Prob");
        lblProb.setBounds(95, 30, 40, 20);
        frmTest.getContentPane().add(lblProb);
        
        JLabel lblSpeed = new JLabel("Speed");
        lblSpeed.setBounds(140, 30, 40, 20);
        frmTest.getContentPane().add(lblSpeed);
    }
}