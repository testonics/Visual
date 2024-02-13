package in.testonics.omni.utils.MultiThread;

public class MultiThreadingWithThread extends Thread {

    public void run() {
        try {
            // Displaying the thread that is running
            System.out.println("Thread " + Thread.currentThread().getId() + " is running");
        } catch (Exception e) {
            // Throwing an exception
            System.out.println("Exception is caught");
        }
    }

    /*
    Multithreading is a Java feature that allows concurrent execution of two or more parts of a program for maximum utilization of CPU. Each part of such program is called a thread. So, threads are light-weight processes within a process.

    Threads can be created by using two mechanisms :
    1. Extending the Thread class
    2. Implementing the Runnable Interface

    Thread Class vs Runnable Interface
    1. If we extend the Thread class, our class cannot extend any other class because Java doesn’t support multiple inheritance. But, if we implement the Runnable interface, our class can still extend other base classes.
    2.We can achieve basic functionality of a thread by extending Thread class because it provides some inbuilt methods like yield(), interrupt() etc. that are not available in Runnable interface.
    3.Using runnable will give you an object that can be shared amongst multiple threads.
     */
}
