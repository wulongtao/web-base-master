package com.xxh.web.util.websocket;

import org.junit.Test;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wulongtao on 2017/6/7.
 */
public class WebSocketPerformanceTest {
    private static Map<Integer, WebSocketClientEndpoint> mThread = new HashMap<>();

    @Test
    public void websocketPerformanceTest() throws Exception {
        for (int i = 0; i < 1000; ) {
            int inc = (int)(Math.random()*10);
            for (int j = 0; j < inc; j++) {
                mThread.put(i+j, new WebSocketClientEndpoint(new URI("ws://localhost:20176/chat"), new WebSocketMessageHandler()));
            }

            Thread.sleep(10000);
            i += inc;
        }


    }
}
