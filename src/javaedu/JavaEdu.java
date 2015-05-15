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

        //String s = "synch"; 
        switch ("synch") {//selecting pacage with examples

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
                System.out.println("sunch");
                if (false) {
                    TextField.main();
                    System.out.println("Case SYNCH");
                    break;
                } else if (true) {
                    try {
                        javaedu.threads.synch.FillListTask.main();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(JavaEdu.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    break;
                }

            case "blockingQueue":
                System.out.println("Case blockingQueue");
                if (false) {
                    javaedu.threads.blockingQueue.BlockingQueueTest.main();
                } else if (true) {
                    javaedu.threads.blockingQueue.BlockingQueueTest2.main();
                }
                break;

            case "futures":
                System.out.println("sunch.collables_and_futures");
                //simple metod with "if-else-if" for selecting example
                if (false) {
                    javaedu.threads.synch.Òallables_and_futures.FutureTest.main();
                } else if (true) {
                    try {
                        javaedu.threads.synch.Òallables_and_futures.ThreadPoolTest.main();
                    } catch (Exception ex) {
                        System.out.println("ÿ‡ÈÚ‡Ì Error");
                        Logger.getLogger(JavaEdu.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;

            case "methods":
                System.out.println("threads.methods");
                javaedu.threads.methods.ForkJoinTest.main();
                break;
        }

    }

}
