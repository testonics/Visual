package utils;

import java.awt.*;

public class MouseMover {
    public static final int SLEEP_MILLIS = 60*1000;

    public static void main(String... args) throws Exception {
        Robot robot = new Robot();
        while (true) {
            Point point = MouseInfo.getPointerInfo().getLocation();
            robot.mouseMove(point.x, point.y);
            System.out.println("Mouse Moved!!");
            Thread.sleep(SLEEP_MILLIS);
        }
    }
}