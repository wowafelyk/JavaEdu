/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaedu.threads.synch;

import javax.swing.JTextArea;
import java.util.concurrent.locks.*;

/**
 *
 * A bank with a number of bank accounts that uses locks for serializing access.
 *
 * @version 1.30 2004-08-01
 *
 * @author Cay Horstmann
 *
 */
public class Bank {

    private final double[] accounts;

    private Lock bankLock;

    private Condition sufficientFunds;

    /**
     *
     * Constructs the bank.
     *
     * @param n the number of accounts
     *
     * @param initialBalance the initial balance for each account
     */
    public Bank(int n, double initialBalance) {

        accounts = new double[n];

        for (int i = 0; i < accounts.length; i++) {
            accounts[i] = initialBalance;
        }

        bankLock = new ReentrantLock();

        sufficientFunds = bankLock.newCondition();

    }

    /**
     *
     * Transfers money from one account to another.
     *
     * @param from the account to transfer from
     *
     * @param to the account to transfer to
     *
     * @param amount the amount to transfer
     *
     */
    public void transfer(int from, int to, double amount, JTextArea text) throws InterruptedException {

        bankLock.lock();

        try {

            while (accounts[from] < amount) {
                sufficientFunds.await();
            }
            StringBuilder s = new StringBuilder();
            s.append(Thread.currentThread());

            accounts[from] -= amount;

            s.append(" " + amount + " from " + from + " to " + to);

            accounts[to] += amount;

            s.append(" Total Balance: " + getTotalBalance() + "\n\r");
            text.append(s.toString());
            sufficientFunds.signalAll();

        } finally {

            bankLock.unlock();

        }

    }

    /**
     *
     * Gets the sum of all account balances.
     *
     * @return the total balance
     *
     */
    public double getTotalBalance() {

        bankLock.lock();

        try {

            double sum = 0;

            for (double a : accounts) {
                sum += a;
            }

            return sum;

        } finally {

            bankLock.unlock();

        }

    }

    /**
     *
     * Gets the number of accounts in the bank.
     *
     * @return the number of accounts
     *
     */
    public int size() {

        return accounts.length;

    }

}
