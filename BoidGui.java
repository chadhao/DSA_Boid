package DSA_Boid;

import com.sun.xml.internal.fastinfoset.EncodingConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gpz1505
 */
public class BoidGui {
    
    private static BoidFlock BF;
    private static Timer TT;
    
    public static void centreWindow(Window frame) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
    }
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame mainFrame = new JFrame("Boid GUI");
                mainFrame.setLocationRelativeTo(null);
                mainFrame.setResizable(false);
                mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
                JPanel mainPanel = new JPanel();
                mainPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
                
                JPanel contentPanel = new JPanel();
                contentPanel.setPreferredSize(new Dimension(800, 800)); 
                contentPanel.setBackground(Color.BLACK);
                
                JPanel controlPanel = new JPanel();
                controlPanel.setPreferredSize(new Dimension(300, 800));
                controlPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
                
                //title panel
                JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
                titlePanel.setPreferredSize(new Dimension(290, 80));
                JLabel titleLabel = new JLabel("Control Panel", JLabel.CENTER);
                titleLabel.setFont(new Font("", Font.BOLD, 24));
                titleLabel.setForeground(Color.BLACK);
                titlePanel.add(titleLabel);
                
                //start panel
                JPanel startPanel = new JPanel();
                startPanel.setPreferredSize(new Dimension(300, 50));
                startPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
                JLabel startLabel = new JLabel("Number of boids to start:");
                startLabel.setFont(new Font("", Font.PLAIN, 16));
                startLabel.setForeground(Color.BLACK);
                JTextField startField = new JTextField();
                startField.setPreferredSize(new Dimension(290, 26));
                startField.setHorizontalAlignment(JTextField.LEFT);
                startField.setFont(new Font("", Font.PLAIN, 16));
                startPanel.add(startLabel);
                startPanel.add(startField);
                
                //button panel
                JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                buttonPanel.setPreferredSize(new Dimension(290, 80));
                buttonPanel.setLayout(new GridLayout(2, 2, 10, 10));
                JButton startButton = new JButton("start");
                JButton clearButton = new JButton("clear");
                clearButton.setEnabled(false);
                JButton addButton = new JButton("add");
                addButton.setEnabled(false);
                JButton removeButton = new JButton("remove");
                removeButton.setEnabled(false);
                buttonPanel.add(startButton);
                buttonPanel.add(clearButton);
                buttonPanel.add(addButton);
                buttonPanel.add(removeButton);
                
                //radius detection panel
                JPanel radiusPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
                radiusPanel.setPreferredSize(new Dimension(290, 100));
                radiusPanel.setBorder(new EmptyBorder(20, 0, 0, 0));
                JLabel radiusLabel = new JLabel("radius detection", JLabel.CENTER);
                radiusLabel.setFont(new Font("", Font.PLAIN, 16));
                radiusLabel.setForeground(Color.BLACK);
                JSlider radiusSlider = new JSlider(0, 200, BoidFlock.DETECTRADIUS);
                radiusSlider.setPreferredSize(new Dimension(290, 50));
                radiusSlider.setMajorTickSpacing(50);
                radiusSlider.setMinorTickSpacing(10);
                radiusSlider.setPaintTicks(true);
                radiusSlider.setPaintLabels(true);
                radiusPanel.add(radiusLabel);
                radiusPanel.add(radiusSlider);
                
                //separation weight panel
                JPanel separationPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
                separationPanel.setPreferredSize(new Dimension(290, 100));
                separationPanel.setBorder(new EmptyBorder(20, 0, 0, 0));
                JLabel separationLabel = new JLabel("separation weight", JLabel.CENTER);
                separationLabel.setFont(new Font("", Font.PLAIN, 16));
                separationLabel.setForeground(Color.BLACK);
                JSlider separationSlider = new JSlider(0, 200, BoidFlock.DETECTRADIUS);
                separationSlider.setPreferredSize(new Dimension(290, 50));
                separationSlider.setMajorTickSpacing(50);
                separationSlider.setMinorTickSpacing(10);
                separationSlider.setPaintTicks(true);
                separationSlider.setPaintLabels(true);
                separationPanel.add(separationLabel);
                separationPanel.add(separationSlider);
                
                //cohesion weight panel
                JPanel cohesionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
                cohesionPanel.setPreferredSize(new Dimension(290, 100));
                cohesionPanel.setBorder(new EmptyBorder(20, 0, 0, 0));
                JLabel cohesionLabel = new JLabel("cohesion weight", JLabel.CENTER);
                cohesionLabel.setFont(new Font("", Font.PLAIN, 16));
                cohesionLabel.setForeground(Color.BLACK);
                JSlider cohesionSlider = new JSlider(0, 200, BoidFlock.DETECTRADIUS);
                cohesionSlider.setPreferredSize(new Dimension(290, 50));
                cohesionSlider.setMajorTickSpacing(50);
                cohesionSlider.setMinorTickSpacing(10);
                cohesionSlider.setPaintTicks(true);
                cohesionSlider.setPaintLabels(true);
                cohesionPanel.add(cohesionLabel);
                cohesionPanel.add(cohesionSlider);
                
                //alignment weight panel
                JPanel alignmentPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
                alignmentPanel.setPreferredSize(new Dimension(290, 100));
                alignmentPanel.setBorder(new EmptyBorder(20, 0, 0, 0));
                JLabel alignmentLabel = new JLabel("alignment weight", JLabel.CENTER);
                alignmentLabel.setFont(new Font("", Font.PLAIN, 16));
                alignmentLabel.setForeground(Color.BLACK);
                JSlider alignmentSlider = new JSlider(0, 200, BoidFlock.DETECTRADIUS);
                alignmentSlider.setPreferredSize(new Dimension(290, 50));
                alignmentSlider.setMajorTickSpacing(50);
                alignmentSlider.setMinorTickSpacing(10);
                alignmentSlider.setPaintTicks(true);
                alignmentSlider.setPaintLabels(true);
                alignmentPanel.add(alignmentLabel);
                alignmentPanel.add(alignmentSlider);
                
                controlPanel.add(titlePanel);
                controlPanel.add(startPanel);
                controlPanel.add(buttonPanel);
                controlPanel.add(radiusPanel);
                controlPanel.add(separationPanel);
                controlPanel.add(cohesionPanel);
                controlPanel.add(alignmentPanel);
                
                mainPanel.add(contentPanel);
                mainPanel.add(controlPanel);
                
                mainFrame.add(mainPanel);
                mainFrame.pack();
                centreWindow(mainFrame);
                mainFrame.setVisible(true);
                
                startField.addKeyListener(new KeyAdapter()
                {
                    @Override
                    public void keyTyped(KeyEvent e)
                    {
                        int keyChar = e.getKeyChar();
                        if (keyChar < KeyEvent.VK_0 || keyChar > KeyEvent.VK_9)
                        {
                            e.consume();
                        }
                    }
                });
                
                startButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int number = !startField.getText().equals("")?Integer.parseInt(startField.getText()):0;
                        if ( number == 0 ) {
                            startField.setText("");
                            JOptionPane.showMessageDialog(null, "Number of boids to start cannot be zero!", "Information", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            startButton.setEnabled(false);
                            clearButton.setEnabled(true);
                            addButton.setEnabled(true);
                            removeButton.setEnabled(true);
                            BF = new BoidFlock(number, contentPanel.getWidth(), contentPanel.getHeight());
                            ActionListener task = new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    try {
                                        
                                        BF.drawAllBoids(contentPanel.getGraphics());
                                    } catch (InterruptedException ex) {
                                        Logger.getLogger(BoidGui.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                            };
                            TT = new Timer(33, task);
                            TT.start();
                        }
                    }
                });
                
                clearButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        startField.setText("");
                        startButton.setEnabled(true);
                        clearButton.setEnabled(false);
                        addButton.setEnabled(false);
                        removeButton.setEnabled(false);
                        TT.stop();
                        BF.destroyAllBoids();
                        contentPanel.repaint();
                    }
                });
                
                addButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        BF.addBoidToFlock();
                    }
                });
                
                removeButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        BF.removeBoidFromFlock();
                    }
                });
                
            }
        });
    }
    
}
