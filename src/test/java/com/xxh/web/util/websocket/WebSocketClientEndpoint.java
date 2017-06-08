package com.xxh.web.util.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.*;
import java.io.IOException;
import java.net.URI;

/**
 * Created by wulongtao on 2017/6/7.
 */
@ClientEndpoint
public class WebSocketClientEndpoint {
    private Logger logger = LoggerFactory.getLogger(WebSocketClientEndpoint.class);

    private Session session = null;
    private MessageHandler messageHandler;

    public WebSocketClientEndpoint(URI endpointURI) {
        try {
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            container.connectToServer(this, endpointURI);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public WebSocketClientEndpoint(URI endpointURI, MessageHandler messageHandler) {
        this(endpointURI);
        this.messageHandler = messageHandler;
    }

    @OnOpen
    public void onOpen(Session session) {
        logger.info("on open, session id=["+session.getId()+"]");
        this.session = session;
        if (this.messageHandler != null) {
            this.messageHandler.handleOpen(session);
        }
    }

    @OnClose
    public void onClose(Session session, CloseReason reason) {
        logger.info("on close");
        if (this.messageHandler != null) {
            this.messageHandler.handleClose(session);
        }
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        logger.info("onMessage=["+message+"]");
        if (this.messageHandler != null) {
            this.messageHandler.handleMessage(message, session);
        }
    }

    public void addMessageHandler(MessageHandler messageHandler) {
        if (messageHandler == null) return ;

        this.messageHandler = messageHandler;
    }

    public void sendMessage(String message) {
        this.session.getAsyncRemote().sendText(message);
    }

    public static interface MessageHandler {
        void handleOpen(Session session);
        void handleMessage(String message, Session session);
        void handleClose(Session session);
    }
}
