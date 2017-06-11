package com.xxh.web.util.robot;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

/**
 * Created by raid on 2017/6/2.
 */
public class MouseScript {
    private static Dimension dimension;
    private static Robot robot;
    private volatile boolean stop = false;

    static {
        dimension = Toolkit.getDefaultToolkit().getScreenSize();
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                long time = System.currentTimeMillis();
                while (true) {
                    robot.delay(1000);
//                    robot.mouseMove(800, 450);
                    robot.mouseMove(1100, 650);
                    robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                    robot.delay(2000);
                    robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                    robot.delay(2000);
//                    robot.mouseMove(800, 450);
                    robot.mouseMove(1100, 650);
                    robot.keyPress(KeyEvent.VK_F1);
                    robot.keyRelease(KeyEvent.VK_F1);
                    robot.delay(2000);
                    robot.keyPress(KeyEvent.VK_F1);
                    robot.keyRelease(KeyEvent.VK_F1);
//                    robot.delay(1000);
                    System.out.println(System.currentTimeMillis() - time);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();

//        robot.mouseMove(1150, 600);
//        robot.mouseMove(800, 450);
    }
}
