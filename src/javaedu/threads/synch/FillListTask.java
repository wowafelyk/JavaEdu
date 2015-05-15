/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javaedu.threads.synch;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fenix
 */

public class FillListTask implements Runnable {
private final int size;
private List<String> strings;
public FillListTask(int size) {
this.size = size;
}
public synchronized boolean isFinished() {
    Logger.getLogger(FillListTask.class.getName()).log(Level.INFO , "FillListTask.isFinished()");
return null != strings;
}
public synchronized List<String> getList() {
    Logger.getLogger(FillListTask.class.getName()).log(Level.INFO , "FillListTask.getList()");
return strings;
}
@Override
public void run() {
List<String> strs = new ArrayList<String>(size);
try {
for (int i = 0; i < size; i++ ) {
Thread.sleep(2000);
Logger.getLogger(FillListTask.class.getName()).log(Level.INFO , "FillListTask.run()");
strs.add("element " + String.valueOf(i));
}
synchronized (this) {
strings = strs;
this.notifyAll();
}
}
catch (InterruptedException e) {
// Перехват прерванного исключения вне цикла,
// поскольку прерванное исключение является сигналом того,
// что поток должен завершить работу.
}
}
/**
* Ожидает, пока задача заполнения списка будет завершена.
*/
public static void main()
throws InterruptedException
{
FillListTask task = new FillListTask(7);
Thread t = new Thread(task);
t.start();
// задачи и приостанавливает работу потока до тех пор,
// пока он не получит уведомления.

synchronized (task) {
while (!task.isFinished()) {
    Logger.getLogger(FillListTask.class.getName()).log(Level.INFO , "FillListTask.main working");
    t.wait();
    Logger.getLogger(FillListTask.class.getName()).log(Level.INFO, "FillListTask.main working after wait");
}
}
System.out.println("Array full: " + task.getList());
}
}