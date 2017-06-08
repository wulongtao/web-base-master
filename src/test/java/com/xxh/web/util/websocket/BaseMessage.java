package com.xxh.web.util.websocket;

/**
 * Created by giant039 on 2017/3/15.
 */
public class BaseMessage {
    private Integer result;
    private String message;
    private Integer type;
    private Integer messageType;
    private String userId;

    public BaseMessage() {
    }

    public BaseMessage(int result, String message, int type) {
        this.result = result;
        this.message = message;
        this.type = type;
    }

    public BaseMessage(int result, String message, int type, String userId) {
        this.result = result;
        this.message = message;
        this.type = type;
        this.userId = userId;
    }
    
    

    public BaseMessage(int result, String message, Integer type, Integer messageType, String userId) {
		super();
		this.result = result;
		this.message = message;
		this.type = type;
		this.messageType = messageType;
		this.userId = userId;
	}

	public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getMessageType() {
        return messageType;
    }

    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "BaseMessage{" +
                "result=" + result +
                ", message='" + message + '\'' +
                ", type=" + type +
                ", messageType=" + messageType +
                ", userId=" + userId +
                '}';
    }
}
