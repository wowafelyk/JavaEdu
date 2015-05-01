/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaedu.synch;

import javax.swing.JTextArea;

/**
 *
 * A runnable that transfers money from an account to other accounts in a bank.
 *
 * @version 1.30 2004-08-01
 *
 * @author Cay Horstmann
 *
 */
public class TransferRunnable implements Runnable {

    private Bank bank;
    private int fromAccount;
    private double maxAmount;
    private int DELAY = 10;
    private JTextArea text;
    /**
     *
     * Constructs a transfer runnable.
     *
     * @param b the bank between whose account money is transferred
     *
     * @param from the account to transfer money from
     *
     * @param max the maximum amount of money in each transfer
     *
     */
    public TransferRunnable(Bank b, int from, double max, JTextArea text) {

        bank = b;
        fromAccount = from;
        maxAmount = max;
        this.text=text;
        

    }

    public void run() {

        try {

            while (TextField.stop) {

                int toAccount = (int) (bank.size() * Math.random());

                double amount = maxAmount * Math.random();

                bank.transfer(fromAccount, toAccount, amount, text);

                Thread.sleep((int) (DELAY * Math.random()));

            }

        } catch (InterruptedException e) {

        }

    }

}
