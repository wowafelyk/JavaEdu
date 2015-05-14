/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaedu.threads.methods;

import java.util.concurrent.*;

/**
 * This program demonstrates the fork-join framework.
 *
 * @author Fenix
 */
public class ForkJoinTest {
    
public static int num=0;

    public static void main() {
        
        
        final int SIZE = 10000000;
        double[] numbers = new double[SIZE];

        for (int i = 0; i < SIZE; i++) {
            numbers[i] = Math.random();         
        }
        Counter counter = new Counter(numbers, 0, numbers.length, new Filter() {
                    public boolean accept(double x) {
                        return x > 0.5;
                    }
                });

        ForkJoinPool pool = new ForkJoinPool();

        pool.invoke(counter);
        System.out.println("tasks = " + ForkJoinTest.num);
        System.out.println(counter.join());
    }
}

interface Filter {

    boolean accept(double t);

}

class Counter extends RecursiveTask<Integer> {

    public static final int THRESHOLD = 10;
    private double[] values;
    private int from;
    private int to;
    private Filter filter;

    public Counter(double[] values, int from, int to, Filter filter) {
        this.values = values;
        this.from = from;
        this.to = to;
        this.filter = filter;
        ForkJoinTest.num++;
    }

    protected Integer compute() {
        if (to - from < THRESHOLD) {
            int count = 0;
            for (int i = from; i < to; i++) {
                if (filter.accept(values[i])) {
                    count++;
                }
            }
            return count;
        } else {
            int mid = (from + to) / 2;
            
            Counter first = new Counter(values, from, mid, filter);
            Counter second = new Counter(values, mid, to, filter);
             invokeAll(first, second);
            return first.join() + second.join();
        }
    }
}
