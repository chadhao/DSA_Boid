package DSA_Boid;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

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
		JLabel radiusValue = new JLabel("(" + radiusSlider.getValue() + ")", JLabel.CENTER);
                radiusValue.setForeground(Color.BLACK);
                radiusValue.setFont(new Font("", Font.PLAIN, 16));
                radiusPanel.add(radiusLabel);
		radiusPanel.add(radiusValue);
                radiusPanel.add(radiusSlider);
                
                //separation weight panel
                JPanel separationPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
                separationPanel.setPreferredSize(new Dimension(290, 100));
                separationPanel.setBorder(new EmptyBorder(20, 0, 0, 0));
                JLabel separationLabel = new JLabel("separation weight", JLabel.CENTER);
                separationLabel.setFont(new Font("", Font.PLAIN, 16));
                separationLabel.setForeground(Color.BLACK);
		Dictionary<Integer, Component> separationDictionary = new Hashtable<>();
		separationDictionary.put(0, new JLabel("0.0"));
		separationDictionary.put(60, new JLabel("0.6"));
		separationDictionary.put(120, new JLabel("1.2"));
		separationDictionary.put(180, new JLabel("1.8"));
		separationDictionary.put(240, new JLabel("2.4"));
		separationDictionary.put(300, new JLabel("3.0"));
                JSlider separationSlider = new JSlider(0, 300, (int)(Boid.SEPARATION_WEIGHT*100));
                separationSlider.setPreferredSize(new Dimension(290, 50));
                separationSlider.setMajorTickSpacing(60);
                separationSlider.setMinorTickSpacing(20);
                separationSlider.setPaintTicks(true);
                separationSlider.setPaintLabels(true);
		separationSlider.setLabelTable(separationDictionary);
		JLabel separationValue = new JLabel("(" + separationSlider.getValue()*0.01 + ")", JLabel.CENTER);
                separationValue.setForeground(Color.BLACK);
                separationValue.setFont(new Font("", Font.PLAIN, 16));
                separationPanel.add(separationLabel);
		separationPanel.add(separationValue);
                separationPanel.add(separationSlider);
                
                //cohesion weight panel
                JPanel cohesionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
                cohesionPanel.setPreferredSize(new Dimension(290, 100));
                cohesionPanel.setBorder(new EmptyBorder(20, 0, 0, 0));
                JLabel cohesionLabel = new JLabel("cohesion weight", JLabel.CENTER);
                cohesionLabel.setFont(new Font("", Font.PLAIN, 16));
                cohesionLabel.setForeground(Color.BLACK);
		Dictionary<Integer, Component> cohesionDictionary = new Hashtable<>();
		cohesionDictionary.put(0, new JLabel("0.0"));
		cohesionDictionary.put(2, new JLabel("0.02"));
		cohesionDictionary.put(4, new JLabel("0.04"));
		cohesionDictionary.put(6, new JLabel("0.06"));
		cohesionDictionary.put(8, new JLabel("0.08"));
		cohesionDictionary.put(10, new JLabel("0.1"));
                JSlider cohesionSlider = new JSlider(0, 10, (int)(Boid.COHESION_WEIGHT*100));
                cohesionSlider.setPreferredSize(new Dimension(290, 50));
                cohesionSlider.setMajorTickSpacing(2);
                cohesionSlider.setMinorTickSpacing(1);
                cohesionSlider.setPaintTicks(true);
                cohesionSlider.setPaintLabels(true);
		cohesionSlider.setSnapToTicks(true);
		cohesionSlider.setLabelTable(cohesionDictionary);
		JLabel cohesionValue = new JLabel("(" + cohesionSlider.getValue()*0.01 + ")", JLabel.CENTER);
                cohesionValue.setForeground(Color.BLACK);
                cohesionValue.setFont(new Font("", Font.PLAIN, 16));
                cohesionPanel.add(cohesionLabel);
		cohesionPanel.add(cohesionValue);
                cohesionPanel.add(cohesionSlider);
                
                //alignment weight panel
                JPanel alignmentPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
                alignmentPanel.setPreferredSize(new Dimension(290, 100));
                alignmentPanel.setBorder(new EmptyBorder(20, 0, 0, 0));
                JLabel alignmentLabel = new JLabel("alignment weight", JLabel.CENTER);
                alignmentLabel.setFont(new Font("", Font.PLAIN, 16));
                alignmentLabel.setForeground(Color.BLACK);
		Dictionary<Integer, Component> alignmentDictionary = new Hashtable<>();
		alignmentDictionary.put(0, new JLabel("0.0"));
		alignmentDictionary.put(2, new JLabel("0.02"));
		alignmentDictionary.put(4, new JLabel("0.04"));
		alignmentDictionary.put(6, new JLabel("0.06"));
		alignmentDictionary.put(8, new JLabel("0.08"));
		alignmentDictionary.put(10, new JLabel("0.1"));
                JSlider alignmentSlider = new JSlider(0, 10, (int)(Boid.ALIGNMENT_WEIGHT*100));
                alignmentSlider.setPreferredSize(new Dimension(290, 50));
                alignmentSlider.setMajorTickSpacing(2);
                alignmentSlider.setMinorTickSpacing(1);
                alignmentSlider.setPaintTicks(true);
                alignmentSlider.setPaintLabels(true);
		alignmentSlider.setSnapToTicks(true);
		alignmentSlider.setLabelTable(alignmentDictionary);
		JLabel alignmentValue = new JLabel("(" + alignmentSlider.getValue()*0.01 + ")", JLabel.CENTER);
                alignmentValue.setForeground(Color.BLACK);
                alignmentValue.setFont(new Font("", Font.PLAIN, 16));
                alignmentPanel.add(alignmentLabel);
		alignmentPanel.add(alignmentValue);
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
                                        contentPanel.getGraphics().setColor(Color.BLACK);
					contentPanel.getGraphics().fillRect(0, 0, contentPanel.getWidth(), contentPanel.getHeight());
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
		
		radiusSlider.addChangeListener(new ChangeListener() {
		    @Override
		    public void stateChanged(ChangeEvent e) {
			BoidFlock.DETECTRADIUS = radiusSlider.getValue();
			radiusValue.setText("(" + BoidFlock.DETECTRADIUS + ")");
			radiusValue.repaint();
		    }
		});
		
		separationSlider.addChangeListener(new ChangeListener() {
		    @Override
		    public void stateChanged(ChangeEvent e) {
			Boid.SEPARATION_WEIGHT = (float)(separationSlider.getValue()/100.0);
			separationValue.setText("(" + Boid.SEPARATION_WEIGHT + ")");
			separationValue.repaint();
		    }
		});
		
		cohesionSlider.addChangeListener(new ChangeListener() {
		    @Override
		    public void stateChanged(ChangeEvent e) {
			Boid.COHESION_WEIGHT = (float)(cohesionSlider.getValue()/100.0);
			cohesionValue.setText("(" + Boid.COHESION_WEIGHT + ")");
			cohesionValue.repaint();
		    }
		});
		
		alignmentSlider.addChangeListener(new ChangeListener() {
		    @Override
		    public void stateChanged(ChangeEvent e) {
			Boid.ALIGNMENT_WEIGHT = (float)(alignmentSlider.getValue()/100.0);
			alignmentValue.setText("(" + Boid.ALIGNMENT_WEIGHT + ")");
			alignmentValue.repaint();
		    }
		});
                
            }
        });
    }
    
}
