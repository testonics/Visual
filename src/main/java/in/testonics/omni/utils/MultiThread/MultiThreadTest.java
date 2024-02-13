package in.testonics.omni.utils.MultiThread;

public class MultiThreadTest {

//    //Using Thread Class
//    public static void main(String[] args) {
//        int n = 8; // Number of threads
//        for (int i = 0; i < n; i++) {
//            MultiThreadingWithThread multiThreadingWithThread = new MultiThreadingWithThread();
//            multiThreadingWithThread.start();
//        }
//    }


    //Using Runnable Interface
    public static void main(String[] args) {
        int n = 1; // Number of threads
        for (int i = 0; i < n; i++) {
            Thread thread = new Thread(new MultiThreadingWithRunnable());
            thread.start();
        }
    }

}
