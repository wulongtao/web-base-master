package com.xxh.web.util.websocket;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by giant039 on 2017/3/15.
 */
public class Message extends BaseMessage {
    private String ability;
    private Integer onlineStatus;
    private String toUserId;
    private String toUserNick;
    private String toUserAvatar;
    private String nick;
    private String avatar;
    private String account;

    private String content;
    private Integer contentType;
    
    private String roomId;
    
    //用于监管的字段。标记上一次进入的房间
    private String preRoomId;
    
    private String extraData;
    
    private Integer loginType;
    
    //评价ID
    private String evaluateId;
    
    private String addTime;

    
    private Integer channel = null;
    
    private Integer userServiceCount = null;
    
    private Integer userComplaintCount = null;
    
    

    public Integer getUserServiceCount() {
		return userServiceCount;
	}

	public void setUserServiceCount(Integer userServiceCount) {
		this.userServiceCount = userServiceCount;
	}

	public Integer getUserComplaintCount() {
		return userComplaintCount;
	}

	public void setUserComplaintCount(Integer userComplaintCount) {
		this.userComplaintCount = userComplaintCount;
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public Message() {
    }

    public Message(int result, String message, int type) {
        super(result, message, type);
    }
    
    

    public Message(int result, String message, Integer type, Integer messageType, String userId) {
		super(result, message, type, messageType, userId);
	}

	public Message(int result, String message, int type, String userId, String toUserId, int contentType, String content) {
        super(result, message, type);
        setUserId(userId);
        setToUserId(toUserId);
        setContentType(contentType);
        setContent(content);
    }

    public Message(int result, String message, int type, String userId, String toUserId) {
        super(result, message, type);
        setUserId(userId);
        setToUserId(toUserId);
    }
    

    public Message(int result, String message, Integer type, Integer messageType, String userId, String toUserId, String roomId) {
		super(result, message, type, messageType, userId);
		this.roomId = roomId;
		this.toUserId = toUserId;
	}

	public String getAbility() {
        return ability;
    }

    public void setAbility(String ability) {
        this.ability = ability;
    }

    public int getOnlineStatus() {
        return onlineStatus;
    }

    public void setOnlineStatus(int onlineStatus) {
        this.onlineStatus = onlineStatus;
    }

    public String getToUserId() {
        return toUserId;
    }

    public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public void setToUserId(String toUserId) {
        this.toUserId = toUserId;
    }
    public void setOnlineStatus(Integer onlineStatus) {
        this.onlineStatus = onlineStatus;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getContentType() {
        return contentType;
    }

    public void setContentType(Integer contentType) {
        this.contentType = contentType;
    }
    
    

    public String getPreRoomId() {
		return preRoomId;
	}

	public void setPreRoomId(String preRoomId) {
		this.preRoomId = preRoomId;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}
	
	

	public String getExtraData() {
		return extraData;
	}

	public void setExtraData(String extraData) {
		this.extraData = extraData;
	}
	
	

	public Integer getLoginType() {
		return loginType;
	}

	public void setLoginType(Integer loginType) {
		this.loginType = loginType;
	}

	@Override
    public String toString() {
        return "Message{" +
                "ability=" + ability +
                ", onlineStatus=" + onlineStatus +
                ", account=" + account +
                ", roomId=" + roomId +
                "} " + super.toString();
    }

	public String getEvaluateId() {
		return evaluateId;
	}

	public void setEvaluateId(String evaluateId) {
		this.evaluateId = evaluateId;
	}

	public String getToUserNick() {
		return toUserNick;
	}

	public void setToUserNick(String toUserNick) {
		this.toUserNick = toUserNick;
	}

	public String getToUserAvatar() {
		return toUserAvatar;
	}

	public void setToUserAvatar(String toUserAvatar) {
		this.toUserAvatar = toUserAvatar;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	
	
	
	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}


	public Integer getChannel() {
		return channel;
	}

	public void setChannel(Integer channel) {
		this.channel = channel;
	}
}
