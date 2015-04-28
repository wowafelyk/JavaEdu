/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javaedu;

import java.util.logging.Level;
import java.util.logging.Logger;
import javaedu.threads.bounce.Bounce;

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
        Logger.getLogger(Test_threads.class.getName()).log(Level.INFO,"Haha it's working");
        /**
         * Starting new class in separate thread
         */
        Thread t = new Thread(new Test_threads(),"MyTread");
        t.start();
        System .out.println("Is interrupted = "+t.isInterrupted());
        t.interrupt();
        String [] arr={"Start","project"};
        Bounce.main(arr);
    }
    
}
