package com.xxh.web.util.websocket;

import javax.websocket.ClientEndpointConfig;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by wulongtao on 2017/6/13.
 */
public class ClientConfigurator extends ClientEndpointConfig.Configurator {

    @Override
    public void beforeRequest(Map<String, List<String>> headers) {
        List<String> values = new ArrayList<>();
        values.add(UUID.randomUUID().toString());
        headers.put("X-My-Custom-Header", values);
    }
}
