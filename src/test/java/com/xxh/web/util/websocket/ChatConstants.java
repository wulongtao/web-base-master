package com.xxh.web.util.websocket;

/**
 * Created by giant039 on 2017/3/15.
 */
public class ChatConstants {
    public static final String CLIENT_ID = "clientId";
    
    public static final int USER_TYPE_SERVICE = 1; //客服
    public static final int USER_TYPE_CLIENT = 2; //用户
    public static final int USER_TYPE_SUPERVISOR = 3; //监管

    
    /**
     * type类型
     */
    public static final int TYPE_LOGIN = 1; //客服登录
    
    public static final int TYPE_CUS_LOGIN = 2; //客户登录
    
    
    public static final int TYPE_LOGOUT = 3; //客服登出
    
    public static final int TYPE_CUS_LOGOUT = 4; //客户登出


    public static final int TYPE_SERVER_NOTICE = 5; //服务器返回消息

    public static final int TYPE_CLIENT_NOTICE = 6; //客户端返回消息
    
    public static final int TYPE_SAY = 7; // 说话
    
    public static final int TYPE_SERVICE_WAITING_USER = 8; //登录成功后请求服务等待队列中的客户
    
    public static final int TYPE_NEED_SERVICE_USER = 9; //发送给客服端，表示有用户需要服务
    
    public static final int TYPE_WANT_SERVICE_USER = 10; //客服端回应服务端是否需要服务此用户

    public static final int TYPE_ATTRIBUTE_USER = 11; //推送用户
    
    public static final int TYPE_ATTRIBUTE_USER_CHAT = 12; //用户可以开始聊天标记
    
    
    public static final int TYPE_SUPERVISOR_LOGIN = 13; //监管登录
    
    public static final int TYPE_SUPERVISOR_ENTER_ROOM = 14; //监管加入房间
    
    public static final int TYPE_SUPERVISOR_INTERCHANGE_ROOM = 15; //监管转接房间
    
    public static final int TYPE_SUPERVISOR_LEAVE_ROOM = 16; //监管转接房间
    
    public static final int TYPE_EVALUATE = 17; //咨询用户退出评价
    
    public static final int TYPE_SERVICVE_INTERCHANGE = 18; //客服之间的转接
    
    public static final int TYPE_SOLVE_INTERCHANGE = 19; //是否同意转接
    
    public static final int TYPE_INTERCHANGE_USER = 20; //
    
    public static final int TYPE_CHANGE_ONLINE_STATUS = 21; //修改在线状态
    
    public static final int TYPE_INVITE_ASSISTANT = 22;
    
    public static final int	TYPE_SOLVE_INVITE = 23; //邀请失败
    
    public static final int TYPE_PING = 999; //ping
    

    /**
     * result 错误信息类型
     */
    public static final int RESULT_NEED_LOGIN = -1000; //用户未登录
    
    public static final int RESULT_CONNECT_SUCCESS = 100; //成功连接socket
    
    public static final int RESULT_USER_NOT_EXIST = 1002; //用户不存在
    public static final int	RESULT_USER_ALREADY_LOGIN = 1003; //用户已登录
    public static final int	RESULT_USER_BIND_FAILED = 1004; //绑定账号失败
    public static final int	RESULT_CS_INTERCHANGE_FAILED = 1005; //转接客服失败
    public static final int	RESULT_WAITING_CS = 1006; //没有找到客服,加入到等待队列
    public static final int	RESULT_WAITING_USER = 1007; //没有找到客服,加入到等待队列
    public static final int	RESULT_SAY_FAILED = 1008; //说话失败
    public static final int	RESULT_EMPTY_ROOM = 1010; //加入房间失败
    public static final int	RESULT_CLIENT_LOGOUT = 1012; //客服登出失败
    public static final int	RESULT_ROOM_CREATE_FAILED = 1013; //创建房间失败
    public static final int	RESULT_SUPERVISOR_NOT_EXIST = 1014; //监管用户不存在
    public static final int	RESULT_SERVICE_REFUSED = 1015; //客服拒绝服务
    public static final int	RESULT_ADD_USER_FAILED = 1016; //添加用户失败
    public static final int	RESULT_INTERCHANGE_FAILED = 1017; //转接失败
    public static final int	RESULT_INTERCHANGE_REFUSED = 1018; //转接被拒绝
    public static final int	RESULT_USER_INFO_ERROR = 1019; //转接被拒绝
    public static final int	RESULT_CHANGE_STATUS_PARAMS_ERROR = 1020; //修改在线状态参数错误
    public static final int	RESULT_STATUS_NOT_EXISTS = 1021; //在线状态队列不存在
    public static final int	RESULT_INVITE_FAILED = 1022; //邀请失败
    public static final int	RESULT_INVITE_REFUSED = 1023; //邀请被拒绝
    
    
//    public static final int STATUS_LEAVE = 4;
    
    public static final int STATUS_ACTIVE = 1;
    public static final int STATUS_ONLINE = 2;
    public static final int STATUS_BUSY = 3;
    public static final int STATUS_LEAVE = 4;
    


}
