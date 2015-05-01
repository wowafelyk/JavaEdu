/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javaedu.threads.synch;

import javax.swing.JTextArea;



   /**

    * This program shows data corruption when multiple threads access a data structure.

    * @version 1.30 2004-08-01

    * @author Cay Horstmann

    */

   public class BankTest

   {

     public static final int NACCOUNTS = 100;

     public static final double INITIAL_BALANCE = 1000;



    public static void main(JTextArea text)

     {

        Bank b = new Bank(NACCOUNTS, INITIAL_BALANCE);

       int i;

        for (i = 0; i < NACCOUNTS; i++)

        {

           TransferRunnable r = new TransferRunnable(b, i, INITIAL_BALANCE, text);

           Thread t = new Thread(r);

           t.start();

        }

     }

  }

