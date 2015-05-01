/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaedu;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fenix
 */
public class Test_threads extends Thread {
    
    Test_threads()
    {
        
        //Logger.getLogger(Test_threads.class.getName()).log(Level.INFO, "Log befor SetDAEMON");
        boolean b = Thread.currentThread().isAlive();
        System.out.println("Thread working"+b);
        setDaemon(true);
    }
    
    /**
     * @param args the command line arguments
     */
    @Override
    public synchronized void run() {

        try {
            
            Logger.getLogger(Test_threads.class.getName()).log(Level.INFO, "Log befor wait/sleep");
            int stop = 1;
            if (stop == 1) {
                this.sleep(500);
            }
            Logger.getLogger(Test_threads.class.getName()).log(Level.INFO, "Log after 5 sec");
        } catch (InterruptedException e) {
            Logger.getLogger(Test_threads.class.getName()).log(Level.SEVERE, null, e);
        }
    }

}
