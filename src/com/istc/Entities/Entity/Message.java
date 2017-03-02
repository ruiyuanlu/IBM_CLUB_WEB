package com.istc.Entities.Entity;

/**
 * Created by lurui on 2017/2/21 0021.
 */

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;

/**
 * 站内消息实体类
 * 目标: 使用Json简化数据格式，使得消息的内容格式能够根据需要灵活变化，同时不影响数据库架构设计
 * Json 直接以字符串形式存储, 前端解析
 */
@Entity
public class Message implements Serializable{
    @Id
    private Integer id;
    @OneToOne
    private Member sender;
    @OneToMany
    private Member receiver;
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar sendTime;
    @Lob
    private String messageContentJson;


    public Message() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMessageContentJson() {
        return messageContentJson;
    }

    public void setMessageContentJson(String messageContentJson) {
        this.messageContentJson = messageContentJson;
    }

    public Member getReceiver() {
        return receiver;
    }

    public void setReceiver(Member receiver) {
        this.receiver = receiver;
    }

    public Member getSender() {
        return sender;
    }

    public void setSender(Member sender) {
        this.sender = sender;
    }

    public Calendar getSendTime() {
        return sendTime;
    }

    public void setSendTime(Calendar sendTime) {
        this.sendTime = sendTime;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", sender=" + sender +
                ", receiver=" + receiver +
                ", sendTime=" + sendTime +
                ", messageContentJson=" + messageContentJson +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Message)) return false;

        Message message = (Message) o;

        if (id != null ? !id.equals(message.id) : message.id != null) return false;
        if (sender != null ? !sender.equals(message.sender) : message.sender != null) return false;
        if (receiver != null ? !receiver.equals(message.receiver) : message.receiver != null) return false;
        if (sendTime != null ? !sendTime.equals(message.sendTime) : message.sendTime != null) return false;
        return messageContentJson != null ? messageContentJson.equals(message.messageContentJson) : message.messageContentJson == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (sender != null ? sender.hashCode() : 0);
        result = 31 * result + (receiver != null ? receiver.hashCode() : 0);
        result = 31 * result + (sendTime != null ? sendTime.hashCode() : 0);
        result = 31 * result + (messageContentJson != null ? messageContentJson.hashCode() : 0);
        return result;
    }
}
