package com.xxh.web.thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 测试synchronize关键字同步的问题
 * Created by wulongtao on 2017/6/9.
 */

class Sync {
    protected final static ReentrantLock lock = new ReentrantLock();

    public void test() {
        /*try {
            lock.lockInterruptibly();
            System.out.println("test 开始...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("test 结束...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }*/

        synchronized (Sync.class) {
            System.out.println("test 开始...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("test 结束...");
        }

    }

}


public class SyncTest1 {

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Sync sync = new Sync();
                    sync.test();
                }
            }).start();
        }
    }

}
