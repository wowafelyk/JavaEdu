/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaedu;

import java.util.logging.Level;
import java.util.logging.Logger;
import javaedu.threads.bounce.Bounce;
import javaedu.threads.synch.*;

/**
 *
 * @author Fenix
 */
public class JavaEdu {

    /**
     * @param args the command line arguments
     */
    @SuppressWarnings("empty-statement")
    public static void main(String[] args) {
        
        String s = "synch";
        switch (s) {
            case "bounce":
                Logger.getLogger(BankTest.class.getName()).log(Level.INFO, "Haha it's working");
                /**
                 * Starting new class in separate thread
                 */
                Thread t = new Thread(new Test_threads(), "MyTread");
                t.start();
                //t.interrupt();
                System.out.println("Is interrupted = " + t.isInterrupted());

                Bounce.main();
                break;
            case "synch":
                TextField.main();
        }
    }

}
