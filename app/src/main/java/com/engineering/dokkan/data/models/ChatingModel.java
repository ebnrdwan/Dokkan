package com.engineering.dokkan.data.models;

public class ChatingModel {
    //identifier
    public final static int MSG_TYPE_SENT = 0;
    public final static int MSG_TYPE_RECEIVED = 1;

    private String msgContent;
    private int msgType;//send or recive

    public ChatingModel(int msgType, String msgContent) {
        this.msgType = msgType;
        this.msgContent = msgContent;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    public int getMsgType() {
        return msgType;
    }

    public void setMsgType(int msgType) {
        this.msgType = msgType;
    }

}
