/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaedu.threads.synch.ñallables_and_futures;

/**
 *
 * @author Fenix
 */
import java.io.*;
import java.util.*;
import java.util.concurrent.*;

/**
 * @version 1.01 2012-01-26
 * @author Cay Horstmann
 */
public class ThreadPoolTest {

    /**
     * Variable is changing every time when new scanning cycle starting There
     * are only fixed number of threads
     */
    private static int num = 0;

    public synchronized static int getNum() {
        return ThreadPoolTest.num;
    }

    public synchronized static void setNum() {
        ThreadPoolTest.num++;
    }

    public static void main() throws Exception {

        Scanner in = new Scanner(System.in);
        System.out.print("Enter base directory (e.g. /usr/local/jdk5.0/src): ");
        String directory = "E:/Android/sdk/tools/"; //in.nextLine();
        System.out.print("Enter keyword (e.g. volatile): ");
        String keyword = "a";//in.nextLine();
        ExecutorService pool = Executors.newFixedThreadPool(5);
        MatchCounterPool counter = new MatchCounterPool(new File(directory), keyword, pool);
        Future<Integer> result = pool.submit(counter);
        //Thread.sleep(3000);

        try {
            System.out.println(result.get() + " matching files.");
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        pool.shutdown();

        int largestPoolSize = ((ThreadPoolExecutor) pool).getLargestPoolSize();

        System.out.println("largest pool size = " + largestPoolSize);
        System.out.println("Threads = " + getNum());

    }

}

/**
 * This task counts the files in a directory and its subdirectories that contain
 * a given keyword.
 */
class MatchCounterPool implements Callable<Integer> {

    private File directory;
    private String keyword;
    private ExecutorService pool;
    private int count;

    /**
     * Constructs a MatchCounterPool.
     *
     * @param directory the directory in which to start the search
     * @param keyword the keyword to look for
     * @param pool the thread pool for submitting subtasks
     */
    public MatchCounterPool(File directory, String keyword, ExecutorService pool) {
        this.directory = directory;
        this.keyword = keyword;
        this.pool = pool;
        ThreadPoolTest.setNum();
    }

    /**
     * Changing metod Now metod will create new pool for every folder
     *
     * @return
     */
    public Integer call() {
        count = 0;
        try {
            File[] files = directory.listFiles();
            List<Future<Integer>> results = new ArrayList<>();
            ExecutorService pool_New = Executors.newFixedThreadPool(20);

            for (File file : files) {
                if (file.isDirectory()) {

                    MatchCounterPool counter = new MatchCounterPool(file, keyword, pool_New);
                    Future<Integer> result = pool_New.submit(counter);

                    //MatchCounterPool counter = new MatchCounterPool(file, keyword, pool);
                    //Future<Integer> result = pool.submit(counter);
                    results.add(result);

                } else {
                    if (search(file)) {
                        count++;
                    }
                }
            }
            
            pool_New.shutdown();
            
            for (Future<Integer> result : results) {
                try {
                    count += result.get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        } catch (InterruptedException e) {
            System.out.println("Error in serching thread");
        }
        return count;
    }

    /**
     * Searches a file for a given keyword.
     *
     * @param file the file to search
     * @return true if the keyword is contained in the file
     */
    public boolean search(File file) {
        try {
            try (Scanner in = new Scanner(file)) {
                boolean found = false;
                while (!found && in.hasNextLine()) {
                    String line = in.nextLine();
                    if (line.contains(keyword)) {
                        found = true;
                    }
                }
                return found;
            }
        } catch (IOException e) {
            System.out.println("Serch IO_Esception");
            return false;
        }

    }
}
