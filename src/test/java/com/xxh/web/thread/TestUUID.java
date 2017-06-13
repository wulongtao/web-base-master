package com.xxh.web.thread;

import java.util.Random;
import java.util.UUID;

/**
 * Created by wulongtao on 2017/6/13.
 */
public class TestUUID {
    public static void main(String[] args) {
        for(int i=0;i<5;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(generateSequenceID());
                }
            }).start();
        }
    }

    public static String generateSequenceID(){
        String uuid = UUID.randomUUID().toString();
        String ranEight = String.format("%08d", new Random().nextInt(99999999));
        return uuid +"--"+ ranEight;
    }
}
