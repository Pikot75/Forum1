package by.webproject.forum.entity;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

public class Message {
    private long messageId;
    private int messageTo;
    private int messageFrom;
    private Date sendDate;
    private String text;

    public Message(Builder builder) {
        messageId = builder.messageId;
        messageTo = builder.messageTo;
        messageFrom = builder.messageFrom;
        sendDate = builder.sendDate;
        text = builder.text;
    }

    public long getMessageId() {
        return messageId;
    }

    public void setMessageId(long messageId) {
        this.messageId = messageId;
    }

    public int getMessageTo() {
        return messageTo;
    }

    public void setMessageTo(int messageTo) {
        this.messageTo = messageTo;
    }

    public int getMessageFrom() {
        return messageFrom;
    }

    public void setMessageFrom(int messageFrom) {
        this.messageFrom = messageFrom;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Message{" +
                "messageId=" + messageId +
                ", messageTo=" + messageTo +
                ", messageFrom=" + messageFrom +
                ", sendDate=" + sendDate +
                ", text='" + text + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return messageId == message.messageId && Objects.equals(messageTo, message.messageTo) && Objects.equals(messageFrom, message.messageFrom) && Objects.equals(sendDate, message.sendDate) && Objects.equals(text, message.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(messageId, messageTo, messageFrom, sendDate, text);
    }

    public static class Builder {
        private long messageId;
        private int messageTo;
        private int messageFrom;
        private Date sendDate;
        private String text;

        public Builder withMessageId(Long messageId) {
            this.messageId = messageId;
            return this;
        }

        public Builder withUserMessageTo(int messageTo) {
            this.messageTo = messageTo;
            return this;
        }

        public Builder withUserMessageFrom(int messageFrom) {
            this.messageFrom = messageFrom;
            return this;
        }

        public Builder withSendDate(Date sendDate) {
            this.sendDate = sendDate;
            return this;
        }

        public Builder withText(String text) {
            this.text = text;
            return this;
        }

        public Message build() {
            return new Message(this);
        }
    }
}


