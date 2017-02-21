package com.istc.Entities.TestEntities;

/**
 * Created by lurui on 2017/2/21 0021.
 */

import com.istc.Entities.Member;
import com.istc.Utilities.JsonUtils.JsonType;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Calendar;

/**
 * 站内消息实体类
 * 目标: 使用Json简化数据格式，使得消息的内容格式能够根据需要灵活变化，同时不影响数据库架构设计
 * JsonType 是使用Hibernate实现 Json 字符串存储到数据库的POJO类，与hibernate的映射的实现基于
 * JsonTypeForHibernate 和 JsonTypeMySQLDialect
 */
public class Message {
    @Id
    private Integer id;
    @OneToOne
    private Member sender;
    @OneToOne
    private Member receiver;
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar sendTime;

    /**
     * 自定义 Json 类型
     */
    @Column
    @Type(type = "com.istc.Utilities.JsonUtils.JsonType")
    private JsonType messageContent;


    public Message() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public JsonType getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(JsonType messageContent) {
        this.messageContent = messageContent;
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
                ", messageContent=" + messageContent +
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
        return messageContent != null ? messageContent.equals(message.messageContent) : message.messageContent == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (sender != null ? sender.hashCode() : 0);
        result = 31 * result + (receiver != null ? receiver.hashCode() : 0);
        result = 31 * result + (sendTime != null ? sendTime.hashCode() : 0);
        result = 31 * result + (messageContent != null ? messageContent.hashCode() : 0);
        return result;
    }
}
