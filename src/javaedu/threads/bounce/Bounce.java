/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaedu.threads.bounce;

/**
 *
 * @author Fenix
 */
import java.awt.*;
import java.awt.event.*;
import static javaedu.threads.bounce.BallRunnable.DELAY;
import javax.swing.*;

/**
 * Shows an animated bouncing ball.
 *
 * @version 1.33 2007-05-17
 * @author Cay Horstmann
 */
public class Bounce {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new BounceFrame();
                System.err.println("grrsrgg");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

/**
 * The frame with ball component and buttons.
 */
class BallRunnable implements Runnable {

    private Ball ball;
    private Component component;
    public static final int STEPS = 5000;
    public static final int DELAY = 5;

    /**
     * Constructs the runnable.
     *
     * @param aBall the ball to bounce
     * @param aComponent the component in which the ball bounces
     */
    public BallRunnable(Ball aBall, Component aComponent) {
        ball = aBall;
        component = aComponent;
    }

    public void run() {
        try {
            for (int i = 1; i <= STEPS; i++) {
                ball.move(component.getBounds());
                component.repaint();
                Thread.sleep(DELAY);
            }
        } catch (InterruptedException e) {
            System.err.println("Error jopta");
        }
    }

}

class BounceFrame extends JFrame {

    private BallComponent comp;
    //public static final int STEPS = 1000;
    //public static final int DELAY = 3;
    public static final int WAIT = 10;

    /**
     *
     * Constructs the frame with the component for showing the bouncing ball and
     * Start and Close
     *
     * buttons
     *
     */
    public BounceFrame() {
        setTitle("Bounce");

        comp = new BallComponent();
        add(comp, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        addButton(buttonPanel, "Start", new ActionListener() {

            public void actionPerformed(ActionEvent event) {
                
                new Thread(new Runnable(){
                   
                    public void run(){
                      try {
                    for (int i = 0; i < 1; i++) {
                        Thread.sleep(WAIT);
                        addBall();
                    }
                } catch (InterruptedException e) {
                    System.err.println("shajtan error");
                }  
                    }
                    
                }).start();

                

            }
        });
        addButton(buttonPanel, "Close", new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });

        add(buttonPanel, BorderLayout.SOUTH);

        pack();

    }

    /**
     * Adds a button to a container.
     *
     * @param c the container
     * @param title the button title
     * @param listener the action listener for the button
     */
    public void addButton(Container c, String title, ActionListener listener) {

        JButton button = new JButton(title);
        c.add(button);
        button.addActionListener(listener);

    }

    /**
     * Adds a bouncing ball to the panel and makes it bounce 1,000 times.
     */
    public void addBall() {

        Ball b = new Ball();
        comp.add(b);
        /*for (int i = 1; i <= STEPS; i++) {
         ball.move(comp.getBounds());
         comp.paint(comp.getGraphics());
         //comp.paintComponent(comp.getGraphics());
         Thread.sleep(DELAY);
         } */
        Runnable r = new BallRunnable(b, comp);
        Thread t = new Thread(r);
        t.start();

    }

}
