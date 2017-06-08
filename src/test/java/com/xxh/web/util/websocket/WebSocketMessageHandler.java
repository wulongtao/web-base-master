package com.xxh.web.util.websocket;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.Session;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wulongtao on 2017/6/7.
 */
public class WebSocketMessageHandler implements WebSocketClientEndpoint.MessageHandler {
    private Logger logger = LoggerFactory.getLogger(WebSocketMessageHandler.class);

    public static Map<String, Session> mUser = new HashMap<>();
    public static Map<String, String> mSid = new HashMap<>();
    public static Map<String, List<String>> mUserRoom = new HashMap<>();
    public static List<String> lstServerUser = new ArrayList<>();
    public static List<String> lstClientUser = new ArrayList<>();
    public static Map<String, Integer> mUserOnlineStatus = new HashMap<>();

    public static int curUserId = 10;

    static {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    long sleepTime = (long)(Math.random()*10000) + 10000;
                    try {
                        Thread.sleep(sleepTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    //随机发生事件：（1）会话转移（2）改变在线状态（3）退出登录
                    int randomEvent = (int)(Math.random()*4) + 1;
                    if (randomEvent == 1) {
                        if (lstServerUser.size() == 0) continue;
                        //随机一个用户
                        String userId = lstServerUser.get((int)(Math.random()*lstServerUser.size()));
                        if (mUser.get(userId) == null) continue;
                        String toUserId = lstServerUser.get((int)(Math.random()*lstServerUser.size()));
                        if (userId.equals(toUserId) || mUser.get(toUserId)==null) continue;
                        if (mUserRoom.get(userId) == null) continue;

                        String roomId = mUserRoom.get(userId).get((int)(Math.random()*mUserRoom.get(userId).size()));
                        //发送会话转移
                        Message request = new Message();
                        request.setType(ChatConstants.TYPE_SERVICVE_INTERCHANGE);
                        request.setUserId(userId);
                        request.setToUserId(toUserId);
                        request.setRoomId(roomId);
                        sendMessage(mUser.get(userId), request);
                    } else if (randomEvent == 2) {
                        String userId = lstServerUser.get((int)(Math.random()*lstServerUser.size()));
                        if (mUser.get(userId) == null) continue;
                        Integer oldStatus = mUserOnlineStatus.get(userId);
                        if (oldStatus == null) continue;

                        int newStatus = (int)(Math.random()*3) + 1;
                        while (oldStatus == newStatus) {
                            newStatus = (int)(Math.random()*3) + 1;
                        }

                        Message request = new Message();
                        request.setType(ChatConstants.TYPE_CHANGE_ONLINE_STATUS);
                        request.setUserId(userId);
                        request.setExtraData(oldStatus+"");
                        request.setOnlineStatus(newStatus);
                        sendMessage(mUser.get(userId), request);
                    } else if (randomEvent == 3) {
                        String userId = lstServerUser.get((int)(Math.random()*lstServerUser.size()));
                        if (mUser.get(userId) == null) continue;

                        try {
                            mUser.get(userId).close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        }).start();

    }

    @Override
    public void handleOpen(Session session) {
        logger.info("websocket message handle open");
    }

    @Override
    public void handleMessage(String message, Session session) {
        logger.info("websocket message handle message, session=["+session+"]");

        if (message == null || message.equals("") || session == null) return ;

        Message msg = new Gson().fromJson(message, Message.class);

        int type = msg.getType();
        Message response = null;
        switch (type) {
            case ChatConstants.TYPE_SERVER_NOTICE:
                handleServerNotice(msg, session);
                break;

            case ChatConstants.TYPE_NEED_SERVICE_USER:
                //客服收到的消息，是否需要服务客户，这里按（拒绝）1:9（同意）的比例来发送请求
                int randomAggree = (int)(Math.random()*10)+1;
                response = new Message();
                response.setType(ChatConstants.TYPE_WANT_SERVICE_USER);
                response.setToUserId(msg.getToUserId());
                response.setUserId(msg.getUserId());
                if (randomAggree == 1) { //拒绝
                    logger.info("拒绝服务");
                    response.setExtraData(msg.getExtraData());
                } else { //同意
                    logger.info("同意服务");
                    response.setExtraData("0");
                }
                sendMessage(session, response);
                break;

            case ChatConstants.TYPE_ATTRIBUTE_USER: //同意后建立会话
                response = new Message();
                response.setType(ChatConstants.TYPE_ATTRIBUTE_USER_CHAT);
                response.setRoomId(msg.getRoomId());
                response.setUserId(msg.getUserId());
                response.setToUserId(msg.getToUserId());
                sendMessage(session, response);

                if (mUserRoom.get(msg.getUserId()) == null) {
                    mUserRoom.put(msg.getUserId(), new ArrayList<>());
                }
                if (mUserRoom.get(msg.getToUserId()) == null) {
                    mUserRoom.put(msg.getToUserId(), new ArrayList<>());
                }
                if (!mUserRoom.get(msg.getUserId()).contains(msg.getRoomId())) {
                    mUserRoom.get(msg.getUserId()).add(msg.getRoomId());
                }
                if (!mUserRoom.get(msg.getToUserId()).contains(msg.getRoomId())) {
                    mUserRoom.get(msg.getToUserId()).add(msg.getRoomId());
                }

                break;
        }

    }

    private void handleServerNotice(Message message, Session session) {
        int result = message.getResult();
        String userId = message.getUserId();
        Message response = null;
        Message request = null;

        switch (result) {

            case 100:
                //随机模拟用户客服不断登陆，比例为（用户）3：1（客服）
                int randomLoginType = (int)(Math.random()*3) + 1;
                response = new Message();
                if (randomLoginType == 1) { // 模拟客服登陆
                    response.setType(ChatConstants.TYPE_LOGIN);
                    response.setUserId((curUserId++)+"");
                    logger.info("("+randomLoginType+")----登陆客服");
                } else { //模拟用户（匿名用户）登录
                    response.setType(ChatConstants.TYPE_CUS_LOGIN);
                    response.setAbility("1");
                    response.setLoginType(0);
                    logger.info("("+randomLoginType+")----登陆用户");
                }

                sendMessage(session, response);

                break;

            case 0:
                int messageType = message.getMessageType();
                if (messageType == 1 || messageType == 2) { //客服登陆成功
                    if (userId == null) break;
                    //保存到mUser和mSid中
                    mUser.put(userId, session);
                    mSid.put(session.getId(), userId);

                    //
                    if (messageType == 1) {
                        lstServerUser.add(userId);
                        mUserOnlineStatus.put(userId, message.getOnlineStatus());
                    }
                    else if (messageType == 2) lstClientUser.add(userId);

                    //请求服务
                    request = new Message();
                    request.setType(ChatConstants.TYPE_SERVICE_WAITING_USER);
                    request.setUserId(userId);
                    sendMessage(session, request);
                } else if (messageType == ChatConstants.TYPE_SERVICVE_INTERCHANGE) {
                    //不同意：同意——1:5
                    response = new Message();
                    response.setType(ChatConstants.TYPE_SOLVE_INTERCHANGE);
                    response.setRoomId(message.getRoomId());
                    response.setToUserId(message.getToUserId());
                    response.setUserId(message.getUserId());

                    int randomAgree = (int)(Math.random()*5)+1;
                    if (randomAgree == 1) {
                        response.setExtraData("0");
                    } else {
                        response.setExtraData("1");
                    }

                    sendMessage(mUser.get(message.getUserId()), response);
                }
                break;
        }

    }

    @Override
    public void handleClose(Session session) {
        logger.info("websocket message handle close");
    }


    public static void sendMessage(Session session, Message message) {
        session.getAsyncRemote().sendText(new Gson().toJson(message));
    }


}
