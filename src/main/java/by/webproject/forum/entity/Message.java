package by.webproject.forum.entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class Message {
    private long messageId;
    private User to;
    private User from;
    private LocalDateTime sendDate;
    private String text;

    public Message(Builder builder) {
        this.messageId = messageId;
        this.to = to;
        this.from = from;
        this.sendDate = sendDate;
        this.text = text;
    }

    public long getMessageId() {
        return messageId;
    }

    public void setMessageId(long messageId) {
        this.messageId = messageId;
    }

    public User getTo() {
        return to;
    }

    public void setTo(User to) {
        this.to = to;
    }

    public User getFrom() {
        return from;
    }

    public void setFrom(User from) {
        this.from = from;
    }

    public LocalDateTime getSendDate() {
        return sendDate;
    }

    public void setSendDate(LocalDateTime sendDate) {
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
                ", to=" + to +
                ", from=" + from +
                ", sendDate=" + sendDate +
                ", text='" + text + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return messageId == message.messageId && Objects.equals(to, message.to) && Objects.equals(from, message.from) && Objects.equals(sendDate, message.sendDate) && Objects.equals(text, message.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(messageId, to, from, sendDate, text);
    }
    public static class Builder{
        private long messageId;
        private User to;
        private User from;
        private LocalDateTime sendDate;
        private String text;

    }

}
