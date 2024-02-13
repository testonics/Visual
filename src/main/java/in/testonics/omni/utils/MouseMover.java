package in.testonics.omni.utils;

import java.awt.*;

public class MouseMover {

    public static void keepAliveByMouseMove() throws Exception {
        System.out.println("Keep Alive utility will stop after 10 seconds by default. Pass the timeoutInSeconds parameter to customize");
        keepAliveByMouseMove(10);
    }

    public static void keepAliveByMouseMove(int timeoutInSeconds) throws Exception {
        System.out.println("Mouse Will be moved every 5 seconds. Pass moveIntervalInSeconds to parameter to customize");
        keepAliveByMouseMove(timeoutInSeconds,5);
    }

    public static void keepAliveByMouseMove(int timeoutInSeconds, int moveIntervalInSeconds) throws Exception {
        long sleepTime = moveIntervalInSeconds* 1000L;
        Robot robot = new Robot();
        long currentTimeInMillis = System.currentTimeMillis();
        long timeout = currentTimeInMillis + (timeoutInSeconds * 1000L);
        while (System.currentTimeMillis() < timeout) {
            Point point = MouseInfo.getPointerInfo().getLocation();
            robot.mouseMove(point.x, point.y);
            System.out.println("Mouse Moved!!");
            Thread.sleep(sleepTime);
        }
    }
}